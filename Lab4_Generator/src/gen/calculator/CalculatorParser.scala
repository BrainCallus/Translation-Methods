
package calculator;

import CalculatorParser._
import grammar.LexerRule
import template.AbstractLexer
import template.AbstractLexer.LexerParams
import util.Tree
import util.GrammarTree
import util.GrammarTree._
import util.CommonUtils._
import java.text.ParseException
import java.io.InputStream
import cats.data.State
import template.Tokenized._

case class CalculatorParser(inputStream: InputStream) {
	val lex: CalculatorLexer = CalculatorLexer(inputStream, CalculatorLexer(inputStream, LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken())

	def calculator() : State[CalculatorLexer, CalculatorContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						e <- expr()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
						e1 <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.EOF)) {
							throw new ParseException("Expected EOF, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = e.res;
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield CalculatorContext("calculator", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def term() : State[CalculatorLexer, TermContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						h <- highPrior()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
						m <- muldiv(h.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => m :: list)
						
						// USER CODE
						res = m.res;
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield TermContext("term", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def muldiv(in : Double) : State[CalculatorLexer, MuldivContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.MUL))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						m <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.MUL)) {
							throw new ParseException("Expected MUL, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						h <- highPrior()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
						mm <- muldiv(in * h.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => mm :: list)
						
						// USER CODE
						res = mm.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield MuldivContext("muldiv", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.DIV))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						d <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.DIV)) {
							throw new ParseException("Expected DIV, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						h <- highPrior()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
						mm <- muldiv(in / h.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => mm :: list)
						
						// USER CODE
						res = mm.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield MuldivContext("muldiv", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.ADD, CalculatorToken.SUB, CalculatorToken.EOF, CalculatorToken.RPAREN))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						
						// USER CODE
						res = in;
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield MuldivContext("muldiv", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def expr() : State[CalculatorLexer, ExprContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						h <- highPrior()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
						m <- muldiv(h.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => m :: list)
						as <- addsub(m.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => as :: list)
						
						// USER CODE
						res = as.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield ExprContext("expr", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def highPrior() : State[CalculatorLexer, HighPriorContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						a <- atom()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => a :: list)
						e <- log(a.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
						
						// USER CODE
						res = e.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield HighPriorContext("highPrior", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def log(in : Double) : State[CalculatorLexer, LogContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.LOG))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						l <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.LOG)) {
							throw new ParseException("Expected LOG, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						a <- atom()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => a :: list)
						e <- log(a.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
						
						// USER CODE
						res = math.log10(in) / math.log10(e.res)
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield LogContext("log", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN, CalculatorToken.SUB, CalculatorToken.ADD, CalculatorToken.DIV, CalculatorToken.MUL))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						
						// USER CODE
						res = in;
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield LogContext("log", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def addsub(in : Double) : State[CalculatorLexer, AddsubContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.ADD))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						a <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.ADD)) {
							throw new ParseException("Expected ADD, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						t <- term()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => t :: list)
						as <- addsub(in + t.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => as :: list)
						
						// USER CODE
						res = as.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield AddsubContext("addsub", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.SUB))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						s <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.SUB)) {
							throw new ParseException("Expected SUB, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						t <- term()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => t :: list)
						as <- addsub(in - t.res)
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => as :: list)
						
						// USER CODE
						res = as.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield AddsubContext("addsub", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						
						// USER CODE
						res = in;
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield AddsubContext("addsub", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def atom() : State[CalculatorLexer, AtomContext] = {
		for { 
			lex <- State.get[CalculatorLexer]
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						s <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.SUB)) {
							throw new ParseException("Expected SUB, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						l <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.LPAREN)) {
							throw new ParseException("Expected LPAREN, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						e <- expr()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
						r <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.RPAREN)) {
							throw new ParseException("Expected RPAREN, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = -e.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield AtomContext("atom", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.LPAREN))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						l <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.LPAREN)) {
							throw new ParseException("Expected LPAREN, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						e <- expr()
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
						r <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.RPAREN)) {
							throw new ParseException("Expected RPAREN, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = e.res
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield AtomContext("atom", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(CalculatorToken.NUMBER))) {
					for {
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](List.empty)
						d <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[CalculatorLexer]
						_ = if(!lexer.compareToken(CalculatorToken.NUMBER)) {
							throw new ParseException("Expected NUMBER, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = (d.text).toDouble
						// END USER CODE
						
						children <- State.pure[CalculatorLexer, List[GrammarTree[_]]](curState)
					} yield AtomContext("atom", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}
}

object CalculatorParser {

	case class CalculatorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): CalculatorContext = CalculatorContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): CalculatorContext = CalculatorContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class TermContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): TermContext = TermContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): TermContext = TermContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class MuldivContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): MuldivContext = MuldivContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): MuldivContext = MuldivContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class ExprContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): ExprContext = ExprContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): ExprContext = ExprContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class HighPriorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): HighPriorContext = HighPriorContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): HighPriorContext = HighPriorContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class LogContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): LogContext = LogContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): LogContext = LogContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class AddsubContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): AddsubContext = AddsubContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): AddsubContext = AddsubContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class AtomContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): AtomContext = AtomContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): AtomContext = AtomContext(ctxRoot, ctxChildren ++ List(child))
	 }
}

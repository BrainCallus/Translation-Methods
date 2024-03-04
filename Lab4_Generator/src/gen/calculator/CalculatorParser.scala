
package calculator;

import CalculatorParser._;
import grammar.LexerRule;
import template.AbstractLexer;
import template.AbstractLexer.LexerParams;
import util.Tree;
import util.GrammarTree;
import util.GrammarTree._;
import util.CommonUtils._;
import java.text.ParseException;
import java.io.InputStream;
import cats.data.State;
import template.Tokenized._;

case class CalculatorParser(inputStream: InputStream) {
	val lex: CalculatorLexer = CalculatorLexer(inputStream, CalculatorLexer(inputStream, LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken())

	def calculator() : State[CalculatorLexer, CalculatorContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						e <- expr()
						curState <- getCurState[CalculatorLexer](curState).map(list => e :: list)
						e1 <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.EOF)) {
								throw new ParseException("Expected EOF, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = e.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(CalculatorContext("calculator", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def term() : State[CalculatorLexer, TermContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						h <- highPrior()
						curState <- getCurState[CalculatorLexer](curState).map(list => h :: list)
						m <- muldiv(h.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => m :: list)
						
						// USER CODE
						res = m.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(TermContext("term", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def muldiv(in : Double) : State[CalculatorLexer, MuldivContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.MUL))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						m <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.MUL)) {
								throw new ParseException("Expected MUL, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						h <- highPrior()
						curState <- getCurState[CalculatorLexer](curState).map(list => h :: list)
						mm <- muldiv(in * h.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => mm :: list)
						
						// USER CODE
						res = mm.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(MuldivContext("muldiv", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.DIV))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						d <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.DIV)) {
								throw new ParseException("Expected DIV, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						h <- highPrior()
						curState <- getCurState[CalculatorLexer](curState).map(list => h :: list)
						mm <- muldiv(in / h.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => mm :: list)
						
						// USER CODE
						res = mm.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(MuldivContext("muldiv", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.ADD, CalculatorToken.SUB, CalculatorToken.EOF, CalculatorToken.RPAREN))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						
						// USER CODE
						res = in;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(MuldivContext("muldiv", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def expr() : State[CalculatorLexer, ExprContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						h <- highPrior()
						curState <- getCurState[CalculatorLexer](curState).map(list => h :: list)
						m <- muldiv(h.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => m :: list)
						as <- addsub(m.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => as :: list)
						
						// USER CODE
						res = as.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(ExprContext("expr", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def highPrior() : State[CalculatorLexer, HighPriorContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						a <- atom()
						curState <- getCurState[CalculatorLexer](curState).map(list => a :: list)
						e <- log(a.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => e :: list)
						
						// USER CODE
						res = e.res
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(HighPriorContext("highPrior", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def log(in : Double) : State[CalculatorLexer, LogContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.LOG))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						l <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.LOG)) {
								throw new ParseException("Expected LOG, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						a <- atom()
						curState <- getCurState[CalculatorLexer](curState).map(list => a :: list)
						e <- log(a.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => e :: list)
						
						// USER CODE
						res = math.log10(in) / math.log10(e.res);
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(LogContext("log", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN, CalculatorToken.SUB, CalculatorToken.ADD, CalculatorToken.DIV, CalculatorToken.MUL))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						
						// USER CODE
						res = in;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(LogContext("log", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def addsub(in : Double) : State[CalculatorLexer, AddsubContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.ADD))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						a <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.ADD)) {
								throw new ParseException("Expected ADD, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						t <- term()
						curState <- getCurState[CalculatorLexer](curState).map(list => t :: list)
						as <- addsub(in + t.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => as :: list)
						
						// USER CODE
						res = as.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(AddsubContext("addsub", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.SUB))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						s <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.SUB)) {
								throw new ParseException("Expected SUB, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						t <- term()
						curState <- getCurState[CalculatorLexer](curState).map(list => t :: list)
						as <- addsub(in - t.res)
						curState <- getCurState[CalculatorLexer](curState).map(list => as :: list)
						
						// USER CODE
						res = as.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(AddsubContext("addsub", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						
						// USER CODE
						res = in;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(AddsubContext("addsub", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def atom() : State[CalculatorLexer, AtomContext] = {
		for { 
			lex <- getEmptyListState[CalculatorLexer].inspect(lex => lex)
			res <- 
				if(lex.curTokenIn(Set(CalculatorToken.SUB))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						s <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.SUB)) {
								throw new ParseException("Expected SUB, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						l <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.LPAREN)) {
								throw new ParseException("Expected LPAREN, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						e <- expr()
						curState <- getCurState[CalculatorLexer](curState).map(list => e :: list)
						r <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.RPAREN)) {
								throw new ParseException("Expected RPAREN, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = -e.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(AtomContext("atom", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.LPAREN))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						l <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.LPAREN)) {
								throw new ParseException("Expected LPAREN, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						e <- expr()
						curState <- getCurState[CalculatorLexer](curState).map(list => e :: list)
						r <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.RPAREN)) {
								throw new ParseException("Expected RPAREN, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = e.res;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(AtomContext("atom", children.reverse, res))
					result
				}
				else if(lex.curTokenIn(Set(CalculatorToken.NUMBER))) {
					val result = for {
						curState <- getEmptyListState[CalculatorLexer]
						d <- getCurState[CalculatorLexer](curState).inspect(lexer => lexer.curToken())
						_ <- getCurState[CalculatorLexer](curState).inspect(lexer => {
							if(!lexer.compareToken(CalculatorToken.NUMBER)) {
								throw new ParseException("Expected NUMBER, found:" + lexer.curToken().text, lexer.curPos())
							}
						})
						curState <- getCurState[CalculatorLexer](curState)
							.inspect(lexer => TerminalTree(lexer.curToken()) :: curState)
							.modify(lexer => CalculatorLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = (d.text).toDouble;
						// END USER CODE
						
						children <- getCurState[CalculatorLexer](curState)
					} yield(AtomContext("atom", children.reverse, res))
					result
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}
}

object CalculatorParser {

	case class CalculatorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = CalculatorContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = CalculatorContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class TermContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = TermContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = TermContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class MuldivContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = MuldivContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = MuldivContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class ExprContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = ExprContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = ExprContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class HighPriorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = HighPriorContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = HighPriorContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class LogContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = LogContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = LogContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class AddsubContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = AddsubContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = AddsubContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class AtomContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, val res: Double = 0) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]) = AtomContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = AtomContext(ctxRoot, ctxChildren ++ List(child))
	 }
}

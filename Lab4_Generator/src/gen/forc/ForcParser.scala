
package forc;

import ForcParser._
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

case class ForcParser(inputStream: InputStream) {
	val lex: ForcLexer = ForcLexer(inputStream, ForcLexer(inputStream, LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken())

	def cmp() : State[ForcLexer, CmpContext] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.LT))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						l <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.LT)) {
							throw new ParseException("Expected LT, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = CmpContext("CMP", List(TerminalTree(l)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield CmpContext("cmp", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(ForcToken.GT))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						g <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.GT)) {
							throw new ParseException("Expected GT, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = CmpContext("CMP", List(TerminalTree(g)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield CmpContext("cmp", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(ForcToken.NGT))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						ng <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NGT)) {
							throw new ParseException("Expected NGT, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = CmpContext("CMP", List(TerminalTree(ng)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield CmpContext("cmp", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(ForcToken.NLT))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						nl <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NLT)) {
							throw new ParseException("Expected NLT, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = CmpContext("CMP", List(TerminalTree(nl)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield CmpContext("cmp", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def p3(name : String) : State[ForcLexer, P3Context] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.NAME))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						n <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NAME)) {
							throw new ParseException("Expected NAME, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						c3 = if(n.text != name) {
						    throw new ParseException(s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}", lex.curPos())
						}
						// END USER CODE
						
						i <- incdec()
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).map(list => i :: list)
						
						// USER CODE
						res = P3Context("P3", List(TerminalTree(n), i.res))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield P3Context("p3", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def p2(name : String) : State[ForcLexer, P2Context] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.NAME))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						n <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NAME)) {
							throw new ParseException("Expected NAME, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						c2 = if(n.text != name) {
						    throw new ParseException(s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}", lex.curPos())
						}
						// END USER CODE
						
						c <- cmp()
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).map(list => c :: list)
						num <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NUMBER)) {
							throw new ParseException("Expected NUMBER, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = P2Context("P2", List(TerminalTree(n), c.res, TerminalTree(num)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield P2Context("p2", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def forc() : State[ForcLexer, ForcContext] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.FOR))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						f <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.FOR)) {
							throw new ParseException("Expected FOR, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						l <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.LPAREN)) {
							throw new ParseException("Expected LPAREN, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						i <- inner()
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).map(list => i :: list)
						r <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.RPAREN)) {
							throw new ParseException("Expected RPAREN, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						e <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.EOF)) {
							throw new ParseException("Expected EOF, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = ForcContext("S", List(TerminalTree(f), TerminalTree(l), i.res, TerminalTree(r), TerminalTree(e)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield ForcContext("forc", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def inner() : State[ForcLexer, InnerContext] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.TYPE_NAME))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						pp1 <- p1()
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).map(list => pp1 :: list)
						s1 <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.SEMICOLON)) {
							throw new ParseException("Expected SEMICOLON, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						pp2 <- p2(pp1.name)
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).map(list => pp2 :: list)
						s2 <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.SEMICOLON)) {
							throw new ParseException("Expected SEMICOLON, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						pp3 <- p3(pp1.name)
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).map(list => pp3 :: list)
						
						// USER CODE
						res = InnerContext("INNER", List(pp1.res, TerminalTree(s1),  pp2.res, TerminalTree(s2), pp3.res))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield InnerContext("inner", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def p1() : State[ForcLexer, P1Context] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.TYPE_NAME))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						t <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.TYPE_NAME)) {
							throw new ParseException("Expected TYPE_NAME, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						n <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NAME)) {
							throw new ParseException("Expected NAME, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						e <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.EQ)) {
							throw new ParseException("Expected EQ, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						num <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.NUMBER)) {
							throw new ParseException("Expected NUMBER, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						name = n.text
						res = P1Context("P1", List(TerminalTree(t), TerminalTree(n),  TerminalTree(e), TerminalTree(num)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield P1Context("p1", children.reverse, name, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}

	def incdec() : State[ForcLexer, IncdecContext] = {
		for { 
			lex <- State.get[ForcLexer]
			res <- 
				if(lex.curTokenIn(Set(ForcToken.INC))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						i <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.INC)) {
							throw new ParseException("Expected INC, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = IncdecContext("INC_DEC", List(TerminalTree(i)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield IncdecContext("incdec", children.reverse, res)
				}
				else if(lex.curTokenIn(Set(ForcToken.DEC))) {
					for {
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](List.empty)
						d <- State.pure[ForcLexer, List[GrammarTree[_]]](curState).inspect(lexer => lexer.curToken())
						lexer <- State.get[ForcLexer]
						_ = if(!lexer.compareToken(ForcToken.DEC)) {
							throw new ParseException("Expected DEC, found:" + lexer.curToken().text, lexer.curPos())
						}
						curState <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
							.map(list => TerminalTree(lexer.curToken()) :: list)
							.modify(lexer => ForcLexer(lexer.inputStream, lexer.nextToken()))
						
						// USER CODE
						res = IncdecContext("INC_DEC", List(TerminalTree(d)))
						// END USER CODE
						
						children <- State.pure[ForcLexer, List[GrammarTree[_]]](curState)
					} yield IncdecContext("incdec", children.reverse, res)
				}
				else {
					throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
				}
		} yield res
	}
}

object ForcParser {

	case class CmpContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): CmpContext = CmpContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): CmpContext = CmpContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class P3Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): P3Context = P3Context(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): P3Context = P3Context(ctxRoot, ctxChildren ++ List(child))
	 }

	case class P2Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): P2Context = P2Context(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): P2Context = P2Context(ctxRoot, ctxChildren ++ List(child))
	 }

	case class ForcContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): ForcContext = ForcContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): ForcContext = ForcContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class InnerContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): InnerContext = InnerContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): InnerContext = InnerContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class P1Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, name: String = null, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): P1Context = P1Context(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): P1Context = P1Context(ctxRoot, ctxChildren ++ List(child))
	 }

	case class IncdecContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null) extends ContextTree(ctxRoot, ctxChildren) {
		override def pushFirstChild(child: GrammarTree[_]): IncdecContext = IncdecContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]): IncdecContext = IncdecContext(ctxRoot, ctxChildren ++ List(child))
	 }
}


package forc;

import grammar.LexerRule;
import template.AbstractLexer;
import util.Tree;
import util.GrammarTree;
import util.GrammarTree._;
import java.text.ParseException;
import java.io.InputStream;
import template.Tokenized._;

case class ForcParser(inputStream: InputStream) {
	val lex: ForcLexer = ForcLexer(inputStream)
	lex.lexerParams = lex.nextToken()
	case class CmpContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = CmpContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = CmpContext(ctxRoot, ctxChildren ++ List(child))
	 }
	def cmp() : CmpContext = {
		var ctx = CmpContext("cmp")
		if(lex.curTokenIn(Set(ForcToken.LT))) {
			if(!lex.compareToken(ForcToken.LT)) {
				throw new ParseException("Expected LT, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val l = lex.curToken()
			lex.nextToken()
			ctx.res = CmpContext("CMP", List(TerminalTree(l)))
			ctx
		}
		else if(lex.curTokenIn(Set(ForcToken.GT))) {
			if(!lex.compareToken(ForcToken.GT)) {
				throw new ParseException("Expected GT, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val g = lex.curToken()
			lex.nextToken()
			ctx.res = CmpContext("CMP", List(TerminalTree(g)))
			ctx
		}
		else if(lex.curTokenIn(Set(ForcToken.NGT))) {
			if(!lex.compareToken(ForcToken.NGT)) {
				throw new ParseException("Expected NGT, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val ng = lex.curToken()
			lex.nextToken()
			ctx.res = CmpContext("CMP", List(TerminalTree(ng)))
			ctx
		}
		else if(lex.curTokenIn(Set(ForcToken.NLT))) {
			if(!lex.compareToken(ForcToken.NLT)) {
				throw new ParseException("Expected NLT, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val nl = lex.curToken()
			lex.nextToken()
			ctx.res = CmpContext("CMP", List(TerminalTree(nl)))
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
	case class P3Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = P3Context(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = P3Context(ctxRoot, ctxChildren ++ List(child))
	 }
	def p3(name : String) : P3Context = {
		var ctx = P3Context("p3")
		if(lex.curTokenIn(Set(ForcToken.NAME))) {
			if(!lex.compareToken(ForcToken.NAME)) {
				throw new ParseException("Expected NAME, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val n = lex.curToken()
			lex.nextToken()
			if(n.text != name) {
			    throw new ParseException(s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}", lex.curPos())
			}
			val i = incdec()
			ctx = ctx.appendLastChild(i)
			ctx.res = P3Context("P3", List(TerminalTree(n), i.res));
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
	case class P2Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = P2Context(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = P2Context(ctxRoot, ctxChildren ++ List(child))
	 }
	def p2(name : String) : P2Context = {
		var ctx = P2Context("p2")
		if(lex.curTokenIn(Set(ForcToken.NAME))) {
			if(!lex.compareToken(ForcToken.NAME)) {
				throw new ParseException("Expected NAME, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val n = lex.curToken()
			lex.nextToken()
			if(n.text != name) {
			    throw new ParseException(s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}", lex.curPos())
			}
			val c = cmp()
			ctx = ctx.appendLastChild(c)
			if(!lex.compareToken(ForcToken.NUMBER)) {
				throw new ParseException("Expected NUMBER, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val num = lex.curToken()
			lex.nextToken()
			ctx.res = P2Context("P2", List(TerminalTree(n), c.res, TerminalTree(num)))
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
	case class ForcContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = ForcContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = ForcContext(ctxRoot, ctxChildren ++ List(child))
	 }
	def forc() : ForcContext = {
		var ctx = ForcContext("forc")
		if(lex.curTokenIn(Set(ForcToken.FOR))) {
			if(!lex.compareToken(ForcToken.FOR)) {
				throw new ParseException("Expected FOR, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val f = lex.curToken()
			lex.nextToken()
			if(!lex.compareToken(ForcToken.LPAREN)) {
				throw new ParseException("Expected LPAREN, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val l = lex.curToken()
			lex.nextToken()
			val i = inner()
			ctx = ctx.appendLastChild(i)
			if(!lex.compareToken(ForcToken.RPAREN)) {
				throw new ParseException("Expected RPAREN, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val r = lex.curToken()
			lex.nextToken()
			if(!lex.compareToken(ForcToken.EOF)) {
				throw new ParseException("Expected EOF, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val e = lex.curToken()
			lex.nextToken()
			ctx.res = ForcContext("S", List(TerminalTree(f), TerminalTree(l), i.res, TerminalTree(r), TerminalTree(e)))
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
	case class InnerContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = InnerContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = InnerContext(ctxRoot, ctxChildren ++ List(child))
	 }
	def inner() : InnerContext = {
		var ctx = InnerContext("inner")
		if(lex.curTokenIn(Set(ForcToken.TYPE_NAME))) {
			val pp1 = p1()
			ctx = ctx.appendLastChild(pp1)
			if(!lex.compareToken(ForcToken.SEMICOLON)) {
				throw new ParseException("Expected SEMICOLON, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val s1 = lex.curToken()
			lex.nextToken()
			val pp2 = p2(pp1.name)
			ctx = ctx.appendLastChild(pp2)
			if(!lex.compareToken(ForcToken.SEMICOLON)) {
				throw new ParseException("Expected SEMICOLON, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val s2 = lex.curToken()
			lex.nextToken()
			val pp3 = p3(pp1.name)
			ctx = ctx.appendLastChild(pp3)
			ctx.res = InnerContext("INNER", List(pp1.res, TerminalTree(s1),  pp2.res, TerminalTree(s2), pp3.res))
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
	case class P1Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var name: String = null
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = P1Context(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = P1Context(ctxRoot, ctxChildren ++ List(child))
	 }
	def p1() : P1Context = {
		var ctx = P1Context("p1")
		if(lex.curTokenIn(Set(ForcToken.TYPE_NAME))) {
			if(!lex.compareToken(ForcToken.TYPE_NAME)) {
				throw new ParseException("Expected TYPE_NAME, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val t = lex.curToken()
			lex.nextToken()
			if(!lex.compareToken(ForcToken.NAME)) {
				throw new ParseException("Expected NAME, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val n = lex.curToken()
			lex.nextToken()
			if(!lex.compareToken(ForcToken.EQ)) {
				throw new ParseException("Expected EQ, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val e = lex.curToken()
			lex.nextToken()
			if(!lex.compareToken(ForcToken.NUMBER)) {
				throw new ParseException("Expected NUMBER, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val num = lex.curToken()
			lex.nextToken()
			ctx.name = n.text;
			                            ctx.res = P1Context("P1", List(TerminalTree(t), TerminalTree(n),  TerminalTree(e), TerminalTree(num)));
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
	case class IncdecContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: ContextTree = null
		override def pushFirstChild(child: GrammarTree[_]) = IncdecContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = IncdecContext(ctxRoot, ctxChildren ++ List(child))
	 }
	def incdec() : IncdecContext = {
		var ctx = IncdecContext("incdec")
		if(lex.curTokenIn(Set(ForcToken.INC))) {
			if(!lex.compareToken(ForcToken.INC)) {
				throw new ParseException("Expected INC, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val i = lex.curToken()
			lex.nextToken()
			ctx.res = IncdecContext("INC_DEC", List(TerminalTree(i)));
			ctx
		}
		else if(lex.curTokenIn(Set(ForcToken.DEC))) {
			if(!lex.compareToken(ForcToken.DEC)) {
				throw new ParseException("Expected DEC, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val d = lex.curToken()
			lex.nextToken()
			ctx.res = IncdecContext("INC_DEC", List(TerminalTree(d)))
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
}

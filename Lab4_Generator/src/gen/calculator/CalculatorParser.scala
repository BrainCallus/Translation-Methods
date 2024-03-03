
package calculator;

import CalculatorParser._;
import grammar.LexerRule;
import template.AbstractLexer;
import util.Tree;
import util.GrammarTree;
import util.GrammarTree._;
import java.text.ParseException;
import java.io.InputStream;
import template.Tokenized._;

case class CalculatorParser(inputStream: InputStream) {
	val lex: CalculatorLexer = CalculatorLexer(inputStream)
	lex.lexerParams = lex.nextToken()

	def calculator() : CalculatorContext = {
		var ctx = CalculatorContext("calculator")
		if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
			val e = expr()
			ctx = ctx.appendLastChild(e)
			if(!lex.compareToken(CalculatorToken.EOF)) {
				throw new ParseException("Expected EOF, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val e1 = lex.curToken()
			lex.nextToken()
			ctx.res = e.res;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def term() : TermContext = {
		var ctx = TermContext("term")
		if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
			val h = highPrior()
			ctx = ctx.appendLastChild(h)
			val m = muldiv(h.res)
			ctx = ctx.appendLastChild(m)
			ctx.res = m.res;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def muldiv(in : Double) : MuldivContext = {
		var ctx = MuldivContext("muldiv")
		if(lex.curTokenIn(Set(CalculatorToken.MUL))) {
			if(!lex.compareToken(CalculatorToken.MUL)) {
				throw new ParseException("Expected MUL, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val m = lex.curToken()
			lex.nextToken()
			val h = highPrior()
			ctx = ctx.appendLastChild(h)
			val mm = muldiv(in * h.res)
			ctx = ctx.appendLastChild(mm)
			ctx.res = mm.res;
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.DIV))) {
			if(!lex.compareToken(CalculatorToken.DIV)) {
				throw new ParseException("Expected DIV, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val d = lex.curToken()
			lex.nextToken()
			val h = highPrior()
			ctx = ctx.appendLastChild(h)
			val mm = muldiv(in / h.res)
			ctx = ctx.appendLastChild(mm)
			ctx.res = mm.res;
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.ADD, CalculatorToken.SUB, CalculatorToken.EOF, CalculatorToken.RPAREN))) {
			ctx.res = in;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def expr() : ExprContext = {
		var ctx = ExprContext("expr")
		if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
			val h = highPrior()
			ctx = ctx.appendLastChild(h)
			val m = muldiv(h.res)
			ctx = ctx.appendLastChild(m)
			val as = addsub(m.res)
			ctx = ctx.appendLastChild(as)
			ctx.res = as.res;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def highPrior() : HighPriorContext = {
		var ctx = HighPriorContext("highPrior")
		if(lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
			val a = atom()
			ctx = ctx.appendLastChild(a)
			val e = log(a.res)
			ctx = ctx.appendLastChild(e)
			ctx.res = e.res
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def log(in : Double) : LogContext = {
		var ctx = LogContext("log")
		if(lex.curTokenIn(Set(CalculatorToken.LOG))) {
			if(!lex.compareToken(CalculatorToken.LOG)) {
				throw new ParseException("Expected LOG, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val l = lex.curToken()
			lex.nextToken()
			val a = atom()
			ctx = ctx.appendLastChild(a)
			val e = log(a.res)
			ctx = ctx.appendLastChild(e)
			ctx.res = math.log10(in) / math.log10(e.res);
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN, CalculatorToken.SUB, CalculatorToken.ADD, CalculatorToken.DIV, CalculatorToken.MUL))) {
			ctx.res = in;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def addsub(in : Double) : AddsubContext = {
		var ctx = AddsubContext("addsub")
		if(lex.curTokenIn(Set(CalculatorToken.ADD))) {
			if(!lex.compareToken(CalculatorToken.ADD)) {
				throw new ParseException("Expected ADD, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val a = lex.curToken()
			lex.nextToken()
			val t = term()
			ctx = ctx.appendLastChild(t)
			val as = addsub(in + t.res)
			ctx = ctx.appendLastChild(as)
			ctx.res = as.res;
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.SUB))) {
			if(!lex.compareToken(CalculatorToken.SUB)) {
				throw new ParseException("Expected SUB, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val s = lex.curToken()
			lex.nextToken()
			val t = term()
			ctx = ctx.appendLastChild(t)
			val as = addsub(in - t.res)
			ctx = ctx.appendLastChild(as)
			ctx.res = as.res;
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN))) {
			ctx.res = in;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}

	def atom() : AtomContext = {
		var ctx = AtomContext("atom")
		if(lex.curTokenIn(Set(CalculatorToken.SUB))) {
			if(!lex.compareToken(CalculatorToken.SUB)) {
				throw new ParseException("Expected SUB, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val s = lex.curToken()
			lex.nextToken()
			if(!lex.compareToken(CalculatorToken.LPAREN)) {
				throw new ParseException("Expected LPAREN, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val l = lex.curToken()
			lex.nextToken()
			val e = expr()
			ctx = ctx.appendLastChild(e)
			if(!lex.compareToken(CalculatorToken.RPAREN)) {
				throw new ParseException("Expected RPAREN, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val r = lex.curToken()
			lex.nextToken()
			ctx.res = -e.res;
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.LPAREN))) {
			if(!lex.compareToken(CalculatorToken.LPAREN)) {
				throw new ParseException("Expected LPAREN, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val l = lex.curToken()
			lex.nextToken()
			val e = expr()
			ctx = ctx.appendLastChild(e)
			if(!lex.compareToken(CalculatorToken.RPAREN)) {
				throw new ParseException("Expected RPAREN, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val r = lex.curToken()
			lex.nextToken()
			ctx.res = e.res;
			ctx
		}
		else if(lex.curTokenIn(Set(CalculatorToken.NUMBER))) {
			if(!lex.compareToken(CalculatorToken.NUMBER)) {
				throw new ParseException("Expected NUMBER, found:" + lex.curToken().text, lex.curPos())
			}
			ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))
			val d = lex.curToken()
			lex.nextToken()
			ctx.res = (d.text).toDouble;
			ctx
		}
		else {
			throw new ParseException("Unexpected token: " + lex.curToken().text, lex.curPos())
		}
	}
}

object CalculatorParser {

	case class CalculatorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = CalculatorContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = CalculatorContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class TermContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = TermContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = TermContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class MuldivContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = MuldivContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = MuldivContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class ExprContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = ExprContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = ExprContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class HighPriorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = HighPriorContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = HighPriorContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class LogContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = LogContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = LogContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class AddsubContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = AddsubContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = AddsubContext(ctxRoot, ctxChildren ++ List(child))
	 }

	case class AtomContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {
		var res: Double = 0
		override def pushFirstChild(child: GrammarTree[_]) = AtomContext(ctxRoot, child::ctxChildren)
		override def appendLastChild(child: GrammarTree[_]) = AtomContext(ctxRoot, ctxChildren ++ List(child))
	 }
}

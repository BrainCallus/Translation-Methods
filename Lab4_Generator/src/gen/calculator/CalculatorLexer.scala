
package calculator;

import grammar.LexerRule;
import template.AbstractLexer;
import java.text.ParseException;
import java.io.InputStream;
import template.AbstractLexer._;
import template.Tokenized._;

case class CalculatorLexer(inputStream: InputStream)
  extends AbstractLexer[CalculatorToken](inputStream) {
	override val lexerRules = List(
	LexerRule[CalculatorToken](CalculatorToken.ADD, false),
	LexerRule[CalculatorToken](CalculatorToken.SUB, false),
	LexerRule[CalculatorToken](CalculatorToken.MUL, false),
	LexerRule[CalculatorToken](CalculatorToken.DIV, false),
	LexerRule[CalculatorToken](CalculatorToken.POW, false),
	LexerRule[CalculatorToken](CalculatorToken.LPAREN, false),
	LexerRule[CalculatorToken](CalculatorToken.RPAREN, false),
	LexerRule[CalculatorToken](CalculatorToken.NUMBER, false),
	LexerRule[CalculatorToken](CalculatorToken.SPACES, true)
  )
	override def getTokenWithName(name: String): CalculatorToken = { 
		CalculatorToken.withName(name)
	}
}


package forc;

import grammar.LexerRule;
import template.AbstractLexer;
import java.text.ParseException;
import java.io.InputStream;
import template.AbstractLexer._;
import template.Tokenized._;

case class ForcLexer(inputStream: InputStream)
  extends AbstractLexer[ForcToken](inputStream) {
	override val lexerRules = List(
	LexerRule[ForcToken](ForcToken.LPAREN, false),
	LexerRule[ForcToken](ForcToken.RPAREN, false),
	LexerRule[ForcToken](ForcToken.SEMICOLON, false),
	LexerRule[ForcToken](ForcToken.FOR, false),
	LexerRule[ForcToken](ForcToken.INC, false),
	LexerRule[ForcToken](ForcToken.DEC, false),
	LexerRule[ForcToken](ForcToken.EQ, false),
	LexerRule[ForcToken](ForcToken.LT, false),
	LexerRule[ForcToken](ForcToken.GT, false),
	LexerRule[ForcToken](ForcToken.NGT, false),
	LexerRule[ForcToken](ForcToken.NLT, false),
	LexerRule[ForcToken](ForcToken.NUMBER, false),
	LexerRule[ForcToken](ForcToken.TYPE_NAME, false),
	LexerRule[ForcToken](ForcToken.NAME, false),
	LexerRule[ForcToken](ForcToken.SPACES, true)
  )
	override def getTokenWithName(name: String): ForcToken = { 
		ForcToken.withName(name)
	}
}

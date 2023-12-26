import exception.{NotMatchesForPattern, ParseException}
import functional.{LexicalAnalyzer, Tree}
import grammar_entry.GrammarEntry.Terminal
import grammar_entry.{Grammar, Token}
import util.Constants
import util.Util.Ternary

import scala.annotation.tailrec

case class Parser() {
  def parse(input: String): Tree[_] = {
    validateMatchesFor(input)
    val grammar = Grammar(Constants.derivations, Token.values.map(t => (t.toString, Terminal(t))).toMap)
    val analyzer = LexicalAnalyzer(prepareExpression(Token.values)(input))

    try {
      (grammar.getDerivation("S") parseState (analyzer.moveNext(), grammar))._2
    } catch {
      case parseException: ParseException => throw new ParseException(parseException.getMessage + "\n" + input.trim)
    }
  }

  private def validateMatchesFor(input: String): Unit =
    if (!Constants.forPattern.matcher(input.trim).matches()) {
      throw NotMatchesForPattern
    }

  @tailrec
  private def prepareExpression(tokens: IndexedSeq[Token], idx: Int = 0)(input: String): String =
    if (idx >= tokens.length) {
      ("^ " + input + Token.END.getPatternAsString).trim().replaceAll("\\s+", " ")
    } else {
      prepareExpression(tokens, idx + 1)(
        (tokens(idx) == Token.EPS || tokens(idx) == Token.NUMBER || tokens(idx) == Token.TYPE_NAME) ?? (input,
        input.replaceAll(tokens(idx).getPatternAsString, " $0 "))
      )
    }
}

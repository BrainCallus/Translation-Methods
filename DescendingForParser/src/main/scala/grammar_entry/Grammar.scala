package grammar_entry

import exception.ParseException
import grammar_entry.GrammarEntry._

case class Grammar(nonTerms: Map[String, NonTerminal], terms: Map[String, Terminal]) {
  def getDerivation(key: String): GrammarEntry =
    nonTerms get key match {
      case Some(value) => value
      case None =>
        terms get key match {
          case Some(term) => term
          case None       => throw new ParseException(s"Grammar does not contain key $key")
        }
    }
}

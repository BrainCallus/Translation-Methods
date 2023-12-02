package functional

import grammar_entry.Token

import scala.annotation.tailrec

case class LexicalAnalyzer(
  stringTokens: List[String],
  private var curToken: Token = Token.EPS
) {
  def getCurToken: Token = curToken

  def getValueAndToken: (String, Token) = (stringTokens.head, curToken)

  def moveNext(): LexicalAnalyzer = {
    LexicalAnalyzer(stringTokens.tail, nextToken(Token.values, stringTokens.tail.head))
  }

  @tailrec
  private def nextToken(tokens: IndexedSeq[Token], curElement: String, idx: Int = 0): Token = {
    if(idx >= tokens.length) {
      Token.EPS
    }
    else if (tokens(idx).getPattern.matcher(curElement).matches()) {
      tokens(idx)
    } else {
      nextToken(tokens, curElement, idx + 1)
    }
  }

}

object LexicalAnalyzer {
  def apply(input: String) =
    new LexicalAnalyzer(input.split(" ").toList)
}

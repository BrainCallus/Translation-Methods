
package calculator;

import enumeratum.Enum
import grammar.Token

sealed abstract class CalculatorToken(protected val pattern: String) extends Token

object CalculatorToken extends Enum[CalculatorToken] {
  case object ADD extends CalculatorToken("\\+")
  case object SUB extends CalculatorToken("-")
  case object LPAREN extends CalculatorToken("\\(")
  case object RPAREN extends CalculatorToken("\\)")
  case object LOG extends CalculatorToken("//")
  case object MUL extends CalculatorToken("\\*")
  case object SPACES extends CalculatorToken("[ \n\t]+")
  case object DIV extends CalculatorToken("/")
  case object EOF extends CalculatorToken(" ")
  case object NUMBER extends CalculatorToken("(-)?(0|[1-9][0-9]*)(\\.[0-9]+)?")
  val values: IndexedSeq[CalculatorToken] = findValues
}

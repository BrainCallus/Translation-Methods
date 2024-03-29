
package forc;

import enumeratum.Enum
import grammar.Token

sealed abstract class ForcToken(protected val pattern: String) extends Token

object ForcToken extends Enum[ForcToken] {
  case object TYPE_NAME extends ForcToken("(int|long|char|byte)")
  case object NAME extends ForcToken("([a-zA-Z_]+[a-zA-Z_0-9]*)")
  case object NLT extends ForcToken(">=")
  case object SPACES extends ForcToken("[ \n\t]+")
  case object LPAREN extends ForcToken("\\(")
  case object GT extends ForcToken(">")
  case object LT extends ForcToken("<")
  case object EQ extends ForcToken("=")
  case object NGT extends ForcToken("<=")
  case object DEC extends ForcToken("--")
  case object RPAREN extends ForcToken("\\)")
  case object FOR extends ForcToken("for")
  case object EOF extends ForcToken(" ")
  case object NUMBER extends ForcToken("(-)?(\\d+)")
  case object INC extends ForcToken("\\+\\+")
  case object SEMICOLON extends ForcToken(";")
  val values: IndexedSeq[ForcToken] = findValues
}

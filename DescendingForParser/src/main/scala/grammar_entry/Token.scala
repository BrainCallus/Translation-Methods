package grammar_entry

import java.util.regex.Pattern
import util.Constants
import enumeratum._

sealed abstract class Token(private val pattern: String) extends EnumEntry {
  override def toString: String = {
    val name = this.getClass.getSimpleName
    name.substring(0, name.length-1)
  }
  def getPattern: Pattern = Pattern.compile(pattern)
  def getPatternAsString: String = pattern
}

object Token extends Enum [Token ]{
  case object LPAREN extends Token("\\(")

  case object RPAREN extends Token("\\)")
  case object END extends Token("\\$") {
    override def getPatternAsString: String = "$"
  }
  case object SEMICOLON extends Token(";")
  case object FOR extends Token("for")
  case object INC extends Token("\\+\\+")
  case object DEC extends Token("--")
  case object EQ extends Token("=")
  case object LT extends Token("<")
  case object GT extends Token(">")
  case object NUMBER extends Token("(-)?(\\d+)")
  case object TYPE_NAME extends Token(Constants.validTypeNames.mkString("(", "|", ")"))
  case object NAME extends Token("([a-zA-Z_]+[\\w[^\\-]]*)")
  case object EPS extends Token("")

  val values: IndexedSeq[Token] = findValues
}

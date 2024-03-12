package forc.dsl
import enumeratum._

sealed abstract class ComparatorSign(val value: String) extends EnumEntry

object ComparatorSign extends Enum[ComparatorSign] {
  case object LT extends ComparatorSign("<")
  case object GT extends ComparatorSign(">")
  case object NLT extends ComparatorSign(">=")
  case object NGT extends ComparatorSign("<=")

  override def values: IndexedSeq[ComparatorSign] = findValues
}

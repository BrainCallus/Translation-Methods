package forc.dsl

import enumeratum._

sealed abstract class VarType(val name: String) extends EnumEntry

object VarType extends Enum[VarType] {
  case object INT extends VarType("int")
  case object LONG extends VarType("long")
  case object CHAR extends VarType("char")
  case object BYTE extends VarType("byte")

  override def values: IndexedSeq[VarType] = findValues
}

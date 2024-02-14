package forc.dsl

import common.RandomUtil.{generateVarName, randInt, unbiasedCoin}
import util.Ternary.Ternary
import common.TestSample
import common.TestSample._

case class Forc(varType: VarType, varName: String, startIdx: BigInt, endIdx: BigInt, cmp: ComparatorSign, inc: Boolean)
  extends TestSample {
  override def res: String =
    s"for($varType $varName = $startIdx; $varName $cmp $endIdx; $varName ${inc ?? ("++", "--")})"

  override def expectedTokens: List[String] = List(
    "for",
    "(",
    varType.name,
    varName,
    "=",
    startIdx.toString,
    ";",
    varName,
    cmp.value,
    endIdx.toString,
    ";",
    varName,
    inc ?? ("++", "--"),
    ")",
    ""
  )
}

object Forc {
  def generateFor(): Forc = {
    Forc(getVarType, generateVarName(15), randInt(Int.MaxValue), randInt(Int.MaxValue), getCmp, unbiasedCoin)
  }

  private def getVarType: VarType = getEnumArbitrary(VarType.values)

  private def getCmp: ComparatorSign = getEnumArbitrary(ComparatorSign.values)
}

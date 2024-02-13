package util

import enumeratum._

import util.CommonUtils.{foldl, treeToStringList}
import util.GrammarTree.ContextTree
import util.RandomUtil.randPositiveInt

trait TestSample {
  def res: String
  def expectedTokens: List[String]

  def generateStringSample: String = {
    expectedTokens.foldLeft("")((curStr, token) => {
      curStr + " ".repeat(randPositiveInt(20)) ++ token
    })
  }

  def verifyResult(res: ContextTree): Boolean = {
    foldl(true)(
      (acc: Boolean, entry: (String, String)) =>
        acc && entry._1 == entry._2
    )(
      treeToStringList(res).zip(expectedTokens)
    )
  }
}

object TestSample {
   def getEnumArbitrary[E <: EnumEntry](values: IndexedSeq[E]): E = {
    values(randPositiveInt(values.length) - 1)
  }
}

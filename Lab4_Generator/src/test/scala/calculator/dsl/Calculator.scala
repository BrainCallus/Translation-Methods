package calculator.dsl

import calculator.dsl.ExpressionModules.Verifiers
import common.RandomUtil.randPositiveInt
import common.TestSample

case class Calculator(expression: Algebraic[Verifiers]) extends TestSample {
  override def res: String = expression.res._1.toString
  override def expectedTokens: List[String] = expression.res._2 ++ List("")

  override def generateStringSample: String = expression.res._2.foldLeft("") { (curStr, token) =>
    {
      curStr + " ".repeat(randPositiveInt(20)) ++ token
    }
  }
}

object Calculator {}

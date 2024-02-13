package calculator.dsl

import calculator.dsl.Expression._
import util.RandomUtil.{randDouble, randPositiveInt}
import util.Ternary.Ternary
import util.TestSample

case class Calculator(expression: Expression) extends TestSample {
  override def res: String = expression.res.toString

  override def expectedTokens: List[String] = expression.expectedTokens ++ List("")

  override def generateStringSample: String = expression.expectedTokens.foldLeft("") { (curStr, token) =>
    {
      curStr + " ".repeat(randPositiveInt(20)) ++ token
    }
  }
}

object Calculator {
  val operations: Seq[(Expression, Expression) => BinaryOperation] = List(Add, Sub, Mul, Div)

  private def arbitraryGetBinOperation: (Expression, Expression) => BinaryOperation = operations(
    randPositiveInt(operations.size) - 1
  )

  def generateSimpleBinOp(): BinaryOperation =
    DBZSafeBinOperation(arbitraryGetBinOperation, Number(randDouble), Number(randDouble))

  def generateArbitraryExpression(): Expression = {
    recursiveGenExpression(10)
  }

  def recursiveGenExpression(maxDepth: Int, curDepth: Int = 0): Expression = {
    if (curDepth >= maxDepth) {
      Number(randDouble)
    } else {
      randPositiveInt(3) match {
        case 1 => Number(randDouble)
        case 2 =>
          InBrackets(recursiveGenExpression(maxDepth, curDepth + 1))
        case _ =>
          DBZSafeBinOperation(
            arbitraryGetBinOperation,
            recursiveGenExpression(maxDepth, curDepth + 1),
            recursiveGenExpression(maxDepth, curDepth + 1)
          )
      }
    }
  }

  private def DBZSafeBinOperation(
    op: (Expression, Expression) => BinaryOperation,
    e1: Expression,
    e2: Expression
  ): BinaryOperation =
    op match {
      case Div => op(e1, (e2.res == 0) ?? (Number(1), e2))
      case _   => op(e1, e2)
    }
}

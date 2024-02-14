package calculator.dsl

import calculator.dsl.Algebraic._
import calculator.dsl.Expression.Verifiers
import util.RandomUtil.{randDouble, randPositiveInt}
import util.Ternary.Ternary
import util.TestSample

case class Calculator(expression: Algebraic[Verifiers]) extends TestSample {
  override def res: String = expression.res._1.toString
  override def expectedTokens: List[String] = expression.res._2 ++ List("")

  override def generateStringSample: String = expression.res._2.foldLeft("") { (curStr, token) =>
    {
      curStr + " ".repeat(randPositiveInt(20)) ++ token
    }
  }
}

object Calculator {
  def operations[F[_]: Expression]: List[(Algebraic[F], Algebraic[F]) => BinaryOperation[F]] =
    List(Expression[F].add, Expression[F].sub, Expression[F].mul, Expression[F].div)

  private def arbitraryGetBinOperation[F[_]: Expression]: (Algebraic[F], Algebraic[F]) => BinaryOperation[F] =
    operations[F].apply(randPositiveInt(operations.length) - 1)

  def generateSimpleBinOp(): BinaryOperation[Verifiers] =
    DBZSafeBinOperation(
      arbitraryGetBinOperation,
      Expression[Verifiers].number(randDouble),
      Expression[Verifiers].number(randDouble)
    )

  def generateArbitraryExpression(): Algebraic[Verifiers] = {
    recursiveGenExpression(10)
  }

  def recursiveGenExpression(maxDepth: Int, curDepth: Int = 0): Algebraic[Verifiers] = {
    if (curDepth >= maxDepth) {
      Expression[Verifiers].number(randDouble)
    } else {
      randPositiveInt(3) match {
        case 1 => Expression[Verifiers].number(randDouble)
        case 2 =>
          Expression[Verifiers].inBrackets(recursiveGenExpression(maxDepth, curDepth + 1))
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
    op: (Algebraic[Verifiers], Algebraic[Verifiers]) => BinaryOperation[Verifiers],
    e1: Algebraic[Verifiers],
    e2: Algebraic[Verifiers]
  ): BinaryOperation[Verifiers] = {
    if (e2.res._1 == 0) {
      val oper = op(e1, Expression[Verifiers].number(1))
      (oper.opStr == "/") ?? (oper, op(e1, e2))
    } else {
      op(e1, e2)
    }
  }
}

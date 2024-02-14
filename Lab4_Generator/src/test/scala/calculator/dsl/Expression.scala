package calculator.dsl

import calculator.dsl.Algebraic._
import common.RandomUtil.{randDouble, randPositiveInt}

trait Expression[F[_]] {
  import Algebraic._
  def number(double: Double): Number[F]
  def inBrackets(e: Algebraic[F]): InBrackets[F]
  def unoMinus(e: Algebraic[F]): UnoMinus[F]
  def add(e1: Algebraic[F], e2: Algebraic[F]): Add[F]
  def sub(e1: Algebraic[F], e2: Algebraic[F]): Sub[F]
  def mul(e1: Algebraic[F], e2: Algebraic[F]): Mul[F]
  def div(e1: Algebraic[F], e2: Algebraic[F]): Div[F]
  def exp(e1: Algebraic[F], e2: Algebraic[F]): Exp[F]
}

object Expression {
  def apply[F[_]](implicit exprBuild: Expression[F]): Expression[F] = exprBuild

  def operations[F[_]: Expression]: List[(Algebraic[F], Algebraic[F]) => BinaryOperation[F]] =
    List(Expression[F].add, Expression[F].sub, Expression[F].mul, Expression[F].div, Expression[F].exp)

  private def arbitraryGetBinOperation[F[_]: Expression]: (Algebraic[F], Algebraic[F]) => BinaryOperation[F] =
    operations[F].apply(randPositiveInt(operations.length) - 1)

  def generateSimpleBinOp[F[_]: Expression](): BinaryOperation[F] =
    arbitraryGetBinOperation.apply(Expression[F].number(randDouble), Expression[F].number(randDouble))

  def generateArbitraryExpression[F[_]: Expression](): Algebraic[F] = {
    recursiveGenExpression[F](10)
  }

  def recursiveGenExpression[F[_]: Expression](maxDepth: Int, curDepth: Int = 0): Algebraic[F] = {
    if (curDepth >= maxDepth) {
      Expression[F].number(randDouble)
    } else {
      randPositiveInt(4) match {
        case 1 => Expression[F].number(randDouble)
        case 2 =>
          Expression[F].inBrackets(recursiveGenExpression(maxDepth, curDepth + 1))
        case 3 => Expression[F].unoMinus(recursiveGenExpression(maxDepth, curDepth + 1))
        case _ =>
          arbitraryGetBinOperation[F].apply(
            recursiveGenExpression[F](maxDepth, curDepth + 1),
            recursiveGenExpression[F](maxDepth, curDepth + 1)
          )
      }
    }
  }
}

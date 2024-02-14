package calculator.dsl

sealed trait Algebraic[F[_]] {
  def res: F[Double]
}

object Algebraic {
  type Id[A] = A
  abstract class Number[F[_]]() extends Algebraic[F] {
    override def res: F[Double]
  }

  abstract class InBrackets[F[_]](expression: Algebraic[F]) extends Algebraic[F] {
    override def res: F[Double] = expression.res

  }

  abstract class BinaryOperation[F[_]](
    val expression1: Algebraic[F],
    val expression2: Algebraic[F],
    val opStr: String,
    val infixes: (Int, Int, Int)
  ) extends Algebraic[F] {
    def infixl: Int = infixes._1

    def infix: Int = infixes._2

    def infixr: Int = infixes._3

  }

  abstract class Add[F[_]](override val expression1: Algebraic[F], override val expression2: Algebraic[F])
    extends BinaryOperation[F](expression1, expression2, "+", (3, 3, 3))

  abstract class Sub[F[_]](override val expression1: Algebraic[F], override val expression2: Algebraic[F])
    extends BinaryOperation[F](expression1, expression2, "-", (3, 4, 5))

  abstract class Mul[F[_]](override val expression1: Algebraic[F], override val expression2: Algebraic[F])
    extends BinaryOperation[F](expression1, expression2, "*", (5, 6, 6))

  abstract class Div[F[_]](override val expression1: Algebraic[F], override val expression2: Algebraic[F])
    extends BinaryOperation[F](expression1, expression2, "/", (5, 5, 7))
}

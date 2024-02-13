package calculator.dsl

import util.Ternary.Ternary

sealed trait Expression {
  def res: Double
  def expectedTokens: List[String]
}

object Expression {
  case class Number(number: Double) extends Expression {
    override def res: Double = number

    override def expectedTokens: List[String] = List(number.toString)
  }

  case class InBrackets(expression: Expression) extends Expression {
    override def res: Double = expression.res

    override def expectedTokens: List[String] = List("(") ++ expression.expectedTokens ++ List(")")
  }

  abstract class BinaryOperation(
    val expression1: Expression,
    val expression2: Expression,
    op: (Double, Double) => Double,
    val opStr: String,
    val infixes: (Int, Int, Int)
  ) extends Expression {
    override def res: Double = op(expression1.res, expression2.res)

    override def expectedTokens: List[String] = {
      leftOperandTokens(expression1) ++ List(opStr) ++ rightOperandTokens(expression2)
    }

    protected def infixl: Int = infixes._1
    protected def infix: Int = infixes._2
    protected def infixr: Int = infixes._3

    private val leftOperandTokens: Expression=>List[String] = operandTokens(infixl)
    private val rightOperandTokens: Expression => List[String] = operandTokens(infixr)

    private def operandTokens(infixValue: Int)(operand:Expression): List[String] = {
      operand match {
        case b: BinaryOperation => (b.infix < infixValue) ?? ("(" :: b.expectedTokens ++ List(")"), b.expectedTokens)
        case _ => operand.expectedTokens
      }
    }


  }

  case class Add(override val expression1: Expression, override val expression2: Expression)
    extends BinaryOperation(expression1, expression2, _ + _, "+", (3, 3,3))
  case class Sub(override val expression1: Expression, override val expression2: Expression)
    extends BinaryOperation(expression1, expression2, _ - _, "-", (3, 4, 5))
  case class Mul(override val expression1: Expression, override val expression2: Expression)
    extends BinaryOperation(expression1, expression2, _ * _, "*", (5,6,6))
  case class Div(override val expression1: Expression, override val expression2: Expression)
    extends BinaryOperation(expression1, expression2, _ / _, "/", (5, 5, 7))

}

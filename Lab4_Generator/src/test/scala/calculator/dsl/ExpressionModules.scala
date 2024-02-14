package calculator.dsl

import calculator.dsl.Algebraic._
import util.Ternary.Ternary

object ExpressionModules {
  type Id[A] = A
  type Verifiers[_] = (Double, List[String])

  /** Simplest usage as a just calculator
    */
  implicit val calculate: Expression[Id] = new Expression[Id] {
    override def number(double: Double): Algebraic.Number[Id] = new Number[Id]() {
      override def res: Id[Double] = double
    }

    override def inBrackets(e: Algebraic[Id]): Algebraic.InBrackets[Id] = new InBrackets[Id](e) {}

    override def unoMinus(e: Algebraic[Id]): UnoMinus[Id] = new UnoMinus[Id] {
      override def res: Id[Double] = -e.res
    }

    override def add(e1: Algebraic[Id], e2: Algebraic[Id]): Algebraic.Add[Id] = new Add[Id](e1, e2) {
      override def res: Id[Double] = e1.res + e2.res
    }

    override def sub(e1: Algebraic[Id], e2: Algebraic[Id]): Sub[Id] = new Sub[Id](e1, e2) {
      override def res: Id[Double] = e1.res - e2.res
    }

    override def mul(e1: Algebraic[Id], e2: Algebraic[Id]): Mul[Id] = new Mul[Id](e1, e2) {
      override def res: Id[Double] = e1.res * e2.res
    }

    override def div(e1: Algebraic[Id], e2: Algebraic[Id]): Div[Id] = new Div[Id](e1, e2) {
      override def res: Id[Double] = e1.res / e2.res
    }

    override def exp(e1: Algebraic[Id], e2: Algebraic[Id]): Exp[Id] = new Exp[Id](e1, e2) {
      override def res: Id[Double] = math.pow(e1.res, e2.res)
    }
  }

  implicit val verifiers: Expression[Verifiers] = new Expression[Verifiers] {
    override def number(double: Double): Number[Verifiers] = new Number[Verifiers]() {
      override def res: Verifiers[Double] = (double, List(double.toString))
    }

    override def inBrackets(e: Algebraic[Verifiers]): InBrackets[Verifiers] = new InBrackets[Verifiers](e) {
      override def res: Verifiers[Double] = (e.res._1, List("(") ++ e.res._2 ++ List(")"))
    }

    override def unoMinus(e: Algebraic[Verifiers]): UnoMinus[Verifiers] = new UnoMinus[Verifiers]() {
      override def res: Verifiers[Double] = (-e.res._1, List("-", "(") ++ e.res._2 ++ List(")"))
    }

    override def add(e1: Algebraic[Verifiers], e2: Algebraic[Verifiers]): Add[Verifiers] = new Add[Verifiers](e1, e2) {
      override def res: Verifiers[Double] = binOpToList(this, _ + _)
    }

    override def sub(e1: Algebraic[Verifiers], e2: Algebraic[Verifiers]): Sub[Verifiers] = new Sub[Verifiers](e1, e2) {
      override def res: Verifiers[Double] = binOpToList(this, _ - _)
    }

    override def mul(e1: Algebraic[Verifiers], e2: Algebraic[Verifiers]): Mul[Verifiers] = new Mul[Verifiers](e1, e2) {
      override def res: Verifiers[Double] = binOpToList(this, _ * _)
    }

    override def div(e1: Algebraic[Verifiers], e2: Algebraic[Verifiers]): Div[Verifiers] = new Div[Verifiers](e1, e2) {
      override def res: Verifiers[Double] = binOpToList(this, _ / _)
    }

    override def exp(e1: Algebraic[Verifiers], e2: Algebraic[Verifiers]): Exp[Verifiers] = new Exp[Verifiers](e1, e2) {
      override def res: Verifiers[Double] = binOpToList(this, math.pow)
    }

    private def binOpToList(b: BinaryOperation[Verifiers], op: (Double, Double) => Double): (Double, List[String]) = {
      (
        op(b.expression1.res._1, b.expression2.res._1),
        leftOperandTokens(b)(b.expression1) ++ List(b.opStr) ++ rightOperandTokens(b)(b.expression2)
      )
    }

    private def leftOperandTokens(e: BinaryOperation[Verifiers]): Algebraic[Verifiers] => List[String] = operandTokens(
      e.infixl
    )

    private def rightOperandTokens(e: BinaryOperation[Verifiers]): Algebraic[Verifiers] => List[String] = operandTokens(
      e.infixr
    )

    private def operandTokens(infixValue: Int)(operand: Algebraic[Verifiers]): List[String] = {
      operand match {
        case b: BinaryOperation[Verifiers] => (b.infix < infixValue) ?? ("(" :: b.res._2 ++ List(")"), b.res._2)
        case _                             => operand.res._2
      }
    }
  }

}

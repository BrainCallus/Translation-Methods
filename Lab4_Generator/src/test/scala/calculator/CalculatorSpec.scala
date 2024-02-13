package calculator

import calculator.dsl.Calculator._
import calculator.dsl.Expression.{BinaryOperation, InBrackets, Number}
import calculator.dsl.{Calculator, Expression}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import util.CommonUtils.treeToStringList
import util.RandomUtil.{generateVarName, randDouble, unbiasedCoin}
import util.Ternary.Ternary
import util.WriteUtil.{clearDirectoryFiles, makeGraph}

import java.io.ByteArrayInputStream
import java.nio.file.Path
import java.text.ParseException

class CalculatorSpec extends AnyFlatSpec with Matchers {
  val DEFAULT_TEST_SIZE = 100
  private val outputDir = "graphviz\\calculator"
  "calculator" should "parse and calculate numbers correct" in {
    correctTestSeries(Number(randDouble), "Number")

  }

  it should "parse and calculate numbers surrounded brackets" in {
    correctTestSeries(InBrackets(Number(randDouble)), "NumberBrackets")
  }

  it should "parse and calculate simple binary operations" in {
    correctTestSeries(generateSimpleBinOp(), "Op")
  }

  it should "parse and calculate simple binary operations surrounded brackets" in {
    correctTestSeries(InBrackets(generateSimpleBinOp()), "OpBrackets")

  }

  it should "parse and calculate random correct expression" in {
    clearOutput(Path.of(outputDir))
    correctTestSeries(generateArbitraryExpression(), "RandExpr")
  }

  it should "throw ParseException if expression has variables" in {
    invalidSeries(generateSimpleBinOp(), c => {
      c.generateStringSample.replaceAll((unbiasedCoin??(c.expression.asInstanceOf[BinaryOperation].expression1, c.expression.asInstanceOf[BinaryOperation].expression2)).res.toString, generateVarName(10))
    })
  }

  it should "throw ParseException if expression" in {
    invalidSeries(Number(randDouble), _ => "()")
  }

  it should "throw ParseException if at least one operand missed" in {
    List("+", "-", "*", "/").foreach(op => {
      invalidSeries(generateArbitraryExpression(), c => c.generateStringSample+op)
      invalidSeries(generateArbitraryExpression(), c => op + c.generateStringSample)
    })
  }

  it should "throw ParseException if at least one operand is empty parentheses" in {
    List("+", "-", "*", "/").foreach(op => {
      invalidSeries(generateArbitraryExpression(), c => c.generateStringSample + op + "( )")
      invalidSeries(generateArbitraryExpression(), c => "( )" + op + c.generateStringSample)
    })
  }

  it should "throw ParseException if bracket sequence is incorrect" in {
    invalidSeries(InBrackets(generateArbitraryExpression()), c => c.generateStringSample.replaceAll("\\(", ""))
    invalidSeries(InBrackets(generateArbitraryExpression()), c => c.generateStringSample.replaceAll("\\)", ""))
    invalidSeries(InBrackets(generateArbitraryExpression()), c => c.generateStringSample.replaceAll("\\(", ")"))
    invalidSeries(InBrackets(generateArbitraryExpression()), c => c.generateStringSample.replaceAll("\\)", "("))
    invalidSeries(generateArbitraryExpression(), c => c.generateStringSample + ")")
    invalidSeries(generateArbitraryExpression(), c => c.generateStringSample + "(")
    invalidSeries(generateArbitraryExpression(), c => "("+ c.generateStringSample)
    invalidSeries(generateArbitraryExpression(), c => ")"+ c.generateStringSample)
  }

  private def invalidSeries(gen: => Expression, invalidate: Calculator => String): Unit = {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      parseExceptionTest(gen, invalidate)
    }
  }

  private def parseExceptionTest(gen: => Expression, invalidate: Calculator => String) = {
    val calc = Calculator(gen)
    assertThrows[ParseException]({
      val parser = CalculatorParser(new ByteArrayInputStream(invalidate(calc).getBytes))
      parser.calculator()
    })
  }

  private def correctTestSeries(gen: => Expression, testName: String): Unit = {
    for (i <- 0 until DEFAULT_TEST_SIZE) {
      calculatorCorrectInputTest(gen, s"$testName$i")
    }
  }

  private def calculatorCorrectInputTest(expression: Expression, id: String) = {
    val calc = Calculator(expression)
    val parser = CalculatorParser(new ByteArrayInputStream(calc.generateStringSample.getBytes))
    val res = parser.calculator()
    val diff = math.abs(res.res - calc.res.toDouble)
    if (diff > 1e-5) {
      println(s"real expression: ${calc.expression}")
      println(s"sample: ${calc.generateStringSample.replaceAll("\\s+", " ")}")
      println(s"parsed: ${treeToStringList(res).mkString(" ")}")
      println(s"diff: $diff")
      makeGraph(res, s"$outputDir\\graphCalc$id.dot")
    }
    assert(diff / math.abs(calc.res.toDouble + 1e-9) <= 1e-5)
    assertResult(true)(calc.verifyResult(res))
  }

  private def clearOutput(path: Path): Unit = {
    println(
      clearDirectoryFiles(path).fold(
        e => s"Unexpected exception while delete files from $path: ${e.getMessage}",
        _ => "Directory successfully cleared"
      )
    )
  }
}

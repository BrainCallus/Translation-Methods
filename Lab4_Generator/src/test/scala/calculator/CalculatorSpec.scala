package calculator

import calculator.CalculatorParser.CalculatorContext
import calculator.dsl.Algebraic.BinaryOperation
import calculator.dsl.Expression.{generateArbitraryExpression, generateSimpleBinOp}
import calculator.dsl.ExpressionModules._
import calculator.dsl.{Algebraic, Calculator, Expression}
import cats.Monad
import common.ParserSpec
import util.CommonUtils.treeToStringList
import common.RandomUtil.{generateVarName, randDouble, unbiasedCoin}
import org.scalatest.{Assertion, BeforeAndAfterAll}
import util.Ternary.Ternary
import util.WriteUtil.{clearDirectoryFiles, makeGraph}

import java.io.ByteArrayInputStream
import java.nio.file.Path
import java.text.ParseException

class CalculatorSpec extends ParserSpec[Calculator, CalculatorContext] with BeforeAndAfterAll {
  private val outputDir = "graphviz\\calculator"
  val ExprVerify: Expression[Verifiers] = Expression[Verifiers]
  val DEFAULT_TEST_SIZE = 100
  override def callParse[F[_]: Monad]: ByteArrayInputStream => F[Either[ParseException, CalculatorContext]] =
    (bytes: ByteArrayInputStream) => {
      val parser = CalculatorParser[F](bytes)
      parser.calculator().runA(parser.lex).value
    }

  override def verifyParseResult(res: CalculatorContext, sample: Calculator, id: String): Assertion = {
    val expectedRes = sample.res.toDouble
    expectedRes match {
      case x if x == Double.NegativeInfinity || x == Double.PositiveInfinity => assert(expectedRes == res.res)
      case y if y.isNaN                                                      => assert(res.res.isNaN)
      case _ =>
        val diff = math.abs(res.res - expectedRes)

        if (diff > 1e-5) {
          println(s"real expression: ${sample.expression}")
          println(s"sample: ${sample.generateStringSample.replaceAll("\\s+", " ")}")
          println(s"parsed: ${treeToStringList(res).mkString(" ")}")
          println(s"diff: $diff")
          makeGraph(res, s"$outputDir\\graphCalc$id.dot")
        }
        assert(diff / math.abs(expectedRes + 1e-9) <= 1e-5)
    }
  }

  override def beforeAll(): Unit = {
    clearOutput(Path.of(outputDir))
  }

  "calculator" should "parse and calculate numbers correct" in {
    validSeries(buildCalc(ExprVerify.number(randDouble)), "Number")

  }

  it should "parse and calculate numbers surrounded brackets" in {
    validSeries(buildCalc(ExprVerify.inBrackets(ExprVerify.number(randDouble))), "NumberBrackets")
  }

  it should "parse and calculate unary minus operation" in {
    validSeries(buildCalc(ExprVerify.unoMinus(generateArbitraryExpression[Verifiers]())), "UnaryMinus")
  }

  it should "parse and calculate simple binary operations" in {
    validSeries(buildCalc(generateSimpleBinOp[Verifiers]()), "Op")
  }

  it should "parse and calculate simple binary operations surrounded brackets" in {
    validSeries(buildCalc(ExprVerify.inBrackets(generateSimpleBinOp[Verifiers]())), "OpBrackets")

  }

  it should "parse and calculate random correct expression" in {
    validSeries(buildCalc(generateArbitraryExpression[Verifiers]()), "RandExpression")
  }

  it should "throw ParseException if expression has variables" in {
    invalidSeries(
      buildCalc(generateSimpleBinOp[Verifiers]()),
      c => {
        val binOp = c.expression.asInstanceOf[BinaryOperation[Verifiers]]
        c.generateStringSample
          .replaceAll((unbiasedCoin ?? (binOp.expression1, binOp.expression2)).res._1.toString, generateVarName(10))
      }
    )
  }

  it should "throw ParseException if expression" in {
    invalidSeries(buildCalc(ExprVerify.number(randDouble)), _ => "()")
  }

  it should "throw ParseException if at least one operand missed" in {
    List("+", "-", "*").foreach(op => {
      invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => c.generateStringSample + op)
      if (op != "-") {
        invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => op + c.generateStringSample)
      }
    })
  }

  it should "throw ParseException if at least one operand is empty parentheses" in {
    List("+", "-", "*", "/").foreach(op => {
      invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => c.generateStringSample + op + "( )")
      invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => "( )" + op + c.generateStringSample)
    })
  }

  it should "throw ParseException if bracket sequence is incorrect" in {
    invalidSeries(
      buildCalc(ExprVerify.inBrackets(generateArbitraryExpression[Verifiers]())),
      c => c.generateStringSample.replaceAll("\\(", "")
    )
    invalidSeries(
      buildCalc(ExprVerify.inBrackets(generateArbitraryExpression[Verifiers]())),
      c => c.generateStringSample.replaceAll("\\)", "")
    )
    invalidSeries(
      buildCalc(ExprVerify.inBrackets(generateArbitraryExpression[Verifiers]())),
      c => c.generateStringSample.replaceAll("\\(", ")")
    )
    invalidSeries(
      buildCalc(ExprVerify.inBrackets(generateArbitraryExpression[Verifiers]())),
      c => c.generateStringSample.replaceAll("\\)", "(")
    )
    invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => c.generateStringSample + ")")
    invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => c.generateStringSample + "(")
    invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => "(" + c.generateStringSample)
    invalidSeries(buildCalc(generateArbitraryExpression[Verifiers]()), c => ")" + c.generateStringSample)
  }

  private def buildCalc(alg: Algebraic[Verifiers]): Calculator = Calculator(alg)

  private def clearOutput(path: Path): Unit = {
    println(
      clearDirectoryFiles(path).fold(
        e => s"Unexpected exception while delete files from $path: ${e.getMessage}",
        _ => "Directory successfully cleared"
      )
    )
  }
}

import TestUtil._
import exception.{NotMatchesForPattern, ParseException}
import grammar_entry.Token
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import util.Constants.validTypeNames
import util.Util.Ternary

import scala.util.{Failure, Random, Try}

class ParserSpec extends AnyFlatSpec with Matchers {

  "parse" should "parse correct for expression" in {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      val sample = generateTestSample(false)
      val parsed = Parser().parse(sample.generateTestSample())
      assert(sample.verifyAnswer(parsed))
    }
  }

  "parse ++i| --i" should "parse correct expression with prefix increment|decrement" in {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      val sample = generateTestSample(true)
      val parsed = Parser().parse(sample.generateTestSample())
      assert(sample.verifyAnswer(parsed))
    }
  }

  def testThrowsNotMatchesFor(func: TestSample => String): Unit = {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      val s = func(generateTestSample(false))
      assert(Try(Parser() parse s) match {
        case Failure(NotMatchesForPattern) => true
        case _                             => false
      })
    }
  }

  def testTrowsNotMatchesFor(replacement: String => String): Unit = {
    testThrowsNotMatchesFor(sample => replacement(sample.generateTestSample()))
  }

  "parse" should "throw NotMatchesForPattern reports that given string doesn't match for-pattern if 'for' has uppercase letters" in {
    val upperFors = List("For", "fOr", "foR", "FOr", " fOR", "FOR")
    upperFors.foreach(forr => {
      testTrowsNotMatchesFor(s => s.replaceAll(Token.FOR.getPatternAsString, forr))
    })
  }

  it should "throw NotMatchesForPattern if something else instead 'for'" in {
    testTrowsNotMatchesFor(s =>
      s.replaceAll(
        Token.FOR.getPatternAsString,
        (
          () =>
            randomLatinString() match {
              case "for" => ""
              case x     => x
            }
        ).apply()
      )
    )
  }

  it should "throw NotMatchesForPattern if '(' mismatch or occurred more than 1 time" in {
    testTrowsNotMatchesFor(s =>
      s.replaceAll(
        Token.LPAREN.getPatternAsString,
        (() => Random.nextBoolean() ?? ("", "(".repeat(Random.nextInt(8) + 2))).apply()
      )
    )
  }

  it should "throw NotMatchesForPattern if ')' mismatch or occurred more than 1 time" in {
    testTrowsNotMatchesFor(s =>
      s.replaceAll(
        Token.RPAREN.getPatternAsString,
        (() => Random.nextBoolean() ?? ("", ")".repeat(Random.nextInt(8) + 2))).apply()
      )
    )
  }

  it should "throw NotMatchesForPattern if '(' and ')' surround inner part twice or more" in {
    testTrowsNotMatchesFor(s => {
      val bracketCount = Random.nextInt(10) + 1
      "for" ++ "(".repeat(bracketCount) ++ s.split("for").apply(1) ++ ")".repeat(bracketCount)
    })
  }

  it should "throw NotMatchesForPattern if '(' and ')' were swapped" in {
    testTrowsNotMatchesFor(s => {
      val parts = s.split("[()]")
      parts.apply(0) ++ ")" ++ parts.apply(1) ++ "("
    })
  }

  it should "throw NotMatchesForPattern some of ';' missed" in {
    testTrowsNotMatchesFor(s => {
      Random.nextInt(3) match {
        case 0 => s.replaceFirst(";", "")
        case 1 =>
          val idx = s.lastIndexOf(';')
          s.substring(0, idx) ++ s.substring(idx + 1)
        case 2 => s.replaceAll(";", "")
      }
    })
  }

  it should "throw NotMatchesForPattern compare operator missed" in {
    testTrowsNotMatchesFor(s => s.replaceAll("[<>]", ""))
  }

  it should "throw NotMatchesForPattern increment and decrement missed" in {
    testTrowsNotMatchesFor(s =>
      s.replaceAll(Token.INC.getPatternAsString, "").replaceAll(Token.DEC.getPatternAsString, "")
    )
  }

  it should "throw NotMatchesForPattern if increment and decrement is invalid" in {
    val invalid = List("+ +", "- -", "+-", "-+", "+=", "-=", "+ -", "- +")
    invalid.foreach(op => {
      testTrowsNotMatchesFor(s =>
        s.replaceAll(Token.INC.getPatternAsString, op).replaceAll(Token.DEC.getPatternAsString, op)
      )
    })
  }

  it should "not parse increasing/decreasing with +=n -=n" in {
    testTrowsNotMatchesFor(s =>
      s.replaceAll(Token.INC.getPatternAsString, "+=" + randomNumber().toString)
        .replaceAll("--", "-=" + randomNumber().toString)
    )
  }

  it should "throw NotMatchesForPattern if type name invalid or missed" in {
    testTrowsNotMatchesFor(s =>
      s.replaceAll(
        Token.TYPE_NAME.getPatternAsString,
        (() => {
          val gen = randomLatinString()
          validTypeNames.contains(gen) ?? ("", gen)
        }).apply()
      )
    )
  }

  it should "throw NotMatchesForPattern if no whitespace between name and typename" in {
    testTrowsNotMatchesFor(s => {
      s.replaceAll(s"(${Token.TYPE_NAME.getPatternAsString})\\s+", "$1")
    })
  }

  it should "throw NotMatchesForPattern if variable name different" in {
    testThrowsNotMatchesFor(sample => {
      val name = sample.expectedLeafValues.apply(3)
      val parts = sample.generateTestSample().split(name)
      val gen = randomLatinString()
      val alter = (gen == name) ?? ("", gen)
      parts.apply(0) + name + (Random.nextInt(3) match {
        case 0 => parts.apply(1) + alter + parts(2) + name
        case 1 => parts(1) + name + parts(2) + alter
        case 2 => parts(1) + alter + parts(2) + randomLatinString()
      }) + parts(3)
    })
  }

  it should "throw NotMatchesForPattern if variable name starts with digit" in {
    testThrowsNotMatchesFor(sample => {
      sample.generateTestSample().replaceAll(sample.getVarName, Random.nextInt(10).toString + sample.getVarName)
    })
  }

  it should "throw NotMatchesForPattern if variable name contain invalid characters" in {
    val chars = "+-=()*&^%#@!~`\"'./,;:?><| "
    chars.foreach(ch => {
      testThrowsNotMatchesFor(sample => {
        val gen = sample.generateTestSample()
        gen.replaceAll(
          sample.getVarName,
          (sample.getVarName.length > 1) ?? ({
            val pos = Random.nextInt(math.max(1, sample.getVarName.length - 1)) + 1
            sample.getVarName.substring(0, pos) + ch.toString + sample.getVarName.substring(pos)
          }, sample.getVarName + ch.toString + sample.getVarName)
        )
      })
    })
  }

  it should "throw ParseException if name is one of the types" in {
    validTypeNames.foreach(typeName => {
      for (_ <- 0 until DEFAULT_TEST_SIZE) {
        val sample = generateTestSample(false)
        assertThrows[ParseException](
          Parser().parse(sample.generateTestSample().replaceAll(sample.expectedLeafValues(3), typeName))
        )
      }
    })
  }

  it should "throw NotMatchesForPattern if initial or last variable value is not a number" in {
    testThrowsNotMatchesFor(sample => {
      sample
        .generateTestSample()
        .replaceAll(
          Random.nextBoolean() ?? (sample.getStartValue, sample.getEndValue),
          randomLatinString(10) match {
            case x if Token.NUMBER.getPattern.matcher(x).matches() => ""
            case y                                                 => y
          }
        )
    })
  }

}

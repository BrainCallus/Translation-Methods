import TestUtil._
import exception.{NotMatchesForPattern, ParseException}
import grammar_entry.Token
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import util.Util.Ternary

import scala.util.{Failure, Random, Success, Try}

class ParserSpec extends AnyFlatSpec with Matchers {

  "parse" should "parse correct for expression" in {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      val sample = generateTestSample
      val parsed = Parser().parse(sample.generateTestSample())
      assert(sample.verifyAnswer(parsed))
    }
  }

  def testTrowsNotMatchesFor(replacement: String => String): Unit =
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      assert(Try(Parser() parse replacement(generateTestSample.generateTestSample())) match {
        case Failure(NotMatchesForPattern) => true
        case _                             => false
      })
    }

  it should "throw NotMatchesForPattern reports that given string doesn't match for-pattern if 'for' has uppercase letters" in {
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

  it should "throw NotMatchesForPattern increment and decrement is invalid" in {
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

}

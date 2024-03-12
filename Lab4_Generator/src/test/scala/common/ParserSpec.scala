package common

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.{Id, Monad}
import org.scalatest
import org.scalatest.Assertion
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import util.GrammarTree.ContextTree

import java.io.ByteArrayInputStream
import java.text.ParseException

trait ParserSpec[S <: TestSample, C <: ContextTree] extends AnyFlatSpec with Matchers {
  val DEFAULT_TEST_SIZE: Int

  def verifyParseResult(res: C, sample: S, id: String = "0"): scalatest.Assertion

  def callParse[F[_]: Monad]: ByteArrayInputStream => F[Either[ParseException, C]]

  def invalidSeries(sample: => S, invalidate: S => String): Unit =
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      parseExceptionTest(sample, invalidate)
    }

  def validSeries(sampleGen: => S, testName: String = ""): Unit =
    for (i <- 0 until DEFAULT_TEST_SIZE) {
      correctInputTest(sampleGen, s"$testName$i")
    }

  def parseExceptionTest(sample: S, invalidate: S => String): Assertion =
    callParse[Id]
      .apply(new ByteArrayInputStream(invalidate(sample).getBytes))
      .fold(
        _ => succeed,
        _ => fail("Parse exception was not thrown")
      )

  def correctInputTest(sample: S, testId: String = "0"): Assertion =
    callParse[IO]
      .apply(new ByteArrayInputStream(sample.generateStringSample.getBytes))
      .map {
        _.fold(
          e => fail(s"For test $testId no ParseException expected. Got: $e"),
          res => {
            verifyParseResult(res, sample, testId)
            assertResult(true)(sample.verifyResult(res))
          }
        )
      }
      .unsafeRunSync()

}

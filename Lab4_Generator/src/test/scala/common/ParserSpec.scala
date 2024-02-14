package common

import org.scalatest
import org.scalatest.Assertion
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import util.GrammarTree
import util.GrammarTree.ContextTree

import java.io.ByteArrayInputStream
import java.text.ParseException

trait ParserSpec[S <: TestSample, C <: ContextTree]  extends AnyFlatSpec with Matchers {
  val DEFAULT_TEST_SIZE: Int

  def verifyParseResult(res: C, sample: S, id: String="0"): scalatest.Assertion

  def callParse: ByteArrayInputStream => C

   def invalidSeries(sample: =>S, invalidate: S => String): Unit = {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      parseExceptionTest(sample, invalidate)
    }
  }

  def validSeries(sampleGen: => S, testName: String = ""): Unit = {
    for(i <- 0 until DEFAULT_TEST_SIZE) {
      correctInputTest(sampleGen, s"$testName$i")
    }
  }

  def parseExceptionTest(sample: S, invalidate: S => String): Assertion = {
    val input = invalidate(sample)
    assertThrows[ParseException](callParse(new ByteArrayInputStream(input.getBytes)))
  }

  def correctInputTest(sample: S, testId: String = "0"): Assertion = {
    val res = callParse(new ByteArrayInputStream(sample.generateStringSample.getBytes))
    verifyParseResult(res, sample, testId)
    assertResult(true)(sample.verifyResult(res))
  }
}

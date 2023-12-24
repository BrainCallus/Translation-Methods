import functional.Tree
import util.Constants.{epsilon, validTypeNames}
import util.Util._

import scala.util.Random

object TestUtil {

  case class TestSample(expectedLeafValues: List[String]) {

    def getVarName: String = expectedLeafValues(3)
    def getStartValue: String = expectedLeafValues(5)

    def getEndValue: String = expectedLeafValues(10)
    def generateTestSample(): String =
      foldl("")((s: String, value: String) => {
        s ++ s.isEmpty ?? (" ".repeat(Random.nextInt(5)), s.charAt(s.length - 1) match {
          case '>' | '<' => ""
          case _         => " ".repeat(Random.nextInt(5))
        }) ++ value
      })(expectedLeafValues)
        .replaceAll(epsilon.toString, "")
    def verifyAnswer(parsed: Tree[_]): Boolean =
      foldl(true)((acc: Boolean, entry: (String, String)) => acc && entry._1 == entry._2.trim)(
        treeToStringList(parsed).zip(expectedLeafValues)
      )
  }

  object TestSample {
    def apply(
      varType: String,
      name: String,
      startValue: Long,
      endValue: Long,
      lt: Boolean,
      strict: Boolean,
      inc: Boolean
    ): TestSample =
      TestSample(
        List(
          "for",
          "(",
          varType ++ " ",
          name,
          "=",
          startValue.toString,
          ";",
          name,
          lt ?? ("<", ">"),
          strict ?? ("=", epsilon.toString),
          endValue.toString,
          ";",
          name,
          inc ?? ("++", "--"),
          ")"
        )
      )
  }

  val DEFAULT_TEST_SIZE = 10000
  def latinLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_"
  def validChars: String = latinLetters ++ Range.Int(0, 10, 1).mkString("")

  def randomSign: Int = Random.nextBoolean() ?? (-1, 1)

  def randomNumber(constraint: Long = Int.MaxValue): Long = Random.nextLong(constraint) * randomSign

  def randomLatinString(constraint: Int = 100): String = (0 until Random.nextInt(constraint))
    .map(_ => validChars.charAt(Random.nextInt(TestUtil.validChars.length)))
    .mkString

  def getRandomType: String = validTypeNames(Random.nextInt(validTypeNames.length))

  def randomName: String = {
    val first = latinLetters.charAt(Random.nextInt(latinLetters.length))
    first.toString ++ randomLatinString(20)
  }
  def generateTestSample: TestSample = TestSample(
    getRandomType,
    randomName,
    randomNumber(),
    randomNumber(),
    Random.nextBoolean(),
    Random.nextBoolean(),
    Random.nextBoolean()
  )
}

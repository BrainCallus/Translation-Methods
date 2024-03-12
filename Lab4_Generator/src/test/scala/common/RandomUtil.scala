package common

import util.Ternary._

import scala.util.Random

object RandomUtil {
  def randPositiveInt(constraint: Int): Int = Random.nextInt(constraint) + 1

  def randInt(constraint: Int): Int = randPositiveInt(constraint) * randomSign

  def randDouble: Double = limitedDouble * randomSign * randPositiveInt(Int.MaxValue / 1000)

  def unbiasedCoin: Boolean = Random.nextBoolean()

  def generateVarName(constraint: Int = 20): String = {
    val first = latinLetters.charAt(Random.nextInt(latinLetters.length))
    first.toString ++ randomLatinString(constraint - 1)
  }

  def randomLatinString(constraint: Int): String =
    (0 until Random.nextInt(constraint)).map(_ => validChars.charAt(Random.nextInt(validChars.length))).mkString

  private val latinLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_"

  private val validChars: String = latinLetters ++ Range.Int(0, 10, 1).mkString("")

  private def limitedDouble: Double = {
    BigDecimal(Random.nextDouble()).setScale(5, BigDecimal.RoundingMode.HALF_UP).doubleValue
  }
  private def randomSign: Int = Ternary(Random.nextBoolean()) ?? (-1, 1)
}

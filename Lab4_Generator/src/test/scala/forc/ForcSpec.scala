package forc

import forc.dsl.{Forc, VarType}
import forc.dsl.Forc.generateFor
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import util.CommonUtils.treeToStringList
import util.RandomUtil.{randPositiveInt, randomLatinString, unbiasedCoin}
import util.Ternary.Ternary

import java.io.ByteArrayInputStream
import java.text.ParseException

class ForcSpec extends AnyFlatSpec with Matchers {
  val DEFAULT_TEST_SIZE = 100
  "forc" should "parse and build tree for correct for-statement" in {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      forCorrectInputTest()
    }
  }

  it should "throw Parse Exception if 'for' has uppercase letters" in {
    val upperFors = List("For", "fOr", "foR", "FOr", "fOR", "FOR")
    upperFors.foreach { uFor =>
      parseExceptionTest(f => f.generateStringSample.replaceAll("for", uFor))
    }
  }

  it should "throw Parse Exception if something else instead 'for'" in {
    invalidSeries(f =>
      f.generateStringSample.replaceAll(
        "for",
        randomLatinString(20) match {
          case "for" => ""
          case s => s
        }
      ))
  }

  it should "throw Parse Exception if '(' mismatch or occurred more than 1 time" in {
    invalidSeries(f =>
      f.generateStringSample.replaceAll("\\(", (() => unbiasedCoin ?? ("", "(".repeat(randPositiveInt(8) + 1)))())
    )
  }

  it should "throw Parse Exception if ')' mismatch or occurred more than 1 time" in {
    invalidSeries(f => {
      f.generateStringSample.replaceAll("\\)", (() => unbiasedCoin ?? ("", ")".repeat(randPositiveInt(8) + 1)))())
    })
  }

  it should "throw Parse Exception if '(' and ')' surround inner part twice or more" in {
    invalidSeries(f => {
      val bracketCount = randPositiveInt(10)
      s"for${"(".repeat(bracketCount)}${f.generateStringSample.split("for")(1)}${")".repeat(bracketCount)}"
    })
  }

  it should "throw Parse Exception if '(' and ')' were swapped" in {
    invalidSeries(f => {
      val parts = f.generateStringSample.split("[()]")
      parts(0) ++ ")" ++ parts(1) ++ "("
    })
  }

  it should "throw Parse Exception if some of ';' missed" in {
    invalidSeries(f => {
      randPositiveInt(3) match {
        case 1 => f.generateStringSample.replaceFirst(";", "")
        case 2 =>
          val sample = f.generateStringSample
          val idx = sample.lastIndexOf(';')
          sample.substring(0, idx) ++ sample.substring(idx + 1)
        case _ => f.generateStringSample.replaceAll(";", "")
      }
    })
  }

  it should "throw Parse Exception if compare operator missed" in {
    invalidSeries(f => f.generateStringSample.replaceAll("[<>](=)?", ""))
  }

  it should "throw Parse Exception if increment and decrement missed" in {
    invalidSeries(f => f.generateStringSample.replaceAll("(\\+\\+|--)", ""))
  }

  it should "throw Parse Exception if increment and decrement is invalid" in {
    val invalid = List("+ +", "- -", "+-", "-+", "+=", "-=", "+ -", "- +")
    invalid.foreach(i => {
      parseExceptionTest(f => f.generateStringSample.replaceAll("(\\+\\+|--)", i))
    })

  }

  it should "throw Parse Exception if type name invalid or missed" in {
    val validTypes = VarType.values.map(_.name)
    invalidSeries(f => {
      val invType = randomLatinString(20)
      f.generateStringSample.replaceAll(f.varType.name, validTypes.contains(invType) ?? ("", invType))
    })
  }

  it should "throw Parse Exception if variable name different" in {
    invalidSeries(f => {
      val name = f.varName
      val parts = f.generateStringSample.split(name)
      val gen = randomLatinString(20)
      val alter = (gen == name) ?? ("", gen)
      parts.apply(0) + name + (randPositiveInt(3) match {
        case 1 => parts.apply(1) + alter + parts(2) + name
        case 2 => parts(1) + name + parts(2) + alter
        case _ => parts(1) + alter + parts(2) + randomLatinString(20)
      }) + parts(3)
    })
  }

  it should "throw Parse Exception if variable name starts with digit" in {
    invalidSeries(f => {
      f.generateStringSample.replaceAll(f.varName, (randPositiveInt(10) - 1).toString + f.varName)
    })
  }

  it should "throw Parse Exception if variable name contain invalid characters" in {
    val chars = "+-=()*&^%#@!~`\"'./,;:?><| "
    chars.foreach(ch => {
      invalidSeries(f => {
        val gen = f.generateStringSample
        gen.replaceAll(
          f.varName,
          (f.varName.length > 1) ?? ({
            val pos = randPositiveInt(math.max(1, f.varName.length - 1))
            f.varName.substring(0, pos) + ch.toString + f.varName.substring(pos)
          }, f.varName + ch.toString + f.varName)
        )
      })
    })
  }

  it should "throw Parse Exception if name is one of the types" in {
    VarType.values.foreach { vType =>
      invalidSeries(f => {
        f.generateStringSample.replaceAll(f.varName, vType.name)
      })
    }
  }

  private def invalidSeries(invalidate: Forc => String): Unit = {
    for (_ <- 0 until DEFAULT_TEST_SIZE) {
      parseExceptionTest(invalidate)
    }
  }

  private def parseExceptionTest(invalidate: Forc => String) = {
    val forc = generateFor()
    assertThrows[ParseException]({
      val parser = ForcParser(new ByteArrayInputStream(invalidate(forc).getBytes))
      parser.forc()
    })
  }

  private def forCorrectInputTest() = {
    val forc = generateFor()
    val parser = ForcParser(new ByteArrayInputStream(forc.generateStringSample.getBytes))
    val res = parser.forc()
    assertResult(treeToStringList(res).mkString(" "))(treeToStringList(res.res).mkString(" "))
    assertResult(true)(forc.verifyResult(res))
  }

}

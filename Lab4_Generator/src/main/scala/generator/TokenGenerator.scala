package generator
import cats.data.State
import cats.implicits.catsSyntaxApplyOps
import grammar.{Grammar, Token}
import util.WriteUtil.writeState

import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path}
import scala.util.Using

case class TokenGenerator(grammar: Grammar[_ <: Token]) extends AbstractGenerator {

  private def IMPORTS: List[String] = List(
    "enumeratum.Enum",
    "grammar.Token"
  )

  override def generateFile(path: Path): Unit = {
    val file = path.resolve(tokenEnumName + ".scala")
    createFile(file, tokenEnumName)
    Using(Files.newBufferedWriter(file, StandardCharsets.UTF_8)) { writer =>
      writeHeaders(writer)
      writeImports(writer, IMPORTS)
      val state = for {
        _ <- writeAbstractClass()(writer)
        _ <- writeCompanion(getTokenMap)(writer)
      } yield ()
      state.runA(0).value
    }
  }

  private def tokenEnumName = grammar.name + "Token"

  private def writeAbstractClass(): BufferedWriter => State[Int, Unit] = { implicit writer: BufferedWriter =>
    writeState(writer, s"sealed abstract class $tokenEnumName(protected val pattern: String) extends Token\n")(
      identity
    )
  }

  private def writeCompanion(tokens: Map[String, String]): BufferedWriter => State[Int, Unit] = {
    implicit writer: BufferedWriter =>
      tokens
        .foldLeft(writeState(writer, s"object $tokenEnumName extends Enum[$tokenEnumName] {")(_ + 1))(
          (state: State[Int, Unit], token) => state.flatMap(_ => writeToken(token._1, token._2)(writer))
        ) *> writeState(writer, s"val values: IndexedSeq[$tokenEnumName] = findValues")(_ - 1) *> writeState(
        writer,
        "}"
      )(identity)
  }

  private def writeToken(name: String, pattern: String): BufferedWriter => State[Int, Unit] = {
    implicit writer: BufferedWriter =>
      writeState(writer, s"case object $name extends $tokenEnumName(\"${unquotePattern(pattern)}\")")(identity)
  }

  private def unquotePattern(pattern: String): String = {
    pattern.substring(1, pattern.length - 1)
  }

  private def getTokenMap: Map[String, String] =
    grammar.lexerRules
      .map(_.token)
      .map(token => token.getName -> token.getPatternAsString)
      .toMap + ("EOF" -> "'\u0000'")
}

package generator
import grammar.{Grammar, Token}

import java.nio.file.{Files, Path}
import cats.data.State
import util.WriteUtil.writeState

import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import scala.util.Using

case class LexerGenerator(grammar: Grammar[? <: Token]) extends AbstractGenerator { self =>

  private def IMPORTS: List[String] = List(
    // todo:
    "grammar.LexerRule",
    "template.AbstractLexer",
    "java.text.ParseException",
    "java.io.InputStream",
    "template.AbstractLexer._",
    "template.Tokenized._"
  )

  override def generateFile(path: Path): Unit = {
    val className = getLexerName
    val file = path.resolve(className + ".scala")
    createFile(file, className)
    Using(Files.newBufferedWriter(file, StandardCharsets.UTF_8)) { writer =>
      writeHeaders(writer)
      writeImports(writer, IMPORTS)
      val state = for {
        _ <- writeState(
          writer,
          s"case class $className(inputStream: InputStream)\n" +
           // s"lexerParams: LexerParams = LexerParams(TokenizedEmpty, 0, 0, -2))\n"
            s"  extends AbstractLexer[$getEnumName](inputStream) {"
        )(x => x + 1)
        _ <- writeState(writer, s"override val lexerRules = List($getLexerRulesAsString)")(identity)
        _ <- writeTokenWithName(writer).modify(_ -1)
        //_ <- writeMoveNext(writer).modify(_ - 1)
        _ <- writeState(writer, "}")(identity)
      } yield ()
      state.runA(0).value
    }
  }

  // private def writeConstructor(writer: BufferedWriter, className: String): State[Int, Unit] =
  //  for {
  //    _ <- writeState(writer, s"case class $className(InputStream inputStream) extends AbstractLexer {")(x=>x+1)
  //    _ <- writeState(writer, s"val lexerRules = List($getLexerRulesAsString)")(identity)
//
  //  } yield()

  private def writeTokenWithName(writer: BufferedWriter): State[Int, Unit] = {
    for {
      _ <- writeState(writer, s"override def getTokenWithName(name: String): ${grammar.name}Token = { ")(x => x + 1)
      _ <- writeState(writer, s"${grammar.name}Token.withName(name)")(x => x - 1)
      _ <- writeState(writer, "}")(identity)
    } yield ()
  }

  private def writeMoveNext(writer: BufferedWriter): State[Int, Unit] = {
    for {
      _ <- writeState(
        writer,
        s"override protected def moveNext(is: InputStream, params: LexerParams): $getLexerName = "
      )(_ + 1)
      _ <- writeState(writer, s"$getLexerName(is, params)")(_ - 1)
    } yield ()
  }

  private def getLexerName: String = grammar.name + "Lexer"

  private def getLexerRulesAsString: String = {

    grammar.lexerRules
      .map(rule => s"LexerRule[${getEnumName}](${getEnumName}.${rule.token.getName}, ${rule.skip})")
      .mkString("\n\t", ",\n\t", "\n  ")
  }

}

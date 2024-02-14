package generator

import cats.data.State
import grammar.{Grammar, Token}
import util.WriteUtil.writeLine

import java.io.BufferedWriter
import java.nio.file.{Files, Path}

trait AbstractGenerator {
  protected type StateWithWriter = BufferedWriter => State[Int, Unit]

  protected def grammar: Grammar[? <: Token]

  def generateFile(path: Path): Unit

  protected def writeHeaders(writer: BufferedWriter): Unit =
    grammar.headers
      .map(header => {
        writer.write(header.code)
        writer.newLine()
      })
      .getOrElse()

  protected def createFile(file: Path, className: String): Unit = {
    if (Files.deleteIfExists(file)) {
      println(s"Old version of $file deleted")
    }
    Files.createFile(file)
    println(s"Created $className")
  }

  def writeImports(writer: BufferedWriter, imports: List[String]): Unit = {
    for (imprt <- imports) {
      writeLine(writer, s"import $imprt;", 0)
    }
    writer.newLine()
  }

  protected def getEnumName = s"${grammar.name}Token"
}

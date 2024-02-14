package generator

import cats.data.State
import grammar.entry.{GrammarEntry, NonTerminal, Terminal, TranslatingSymbol}
import grammar.{Grammar, ParserRule, Token}
import util.CommonUtils.getDefaultValueByTypeAsString
import util.Ternary.Ternary
import util.WriteUtil.writeState

import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path}
import scala.util.Using

case class ParserGenerator(grammar: Grammar[_ <: Token]) extends AbstractGenerator {
  private def IMPORTS: List[String] = List(
    "grammar.LexerRule",
    "template.AbstractLexer",
    "util.Tree",
    "util.GrammarTree",
    "util.GrammarTree._",
    "java.text.ParseException",
    "java.io.InputStream",
    "template.Tokenized._"
  )
  // todo: writer.newLine()

  // TODO: more clear; structurize || to different files
  // todo: AbstractGenerator::writeState -> WriteUtil[to_create]

  override def generateFile(path: Path): Unit = {
    val className = getParserName
    val file = path.resolve(className + ".scala")
    createFile(file, className)
    Using(Files.newBufferedWriter(file, StandardCharsets.UTF_8)) { writer =>
      writeHeaders(writer)
      writeImports(writer, IMPORTS)
      val st = for {
        _ <- writeState(writer, s"case class $className(inputStream: InputStream) {")(_ + 1)
        _ <-
          writeState(writer, s"val lex: ${grammar.name}Lexer = ${grammar.name}Lexer(inputStream)")(identity)
        _ <- writeState(writer, "lex.lexerParams = lex.nextToken()")(identity)
      } yield ()
      val st2 = for {
        _ <- grammar.parserRules.values
          .foldLeft(st)((state, rule) =>
            for {
              _ <- state
              _ <- writeRuleContext(rule).andThen(s => {
                writer.newLine()
                s
              })(writer)
              _ <- writeParseRule(rule).andThen(s => {
                  writer.newLine()
                  s
                })(writer)

            } yield ()
          )
          .modify(_ - 1)
        _ <- writeState(writer, "}")(identity)
      } yield ()
      st2.runA(0).value

    }
  }

  private def writeParseRule(rule: ParserRule): StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- writeState(
        writer,
        s"def ${rule.name}(${rule.inheritAttrs.map(attr => attr.attrValue + " : " + attr.attrType).mkString(", ")}) : ${getContextName(rule)} = {"
      )(_ + 1)

      _ <- {
        val st = writeState(writer, s"var ctx = ${getContextName(rule)}(\"${rule.name}\")")(identity)
        rule.rules.toList match {
          case Nil      => st
          case x :: Nil => st.flatMap(_ => writePossibleNext(x, rule)(writer))
          case x :: xs =>
            val st1 = st.flatMap(_ => writePossibleNext(x, rule)(writer))
            xs.foldLeft(st1)((state, entries) =>
              state.flatMap(_ => writePossibleNext(entries, rule, elseif = true)(writer))
            )
        }
      }
      _ <- {
        val exceptionStr = s"throw new ParseException(\"Unexpected token: \" + lex.curToken().text, lex.curPos())"
        (rule.rules.toList == Nil) ?? (
          writeState(writer, exceptionStr)(identity),
          for {
            _ <- writeState(writer, "else {")(_ + 1)
            _ <- writeState(writer, exceptionStr)(_ - 1)
            _ <- writeState(writer, "}")(identity)
          } yield ()
        )

      }.modify(_ - 1)
      _ <- writeState(writer, "}")(identity)

    } yield ()

  }

  private def writePossibleNext(
    entries: List[GrammarEntry],
    parserRule: ParserRule,
    elseif: Boolean = false
  ): StateWithWriter = { implicit writer: BufferedWriter =>
    val nextElems = grammar.getNextRule(entries, parserRule.name)
    val cond = "lex.curTokenIn(" + nextElems.isEmpty ?? (s"Set.empty[$getEnumName]", s"Set(${nextElems
        .map(s => s"${getEnumValue(s)}")
        .mkString(", ")})") + ")"

    for {
      _ <- entries.foldLeft(writeState(writer, s"${elseif ?? ("else if", "if")}($cond) {")(_ + 1))((state, entry) =>
        state.flatMap(_ => writeEntryInRuleCond(entry)(writer))
      )
      _ <- writeState(writer, "ctx")(_ - 1)
      _ <- writeState(writer, "}")(identity)

    } yield ()
  }

  private def writeEntryInRuleCond(entry: GrammarEntry): StateWithWriter = { implicit writer: BufferedWriter =>
    entry match {
      case nt: NonTerminal =>
        for {
          _ <- writeState(
            writer,
            s"val ${nt.name} = ${nt.value}(${nt.translatingSymbol.code})"
          )(identity)
          _ <- writeState(writer, s"ctx = ctx.appendLastChild(${nt.name})")(identity)
        } yield ()
      case term: Terminal =>
        for {
          _ <- writeState(writer, s"if(!lex.compareToken(${getEnumValue(term.value)})) {")(_ + 1)
          _ <- writeState(
            writer,
            s"throw new ParseException(\"Expected ${term.value}, found:\" + lex.curToken().text, lex.curPos())"
          )(_ - 1)
          _ <- writeState(writer, s"}")(identity)
          _ <- writeState(writer, s"ctx = ctx.appendLastChild(TerminalTree(lex.curToken()))")(identity)
          _ <- writeState(writer, s"val ${term.name} = lex.curToken()")(identity)
          _ <- writeState(writer, "lex.nextToken()")(identity)
        } yield ()
      case trans: TranslatingSymbol =>
        trans
          .normalize("ctx")
          .code
          .split("\n")
          .foldLeft(State.empty[Int, Unit])((state, line) => state.flatMap(_ => writeState(writer, line)(identity)))
    }
  }

  private def writeRuleContext(rule: ParserRule): BufferedWriter => State[Int, Unit] = {
    implicit writer: BufferedWriter =>
      for {
        _ <- rule.synteticAttrs
          .foldLeft(
            writeState(
              writer,
              s"case class ${getContextName(rule)}(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty) extends ContextTree(ctxRoot, ctxChildren) {"
            )(_ + 1)
          )((state: State[Int, Unit], attr) =>
            state.flatMap(_ =>
              writeState(
                writer,
                s"var ${attr.attrValue}: ${attr.attrType} = ${getDefaultValueByTypeAsString(attr.attrType)}"
              )(identity)
            )
          )

        _ <- writeState(
          writer,
          s"override def pushFirstChild(child: GrammarTree[_]) = ${getContextName(rule)}(ctxRoot, child::ctxChildren)"
        )(identity)
        _ <- writeState(
          writer,
          s"override def appendLastChild(child: GrammarTree[_]) = ${getContextName(rule)}(ctxRoot, ctxChildren ++ List(child))"
        )(_ - 1)
        _ <- writeState(writer, " }")(identity)
      } yield ()
  }

  // todo: get*..*Name -> AbstractGenerator
  private def getParserName = grammar.name + "Parser"

  private def getEnumValue(value: String) =
    s"$getEnumName.$value"

  private def getContextName(parserRule: ParserRule) = s"${parserRule.name.capitalize}Context"

}

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
    "template.AbstractLexer.LexerParams",
    "util.Tree",
    "util.GrammarTree",
    "util.GrammarTree._",
    "util.CommonUtils._",
    "java.text.ParseException",
    "java.io.InputStream",
    "cats.data.State",
    "template.Tokenized._"
  )
  // todo: writer.newLine()

  override def generateFile(path: Path): Unit = {
    val className = getParserName
    val file = path.resolve(className + ".scala")
    createFile(file, className)
    Using(Files.newBufferedWriter(file, StandardCharsets.UTF_8)) { writer =>
      writeHeaders(writer)
      writeImports(writer, s"$className._" :: IMPORTS)
      val st = for {
        _ <- writeState(writer, s"case class $className(inputStream: InputStream) {")(_ + 1)
        _ <- writeState(
          writer,
          s"val lex: $getLexerName = $getLexerName(inputStream, " +
            s"$getLexerName(inputStream, LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken())"
        )(identity)
      } yield ()
      val st2 = for {
        _ <- grammar.parserRules.values
          .foldLeft(st)((state, rule) =>
            for {
              _ <- state
              _ <- writeParseRule(rule).andThen(s => {
                writer.newLine()
                s
              })(writer)
            } yield ()
          )
          .modify(_ - 1)
        _ <- writeState(writer, "}\n")(identity)
        _ <- writeParserCompanion(grammar.parserRules.values.toList)(writer)
      } yield ()
      st2.runA(0).value
    }
  }

  private def writeParserCompanion(rules: List[ParserRule]): StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- rules
        .foldLeft(writeState(writer, s"object $getParserName {")(_ + 1))((state, rule) =>
          for {
            _ <- state
            _ <- writeRuleContext(rule).andThen(s => {
              writer.newLine()
              s
            })(writer)
          } yield ()
        )
        .modify(_ - 1)
      _ <- writeState(writer, "}")(identity)
    } yield ()
  }

  private def writeParseRule(rule: ParserRule): StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- writeState(
        writer,
        s"def ${rule.name}(${rule.inheritAttrs.map(attr => attr.attrValue + " : " + attr.attrType).mkString(", ")}) "
          + s": State[$getLexerName, ${getContextName(rule)}] = {"
      )(_ + 1)

      _ <- writeState(writer, s"for { ")(_ + 1)
      _ <- writeState(writer, s"lex <- getEmptyListState[$getLexerName].inspect(lex => lex)")(identity)
      _ <- {
        val st = writeState(writer, s"res <- ")(_ + 1)

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
            _ <- writeState(writer, "}")(_ - 1)
          } yield ()
        )
      }.modify(_ - 1)
      _ <- writeState(writer, "} yield res")(_ - 1)
      _ <- writeState(writer, "}")(identity)
    } yield ()
  }

  private def writePossibleNext(
    entries: List[GrammarEntry],
    parserRule: ParserRule,
    elseif: Boolean = false
  ): StateWithWriter = { implicit writer: BufferedWriter =>
    val nextElems = grammar.getNextRule(entries, parserRule.name)
    val cond = "lex.curTokenIn(" + nextElems.isEmpty ?? (s"Set.empty[$getEnumName]",
    s"Set(${nextElems.map(s => s"${getEnumValue(s)}").mkString(", ")})") + ")"

    for {
      _ <- writeState(writer, s"${elseif ?? ("else if", "if")}($cond) {")(_ + 1)
      _ <- writeState(writer, "val result = for {")(_ + 1)
      _ <- entries.foldLeft(writeState(writer, s"curState <- getEmptyListState[$getLexerName]")(identity))(
        (state, entry) => state.flatMap(_ => writeEntryInRuleCond(entry)(writer))
      )
      _ <- writeState(writer, s"children <- getCurState[$getLexerName](curState)")(_ - 1)
      _ <- writeState(
        writer, {
          val attrs = parserRule.synteticAttrs.map(attr => s"${attr.attrValue}").mkString(", ")
          s"} yield(${getContextName(parserRule)}(\"${parserRule.name}\", children.reverse" +
            s"${attrs.isEmpty ?? ("", ", " ++ attrs)}))"
        }
      )(identity)
      _ <- writeState(writer, "result")(_ - 1)
      _ <- writeState(writer, "}")(identity)
    } yield ()
  }

  private def writeEntryInRuleCond(entry: GrammarEntry): StateWithWriter = { implicit writer: BufferedWriter =>
    entry match {
      case nt: NonTerminal =>
        for {
          _ <- writeState(writer, s"${nt.name} <- ${nt.value}(${nt.translatingSymbol.code})")(identity)
          _ <- writeState(writer, s"curState <- getCurState[$getLexerName](curState).map(list => ${nt.name} :: list)")(
            identity
          )
        } yield ()
      case term: Terminal =>
        for {
          _ <- writeState(
            writer,
            s"${term.name} <- getCurState[$getLexerName](curState).inspect(lexer => lexer.curToken())"
          )(identity)
          _ <- writeState(writer, s"_ <- getCurState[$getLexerName](curState).inspect(lexer => {")(_ + 1)
          _ <- writeState(writer, s"if(!lexer.compareToken(${getEnumValue(term.value)})) {")(_ + 1)
          _ <- writeState(
            writer,
            s"throw new ParseException(\"Expected ${term.value}, found:\" + lexer.curToken().text, lexer.curPos())"
          )(_ - 1)
          _ <- writeState(writer, s"}")(_ - 1)
          _ <- writeState(writer, "})")(identity)
          _ <- writeState(writer, s"curState <- getCurState[$getLexerName](curState)")(_ + 1)
          _ <- writeState(writer, s".inspect(lexer => TerminalTree(lexer.curToken()) :: curState)")(identity)
          _ <- writeState(writer, s".modify(lexer => $getLexerName(lexer.inputStream, lexer.nextToken()))")(_ - 1)
        } yield ()
      case trans: TranslatingSymbol =>
        for {
          _ <- writeState(writer, "")(identity)
          _ <- trans
            .normalize("ctx")
            .code
            .split("\r?\n")
            .foldLeft(writeState(writer, "// USER CODE")(identity))((state, line) =>
              state.flatMap(_ => writeState(writer, line)(identity))
            )
          _ <- writeState(writer, "// END USER CODE")(identity)
          _ <- writeState(writer, "")(identity)
        } yield ()
    }
  }

  private def writeRuleContext(rule: ParserRule): BufferedWriter => State[Int, Unit] = {
    implicit writer: BufferedWriter =>
      for {
        _ <- writeState(
          writer, {
            val attrs = getRuleSynteticAttrsForContext(rule)
            s"case class ${getContextName(rule)}(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty" +
              s"${attrs.isEmpty ?? ("", ", " ++ attrs)}) extends ContextTree(ctxRoot, ctxChildren) {"
          }
        )(_ + 1)
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

  private def getRuleSynteticAttrsForContext(rule: ParserRule): String =
    rule.synteticAttrs
      .map(attr => s"val ${attr.attrValue}: ${attr.attrType} = ${getDefaultValueByTypeAsString(attr.attrType)}")
      .mkString(", ")

  private def getEnumValue(value: String) =
    s"$getEnumName.$value"

  private def getContextName(parserRule: ParserRule) = s"${parserRule.name.capitalize}Context"
}

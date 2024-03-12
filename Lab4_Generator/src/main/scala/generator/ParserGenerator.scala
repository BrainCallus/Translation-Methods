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
    "template.Tokenized._",
    "util.Tree",
    "util.GrammarTree",
    "util.GrammarTree._",
    "util.CommonUtils._",
    "util.TypeOps._",
    "java.text.ParseException",
    "java.io.InputStream",
    "cats.data.{EitherT, StateT}",
    "cats.Monad"
  )

  override def generateFile(path: Path): Unit = {
    val className = getParserName
    val file = path.resolve(className + ".scala")
    createFile(file, className)
    Using(Files.newBufferedWriter(file, StandardCharsets.UTF_8)) { writer =>
      writeHeaders(writer)
      writeImports(writer, s"$className._" :: IMPORTS)
      val st2 = for {
        _ <- writeParserClass()(writer)
        _ <- writeParserCompanion(grammar.parserRules.values.toList)(writer)
      } yield ()
      st2.runA(0).value
    }
  }

  private val parseStateName = s"${grammar.name}ParseState"
  private val metName = s"${grammar.name}MET"

  private def writeParserClass(): StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- writeState(writer, s"case class $getParserName[F[_]: Monad](inputStream: InputStream) {")(_ + 1)
      _ <- writeState(writer, s"private type $metName[A] = MkContainer[MET, F]#Cont[A]")(identity)
      _ <- writeState(writer, s"type $parseStateName[A] = ParseState[F, $getLexerName, A]")(identity)
      st = writeState(
        writer,
        s"val lex: $getLexerName = $getLexerName(inputStream, " +
          s"$getLexerName(inputStream,\n    LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken()" +
          ".getOrElse(LexerParams(TokenizedEmpty, 0, 0, -2)))"
      )(identity)
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
      _ <- writeLexerNext(writer).modify(_ - 1)
      _ <- writeState(writer, "}\n")(identity)
    } yield ()
  }

  private def writeParseRule(rule: ParserRule): StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- writeState(
        writer,
        s"def ${rule.name}(${rule.inheritAttrs.map(attr => attr.attrValue + " : " + attr.attrType).mkString(", ")}) "
          + s": $parseStateName[${getContextName(rule)}] = {"
      )(_ + 1)

      _ <- writeState(writer, s"for { ")(_ + 1)
      _ <- writeState(writer, s"lex <- StateT.get[$metName, $getLexerName]")(identity)
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
        val exceptionStr =
          s"throwPEStateT[F, $getLexerName, ${getContextName(rule)}](\"Unexpected token: \" + lex.curToken())"
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
      _ <- writeState(writer, s"${elseif ?? ("else if", "if")} ($cond) {")(_ + 1)
      _ <- writeState(writer, "for {")(_ + 1)
      _ <- entries.foldLeft(
        writeState(writer, s"curState <- StateT.pure[$metName, $getLexerName, List[GrammarTree[_]]](List.empty)")(
          identity
        )
      )((state, entry) => state.flatMap(_ => writeEntryInRuleCond(entry)(writer)))
      _ <- writeState(writer, s"children <- StateT.pure[$metName, $getLexerName, List[GrammarTree[_]]](curState)")(
        _ - 1
      )
      _ <- writeState(
        writer, {
          val attrs = parserRule.synteticAttrs.map(attr => s"${attr.attrValue}").mkString(", ")
          s"} yield ${getContextName(parserRule)}(\"${parserRule.name}\", children.reverse" +
            s"${attrs.isEmpty ?? ("", ", " ++ attrs)})"
        }
      )(_ - 1)
      _ <- writeState(writer, "}")(identity)
    } yield ()
  }

  private def writeEntryInRuleCond(entry: GrammarEntry): StateWithWriter = { implicit writer: BufferedWriter =>
    entry match {
      case nt: NonTerminal =>
        for {
          _ <- writeState(writer, s"${nt.name} <- ${nt.value}(${nt.translatingSymbol.code})")(identity)
          _ <- writeState(
            writer,
            s"curState <- StateT.pure[$metName, $getLexerName, List[GrammarTree[_]]](curState).map(list => ${nt.name} :: list)"
          )(
            identity
          )
        } yield ()
      case term: Terminal =>
        for {
          _ <- writeState(
            writer,
            s"${term.name} <- StateT.get[$metName, $getLexerName].map(lexer => lexer.curToken())"
          )(identity)
          _ <- writeState(writer, s"lexer <- StateT.get[$metName, $getLexerName]")(identity)
          _ <- writeCurTokenCheck(term)(writer)
          _ <- writeState(writer, s"curState <- StateT.pure[$metName, $getLexerName, List[GrammarTree[_]]](curState)")(
            _ + 1
          )
          _ <- writeState(writer, s".map(list => TerminalTree(lexer.curToken()) :: list)")(_ - 1)
          _ <- writeState(writer, s"curState <- getLexerWithNextToken(curState)")(identity)
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

  private def writeCurTokenCheck(term: Terminal): StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- writeState(writer, s"_ <-")(_ + 1)
      _ <- writeState(writer, s"if (!lexer.compareToken(${getEnumValue(term.value)})) {")(_ + 1)
      _ <- writeState(
        writer,
        s"throwPEStateT[F, $getLexerName, List[GrammarTree[_]]](\"Expected ${term.value}, found:\" + lexer.curToken().text)"
      )(_ - 1)
      _ <- writeState(writer, s"} else {")(_ + 1)
      _ <- writeState(writer, s"StateT.pure[$metName, $getLexerName, List[GrammarTree[_]]](curState)")(_ - 1)
      _ <- writeState(writer, "}")(_ - 1)
    } yield ()
  }

  private def writeLexerNext: StateWithWriter = { implicit writer: BufferedWriter =>
    for {
      _ <- writeState(writer, "")(identity)
      _ <- writeState(
        writer,
        s"private def getLexerWithNextToken(curState: List[GrammarTree[_]])" +
          s": $parseStateName[List[GrammarTree[_]]] = {"
      )(_ + 1)
      _ <- writeState(writer, s"StateT.apply[$metName, $getLexerName, List[GrammarTree[_]]] { lexer =>")(_ + 1)
      _ <- writeState(writer, s"lexer.nextToken().fold(")(_ + 1)
      _ <- writeState(writer, s"e => EitherT.leftT(e),")(identity)
      _ <- writeState(writer, s"params => EitherT.rightT(($getLexerName(lexer.inputStream, params), curState))")(_ - 1)
      _ <- writeState(writer, ")")(_ - 1)
      _ <- writeState(writer, "}")(_ - 1)
      _ <- writeState(writer, "}")(identity)
    } yield ()
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

  private def writeRuleContext(rule: ParserRule): BufferedWriter => State[Int, Unit] = {
    implicit writer: BufferedWriter =>
      val contextName = getContextName(rule)
      for {
        _ <- writeState(
          writer, {
            val attrs = getRuleSynteticAttrsForContext(rule)
            s"case class $contextName(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty" +
              s"${attrs.isEmpty ?? ("", ", " ++ attrs)})\n  extends ContextTree(ctxRoot, ctxChildren) {"
          }
        )(_ + 1)
        _ <- writeState(
          writer,
          s"override def pushFirstChild(child: GrammarTree[_]): $contextName = $contextName(ctxRoot, child::ctxChildren)"
        )(identity)
        _ <- writeState(
          writer,
          s"override def appendLastChild(child: GrammarTree[_]): $contextName = $contextName(ctxRoot, ctxChildren ++ List(child))"
        )(_ - 1)
        _ <- writeState(writer, "}")(identity)
      } yield ()
  }

  private def getRuleSynteticAttrsForContext(rule: ParserRule): String =
    rule.synteticAttrs
      .map(attr => s"${attr.attrValue}: ${attr.attrType} = ${getDefaultValueByTypeAsString(attr.attrType)}")
      .mkString(", ")

  private def getEnumValue(value: String) =
    s"$getEnumName.$value"

  private def getContextName(parserRule: ParserRule) = s"${parserRule.name.capitalize}Context"
}

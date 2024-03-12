import antlr.{LexerGrammar, ParserGrammar}
import calculator.CalculatorParser
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import forc.ForcParser
import generator.{LexerGenerator, ParserGenerator, TokenGenerator}
import grammar.entry.{GrammarEntry, NonTerminal, TranslatingSymbol}
import grammar.{Grammar, LexerRule, ParserRule, Token}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import prom.{JavaLexerRule, JavaParserRule}
import template.InterimToken
import util.CommonUtils.convertList
import util.Ternary.Ternary
import util.WriteUtil.makeGraph

import java.io.ByteArrayInputStream
import java.nio.file.{Files, Path}
import java.text.ParseException
import java.util.Optional
import scala.util.Try

object Main {
  private val calcPath = "source_grammar\\Calculator.g4"
  private val forPath = "source_grammar\\Forc.g4"
  private val genDir = Path.of("src\\gen")

  def main(args: Array[String]): Unit = {
    generateAndRunCalc()
    generateAndRunFor()
  }

  private def generateAndRunFor(): Unit = {
    generateFiles(forPath)
    val forcParser = ForcParser[IO](
      new ByteArrayInputStream("for     (long vArfor1i=1000   ; vArfor1i<=    -10;vArfor1i-- )".getBytes())
    )
    forcParser.forc().run(forcParser.lex).value.redeem(
        {
          case x: ParseException => Left(x)
          case e                 => Left(new ParseException(e.getMessage, -1))
        },
        identity
      )
      .map {
        case Left(e)    => println(e)
        case Right(res) => makeGraph(res._2.res, "graphviz\\forc\\graphFor.dot")
      }
      .unsafeRunSync()
  }

  private def generateAndRunCalc(): Unit = {
    generateFiles(calcPath)
    val calculatorParser = new CalculatorParser[IO](
      new ByteArrayInputStream(
        "  64 ^ 4 ^ 2 + 2*2/(2/2) - 10 + 0.1".getBytes() // 0.1
      )
    )
    calculatorParser.calculator().run(calculatorParser.lex).value
      .redeem(
        {
          case x: ParseException => Left(x)
          case e                 => Left(new ParseException(e.getMessage, -1))
        },
        identity
      )
      .map {
        case Left(e)    => println(e)
        case Right(res) => println(res._2.res)
      }
      .unsafeRunSync()
  }

  private def generateFiles(path: String): Unit = {
    val grammarPath = Path.of(path)
    if (!Files.exists(grammarPath)) {
      println("not exist")
    }

    Try({
      val grammar = getGrammar(grammarPath)
      val outDir = prepareEnvironment(grammar)
      generateParser(outDir, grammar)
    }).getOrElse(println("exception occurred"))
  }

  private def prepareEnvironment(grammar: Grammar[_]): Path = {
    val packageGrammar = genDir.resolve(grammar.name.toLowerCase())

    if (!Files.isDirectory(packageGrammar)) {
      Files.createDirectory(packageGrammar)
    }
    packageGrammar
  }

  private def getGrammar(grammarPath: Path): Grammar[InterimToken] = {
    val lexerGrammar = new LexerGrammar(CharStreams.fromPath(grammarPath))
    val parserGrammar = new ParserGrammar(new CommonTokenStream(lexerGrammar))
    val javaGrammar = parserGrammar.translationGrammar().gr
    val lr = javaGrammar.lexerRules
    val lexerRules: List[LexerRule[InterimToken]] = tokenizeJavaLexerRules(convertList(lr))
    val headers: Option[TranslatingSymbol] = javaOptionalToOption(javaGrammar.headers)
    val parserRules = toParserRules(convertList(javaGrammar.parserRules))
    Grammar(javaGrammar.name, headers, parserRules, lexerRules)
  }

  private def toParserRules(parserRules: List[JavaParserRule]): List[ParserRule] = {
    parserRules.map(pr =>
      ParserRule(
        pr.name,
        convertList(pr.inheritAttrs),
        convertList(pr.synteticAttrs),
        convertList(pr.parserRules).map(javaList => convertList(javaList).map(replaceNullSymbolsToEmpty)).toSet
      )
    )
  }

  private def replaceNullSymbolsToEmpty[E <: GrammarEntry](entry: E): GrammarEntry =
    entry match {
      case NonTerminal(name, value, symbol) =>
        (symbol == null) ?? (NonTerminal(name, value, TranslatingSymbol("")), entry)
      case _ => entry
    }

  private def tokenizeJavaLexerRules(lexerRules: List[JavaLexerRule]): List[LexerRule[InterimToken]] = {
    lexerRules.map(r => LexerRule(InterimToken(r.name, r.pattern), r.skip))
  }

  private def javaOptionalToOption[T](opt: Optional[T]): Option[T] = {
    if (opt.isEmpty) {
      None
    } else {
      Some(opt.get())
    }
  }

  private def generateParser(outDir: Path, grammar: Grammar[? <: Token]): Unit = {
    val tokenGenerator = TokenGenerator(grammar)
    val lexerGenerator = LexerGenerator(grammar)
    val parserGenerator = ParserGenerator(grammar)

    tokenGenerator.generateFile(outDir)
    lexerGenerator.generateFile(outDir)
    parserGenerator.generateFile(outDir)
  }
}

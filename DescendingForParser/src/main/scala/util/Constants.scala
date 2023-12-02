package util

import grammar_entry.GrammarEntry.NonTerminal
import grammar_entry.Token

import java.util.regex.Pattern

object Constants {
  val epsilon = 'ε'
  val validTypeNames: List[String] = List(
    "int32",
    "int64",
    "int",
    "uint",
    "nint",
    "nuint",
    "long",
    "ulong",
    "short",
    "ushort",
    "byte",
    "size_t",
    "double",
    "float"
  )

  private val patternParts: List[String] =
    List(
      Token.FOR.getPatternAsString,
      Token.LPAREN.getPatternAsString,
      Token.TYPE_NAME.getPatternAsString + "\\s+" + Token.NAME.getPatternAsString,
      Token.EQ.getPatternAsString,
      Token.NUMBER.getPatternAsString,
      Token.SEMICOLON.getPatternAsString,
      "\\2",
      "[" + Token.LT.getPatternAsString + Token.GT.getPatternAsString + "]" + Token.EQ.getPatternAsString + "?",
      Token.NUMBER.getPatternAsString,
      Token.SEMICOLON.getPatternAsString,
      "\\2",
      "(" + Token.INC.getPatternAsString + "|" + Token.DEC.getPatternAsString + ")",
      Token.RPAREN.getPatternAsString
    )

  val forPattern: Pattern = Pattern.compile(patternParts.mkString("\\s*"));

  val derivations: Map[String, NonTerminal] = Map(
    "S"->  NonTerminal("S", Seq(List("FOR", "LPAREN", "INNER", "RPAREN"))),
    "INNER"->NonTerminal("INNER", Seq(List("P1", "SEMICOLON", "P2", "SEMICOLON", "P3"))),
    "P1"-> NonTerminal("P1", Seq(List("TYPE_NAME", "NAME", "EQ", "NUMBER"))),
    "P2" -> NonTerminal("P2", Seq(List("NAME", "CMP", "NUMBER"))),
    "P3" -> NonTerminal("P3", Seq(List("NAME", "INC_DEC"))),
    "CMP" -> NonTerminal("CMP", Seq(List("LT_GT", "Strict"))),
    "LT_GT" -> NonTerminal("LT_GT", Seq(List("LT"), List("GT"))),
    "Strict" -> NonTerminal("Strict", Seq(List("EQ"), List("EPS"))),
    "INC_DEC" -> NonTerminal("INC_DEC", Seq(List("INC"), List("DEC")))
  )
  
}

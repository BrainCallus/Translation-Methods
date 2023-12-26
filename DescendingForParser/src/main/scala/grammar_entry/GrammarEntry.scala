package grammar_entry

import exception.ParseException
import functional.Tree.{Leaf, NonTerminalTree}
import functional.{LexicalAnalyzer, Tree}

import scala.annotation.tailrec
import cats.data.State
import grammar_entry.GrammarEntry.StateResult
import util.Util._

trait GrammarEntry {
  def parseState(analyzer: LexicalAnalyzer, grammar: Grammar): StateResult
}

object GrammarEntry {
  type StateResult = (LexicalAnalyzer, Tree[_])
  private type StateMultiResult = (LexicalAnalyzer, List[Tree[_]])

  final case class Terminal(token: Token) extends GrammarEntry {
    override def parseState(analyzer: LexicalAnalyzer, grammar: Grammar): StateResult = {
      State[LexicalAnalyzer, Tree[_]](lex => {
        (lex.moveNext(), Leaf(lex.getValueAndToken))
      }).run(analyzer).value
    }
  }

  final case class NonTerminal private (
    name: String,
    rules: Set[List[String]],
    var FIRST: Set[Token] = Set.empty[Token]
  ) extends GrammarEntry {

    override def parseState(analyzer: LexicalAnalyzer, grammar: Grammar): StateResult = {
      val derivation = chooseDerivationByFirst(analyzer.getCurToken, grammar)
      if (derivation.isEmpty && !getFIRST(grammar).contains(Token.EPS)) {
        throw new ParseException(
          s"Unexpected token at position ${analyzer.currentPosition} found: ${analyzer.getCurToken}"
        )
      }

      derivation.isEmpty ?? ((
        analyzer,
        NonTerminalTree(name, List(Leaf((util.Constants.epsilon.toString, Token.EPS))))
      ), {
        val children =
          foldl((analyzer, List.empty[Tree[_]]))((acc: StateMultiResult, entry: GrammarEntry) => {
            val stateAndTree = entry parseState (acc._1, grammar)
            (stateAndTree._1, stateAndTree._2 :: acc._2)
          })(derivation map (part => grammar getDerivation part))

        (children._1, NonTerminalTree(name, children._2.reverse))
      })
    }

    private def getFIRST(grammar: Grammar): Set[Token] = {
      if (FIRST.isEmpty) {
        FIRST = FIRST ++ extractFirst(grammar, rules.map(d => d.head).toList)(FIRST)
      }
      FIRST
    }

    private def chooseDerivationByFirst(token: Token, grammar: Grammar): List[String] = {
      for (rule <- rules) {
        grammar getDerivation rule.head match {
          case x: NonTerminal => if (x getFIRST grammar contains token) return rule
          case y: Terminal    => if (y.token == token) return rule
        }
      }
      List.empty[String]
    }

    @tailrec
    private def extractFirst(grammar: Grammar, ruleHeads: List[String], curPos: Int = 0)(
      first: Set[Token] = Set.empty[Token]
    ): Set[Token] =
      if (curPos >= ruleHeads.length) {
        first
      } else {
        grammar getDerivation ruleHeads(curPos) match {
          case x: NonTerminal => extractFirst(grammar, ruleHeads, curPos + 1)(first ++ x.getFIRST(grammar))
          case y: Terminal    => extractFirst(grammar, ruleHeads, curPos + 1)(first ++ Set(y.token))
        }
      }
  }

  object NonTerminal {
    def apply(name: String, rules: Seq[List[String]]) = new NonTerminal(name, rules.toSet[List[String]])
  }
}

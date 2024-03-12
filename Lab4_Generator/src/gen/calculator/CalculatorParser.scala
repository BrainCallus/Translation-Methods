
package calculator;

import CalculatorParser._
import grammar.LexerRule
import template.AbstractLexer
import template.AbstractLexer.LexerParams
import template.Tokenized._
import util.Tree
import util.GrammarTree
import util.GrammarTree._
import util.CommonUtils._
import util.TypeOps._
import java.text.ParseException
import java.io.InputStream
import cats.data.{EitherT, StateT}
import cats.Monad

case class CalculatorParser[F[_]: Monad](inputStream: InputStream) {
  private type CalculatorMET[A] = MkContainer[MET, F]#Cont[A]
  type CalculatorParseState[A] = ParseState[F, CalculatorLexer, A]
  val lex: CalculatorLexer = CalculatorLexer(inputStream, CalculatorLexer(inputStream,
    LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken().getOrElse(LexerParams(TokenizedEmpty, 0, 0, -2)))

  def calculator() : CalculatorParseState[CalculatorContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            e <- expr()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
            e1 <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.EOF)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected EOF, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = e.res;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield CalculatorContext("calculator", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, CalculatorContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def term() : CalculatorParseState[TermContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            h <- highPrior()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
            m <- muldiv(h.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => m :: list)
            
            // USER CODE
            res = m.res;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield TermContext("term", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, TermContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def muldiv(in : Double) : CalculatorParseState[MuldivContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.MUL))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            m <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.MUL)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected MUL, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            h <- highPrior()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
            mm <- muldiv(in * h.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => mm :: list)
            
            // USER CODE
            res = mm.res
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield MuldivContext("muldiv", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.DIV))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            d <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.DIV)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected DIV, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            h <- highPrior()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
            mm <- muldiv(in / h.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => mm :: list)
            
            // USER CODE
            res = mm.res
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield MuldivContext("muldiv", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.ADD, CalculatorToken.SUB, CalculatorToken.EOF, CalculatorToken.RPAREN))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            
            // USER CODE
            res = in;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield MuldivContext("muldiv", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, MuldivContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def expr() : CalculatorParseState[ExprContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            h <- highPrior()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => h :: list)
            m <- muldiv(h.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => m :: list)
            as <- addsub(m.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => as :: list)
            
            // USER CODE
            res = as.res
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield ExprContext("expr", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, ExprContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def exp(in : Double) : CalculatorParseState[ExpContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.POW))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            p <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.POW)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected POW, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            a <- atom()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => a :: list)
            e <- exp(a.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
            
            // USER CODE
            res = math.pow(in, e.res);
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield ExpContext("exp", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN, CalculatorToken.SUB, CalculatorToken.ADD, CalculatorToken.DIV, CalculatorToken.MUL))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            
            // USER CODE
            res = in;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield ExpContext("exp", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, ExpContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def highPrior() : CalculatorParseState[HighPriorContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.SUB, CalculatorToken.LPAREN, CalculatorToken.NUMBER))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            a <- atom()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => a :: list)
            e <- exp(a.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
            
            // USER CODE
            res = e.res
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield HighPriorContext("highPrior", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, HighPriorContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def addsub(in : Double) : CalculatorParseState[AddsubContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.ADD))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            a <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.ADD)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected ADD, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            t <- term()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => t :: list)
            as <- addsub(in + t.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => as :: list)
            
            // USER CODE
            res = as.res
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield AddsubContext("addsub", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.SUB))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            s <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.SUB)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected SUB, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            t <- term()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => t :: list)
            as <- addsub(in - t.res)
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => as :: list)
            
            // USER CODE
            res = as.res
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield AddsubContext("addsub", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.EOF, CalculatorToken.RPAREN))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            
            // USER CODE
            res = in;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield AddsubContext("addsub", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, AddsubContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def atom() : CalculatorParseState[AtomContext] = {
    for { 
      lex <- StateT.get[CalculatorMET, CalculatorLexer]
      res <- 
        if (lex.curTokenIn(Set(CalculatorToken.SUB))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            s <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.SUB)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected SUB, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            l <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.LPAREN)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected LPAREN, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            e <- expr()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
            r <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.RPAREN)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected RPAREN, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = -e.res;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield AtomContext("atom", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.LPAREN))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            l <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.LPAREN)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected LPAREN, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            e <- expr()
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState).map(list => e :: list)
            r <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.RPAREN)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected RPAREN, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = e.res;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield AtomContext("atom", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(CalculatorToken.NUMBER))) {
          for {
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](List.empty)
            d <- StateT.get[CalculatorMET, CalculatorLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[CalculatorMET, CalculatorLexer]
            _ <-
              if (!lexer.compareToken(CalculatorToken.NUMBER)) {
                throwPEStateT[F, CalculatorLexer, List[GrammarTree[_]]]("Expected NUMBER, found:" + lexer.curToken().text)
              } else {
                StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = (d.text).toDouble;
            // END USER CODE
            
            children <- StateT.pure[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]](curState)
          } yield AtomContext("atom", children.reverse, res)
        }
        else {
          throwPEStateT[F, CalculatorLexer, AtomContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }
  
  private def getLexerWithNextToken(curState: List[GrammarTree[_]]): CalculatorParseState[List[GrammarTree[_]]] = {
    StateT.apply[CalculatorMET, CalculatorLexer, List[GrammarTree[_]]] { lexer =>
      lexer.nextToken().fold(
        e => EitherT.leftT(e),
        params => EitherT.rightT((CalculatorLexer(lexer.inputStream, params), curState))
      )
    }
  }
}

object CalculatorParser {

  case class CalculatorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): CalculatorContext = CalculatorContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): CalculatorContext = CalculatorContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class TermContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): TermContext = TermContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): TermContext = TermContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class MuldivContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): MuldivContext = MuldivContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): MuldivContext = MuldivContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class ExprContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): ExprContext = ExprContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): ExprContext = ExprContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class ExpContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): ExpContext = ExpContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): ExpContext = ExpContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class HighPriorContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): HighPriorContext = HighPriorContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): HighPriorContext = HighPriorContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class AddsubContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): AddsubContext = AddsubContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): AddsubContext = AddsubContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class AtomContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: Double = 0)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): AtomContext = AtomContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): AtomContext = AtomContext(ctxRoot, ctxChildren ++ List(child))
  }
}

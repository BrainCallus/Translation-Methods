
package forc;

import ForcParser._
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

case class ForcParser[F[_]: Monad](inputStream: InputStream) {
  private type ForcMET[A] = MkContainer[MET, F]#Cont[A]
  type ForcParseState[A] = ParseState[F, ForcLexer, A]
  val lex: ForcLexer = ForcLexer(inputStream, ForcLexer(inputStream,
    LexerParams(TokenizedEmpty, 0, 0, -2)).nextToken().getOrElse(LexerParams(TokenizedEmpty, 0, 0, -2)))

  def cmp() : ForcParseState[CmpContext] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.LT))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            l <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.LT)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected LT, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = CmpContext("CMP", List(TerminalTree(l)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield CmpContext("cmp", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(ForcToken.GT))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            g <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.GT)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected GT, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = CmpContext("CMP", List(TerminalTree(g)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield CmpContext("cmp", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(ForcToken.NGT))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            ng <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NGT)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NGT, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = CmpContext("CMP", List(TerminalTree(ng)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield CmpContext("cmp", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(ForcToken.NLT))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            nl <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NLT)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NLT, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = CmpContext("CMP", List(TerminalTree(nl)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield CmpContext("cmp", children.reverse, res)
        }
        else {
          throwPEStateT[F, ForcLexer, CmpContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def p3(name : String) : ForcParseState[P3Context] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.NAME))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            n <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NAME)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NAME, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            _ <- if (n.text != name) {
              throwPEStateT[F, ForcLexer, List[GrammarTree[_]]](s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}")
            } else {
              StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            }
            // END USER CODE
            
            i <- incdec()
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState).map(list => i :: list)
            
            // USER CODE
            res = P3Context("P3", List(TerminalTree(n), i.res))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield P3Context("p3", children.reverse, res)
        }
        else {
          throwPEStateT[F, ForcLexer, P3Context]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def p2(name : String) : ForcParseState[P2Context] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.NAME))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            n <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NAME)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NAME, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            _ <- if (n.text != name) {
              throwPEStateT[F, ForcLexer, List[GrammarTree[_]]](s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}")
            } else {
              StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            }
            // END USER CODE
            
            c <- cmp()
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState).map(list => c :: list)
            num <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NUMBER)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NUMBER, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = P2Context("P2", List(TerminalTree(n), c.res, TerminalTree(num)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield P2Context("p2", children.reverse, res)
        }
        else {
          throwPEStateT[F, ForcLexer, P2Context]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def forc() : ForcParseState[ForcContext] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.FOR))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            f <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.FOR)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected FOR, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            l <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.LPAREN)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected LPAREN, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            i <- inner()
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState).map(list => i :: list)
            r <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.RPAREN)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected RPAREN, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            e <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.EOF)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected EOF, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = ForcContext("S", List(TerminalTree(f), TerminalTree(l), i.res, TerminalTree(r), TerminalTree(e)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield ForcContext("forc", children.reverse, res)
        }
        else {
          throwPEStateT[F, ForcLexer, ForcContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def inner() : ForcParseState[InnerContext] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.TYPE_NAME))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            pp1 <- p1()
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState).map(list => pp1 :: list)
            s1 <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.SEMICOLON)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected SEMICOLON, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            pp2 <- p2(pp1.name)
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState).map(list => pp2 :: list)
            s2 <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.SEMICOLON)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected SEMICOLON, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            pp3 <- p3(pp1.name)
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState).map(list => pp3 :: list)
            
            // USER CODE
            res = InnerContext("INNER", List(pp1.res, TerminalTree(s1),  pp2.res, TerminalTree(s2), pp3.res))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield InnerContext("inner", children.reverse, res)
        }
        else {
          throwPEStateT[F, ForcLexer, InnerContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def p1() : ForcParseState[P1Context] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.TYPE_NAME))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            t <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.TYPE_NAME)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected TYPE_NAME, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            n <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NAME)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NAME, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            e <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.EQ)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected EQ, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            num <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.NUMBER)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected NUMBER, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            name = n.text
            res = P1Context("P1", List(TerminalTree(t), TerminalTree(n),  TerminalTree(e), TerminalTree(num)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield P1Context("p1", children.reverse, name, res)
        }
        else {
          throwPEStateT[F, ForcLexer, P1Context]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }

  def incdec() : ForcParseState[IncdecContext] = {
    for { 
      lex <- StateT.get[ForcMET, ForcLexer]
      res <- 
        if (lex.curTokenIn(Set(ForcToken.INC))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            i <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.INC)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected INC, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = IncdecContext("INC_DEC", List(TerminalTree(i)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield IncdecContext("incdec", children.reverse, res)
        }
        else if (lex.curTokenIn(Set(ForcToken.DEC))) {
          for {
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](List.empty)
            d <- StateT.get[ForcMET, ForcLexer].map(lexer => lexer.curToken())
            lexer <- StateT.get[ForcMET, ForcLexer]
            _ <-
              if (!lexer.compareToken(ForcToken.DEC)) {
                throwPEStateT[F, ForcLexer, List[GrammarTree[_]]]("Expected DEC, found:" + lexer.curToken().text)
              } else {
                StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              }
            curState <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
              .map(list => TerminalTree(lexer.curToken()) :: list)
            curState <- getLexerWithNextToken(curState)
            
            // USER CODE
            res = IncdecContext("INC_DEC", List(TerminalTree(d)))
            // END USER CODE
            
            children <- StateT.pure[ForcMET, ForcLexer, List[GrammarTree[_]]](curState)
          } yield IncdecContext("incdec", children.reverse, res)
        }
        else {
          throwPEStateT[F, ForcLexer, IncdecContext]("Unexpected token: " + lex.curToken())
        }
    } yield res
  }
  
  private def getLexerWithNextToken(curState: List[GrammarTree[_]]): ForcParseState[List[GrammarTree[_]]] = {
    StateT.apply[ForcMET, ForcLexer, List[GrammarTree[_]]] { lexer =>
      lexer.nextToken().fold(
        e => EitherT.leftT(e),
        params => EitherT.rightT((ForcLexer(lexer.inputStream, params), curState))
      )
    }
  }
}

object ForcParser {

  case class CmpContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): CmpContext = CmpContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): CmpContext = CmpContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class P3Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): P3Context = P3Context(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): P3Context = P3Context(ctxRoot, ctxChildren ++ List(child))
  }

  case class P2Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): P2Context = P2Context(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): P2Context = P2Context(ctxRoot, ctxChildren ++ List(child))
  }

  case class ForcContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): ForcContext = ForcContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): ForcContext = ForcContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class InnerContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): InnerContext = InnerContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): InnerContext = InnerContext(ctxRoot, ctxChildren ++ List(child))
  }

  case class P1Context(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, name: String = null, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): P1Context = P1Context(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): P1Context = P1Context(ctxRoot, ctxChildren ++ List(child))
  }

  case class IncdecContext(ctxRoot: String, ctxChildren: List[GrammarTree[_]] = List.empty, res: ContextTree = null)
  extends ContextTree(ctxRoot, ctxChildren) {
    override def pushFirstChild(child: GrammarTree[_]): IncdecContext = IncdecContext(ctxRoot, child::ctxChildren)
    override def appendLastChild(child: GrammarTree[_]): IncdecContext = IncdecContext(ctxRoot, ctxChildren ++ List(child))
  }
}

package template

import grammar.{LexerRule, Token}

import java.io.InputStream
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import template.AbstractLexer.LexerParams
import template.Tokenized.{TokenizedEmpty, TokenizedValue}
import util.Ternary.Ternary

import java.text.ParseException
import java.util.regex.Pattern
import scala.annotation.tailrec
import scala.collection.immutable.Queue

abstract class AbstractLexer[T <: Token](
  inputStream: InputStream,
  var lexerParams: LexerParams = LexerParams(TokenizedEmpty, 0, 0, -2)
) {
  def apply[L <: AbstractLexer[_ <: Token]](inputStream: InputStream): L = this(inputStream)

  val lexerRules: List[LexerRule[T]]
  def getTokenWithName(name: String): T

  def curPos(): Int = lexerParams.curPos

  def curToken(): Tokenized = lexerParams.tokenized

  def curTokenIn(set: Set[T]): Boolean =
    curToken() match {
      case TokenizedEmpty                 => false
      case TokenizedValue(token, _, _, _) => set.contains(token.asInstanceOf[T])
    }

  def compareToken(expected: T): Boolean = {
    curToken() match {
      case TokenizedEmpty => false
      case t              => t.asInstanceOf[TokenizedValue[T]].token == expected
    }
  }

  def nextToken(): LexerParams = {
    val newParams = processCurToken(this.lexerParams, skip=true)
    //val newParams = rudeNextToken(this.lexerParams)
    this.lexerParams = newParams
    lexerParams
  }

  @tailrec
  private def processCurToken(curParams: LexerParams, skip: Boolean, curTokenized:Tokenized = TokenizedEmpty): LexerParams = {
    if (!skip) {
      LexerParams(curTokenized ,curParams.curPos, curParams.realPos, curParams.lastRead, curParams.buffer)
    } else {
      val candidates = lexerRules.toSet
      val tokenized = TokenizedEmpty
      val (newParams, nPos, newTokenized) = processCandidates(candidates, curParams, curParams.curPos, tokenized)
      val nextParams = removeProcessedChars(nPos)(newParams)
      processCurToken(nextParams, newTokenized.asInstanceOf[TokenizedValue[T]].skip, newTokenized)
    }
  }

  @tailrec
  private def processCandidates(candidates: Set[LexerRule[T]], params: LexerParams, nPos:Int, curTokenized:Tokenized):
  (LexerParams, Int, Tokenized) = {
    if(!params.signalizeEnd && candidates.nonEmpty) {
      val nCandidates = Set.empty[LexerRule[T]]
      val res = candidates.foldLeft((nCandidates, params, nPos, curTokenized))((tuple, candidate) => {
        processCandidate(candidate, params, tuple._3)(tuple._1, tuple._4)
      })
      val newParams = nextChar(params)
      processCandidates(res._1, newParams, res._3, res._4)
    } else {
      val newTokenized = params.signalizeEnd ??(TokenizedValue(getTokenWithName("EOF"), "", params.curPos, skip = false), curTokenized)
      if(newTokenized == TokenizedEmpty) {
        throw new ParseException("Recognition error at " + getBufferedString(params.buffer), params.curPos)
      }
      (params, nPos, newTokenized)
    }
  }

  private def processCandidate(candidate: LexerRule[T], params: LexerParams, nPos:Int)(
    curCandidates: Set[LexerRule[T]], curTokenized: Tokenized
  ): (Set[LexerRule[T]], LexerParams, Int, Tokenized) = {
    val curStr = getBufferedString(params.buffer)
    val matcher = Pattern.compile(candidate.token.getPatternAsString).matcher(curStr)
    matcher.matches()
    val newCandidates = curCandidates++((matcher.hitEnd() && params.lastRead != -1)??(Set(candidate), Set.empty))
    if(matcher.lookingAt()) {
      if(curTokenized == TokenizedEmpty || curTokenized.asInstanceOf[TokenizedValue[T]].tokenLen < matcher.end()) {
        val tokenized = TokenizedValue(candidate.token, curStr.substring(0, matcher.end()), params.curPos, candidate.skip)
        (newCandidates, params, params.curPos + matcher.end(), tokenized)
      } else {
        (newCandidates, params, nPos, curTokenized)
      }
    } else {
      (newCandidates, params, nPos, curTokenized)
    }
  }

  private def getBufferedString(queue: Queue[Char]) = {
    queue.foldLeft(new StringBuilder())((builder, ch) => builder.append(ch)).toString()
  }

  @tailrec
  private def removeProcessedChars(newPos: Int)(params: LexerParams): LexerParams = {
    if (params.curPos == newPos) {
      params
    } else {
      removeProcessedChars(newPos)(
        LexerParams(
          params.tokenized,
          params.curPos + 1,
          params.realPos,
          params.lastRead,
          (params.buffer.size > 1) ?? (params.buffer.tail, Queue.empty)
        )
      )
    }
  }

   private def nextChar(params: LexerParams): LexerParams = {
    IO {
      val read = inputStream.read()
      if (read != -1) {
        LexerParams(params.tokenized, params.curPos, params.realPos + 1, read, params.buffer.appended(read.toChar))
      } else {
        LexerParams(params.tokenized, params.curPos, params.realPos, read, params.buffer)
      }
    }.redeem(e => throw new ParseException(e.getMessage, -1), identity).unsafeRunSync()
   }

  // private def rudeNextToken(lexerParams: LexerParams): LexerParams = {
  //   var tokenized: Tokenized = TokenizedEmpty
  //  // val curPos = lexerParams.curPos
  //   var curParams = lexerParams
  //   do {
  //     var candidates = lexerRules.toSet
  //     tokenized = TokenizedEmpty
  //     var newPos = curParams.curPos
  //     while (!curParams.signalizeEnd && candidates.nonEmpty) {
  //       var newCandidates = Set.empty[LexerRule[T]]
  //       for (candidate <- candidates) {
  //         val curStr = getBufferedString(curParams.buffer)
  //         val matcher = Pattern.compile(candidate.token.getPatternAsString).matcher(curStr)
  //         matcher.matches()
  //         if (matcher.hitEnd() && curParams.lastRead != -1) {
  //           newCandidates = newCandidates + candidate
  //         }
  //         if (matcher.lookingAt()) {
  //           val matchedStr = curStr.substring(0, matcher.end())
  //           val token = candidate.token
  //           if (tokenized == TokenizedEmpty) {
  //             tokenized = TokenizedValue(token, matchedStr, curParams.curPos, candidate.skip)
  //             newPos = curParams.curPos + matcher.end()
  //           } else if (tokenized.asInstanceOf[TokenizedValue[T]].tokenLen < matcher.end()) {
  //             tokenized = TokenizedValue(token, matchedStr, curParams.curPos, candidate.skip)
  //             newPos = curParams.curPos + matcher.end()
  //           }
  //         }
  //       }
  //       candidates = newCandidates
  //       curParams = nextChar(curParams)
  //     }
//
  //     if (curParams.signalizeEnd) {
  //       tokenized = TokenizedValue(getTokenWithName("EOF"), "", curParams.curPos, false)
  //     }
  //     if (tokenized == TokenizedEmpty) {
  //       throw new ParseException("Recognition error at " + getBufferedString(curParams.buffer), curParams.curPos)
  //     }
  //     curParams = removeProcessedChars(newPos)(curParams)
//
  //   } while (tokenized.asInstanceOf[TokenizedValue[T]].skip)
  //     LexerParams(tokenized, curParams.curPos, curParams.realPos, curParams.lastRead, curParams.buffer)
  // }

}

object AbstractLexer {

  case class LexerParams(
    tokenized: Tokenized,
    curPos: Int,
    realPos: Int,
    lastRead: Int,
    buffer: Queue[Char] = Queue.empty
  ) {
    def signalizeEnd: Boolean = curPos == realPos && lastRead == -1
  }

}
import grammar_entry.Token
import cats.{Eval, _}
import cats.data.State
import util.Util
import util.Util._

import scala.sys.process._
import java.io.{File, FileWriter}
import scala.util.Using

object Main {
  private val PATH_PREFIX: String = "src\\main\\scala\\tmp\\".replace("\\", File.separator)
  def runParser(): Unit = {
    val parser = Parser()
    println(Token.END.getPattern.matcher(" $").matches())
    val tree = parser.parse("for(  int32    WRssVAFu69cm3DCt   =  -965884343    ;  WRssVAFu69cm3DCt    <=  17556987  ;    WRssVAFu69cm3DCt ++)")
    val graphviz = Util.toDotFileString(tree)
    val fileWriter = new FileWriter(new File(PATH_PREFIX+"graph.dot"))
    fileWriter.write(graphviz)
    fileWriter.close()
    println(treeToStringList(tree).mkString(" "))
    //dot -v -Tpng –o

  }

  def main(args: Array[String]): Unit = {
    runParser()
   // val fileWriter = new FileWriter(new File(PATH_PREFIX+"graph.out"))
   //  try {
//
   //    Process(s"dot -v -Tpng -O ${PATH_PREFIX}graph.dot").run()
   //  } catch {
   //    case e: Exception =>
   //      fileWriter.write(e.getMessage)
   //      //println(e)
   //  }
   // println(pr)
  }
}

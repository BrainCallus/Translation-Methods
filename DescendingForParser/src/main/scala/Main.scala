import grammar_entry.Token
import util.Util._

object Main {
  private def runParser(): Unit = {
    val parser = Parser()
    println(Token.END.getPattern.matcher(" $").matches())
    val tree = parser.parse("for(  int32    var_naMe   =  -965884343    ;  var_naMe    <=  17556987  ;    var_naMe ++)")
    makeGraph(tree)
  }

  def main(args: Array[String]): Unit = {
    runParser()
  }
}

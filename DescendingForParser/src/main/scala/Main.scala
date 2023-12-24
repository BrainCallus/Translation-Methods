import grammar_entry.Token
import util.Util._

object Main {
  private def runParser(input: String = "for(int32  var_naMe = -987654321; var_naMe <= 123456789; var_naMe++)"): Unit = {
    val parser = Parser()
    println(Token.END.getPattern.matcher(" $").matches())
    val tree = parser.parse(input)
    makeGraph(tree)
  }

  def main(args: Array[String]): Unit = {
      println("Input expression:")
      val input =scala.io.StdIn.readLine()
      runParser(input)

  }
}
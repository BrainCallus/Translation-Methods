package util
import functional.Tree
import functional.Tree.{Leaf, NonTerminalTree}
import util.Constants.PATH_PREFIX

import scala.annotation.tailrec
import java.io.{File, FileWriter}
import scala.sys.process.Process

object Util {
  implicit class Ternary[T](val condition: Boolean) {
    def ??(onTrue: => T, onFalse: => T): T = if (condition) onTrue else onFalse
  }

  def makeGraph(parsed: Tree[_], fileName: String = "graph"): Unit = {
    try {
      val fileWriter = new FileWriter(new File(s"$PATH_PREFIX$fileName.dot"))
      fileWriter.write(toDotFileString(parsed))
      fileWriter.close()
      Process(s"dot -v -Tpng -O $PATH_PREFIX$fileName.dot").run()
      println("Graph successfully created.")
    } catch {
      case e: Exception => println(s"${e.getMessage}\n${e.getStackTrace.mkString("StackTrace:\n  ", "\n  ", "")}")
    }

  }

  private def toDotFileString(tree: Tree[_]): String =
    internalWriteTree(new StringBuilder("digraph G {\n"), tree)(0, -1)._2.append("}\n").toString()

  private def internalWriteTree(
    stringBuilder: StringBuilder,
    tree: Tree[_]
  )(idx: Int, pIdx: Int): (Int, StringBuilder) = {
    tree match {
      case Leaf(r, t) =>
        (idx, stringBuilder.append(s"$idx [label = \"[$t, $r]\"]\n").append((pIdx == -1) ?? ("", s"$pIdx -> $idx\n")))
      case NonTerminalTree(r, children) =>
        stringBuilder.append(s"$idx [label = \"$r\"]\n").append((pIdx == -1) ?? ("", s"$pIdx -> $idx\n"))
        children.foldLeft((idx, stringBuilder))((acc: (Int, StringBuilder), child: Tree[_]) => {
          internalWriteTree(acc._2, child)(acc._1 + 1, idx)
        })
    }
  }

  def treeToStringList(tree: Tree[_]): List[String] = internalTreeToStringList(List.empty[String])(tree)

  private def internalTreeToStringList(acc: List[String])(tree: Tree[_]): List[String] =
    tree match {
      case Leaf(r, _) => acc ++ List(r)
      case NonTerminalTree(_, children) =>
        foldl(acc)((accum: List[String], tree: Tree[_]) => {
          accum ++ treeToStringList(tree)
        })(children)

    }

  def foldl[T, E](accum: E)(func: (E, T) => E)(list: List[T]): E = doFoldl(accum)(func)(list)

  @tailrec
  private def doFoldl[T, E](accum: E)(
    func: (E, T) => E
  )(list: List[T]): E =
    list match {
      case Nil          => accum
      case head :: tail => doFoldl(func(accum, head))(func)(tail)
    }

}

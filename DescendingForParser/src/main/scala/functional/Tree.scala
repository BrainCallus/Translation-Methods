package functional

import grammar_entry.Token

sealed abstract class Tree[T](val root: String, val addition: T) {
  def toString: String
}

object Tree {
  final case class NonTerminalTree(r:String, children: List[Tree[_]]) extends Tree[List [Tree[_]]](r, children){
    override def toString: String = {
      val sb:StringBuilder = new StringBuilder(root)
      sb.append(System.lineSeparator())
      addition.foreach(child => sb.append(child.toString))
      sb.toString()
    }

  }

  final case class Leaf private(r: String, token: Token) extends Tree[Token](r,token) {
    override def toString: String = s"[$addition, $root]\n"
  }
  object Leaf{
    def apply(pair: (String, Token)) = new Leaf(pair._1, pair._2)
  }
}

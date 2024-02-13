package util

import cats.Show
import cats.implicits.toShow
import cats.instances.show

import scala.language.implicitConversions

trait  Tree[T] {
  implicit def showEv: Show[T]
  def root: T
  def children: List[Tree[_]]
  def showRoot: String = showEv.show(root)

}

object Tree {

}
/*
class Tree[T<:Tree[T]](node: String, children: List[Tree[_]] = List.empty) {
  def getNode: String = node
  def getChildren: List[Tree[_]]= children
  def addChild(child: Tree[_]):Tree[T] = new Tree[T](node, child::children)
}
*/
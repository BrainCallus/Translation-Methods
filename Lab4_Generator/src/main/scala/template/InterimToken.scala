package template

import grammar.Token

case class InterimToken(name:String, pattern:String) extends Token {
  override def getName: String = name
}

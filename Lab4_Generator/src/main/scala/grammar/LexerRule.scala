package grammar

final case class LexerRule[T <: Token](token: T, skip: Boolean)

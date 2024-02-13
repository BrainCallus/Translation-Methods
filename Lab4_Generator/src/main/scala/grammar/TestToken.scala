package grammar

sealed abstract class TestToken(protected val pattern: String) extends Token {}

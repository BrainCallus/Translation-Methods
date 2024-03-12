package grammar.entry

case class NonTerminal(name: String, value: String, translatingSymbol: TranslatingSymbol) extends GrammarEntry

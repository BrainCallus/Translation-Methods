package grammar

import grammar.entry.{Attribute, GrammarEntry}

final case class ParserRule(
  name: String,
  inheritAttrs: List[Attribute],
  synteticAttrs: List[Attribute],
  rules: Set[List[GrammarEntry]]
)

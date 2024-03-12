package prom;

import grammar.entry.Attribute;
import grammar.entry.GrammarEntry;

import java.util.List;

public record JavaParserRule(String name, List<Attribute> inheritAttrs, List<Attribute> synteticAttrs,
                             List<List<GrammarEntry>> parserRules) {

}

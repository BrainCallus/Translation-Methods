package prom;

import grammar.entry.TranslatingSymbol;

import java.util.List;
import java.util.Optional;

public record JavaGrammar(String name, Optional<TranslatingSymbol> headers, List<JavaLexerRule> lexerRules,
                          List<JavaParserRule> parserRules) {

}

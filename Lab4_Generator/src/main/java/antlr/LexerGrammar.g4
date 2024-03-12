lexer grammar LexerGrammar;


GRAMMAR : 'grammar';

HEADER : '@header';

RETURNS : 'returns';

COMMA : ',';

EQUALS : '=';

COLON : ':';

OR : '|';

REGEX : '\'' (~('\''))* '\'';

SEMICOLON : ';';

/* We're going to ignore all white space characters */
SPACES : [ \t\n\r]+ -> skip;

OP : '[';

CP : ']';

FUNCTIONAL_ARROW : '->';

SKIP_ : 'skip';

LOWER_CASE_INTEDEFICATION : [a-z][a-zA-Z0-9_]*;

UPPER_CASE_INTEDEFICATION : [A-Z][a-zA-Z0-9_]*;

OPENP : '{' -> pushMode(JAVA_CODE);

mode JAVA_CODE;

CODE_OPENP : '{' -> pushMode(JAVA_CODE);

CODE : (~[{}])+;

CLOSEP : '}' -> popMode;
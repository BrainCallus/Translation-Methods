grammar JavaCode;

code : package? imports? programmEntities EOF;
    package : KEY_package typeToken ';';
    imports : imprt+;
        imprt : KEY_import typeToken ('.' '*')? ';';

programmEntities : codeEntry+;

codeEntry : declaration codeBody;
    declaration : declarationBegin declarationRest?;
        declarationBegin: declarationPrefix ('(' funcDeclarationArgs ')')?;
            declarationPrefix : (keyWord | typeToken)+;
        declarationRest : keyWordAndTokens+;
    codeBody : '{' innerCode? '}';
        innerCode : programmEntities | (executableLine | executableStatement | switchStatement)+;

executableLine : (keyWord | declareValue)? ';';
executableStatement : statementDeclaration codeBody (statementDeclaraitonRest codeBody)* statementLineEnd?;
    statementDeclaration : KEY_STATEMENT+ statementDeclarationScope?;
    statementDeclaraitonRest : KEY_STATEMENT_REST KEY_STATEMENT* statementDeclarationScope?;
        statementDeclarationScope : ('(' statementDeclarationInScope ')');
            statementDeclarationInScope : declareValue (';' declareValue)*;
    statementLineEnd : 'while' ';';

switchStatement : KEY_switch switchDeclare switchBody;
    switchDeclare : '(' value ')';
    switchBody : '{' switchCode '}';
        switchCode : case* default? case*;
            case: KEY_case value ':' innerCode;
            default : KEY_default ':' innerCode;

funcDeclarationArgs : (funcDeclarationArg (Comma funcDeclarationArg)*)?;
    funcDeclarationArg : keyWord* argTypeToken varToken;
        argTypeToken : typeToken vararg?;
        vararg: ('...');

declareValue : value (value (Comma value)*)?;

value : oper* unoValue (oper+ unoValue)* oper*;
unoValue : unoOper* unoValueKeyWord unoOper*;
unoValueKeyWord : keyWord* unoValueGet;
unoValueGet : justValue ('.' unoValue)*;
justValue : '(' value ')'
               | NUMBER
               | STRING
               | funcToken
               ;

funcToken : typeToken funcCalledArgs?;
funcCalledArgs : '(' (value (Comma value)*)? ')';

typeToken : varToken  ('['']')*;
varToken : NAME ('.' NAME)*;

keyWordAndTokens: keyWord typeTokensComma;
    typeTokensComma : typeToken (Comma typeToken)*;

keyWord : KEYWORD;
KEY_package: 'package';
KEY_import: 'import';

KEY_switch: 'switch';
KEY_case: 'case';
KEY_default: 'default';

KEY_STATEMENT : 'do'
                  | 'for'
                  | 'if'
                  | 'try'
                  | 'while'
                  ;

KEY_STATEMENT_REST : 'catch'
                   | 'else'
                   | 'finally'
                   ;

KEYWORD : 'abstract'
        | 'assert'
        | 'break'
        | 'class'
        | 'const'
        | 'continue'
        | 'enum'
        | 'extends'
        | 'final'
        | 'goto'
        | 'implements'
        | 'instanceof'
        | 'interface'
        | 'native'
        | 'new'
        | 'private'
        | 'protected'
        | 'public'
        | 'return'
        | 'static'
        | 'strictfp'
        | 'super'
        | 'synchronized'
        | 'throw'
        | 'throws'
        | 'transient'
        | 'volatile'
        ;

STRING : ["] (~["\\] | '\\'. )* ["]
       | ['] (~['\\] | '\\'.) ['];

NUMBER : '-'?DIGIT+;
NAME : (LETTER | '$' | '_') (LETTER | '$' | DIGIT | '_')*;

unoOper: UNO_OPER;
oper : OPER | Langle | Rangle | Question | Colon | Star;

Langle   : '<';
Rangle   : '>';
Question : '?';
Colon    : ':';
Star     : '*';
UNO_OPER : '!' | '++' | '--';
OPER : [&|?:+\-*/%!<=>^~]
         | [&|?:+\-*/%!<=>^~]? [&|:+\-*/%=^~]+ [&|?:+\-*/%!<=>^~]?;

Comma: ',';

fragment LETTER : [\p{L}];
fragment DIGIT : [0-9];
SPACE : [ \t\r\n]+ -> skip;

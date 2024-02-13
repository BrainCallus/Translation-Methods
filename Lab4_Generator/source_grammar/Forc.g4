grammar Forc;

@header {
package forc;
}

forc returns[ContextTree res]:
                            f = FOR l = LPAREN i = inner r = RPAREN e = EOF
                            {$res = ForcContext("S", List(TerminalTree(f), TerminalTree(l), i.res, TerminalTree(r), TerminalTree(e)))};

inner returns[ContextTree res]:
                            pp1 = p1 s1 = SEMICOLON pp2 = p2[{pp1.name}] s2 = SEMICOLON pp3 = p3[{pp1.name}]
                            {$res = InnerContext("INNER", List(pp1.res, TerminalTree(s1),  pp2.res, TerminalTree(s2), pp3.res))};

p1 returns[String name, ContextTree res]:
                            t = TYPE_NAME n = NAME e = EQ num = NUMBER
                            {$name = n.text;
                            $res = P1Context("P1", List(TerminalTree(t), TerminalTree(n),  TerminalTree(e), TerminalTree(num)));};

p2[String name] returns[ContextTree res]:
                            n = NAME {if(n.text != name) {
    throw new ParseException(s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}", lex.curPos())
}}
                            c = cmp
                            num = NUMBER {$res = P2Context("P2", List(TerminalTree(n), c.res, TerminalTree(num)))};

p3[String name] returns[ContextTree res]:
                            n=NAME {if(n.text != name) {
    throw new ParseException(s"Invalid variable name. Variable name was declared as \"" + name+ s"\", but got ${n.text}", lex.curPos())
}}
                            i=incdec {$res = P3Context("P3", List(TerminalTree(n), i.res));};

cmp returns[ContextTree res]:
                            l = LT {$res = CmpContext("CMP", List(TerminalTree(l)))}
                            |
                            g = GT {$res = CmpContext("CMP", List(TerminalTree(g)))}
                            |
                            ng = NGT {$res = CmpContext("CMP", List(TerminalTree(ng)))}
                            |
                            nl = NLT {$res = CmpContext("CMP", List(TerminalTree(nl)))};

incdec returns[ContextTree res]:
                            i = INC {$res = IncdecContext("INC_DEC", List(TerminalTree(i)));}
                            |
                            d = DEC {$res = IncdecContext("INC_DEC", List(TerminalTree(d)))};

LPAREN: '\\(';
RPAREN: '\\)';
SEMICOLON : ';';
FOR: 'for';
INC: '\\+\\+';
DEC: '--';
EQ: '=';
LT:  '<';
GT: '>';
NGT: '<=';
NLT: '>=';
NUMBER: '(-)?(\\d+)';
TYPE_NAME: '(int|long|char|byte)';
NAME: '([a-zA-Z_]+[a-zA-Z_0-9]*)';
SPACES : '[ \n\t]+' -> skip;
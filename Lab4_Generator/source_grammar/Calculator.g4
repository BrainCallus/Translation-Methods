grammar Calculator;

@header {
package calculator;
}

calculator returns[Double res]: e = expr e1 = EOF {$res = e.res;};

expr returns[Double res]: a = atom m = muldiv[{a.res}] as = addsub[{m.res}] {$res = as.res;};

addsub[Double in] returns[Double res]:
            a = ADD t = term as = addsub[{in + t.res}] {$res = as.res;}
                |
            s = SUB t = term as=addsub[{in - t.res}] {$res = as.res;}
                |
            {$res = in;};

term returns[Double res]:
            a = atom m = muldiv[{a.res}] {$res = m.res;};

muldiv[Double in] returns[Double res]:
            m = MUL a = atom mm = muldiv[{in * a.res}] {$res = mm.res;}
                |
            d = DIV a = atom mm = muldiv[{in / a.res}] {$res = mm.res;}
                |
            {$res = in;} ;

atom returns[Double res]: o = LPAREN e = expr c = RPAREN {$res = e.res;} | d = NUMBER {$res = (d.text).toDouble;};

ADD : '\\+';

SUB : '-';

MUL : '\\*';

DIV : '/';

LPAREN : '\\(';

RPAREN : '\\)';

NUMBER : '(-)?(0|[1-9][0-9]*)(\\.[0-9]+)?';

SPACES : '[ \n\t]+' -> skip;
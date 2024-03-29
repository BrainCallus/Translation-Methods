grammar Calculator;

@header {
package calculator;
}

calculator returns[Double res]: e = expr e1 = EOF {$res = e.res;};

expr returns[Double res]: h = highPrior m = muldiv[{h.res}] as = addsub[{m.res}] {$res = as.res};

addsub[Double in] returns[Double res]:
            a = ADD t = term as = addsub[{in + t.res}] {$res = as.res}
                |
            s = SUB t = term as=addsub[{in - t.res}] {$res = as.res}
                |
            {$res = in;};

term returns[Double res]:
            h = highPrior m = muldiv[{h.res}] {$res = m.res;};

muldiv[Double in] returns[Double res]:
            m = MUL h = highPrior mm = muldiv[{in * h.res}] {$res = mm.res}
                |
            d = DIV h = highPrior mm = muldiv[{in / h.res}] {$res = mm.res}
                |
            {$res = in;};

highPrior returns[Double res]:
            a = atom e = log[{a.res}] {$res = e.res};

log[Double in] returns[Double res]:
            l = LOG a = atom e = log[{a.res}] {$res = math.log10(in) / math.log10(e.res)}
                |
            {$res = in;};

atom returns[Double res]:
            s = SUB l = LPAREN e = expr r = RPAREN {$res = -e.res}
                |
            l = LPAREN e = expr r = RPAREN {$res = e.res}
                |
            d = NUMBER {$res = (d.text).toDouble};

ADD : '\\+';

SUB : '-';

MUL : '\\*';

DIV : '/';

LOG: '//';

LPAREN : '\\(';

RPAREN : '\\)';

NUMBER : '(-)?(0|[1-9][0-9]*)(\\.[0-9]+)?';

SPACES : '[ \n\t]+' -> skip;
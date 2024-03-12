use strict;
use warnings FATAL => 'all';

while (<>) {
    print if /\([^\(\)]*\w+[^\(\)]*\)/;
}
/^((\S)*|\S(.)*\S)$/
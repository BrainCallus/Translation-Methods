use strict;
use warnings FATAL => 'all';

while (<>) {
    print if /^((.)*(cat)(.)*){2}$/;
}
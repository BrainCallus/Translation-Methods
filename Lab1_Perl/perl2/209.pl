#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

while (<>) {
    s/\(([^\)]*)((\)?))/\($2/g;
    print;
}

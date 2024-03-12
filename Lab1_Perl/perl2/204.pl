#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

while (<>) {
    s/((\W*)(\w+))((\W*)(\w+))/$2$6$5$3/;
    print;
}

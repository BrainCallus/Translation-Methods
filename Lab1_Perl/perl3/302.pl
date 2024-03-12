#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';

my $lines = "";
my $has_empty = 0;
my $empty_pattern = '^\s*$';
while (my $line = <>) {
    #if ($line =~ /!exit!/) {
    #    last;
    #}
    if ($line =~ /$empty_pattern/) {
        if ($has_empty == 0) {
            $has_empty = 1;
            $lines = $lines . "\n"
        }
    }
    else {
        $has_empty = 0;
        $lines = $lines . $line;
    }
}
$lines =~ s/<.*?>//g;
$lines =~ s/^\s+//;
$lines =~ s/\s+$//;
$lines =~ s/ *(\n) */$1/g;
$lines =~ s/ +/ /g;
$lines =~ s/(\s*\n){2,}/\n\n/g;
print($lines);
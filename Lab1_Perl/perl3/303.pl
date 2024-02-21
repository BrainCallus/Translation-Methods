#!/usr/bin/perl

my $str = "";
while (my $line = <>) {
    #if($line =~/!exit!/) {
    #    last;
    #}
    $str = $str . $line;
}

my @urls = ();
push(@urls, $+{url}) while $str =~ m{<a.*?href *= *["'](?<url>.*?)["'].*?>}gx;

my %domains;

my $maybeUserInf = "(.*?(:.*?)?@)?";
my $delimiter = "[/#?;:]";

for my $url (@urls) {
    $url =~ m{(//$maybeUserInf(?<dom1>.*?)($delimiter|$))|($maybeUserInf(?<dom2>[^/]*?):(\d+)($delimiter|$))}gx;
    $domains{$+{dom1}} = 1;
    $domains{$+{dom2}} = 1;
}

for my $dom (sort keys %domains) {
    if (length $dom > 0) {
        print "$dom\n";
    }
}

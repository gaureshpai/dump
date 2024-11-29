set val(stop) 10.0;
set ns [new Simulator]
set tracefile [open prg2.tr w]
$ns trace-all $tracefile
set namfile [open prg2.nam w]
$ns namtrace-all $namfile
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
$ns color 1 red
$ns color 2 blue
$ns duplex-link $n0 $n1 100.0Mb 10ms DropTail
$ns queue-limit $n0 $n1 4
$ns duplex-link $n1 $n2 50.0Mb 10ms DropTail
$ns queue-limit $n1 $n2 4
$ns duplex-link $n2 $n3 1.0Mb 10ms DropTail
$ns queue-limit $n2 $n3 5
$ns duplex-link $n3 $n4 1.0Mb 10ms DropTail
$ns duplex-link $n4 $n5 10.0Mb 10ms DropTail
$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient right
$ns duplex-link-op $n2 $n3 orient right-down
$ns duplex-link-op $n3 $n4 orient left
$ns duplex-link-op $n4 $n5 orient left
Agent/Ping instproc recv {from rtt} {
$self instvar node_
puts "node [$node_ id] recived ping answer from \

#$from with round-trip-time $rtt ms"
}
set p0 [new Agent/Ping]
$ns attach-agent $n0 $p0
$p0 set packetSize_ 50000
$p0 set fid_ 1
set p5 [new Agent/Ping]
$ns attach-agent $n5 $p5
$p5 set packetSize_ 50000
$p5 set fid_ 2
$ns connect $p0 $p5
$ns at 0.1 "$p0 send"
$ns at 0.2 "$p0 send"
$ns at 0.3 "$p0 send"
$ns at 0.4 "$p0 send"
$ns at 0.5 "$p0 send"
$ns at 0.6 "$p0 send"
$ns at 0.7 "$p0 send"
$ns at 0.9 "$p0 send"
$ns at 1.0 "$p0 send"

$ns at 0.1 "$p5 send"
$ns at 0.2 "$p5 send"
$ns at 0.3 "$p5 send"
$ns at 0.4 "$p5 send"
$ns at 0.5 "$p5 send"
$ns at 0.6 "$p5 send"
$ns at 0.7 "$p5 send"
$ns at 0.9 "$p5 send"
$ns at 1.0 "$p5 send"
proc finish {} {
global ns tracefile namfile
$ns flush-trace
close $tracefile
close $namfile
exec nam prg2.nam &
puts "no. of packets dropped:"
exec grep -c "^d" prg2.tr &
exit 0
}
$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run

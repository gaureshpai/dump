set val(stop) 10.0;

set ns [new Simulator]

set tracefile [open prg3.tr w]
$ns trace-all $tracefile

set namfile [open prg3.nam w]
$ns namtrace-all $namfile

set winfile0 [open WinFileReno w]
set winfile1 [open WinFileNewReno w]

$ns color 1 Red
$ns color 2 Blue

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns duplex-link $n0 $n1 100.0Mb 10ms DropTail
$ns queue-limit $n0 $n1 50
$ns duplex-link-op $n0 $n1 color "blue"
$ns duplex-link $n2 $n1 100.0Mb 10ms DropTail
$ns queue-limit $n2 $n1 20
$ns duplex-link-op $n2 $n1 color "green"
$ns duplex-link $n1 $n3 100.0Mb 10ms DropTail
$ns queue-limit $n1 $n3 50

$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n2 $n1 orient right-up
$ns duplex-link-op $n1 $n3 orient right

set lan [$ns newLan "$n3 $n4 $n5" 1Mb 40ms LLQueue/DropTail Mac/802_3 channel]

proc PlotWindow {tcpSource file} {
    global ns
    set time 0.1
    set now [$ns now]
    set cwnd [$tcpSource set cwnd_]
    puts $file "$now+$time"
    $ns at [expr $now+$time] "PlotWindow $tcpSource $file"
}

set tcp0 [new Agent/TCP/Reno]
$ns attach-agent $n0 $tcp0
$tcp0 set window_ 8000
$tcp0 set fid_ 1

set sink1 [new Agent/TCPSink]
$ns attach-agent $n4 $sink1
$ns connect $tcp0 $sink1
$tcp0 set packetSize_ 1500

set tcp3 [new Agent/TCP/Reno]
$ns attach-agent $n2 $tcp3
$tcp3 set window_ 8000
$tcp3 set fid_ 2

set sink4 [new Agent/TCPSink]
$ns attach-agent $n5 $sink4
$ns connect $tcp3 $sink4
$tcp3 set packetSize_ 1500

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ns at 0.1 "$ftp0 start"
$ns at 0.1 "PlotWindow $tcp0 $winfile0"
$ns at 100.0 "$ftp0 stop"

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp3
$ns at 0.1 "$ftp1 start"
$ns at 0.1 "PlotWindow $tcp3 $winfile1"
$ns at 100.0 "$ftp1 stop"

proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam prg3.nam &
    puts "Number of Packets dropped:"
    exec grep -c "^d" prg3.tr &
    exit 0
}

$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run
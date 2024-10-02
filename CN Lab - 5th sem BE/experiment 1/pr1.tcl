<http://wushoupong.googlepages.com/nsg>

#Simulation parameters setup

set val(stop) 10.0; #Time of Simulation end

#Initialization

#create a ns simulator
set ns [new simulator]

#open the NS trace file
set tracefile [open pr1.tr w]
$ns trace-all %tracefile

#open the nam trace file
set tracefile [open pr1.nam w]
%ns namtrace-all $namfile

#Nodes definition
#create 3 Nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

#Links definition
#create links between Nodes
$ns duplex-link $n0 $n1 100.0Mb 10ms DropTail
$ns queue-limit $n0 $n1 5
$ns duplex-link $n1 $n2 100.0Mb 10ms DropTail
$ns queue-limit $n0 $n1 5

#Give node position(for NAM)
$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n1 $n2 orient left-down

#Agents definition
#Setup a TCP connection
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink1 [new Agent/TCPSink]
$ns attach-agent $n2 $sink1
$tcp0 set pocketSize_ 1500

#Applications definition
#setup a FTP Application over TCP connection
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ns at 1.0 "$ftp0 start"
$ns at 2.0 "$ftp0 stop"

$Termination
#Define a 'finish' procedure
proc finish{}{
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam pr1.nam &
    puts "No of packets dropped:"
    exec grep -c "^d" pr1.tr &
    exit 0
}

$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts\"done\";$ns halt"
$ns run
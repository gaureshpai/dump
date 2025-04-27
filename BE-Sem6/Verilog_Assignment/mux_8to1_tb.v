`timescale 1ns/1ps

module mux_8to1_tb;
    // Testbench signals
    reg [7:0] data_in;
    reg [2:0] select;
    wire out;
    
    // Instantiate the MUX
    mux_8to1 uut (
        .data_in(data_in),
        .select(select),
        .out(out)
    );
    
    // Monitor changes in signals
    initial begin
        $monitor("Time=%0t | data_in=%b | select=%b | out=%b", 
                 $time, data_in, select, out);
    end
    
    // Test stimulus
    initial begin
        // Initialize inputs
        data_in = 8'b10101010;
        select = 3'b000;
        
        // Test all select combinations
        #10 select = 3'b000; // Should select data_in[0] (0)
        #10 select = 3'b001; // Should select data_in[1] (1)
        #10 select = 3'b010; // Should select data_in[2] (0)
        #10 select = 3'b011; // Should select data_in[3] (1)
        #10 select = 3'b100; // Should select data_in[4] (0)
        #10 select = 3'b101; // Should select data_in[5] (1)
        #10 select = 3'b110; // Should select data_in[6] (0)
        #10 select = 3'b111; // Should select data_in[7] (1)
        
        // Change data_in and test again
        #10 data_in = 8'b11001100;
        #10 select = 3'b000; // Should select data_in[0] (0)
        #10 select = 3'b001; // Should select data_in[1] (0)
        #10 select = 3'b010; // Should select data_in[2] (1)
        #10 select = 3'b011; // Should select data_in[3] (1)
        
        // Finish simulation
        #10 $finish;
    end
    
    // Optional: Generate VCD file for waveform viewing
    initial begin
        $dumpfile("mux_8to1_tb.vcd");
        $dumpvars(0, mux_8to1_tb);
    end
    
endmodule
module mux_8to1 (
    input [7:0] data_in,    // 8 input data lines
    input [2:0] select,     // 3 select lines
    output reg out          // Output
);

    // Using case statement to implement 8:1 MUX
    always @(*) begin
        case(select)
            3'b000: out = data_in[0];
            3'b001: out = data_in[1];
            3'b010: out = data_in[2];
            3'b011: out = data_in[3];
            3'b100: out = data_in[4];
            3'b101: out = data_in[5];
            3'b110: out = data_in[6];
            3'b111: out = data_in[7];
            default: out = 1'bx; // For simulation purposes
        endcase
    end

endmodule
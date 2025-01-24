    AREA MyData, DATA, READWRITE
memoryLoc1 DCD 0X12345678 ; first memory location ( example value )
memoryLoc2 DCD 0X9ABCDEF0 ; second memory location ( example value )
onescount DCD 0 ; to store the count of ones
zeroscount DCD 0 ; to store the count of zeros

    AREA MyCode, CODE, READONLY
    ENTRY
    EXPORT _main 
_main
    LDR R0 ,= memoryLoc1 ; load the address of the first memory location
    LDR R1, [R0] ; Load the value at the first memory location into R1
    LDR R0 ,= memoryLoc2 ; Load the address of the second number location
    LDR R2, [R0]; Load the value at the second memory location into R2
    MOV R3, R1 ; Copy first value of R3 for bit counting
    MOV R4, R2 ; Copy second value of R4 for bit counting
    MOV R5, #0 ; Initialize the ones counter to 0
    MOV R6, #0 ; Initialize the zeros counter to 0

count_bits
    TST R3, #1 ; test the least significant bit of R3
    ADDNE R5, R5, #1 ; if it is 1, increment the ones counter
    ADDEQ R6, R6, #1 ; if it is 0, increment the zeros counter
    LSR R3, R3, #1 ; logical shift right R3 by 1 bit
    CMP R3, #0 ; check if R3 is zero
    BNE count_bits ; If not repeat the bit counting for R3

    MOV R3, R4 ; Reload the second value into R3 for bit counting

 count_bits2
    TST R3, #1 ; test the least significant bit of R3
    ADDNE R5, R5, #1 ; if it is 1, increment the ones counter
    ADDEQ R6, R6, #1 ; if it is 0, increment the zeros counter
    LSR R3, R3, #1 ; logical shift right R3 by 1 bit
    CMP R3, #0 ; check if R3 is zero
    BNE count_bits ; If not repeat the bit counting for R3   

    LDR R0 ,= onescount ; Load the address to store the ones count
    STR R5, [R0] ; store the ones count
    LDR R7 ,=  zeroscount ; Load the address to store the zeros count
    STR R6, [R7] ; store the zeros count
end
    B end
    END
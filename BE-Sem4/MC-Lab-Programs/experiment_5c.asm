    AREA Mydata, DATA, READWRITE
numbers DCD 5, 2, 8, 4, 12, 9, 15, 3, 6, 10; Array of 10 numbers
smallest DCD 0X7FFFFFFF; initialize with the maximum possible 32 bit value

    AREA MyCode, CODE, READONLY
    ENTRY
    EXPORT _main
_main
    LDR R0,=numbers; Load the address of the numbers array
    LDR R1,=smallest; Load the address of the smallest variable
    LDR R2, [R1]; Load the initial value of the smallest(0X7FFFFFFF)
    MOV R3, #10; initialize counter to 10

find_smallest
    LDR R4, [R0], #4; Load the next number into R4 and increment R0 by 4[point to the next number]
    CMP R2, R4; Compare R2 with R4
    MOVGT R2, R4; If R4 is greater, move to R2
    SUBS R3, R3, #1; Decrement the counter
    BNE find_smallest; If R3 is >0, continue the loop
    STR R2, [R1]; Store the smallest number in the address pointed to by R1
    end
    B end
    END
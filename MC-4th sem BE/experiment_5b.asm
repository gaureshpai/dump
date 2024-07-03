    AREA Mydata, DATA, READWRITE
numbers DCD 5, 2, 8, 4, 12, 9, 15, 3, 6, 10; Array of 10 numbers
largest DCD 0;

    AREA MyCode, CODE, READONLY
    ENTRY
    EXPORT _main
_main
    LDR R0,=numbers; Load the address of the numbers array
    LDR R1, [R0]; Load the first number into R1[Assume largest initially]
    MOV R2, #10; initialize counter to 10

find_largest
    LDR R3, [R0], #4; Load the next number into R3 and increment R0 by 4[point to the next number]
    CMP R1, R3; Compare R1 with R3
    MOVLT R1, R3; If R1 is less than R3, move R3 to R1
    SUBS R2, R2, #1; Decrement the counter
    BNE find_largest; If R2 is not zero, continue the loop

    LDR R0, = largest ; Load the address to store the largest number
    STR R1, [R0]; Store the largest number
    end
    B end; Infinite loop to end the program
    END
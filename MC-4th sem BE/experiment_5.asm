    AREA Mydata, DATA, READONLY
numbers  DCD 5, 2, 8, 4, 12, 9, 15, 1, 6, 10;

    AREA MyCode, CODE, READONLY
    ENTRY
    MOV R3, #10     ; initialize counter to 10
    LDR R0 ,= numbers   ; Load the address of numbers away
    LDR R2, [R0]    ;  Load the initiale value of the smallest [07FFFFFFF]
up
    LDR R4, [R0], #4    ; Load the next number into R4 & increment R0 by 4
    CMP R2, R4      ; Compare R2 with R4
    MOVGT R2, R4    ; If R2 is greater than R4, move R4 to R2
    SUB R3, R3, #1  ; Decrement the counter
    BNE up      ; If R3 is not zero, continue the loop
    B .     ; Infinite loop to end the program, set break point here
    END

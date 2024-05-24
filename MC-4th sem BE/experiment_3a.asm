    AREA MULTIPLY, CODE, READONLY
    LDR R0, = OX706F; Load 16 bit number to R0
    LDR R1, = 0X7161; Load 16 bit number to R1
    MUL R2, R1, R0; Multiply R1 with R0 and store in register R2
    B .
    END
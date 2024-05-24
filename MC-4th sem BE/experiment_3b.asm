    AREA MULTIPLY, CODE, READONLY
    LDR R0, = OX706F7160; Load 32 bit number to R0
    LDR R1, = 0X71617060; Load 32 bit number to R1
    EMULL R3, R2, R1, R0; Multiply R1 with R0 and store in register R3, R2
    B .
    END
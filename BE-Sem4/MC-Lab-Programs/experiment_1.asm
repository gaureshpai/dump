    AREA Simple_ALP, CODE, READONLY
    
    MOV R0, #10; load first number (10) into R0
    MOV R1, #20; load second number (20) into R1
    ADD R2, R0, R1; Add R0 and R1, store result in R2
    ; Observing Registers
    ; Register R0 contains first number (10)
    ; Register R1 contains second number (20)
    ; Register R2 contains the result of addition (30)
    ; Dumping Memory

    DCD 10; Dumping first number (10) into memory

    DCD 20; Dumping second number (20) into memory

    DCD 0; Placeholder for result

    ; Observing CPSR

    MRS R3, CPSR; Move CPSR (Current Program Status Register) to R3

    END
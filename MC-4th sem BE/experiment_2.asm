AREA myProgram, CODE, READONLY

ENTRY

;Data Transfer

MOV r0, #10; Move immediate value 10 into Register
MOV r1, r0; Move immediate value from r0 to r1
MOV r2, #20; Move immediate value 20 into Register
MOV r3, r2; Move immediate value from r2 to r3
MOV r4, r1; Move immediate value from r1 to r4

;Arithmetic Operations
ADD r5, r0, r2; Add values in r0 and r2, STore result in r5
SUB r6, r0, r2; Subtract values in r2 from r0, Store result in r6
MUL r7, r1, r3; Multiply values in r1 and r3, STore result in r7
RSBS r8, r0, #0; Reverse subtract from 0: r8 = 0 - r0
RSB r9, r2, r0; Reverse subtract: r9 = r0 - r2

;Logical Operations
AND r10, r0, r2; Perform logical AND between values in r0 & r2, Store results in r10
ORR r11, r1, r3; Perform logical OR between values in r1 & r3, Store results in r11
EOR r12, r0, r3; Perform Exclusive OR between values in r0 & r3, Store results in r12
BIC r13, r1, r2; Perform bit clear between values in r1 & r2, Store results in r13
MVN r14, r0; Perform bitwise NOT operation in r0, Store results in r14

;Halt CPU

B .

END
AREA SUM, CODE, READONLY
MOV R1, #10; Load 10 to register R1
MOV R2, #0; Empty R2 register to store result
loop ADD R2, R1, R2; Add the contenet of R1 with the result at R2
SUBS R1, #0X01; Decrement R1 by 1
BNME loop; Repeat till R1 goes to zero
B .
END
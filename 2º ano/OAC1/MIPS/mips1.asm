.data
vetor: .word 4,0,1,7,1

.text
lw $s0, 0($s2)
lw $s1, 4($s2)
la $s2, vetor

LOOP:
slt $t0, $s1, $s0
beq $t0, $zero, ELSE
add $t1,$s0,0
sw $s1,
ELSE:
j LOOP


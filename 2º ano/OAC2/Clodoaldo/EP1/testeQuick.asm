.data
List: .word 67,49,73,2

newline: .asciiz "\n"

.text

main:
	la 	$s1, List
	addi 	$a1, $zero, 0
	addi	$a2, $zero, 3
	
	jal quickSort

quickSort:	
	subi 	$sp, $sp, 12
	sw 	$ra, 8($sp)	
	sw 	$a1, 4($sp)				# Inicio			
	sw 	$a2, 0($sp)				# Fim			
	
	slt 	$t1, $a1, $a2				# se inicio < fim = 1
	beq 	$t1, $zero, fimQuick			# se inicio >= fim, fimQuick

	jal 	particionar
	add 	$t0, $v0, $zero				# pivot_index = particionar
	
	add	$t2, $a2, $zero				# Salva fim antes da recursão
	subi	$a2, $t0, 1				# novo fim = pivot - 1
	jal 	quickSort
	
	add	$a2, $t2, $zero				# Restaura fim
	addi	$a1, $t0, 1				# novo inicio = pivot + 1
	jal	quickSort

particionar:
	subi 	$sp, $sp, 28
	sw 	$ra, 24($sp)				# particionar chama troca, portanto salvar ra	
	sw	$a1, 20($sp)				# arg é reutilizados em troca
	sw	$t0, 16($sp)
	sw	$t1, 12($sp)
	sw	$t2, 8($sp)
	sw	$t3, 4($sp)
	sw	$t4, 0($sp)			
	
	sll	$t0, $a2, 2			
	add	$t0, $s1, $t0				# numeros[fim]
	lw	$t0, 0($t0)				# pivot = numeros[fim]
	
	subi	$t1, $a1, 1				# i = inicio - 1
	add	$t2, $a1, $zero				# j = inicio
	subi	$t3, $a2, 1				# high - 1
	
	for:	
		slt 	$t5, $t2, $t3			# j > high - 1?
		bne 	$t5, $zero, fimFor		# se sim = 1 -> fimFor
		
		sll	$t4, $t2, 2			
		add	$t4, $s1, $t4			# numeros[j]
		lw	$t4, 0($t4)			# valor de numeros[j]
		
		li	$v0, 1
		la	$a0, ($t4)
		syscall
		
		slt	$t4, $t4, $t0			# numeros[j] < pivot?	
		beq	$t4, $zero, maior		# Se não = 0, numeros[j] > pivot -> maior
		
		addi 	$t1, $t1, 1			# i++
		
		add	$a1, $t1, $zero			# arg1 = i --> para função troca
		add	$a3, $t2, $zero			# arg3 = j --> numeros[j] = pivot
		jal 	trocaQuick
		
		maior:
		add	$t2, $t2, 1			# j++
		
		j	for
	
fimFor:
	add 	$a1, $t1, 1				# arg2 = i + 1
	add	$a3, $a2, $zero				# arg3 = fim
	jal 	trocaQuick
	
	addi 	$v0, $t1, 1				# retorna i + 1
	
	lw 	$t4, 0($sp)
	lw 	$t3, 4($sp)
	lw 	$t2, 8($sp)
	lw 	$t1, 12($sp)
	lw 	$t0, 16($sp)
	lw 	$a1, 20($sp)
	lw 	$ra, 24($sp)
	addi 	$sp, $sp, 28
	
	jr $ra
	
trocaQuick:
	subi 	$sp, $sp, 16
	sw	$t0, 12($sp)			
	sw	$t1, 8($sp)
	sw	$t2, 4($sp)
	sw	$t3, 0($sp)		

	sll	$t1, $a1, 2			# arg1 * 4
	add	$t1, $s1, $t1			# numeros[arg1]
	
	sll	$t0, $a3, 2			# arg3 * 4
	add	$t0, $s1, $t0			# numeros[arg3]
	
	lw	$t2, 0($t1)			# valor de numeros[arg1]
	lw	$t3, 0($t0)			# valor de numeros[arg3]
	sw	$t2, 0($t0)			
	sw	$t3, 0($t1)
				
	lw $t3, 0($sp)
	lw $t2, 4($sp)
	lw $t1, 8($sp)
	lw $t0, 12($sp)
	addi $sp, $sp, 16
	
	jr $ra
	
fimQuick:
	la	$s0, ($s1)			# salva array ordenado em $s0

	lw 	$a2, 0($sp)
	lw 	$a1, 4($sp)
	lw 	$ra, 8($sp)
	addi 	$sp, $sp, 12
	
	jr $ra
	

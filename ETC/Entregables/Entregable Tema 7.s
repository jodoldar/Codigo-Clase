.data 0x00001000
DB:		.word 0xFFFF0020
# La palabra a mostrar se muestra ya como un byte para
# adaptarse al visualizador de segmentos
H:		.byte 0x76
O:		.byte 0x3F
L:		.byte 0x38
A:		.byte 0x77
.globl main
.text 0x00400000

main:	li $a1, 8
		la $t5, 0xFFFF0020
inicio:	sw $zero, 8($t5)
# Introducir la palabra en el visualizador
		li $t3, 8
		la $t4, H
		lw $t5, DB
bucle:	lb $t2, 0($t4)
		sb $t2, 0($t5)
		addi $t4, 1
		addi $t5, 1
		addi $t3, -1
		bgtz $t3, bucle
# Mostrar la palabra en el visualizador
		li $t1, 1
		la $t5, 0xFFFF0020
		sw $t1, 8($t5)
# Desplazamiento en el visualizador
		lw $t0, DB
		addi $t0, 1
		and $t0, $t0, 0xFFFFFFF7
		sw $t0, DB
# Comenzar el proceso de nuevo
		addi $a1, -1
		bgtz $a1, inicio
.end
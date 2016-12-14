          .globl __start
          .data 0x10000000
demana:   .asciiz "Escriba alguna cosa: "
responde: .asciiz "Ha escrito: "
longitud: .asciiz "La longitud es: "
cadena:   .space 80             

          .text 0x00400000
__start:  la $a0, demana        
          la $a1, cadena        
          li $a2, 80            
          jal InputS 
		  la $a0, responde
		  la $a1, cadena
		  jal PromptS
		  la $a0, cadena
		  jal StrLength
		  la $a0, longitud
		  li $v0, 4
		  syscall
		  move $a0, $a1
		  li $v0, 1
		  syscall
          li $v0,10
          syscall

InputS:   li $v0, 4
          syscall
          li $v0, 8
          move $a0, $a1
          move $a1, $a2
          syscall
          jr $ra
		  
PromptS:  li $v0, 4
		  syscall
		  li $v0, 4
		  move $a0, $a1
		  syscall
		  jr $ra
		  
StrLength:	li $t0, -2  
Bucle:		lb $t1,0($a0)
			addi $t0,$t0, 1 
			addi $a0,$a0, 1 
			bne $t1,$zero,Bucle
			move $a1, $t0
			jr $ra

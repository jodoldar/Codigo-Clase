          .globl __start
          .data 0x10000000
M:        .space 4
Q:		    .space 4
R:		    .space 4
          
          .text 0x00400000
__start:  li $a0,'M'
          la $a1, M
          jal InputV
		      li $a0, 'Q'
		      la $a1, Q
		      jal InputV
		      la $a0, M 
		      la $a1, Q
		      la $a2, R
		      jal MultV
		      la $a1, R
		      li $a0, 'R'
		      jal PromptV
          li $v0,10
          syscall

InputV:   li $v0, 11
          syscall
          li $v0, 11
          li $a0, '='
          syscall
          li $v0, 5
          syscall
          sw $v0, 0($a1)
          jr $ra
		  
MultV:	  lw $t0, 0($a0)
		      lw $t1, 0($a1)
		      bltz $t1, Signo
Cont:	    li $t2, 0
Bucle:	  add $t2, $t2, $t0
          addi $t1,-1
		      bgtz $t1, Bucle
		      sw $t2, 0($a2)
		      jr $ra
Signo:    sub $t0,$zero,$t0
          sub $t1,$zero,$t1
		      j Cont
		  
PromptV:  li $v0, 11
		      syscall
		      li $v0, 11
          li $a0, '='
          syscall
		      lw $a0, 0($a1)
		      li $v0,1 
		      syscall
		      jr $ra

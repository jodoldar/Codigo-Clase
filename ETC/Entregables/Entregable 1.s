         .data 0x20809F00
         .word 0x00000001
         .word 0x00000002
         .word 0x00000003
         .word 0x00000004
         .word 0x00000005
         .word 0x00000006
         .word 0x00000007
         .word 0x00000008
         .space 16
         .text 0x00040000
__start: lui $t1, 0x2080
         ori $t1, $t1, 0x9F00
         addi $t0, $zero, 4
bucle:   lw $t2, 0($t1)
         lw $t3, 16($t1)
         addi $t0, $t0, -1
         add $t2, $t2, $t3
         sw $t2, 32($t1)
         addi $t1, $t1, 4
         bne $t0, $zero, bucle




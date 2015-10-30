        .globl __start
        .text 0x00400000
__start:li $a0, 'M'
        jal Input
        move $a1,$v0
        li $a0, 'Q'
        jal Input
        move $a2,$v0
        jal Mult
        move $a1,$v0
        li $a0,'R'
        jal Prompt
        li $v0,10
        syscall
Mult:   li $v0,0
        beqz $a2, MultRet
MultFor:add $v0,$v0,$a1
        addi $a2, $a2, -1
        bne $a2, $zero, MultFor
MultRet:jr $ra
Input:  li $v0, 11
        syscall
        li $a0, '='
        syscall
        li $v0, 5
        syscall
        jr $ra
Prompt: li $v0, 11
        syscall
        li $a0, '='
        syscall
        move $a0, $a1
        li $v0, 1
        syscall
        li $v0, 11
        li $a0, 10
        syscall
        jr $ra
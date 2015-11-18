                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
reloj:          .word 0                # HH:MM:SS (3 bytes de menor peso)

cad_asteriscos: .asciiz "\n  **************************************"
cad_horas:      .asciiz "\n   Horas: "
cad_minutos:    .asciiz " Minutos: "
cad_segundos:   .asciiz " Segundos: "
cad_reloj_en_s: .asciiz "\n   Reloj en segundos: "

                ##########################################################
                # Segmento de codigo
                ##########################################################

                .globl __start
                .text 0x00400000

__start:        la $a0, reloj
                li $a1, 0x0012202D
                jal inicializa_reloj
                la $a0, reloj
                jal devuelve_reloj_en_s
                move $a0, $v0
                jal imprime_s
             
salir:          li $v0, 10              # Codigo de exit (10)
                syscall                 # Ultima instruccion ejecutada
                .end


                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la direccion de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Direccion de la cadena
                li $v0, 4               # Codigo de print_string
                syscall

                la $a0, cad_horas       # Direccion de la cadena
                li $v0, 4               # Codigo de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # Codigo de print_int
                syscall

                la $a0, cad_minutos     # Direccion de la cadena
                li $v0, 4               # Codigo de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # Codigo de print_int
                syscall

                la $a0, cad_segundos    # Direccion de la cadena
                li $v0, 4               # Codigo de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # Codigo de print_int
                syscall

                la $a0, cad_asteriscos  # Direccion de la cadena
                li $v0, 4               # Codigo de print_string
                syscall
                jr $ra

inicializa_reloj: 
                li $t0, 0x1F3F3F
                and $t0, $a1, $t0
                sw $t0, 0($a0)
                jr $ra

#inicializa_reloj_alt:
#                ori $t0, $t0, $a1
#                ori $t0, $t0, $a2
#                ori $t0, $t0, $a3
#
#                sw $t0, 0($a0)
#                jr $ra

inicializa_reloj_hh:
                sb $a1, 2($a0)
                jr $ra

inicializa_reloj_mm:
                sb $a1, 1($a0)
                jr $ra

inicializa_reloj_ss:
                sb $a1, 0($a0)
                jr $ra

devuelve_reloj_en_s:
                lbu $t0, 2($a0)
                li $t1, 3600
                multu $t0, $t1
                mflo $v0
                mfhi $t2
                bgtz $t2, salir

                lbu $t0, 1($a0)
                li $t1, 60
                multu $t0, $t1
                mflo $t0
                mfhi $t2
                bgtz $t2, salir
                addu $v0, $v0, $t0

                lbu $t0, 0($a0)
                addu $v0,$v0,$t0

                jr $ra

                #lbu $t0, 0($a0)
                #addu $t1, $t1, $t0

                #lbu $t0, 1($a0)
                #li $t4, 60
                #multu $t0, $t4
                #mflo $t2
                #addu $t1, $t1, $t2

                #lbu $t0, 2($a0)
                #li $t4, 3600
                #multu $t0, $t4
                #mflo $t2
                #addu $t1, $t1, $t2

                #move $v0, $t1
                
inicializa_reloj_en_s:
                li $t0, 60
                divu $a1, $t0
                mfhi $t1
                sb $t1, 0($a0)

                mflo $t1
                divu $t1, $t0
                mfhi $t1
                sb $t1, 1($a0)

                mflo $t1
                sb $t1, 2($a0)

                jr $ra
                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall


                la $a0, cad_reloj_en_s  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # Código de print_int
                syscall

                la $a0, cad_asteriscos  # Dirección de la cadena
                li $v0, 4               # Código de print_string
                syscall
                jr $ra
                
                ########################################################## 
                # Subrutina que incrementa el reloj en una hora
                # Entrada: $a0 con la dirección del reloj
                # Salida: reloj incrementado en memoria
                # Nota: 23:MM:SS -> 00:MM:SS
                ########################################################## 
                
pasa_hora:      lbu $t0, 2($a0)         # $t0 = HH
                addiu $t0, $t0, 1       # $t0 = HH++
                li $t1, 24
                beq $t0, $t1, H24       # Si HH==24 se pone HH a cero
                sb $t0, 2($a0)          # Escribe HH++
                j fin_pasa_hora
H24:            sb $zero, 2($a0)        # Escribe HH a 0
fin_pasa_hora:  jr $ra

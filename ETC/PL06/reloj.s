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
                # Segmento de c—digo
                ##########################################################

                .globl __start
                .text 0x00400000

__start:        la $a0, reloj           # $a0 = reloj
                li $a1, 0x00173b3b      # $a1 = 0x00173B3B
                jal inicializa_reloj

                la $a0, reloj           # $a0 = reloj
                jal imprime_reloj

                la $a0, reloj           # $a0 = reloj
                jal devuelve_reloj_en_s
                move $a0, $v0           # $a0 <- $v0
                jal imprime_s

                la $a0, reloj           # $a0 = reloj
                jal devuelve_reloj_en_s_sd
                move $a0, $v0           # $a0 <- $v0
                jal imprime_s

                la $a0, reloj           # $a0 = reloj
                jal devuelve_reloj_en_s_srd
                move $a0, $v0           # $a0 <- $v0
                jal imprime_s

                la $a0, reloj           # $a0 = reloj
                jal pasa_segundo
                jal pasa_segundo

                la $a0, reloj           # $a0 = reloj
                jal imprime_reloj

                la $a0, reloj           # $a0 = reloj
                li $a1, 0x00150f2d      # $a1 = 0x00150F2D
                jal inicializa_reloj

                la $a0, reloj           # $a0 = reloj
                jal imprime_reloj

                la $a0, reloj           # $a0 = reloj
                li $a2,3                # $a2 = 3
                li $a3,40               # $a2 = 40
                jal sumador_horas
salto2:         jal sumador_segundos
salto3:
                la $a0, reloj           # $a0 = reloj
                jal imprime_reloj
             
salir:          li $v0, 10              # C—digo de exit (10)
                syscall                 # òltima instrucci—n ejecutada
                .end

                ##########################################################
                # Bucle para sumar horas al reloj
                # Entrada: $a0 con el valor del reloj
                #          $a1 con las horas a sumar
                # Salida: $a0 con el nuevo valor del reloj
                ##########################################################

sumador_horas:  jal pasa_hora           # Llamada a la funci—n pasa_hora
                subu $a2, $a2 ,1        # $a2 = $a2--
                bgtz $a2, sumador_horas # $a2 >= 0 -> sumador_horas
                j salto2

                ##########################################################
                # Bucle para sumar segundos al reloj
                # Entrada: $a0 con el valor del reloj
                #          $a1 con los segundos a sumar
                # Salida: $a0 con el nuevo valor del reloj
                ##########################################################

sumador_segundos:
                jal pasa_segundo        # Llamada a la funci—n pasa_segundo
                subu $a3, $a3 ,1        # $a3 = $a3--
                bgtz $a3, sumador_segundos  # $a3 >= 0 -> sumador_segundos
                j salto3

                ########################################################## 
                # Subrutina que inicializa el reloj
                # Entrada: $a1 con el valor para iniciar el reloj
                # Salida: $a0 el registro con el reloj en el
                ##########################################################

inicializa_reloj: 
                li $t0, 0x1F3F3F        # $t0 = 0x1F3F3F -> Mascara del reloj
                and $t0, $a1, $t0       # $t0 = $a1 AND $t0 -> Se aplica la mascara
                sw $t0, 0($a0)          # $a0 <- $t0
                jr $ra

                ########################################################## 
                # Subrutina que calcula el valor del reloj en segundos
                # Entrada: $a0 con la direcci—n de la variable reloj
                # Salida: $v0 con la direcci—n de los segundos del reloj
                ##########################################################

devuelve_reloj_en_s:
                lbu $t0, 2($a0)         # $t0 = HH
                li $t1, 3600            # $t1 = 36000
                multu $t0, $t1          # $t0 * $t1
                mflo $v0                # $v0 <- LO
                mfhi $t2                # $t2 <- HI
                bgtz $t2, salir         # $t2 >= 0 -> salir

                lbu $t0, 1($a0)         # $t0 = MM
                li $t1, 60              # $t1 = 60
                multu $t0, $t1          # $t0 * $t1
                mflo $t0                # $t0 <- LO
                mfhi $t2                # $t2 <- HI
                bgtz $t2, salir         # $t2 >= 0 -> salir
                addu $v0, $v0, $t0      # $v0 = $v0 + $t0

                lbu $t0, 0($a0)         # $t0 = SS
                addu $v0,$v0,$t0        # $v0 = $v0 + $t0

                jr $ra

                ########################################################## 
                # Subrutina que calcula el valor del reloj en segundos,
                # usando la t‡ctica de desplazamientos.
                # Entrada: $a0 con la direcci—n de la variable reloj
                # Salida: $v0 con la direcci—n de los segundos del reloj
                ##########################################################

devuelve_reloj_en_s_sd:
                lbu $t0, 2($a0)             # $t0 = HH
                sll $v0, $t0, 11            # $v0 = HH*2^11
                sll $t1, $t0, 10            # $t1 = HH*2^10
                addu $v0, $v0, $t1          # $v0 = HH*(2^11 + 2^10)
                sll $t1, $t0, 9             # $t1 = HH*2^9
                addu $v0, $v0, $t1          # $v0 = HH*(2^11 + 2^10 + 2^9)
                sll $t1, $t0, 4             # $t1 = HH*2^4
                addu $v0, $v0, $t1          # $v0 = HH*(2^11 + 2^10 + 2^9 + 2^4)

                lbu $t0, 1($a0)             # $t0 = MM
                sll $t1, $t0, 5             # $t1 = MM*2^5
                addu $v0, $v0, $t1          # $v0 = MM*2^5 + HH*(2^11 + 2^10 + 2^9 + 2^4)
                sll $t1, $t0, 4             # $t1 = MM*2^4
                addu $v0, $v0, $t1          # $v0 = MM*(2^5 + 2^4) + HH*(2^11 + 2^10 + 2^9 + 2^4)
                sll $t1, $t0, 3             # $t1 = MM*2^3
                addu $v0, $v0, $t1          # $v0 = MM*(2^5 + 2^4 + 2^3) + HH*(2^11 + 2^10 + 2^9 + 2^4)
                sll $t1, $t0, 2             # $t1 = MM*2^2
                addu $v0, $v0, $t1          # $v0 = MM*(2^5 +2^4 + 2^3 + 2^2) + HH*(2^11 + 2^10 + 2^9 + 2^4)

                lbu $t0, 0($a0)             # $t0 = SS
                addu $v0, $v0, $t0          # $v0 = SS + MM*(2^5 +2^4 + 2^3 + 2^2) + HH*(2^11 + 2^10 + 2^9 + 2^4)

                jr $ra

                ########################################################## 
                # Subrutina que calcula el valor del reloj en segundos,
                # usando la t‡ctica de desplazamiento y la codificaci—n
                # de Booth
                # Entrada: $a0 con la direcci—n de la variable reloj
                # Salida: $v0 con la direcci—n de los segundos del reloj
                ##########################################################

devuelve_reloj_en_s_srd:
                lbu $t0, 2($a0)             # $t0 = HH
                sll $v0, $t0, 11            # $v0 = HH*2^11
                sll $t1, $t0, 10            # $t1 = HH*2^10
                addu $v0, $v0, $t1          # $v0 = HH*(2^11 + 2^10)
                sll $t1, $t0, 9             # $t1 = HH*2^9
                addu $v0, $v0, $t1          # $v0 = HH*(2^11 + 2^10 + 2^9)
                sll $t1, $t0, 4             # $t1 = HH*2^4
                addu $v0, $v0, $t1          # $v0 = HH*(2^11 + 2^10 + 2^9 + 2^4)

                lbu $t0, 1($a0)             # $t0 = MM
                sll $t1, $t0, 6             # $t1 = MM*2^6
                addu $v0, $v0, $t1          # $v0 = MM*2^6 + HH*(2^11 + 2^10 + 2^9 + 2^4)
                sll $t1, $t0, 2             # $t1 = MM*2^2
                subu $v0, $v0, $t1          # $v0 = MM*(2^6 - 2^2) + HH*(2^11 + 2^10 + 2^9 + 2^4)

                lbu $t0, 0($a0)             # $t0 = SS
                addu $v0, $v0, $t0          # $v0 = SS + MM*(2^6 - 2^2) + HH*(2^11 + 2^10 + 2^9 + 2^4)

                jr $ra

                ########################################################## 
                # Subrutina que imprime el valor del reloj
                # Entrada: $a0 con la direcci—n de la variable reloj
                ########################################################## 

imprime_reloj:  move $t0, $a0
                la $a0, cad_asteriscos  # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
                syscall

                la $a0, cad_horas       # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
                syscall

                lbu $a0, 2($t0)         # Lee el campo HH
                li $v0, 1               # C—digo de print_int
                syscall

                la $a0, cad_minutos     # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
                syscall

                lbu $a0, 1($t0)         # Lee el campo MM
                li $v0, 1               # C—digo de print_int
                syscall

                la $a0, cad_segundos    # Direcciœn de la cadena
                li $v0, 4               # C—digo de print_string
                syscall

                lbu $a0, 0($t0)         # Lee el campo SS
                li $v0, 1               # C—digo de print_int
                syscall

                la $a0, cad_asteriscos  # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
                syscall
                jr $ra

                ########################################################## 
                # Subrutina que imprime los segundos calculados
                # Entrada: $a0 con los segundos a imprimir
                ########################################################## 

imprime_s:      move $t0, $a0
                la $a0, cad_asteriscos  # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
                syscall


                la $a0, cad_reloj_en_s  # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
                syscall

                move $a0, $t0           # Valor entero a imprimir
                li $v0, 1               # C—digo de print_int
                syscall

                la $a0, cad_asteriscos  # Direcci—n de la cadena
                li $v0, 4               # C—digo de print_string
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

pasa_segundo:	lbu $t0, 0($a0)         # $t0 = SS
                addiu $t0, $t0, 1       # $t0 = SS++
                li $t1, 60              # $t1 = 60
                beq $t0, $t1, S60       # $t0 == $t1 -> S60
                sb $t0, 0($a0)          # $SS <- $t0
                j fin_pasa_segundo
S60:            sb $zero, 0($a0)        # $a0 <- 0
pasa_minuto:	lbu $t2, 1($a0)         # $t2 = MM
                addiu $t2, $t2, 1       # $t2 = MM++
                li $t3, 60              # $t3 = 60
                beq $t2, $t3, M60       # $t2 == $t3 -> M60
                sb $t2, 1($a0)          # MM <- $t2
                j fin_pasa_minuto
M60:            sb $zero, 1($a0)        # MM <- 0
                j pasa_hora
fin_pasa_minuto:
                jr $ra
fin_pasa_segundo:
                jr $ra
		

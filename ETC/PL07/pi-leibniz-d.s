                ##########################################################
                # Segmento de datos
                ##########################################################

                .data 0x10000000
cad_entrada:    .asciiz "\nDime el numero de iteraciones: "
cad_salida:     .asciiz "El valor calculado de pi es: " 

                ##########################################################
                # Segmento de c—digo
                ##########################################################

                .globl __start
                .text 0x00400000
__start:        
                ########################################################
                # Lectura del numero de iteraciones
                ########################################################

                la $a0, cad_entrada     # Cadena a imprimir
                li $v0, 4               # Funcion print_string
                syscall

                li $v0, 5               # Funcion read_int
                syscall

                move $a0, $v0           # Parametro de la subrutina
                jal leibniz             # Salto a la subrutina

                ########################################################
                # Impresion del resultado
                ########################################################

                la $a0, cad_salida      # Cadena a imprimir
                li $v0, 4               # Funcion print_string
                syscall

                li $v0, 3               # Funcion print_double
                mfc1.d $t0, $f2           # Valor a imprimir
                mtc1.d $t0, $f12
                syscall

                ########################################################
                # Finalizacion del programa
                # Llamada al sistema denominada "exit"
                ########################################################= 

                li $v0, 10
                syscall
                
                ########################################################
                # Calculo de pi con el metodo de Leibniz
                # $a0 = Numero de iteraciones de la serie
                ########################################################

leibniz:        li.d $f0, 0.0           # Constante 0.0
                li.d $f4, 1.0           # Constante 1.0
                li.d $f6, 2.0           # Constante 2.0
                move $t0, $a0           # Contador numero de iteraciones

bucle:          mtc1 $t0, $f8           # Lleva n a la FPU
                cvt.d.w $f8, $f8        # Convierte n en numero real

                mul.d $f8, $f8, $f6     # Calcula 2.0*n
                add.d $f8, $f8, $f4     # Calcula 2.0*n + 1.0
                div.d $f8, $f4, $f8     # Calcula 1.0/(2.0*n + 1.0)

                andi $t1, $t0, 0x0001   # Extrae bit LSB de n
                bne $t1, $zero, resta   # Salta si es impar (LSB==1)
                add.d $f0, $f0, $f8     # El termino se suma
                j continua
resta:          sub.d $f0, $f0, $f8     # El termino se resta
continua:       addi $t0, $t0, -1       # Decrementa numero de iteraciones
                bgez $t0, bucle         # Vuelve si quedan iteraciones		

                li.d $f4, 4.0           # Constante 4.0
                mul.d $f2, $f0, $f4     # Devuelve en $f0 el calculo de pi
                jr $ra
                .end

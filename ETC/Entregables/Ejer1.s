
# Ejer1.s

#  Created by Josep Dols on 26/11/15.

# Subrutina que calcule el producto escalar de dos vectores de elementos de tipo float.
# En $a0 y $a1 se pasará la dirección de los dos vectores y en $a2 la dimensión.
# El resultado se retornará en $f0.
#   $f0 = prodesc(float *a0, float *a1, int $a2)
# Implemente también el programa principal realizando la llamada a la función creada.

            .data 0x10000000
vector_a:          .float 2.0, 3.0  4.0
vector_b:          .float 3.0, 4.0, 5.0
result_aux:        .space 12
zero_f:            .float 0.0
dim:               .word 3

            .globl __start
            .text 0x00400000
__start:
            #Direcciones de los vectores
            la $t0, vector_a
            la $t1, vector_b
            la $t3, result_aux

            #Bloque para cargar la dimension del vector
            la $t2, dim
            lw $t2, 0($t2)
            mtc1 $t2, $f1
            j prodesc

             li $v0, 10
            syscall
            .end

prodesc:
            #Método para calcular el producto de 2 numeros
            lwc1 $f2, 0($t0)    #Lee vector_a[i]
            lwc1 $f3, 0($t1)    #Lee vector_b[i]
            lwc1 $f4, 0($t3)    #Espacio para guardar auxiliar
            mul.s $f4, $f2, $f3 #Multiplicacion de a[i] y b[i]
            s.s $f4, 0($t3)     #Guarda el producto
            addiu $t2, $t2, -1  #Decrementa contador
            addi $t0, $t0, 4
            addi $t1, $t1, 4
            addi $t3, $t3, 4
            bgtz $t2, prodesc

            la $t2, dim
            lw $t2, 0($t2)
            la $t3, result_aux
            la $t6, zero_f
            l.s $f6, 0($t6)

suma_vec:
            l.s $f5,0($t3)

            #mtc1 $f6, $t6
            add.s $f6, $f6, $f5
            addiu $t2, $t2, -1
            addiu $t3, $t3, 4
            bgtz $t2, suma_vec

            mov.s $f0, $f6
            jr $ra







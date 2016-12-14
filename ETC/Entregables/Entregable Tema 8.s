##############################################################
##                                                          ##
##  Entregable Tema 8.s                                     ##
##                                                          ##
##  Created by Josep Dols on 19/5/16.                       ##
##                                                          ##
##############################################################

.kdata
coches:     .word 0

.ktext

int1:   la $t0, 0xFFFF1000
        lb $t1, 0x0($t0)    # Cancelar la interrupción
        la $t0, 0xFFFF2000
        li $t1, 0x08
        sb $t1, 0($t0)
        j retexc            # Salta al final del manejador

##############################################################
##                                                          ##
## EJERCICIO 1                                              ##
## Escribe el tratamiento de la interrupción Int3 para que  ##
## se baje la barrera al pasar un coche. Además, el         ##
## tratamiento ha de contar los coches que entran           ##
## incrementando la variable definida en el segmento de     ##
## memoria .ktext coches: .word 0                           ##
##                                                          ##
##############################################################

int3:   la $t0, 0xFFFF3000
        li $t1, 0x10
        sb $t1, 0x0($t0)    # Cancelar la interrupción
        la $t0, 0xFFFF2000
        sb $t1, 0x0($t0)    # Bajar la barrera
        lw $t1, coches
        addi $t1, $t1, 1
        sw $t1, coches      # Aumentar el número de coches
        j retexc

##############################################################
##                                                          ##
## EJERCICIO 2                                              ##
## Escribe el tratamiento de la interrupción Int4 para que  ##
## se levante la barrera al detectar un coche.              ##
##                                                          ##
##############################################################

int4:   la $t0, 0xFFFF3100
        li $t1, 0x10
        sb $t1, 0x0($t0)    # Cancelar la interrupción
        la $t0, 0xFFFF2100
        li $t1, 0x08
        sb $t1, 0x0($t0)    # Abrir la barrera
        j retexc

##############################################################
##                                                          ##
## EJERCICIO 3                                              ##
## Escribe el tratamiento de la interrupción Int5 para que  ##
## se baje la barrera al pasar un coche. Además, el         ##
## tratamiento ha de descontar los coches que salen         ##
## decrementando la variable coches definida anteriormente. ##
##                                                          ##
##############################################################

int5:   la $t0, 0xFFFF3200
        li $t1, 0x10
        sb $t1, 0x0($t0)    # Cancelar la interrupción
        la $t0, 0xFFFF2100
        sb $t1, 0x0($t0)    # Bajar la barrera
        lw $t1, coches
        addi $t1, $t1, -1
        sw $t1, coches      # Disminuir el número de coches
        j retexc

##############################################################
##                                                          ##
## EJERCICIO 4                                              ##
## El vicerrector de Fomento de la universidad quiere que   ##
## se levante la barrera cuando la tarjeta sea de cualquier ##
## tipo si hay menos de 50 coches, si es plantilla o VIP si ##
## hay menos de 100 coche y solo VIP si hay menos de 150    ##
## coches. Si hay 150 coches (lleno) no se levantará la     ##
## barrera. Modifica el tratamiento de la interrupción Int1 ##
## para seguir estas directrices.                           ##
##                                                          ##
##############################################################

int1:       la $t0, 0xFFFF1000
            lb $t1, 0x0($t0)
            andi $t1, $t1, 0x18
            beq $t1, $zero, estudiante  # Si el código de la tarjeta es 00
            li $t0, 0x08
            beq $t1, $t0, plantilla     # Si el código de la tarjeta es 01
            li $t0, 0x10
            beq $t1, $t0, vip           # Si el código de la tarjeta es 10
            j retexc
estudiante: lw $t0, coches
            li $t1, 50
            blt $t0, $t1, abre          # Compueba si puede entrar en el parking
            j retexc
plantilla:  lw $t0, coches
            li $t1, 100
            blt $t0, $t1, abre          # Compueba si puede entrar en el parking
            j retexc
vip:        lw $t0, coches
            li $t1, 150
            blt $t0, $t1, abre          # Compueba si puede entrar en el parking
            j retexc
abre:       la $t0, 0xFFFF2000
            li $t1, 0x08
            sb $t1, 0x0($t0)            # Abre la barrera
            j retexc

##############################################################
##                                                          ##
## EJERCICIO 5                                              ##
## Si los tres sensores estuvieran conectados a una única   ##
## línea de interrupción Int3. Rediseña la función de la    ##
## Int3 para que incluya la funcionalidad de los            ##
## apartados 1, 2 y 3.                                      ##
##                                                          ##
##############################################################

int3:   la $t0, 0xFFFF3000
        lb $t1, 0x0($t0)
        andi $t1, $t1, 0x1
        bne $t1, $zero, sen1    # Comprueba si es el dispositivo que causa la excepcion
        la $t0, 0xFFFF3100
        lb $t1, 0x0($t0)
        andi $t1, $t1, 0x1
        bne $t1, $zero, sen2    # Comprueba si es el dispositivo que causa la excepcion
        la $t0, 0xFFFF3200
        lb $t1, 0x0($t0)
        andi $t1, $t1, 0x1
        bne $t1, $zero, sen3    # Comprueba si es el dispositivo que causa la excepcion
sen1:   la $t0, 0x0FFFF3000
        li $t1, 0x10
        sb $t1, 0x0($t0)        # Cancelar la interrupcion
        la $t0, 0xFFFF2000
        sb $t1, 0x0($t0)        # Bajar la barrera
        lw $t1, coches
        addi $t1, $t1, 1
        sw $t1, coches          # Incrementar el número de coches
sen2:   la $t0, 0xFFFF3100
        li $t1, 0x10
        sb $t1, 0x0($t0)        # Cancelar la interrupción
        la $t0, 0xFFFF2100
        li $t1, 0x08
        sb $t1, 0x0($t0)        # Abrir la barrera
        j retexc
sen3:   la $t0, 0xFFFF3200
        li $t1, 0x10
        sb $t1, 0x0($t0)        # Cancelar la interrupción
        la $t0, 0xFFFF2200
        sb $t1, 0x0($t0)        # Bajar la barrera
        lw $t1, coches
        addi $t1, $t1, -1
        sw $t1, coches          # Disminuir el número de coches
        j retexc

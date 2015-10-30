#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <semaphore.h>

/*
 CONSTANTES
 REPETICIONES : Numero de veces que se suma/resta 1 a V
 */

#define REPETICIONES      1000

/*
 VARIABLES GLOBALES (COMPARTIDAS)
 */

long int V = 100;      // Valor inicial
sem_t sem;             // No esta inicializado, solo declarado.

/*
 FUNCIONES AUXILIARES
 test_and_set(int * objetivo) : devuelve 1 (cierto) si llave esta siendo utilizada,
 devuelve 0 (falso) si llave esta libre.
 */

int test_and_set(int *spinlock) {
    int ret;
    __asm__ __volatile__(
                         "xchg %0, %1"
                         : "=r"(ret), "=m"(*spinlock)
                         : "0"(1), "m"(*spinlock)
                         : "memory");
    return ret;
}


/*
 FUNCIONES QUE EJECUTAN LAS TAREAS
 - Agrega : Ejecuta un bucle donde incrementa la variable V
 - Resta  : Ejecuta un bucle donde decrementa la variable V
 - Inspecciona : Imprime cada segundo el valor de V
 */

void *agrega (void *argumento) {
    
    long int cont;
    long int aux;
    sem_wait(&sem);
    for (cont = 0; cont < REPETICIONES; cont = cont + 1) {
      aux = V;
      aux= aux + 1;
      usleep(500);
      V = aux;     
    }
    sem_post(&sem);
    printf("-------> Fin AGREGA (V = %ld)\n", V);
    pthread_exit(0);
}

void *resta (void *argumento) {
    
    long int cont;
    long int aux;
    sem_wait(&sem);
    for (cont = 0; cont < REPETICIONES; cont = cont + 1) {
      aux = V;
      aux= aux - 1;
      usleep(500);
      V = aux;   
    }
    sem_post(&sem);
    printf("-------> Fin RESTA  (V = %ld)\n", V);
    pthread_exit(0);
}

void *inspecciona (void *argumento) {
    
    for (;;) {
        usleep(200000);
        fprintf(stderr, "Inspeccion: Valor actual de V = %ld\n", V);
    }
}

/*
 PROGRAMA PRINCIPAL
 */
int main (void) {
    //Declaracion de las variables necesarias.
    pthread_t hiloSuma, hiloResta, hiloInspeccion;
    pthread_attr_t attr;
    
    // Definimos el valor de los atributos de las tareas (por defecto)
    pthread_attr_init(&attr);
    
    //Inicializar el semaforo.
    sem_init(&sem,0,1);
    
    // EJERCICIO: Creamos las tres tareas con dichos atributos
    pthread_create(&hiloSuma,&attr,agrega,NULL);
    pthread_create(&hiloResta,&attr,resta,NULL);
    pthread_create(&hiloInspeccion, &attr,inspecciona,NULL);
    
    // EJERCICIO: Obligamos a que el programa principal se espere a que las
    // tareas "agrega" y "resta" finalicen
    pthread_join(hiloSuma,NULL);
    pthread_join(hiloResta,NULL);
    
    // Fin del programa principal
    fprintf(stderr, "-------> VALOR FINAL: V = %ld\n\n", V);
    exit(0);
}





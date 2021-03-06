/*** fichero biblio_cos.c ***/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>

/*** Variables globales ***/
char path_maps[80];
char ni_glob[4096];
long i_glob=20;

void f(int param){
    printf("Direccion del parametro de la funcion f: %p\n",&param);
}

int main(){
    /*** Variables locales ***/
    long i_loc=20;
    long ni_loc;
    float c;
    
    printf("PID del proceso: %d\n\n", getpid());
    /**** VISUALIZACION DE DIRECCIONES ***/
    printf("Direccion de funcion main: %p \n", main);
    printf("Direccion de funcion f: %p \n", f);
    printf("Direccion de var. global inicializada i_glob: ,%p\n", &i_glob);
    printf("Direccion de var. global NO inicializada ni_glob\n");
    printf(" dir. del primer elemento de ni_glob: %p\n", &ni_glob[0]);
    printf(" dir. del ultimo elemento de ni_glob: %p\n", &ni_glob[4095]);
    printf("Direccion de var. local inicializada i_loc: %p\n", &i_loc);
    printf("Direccion de var. local NO inicializada ni_loc:%p\n", &ni_loc);
    f(40);
    /** Operacion matematica **/
    c=cos(45);
    printf("El resultado de la operacion matematica es: %f\n", c);
    printf("\n MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    sprintf(path_maps,"cat /proc/%d/maps",getpid());
    fflush(stdout);
    system(path_maps);
    printf(" ---------------\n\n");
    return 0;
}
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>

void construye_orden(char orden[80])
{
 //Construye orden para mostrar MAPA MEMORIA
  sprintf(orden,"cat /proc/%d/maps",getpid());
   
}

void  *Imprime( void *ptr)
{char *men;
  men=(char *) ptr;
  fprintf(stderr,"El hilo dice:%s\n", men);
  pthread_exit(NULL);
}


int main ()
{
    int val_pid;
    char path_maps[80];
    pthread_attr_t atrib;
    pthread_t hilo1,hilo2, hilo3;
      
      
    printf(" MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps);  //LLamada al sistema para ejecutar orden

    //creo los hilos con atributos
   
    pthread_attr_init(&atrib);

    pthread_create(&hilo1, &atrib,Imprime, "hola de hilo1");
    pthread_create(&hilo2, &atrib,Imprime, "hola de hilo2");
    pthread_create(&hilo3, &atrib,Imprime, "hola de hilo3");
	
    printf(" MAPA DE MEMORIA DEL PROCESO con hilos /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps); //LLamada al sistema para ejecutar orden
	
    pthread_join(hilo1,NULL);
    pthread_join(hilo2,NULL);
    pthread_join(hilo3,NULL);
	
    exit(0);
}


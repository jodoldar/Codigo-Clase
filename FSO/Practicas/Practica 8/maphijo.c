#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void construye_orden(char orden[80])
{
 //Construye orden para mostrar MAPA MEMORIA
  sprintf(orden,"cat /proc/%d/maps",getpid());
   
}


int main ()
{
    int val_pid;
    char path_maps[80];
      
      
    printf(" MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps);  //LLamada al sistema para ejecutar orden


    val_pid=fork();//se crea un proceso

    if(val_pid==0)
      { printf("MAPA DEL PROCESO HIJO %d\n",getpid());
        construye_orden(path_maps);
	system(path_maps); //LLamada al sistema para ejecutar orden

        printf("\n MAPA DE MEMORIA PROCESO %d DESPUES DE   exec \n", getpid()); 
	sprintf(path_maps,"/proc/%d/maps",getpid());
	execlp("cat","cat",path_maps,NULL);
 
        exit(0);
      }

    if (val_pid==-1)
	{ printf("No se ha podido crear el hijo \n");
	  exit(-1);
	}
    if (val_pid>0) wait(); //Padre espera	
 
	
    printf(" MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps); //LLamada al sistema para ejecutar orden
	
	
    exit(0);
}



#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>


void error (char * mensaje)
{
  fprintf(stderr, "%s", mensaje);
    exit(EXIT_FAILURE);
}

void construye_orden(char orden[80])
{
 //Construye orden para mostrar MAPA MEMORIA
  sprintf(orden,"cat /proc/%d/maps",getpid());
   
}

int main (int argc,char *argv[])
{
    int fd;
    void *mapeo;
    struct stat stadbuf;
     char path_maps[80];

    //Abrir el archivo a ser mapeado
    if (argc!=2) {
      fprintf(stderr,"Usar: mapear NombreArchivo\n");
      exit(EXIT_FAILURE);
    }

    if ((fd=open(argv[1],O_RDONLY))<0)
    error("Fallo en la apertura (open)\n");

    //Obtener la longitud del archivo a mapear
    fstat(fd, &stadbuf); //fstat vuelca su informacion en estadobuf

   //MOSTRAR MAPA
    printf("El fichero proyectado es %s de longitud %d\n",argv[1], stadbuf.st_size);
    printf(" MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps);//Llamada al sistema para ejecutar orden
  
	
  //Mapear el archivo de entrada
    if ((mapeo=mmap(0,stadbuf.st_size,PROT_READ,MAP_SHARED,fd,0)) == MAP_FAILED)
    error("Fallo al mapear (open)");
    close(fd); //cierro fichero 
    
  //MOSTRAR MAPA
   printf ("\n\n FICHERO MAPEADO EN MEMORIA\n");
   system(path_maps);//Llmada al sistema para ejecutar orden
     
   munmap(mapeo,stadbuf.st_size); //Elimina mapeo
 
	
  printf ("\n\n ELIMINADO EL MAPEO DEL FICHERO EN MEMORIA\n");
  system(path_maps);
  exit(EXIT_SUCCESS);
}
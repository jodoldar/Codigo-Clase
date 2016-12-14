
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>


void error (char * mensaje)
{
    perror(mensaje);
    exit(EXIT_FAILURE);
}

void construye_orden(char orden[80])
{
 //Construye orden para mostrar MAPA MEMORIA
  sprintf(orden,"cat /proc/%d/maps",getpid());
   

}

int main (int argc,char *argv[])
{
  int fd, contador,i;
    void *mapeo;
    struct stat stadbuf;
    char path_maps[80];
    char c, *pm;

    //Abrir el archivo a ser mapeado
    if (argc!=3) {
    puts("Usar mapear2: NombreArchivo, caracter \n");
    exit(EXIT_FAILURE);
    }

    if ((fd=open(argv[1],O_RDONLY))<0)
    error("Fallo en la apertura (open)\n");

    //Obtener la longitud del archivo a mapear
    fstat(fd, &stadbuf); //fstat vuelca su informacion en estadobuf

   //MOSTRAR MAPA
    printf(" MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps);//Llamada al sistema para ejecutar orden

	
//Mapear el archivo de entrada
    if ((mapeo=mmap(0,stadbuf.st_size,PROT_READ,MAP_SHARED,fd,0)) == MAP_FAILED)
    error("Fallo al mapear (open)");
    close(fd); //cierro archivo
    

   printf ("\n\n FICHERO MAPEADO EN MEMORIA\n");
   system(path_maps);//Muestra el mapa de memoria
   
   //cuenta el numero de veces que aparece el carcter argv[2]
   contador=0;
   c=argv[2][0];
   pm= mapeo;
   for (i=0; i<stadbuf.st_size; i++)
     {if (*pm==c) contador++;
       pm++;
     }
  

 
   munmap(mapeo,stadbuf.st_size);   //Elimino el mapeo del fichero
 
   //MOSTRAR MAPA
   printf ("\n\n ELIMINADO EL MAPEO DEL FICHERO EN MEMORIA\n");
  system(path_maps);//Llamada al sistema para ejecutar orden
  
  printf("\nHay %d caracteres %c en el fichero %s\n", contador,argv[2][0],argv[1]);	
  exit(EXIT_SUCCESS);
}




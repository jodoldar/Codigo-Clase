
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>


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

int  contar_caracteres(char caracter, char *dirlog, int longitud)
{int i,contador=0;
  for (i=0; i<longitud; i++)
    if (dirlog[i]==caracter) contador++;
  return contador;

}

int escribir_fichero(char *frase, char *dirlog, int longitud)
{ int i,long_frase, sucess=-1;
 
  long_frase=strlen(frase);
  printf("longitud frase %d, longitud del fichero %d\n",long_frase,longitud);
  for(i=0; i<long_frase; i++)
     dirlog[i]=frase[i];
     
  if (long_frase==i) sucess=1;

  return sucess;
}

int main (int argc,char *argv[])
{
    int fd;
    char *mapeo;
    struct stat stadbuf;
    char caracter,path_maps[80];
    int i,contador=0,num_car;


    //Abrir el archivo a ser mapeado
    if (argc!=4) {
      fprintf(stderr,"Usar: mapear NombreArchivo\n");
      exit(EXIT_FAILURE);
    }

    if ((fd=open(argv[1],O_RDWR))<0)
    error("Fallo en la apertura (open)\n");

    //Obtener la longitud del archivo a mapear
    fstat(fd, &stadbuf); //fstat vuelca su informacion en estadobuf

   //MOSTRAR MAPA
    printf("El fichero proyectado es %s de longitud %d\n",argv[1], stadbuf.st_size);
    printf(" MAPA DE MEMORIA DEL PROCESO /proc/%d/maps \n", getpid());
    construye_orden(path_maps);
    system(path_maps);//Llamada al sistema para ejecutar orden
  
	
  //Mapear el archivo de entrada
    if ((mapeo=mmap(0,stadbuf.st_size,PROT_READ|PROT_WRITE,MAP_SHARED,fd,0)) == MAP_FAILED)
    error("Fallo al mapear (open)\n");
   
    
  //MOSTRAR MAPA
   printf ("\n\n FICHERO MAPEADO EN MEMORIA\n");
   system(path_maps);//Llmada al sistema para ejecutar orden
   
    if ((escribir_fichero(argv[3],mapeo,stadbuf.st_size))<0)
     fprintf(stderr,"Error al escribir\n");
   

   num_car=contar_caracteres(argv[2][0],mapeo,stadbuf.st_size);
   fprintf(stderr,"%s contiene %d caracteres %c\n", argv[1],num_car,argv[2][0]);
  close(fd); //cierro fichero 
     
   munmap(mapeo,stadbuf.st_size); //Elimina mapeo
 
	
  printf ("\n\n ELIMINADO EL MAPEO DEL FICHERO EN MEMORIA\n");
  system(path_maps);
  exit(EXIT_SUCCESS);
}




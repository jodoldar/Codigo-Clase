//código del archivo  descriptor.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

int main (int argc,char *argv[]) {
    int fda,fdb;
	
    if (argc!=2) {
        fprintf(stderr, "uso: necesario archivo lectura/escritura\n");
        exit(-1);
    }
	
    if ((fda=open(argv[1],O_RDONLY))<0) {
        fprintf(stderr,"Fallo en la apertura (open)\n");
    } else {
        fprintf(stderr, "Leer %s descriptor = %d\n",argv[1],fda);
    }
	
    if ((fdb=open(argv[1],O_WRONLY))<0) {
        fprintf(stderr,"Fallo en la apertura (open)\n");
    } else {
        fprintf(stderr, "Escribir %s descriptor = %d\n",argv[1],fdb);
    }
    return(0);
}

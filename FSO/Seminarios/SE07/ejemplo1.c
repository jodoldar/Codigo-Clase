#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>

int main(int  argc, char **argv) {
    int i,fd,contador;
	char *p,*org;
	struct stat bstat;
	char caracter;
	caracter=*(&argv[1][0]);
    fd=open(argv[2], O_RDONLY); /* Abre fichero */
    fstat(fd, &bstat); /* Averigua long. fichero */
    /* Se proyecta el fichero */
    printf("espera antes de mapeo fichero\n");
    sleep(30); 
    org=mmap((caddr_t) 0, bstat.st_size, PROT_READ,MAP_SHARED, fd, 0);
	close(fd); /* Se cierra el fichero */
	  /* Bucle de acceso */
     p=org;
	 contador=0;
     for (i=0; i<bstat.st_size; i++)
        if (*p++==caracter) contador++;
    /* Se elimina la proyeccion */
    printf("espera entes de desalojar fichero de memoria\n");
    sleep(30);
    munmap(org, bstat.st_size);
    printf("%d\n", contador);
}

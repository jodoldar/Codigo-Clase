//compartir_archivo.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main (int argc,char *argv[]) {
    int fd;
	pid_t pid;
	mode_t fd_mode = S_IRWXU;   //permisos del fichero
	char *mensaje_padre = "mensaje padre\n";
	char *mensaje_hijo = "mensaje hijo\n";
	
	fd = open("mensajes.txt",O_RDWR | O_CREAT,fd_mode);
	write(fd,mensaje_padre,strlen(mensaje_padre));
	
	pid = fork();
	if (pid==0) {
        write(fd,mensaje_hijo,strlen(mensaje_hijo));
		close(fd);
		exit(0);
	}
	wait(pid);
	write(fd,mensaje_padre,strlen(mensaje_padre));
	close(fd);
	return(0);
}


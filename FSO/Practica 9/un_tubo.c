//codigo del archive un_tubo.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main (int argc,char *argv[]) {
    int i;
	char* argumentos1 [] = { "ls", "-la", 0 };
	char* argumentos2 [] = { "wc", "-l", 0 };
	int fildes[2];
	pid_t pid[2];
    
	//proceso padre crea tubo
	if ((pipe(fildes)==-1)) {
        fprintf(stderr,"fallo en el pipe\n");
        exit(-1);
	}
	for (i=0; i<2; i++) {
        pid[i] = fork(); //cree procesos
		if ((pid[i]==0) && (i==0)) {
			//proceso hijo redirecciona al tubo su salida
            if(dup2(fildes[1],STDOUT_FILENO)==-1){
                printf("Error de llamada dup2\n");
                exit(-1);
            }
			//proceso hijo cierra descriptores del tubo
            close(fildes[0]);
            close(fildes[1]);
			
			//hijo cambia su imagen de memoria
            if (execvp("ls",argumentos1)<0) {
                fprintf(stderr,"no he encontrado el ls\n");
                exit(-1);
            }
		}
		else if ((pid[i]==0) && (i==1)) {
			
			//proceso hijo redirecciona su entrada al tubo
            if(dup2(fildes[0],STDIN_FILENO)==-1){
                printf("Error de llamada dup2\n");
                exit(-1);
            }
			//proceso hijo cierra descriptores del tubo
            close(fildes[0]);
            close(fildes[1]);
			//hijo cambia su imagen de memoria
            if (execvp("wc",argumentos2)<0) {
                fprintf(stderr,"no he encontrado wc\n");
                exit(-1);
            }
		}
	}
	
	//padre cierra descriptores del tubo
	close(fildes[0]);
	close(fildes[1]);
	for (i=0;i<2;i++) wait(pid[i]);
	return(0);
}




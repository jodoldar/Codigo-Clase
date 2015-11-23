//codigo del archive dos_tubos.c
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main (int argc,char *argv[]) {
    int i, fd;
	char* argumentos1 [] = { "ls", "-la", 0 };
	char* argumentos2 [] = { "grep", "ejemplo", 0 };
    char* argumentos3 [] = { "wc", "-l", 0 };
    char *archivo = "result.txt";
    mode_t fd_mode = S_IRWXU;
	int tubo1[2];
    int tubo2[2];
	pid_t pid[3];
    
	//proceso padre crea tubo
	if ((pipe(tubo1)==-1)) {
        fprintf(stderr,"Fallo en el pipe 1\n");
        exit(-1);
	}
    if ((pipe(tubo2)==-1)) {
        fprintf(stderr,"Fallo en el pipe 2\n");
        exit(-1);
    }
	for (i=0; i<3; i++) {
        pid[i] = fork(); //cree procesos
		if ((pid[i]==0) && (i==0)) {
			//proceso hijo redirecciona al tubo su salida
            if(dup2(tubo1[1],STDOUT_FILENO)==-1){
                printf("Error de llamada dup2 del HIJO1\n");
                exit(-1);
            }
            
			//proceso hijo cierra descriptores del tubo
            close(tubo1[0]);
            close(tubo1[1]);
			
			//hijo cambia su imagen de memoria
            if (execvp("ls",argumentos1)<0) {
                fprintf(stderr,"no he encontrado el ls\n");
                exit(-1);
            }
		} else if ((pid[i]==0) && (i==1)) {
			
			//proceso hijo2 redirecciona su entrada a tubo1
            if(dup2(tubo1[0],STDIN_FILENO)==-1){
                printf("Error de llamada dup2 del HIJO2 - ENTRADA\n");
                exit(-1);
            }
            //Proceso hijo2 redirecciona la salida a tubo2
            if (dup2(tubo2[1],STDOUT_FILENO)==-1) {
                printf("Error de llamada dup2 del HIJO2 - SALIDA\n");
                exit(-1);
            }
            
			//proceso hijo cierra descriptores de los tubos
            close(tubo1[0]);
            //close(tubo1[1]);
            
            //close(tubo2[0]);
            close(tubo2[1]);
            
			//hijo cambia su imagen de memoria
            if (execvp("grep",argumentos2)<0) {
                fprintf(stderr,"no he encontrado grep\n");
                exit(-1);
            }
        } else if ((pid[i]==0) && (i==2)) {
            
            //proceso hijo3 redirecciona su entrada a tubo2
            if (dup2(tubo2[0],STDIN_FILENO)==-1) {
                printf("Error de llamada dup2 del HIJO3 - ENTRADA\n");
                exit(-1);
            }
            
            //proceso hijo3 redirecciona su salida a "result.txt"
            fd = open(archivo,O_RDWR | O_CREAT, fd_mode);
            if (dup2(fd,STDOUT_FILENO)==-1) {
                printf("Error de llamada dup2 del HIJO3 - SALIDA\n");
                exit(-1);
            }
            
            //Proceso hijo cierra descriptores de los tubos
            close(tubo2[0]);
            //close(tubo2[1]);
            close(fd);
            
            //hijo cambia su imagen de memoria
            if (execvp("wc",argumentos3)<0) {
                fprintf(stderr,"no he encontrado wc\n");
                exit(-1);
            }
        }
	}
	
	//padre cierra descriptores del tubo
	close(tubo1[0]);
	close(tubo1[1]);
    close(tubo2[0]);
    close(tubo2[1]);
	for (i=0;i<3;i++) wait(pid[i]);
	return(0);
}




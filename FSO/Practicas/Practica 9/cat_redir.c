//
//  cat_redir.c
//  
//
//  Created by Josep Dols on 23/11/15.
//
//

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main (int argc,char *argv[]) {
    int fd;
    char *arch = "ls_salida.txt";
    mode_t fd_mode = S_IRWXU;   //permisos del fichero
    
    fd = open(arch,O_RDONLY,fd_mode);
    if (dup2(fd,STDIN_FILENO)==-1) {
        printf("Error en llamada dup2\n");
        exit(-1);
    }
    
    execl("/bin/cat","cat","ls_salida.txt",NULL);
    
    fprintf(stdout,"out:Salida redireccionada\n");
    fprintf(stderr,"error: no redireccionada\n");
    fprintf(stderr,"Compruebe archivo %s\n",arch);
    close(fd);
    return(0);
}




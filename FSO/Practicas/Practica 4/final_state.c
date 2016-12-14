#include <stdio.h>
#include <stdlib.h>

int main(){
    int final_state,i;
    pid_t val_return;
    for (i=0; i<4; i++) {
        val_return = fork();
        if (val_return==0) {
            printf("Hijo creado en la iteración %d\n",i);
        }else{
            printf("Id del padre, %ld, Iteración %d\n",(long)getpid(),i);
            printf("Creado un hijo con Id %ld\n",(long)val_return);
            break;
        }
    }
    while (wait(&final_state)>0) {
        printf("Father %ld iteration %d",(long) getpid(),i);
        printf("My son said %d\n",WEXITSTATUS(final_state));
        printf("My son said %d\n", final_state/256);
    }
    exit(i);
}
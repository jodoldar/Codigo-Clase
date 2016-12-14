#include <stdio.h>
#include <stdlib.h>

int main(){
    int i=0;
    int val_return;
    while (i<5) {
        val_return=fork();
        if (val_return==0) {
            exit(i);
        }
        printf("Hijo creado en iteración = %d\n",i);
        i++;
    }
    wait(val_return);
    sleep(10);
    exit(0);
}
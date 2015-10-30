/* change_memory1.c */
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    pid_t childpid;
    int status, x, i;
    char* arguments[argc];
    //= {"ls", "-R", "/", NULL};
    
    for (i=0; i<argc; i++) {
        arguments[i]=argv[i];
    }

    childpid = fork();
    if (childpid == -1) {
        fprintf(stderr, "fork failed \n");
        exit(1);
    } else if (childpid == 0) {
        if (execlp(argv[0],arguments) < 0) {
            fprintf(stderr, "Could not execute ls \n");
            exit(1);
        }
    }
    x = wait(&status);
    if (x != childpid)
        fprintf(stderr, "Child has been interrupted by a signal \n");
    exit(0);
}
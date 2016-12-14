#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
     pid_t val;
     printf("PID antes fork(): %d\n", (int) getpid());

     val=fork();
     if (val > 0) {
       printf("Padre PID: %d\n", (int) getpid());
       sleep(2);
       printf("Termina padre\n");
     } else {
       printf("Hijo PID: %d\n", (int) getpid());
       sleep(5);
       printf("Termina hijo (PPID: %d\n)", (int) getppid());
     };
     return 0;
}


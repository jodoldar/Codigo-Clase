#include <stdio.h>
#include <unistd.h>

int main(void)
{ 
  printf("Process %ld creates another process\n", (long)getpid());
  fork();
  printf("Process %ld with parent %ld\n",(long)getpid(),(long)getppid());
  sleep(5);
  return 0;
}

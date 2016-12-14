Seminario 3:

Identificador de procesos:
	-PID del proceso, cada proceso tiene un PID asociado. 
	-PPID, PID del proceso padre que ha creado al actual, condición necesaria

	pid_t getpid(void);
	pid_t getppid(void);

	#include <stdio.h>
	#include <unistd.h>

	int main(void){
		printf("\nID del proceso: %ld\n", (long)getpid());
		printf("ID del padre: %ld\n", (long)getppid());
		while(1);
		return 0;
	}

	$ Process ID: 14135
	$ Parent process ID: 14125
	$ ps -l
  	$ UID   PID  PPID   F CPU PRI NI   SZ    RSS WCHAN   S   ADDR TTY      TIME CMD
  	$ 501 14125 14124   4006 0 31  0  2461020   1360 -    S   0 ttys000    0:00.03 -bash
  	$ 501 14135 14125   4006 0 23  0  2432748    528 -    R   0 ttys000    0:16.40 ./ej1

Creación de procesos:
	-El proceso hijo será un clon del padre, con la mayoria de atributos
	    heredados. Cada uno con un PID diferente, y con la diferencia de
	    que el proceso hijo retorna el PID en vez de 0(o -1 si hay error)

	pid_t fork(void);

	#include <stdio.h>
	#include <unistd.h>

	int main(void) { 
  		printf("Process %ld creates another process\n", (long)getpid());
  		fork();
  		printf("Process %ld with parent %ld\n",(long)getpid(),(long)getppid());
  		sleep(5);
  		return 0;
	}

	#include <stdio.h>
	#include <sys/types.h>
	#include <unistd.h>

	int main() {
     	pid_t val;
     	int var = 0;
     	printf("PID antes fork(): %d\n", (int) getpid());

     	if (val = fork()) {
       		printf("Padre PID: %d\n", (int) getpid());
       		var++;
     	}else {
       		printf("Hijo PID: %d\n", (int) getpid());
     	}
     	printf("Proceso [%d]-> var=%d\n", (int) getpid(), var);
     	return 0;
	}

	$ PID antes fork(): 14188
	$ Padre PID: 14188
	$ Proceso [14188]-> var=1
	$ Hijo PID: 14189
	$ Proceso [14189]-> var=0

	#include <stdio.h>
	#include <sys/types.h>
	#include <unistd.h>

	int main(void) {
		pid_t pid=fork();

		switch (pid) {
			case -1:
				printf("No se ha podido crear el proceso hijo\n");
				break;
			case 0:
				printf("Soy el hijo con PID %ld y PPID %ld\n", 
                  (long)getpid(), (long)getppid());
				break;
			default:
				printf("Soy el padre con PID %ld y mi hijo es %d\n", 
                  (long)getpid(), pid);
		}
		sleep(5);
		return 0;
	}

	$ Soy el padre con PID 14198 y mi hijo es 14199
	$ Soy el hijo con PID 14199 y PPID 14198

	#include <unistd.h>
	#include <stdio.h>
	#include <sys/types.h>
	#define NPROCESOS 4
	
	int main(void) {
    	pid_t pid;
    	int i;
    	for (i=0; i<NPROCESOS; i++) {
        	pid=fork();
        	if (pid != 0){
        		break;
        	}
        	printf("Soy el hijo con PID %ld con padre %ld\n",
              (long)getpid(), (long)getppid());
    	}
    	sleep(5);
    	return 0;
	}

	$ Soy el hijo con PID 14204 con padre 14203
	$ Soy el hijo con PID 14205 con padre 14204
	$ Soy el hijo con PID 14206 con padre 14205
	$ Soy el hijo con PID 14207 con padre 14206

	-Con la instrucción fork() se obtiene una copia de un proceso, para 
	poder cambiar el codigo de un proceso, se utiliza exec(), con 
	diferentes variantes:
		.-l -> Los argumentos se proporcionan por separado.
		.-v -> Los argumentos se proporcionan como un puntero.
		.-p -> Se busca la ruta del archivo proporcionado en el PATH
		.-e -> Se le proporciona el entorno(envp[]), diferente al del padre

	￼
	#include <unistd.h>
	int execl(const char *path, const char *arg0, ...,
        const char *argn, char * /*NULL*/);
	int execlp(const char *file, const char *arg, ... ,
        const char *argn, char * /*NULL*/);
	int execle(const char *path, const char *arg, ...,
		const char *argn, char * /*NULL*/, char * const envp[]);
	int execv(const char *path, char *const argv[]);
	int execvp(const char *file, char *const argv[]);
	int execve(const char *path, char *const argv[], char *const envp[]);

	-Si por algun motivo exec retorna al programa que lo ha invocado,
	   seré por que ha ocurrido algun problema en la ejecución, en caso
	   contrario, el programa que lo invoca detiene la ejecución.

    #include <stdio.h>
	#include <sys/types.h>
	#include <unistd.h>
	#include <stdlib.h>

	int main(void) {
		int status;
		pid_t pid = fork();

		char* argumentos [] = { "ls", "-l", 0 };
	
		switch (pid) {
			case -1:
				printf("No se ha podido crear el proceso hijo \n");
				break;
			case 0:
				printf("Soy el hijo con PID %ld y voy a listar el directorio\n",    
                  (long)getpid());
				if (execvp("ls",argumentos)==-1){
					printf("Error en exec\n");
					exit(0);
				}
			default:
				printf("Soy el padre con PID %ld y mi hijo es %d.\n", 
                  (long)getpid(), pid);	
		}
		return 0;
	}

	$ Soy el padre con PID 14257 y mi hijo es 14258.
	$ Soy el hijo con PID 14258 y voy a listar el directorio

Espera de procesos:
	-Esta instrucción se usa para que el proceso padre espere hasta que
	 finalize la ejecución del proceso hijo.

	 pid_t wait(int *status);
	 pid_t waitpid(pid_t pid, int *status, int options);

	-En el puntero a "status", se guarda el estado que devuelve el 
	 proceso hijo tras su ejecución.

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/wait.h>
	#include <errno.h>
	#include <unistd.h>

	int main(void) {
		int status;
		pid_t pid=fork();
	
		switch (pid) {
			case -1:
				printf("No se ha podido crear el proceso hijo \n");
				break;
			case 0:
				printf("Soy el hijo con PID %ld y mi padre es %ld\n", 
                  (long)getpid(), (long)getppid());
				sleep(20);
				printf("Ya he terminado\n");
				break;
			default:
				printf("Soy el padre con PID %ld y mi hijo es %d. Esperándolo ...\n", 
		            (long)getpid(), pid);
				if (wait(&status)!=-1){
					printf("Mi hijo ha terminado normalmente\n");
				}			
		}
		return 0;
	}

	$ Soy el padre con PID 14281 y mi hijo es 14282. Esperándolo ...
	$ Soy el hijo con PID 14282 y mi padre es 14281
	$ Ya he terminado
	$ Mi hijo ha terminado normalmente 

	#include <stdio.h>
	#include <sys/types.h>
	#include <sys/wait.h>
	#include <unistd.h>
	#include <stdlib.h>

	#define NPROCESOS 4

	int main(void) {
		pid_t pid[NPROCESOS];
		int i, status;

		for (i=0; i<NPROCESOS; i++) {
			pid[i]=fork();
			if (pid[i]==0){
				printf("Soy el hijo %ld con padre %ld. Mi sleep es de %d segundos\n", 
                       (long)getpid(), (long)getppid(), (i+1)*5);
				sleep((i+1)*5);
				exit(0);
			}
		}
		//Ahora a esperar al tercer hijo
		if (waitpid(pid[2],&status,0)==pid[2]){
			printf("Mi tercer hijo ya ha terminado \n");
		}
		return 0;
	}

	$ Soy el hijo 14270 con padre 14269. Mi sleep es de 5 segundos
	$ Soy el hijo 14271 con padre 14269. Mi sleep es de 10 segundos
	$ Soy el hijo 14272 con padre 14269. Mi sleep es de 15 segundos
	$ Soy el hijo 14273 con padre 14269. Mi sleep es de 20 segundos
	$ Mi tercer hijo ya ha terminado 

Terminación de procesos:
	-Un proceso termina si finaliza siguiendo su curso o si el proceso
	 padre realiza una llamada wait(); para la salida normal se invoca
	 a la instrucción void exit(int status), siendo status el valor de
	 retorno para indicar si ha finalizado correctamente.



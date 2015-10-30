1.Ejercicio: Ejecute la orden ps con los siguientes argumentos y observe las diferencias en la salida que generan.
 $ps
 	MacBook-Pro-de-Josep:fso_pract3 Josep$ ps
  	PID TTY           TIME CMD
	17030 ttys000    0:00.02 -bash
 $ps u 
 	MacBook-Pro-de-Josep:fso_pract3 Josep$ ps u
	USER    PID  %CPU %MEM      VSZ    RSS   TT  STAT STARTED      TIME COMMAND
	Josep 17030   0,0  0,0  2461020   1384 s000  S     3:59PM   0:00.02 -bash
 $ps -la 
 	MacBook-Pro-de-Josep:fso_pract3 Josep$ ps -la
  	UID   PID  PPID      F CPU PRI NI       SZ    RSS WCHAN   S  ADDR TTY         TIME CMD
    0 17029 15520   4106   0  31  0  2469496   2904 -      Us     0 ttys000  0:00.20 login -pf Josep
  501 17030 17029   4006   0  31  0  2461020   1384 -      S      0 ttys000  0:00.02 -bash
    0 17040 17030   4106   0  31  0  2433980    828 -      R+     0 ttys000  0:00.00 ps -la
 $ps f
 	MacBook-Pro-de-Josep:fso_pract3 Josep$ ps -f
  	UID   PID  PPID   C STIME   TTY           TIME CMD
  	501 17030 17029   0  3:59PM ttys000    0:00.03 -bash
 $ps aux
 	MacBook-Pro-de-Josep:fso_pract3 Josep$ ps aux
 	-> fso_pract3/ps aux.txt (archivo externo)

 2.Ejercicio: Lance la orden yes y finalice el proceso enviando la senñal SIGINT tecleando la combinacioón ctrl-c
	$ yes
	$ y
	.
	.
	.
	$y
	$^c == ctrl-c

Lance la orden yes en background, y averigüe el PID del proceso, a continuacion finalice el proceso yes utilizando la orden kill, 
despues compruebe con ps que ya no existe el proceso.
   	$ yes >/dev/null &
	$ ps –la
   	$ kill –SIGKILL PID

   	MacBook-Pro-de-Josep:fso_pract3 Josep$ ps -la
  UID   PID  PPID        F CPU PRI NI       SZ    RSS WCHAN     S             ADDR TTY           TIME CMD
    0 17029 15520     4106   0  31  0  2469496   2904 -      Us                  0 ttys000    0:00.20 login -pf Josep
  501 17030 17029     4006   0  31  0  2461020   1388 -      S                   0 ttys000    0:00.04 -bash
  501 17056 17030     4006   0  31  0  2432752    596 -      R                   0 ttys000    0:04.81 yes				<<<=====
    0 17057 17030     4106   0  31  0  2432952    820 -      R+                  0 ttys000    0:00.00 ps -la

    $ kill -SIGKILL 17056
    $ [1]+  Killed: 9 

3.Ejercicio: Ejecute la siguiente secuencia y compruebe el funcionamiento de killall

	$ yes >/dev/null &
   	$ yes >/dev/null &
   	$ yes >/dev/null &
	$ ps –la 
		MacBook-Pro-de-Josep:fso_pract3 Josep$ ps -la
  		UID   PID  PPID        F CPU PRI NI       SZ    RSS WCHAN     S             ADDR TTY           TIME CMD
    	  0 17058 15520     4106   0  31  0  2468972   2936 -      Ss                  0 ttys000    0:00.02 login -pf Josep
  	  	501 17059 17058     4006   0  31  0  2452828   1364 -      S                   0 ttys000    0:00.02 -bash
  	  	501 17065 17059     4006   0  20  0  2432752    596 -      R                   0 ttys000    0:06.81 yes
  		501 17066 17059     4006   0  20  0  2432752    588 -      R                   0 ttys000    0:04.74 yes
  		501 17067 17059     4006   0  20  0  2432752    588 -      R                   0 ttys000    0:03.46 yes
    	  0 17068 17059     4106   0  31  0  2432952    812 -      R+                  0 ttys000    0:00.00 ps -la
	$ killall yes
		[1]   Terminated: 15          yes > /dev/null
		[2]-  Terminated: 15          yes > /dev/null
		[3]+  Terminated: 15          yes > /dev/null
	$ ps -la
		UID   PID  PPID        F CPU PRI NI       SZ    RSS WCHAN     S             ADDR TTY           TIME CMD
    	  0 17058 15520     4106   0  31  0  2468972   2936 -      Ss                  0 ttys000    0:00.02 login -pf Josep
  		501 17059 17058     4006   0  31  0  2452828   1364 -      S                   0 ttys000    0:00.02 -bash
    	  0 17070 17059     4106   0  31  0  2432952    812 -      R+                  0 ttys000    0:00.00 ps -la

4. Ejercicio: En un terminal ejecuta el editor Kate en background. En el mismo terminal, ejecuta la orden ps para encontrar el 
PID del proceso que has iniciado. Utiliza la orden kill para terminar el proceso iniciado.

		$ kate >/dev/null/ &
        $ ps -la
        $ USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMM$
        $ jodoldar  3572  0.6  0.1  24828  4680 pts/1    Ss   09:45   0:00 /bin$
        $ jodoldar  3638  7.5  1.0 431556 41124 pts/1    Sl   09:45   0:00 kate
        $ jodoldar  3641  0.0  0.0  19384  1252 pts/1    R+   09:45   0:00 ps u
        $ kill -9 3638
        $ [1]+  Terminado (killed)      kate > /dev/null

5. Ejercicio: Ejecute la siguiente secuencia para comprobar el contenido de /proc. Verifique tambien que existe un directorio cuyo
 nombre es el pid de su Shell. Para conocer este pid ejecute la orden ps (sustituya $pid_bash por el PID de su shell):

	$ ps
	$ ls /proc
    $ ls /proc/$pid_bash
    $ more /proc/cpuinfo

    processor	: 0
	vendor_id	: AuthenticAMD
	cpu family	: 6
	model		: 2
	model name	: QEMU Virtual CPU version 0.12
	stepping	: 3
	microcode	: 0x1000065
	cpu MHz		: 2613.280
	cache size	: 512 KB
	fpu		: yes
	fpu_exception	: yes
	cpuid level	: 4
	wp		: yes
	flags		: fpu de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse sse2 syscall
 				  nx lm nopl pni cx16 x2apic hypervisor lahf_lm svm vmmcall
	bogomips	: 5226.56
	TLB size	: 1024 4K pages
	clflush size	: 64
	cache_alignment	: 64
	address sizes	: 40 bits physical, 48 bits virtual
	power management:

6.Ejercicio: Escriba una linea de ordenes que muestre por pantalla la frecuencia de los procesadores de su sistema. Se trata de
 acceder al fichero /proc/cpuinfo y utilizar el grep para seleccionar la linea adecuada.

 	$ ps
 	$ ls /proc/12337
 	$ more /proc/cpuinfo | grep -i mhz
 	$ cpu MHz		: 2613.280
	$ cpu MHz		: 2613.280

7.Ejercicio: Escriba una linea de ordenes que muestre por pantalla el tamaño de memoria cache de los procesadores de su sistema. Se trata de acceder al fichero /proc/cpuinfo y utilizar el grep para seleccionar la linea adecuada.

	$ ps
 	$ ls /proc/12337
 	$ more /proc/cpuinfo | grep -i cache\ size
	$ cache size	: 512 KB
	$ cache size	: 512 KB

8. Ejercicio: Cree el fichero anterior usando un editor de textos y llamelo “content”. A continuacion ejecutelo dandole
 los permisos adecuados:

 	$ chmod +x content
￼	$ ./content
	$ Number of System process is: 115

9.Ejercicio: Cree un archivo denominado arguments con el siguiente contenido

#!/bin/bash
# arguments
echo El numero de argumentos es: $#
echo La orden tecleada es: $0
echo El primer argumento: $1
echo El segundo argumento: $2
echo El tercero argumento: $3
￼
￼A continuacion ejecute argumentos con diferentes parametros

    $ ./arguments uno dos tres
    	El numero de argumentos es: 3
		La orden tecleada es: ./arguments
		El primer argumento: uno
		El segundo argumento: dos
		El tercero argumento: tres
    $ ./arguments  FSO TCO ESO
    	El numero de argumentos es: 3
		La orden tecleada es: ./arguments
		El primer argumento: FSO
		El segundo argumento: TCO
		El tercero argumento: ESO

10.Ejercicio. Edite un fichero llamado “my_process” con los siguientes comandos y ejecutelo:

#!/bin/bash
# my_process
process=$(ps u | grep $USER | wc -l)
if test $process -gt 2
then
echo "More than 2 user process”
else
echo "Few process”
fi

	$ More than 2 user process

11.Ejercicio: Realiza un script borrar_fichero, al que se le pasa un argumento con el nombre de un fichero. Este script comprobar
 que el fichero existe y que no es un directorio. En el caso de que no exista se sacara un mensaje de texto por pantalla, en el
 caso de que sea un fichero regular, se borrara este fichero y en el caso de que sea un directorio se sacara un mensaje indicado
 que es un directorio y que no se puede borrar.

#!/bin/bash
# borrar_fichero

if test -s $1
then
   if test -d $1
   then
   echo "Esto es un directorio"
   else
   rm $1
   echo "Archivo $1 eliminado"
   fi
else
echo "No existe el archivo o directorio"
fi

12. Ejercicio: Busca dentro del fichero /etc/passwd la cadena de caracteres root, e imprime por pantalla la linea donde se 
ha encontrado dicha cadena.

	$ jodoldar@shell-labs:~$ awk '/root/' /etc/passwd
	$ root:x:0:0:root:/root:/bin/bash

13. Ejercicio: Realice un Shell script denominado inf_process que tome como argumento el pid de un proceso e imprima por pantalla
 en formato de columnas el PID, PPID, ESTADO y COMANDO que ejecuta dicho proceso. Recuerde que esta informacion la puede adquirir
  de los ficheros /proc/$pid/status y /proc/$pid/cmdline. La salida debe ser del tipo:

           PID   PPID  ESTADO  COMANDO
           8900  8880  S       /bin/bash

        #!/bin/bash
		# inf_process

		echo -en "PID\tPPID\tESTADO\tCOMANDO\n"
		awk '/^Pid/ { printf $2}' /proc/$1/status
		echo -en "\t"
		awk '/PPid/ {printf $2}' /proc/$1/status
		echo -en "\t"
		awk '/State/ {printf $2}' /proc/$1/status
		echo -en "\t"
		awk '/Name/ {print $2}' /proc/$1/status

		$ jodoldar@shell-labs:~$ ./inf_process 1087
		$ PID	PPID	ESTADO	COMANDO
		$ 1087	1086	S	bash

14. Ejercicio: Realice un Shell script denominado system_process que proporcione al scripts inf_process todos los PID de los
 procesos del sistema, para que imprima su informacion.

 	#!/bin/bash
	# system_process

	for STATUS_FILE in /proc/[0-9]*/status; 
	do
		STATUS_FILE=${STATUS_FILE:6}
		len=${#STATUS_FILE}
		./inf_process ${STATUS_FILE:0:$len-7}
	done































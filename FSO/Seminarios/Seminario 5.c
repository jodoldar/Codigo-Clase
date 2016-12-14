SEMINARIO 5: Programación con hilos POSIX

Procesos POSIX:
	Se crea un hilo que ejecuta la función main(), de la que pueden salir más hilos
		para ejecutar otras funciones.
	Todos los hilos de un proceso se situan al mismo nivel, en relacion de 'hermanos',
		de forma diferente a los procesos 'padre-hijo'.
	Se comparten las variables y los recursos globales del proceso, pero se pueden
		crear copias privadas de parametros y variables locales.

	Llamadas basicas:
		-<code>pthread_create</code> -> Crea un hilo para una función
		-<code>pthread_attr_init</code> -> Inicializa un objeto atributo de hilo
		-<code>pthread_attr_destroy</code> -> Destruye el objeto atributo de hilo
		-<code>pthread_join</code> -> Espera la terminación del hilo especificado
		-<code>pthread_exit</code> -> Termina la ejecución del hilo
		-<code>pthread_self</code> -> Devuelve la identidad del hilo que lo llama
		-<code>pthread_attr_setdetachstate</code> -> Modifica el atributo de 
				desconectado
		-<code>pthread_attr_getdetachstate</code> -> Consulta el atributo de 
				desconectado

Creación de hilos:
	pthread_create():
		Crea un nuevo hilo preparado, el hilo creado y el existente competiran por
		la CPU segun marque el sistema. Puede ser invocado por cualquier hilo del
		proceso.

		#include <pthread.h>
		int pthread_create(pthread_t *thread, const pthread_attr_t *attr,
			void *(*start_routine)(void*),void *arg);

	Argumentos: 
		attr -> atributo con las caracteristicas del nuevo hilo
		start_routine -> función que ejecutará el hilo
		arg -> puntero a los parametros iniciales del hilo
		thread -> identificador del hilo creado

	Atributos:
		Siendo attr el atributo a ser creado/destruido:

		#include <pthread.h>

		int pthread_attr_init(pthread_attr_t *attr);
		int pthread_attr_destroy(pthread_attr_t *attr);

		Para modificar los atributos:

		#include <pthread.h>

		int pthread_attr_setdetachstate(pthread_attr_t *attr, 
			int detachstate);
		int pthread_attr_getdetachstate(const pthread_attr_t *attr, 
			int *detachstate);

		Donde detachstate indica si otro hilo podrá esperar a la finalización de 
		este, usando: PTHREAD_CREATE_JOINABLE, PTHREAD_CREATE_DETACHED

Finalización y espera de hilos:
	Finalización de hilos POSIX:
		Un hilo finaliza su ejecución cuando o bien se ejecuta su ultima instrucción
		o bien cuando se invoca a pthread_exit, devolviendo un entero con la 
		información del estado del hilo.

		#include <pthread.h>

		int pthread_exit(void *exit_status);

	Espera de hilos POSIX:
		Siempre que un hilo haya sido creado con PTHREAD_CREATE_JOINABLE, se puede
		usar pthread_join para hacer que espera la finalización, recibiendo el 
		valor de pthread_exit.

		#include <pthread.h>

		int pthread_join(pthread_t thread, void **exit_status);

Identificación de hilos:
	La instrucción pthread_self devulve el identificador del hilo, y pthread_equal 
	sirve para comparar dos identifadores, devulve 0 si son iguales o un valor
	distinto si no lo son.

	#include <pthread.h>

	pthread_t pthread_self(void);
	int pthread_equal(pthread_t th1, pthread_t th2);




	/* fichero fuente.c */
	#include <stdio.h>
	main(){
		printf("Hola mundo!\n");
	}

-Para los includes, si se usan < >, el archivo se busca en el directorio 
 del sistema, mientras que si se usa " ", el archivo debe de ser creado 
 por el usuario.

	/* fichero cabecera.h */
	#define MI_CADENA "Hola Mundo"
	#define PI 3.1415925
	#define MAX(A,B) ((A)>(B)?(A):(B))

-Convenio de escritura en C, todo en minusculas para el codigo excepto la
 parte del preprocesador, que se hará en mayusculas

 	typedef int numero;
 	numero	a,b,c;

 -Definición de tipos, se le asigna a una palabra asignandole las 
  propiedades del tipo de variable asociada.

 -Todas las variables deben de ser declaradas al principio del codigo, 
  antes de cualquier otro trozo de codigo.

  	printf("Texto: %s, Entero: %d, Float: %f\n", "rojo", 5, 3.14);
  	scanf("%f %c %d %ld %s",&Float,&Letra,&Entero,&Entero_largo,&Cadena);

 -Las instrucciones printf y scanf sirven para imprimir por pantalla y
  recibir lo escrito respectivamente. Los %_ se refieren al tipo de 
  elemento a esperar para la instruccion.

-Ejemplo que calcula el cuadrado de un número introducido:

 	#include <stdio.h>
 	main(){
 		int numero;
 		int cuadrado;
 		printf("Introduzca un número: ");
 		scanf("%d", &numero);
 		cuadrado = numero * numero;
 		printf("El cuadrado de %d es %d\n", numero, cuadrado);
 	}  

-Para un bucle for, es necesario declarar las variables antes del bucle.

 	int i;
 	for(i=0;i<10;i++){
 		printf("%d\n", i);
 	} 

-Ejemplos de codigo:
	￼
	#include <stdio.h>
	main() {
		int N;
   		int suma = 0; /*leer numero N */
   		printf("N: ");
   		scanf("%d", &N);
   		while (N > 0) {
    		suma = suma + N;
    		N = N - 1; /* equivale a N-- */
   		}
   		printf("1 + 2 +...+ N = %d\n", suma);
   	}

   	#include <stdio.h>
	main() {
  		int N, suma, j;
  		do {
  	 		/* leer el numero N */
   			printf("Introduzca N: ");
   			scanf("%d", &N);
   			suma = 0;
   			for (j = 0; j <= N; j++)
      			/* bucle anidado */
    			suma = suma + j;
   				printf("1 + 2 + ... + N = %d\n", suma);
  		} while (N > 0);/* fin del bucle do */
	}

-Para la copia de vectores, utilizar la instruccion memcpy(v1,v2,tam) con 
 la cual se copia en v1 el vector v2, de tamaño tam.

-Puntero: variable que contiene la direccion de otro objeto: tipo *nombre
 	- Operador &: devuelve la direccion de la variable.
 	- Operador *: indirección, devuelve el dato apuntado por el puntero.

 	#include <stdio.h>
	main() {  
		int x;   /* x variable entera */
  		int y;   /* y variable entera */
		int *px; /* puntero a entero */
		x=5;
		px=&x;
		y=*px;/* px contiene direccion de x */
  		printf("x = %d\n", x);
	}

 Los punteros permiten aplicar operaciones sobre ellos, como suma(+),
 y resta(-), e incremento(++) y decremento(--).

 Puntero generico "void", que apunta a cualquier tipo de dato.
 Puntero nulo "NULL", de valor 0, se utiliza para indicar errores o 
 	que no apunta a ninguna direccion.

-Cadenas: array de caracteres, siempre finalizan con "\0". Al igual que
 un vector, se pueden copiar cadenas con la instruccion strcpy(cad1,cad2)
 donde se copia en cad2 la cadena cad1.
	#include <stdio.h>
	#define TAM_CADENA 80
	main() {
  		char cadena[TAM_CADENA];
  		printf("Introduzca una cadena: ");
  		scanf("%s", cadena); // No requiere &
  		printf("Cadena %s\n", cadena);
	}
￼
	char subject[5] = “FSO";
	char name[] = “J. Perez";
	char name2[20];
	strcpy(name2, name); 

-Estructuras: conjunto de datos incluidos dentro de un bloque
 independiente, tratable como un objeto.
￼
	//definicion de una estructura
	struct CD {
  		char titulo[100];
  		char artista[50];
  		int num_canciones;
  		int anyo;
  		float precio;
	};
	//declaracion de una variable
	struct CD cd1;
	//accediendo a la estructura
	strcpy(cd1.titulo, "La Boheme");
	strcpy(cd1.artista, "Puccini");
	cd1.num_canciones = 2;
	c1.anyo = 2006;

	struct CD *pcd;
	pcd = &cd1;
	pcd ->precio = 16.5;

 Para acceder mediante puntero a un valor de una estructura se utiliza 
 el simbolo "->".

-Funciones: para porder usar una funcion en C, debe de ser declarada antes
 ser usada en el programa. En el paso de parametros, se pueden pasar, tanto 
 por valor, como por referencia.
 	Valor: Declaracion: nombre_funcion(tipo arg, tipoN argN);
 		   Definición:  tipo_retorno nombre_funcion(tipo arg, tipoN argN)
 	Referencia: Declaracion:  nombre_funcion(&arg1,..., tipoN argN)
 				Definición:   tipo_retorno nombre_funcion(tipo1 *arg1, tipoN argN)

	#include <stdio.h>
	#include <math.h>
	void hipotenusa(float a, float b, float *h) {
		*h = sqrt(pow(a,2) + pow(b, 2));
	}
	void leer (float *a, float *b) {
  		printf("Dame valores a y b:\n");
  		scanf("%f %f", a, b);
  		//scanf("%f %f", *&a, *&b);
	}
	
	main() {
  		float a, b, h;
  		leer (&a,&b);
  		hipotenusa(a,b,&h);
  		printf("La hipotenusa es %f\n", h);
	}

-Parametros en linea de comandos, mediante los parametros 
	de int main(int argc, char *argv[]).

	#include <stdio.h>
	int main(int argc, char *argv[]){
		int sum1, sum2;
		if (argc==3){
			sum1 = atoi(argv[1]);
			sum2 = atoi(argv[2]);
			printf("La suma es %d\n", sum1+sum2);
		} else{
			printf("Uso orden: %s arg1 arg2\n", argv[0]);
		}
	}
		

/*****************************************************************************/
/**  Definiciones de las constantes y estructuras auxiliares usadas en      **/
/**  la libreria <<libtds>>, asi como el perfil de las funciones de         **/
/**  manipulación de la  TDS.                                               **/
/**                     Jose Miguel Benedi, 2017-2018 <jbenedi@dsic.upv.es> **/
/*****************************************************************************/
/*****************************************************************************/
#ifndef _LIBTDS_H
#define _LIBTDS_H

/************************* Constantes para los tipos en la Tabla de Simbolos */
#define T_VACIO       0
#define T_ENTERO      1
#define T_LOGICO      2
#define T_ARRAY       3
#define T_ERROR       4
typedef struct simb /******************************** Estructura para la TDS */
{
  int   tipo;            /* Tipo del objeto                                  */
  int   desp;            /* Desplazamiento relativo en el segmento variables */
  int   ref;             /* Campo de referencia de usos multiples            */
} SIMB;
typedef struct dim  /* Estructura para la informacion obtenida de la TDArray */
{
  int   telem;                                      /* Tipo de los elementos */
  int   nelem;                                      /* Numero de elementos   */
} DIM;
/*************************** Variables globales de uso en todo el compilador */
int dvar;                     /* Desplazamiento en el Segmento de Variables  */

/************************************* Operaciones para la gestion de la TDS */
int insertarTDS(char *nom, int tipo, int desp, int ref) ;
/* Inserta en la TDS toda la informacion asociada con un simbolo de: nombre 
   "nom"; tipo "tipo"; desplazamiento relativo en el segmento de variables
   "desp" y referencia ``ref'' a una posible subtabla de vectores (-1 si no 
   referencia a otras subtablas). Si el identificador ya existe 
   devuelve el valor "FALSE=0" ("TRUE=1" en caso contrario).                 */
int insertaTDArray (int telem, int nelem) ;
/* Inserta en la Tabla de Arrays la informacion de un array cuyos elementos 
   son de tipo "telem" y el numero de elementos es "nelem". Devuelve su 
   referencia en la Tabla de Arrays.                                         */
SIMB obtenerTDS (char *nom) ;
/* Obtiene toda la informacion asociada con un objeto de nombre "nom" y la
   devuelve en una estructura de tipo "SIMB" (ver "libtds.h"). Si el objeto 
   no está declarado, en el campo "tipo" devuelve "T_ERROR".                 */
DIM obtenerInfoArray (int ref) ;
/* Devuelve toda la informacion asociada con un array referenciado por "ref" 
   en la Tabla de Arrays.                                                    */
void mostrarTDS () ;
/* Muestra toda la informacion de la TDS.                                    */

#endif  /* _LIBTDS_H */
/*****************************************************************************/

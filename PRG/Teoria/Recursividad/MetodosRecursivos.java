package Recursividad;

/**
 * Clase con metodos recursivos.
 * @author Josep Dols
 */

public class MetodosRecursivos {
    
    /**
     * Metodo que calcula el factorial de un numero.
     * @param n Numero sobre el que aplicar la operacion.
     * @return Valor del factorial buscado.
     */
    public static int factorial(int n){
        if(n<1){
            return 1;
        }else{
            return n*factorial(n-1);
        }
    }
    
    /**
     * Metodo que calcula la potencia n-esima de un número.
     * @param a Base del número a calcular.
     * @param n Exponente del número a calcular.
     * @return Valor de la potencia calculada.
     */
    public static double potencia(double a, int n){
        if(n<=1){
            return 1;
        }else{
            return a*potencia(a,n-1);
        }
    }
    
    /**
     * Metodo que obtiene el resto de una división.
     * @param a Dividendo de la operación.
     * @param b Divisor de la operación.
     * @return Valor del resto.
     */
    public static int resto(int a,int b){
        if(a<b){
            return a;
        }else{
            return resto(a-b,b);
        }
    }
    
    /**
     * Metodo que calcula el cociente de dos numeros.
     * @param a Dividendo de la operación.
     * @param b Divisor de la operación.
     * @return Valor resultante de la operación.
     */
    public static int cociente(int a, int b){
        if(a<b){
            return 0;
        }else{
            return 1+cociente(a-b,b);
        }
    }
    
    /**
     * Metodo que calcula el maximo comun divisor de dos numeros.
     * @param a Primer valor de la operación.
     * @param b Segundo valor de la operación.
     * @return Valor resultante de la operación.
     */
    public static int mcd(int a, int b){
        if(a==b){
            return b;
        }else if(a>b){
            return mcd(a-b,b);
        }else{
            return mcd(a,b-a);
        }
    }
    
    /**
     * Metodo que realiza la division euclidea de a y b.
     * @param a Primer parametro de la division.
     * @param b Segundo parametro de la división.
     * @return Valor resultante de la division.
     */
    public static int euclides(int a, int b){
        if (a%b==0){
            return b;
        }else{
            return euclides(b,a%b);
        }
    }
    
    /**
     * Metodo que calcula el numero n-esimo de la secuencia de Fibonacci.
     * @param n Numero n-esimo a buscar.
     * @return Valor del numero n-esimo de Fibonacci.
     */
    public static int fibonacci(int n){
        if(n<=1){
            return n;
        }else{
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }
    
    /**
     * Metodos que suman los elementos de un array
     * @param a Array sobre el que aplicar la operación.
     * @param i Posición desde la que se empieza la operación.
     * @return Valor de la suma realizada.
     */
    //CASO 1
    public static double sumaArrayPos(double[] a, int i){
        if(a.length == i){
            return 0;
        }else{
            return a[i] + sumaArrayPos(a,i+1);
        }
    }
    //CASO 2
    public static double sumaArrayNeg(double[] a,int i){
        if(0==i){
            return 0;
        }else{
            return a[i] + sumaArrayNeg(a,i-1);
        }
    }
    
    /**
     * Conjunto de metodos que suman los elementos de un array.
     * @param a Array sobre el que ejecutar la operación.
     * @param inicio Posición desde la que empezar la operación (caso 1).
     * @param fin Posicion desde la que empezar la operacion (caso 2).
     * @return Valor total de la suma.
     */
    //CASO 1
    public static int ejercicio22aA(int[] a, int inicio){
        if(inicio==a.length) return 0;
        else return a[inicio]+ejercicio22aA(a,inicio+1);
    }
    //CASO 2
    public static int ejercicio22aD(int[] a, int fin){
        if(fin==-1) return 0;
        else return a[fin]+ejercicio22aD(a,fin-1);
    }
    
    /**
     * Ejercicio numero 22, apartado b
     * @since a.length > 0
     * @param a Array de enteros donde se va a aplicar la busqueda
     * @param inicio Posicion donde se inicia la busqueda
     * @return Posicion donde se encuentra el máximo(o mínimo)
     */
    public static int ejercicio22bA(int[] a, int inicio){
        if(inicio==a.length-1){
            return inicio;
        }else{
            int aux = ejercicio22bA(a, inicio+1);
            if(a[inicio]>a[aux]){
                return inicio;
            }else{
                return aux;
            }
        }
    }
    
    /**
     * Obtiene la posicion del maximo (minimo) del array DESCENDENTEMENTE
     * @since a.length>0 && 0<=inicio<=a.length
     * @param a Array para buscar la posicion
     * @param fin Posicion desde la que buscar
     * @return Posicion del maximo valor del array
     */
    public static int ejercicio22bD(int[] a, int fin){
        if(fin==0) return fin;
        else{
            int aux = ejercicio22bD(a, fin-1);
            if(a[fin]>a[aux]) return fin;
            else return aux;
        }
    }    
    
    /**
     * Dado un entero x, contar cuantas veces aparece en el array.
     * @since 0 <= x <= a.length... La llamada inicial seria ejercicio22c(a,x,0)
     * @param a Array en el que aplicar la busqueda
     * @param x Numero a buscar en el array
     * @param pos Posicion donde se empieza la busqueda
     * @return Veces que se repite un numero en el array
     */
    public static int ejercicio22c(int[] a, int x, int pos){
        int aux=0;
        if(pos<a.length){
            if(a[pos]==x) aux++;
            aux+=ejercicio22c(a,x, pos+1);
        }
        return aux;
    } 
    
    /**
     * Dadas dos posiciones del array duplique el valor de los elementos del 
     * array situados entre dichas posiciones.
     * @since 0≤izq≤der≤v.length-1
     * @param a Array sobre el que aplicar el cambio
     * @param izq Posicion de la izquierda donde aplicar el cambio
     * @param der Posicion de la derecha donde aplicar el cambio
     */
    public static void ejercicio22h(int[] a, int izq, int der){
        if(izq<=der){
            //Se recorta el array por los dos lados.
            ejercicio22h(a,izq+1,der-1);
            a[izq]*=2;
            if(izq!=der){
                a[der]*=2;
            }
        }
    }
    
    /**
     * Dadas dos posiciones del array, invierta todos los elementos del array 
     * situados entre dichas posiciones, esto es, al finalizar la ejecución del
     * método el array contendrá en su posición izq el elemento que inicialmente
     * ocupaba la posición der, en su posición izq+1 el elemento que inicialmente
     * ocupaba la posición der-1 y así sucesivamente.
     * @param a Array al que aplicar la operacion.
     * @param izq Posicion de la izquierda donde comienza la operacion.
     * @param der Posicion de la derecha donde comienza la operacion.
     * @since 0≤izq≤der≤a.length-1
     */
    public static void ejercicio22g(int[] a, int izq, int der){
        if(izq<der){
            ejercicio22g(a, izq+1, der-1);
            int aux = a[izq];
            a[izq]=a[der];
            a[der]=aux;
        }
    }
    
    /**
     * Determine la posición del primer (último) elemento no nulo del array.
     * @param a Array en el que aplicar la busqueda
     * @param pos Posicion desde la que buscar
     * @return Posicion donde esta el primer elemento no nulo
     */
    public static int ejercicio22e(int[] a, int pos){
        int res;
        if(pos==a.length) res = -1; //Fuera del array
        else{
            if(a[pos]!=0) res = pos; //Elemento encontrado
            else res = ejercicio22e(a,pos+1); //Aún no se ha encontrado
        }
        return res;
    }
    
    /**
     *Compruebe si el array está ordenado ascendentemente.
     * @param a Array donde aplicar la busqueda
     * @param pos Posicion desde la que iniciar la busqueda
     * @return true - si esta ordenado; false - si no esta ordenado
     *  
     */
    public static boolean ejercicio22d(int[] a, int pos){
        boolean res = true;
        if(pos>0){
            res = (a[pos]>=a[pos-1]) && (ejercicio22d(a,pos-1));
        }
        return res;
    }
    
    /**
     *  Ejecuta una busqueda binaria en el array indicado.
     * @param a Array de String donde aplicar la busqueda.
     * @param dato String a buscar en el array.
     * @param ini Posición desde la que realizar la busqueda.
     * @param fin Posición hasta la que realizar la busqueda.
     * @return Posición en la que se encuentra el String a buscar.
     */
    public static int buscaBinR(String[] a, String dato, int ini, int fin){
        int respuesta;
        if(ini>fin) respuesta = -1;
        else{
            int mitad=(ini+fin)/2;
            int comp=a[mitad].compareTo(dato);
            if(comp==0) respuesta = mitad;
            else if (comp > 0) respuesta=buscaBinR(a,dato,mitad+1,fin);
            else respuesta = buscaBinR(a,dato,mitad,fin-1);
        }
        return respuesta;
    }
    
    /**
     * Obtener la suma de todos los elementos del array ASCENDENTEMENTE
     * @param a Array al que aplicar la suma
     * @param pos Posicion de inicio de la busqueda
     * @return Suma de los elementos
     */
    public static int sumaRecAsc(int[] a, int pos){
        if (pos==a.length) return 0;
        else return a[pos] + sumaRecAsc(a, pos+1);
    }
    
    /**
     * Ejercicio 10: Escribir un metodo de clase <b>recursivo</b> que <b>muestre</b>
     * en <b>orden inverso</b> los digitos que componen un numero natural n dado.
     * @since n >= 0 
     * @param n Numero al que aplicar la operacion
     */
    public static void ejercicio10(int n){
        if(n>10){
            System.out.print(n);
        }else{
            System.out.print(n%10);
            ejercicio10(n/10);
        }
    }
    
    /**
     * Ejercicio 10 Bis: Escribir un metodo de clase <b>recursivo</b> que <b>devuelva</b>
     * el numero resultante de situar en <b>orden inverso</b> los digitos que 
     * componen un numero natural n dado.
     * @since n >= 0 
     * @param n Numero al que aplicar la operacion
     * @return numero resultante de la operacion
     */
    public static int ejercicio10B(int n){
        if(n<10){
            return n;
        }else{
            int aux = (int)Math.log10(n);
            return (n%10)*(int)Math.pow(10,aux) + ejercicio10B(n/10);
        }
    }
    
    /**
     * Escribir un método de clase recursivo que devuelva el valor binario 
     * (representado como un entero) de un número natural n dado. 
     * @see si n=5 el método devuelve 101, pero si n=31 el método devuelve 11111.
     * @since n ≥ 0
     * @return El numero introducido en codificacion binaria.
     * @param n Numero al que aplicar la operacion.
     */
    public static int ejercicio11(int n){
        if(n<=1) return n;
        else return n%2 + ejercicio11(n/2)*10;
    }
    
    /**
     * Escribir un método de clase recursivo que, datos dos String s1 y s2 y sin
     * hacer uso de los métodos definidos en la clase String que resuelven el mismo problema, determine:
     *    a)si s2 es prefijo de s1.
     *    b)si s2 es sufijo de s1.
     *    c)si s2 es una subcadena de s1.
     * @param s1 Primer String a comparar
     * @param s2 Segundo String a comparar
     * @return <b>true</b>-si se cumplen las condiciones, en caso contrario, <b>false</b>
     */
    public static boolean ejercicio13a(String s1,String s2){
        if(s2.length()>s1.length()) return false;       //CASO BASE 1
        else if(s2.length()==0) return true;            //CASO BASE 2
        else return s2.charAt(0)==s1.charAt(0) && ejercicio13a(s1.substring(1),s2.substring(1));
    }
    public static boolean ejercicio13b(String s1, String s2){
        if(s2.length()>s1.length()) return false;       //CASO BASE 1
        else if (s2.length()==0) return true;           //CASO BASE 2
        else return s2.charAt(s2.length()-1)==s1.charAt(s1.length()-1) &&
                ejercicio13b(s1.substring(0,s1.length()-1),s2.substring(0,s2.length()-1));
    }
    public static boolean ejercicio13c(String s1, String s2){
        if(s2.length()>s1.length()) return false;       //CASO BASE 1
        else return ejercicio13a(s1,s2) || ejercicio13c(s1.substring(1),s2);
    }
}
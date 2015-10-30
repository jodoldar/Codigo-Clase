package Estructuras; 

/**
 * Metodos para la clase NodoInt
 * @author Josep Dols
 */
public class MetodosEstructuras {
    /** Metodo para saturar los numeros mayores al introducido
     * @param sec Nodo desde el que empezar la busqueda
     * @param maximo Numero sobre el que aplicar la saturacion
     */
    public static void saturar(NodoInt sec,int maximo) { 
        NodoInt aux = sec;
        while (aux!=null) {
            if (aux.dato>maximo){
                aux.dato = maximo;
            } 
            aux = aux.siguiente;
        }
    }
    
    /** Metodo para cambiar el signo a un entero dado
     * @param sec Nodo desde el que empezar la busqueda
     * @param d Entero al que aplicar el cambio de signo
     */
    public static void cambiarSigno(NodoInt sec,int d){
        //Búsqueda del primer nodo cuyo dato sea d:
        NodoInt aux = sec;
        while (aux!=null && aux.dato!=d){
            aux = aux.siguiente;
        }
        //Si la búsqueda termina con éxito, se cambia el signo del dato:
        if (aux!=null) aux.dato = -d;
    }
    
    /** Metodo para buscar un entero d 
     * @param sec Nodo desde el que empezar la busqueda
     * @param d Entero a buscar
     * @return Posicion donde se encuentra el entero a buscar
     */
    public static int buscar(NodoInt sec,int d){
        NodoInt aux = sec;
        int pos = 0;
        while(aux!=null){
            if(aux.dato==d) return pos;
            else{ 
                pos++;
                aux = aux.siguiente;
            }
        }
        return -1;
    }
    
    /** Metodo para insertar un entero al final de una estructura de enteros.
     *      *Insertar aqui parrafada*
     * @param sec NodoInt para tomar como punto inicial.
     * @param d Entero a insertar en la estructura.
     * @return NodoInt que contiene el elemento introducido.
     */
    public static NodoInt insertarAlFinal(NodoInt sec, int d){
        NodoInt aux = sec, ant = null;
        while(aux!=null){
            ant = aux;
            aux = aux.siguiente;
        }
        if(ant==null){  //LA SECUENCIA ESTA VACIA
            return new NodoInt(d);
        }else{          //ANT ESTA EN EL ULTIMO ELEMENTO
            ant.siguiente = new NodoInt(d);
            return sec;
        }
    }
    
    /** Metodo que inserta un entero al comienzo de una secuencia.
     * @param sec NodoInt donde se referencia el primer elemento.
     * @param d Entero a insertar en la estructura.
     * @return NodoInt con el entero insertado.
     */
    public static NodoInt insertarAlPrincipio(NodoInt sec, int d){
        return new NodoInt(d,sec);
    }
    
    /** Metodo que inserta un entero en una secuencia ordenada.
     * PRECONDICION, previamente la secuencia esta ordenada
     * @param sec NodoInt a partir del cual se hace la busqueda para la inserción.
     * @param d Entero a insertar en la estructura.
     * @return NodoInt resultante de la inserción.
     */
    public static NodoInt insertarOrdenado(NodoInt sec, int d){
        NodoInt aux = sec, ant = null, nuevo = new NodoInt(d);
        while(aux!=null && aux.dato<=d){
            ant = aux;
            aux = aux.siguiente;
        }
        nuevo.siguiente = aux;
        if(ant==null){      //O LA SECUENCIA ESTA VACIA O EL ELEMENTO YA ES MAYOR
            sec=nuevo;      
        }else{              //AUX SE HA SALIDO O SE HA PASADO Y APUNTA A UN ELEMENTO MAYOR
            ant.siguiente = nuevo;
        }
        return sec;
    }
    
    /**
     * Implementar un metodo que: dadas una secuencia de enteros y dos valores <b>d</b> y <b>k</b> 
     * inserte el elemento d en la posicion k. Si k es mayor o igual que el maximo numero de 
     * elementos lo insertara al final, si es menor o igual a 0 lo insertará al principio.
     * @param sec NodoInt a partir del cual se hace la busqueda para la inserción.
     * @param d Entero a insertar en la estructura.
     * @param k Posición en la que insertar el entero.
     * @return NodoInt donde se ha insertado el entero.
     */
    public static NodoInt insertaEnPosicion(NodoInt sec, int d, int k){
        NodoInt aux = sec, ant = null, nuevo = new NodoInt(d);
        int cont = 0;
        while(aux!=null && cont<=k){
            cont++;
            ant = aux;
            aux = aux.siguiente;
        }
        nuevo.siguiente = aux;
        if(ant==null){
            sec = nuevo;
        }else{
            ant.siguiente = nuevo;
        }
        return sec;
    }
    /**
     * Implementar un metodo que: dada una secuencia la devuelva invertida. ej: 1->2->3 pasaria a
     * ser 3->2->1 (La inversa de la secuencia vacia es la secuencia vacia).
     * @param sec NodoInt a partir del cual se hace la busqueda para la inserción.
     * @return Cabecera de la secuencia invertida de NodoInt
     */
    public static NodoInt invierteSecuencia(NodoInt sec){
        NodoInt nuevaSec = null;
        if(sec==null || sec.siguiente==null){
            nuevaSec = sec;
        }else{
            while(sec!=null){
                nuevaSec = new NodoInt(sec.dato,nuevaSec);
                sec = sec.siguiente; //NO perdemos la cabeza porque ha sido transferida al otro nodo
            }
        }
        return nuevaSec;
    }
}

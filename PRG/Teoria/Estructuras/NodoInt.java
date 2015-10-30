package Estructuras; 

import java.io.Serializable;

/**
 * Clase de metodos de Estructuras de datos
 * @author Josep Dols
 */
public class NodoInt implements Serializable{
    /**Numero que contiene el objeto*/
    int dato;
    /**Elemento NodoInt que referencia al siguiente objeto NodoInt*/
    NodoInt siguiente;
    
    /**Constructor A
     * @param d Entero con el que se creara el nuevo NodoInt, sin referenciar a
     * otros NodoInt.
     */
    NodoInt(int d){
        dato = d;
        siguiente = null;
    }
    
    /**Constructor B
     * @param d Entero con el que se creara el nuevo NodoInt.
     * @param s NodoInt con es que se creara el objeto NodoInt que referencia a 
     * otro NodoInt.
     */
    NodoInt(int d, NodoInt s){
        dato = d;
        siguiente = s;
    }
}

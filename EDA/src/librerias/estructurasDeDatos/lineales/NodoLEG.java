

package librerias.estructurasDeDatos.lineales;

/**
 *
 * @author Josep
 */
public class NodoLEG<E> {
    E dato;
    NodoLEG<E> siguiente;
    
    NodoLEG(E d){
        this.dato = d;
        this.siguiente = null;
    }
    
    NodoLEG(E d, NodoLEG s){
        this.dato = d;
        this.siguiente = s;
    }
}

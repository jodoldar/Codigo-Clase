package librerias.estructurasDeDatos.lineales;

/**
 * 
 * @author Josep Dols
 */
public class NodoLEG<E> {
    protected E dato;
    protected NodoLEG<E> siguiente;
    
    public NodoLEG(E d){
        this.dato = d;
        this.siguiente = null;
    }
    
    public NodoLEG(E d, NodoLEG s){
        this.dato = d;
        this.siguiente = s;
    }
    
    public E getDato(){
        return this.dato;
    }
    
    public NodoLEG getSiguiente(){
        return this.siguiente;
    }
}

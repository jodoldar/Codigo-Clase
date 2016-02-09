package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
/**
 *
 * @author Josep Dols
 */
public class LEGPila<E> implements Pila<E>{
    protected NodoLEG<E> tope;
    
    // Constructor
    public LEGPila(){
        tope = null;
    }
    
    // Inserta x en el tope de la Pila public void apilar(E x) {
    @Override
    public void apilar(E x){
        tope = new NodoLEG<E>(x, tope);
    }
    
    // SII !esVacia(): elimina el dato del tope de la Pila y lo devuelve
    @Override
    public E desapilar() { 
        E dato = tope.dato;
        tope = tope.siguiente;
        return dato;
    }
    
    // SII !esVacia(): obtiene el dato que ocupa el tope de la Pila
    @Override
    public E tope() {
        return tope.dato;
    }
    
    // Comprueba si la Pila está o no vacía
    @Override
    public boolean esVacia(){
        return (tope==null);
    }
    
    @Override
    public int talla(){
        NodoLEG<E> aux = tope;
        int aux2 = 0;
        while(aux != null){
            aux2++;
            aux = aux.siguiente;
        }
        return aux2;
    }
}

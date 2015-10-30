package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase objeto PilaIntEnlazada, para contener una estructura de enteros.
 * @author Josep Dols
 */
public class PilaIntEnlazada {
    protected NodoInt cima;
    protected int numElem;
    
    public PilaIntEnlazada(){
       cima = null;
       numElem = 0;
    }
    public boolean esVacia(){
        return numElem==0;
    }
    public int talla(){
        return numElem;
    }
    public int cima(){
        if(numElem==0){
            throw new NoSuchElementException("Pila vacia");
        }
        return cima.dato;
    }
    public void apilar(int elemento){
        cima = new NodoInt(elemento,cima);
        numElem++;
    }   
    public int desapilar(){
        if(numElem==0){
            throw new NoSuchElementException("Pila vacia");
        }else{
            int dato = cima.dato;
            numElem--;
            cima = cima.siguiente;
            return dato;
        }
    }
}

package Estructuras;

import java.util.NoSuchElementException;

/**
 *  Implementacion de una cola de enteros enlazada.
 * @author Josep Dols
 */
public class ColaIntEnlazada {
    protected int numElem;
    protected NodoInt primero;
    protected NodoInt ultimo;
    
    public ColaIntEnlazada(){
        primero = ultimo = null;
        numElem = 0;
    }
    
    public boolean esVacia(){
        return numElem==0;
    }
    public int talla(){
        return numElem;
    }
    public int primero() throws NoSuchElementException{
        if(numElem==0){
            throw new NoSuchElementException("Cola vacia");
        }
        return primero.dato;
    }
    public void encolar(int elemento){
        /*if(numElem==0){
            primero = ultimo= new NodoInt(elemento);
        }else{
            ultimo.siguiente = new NodoInt(elemento);
            ultimo = ultimo.siguiente;
        }*/
        NodoInt nuevo = new NodoInt(elemento);
        if(ultimo!=null){
            ultimo.siguiente = nuevo;
        }else{
            primero = nuevo;
        }
        ultimo = nuevo;
        numElem++;
    }
    public int desencolar() throws NoSuchElementException{
        if(numElem==0){
            throw new NoSuchElementException("Cola vacia");
        }else{
            int dato = primero.dato;
            numElem--;
            primero = primero.siguiente;
            if(primero==null){
                ultimo = null;
            }
            return dato;
        }
    }
}

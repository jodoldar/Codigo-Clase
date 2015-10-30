package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase objeto PilaIntArray, para contener a arrays de enteros. talla = numElem
 * @author Josep Dols
 */

public class PilaIntArray {
    protected int[] elArray;
    protected int numElem;
    private final int TALLA_INICIAL = 10;
    
    public PilaIntArray(){
        elArray = new int[TALLA_INICIAL];
        numElem = 0;
    }
    public boolean esVacia(){
        return numElem == 0;
    }
    public int talla(){
        return numElem;
    }
    public int cima(){
        if(numElem==0){
            throw new NoSuchElementException("Pila vacia");
        }
        return elArray[numElem-1];
    }
    public void apilar(int elemento){
        if(numElem==elArray.length){
            int[] aux = elArray;
            elArray = new int[aux.length*2];
            for(int i=0; i<numElem; i++){
                elArray[i] = aux[i];
            }
        }
        elArray[numElem++] = elemento;
    }   
    public int desapilar(){
        if(numElem==0){
            throw new NoSuchElementException("Pila vacia");
        }
        int dato = elArray[--numElem];
        return dato;
    }
}

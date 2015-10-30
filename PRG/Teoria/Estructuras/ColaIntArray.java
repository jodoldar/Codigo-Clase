package Estructuras;

import java.util.NoSuchElementException;

/**
 * Implementación de una cola mediante un array.
 * @author Josep Dols
 */
public class ColaIntArray {
    protected int numElem, primero, ultimo;
    protected int[] elArray;
    private final int CAPACIDAD_INICIAL = 10;
    
    /**
     * Constructor por defecto para la clase ColaArray que inicializa los atributos de la clase.
     */
    public ColaIntArray(){
        elArray = new int[CAPACIDAD_INICIAL];
        numElem = 0;
        primero = 0;
        ultimo = -1;
    }
    
    /**
     * Comprueba si la cola esta vacia.
     * @return true - Si la cola esta vacia. false - Si hay elementos en la cola.
     */
    public boolean esVacia(){
        return numElem==0;
    }
    /**
     * Devuelve el numero de elementos que contiene la cola.
     * @return Numero de elementos actual.
     */
    public int talla(){
        return numElem;
    }
    /**
     * Devuelve el valor situado el primero de la cola. Para que devuelva un valor, la 
     * precondicion es que la cola no esté vacia, lo que es comprobable con le método
     * esVacia() de la misma cola.
     * 
     * @return Valor del entero situado en la primera posición del array.
     * @throws NoSuchElementException En caso de que se invoque con la cola vacia.
     */
    public int primero() throws NoSuchElementException{
        if(numElem==0){
            throw new NoSuchElementException("Cola vacia");
        }
        return elArray[primero];
    }
    /**
     * Añade un elemento al final de la cola.
     * @param elemento Entero a introducir en la cola.
     */
    public void encolar(int elemento){
        if(numElem==elArray.length){
            duplicarArray();
        }
        numElem++;
        ultimo = siguiente(ultimo);
        elArray[ultimo] = elemento;
    }
    private void duplicarArray(){
        int[] nuevo = new int[elArray.length*2];
        ultimo = primero;
        primero = 0;
        for(int i=0; i<elArray.length; i++){
            nuevo[i] = elArray[ultimo];
            ultimo = siguiente(ultimo);
        }
        ultimo = numElem-1;
        elArray = nuevo;
    }
    private int siguiente(int actual){
        //return (actual==elArray.length-1) ? 0 : actual+1
        if(actual==elArray.length-1){
            return 0;
        }else{
            return actual+1;
        }
    }
    /**
     * Metodo de elimina un entero de la primera posicion de la cola.
     * @return Entero retirado de la cola.
     * @throws NoSuchElementException En caso de que se invoque con la cola vacia.
     */
    public int desencolar() throws NoSuchElementException{
        if(numElem==0){
            throw new NoSuchElementException();
        }else{
            int aux = elArray[primero];
            primero = siguiente(primero);
            numElem--;
            return aux;
        }
    }
}


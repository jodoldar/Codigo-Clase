package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase para implementar una lista con punto de interes.
 * @author Josep Dols
 */
public class ListaPIIntEnlazada {
    protected NodoInt primero, PI, antPI;
    protected int talla;
    
    /**
     * Constructor por defecto para la clase ListaPI, que inicializa sus atributos. Crea una lista vacia
     */
    public ListaPIIntEnlazada(){
        primero = null;
        PI = null;
        antPI = null;
        talla = 0;
    }
    
    /**
     * Indica si el punto de interés está a la derecha del todo.
     * @return true - si está al final; false - si no está al final.
     */
    public boolean esFin(){
        return PI==null;
    }
    /**
     * Indica si la lista esta vacia.
     * @return true - si esta vacia; false - si no esta vacia.
     */
    public boolean esVacia(){
        return talla==0;
    }
    /**
     * Devuelve el número de elementos que hay en la lista.
     * @return Talla de la lista
     */
    public int talla(){
        return talla;
    }
    /**
     * Situa el punto de interes al principio de la lista.
     */
    public void inicio(){
        PI = primero;
        antPI = null;
    }
    /**
     * Inserta el elemento introducido delante del punto de interes.
     * @param e Elemento a insertar.
     */
    public void insertar(int e){
        if(PI==primero){
            primero = new NodoInt(e,primero);
            antPI = primero;
        }else{
            antPI.siguiente = new NodoInt(e,PI);
            antPI = antPI.siguiente;
        }
        talla++;
    }
    /**
     * Devuelve y elimina de la lista el elemento que se encuentre en el punto de interes.
     * @return Valor del elemento eliminado.
     * @throws NoSuchElementException Si la lista esta en la posicion final.
     */
    public int eliminar() throws NoSuchElementException{
        if(PI==null){
            throw new NoSuchElementException("Final de la lista");
        }//else
        int resultado;
        //casos posibles
        if(antPI !=null){ //Eliminar en medio o al final(PI==ultimo elemento de la lista)
            antPI.siguiente = antPI.siguiente.siguiente;
        }else{  //Eliminar al principio
            primero = primero.siguiente;
        }
        resultado = PI.dato;
        PI = PI.siguiente;
        talla--;
        return resultado;
        /*int aux = PI.dato;
        if(PI==primero){
            primero = primero.siguiente;
        }else{
            antPI.siguiente = PI.siguiente;
        }
        PI = PI.siguiente;
        talla--;
        return aux;*/
    }
    /**
     * Consulta el numero que se encuentra en el punto de interes.
     * @return Valor del numero en el punto de interes.
     * @throws NoSuchElementException Si la lista esta en la posicion final.
     */
    public int recuperar() throws NoSuchElementException{
        if(PI==null){
            throw new NoSuchElementException("Final de la lista");
        }
        return PI.dato;
    }
    /**
     * Desplaza hacia la derecha el punto de interes una posicion en la lista.
     * @throws NoSuchElementException Si la lista esta en la posicion final.
     */
    public void siguiente() throws NoSuchElementException{
        if(PI==null){
            throw new NoSuchElementException("Final de la lista");
        }
        antPI = PI;
        PI = PI.siguiente;
    }
}

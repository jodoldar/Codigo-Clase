package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/**
 *
 * @author Josep
 */
public class LEGListaConPI<E> implements ListaConPI<E> {
    protected NodoLEG<E> primero, PI, antPI;
    protected int talla;
    
    public LEGListaConPI(){
        primero = null;
        PI = null;
        antPI = null;
        talla = 0;
    }
    
    @Override
    public void inicio(){
        PI = primero;
        antPI = null;
    }
    @Override
    public void siguiente(){
        antPI = PI;
        PI = PI.siguiente;
    }
    @Override
    public void fin(){
        while(PI!=null){
            PI=PI.siguiente;
        }
    }
    
    @Override
    public void insertar(E e){
        if(PI==primero){
            primero = new NodoLEG(e,primero);
            antPI = primero;
        }else{
            antPI.siguiente = new NodoLEG(e,PI);
            antPI = antPI.siguiente;
        }
        talla++;
    }
    @Override
    public void eliminar(){
        if(PI!=null){
            int resultado;
            if(antPI !=null){ //Eliminar en medio o al final(PI==ultimo elemento de la lista)
                antPI.siguiente = antPI.siguiente.siguiente;
            }else{  //Eliminar al principio
                primero = primero.siguiente;
            }
        }
        PI = PI.siguiente;
        talla--;
    }
    @Override
    public E recuperar(){
            return PI.dato;
    }
    
    @Override
    public boolean esFin(){
        return PI==null;
    }
    @Override
    public boolean esVacia(){
        return talla==0;
    }
    @Override
    public int talla(){
        return talla;
    }
}

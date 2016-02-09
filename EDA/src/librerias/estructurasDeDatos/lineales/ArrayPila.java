package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
/**
 *
 * @author Josep Dols
 */
public class ArrayPila<E> implements Pila<E> {
    protected E elArray[];
    protected static final int CAPACIDAD_POR_DEFECTO = 10;
    protected int tope;
    
    public ArrayPila(){
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        tope = -1;
    }
    
    @Override
    public void apilar(E e){
        if(tope+1 == elArray.length){
            duplicarArray();
        }
            tope++; elArray[tope] = e;
    }
    
    @Override
    public E desapilar(){
        E elUltimo = elArray[tope];
        tope--;
        return elUltimo;
    }
    
    @Override
    public E tope() { 
        return elArray[tope]; 
    }
    
    @Override
    public int talla(){
        return this.tope;
    }
    @Override
    public boolean esVacia() { 
        return (tope == -1); 
    }
    
    protected void duplicarArray(){
        E nuevo[] = (E []) new Object[elArray.length*2];
        for (int i = 0; i <= tope; i++){
            nuevo[i] = elArray[i]; 
        } 
        elArray = nuevo;
    }
}

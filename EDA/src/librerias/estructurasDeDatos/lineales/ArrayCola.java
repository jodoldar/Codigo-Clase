package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/**
 *
 * @author Josep Dols
 */
public class ArrayCola<E> implements Cola<E> {
    protected E elArray[];
    protected static final int CAPACIDAD_POR_DEFECTO = 10;
    protected int finalC, principioC, talla;
    
    public ArrayCola(){
        elArray = (E[]) new Object[CAPACIDAD_POR_DEFECTO];
        talla = 0;
        finalC = 0; principioC = 0;
    }
    
    @Override
    public void encolar(E e){
        if( talla== elArray.length){
            duplicarArrayCircular();
        }
        elArray[finalC] = e;
        finalC = incrementar(finalC); talla++;
    }
    
    protected void duplicarArrayCircular(){
        E nuevoArray[] = (E[]) new Object[elArray.length * 2];
        for(int i=0;i<talla;i++,principioC = incrementar(principioC)){
            nuevoArray[i] = elArray[principioC];
        }
        elArray = nuevoArray;
        principioC = 0; finalC = talla;
    }
    
    protected int incrementar(int indice){
        if(++indice==elArray.length){
            return 0;
        }else{
            return indice;
        }
    }
    
    @Override
    public E desencolar(){
        E elPrimero = elArray[principioC];
        principioC = incrementar (principioC); talla--;
        return elPrimero;
    }
    
    @Override
    public E primero(){
        return elArray[principioC];
    }
    
    @Override
    public boolean esVacia(){
        return (talla==0);
    }
    
    @Override
    public String toString(){
        String res = "";
        int aux = principioC;
        for(int i=0;i<talla;i++,aux = incrementar(aux)){
            res += elArray[aux].toString() + "|";
        }
        return res;
    }
}

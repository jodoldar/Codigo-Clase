package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase que extiende a la clase original ListaPIIntEnlazada
 * @author Josep Dols
 */
public class ListaPIIntEnlazadaExtendida extends ListaPIIntEnlazada{
    
    public ListaPIIntEnlazadaExtendida(){
        super();
    }
    
    @Override
    public String toString(){
        String res = "";
        if(talla==0){
            throw new NoSuchElementException("La lista esta vacia");
        }
        NodoInt aux = primero;
        for(int i=0; i<talla; i++){
            res += (aux.dato + "\n");
            aux = aux.siguiente;
        }
        return res;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof ListaPIIntEnlazada){
            boolean cuenta = true;
            ListaPIIntEnlazada aux = (ListaPIIntEnlazada)o;
            NodoInt auxLoc = primero;
            NodoInt auxExt = aux.primero;
            if(talla==aux.talla){
                for(int i=0;i<talla & cuenta; i++){
                    cuenta = auxLoc.dato==auxExt.dato;
                    auxLoc = auxLoc.siguiente;
                    auxExt = auxExt.siguiente;
                }
                return cuenta;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}

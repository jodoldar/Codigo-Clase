package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase extendida de ColaIntEnlazada para aplicar los metodos equals y toString.
 * @author Josep Dols
 */
public class ColaIntEnlazadaExtendida extends ColaIntEnlazada{
    
    public ColaIntEnlazadaExtendida(){
        super();
    }
    
    @Override
    public String toString(){
        String res = "";
        NodoInt aux = primero;
        if(numElem==0){
            throw new NoSuchElementException("Cola vacia");
        }
        for(int i=0; i<numElem; i++){
            res += (aux.dato + "\n");
            aux = aux.siguiente;
        }
        return res;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof ColaIntEnlazada){
            boolean cuenta = true;
            ColaIntEnlazadaExtendida aux = (ColaIntEnlazadaExtendida)o;
            if(numElem==aux.numElem){
                NodoInt auxLoc = this.primero;
                NodoInt auxExt = aux.primero;
                for(int i=0; i<numElem && cuenta; i++){
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

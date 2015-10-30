package Estructuras; 

import java.util.NoSuchElementException;

/**
 * Clase objeto PilaIntEnlazadaExtendida, para contener una estructura de enteros.
 * @author Josep Dols
 */
public class PilaIntEnlazadaExtendida extends PilaIntEnlazada{
    
    public PilaIntEnlazadaExtendida(){
       super();
    }
    
    @Override
    public String toString(){
        String res = "";
        NodoInt aux = cima;
        if(numElem==0){
            throw new NoSuchElementException("Pila vacia");
        }
        for(int i=0;i<numElem;i++){
            res += (aux.dato+"\n");
            aux = aux.siguiente;
        }
        return res;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof PilaIntEnlazada){
            boolean cuenta = true;
            PilaIntEnlazadaExtendida aux = (PilaIntEnlazadaExtendida)o;
            if(numElem==aux.numElem){
                NodoInt auxLoc = this.cima;
                NodoInt auxExt = aux.cima;
                for(int i=0;i<numElem && cuenta;i++){
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
    
    /**
     * Ejercicio 1.- Extender la Pila añadiendo un método que cuente los iguales que otros
     * @param elemento (int) entero a buscar.
     * @return número de iguales al elemento.
     */
    public int contarIgualesQue(int elemento){
        int cont = 0;
        NodoInt aux = cima;
        while(aux != null){
            if(aux.dato==elemento){
                cont++;
            }
            aux = aux.siguiente;
        }
        return cont;
    }
    
    /**
     * Implementar el método dinamico "borraBasePila" que elimina la base de la pila dejando el 
     * resto de elementos donde estan.
     * @return Elemento sacado de la Pila.
     */
    public int borraBasePila(){
        if(cima==null){
            throw new NoSuchElementException("Pila Vacia");
        }else{
            NodoInt aux = cima.siguiente, ant = cima;
            if(aux==null){
                cima = null;
            }else{
                while(aux.siguiente!=null){
                    ant = aux;
                    aux = aux.siguiente;
                }
                ant.siguiente = null;
            }
            numElem--;
            return aux.dato;
        }
    }
    
    /**
     * Metodo para saber si la Pila actual es sombrero de otra dada.
     * @param p1 Pila sobre la que saber si se es sombrero.
     * @return true - Si la pila es sombrero. false - si no lo son.
     */
    public boolean esSombrero(PilaIntEnlazada p1){
        boolean res;
        if(p1.numElem==0){
            res = true;
        }else if(p1.numElem>this.numElem){
            res = false;
        }else{
            NodoInt aux1 = this.cima;
            NodoInt aux2 = p1.cima;
            while(aux2!=null && aux1.dato==aux2.dato){
                aux1 = aux1.siguiente;
                aux2 = aux2.siguiente;
            }
            if(aux2==null){
                res = true;
            }else{
                res = false;
            }
        }
        return res;
    }
}

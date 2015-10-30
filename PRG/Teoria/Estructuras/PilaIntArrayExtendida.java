package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase objeto PilaIntArrayExtendida, para contener a arrays de enteros. talla = numElem
 * @author Josep Dols
 */
public class PilaIntArrayExtendida extends PilaIntArray{
    
    public PilaIntArrayExtendida(){
        super();
    }
    
    @Override
    public String toString(){
        String res = "";
        if(numElem==0){
            throw new NoSuchElementException("Pila vacia");
        }
        for(int i=numElem-1;i>=0;i--){
            res += (elArray[i] + "\n");
        }
        return res;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof PilaIntArrayExtendida){
            boolean cuenta = true;
            PilaIntArrayExtendida aux = (PilaIntArrayExtendida)o;
            if(numElem==aux.numElem){
                for(int i=0;i<numElem && cuenta;i++){
                    cuenta = elArray[i]==aux.elArray[i];
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
     * Implementar el método dinámico "borraBasePila" que elimine la base de la pila dejando el 
     * resto de elementos donde estan.
     * @return Elemento borrado de la base.
     */
    public int borraBasePila(){
        if(this.cima()==0){
            throw new NoSuchElementException("Pila Vacia");
        }else{
            int res = elArray[0];
            for(int i=1; i<this.cima(); i++){
                elArray[i-1] = elArray[i];
            }
            this.numElem--;
            return res;
        }
    }
}
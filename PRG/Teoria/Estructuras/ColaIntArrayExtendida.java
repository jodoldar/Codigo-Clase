package Estructuras;

import java.util.NoSuchElementException;

/**
 * Clase extendida de  ColaIntArray para aplicar los metodos equals y toString.
 * @author Josep Dols
 */
public class ColaIntArrayExtendida extends ColaIntArray{
    
    public ColaIntArrayExtendida(){
        super();
    }
    
    @Override
    public String toString(){
        String res="";
        if(numElem==0){
            throw new NoSuchElementException("Cola vacia");
        }
        for(int i=0;i<numElem;i++){
            res += (elArray[i] + "\n");
        }
        return res;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof ColaIntArray){
            boolean cuenta = true;
            ColaIntArrayExtendida aux = (ColaIntArrayExtendida)o;
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
}

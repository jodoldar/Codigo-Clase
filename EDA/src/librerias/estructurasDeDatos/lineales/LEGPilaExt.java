package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
/**
 *
 * @author Josep Dols
 */
public class LEGPilaExt<E extends Comparable<E>> extends LEGPila<E> implements PilaExt<E>{
    
    @Override
    public E minimo(){
        NodoLEG<E> aux = tope;
        E min = null;
        while(aux != null){
            if(min==null || aux.dato.compareTo(min)<0){
                min = aux.dato;
            }
            aux = aux.siguiente;
        }
        return min;
    }
}

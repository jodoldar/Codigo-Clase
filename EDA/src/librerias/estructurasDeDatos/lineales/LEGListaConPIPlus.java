package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
/**
 *
 * @author Josep Dols
 */
public class LEGListaConPIPlus<E> extends LEGListaConPI<E> implements ListaConPIPlus<E>{
    
    @Override
    public boolean eliminarUltimo(E e){
        NodoLEG<E> aux = primero;
        while(aux!=null){
            aux = aux.siguiente;
        }
        eliminar();
        return true;
    }
}

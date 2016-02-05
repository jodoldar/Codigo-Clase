package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
/**
 *
 * @author Josep
 */
public class LEGListaConPIExt<E> extends LEGListaConPI<E> implements ListaConPIExt<E> {
    @Override
    public void buscar(E x){
        inicio();
        while(!esFin() && !recuperar().equals(x)){
            siguiente();
        }
    }
    
    @Override
    public void vaciar(){
        inicio();
        while(!esVacia()){
            eliminar();
        }
    }
    
    @Override
    public int talla(){
        int n = 0;
        for(inicio();!esFin(); siguiente()){
            n++;
        }
        return n;
    }
    
    @Override
    public void invertir(){
        if(!esVacia()){
            inicio();
            E dato = recuperar();
            eliminar();
            invertir();
            insertar(dato);
        }
    }
    
    @Override
    public void eliminar(E x){
        inicio();
        while(!esFin()){
            if(recuperar().equals(x)){
                eliminar();
            }else{
                siguiente();
            }
        }
    }
}

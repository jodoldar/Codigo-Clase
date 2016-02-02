/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias.estructurasDeDatos.modelos;

/**
 *
 * @author Josep
 * @param <E>
 */
public interface Cola<E>{
    void encolar(E e);
    /** SII !esVacia()**/ E desencolar(); 
    /** SII !esVacia()**/ E primero(); 
    boolean esVacia();
}

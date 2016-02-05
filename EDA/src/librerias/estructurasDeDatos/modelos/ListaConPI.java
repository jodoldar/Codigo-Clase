/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias.estructurasDeDatos.modelos;

/**
 *
 * @author Josep
 */
public interface ListaConPI<E> {
    void inicio();
    void siguiente();
    void fin();
    
    void insertar(E e);
    void eliminar();
    E recuperar();
    
    boolean esFin();
    boolean esVacia();
    int talla();
}

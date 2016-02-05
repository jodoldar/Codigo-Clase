/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias.estructurasDeDatos.modelos;

/**
 *
 * @author Josep Dols
 */
public interface ListaConPIExt<E> extends ListaConPI<E> {
    void buscar(E x);
    void vaciar();
    @Override
    int talla();
    void invertir();
    void eliminar(E x);
}

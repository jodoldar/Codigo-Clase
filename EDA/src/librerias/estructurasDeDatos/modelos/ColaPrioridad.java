package librerias.estructurasDeDatos.modelos;

/**
 * Modelo de una Cola de Prioridad, o de Busqueda Dinamica 
 * del Elemento de maxima prioridad en una Coleccion
 * 
 * @version Febrero 2014  
 * @param <E> tipo de datos de la estructura
 */

public interface ColaPrioridad<E extends Comparable<E>> {

    // Métodos modificadores del estado de una Cola de Prioridad (CP):
    
    /** Atendiendo a su prioridad, inserta el Elemento e en una Cola de Prioridad
     * @param e Elemento a insertar en la cola.
     */
    void  insertar(E e);
    
    /** SII !esVacia(): obtiene y elimina el Elemento con 
     * maxima prioridad de una CP
     * @return Elemento con valor mínimo.
     */
    E  eliminarMin();
    
    // Métodos consultores del estado de una Cola de Prioridad (CP):
    
    /** SII !esVacia(): obtiene el Elemento con maxima prioridad de una CP
     * @return Elemento con valor mínimo.
     */
    E  recuperarMin();
    
    /** Comprueba si una CP esta vacia
     * @return True - si la cola está vacía; False - si la cola NO está vacía.
     */
    boolean esVacia();
}

package librerias.estructurasDeDatos.modelos;

/**
 *
 * @author Josep Dols
 */
public interface Pila<E> {
    public boolean esVacia();
    public int talla();
    public E tope();
    public void apilar(E elemento);
    public E desapilar();
}

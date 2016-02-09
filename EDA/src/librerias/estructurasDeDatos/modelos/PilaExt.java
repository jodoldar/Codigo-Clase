package librerias.estructurasDeDatos.modelos;

/**
 *
 * @author Josep Dols
 */
public interface PilaExt<E extends Comparable<E>> extends Pila<E> {
    E minimo();
}

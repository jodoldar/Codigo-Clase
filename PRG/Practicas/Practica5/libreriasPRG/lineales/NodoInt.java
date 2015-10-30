package Practica5.libreriasPRG.lineales;
import java.io.Serializable;
/**
 * clase NodoInt.
 *
 * @author (PRG - DSIC - ETSINF)
 * @version (curs 2014 - 2015)
 */
public class NodoInt implements Serializable {
    public int dato;
    public NodoInt siguiente;

    /** Constructor que permite crear un nodo con un int y un nodo siguiente determinado
     *  @param dato valor int que contiene el nodo
     *  @param siguiente Referencia al siguiente nodo de la lista
     */
    public NodoInt(int dato, NodoInt siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    /** Constructor que permite crear un nodo con un int y cuyo nodo siguiente es null
     *  @param dato valor int que contiene el nodo
     */
    public NodoInt(int dato) {
        this(dato, null);
    }
}

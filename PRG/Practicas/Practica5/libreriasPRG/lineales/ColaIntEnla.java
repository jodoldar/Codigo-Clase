package Practica5.libreriasPRG.lineales;
import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * ColaIntEnla: Cola de valores int
 *
 * @author (PRG - DSIC - ETSINF)
 * @version (curs 2014 - 2015)
 */
public class ColaIntEnla implements Serializable {

    private NodoInt primero, ultimo; // referencias al primer y último elemento de la cola (puntos de acceso a la cola)
    private int talla;		// número de elementos de la cola

    /**
     * Constructora de una cola vacía.
     */
    public ColaIntEnla() {
        primero = null;
        ultimo = null;
        talla = 0;
    }

    /**
     * Comprueba si la cola está o no vacía.
     * @return boolean, true si la cola está vacía y false en caso contrario.
     */
    public boolean esVacia() { return (primero == null); }

    /**
     * Encola un nuevo elemento en la cola.
     * @param x, int a encolar en la cola.
     */
    public void encolar(int x) {
        NodoInt nuevo = new NodoInt(x);
        if (ultimo != null) ultimo.siguiente = nuevo;
        else primero = nuevo;
        ultimo = nuevo;
        talla++;
    }

    /**
     * Devuelve el dato del elemento primero de la cola.
     * @throws NoSuchElementException si la cola está vacía.
     * @return int, dato del elemento primero de la cola.
     */
    public int primero() {
        if (this.esVacia()) throw new NoSuchElementException("Cola vacia");
        return primero.dato;
    }

    /**
     * Consulta y desencola el primero de la cola.
     * @throws NoSuchElementException si la cola está vacía.
     * @return int, dato del elemento primero desencolado de la cola.
     */
    public int desencolar() {
        if (talla == 0) throw new NoSuchElementException("Cola vacia");
        int x = primero.dato;
        primero = primero.siguiente;
        if (primero == null) ultimo = null;
        talla--;
        return x;
    }

    /**
     * Devuelve el número de elementos de la cola.
     * @return int, número de elementos de la cola.
     */
    public int talla() { return talla; }

    /**
     * Devuelve una String formada por los valores de la ColaIntEnla.
     * @return String con los datos de la cola.
     */
    public String toString() {
        String s = "";
        NodoInt p = primero;
        while (p != null) {
            s += String.format("%4d", p.dato);
            p = p.siguiente;
        }
        return s;
    }

}

package Practica5.concordancia;

import Practica5.libreriasPRG.lineales.ColaIntEnla;
import java.io.Serializable;
/**
 * Clase NodoCnc
 *
 * @author (PRG - DSIC - ETSINF)
 * @version (curs 2014 - 2015)
 */
public class NodoCnc implements Serializable {
    String palabra;
    ColaIntEnla numLins;
    NodoCnc siguiente;

    /** Constructor que permite crear un nodo con los datos asociados y
     *  un nodo siguiente determinados
     *  @param pal String que contiene el nodo
     *  @param num int a encolar
     *  @param siguiente NodoCnc referencia al siguiente nodo de la concordancia
     */
    NodoCnc(String pal, int num, NodoCnc siguiente) {
        this.palabra = pal;
        this.numLins = new ColaIntEnla();
        this.numLins.encolar(num);
        this.siguiente = siguiente;
    }

    /** Constructor que permite crear un nodo con los datos asociados y
     *  cuyo nodo siguiente es null
     *  @param pal String que contiene el nodo
     *  @param num int a encolar
     */
    NodoCnc(String pal, int num) {
        this(pal, num, null);
    }
}

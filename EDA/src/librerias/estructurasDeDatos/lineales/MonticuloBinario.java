

package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;

/**
 *
 * @author Josep Dols
 */
public class MonticuloBinario <E extends Comparable<E>> implements ColaPrioridad<E> {
    protected static final int CAPACIDAD_INICIAL = 50;
    protected E elArray[];
    protected int talla;

    @Override
    public void insertar(E x) {
        //¿Hay espacio en el array para el nuevo dato?
        if(talla == elArray.length - 1){
            duplicarArray();
        }
        // Hueco es la posición donde insertamos x
        int hueco = ++talla;
        //reflotamos hasta que no viole la propiedad de orden
        while(hueco > 1 && x.compareTo(elArray[hueco/2]) < 0){
            elArray[hueco] = elArray[hueco/2];
            hueco = hueco/2;
        }
        //Ya tenemos la posición del nuevo dato, insertamos
        elArray[hueco] = x;
    }

    @Override
    public E eliminarMin() {
    }

    @Override
    public E recuperarMin() {
    }

    @Override
    public boolean esVacia() {
    }

    private void duplicarArray() {
    }
}

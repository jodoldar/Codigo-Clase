

package librerias.estructurasDeDatos.lineales;

/**
 *
 * @author Josep Dols
 */
public class EntradaHash<C,V> {
    C clave;
    V valor;
    
    public EntradaHash(C clave, V valor){
        this.clave = clave;
        this.valor = valor;
    }
}

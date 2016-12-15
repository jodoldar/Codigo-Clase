

package Modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josep Dols
 */
public class Configuracion implements Serializable{
    private String nombrePc;
    private List<Producto> lista = new ArrayList<Producto>();
    private double subtotal = 0, iva = 0, total = 0;
    
    public Configuracion(String str,List<Producto> listaAux){
        this.nombrePc = str;
        this.lista = listaAux;
        for(Producto product:lista){
            subtotal += product.getPrecio() * product.getCantidad();
        }
        this.iva = subtotal*0.21;
        this.total = this.subtotal + this.iva;
    }
    
    public void setNombre(String aux){
        this.nombrePc = aux;
    }
    public String getNombre(){
        return this.nombrePc;
    }
    public List<Producto> getLista(){
        return this.lista;
    }
    public double getSubtotal(){
        return this.subtotal;
    }
    public double getIva(){
        return this.iva;
    }
    public double getTotal(){
        return this.total;
    }
}

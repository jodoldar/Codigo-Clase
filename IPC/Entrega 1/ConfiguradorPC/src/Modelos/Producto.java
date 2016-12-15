

package Modelos;

import es.upv.inf.Product;
import es.upv.inf.Product.Category;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Josep Dols
 */
public class Producto {
    private final SimpleStringProperty descripcion = new SimpleStringProperty();
    private final SimpleDoubleProperty precio = new SimpleDoubleProperty();
    private final SimpleIntegerProperty stock = new SimpleIntegerProperty();
    private final SimpleIntegerProperty cantidad = new SimpleIntegerProperty();
    private final SimpleDoubleProperty total = new SimpleDoubleProperty();
    private final Product.Category categoria;

    
    public Producto(Product p){
        descripcion.set(p.getDescription());
        precio.set(p.getPrice());
        stock.set(p.getStock());
        categoria = p.getCategory();
        cantidad.setValue(1);
        total.set(p.getPrice());
    }
    public Producto(String desc, double prec, int stoc, int cant, double tot, Product.Category cat){
        this.descripcion.set(desc);
        this.precio.set(prec);
        this.stock.set(stoc);
        this.cantidad.set(cant);
        this.total.set(tot);
        this.categoria = cat;
    }
    
    public void refreshTotal(){
        this.total.set(this.precio.get()*this.cantidad.get());
    }
    
    public SimpleIntegerProperty cantidadProperty(){
        return this.cantidad;
    }
    public Integer getCantidad(){
        return this.cantidad.getValue();
    }
    public void setCantidad(Integer x){
        this.cantidad.setValue(x);
    }
    
    public Category getCategoria(){
        return this.categoria;
    }
    
    public SimpleDoubleProperty totalProperty(){
        return this.total;
    }
    public Double getTotal(){
        return this.total.getValue();
    }
    public void setTotal(Double x){
        this.total.setValue(x);
    }
    
    public SimpleStringProperty descripcionProperty(){
        return this.descripcion;
    }
    public String getDescripcion(){
        return this.descripcion.getValue();
    }
    
    public SimpleDoubleProperty precioProperty(){
        return this.precio;
    }
    public Double getPrecio(){
        return this.precio.getValue();
    }
    
    public SimpleIntegerProperty stockProperty(){
        return this.stock;
    }
    public Integer getStock(){
        return this.stock.intValue();
    }
    
    public String parseOut(){
        String aux = this.getDescripcion() + ";";
        aux+= this.getPrecio() + ";";
        aux+=this.getStock() + ";";
        aux += this.getCantidad() + ";" + this.getTotal() + ";" + this.getCategoria() + ";" + "\n";
        return aux;
    }
    
    public static Producto parseIn(String enter){
        String[] aux = enter.split(";");
        Producto p = new Producto(aux[0], Double.parseDouble(aux[1]), Integer.parseInt(aux[2]), Integer.parseInt(aux[3]), Double.parseDouble(aux[4]), Product.Category.valueOf(aux[5]));
        return p;
    }
}

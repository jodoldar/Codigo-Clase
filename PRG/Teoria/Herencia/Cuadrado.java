package Herencia;

/** 
 * Clase Cuadrado que extiende los atributos de Figura
 * @author Josep Dols
 */
public class Cuadrado extends Rectangulo{
    /** Lado del Cuadrado actual */
    private double lado;
    
    /** Constructor de un objeto Cuadrado (herencia de la clase Figura)
     * @param c Nombre con el que se creará el Cuadrado.
     * @param cx Coordenada X donde se creará el Cuadrado
     * @param cy Coordenada Y donde se creará el Cuadrado
     * @param l Lado con el que se creará el Cuadrado
     */
    public Cuadrado(String c, int cx, int cy, double l){
        super(c,cx,cy,l,l);
        this.lado = l;
    }
    
    /** Metodo que permita cambiar el valor del parametro Lado
     * @param l Valor a asignar a la varible Lado.
     */
    public void setLado(double l){
        this.lado = l;
        {
            super.setAltura(l);
            super.setBase(l);
        }
    }
    
    /** Metodo que devuelve el valor del atributo Lado
     * @return Valor actual del lado.
     */
    public double getLado(){
        return this.lado;
    }
    
    /** Metodo que calcula el area del Cuadrado actal (sobreescribe a la clase Figura).
     * @return Valor actual del area.
     */
    @Override
    public double area(){
        return super.area();
    }
    
    /** Metodo que calcula el perimetro del Cuadrado actual (sobreescrible a la clase Figura).
     * @return Valor actual del perimetro
     */
    @Override
    public double perimetro(){
        return super.perimetro();
    }
    
    /** Metodo que devuelve una descripcion del Circulo actual (sobreescribe a la clase Figura).
     * @return String con la descripcion del objeto Cuadrado.
     */
    @Override
    public String toString(){
        return ("Cuadrado de lado: " + this.lado + " y de color: " + this.color +
                " con las coordenadas: " + this.coordX + "," + this.coordY);
    }
    
    /** Metodo que compara dos objetos Cuadrado (sobreescribe a la clase Figura).
     * @param o Objeto sobre el que aplicar la comparacion.
     * @return true - Si son iguales. false - Si son diferentes.
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Rectangulo && !(!(this instanceof Cuadrado) && (o instanceof Cuadrado))){
            Cuadrado otra = (Cuadrado)o;
            return super.equals(otra);
        }else{
            return false;
        }
    }
}

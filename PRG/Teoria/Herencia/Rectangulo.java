package Herencia;

/**
 * Clase Rectangulo que extiende los atributos de la clase Cuadrado
 * @author Josep Dols
 */
public class Rectangulo extends Figura {
    /** Base y Altura del Rectangulo actual */
    private double base, altura;
    
    /** Constructor de un objeto Rectangulo (herencia de la clase Cuadrado)
     * @param c Color con el que se creará el Rectangulo
     * @param cx Coordenada X donde se creará el Rectangulo
     * @param cy Coordenada Y donde se creará el Rectangulo
     * @param b Base con la que se creará el Rectangulo
     * @param a Altura con la que se creará el Rectangulo
     */
    public Rectangulo(String c, int cx, int cy, double b, double a){
        super(c,cx,cy);
        this.base = b;
        this.altura = a;
    }
    
    /** Metodo que permite cambiar el valor del atributo Base.
     * @param b Valor a asignar a la variable Base
     */
    public void setBase(double b){
        this.base = b;
    }
    
    /** Metodo que permite cambiar el valor del atributo Altura.
     * @param a Valor a asignar a la variable Altura
     */
    public void setAltura(double a){
        this.altura = a;
    }
    
    /** Metodo que devuelve el valor del atributo Base.
     * @return Valor actual de la base.
     */
    public double getBase(){
        return this.base;
    }
    
    /** Metodo que devuelve el valor del atributo Altura.
     * @return Valor actual de la altura.
     */
    public double getAltura(){
        return this.altura;
    }
    
    /** Metodo que calcula el area del Rectangulo actual (sobreescribe a la clase Cuadrado).
     * @return Valor actual del area.
     */
    @Override
    public double area(){
        return this.base*this.altura;
    }
    
    /** Metodo que calcula el perimetro del Rectangulo actual (sobreescribe a la clase Cuadrado).
     * @return Valor actual del perimetro.
     */
    @Override
    public double perimetro(){
        return ((2*this.base) + (2*this.altura));
    }
    
    /** Metodo que devuelve una descripcion del Rectangulo actual (sobreescribe a la clase Cuadrado).
     * @return String con la descripcion del objeto Rectangulo.
     */
    @Override
    public String toString(){
        return ("Rectangulo de base: " + this.base + " y altura: " + this.altura + 
                " y de " + super.toString());
    }
    
    /** Metodo que compara dos objetos Rectangulo (sobreescribe a la clase Cuadrado).
     * @param o Objeto sobre el que aplicar la comparacion.
     * @return true - Si son iguales. false - Si son diferentes.
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Rectangulo){
            Rectangulo otra = (Rectangulo)o;
            return super.equals(otra) && this.altura == otra.altura && this.base == otra.base;
        }else{
            return false;
        }
    }
}

package Herencia;

/**
 * Clase Circulo que extiende los atributos de Figura
 * @author Josep Dols
 */
public class Circulo extends Figura{
    /** Radio del circulo actual */
    private double radio;
    
    /** Constructor de un objeto Circulo (herencia de la clase Figura)
     * @param c Nombre con el que se creará el Circulo
     * @param x Coordenada X donde se creará el Circulo
     * @param y Coordenada Y donde se creará el Circulo
     * @param r Radio con el que se creará el Circulo
     */
    public Circulo(String c,int x,int y, double r){
        super(c,x,y);
        radio = r;
    }
    
    /** Metodo que devuelve el valor del atributo Radio
     * @return Valor actual del radio.
     */
    public double getRadio(){
        return radio;
    }
    
    /** Metodo que permite cambiar el valor del atributo Radio
     * @param r Valor a asignar a la variable radio.
     */
    public void setRadio(double r){
        radio=r;
    }
    
    /** Metodo que calcula el area del Circulo actal (sobreescribe a la clase Figura).
     * @return Valor actual del area.
     */
    @Override
    public double area(){
        return Math.PI*(this.radio*this.radio);
    }
    
    /** Metodo que calcula el perimetro del Circulo actual (sobreescrible a la clase Figura).
     * @return Valor actual del perimetro.
     */
    @Override
    public double perimetro(){
        return 2*Math.PI*this.radio;
    }
    
    /** Metodo que devuelve una descripcion del Circulo actual (sobreescribe a la clase Figura).
     * @return String con la descripcion del objeto Circulo.
     */
    @Override
    public String toString(){
        return ("Circulo con radio: " + this.radio + super.toString() + 
                ", area: " + this.area() + " y perimetro: " + this.perimetro());
    }
    
    /** Metodo que compara dos objetos Circulo (sobreescribe a la clase Figura).
     * @param o Objeto sobre el que aplicar la comparacion.
     * @return true - Si son iguales. false - Si son diferentes.
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Circulo){
            Circulo otra = (Circulo) o;
            return super.equals(otra) && this.radio == otra.radio;
        }else {
            return false;
        }
    }
}

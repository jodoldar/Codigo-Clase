package Herencia;

/**
 * Clase abstracta Figura (clase padre)
 * @author Josep Dols
 */

public class Figura{
    /** Color de la Figura actual */
    protected String color;
    /** Coordenadas X(coordX) e Y(coordY) de la Figura actual */
    protected int coordX, coordY;
    
    /** Construccion de un objeto Figura.
     * @param c Color con el que se creará la Figura.
     * @param x Coordenada X donde se creará la Figura.
     * @param y Coordenada Y donde se creará la Figura.
     */
    public Figura(String c, int x, int y){
        color=c;
        coordX=x;
        coordY=y;
    }
    
    /** Metodo que devuelve el valor del atributo Color.
     * @return Valor actual del Color.
     */
    public String getColor(){
        return this.color;
    }
    
    /** Metodo que devuelve el valor del atributo CoordX.
     * @return Valor actual de CoordX.
     */
    public int getCoordX(){
        return this.coordX;
    }
    
    /** Metodo que devuelve el valor del atributo CoordY.
     * @return Valor actual de CoordY.
     */
    public int getCoordY(){
        return this.coordY;
    }
    
    /** Metodo que permite cambiar el valor del atributo Color.
     * @param c Valor a asignar a la variable Color.
     */
    public void setColor(String c){
        this.color = c;
    }
    
    /** Metodo que permite cambiar el valor del atributo CoordX.
     * @param x Valor a asignar a la varible CoordX.
     */
    public void setCoordX(int x){
        this.coordX = x;
    }
    
    /** Metodo que permite cambiar el valor del atributo CoordY.
     * @param y Valor a asignar a la variable CoordY.
     */
    public void setCoordY(int y){
        this.coordY = y;
    }
    
    /** Metodo que devuelve una descripcion de la Figura actual.
     * @return String con la descripcion del objeto Figura.
     */
    @Override
    public String toString(){
        return (" color: " + this.color + "y con centro en: " + this.coordX +
                ":" + this.coordY);
    }
    
    /** Metodo que calcula el area de la Figura actual.
     * @return Valor neutro, el objeto es abstracto.
     */
    public double area(){
        return -1;
    }
    
    /** Metodo que calcula el perimetro de la Figura actual.
     * @return Valor neutro, el objeto es abstracto.
     */
    public double perimetro(){
        return -1;
    }
    
    /** Metodo que compara dos objetos Figura.
     * @param o Objeto sobre el que aplicar la comparacion.
     * @return true - Si son iguales. false - Si son diferentes.
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Figura){
            Figura otra = (Figura) o;
            return color.equals(otra.color) && this.coordX == otra.coordX &&
                    this.coordY ==otra.coordY;
        }else{
            return false;
        }
    }
}

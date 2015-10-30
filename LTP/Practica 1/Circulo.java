
/**
 * Write a description of class Circulo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Circulo extends Figura{
    //private double x,y;
    protected double r;
    Circulo(double a, double b,double c){
        super(a,b);
        this.r = c;
    }
    
    public double area(){
        return Math.pow(r,2)*Math.PI;
    }
    
    public String toString (){
        return "Círculo:\n\t"+ super.toString() + "\n\tRadio: "+ r;
    }
    
    public boolean equals(Object c){
        if(c instanceof Circulo){
            Circulo aux = (Circulo) c;
            return this.x == aux.x && this.y == aux.y && this.r == aux.r;
        }else{return false;}
    }
}

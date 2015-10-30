
/**
 * Write a description of class Triangulo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Triangulo extends Figura{
    
    //private double x,y
    private double base,altura;
    Triangulo(double cx,double cy,double b, double a){
        super(cx,cy);
        this.base = b;
        this.altura = a;
    }
    
    public double area(){
        return (base*altura)/2;
    }
    
    public String toString(){
        return "Triángulo:\n\t" + super.toString() + "\n\tBase: " + base + "\n\tAltura: " 
              + altura;
    }
    
    public boolean equals(Object t){
        if(t instanceof Triangulo){
            Triangulo aux = (Triangulo) t;
            return this.x == aux.x && this.y == aux.y && this.base == aux.base && this.altura == aux.altura;
        }else{ return false;}
    }
}


/**
 * Write a description of class Rectangulo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rectangulo extends Figura{
    double base, altura;
    
    public Rectangulo(double a, double b, double c, double d){
        super(a,b);
        this.base = c;
        this.altura = d;
    }
    
    public double area(){
        return base*altura;
    }
    
    public String toString(){
        return "Rectangulo:\n\t"+ super.toString() + "\n\tBase: "+ base + "\n\tAltura: " + altura;
    }
    
    public boolean equals(Object r){
        if(r instanceof Rectangulo){
            Rectangulo aux = (Rectangulo) r;
            return this.x == aux.x && this.y == aux.y && this.base == aux.base && this.altura == aux.altura;
        }else{return false;}
    }
}


/**
 * Write a description of class Cilindro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cilindro extends Circulo implements Volumen{
    protected double a;
    
    public Cilindro(double x, double y, double radio, double altura){
        super(x,y,radio);
        a = altura;
    }
    public Cilindro(Circulo c, double altura){
        this(c.x, c.y, c.r, altura);
    }
    
    public double volumen(){
        return super.area()*a;
    }
    
    public double superficie(){
        return this.area();
    }
    
    public double area(){
        Rectangulo aux1 = new Rectangulo(1,1,this.r*2*Math.PI,this.a);
        return aux1.area()+(2*super.area());
    }
}

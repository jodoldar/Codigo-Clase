
/**
 * Write a description of class UsoDeGrupoFiguras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UsoDeGrupoFiguras{
    
    public static void main (String args[]){
        GrupoFiguras g = new GrupoFiguras();
        g.anyadeCirculo(new Circulo(10,5,3.5));
        g.anyadeTriangulo(new Triangulo(10,5,6.5,32));
        g.anyadeFigura(new Rectangulo(1,2,3,4));
        System.out.println(g);
    }
}


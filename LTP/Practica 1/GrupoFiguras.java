
/**
 * Write a description of class GrupoFiguras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrupoFiguras{
    
    static final int MAX_NUM_FIGURAS = 10;
    //private Circulo [] listaC = new Circulo [MAX_NUM_FIGURAS/2];
    //private Triangulo [] listaT = new Triangulo [MAX_NUM_FIGURAS/2];
    private Figura[] listaFiguras = new Figura[MAX_NUM_FIGURAS];
    private int numF = 0;
    
    
    public void anyadeCirculo(Circulo c){
        listaFiguras[numF++]= c;
    }
    
    public void anyadeTriangulo(Triangulo t){
        listaFiguras[numF++]= t;
    }
    
    public void anyadeFigura(Figura f){
        if(f instanceof Figura){
            listaFiguras[numF++] = f;
        }
    }
    
    public double area(){
        double aux=0;
        for(int i=0;i<numF; i++){
            aux+=listaFiguras[i].area();
        }
        return aux;
    }
    
    public double volumen(){
        double aux = 0;
        for (int i=0;i<numF;i++){
            if(listaFiguras[i] instanceof Volumen){
                aux += ((Volumen)listaFiguras[i]).volumen();
            }
        }
        return aux;
    }
    
   public String toString(){
       String s = "";
       for(int i=0; i<numF;i++){
           s+="\n" + listaFiguras[i];
        }
        return s;
    }
   
   private boolean included(GrupoFiguras gf){
       for(int i=0;i<this.numF;i++){
           for(int j=0;j<gf.numF;j++){
               if(this.listaFiguras[i].equals(gf.listaFiguras[j])){break;}
               if(j+1 == gf.numF){return false;}
            }
        }
        return true;
    } 
    
   public boolean equals(Object g){
       if(g instanceof GrupoFiguras){
           GrupoFiguras gf = (GrupoFiguras) g;
           return (this.included(gf) && gf.included(this));
        }else{
            return false;
        }
    }
}

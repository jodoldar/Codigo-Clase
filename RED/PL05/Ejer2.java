
/**
 * Ejercicio 2 de la practica 5 de Redes
 * 
 * @author Josep Dols
 * @version 13/11/15
 */
public class Ejer2 extends Thread{
    int id;
    String cadena;
    
    public Ejer2(int i, String cad){
        id = i;
        cadena = cad;
    }
    
    public void run(){
        for( int i=0; i<10; i++){
            System.out.println("Texto de prueba " + (i+1) + " Cadena: " + cadena);
            try{
                sleep(100);
            }catch( InterruptedException e){}
        }
    }
    
    public static void main(String[] args){
        //for (int i=0; i<10;i++){
            Ejer2 p = new Ejer2(1,"cadenita");
            p.start();
            for (int i=0; i<10; i++){
                System.out.println("Texto del padre " + (i+1));
            }
        //}
    }
}

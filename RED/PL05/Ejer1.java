
/**
 * Ejercicio 1 de la practica 5 de Redes
 * 
 * @author Josep Dols
 * @version 13/11/15
 */
public class Ejer1 extends Thread{
    int id;
    
    public Ejer1(int i){
        id = i;
    }
    
    public void run(){
        for( int i=0; i<10; i++){
            System.out.println("Texto de prueba " + (i+1));
            try{
                sleep(100);
            }catch( InterruptedException e){}
        }
    }
    
    public static void main(String[] args){
        //for (int i=0; i<10;i++){
            Ejer1 p = new Ejer1(1);
            p.start();
            for (int i=0; i<10; i++){
                System.out.println("Texto del padre " + (i+1));
            }
        //}
    }
}

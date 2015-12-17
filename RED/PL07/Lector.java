import java.net.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of class Lector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lector extends Thread
{
    Socket sc;
    
    public Lector(Socket sock){
        sc = sock;
    }
    
    public void run(){
        try{
            Scanner entrada = new Scanner(sc.getInputStream());
            while(entrada.hasNextLine()){
                System.out.println(entrada.nextLine());
            }
            sc.close();
        }catch(Exception e){
            System.out.println("FALLO");
        }
    }
}

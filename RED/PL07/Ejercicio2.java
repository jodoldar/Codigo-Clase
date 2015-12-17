import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Write a description of class Ejercicio2 here.
 * 
 * @author Josep Dols    
 * @version 1.0 27/11/15
 */
public class Ejercicio2
{
    public static void main(String[] args) throws Exception{
        ServidorUDP aux = new ServidorUDP();
        aux.start();
        ServerSocket ss = new ServerSocket(7777);
        Socket sc = ss.accept();
        try{
            PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
            Scanner entrada = new Scanner(sc.getInputStream());
            while(entrada.hasNext()){
                salida.println(entrada.nextLine());
            }
            sc.close();
        }catch(Exception e){
            System.err.println("Fallo en el main");
        }
        aux.join();       
    }
}

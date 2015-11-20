import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Ejercicio 3 de la Practica 5
 * 
 * @author Josep Dols
 * @version 13/11/15
 */
public class Ejer3 extends Thread{
    Socket sc;
    
    public Ejer3(Socket s){
        sc = s;
    }
    
    public void run(){
        try{
            PrintWriter salida = new PrintWriter(sc.getOutputStream(), true);
            Scanner entrada = new Scanner(sc.getInputStream());
            String aux;
            while(true){
                aux = entrada.nextLine();
                if(aux.equals("QUIT")){break;}
                salida.println(aux);
            }
            sc.close();
        }catch(Exception e){}
    }
    
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(8080);
        while(true){
            Socket s = ss.accept();
            Ejer3 e = new Ejer3(s);
            e.start();
        }
    }
}

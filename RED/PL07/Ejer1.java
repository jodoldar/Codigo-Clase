import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 1 de la Practica 7
 * 
 * @author Josep Dols 
 * @version 20/11/15 - 
 */
public class Ejer1{
    public static void main(String[] args) throws Exception{
        Socket sc = new Socket("rdc00.redes.upv.es",7777);
        Lector lec = new Lector(sc);
        lec.start();
        PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
        Scanner teclado = new Scanner(System.in);
        while(teclado.hasNext()){
            String aux = teclado.nextLine();
            salida.println(aux);
            if(aux.equals("quit")){break;}
        }
    }
}

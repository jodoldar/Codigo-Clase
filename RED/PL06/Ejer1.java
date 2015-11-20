import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 1 de la Practica 6
 * 
 * @author Josep Dols 
 * @version 20/11/15
 */
public class Ejer1{
    public static void main(String[] args) throws Exception{
        Scanner teclado = new Scanner(System.in);
        String dir = teclado.nextLine();
        InetAddress Ipdir = InetAddress.getByName(dir);
        
        System.out.println("Dirección del host: " + Ipdir);
    }
}

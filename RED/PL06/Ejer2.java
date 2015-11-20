import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 2 de la Practica 6
 * 
 * @author Josep Dols 
 * @version 20/11/15
 */
public class Ejer2{
    public static void main(String[] args) throws Exception{
        Scanner teclado = new Scanner(System.in);
        String dir = teclado.nextLine();
        InetAddress[] Ipdirs = InetAddress.getAllByName(dir);
        
        System.out.println("Direcciones de los hosts: " + Arrays.toString(Ipdirs));
    }
}

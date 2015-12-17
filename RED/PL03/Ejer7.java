import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Ejercicio 7 (opcional): Modifica el programa anterior para que acepte como argumentos del
 * programa el nombre del servidor y el número de puerto en el que escucha.
 * 
 * @author Josep Dols
 * @version 1.0 06/11/15
 */
public class Ejer7
{
    public static void metodo7(){
        Scanner entry = new Scanner(System.in);
        System.out.println("Introduce el nombre del servidor: ");
        String a = entry.nextLine();
        System.out.println("Introduce el número de puerto: ");
        int b = entry.nextInt();
        try{
            Socket sc = new Socket(a,b);
            System.out.println("Puerto local: " + sc.getLocalPort());
            System.out.println("Puerto remoto: " + sc.getPort());
            System.out.println("Direccion IP: " + sc.getInetAddress());
            sc.close();
        }catch(UnknownHostException e1){
            System.err.println("No existe el host");
        }catch(IOException e2){
            System.err.println("Fallo de E/S");
        }
    }
}
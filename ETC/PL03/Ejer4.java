import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Ejercicio 4 Cliente de daytime. Conecta al puerto 13 del servidor “ntp.upv.es” e imprime la
 * respuesta del servidor por pantalla. .
 * 
 * @author Josep Dols 
 * @version 1.0 30/10/15
 */
public class Ejer4
{
    public static void metodo4(){
        try{
            Socket sc = new Socket("ntp.upv.es",13);
            Scanner salida = new Scanner(sc.getInputStream());
            System.out.println(salida.nextLine());
            sc.close();
        }catch(UnknownHostException e1){
            System.err.println("No existe el host");
        }catch(IOException e2){
            System.err.println("Fallo de E/S");
        }
    }
}

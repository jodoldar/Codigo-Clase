import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Ejercicio 5 Cliente HTTP/1.0 básico en modo texto. Se conecta al puerto 80 del servidor
 * “www.upv.es” y envía la cadena “GET / HTTP/1.0\r\n\r\n”. Muestra la respuesta recibida
 * del servidor por pantalla y termina
 * @author Josep Dols 
 * @version 1.0 30/10/15
 */
public class Ejer5
{
    public static void metodo5(){
        try{
            Socket sc = new Socket("www.upv.es",80);
            PrintWriter entrada = new PrintWriter(sc.getOutputStream(),true);
            entrada.printf("GET / HTTP/1.0"+"\r\n\r\n");
            Scanner salida = new Scanner(sc.getInputStream());
            while(salida.hasNext()){
                System.out.println(salida.nextLine());
            }
            sc.close();
        }catch(UnknownHostException e1){
            System.err.println("No existe el host");
        }catch(IOException e2){
            System.err.println("Fallo de E/S");
        }
    }
}

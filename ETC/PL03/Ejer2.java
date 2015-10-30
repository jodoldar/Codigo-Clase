import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Ejercicio 2 Añade un método al programa del ejercicio 1. Este método realizará lo mismo
 * que el del ejercicio 1 con 2 diferencias: el mensaje será ahora “¡Conectado de nuevo!” y se
 * capturarán las posibles excepciones mediante una cláusula try ... catch.
 * Ejecútalo y comprueba que funciona correctamente.
 * Sustituye el puerto por el 81. Ejecuta el método y comprueba que ocurre.
 *      Salta IOException
 * Restaura de nuevo el puerto al valor 80. Añadir una w al nombre del servidor, ejecuta
 * el método de nuevo y comprueba que ocurre.
 *      Falla en nombre del servidor UnknownHostException
 * Al intentar conectar un socket cliente con el de un servidor, ¿cuándo se genera una
 * UnknownHostException?¿y una IOException? 
 *      Se genera UnknownHostException cuando la pagina web no existe, e IOException cuando el 
 *      puerto falla
 * 
 * @author Josep Dols
 * @version 1.0 30/10/15
 */
public class Ejer2
{
    public static void metodo2(){
        try{
            Socket sc = new Socket("wwww.upv.es",80);
            System.out.println("¡Conectado de nuevo!!");
            sc.close();
        }catch(UnknownHostException e1){
            System.err.println("No existe el host");
        }catch(IOException e2){
            System.err.println("Fallo de E/S");
        }
    }
}

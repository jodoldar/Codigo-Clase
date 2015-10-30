import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Ejercicio 3 Añade un nuevo método a tu programa que conecte al puerto 25 del servidor
 * “smtp.upv.es”, lea la primera línea de texto que devuelve el servidor y la imprima por
 * pantalla
 * 
 * @author Josep Dols
 * @version 1.0 30/10/15
 */
public class Ejer3
{
    public static void metodo3(){
        try{
            Socket sc = new Socket("smtp.upv.es",25);
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

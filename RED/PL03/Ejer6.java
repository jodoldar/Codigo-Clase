import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Ejercicio 6 Añade un nuevo método al programa. El método implementará un cliente que
 * se conecta al puerto 25 del servidor SMTP de la UPV, y tras conectarse, muestra por pantalla
 * el puerto local y el remoto, así como la dirección IP del servidor. Ejecútalo tres veces
 * seguidas y comprueba cómo se modifican los valores mostrados. 
 * 
 * @author Josep Dols   
 * @version 1.0 06/11/15
 */
public class Ejer6 {
    public static void metodo6(){
        try{
            Socket sc = new Socket("smtp.upv.es",25);
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

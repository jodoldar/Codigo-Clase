import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 4 de la Practica 6
 * 
 * @author Josep Dols 
 * @version 20/11/15
 */
public class Ejer4{
    public static void main(String[] args) throws Exception{
        Scanner teclado = new Scanner(System.in);
        String name = teclado.nextLine();
        DatagramPacket dp = new DatagramPacket(name.getBytes(), name.getBytes().length, 
            InetAddress.getByName("rdc00.redes.upv.es"),7777);
        
        DatagramSocket ds = new DatagramSocket();
        ds.send(dp);
        //System.out.println("Dirección del host: " + Ipdir);
    }
}

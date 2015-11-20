import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 5 de la Practica 6
 * 
 * @author Josep Dols 
 * @version 20/11/15
 */
public class Ejer5{
    public static void main(String[] args) throws Exception{
        Scanner teclado = new Scanner(System.in);
        String name = teclado.nextLine() + "\n";
        
       
        DatagramPacket dp = new DatagramPacket(name.getBytes(), name.getBytes().length, 
            InetAddress.getByName("localhost"),7777);
        
        DatagramSocket ds = new DatagramSocket();
        ds.send(dp);
        
        byte[] buffer = new byte[100];
        dp.setData(buffer);
        ds.receive(dp);
        String salida = new String(dp.getData(),0,dp.getLength());
        System.out.println(salida);
    }
}

import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 6 de la Practica 6
 * 
 * @author Josep Dols 
 * @version 20/11/15
 */
public class Ejer6{
    public static void main(String[] args) throws Exception{
        byte[] buffer = new byte[100];
        
        DatagramPacket dp = new DatagramPacket(buffer, 100, 
            InetAddress.getByName("localhost"),7777);
        
        DatagramSocket ds = new DatagramSocket(7777);
        while(true){
            ds.receive(dp);
            Date now = new Date();
            String hora = now.toString() + "\n";
            dp.setData(hora.getBytes());
            dp.setLength(hora.getBytes().length);
        
            ds.send(dp);
        }
    }
}

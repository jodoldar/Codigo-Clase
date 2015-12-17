import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Write a description of class ServidorUPD here.
 * 
 * @author Josep Dols
 * @version 1.0 27/11/15
 */
public class ServidorUDP extends Thread
{
    public void run(){
        try{
            byte[] buffer = new byte[100];
            DatagramPacket dp = new DatagramPacket(buffer,100,InetAddress.getByName("localhost"),7777);
            DatagramSocket ds = new DatagramSocket(7777);
            while(true){
                ds.receive(dp);
                ds.send(dp);
            }
        }catch(Exception e){
            System.err.println("Algo falla UDP");
        }
    }
}

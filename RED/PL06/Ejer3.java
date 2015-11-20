import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Ejercicio 3 de la Practica 6
 * 
 * @author Josep Dols 
 * @version 20/11/15
 */
public class Ejer3{
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();
                
        System.out.println("Dirección del puerto: " + ds.getLocalPort());
    }
}

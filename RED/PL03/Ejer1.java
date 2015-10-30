import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Ejercicio 1 Escribe un programa que conecte al puerto 80 del servidor “www.upv.es”,
 * imprima por pantalla el mensaje “¡Conectado!”, después cierre el socket y termine. No hace
 * falta el tratamiento de excepciones
 * 
 * @author Josep Dols
 * @version 1.0 30/10/15
 */
public class Ejer1
{
    public static void main(String[] args) throws Exception{
        Socket sc = new Socket("www.upv.es",80);
        System.out.println("Conectado!!");
        sc.close();
    }
}

import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Primera parte del Examen
 * 
 * @author Josep Dols    
 * @version 18/12/15
 */
public class Examen1
{
   public static void main(String[] arigs){
       try{
           Socket sc = new Socket("rdc00.redes.upv.es",23450);
           byte[] buffer = new byte[200];
            DatagramPacket dp = new DatagramPacket(buffer,200,InetAddress.getByName("rdc00.redes.upv.es"),8181);
            DatagramPacket dp2 = new DatagramPacket(buffer,200,InetAddress.getByName("rdc00.redes.upv.es"),8181);
            DatagramSocket ds = new DatagramSocket(8181);
           PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
           Scanner entrada = new Scanner(sc.getInputStream());
           Scanner teclado = new Scanner(System.in);
            System.out.println(entrada.nextLine());

            salida.println("8181");
            ds.receive(dp);
            
            String mensaje = new String(dp.getData(),0,dp.getLength());
            System.out.println(mensaje);
            
            String nombre = "Josep Vicent Dols Dart - 53258395D";
            dp.setData(nombre.getBytes());
            dp.setLength(nombre.getBytes().length);
            
            ds.send(dp);
            ds.receive(dp2);
            
            String mensaje2 = new String(dp2.getData(),0,dp2.getLength());
            System.out.println(mensaje2);
            
            sc.close();
            ds.close();
        }catch(UnknownHostException e1){
            System.err.println("Fallo de direccion del host");
        }catch(IOException e2){
             System.err.println("Fallo de E/S");
        }
    }
}

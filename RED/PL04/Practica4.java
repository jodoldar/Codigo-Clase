import java.util.*;
import java.net.*;
import java.io.*;

/**
 *  Practica 4
 * 
 * @author Josep Dols    
 * @version 1.0 06/11/15
 */
public class Practica4
{
    /**
     * Ejercicio 1 Escribe un servidor de un único uso (atiende a un cliente y termina) que
     * escuche en el puerto 7777. Tras aceptar la conexión del cliente, el servidor imprime en
     * pantalla el mensaje: “Se ha conectado un cliente al servidor” y termina. (Puede probarse con
     * la orden “nc localhost 7777”).
     */
    public static void metodo1(){
        try{
            ServerSocket ssc = new ServerSocket(7777);
            Socket sc = ssc.accept();
            System.out.println("Se ha conectado un cliente al servidor");
            sc.close();
        }catch(IOException e1){
            System.err.println("Fallos de salida");
        }
    }
    
    /**
     * Ejercicio 2a Siguiendo el modelo anterior, escribe un servidor de que escuche en el puerto 
     * 7777 y devuelva al cliente la hora del día. Para el cálculo de la hora puedes emplear la clase 
     * Calendar del paquete java.util:
     *      Calendar now = Calendar.getInstance();
     *      int h = now.get(Calendar.HOUR_OF_DAY);
     *      int m = now.get(Calendar.MINUTE);
     *      int s = now.get(Calendar.SECOND);
     * ¿Qué ocurre si intentas ejecutar el cliente dos veces sin volver a ejecutar el servidor?
     * ¿Crees que es el comportamiento habitual de un servidor?
     * 
     * Ejercicio 2b Modificar el servidor anterior para que atienda a los clientes de forma
     * ininterrumpida.
     * ¿Te has acordado de cerrar el socket?
     */
    public static void metodo2(){
        try{
            ServerSocket ssc = new ServerSocket(7777);
            while(true){
                Socket sc = ssc.accept();
                Calendar now = Calendar.getInstance();
                PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
                salida.println("Hora: " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + 
                    ":" + now.get(Calendar.SECOND));
                sc.close();
            }
        }catch(IOException e1){
            System.err.println(e1);
        }
    }
    
    /**
     * Ejercicio 3 Escribir un servidor espejo de cabeceras HTTP. Tras recibir la petición del
     * navegador, el servidor devolverá al cliente las mismas cabeceras que éste le había enviado
     * en su petición. Por lo tanto, la respuesta del servidor tendrá la forma:
     * Linea de estado: “HTTP/1.0 200 OK \r\n”.
     * “Content-Type: text/plain \r\n\r\n”.
     * ¿Por qué devuelve dos finales de línea en lugar de uno? Cuando tu servidor funcione
     * correctamente puedes comprobar lo que ocurre si eliminas uno de los finales de
     * línea.
     * Todas las cabeceras enviadas previamente por el navegador.
     * ¿Cómo puede detectar el servidor el final de la petición del cliente?
     */
    public static void metodo3(){
        try{
            ServerSocket ssc = new ServerSocket(7777);
            while(true){
                Socket sc = ssc.accept();
                Scanner entrada = new Scanner(sc.getInputStream());
                PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
                String res = "";
                do{
                    res = entrada.nextLine();
                    salida.println(res);
                }while(!res.equals(""));
                sc.close();
            }
        }catch(IOException e1){
            System.err.println(e1);
        }
    }
    
    /**
     * Ejercicio 4a Para completar el estudio de los clientes TCP, escribe un cliente SMTP que se
     * conecte al servidor SMTP de la UPV y te envíe un correo a tu dirección de la UPV. No es
     * necesario que compruebe el contenido de las respuestas del servidor, aunque debe leerlas.
     */
    public static void metodo4(){
        try{
            Socket sc = new Socket("smtp.upv.es",25);
            PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
            Scanner entrada = new Scanner(sc.getInputStream());
            System.out.println(entrada.nextLine());
            

            salida.println("helo redes.upv.es");
            System.out.println(entrada.nextLine());
            salida.println("mail from: jodoldar@inf.upv.es");
            System.out.println(entrada.nextLine());
            salida.println("rcpt to: jodoldar@inf.upv.es");
            System.out.println(entrada.nextLine());
            salida.println("data");
            System.out.println(entrada.nextLine());
            salida.println("subject:Correo de prueba");
            salida.println("Contenido de prueba");
            salida.println(".\n");
            System.out.println(entrada.nextLine());
            salida.println("quit");
            sc.close();
        }catch(IOException e1){
            System.err.println(e1);
        }
    }
    
    /**
     * Ejercicio 4b (Opcional) Completa el cliente anterior para que compruebe si las respuestas
     * del servidor indican que la orden del cliente fue la correcta. En caso de fallo el cliente
     * enviará la orden “QUIT” y finalizará el programa. 
     */
    public static void metodo4b(){
        try{
            Socket sc = new Socket("smtp.upv.es",25);
            String aux;
            PrintWriter salida = new PrintWriter(sc.getOutputStream(),true);
            Scanner entrada = new Scanner(sc.getInputStream());
            
            aux = entrada.nextLine();
            System.out.println(aux);
            if(!aux.substring(0,3).equals("220")){
                System.out.println("Fallo en el proceso");
                salida.println("quit");
                sc.close();
                System.exit(1);
            }
            
            salida.println("helo redes.upv.es");
            aux = entrada.nextLine();
            System.out.println(aux);
            if(!aux.substring(0,3).equals("250")){
                System.out.println("Fallo en el proceso");
                salida.println("quit");
                sc.close();
                System.exit(1);
            }
            
            salida.println("mail from: jodoldar@inf.upv.es");
            aux = entrada.nextLine();
            System.out.println(aux);
            if(!aux.substring(0,3).equals("250")){
                System.out.println("Fallo en el proceso");
                salida.println("quit");
                sc.close();
                System.exit(1);
            }
            
            salida.println("rcpt to: gg");
            aux = entrada.nextLine();
            System.out.println(aux);
            if(!aux.substring(0,3).equals("250")){
                System.out.println("Fallo en el proceso");
                salida.println("quit");
                sc.close();
                System.exit(1);
            }
            
            salida.println("data");
            aux = entrada.nextLine();
            System.out.println(aux);
            if(!aux.substring(0,3).equals("354")){
                System.out.println("Fallo en el proceso");
                salida.println("quit");
                sc.close();
                System.exit(1);
            }
            
            salida.println("subject:Correo de prueba");
            salida.println("Contenido de prueba");
            salida.println("");
            salida.println(".");
            aux = entrada.nextLine();
            System.out.println(aux);
            if(!aux.substring(0,3).equals("250")){
                System.out.println("Fallo en el proceso");
                salida.println("quit");
                sc.close();
                System.exit(1);
            }
            salida.println("quit");
            sc.close();
        }catch(IOException e1){
            System.err.println(e1);
        }
    }
}

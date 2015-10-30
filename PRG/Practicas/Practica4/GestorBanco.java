import java.util.Scanner;
import java.util.Locale; 
import java.io.*;
/**
 * Clase GestorBanco: clase que prueba las clases Banco y Cuenta. Utiliza un sistema de menús para
 * permitir al usuario probar e interactuar con dichas clases.
 * @author PRG 
 * @version Curso 2014/15
 */
public class GestorBanco {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in).useLocale(Locale.US);        
        Banco banco = new Banco();              
        Cuenta cuenta = null, enc = null;
        int op, numCuenta;
        double cantidad;
        
        do {
            op = menu(tec);
            switch(op) {
                case 1: // Crear una nueva cuenta
                    do {
                        numCuenta = getNumCuenta(tec);
                        enc = banco.getCuenta(numCuenta);
                        if (enc != null)                         
                            System.out.println("\n***ERROR***: Cuenta " + numCuenta + " ya existe!\n"); 
                    } while (enc != null);
                    cantidad = getCantidad(tec);             
                    cuenta = new Cuenta(numCuenta, cantidad);
                    banco.añadir(cuenta);
                    System.out.println("Información de la cuenta: " + cuenta + "\n");                
                    break;
                   
                case 2: // Ingresar en la cuenta activa
                    try{
                        cantidad = getCantidad(tec);
                        cuenta.ingresar(cantidad);
                        System.out.println("Información de la cuenta: " + cuenta + "\n");
                    }catch(NullPointerException e){
                        System.out.println("ERROR: ¡No hay ninguna cuenta activa! Primero búscala o crea una nueva cuenta");
                    }
                    break;
            
                case 3: // Retirar de la cuenta activa
                    try{
                        cantidad = getCantidad(tec);
                        /*if (cantidad <= cuenta.getSaldo()){ 
                                cuenta.retirar(cantidad);
                            }else{
                                System.out.println("Saldo insuficiente");  
                            }  */
                        cuenta.retirar(cantidad);
                        System.out.println("Información de la cuenta: " + cuenta + "\n");
                    }catch(NullPointerException e2){
                        System.out.println("ERROR: ¡No hay ninguna cuenta activa! Primero búscala o crea una nueva cuenta");
                    }catch(SaldoInsuficienteException e3){
                        System.out.println(e3.getMessage());
                    }
                    break;
                
                case 4: // Buscar cuenta
                    numCuenta = getNumCuenta(tec);
                    enc = banco.getCuenta(numCuenta);
                    if (enc != null) {
                        cuenta = enc;
                        System.out.println("Información de la cuenta: " + cuenta + "\n");
                    } else {
                        System.out.println("\n***ERROR***: Cuenta " + numCuenta + " no encontrada!\n");    
                    }            
                    break;
            
                case 5: // Mostrar la cuenta activa
                    try{
                        String c = cuenta.toString();
                        System.out.println("Información de la cuenta: " + c + "\n");
                    }catch(NullPointerException e3){
                        System.out.println("ERROR: ¡No hay ninguna cuenta activa! Primero búscala o crea una nueva cuenta");
                    }
                    break;
                
                case 6: // Mostrar todas las cuentas
                    System.out.print("\n\nTODAS LAS CUENTAS: \n" + banco + "\n\n");
                    break;
                
                case 7: //Introducir cuentas del archivo
                    System.out.println("Introduce el nombre del archivo de las cuentas: ");
                    String arc = tec.nextLine();
                    Scanner lec = null;
                    try{
                        File a = new File(arc);
                        lec = new Scanner(a).useLocale(Locale.US);
                        banco.cargarFormatoTexto(lec);
                    }catch(FileNotFoundException e){
                        System.out.println("No se ha podido leer el archivo");
                    }finally{
                        if(lec!=null){
                            lec.close();
                        }
                    }
                    break;
                    
                case 8:
                    System.out.println("Introduce el nombre del archivo para guardar las cuentas: ");
                    String arc2 = tec.nextLine();
                    PrintWriter esc = null;
                    try{
                        File a = new File(arc2);
                        esc = new PrintWriter(a);
                        banco.guardarFormatoTexto(esc);
                    }catch(FileNotFoundException e){
                        System.out.println("No se ha podido crear o abrir el archivo");
                    }finally{
                        if(esc!=null){
                            esc.close();
                        }
                    }
                    break;
                    
                case 9:
                    System.out.println("Introduce el nombre del archivo para guardar las cuentas(Fichero binario): ");
                    String arc3 = tec.nextLine();
                    ObjectOutputStream oos = null;
                    try{
                        FileOutputStream a = new FileOutputStream(arc3);
                        oos = new ObjectOutputStream(a);
                        banco.guardarFormatoObjeto(oos);
                    }catch(FileNotFoundException e1){
                        System.out.println("No se ha podido crear o abrir el archivo");
                    }catch(IOException e2){
                        System.out.println("Algo falla");
                    }finally{
                        if(oos!=null){
                            try{
                                oos.close();
                            }catch(IOException e3){
                                System.out.println("Algo falla otra vez");
                            }
                        }
                    }
                    break;
                    
                case 10:
                    System.out.println("Introduce el nombre del archivo para cargar las cuentas(Fichero binario): ");
                    String arc4 = tec.nextLine();
                    ObjectInputStream ois = null;
                    try{
                        FileInputStream a = new FileInputStream(arc4);
                        ois = new ObjectInputStream(a);
                        banco.cargarFormatoObjeto(ois);
                    }catch(FileNotFoundException e1){
                        System.out.println("No se encuentra el archivo");
                    }catch(IOException e2){
                        System.out.println("Algo falla dentro");
                    }catch(ClassNotFoundException e3){
                        System.out.println("Algo sigue fallando dentro");
                    }finally{
                        if(ois!=null){
                            try{
                                ois.close();
                            }catch(IOException e4){
                                System.out.println("Algo ha vuelto a fallar");
                            }
                        }
                    }
                    break;
                    
                case 11:
                    System.out.println("Introduce el nombre del archivo para guardar las cuentas: ");
                    String arc5 = tec.nextLine();
                    ObjectOutputStream oos2 = null;
                    try{
                        FileOutputStream a = new FileOutputStream(arc5);
                        oos2 = new ObjectOutputStream(a);
                        banco.guardarFormatoObjetoBanco(oos2);
                    }catch(FileNotFoundException e1){
                        System.out.println("No se puede crear o guardar el fichero");
                    }catch(IOException e2){
                        System.out.println("Hay algun error");
                    }finally{
                        if(oos2!=null){
                            try{
                                oos2.close();
                            }catch(IOException e3){
                                System.out.println("Algo ha fallado");
                            }
                        }
                    }
                    break;
                    
                case 12:
                    System.out.println("Introduce el nombre del archivo para cargar las cuentas(Objeto banco): ");
                    String arc6 = tec.nextLine();
                    ObjectInputStream ois2 = null;
                    try{
                        FileInputStream a = new FileInputStream(arc6);
                        ois2 = new ObjectInputStream(a);
                        banco.cargarFormatoObjetoBanco(ois2);
                    }catch(FileNotFoundException e1){
                        System.out.println("No se encuentra el archivo");
                    }catch(IOException e2){
                        System.out.println("Algo falla dentro");
                    }catch(ClassNotFoundException e3){
                        System.out.println("Algo sigue fallando dentro");
                    }finally{
                        if(ois2!=null){
                            try{
                                ois2.close();
                            }catch(IOException e4){
                                System.out.println("Algo ha vuelto a fallar");
                            }
                        }
                    }
                    break;
            
                case 0: // Terminar  
                    System.out.println("\n\nFin!");
            }
        }while(op!=0);
    }

    /**
     * Muestra un menú de opciones por pantalla y permite 
     * que el usuario elija una de ellas.
     * @param tec Scanner para realizar la lectura.
     * @return int, entero que representa la opción elegida.
     */
    private static int menu(Scanner tec) {
        System.out.println("------------- MENU --------------");
        System.out.println(" 0) Terminar");
        System.out.println(" 1) Crear una nueva cuenta");
        System.out.println(" 2) Ingresar en la cuenta activa");
        System.out.println(" 3) Retirar de la cuenta activa");
        System.out.println(" 4) Buscar cuenta");
        System.out.println(" 5) Mostrar la cuenta activa");
        System.out.println(" 6) Mostrar todas las cuentas");
        System.out.println(" 7) Cargar banco desde fichero");
        System.out.println(" 8) Guardar banco a un fichero");
        System.out.println(" 9) Guardar banco a un fichero(Binario)");
        System.out.println(" 10) Cargar banco desde fichero(Binario)");
        System.out.println(" 11) Guardar banco a un fichero(Obj. banco)");
        System.out.println(" 12) Cargar banco desde fichero(Obj. banco)");
        System.out.println("---------------------------------");        
        int opcion = LecturaValida.leerInt(tec, "    Elige una opción (0 - 12): ", 0, 12);         
        return opcion;
    }
 
    /**
     * Realiza la lectura de una cantidad válida (un valor real >=0).
     * @param tec Scanner para realizar la lectura.
     * @return double, real >=0 que representa la cantidad leída.
     */
    private static double getCantidad(Scanner tec) {        
        double cantidad = LecturaValida.leerDoublePos(tec, "\nIntroduce la cantidad: "); 
        return cantidad;
    }
  
    /**
     * Realiza la lectura de un número de cuenta válido (un valor entero de 5 dígitos).
     * @param tec Scanner para realizar la lectura.
     * @return int, entero de 5 dígitos que representa el número de cuenta leído.
     */
    private static int getNumCuenta(Scanner tec) {        
        int numCuenta = LecturaValida.leerInt(tec, "\nIntroduce el número de cuenta (de 5 dígitos): ", 10000, 99999); 
        return numCuenta;
    }
       
}

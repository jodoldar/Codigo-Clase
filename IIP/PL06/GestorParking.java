import java.util.*;
/** Clase GestorParking: gestor de un parking.
  * @author Josep
  * @version Curso 2014/2015
  */
public class GestorParking {   
    /**
    * Muestra un menu de opciones por pantalla y
    * lee desde teclado una opcion valida.
    * @param tec Scanner que representa el teclado.
    * @return int, opcion valida.
    */ 
    public static int menu(Scanner tec){
        System.out.println("\nOpciones: ");
        System.out.println("===========");
        System.out.println("1. Entrar coche. ");
        System.out.println("2. Salir coche.");
        System.out.println("3. Buscar coche.");
        System.out.println("4. Mostrar ocupación");
        System.out.println("5. Vaciar parking");
        System.out.println("6. Finalizar aplicación");
        System.out.print("\nElige una opción: ");
        return tec.nextInt();
    } 
    /**
    * Lectura desde teclado de una hora valida.
    * @param tec Scanner que representa el teclado.
    * @return Hora, hora valida.
    */
    public static Hora nuevaHora(Scanner tec){
        System.out.print("Introduce la hora (0<h<24): ");
        int h = tec.nextInt();
        System.out.print("\nIntroduce los minutos (0<m<60): ");
        int m = tec.nextInt();
        Hora h1 = new Hora(h,m);
        return h1;
    }
    /**
    * Metodo principal.
    * @param args String[].  
    * @throws Exception si ocurre algun error de entrada/salida.
    */
    public static void main(String [] args) throws Exception {
        Scanner tec = new Scanner(System.in).useLocale(Locale.US);
        Parking p1 = new Parking(5,10,0.015);
        boolean bucle = true;
        while(bucle){
            switch(menu(tec)){
                case 1:
                    System.out.print("\nIntroduce la matricula del coche: ");
                    String matricula = tec.next();
                    System.out.println("Introduce la hora de entrada: ");
                    Hora hora = nuevaHora(tec);
                    System.out.print("\nIntroduce la planta deseada: ");
                    int plant = tec.nextInt();
                    boolean b1 = p1.entrarCoche(matricula, hora, plant);
                    System.out.println("\n" + p1.buscarCoche(matricula).toString());
                    if(b1==false){
                        System.out.println("No quedan plazas libres en el parking. ");
                    }
                    break;
                case 2:
                    System.out.print("\nIntroduce la matricula del coche: ");
                    String matricula2 = tec.next();
                    if(p1.buscarCoche(matricula2) == null){
                        System.out.println("\nEl coche no esta en el parking.");
                    }else{
                        System.out.println("Introduce la hora de salida: (Posterior a la de entrada)");
                        Hora hora2 = nuevaHora(tec);
                        int dat2 = p1.buscarCoche(matricula2).getPlanta();
                        int dat1 = p1.buscarCoche(matricula2).getPlaza();
                        double d2 = p1.salirCoche(matricula2, hora2);
                        System.out.println("\nSale de la plaza " + dat1 + " de la planta "
                                + dat2 + " a la hora " + hora2.toString() + ", con coste " + d2);
                    }
                    break;
                case 3:
                    System.out.print("\nIntroduce la matricula del coche: ");
                    String matricula3 = tec.next();
                    if(p1.buscarCoche(matricula3) == null){
                        System.out.println("El coche no esta en el parking ");
                    }else{
                        System.out.println("\n" + (p1.buscarCoche(matricula3)).toString());
                    }
                    break;
                case 4:
                    System.out.println("\n" + p1.toString());
                    break;
                case 5:
                    double d5 = p1.vaciarParking();
                    System.out.println("\nEl coste acumulado es: " + d5);
                    break;
                case 6:
                    System.out.println("\nGracias por usar la aplicacion");
                    bucle = false;
            }
        }
    }
}
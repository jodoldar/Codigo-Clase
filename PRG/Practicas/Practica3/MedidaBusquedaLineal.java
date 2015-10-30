import java.util.*;
/** Clase MedidaBusquedaLineal: analisis empirico del algoritmo de busqueda lineal.
  * @author PRG ETSInf
  * @version Year 2014-2015
  */
public class MedidaBusquedaLineal {
  // Constantes que definen los parametros de medida
  public static final int MAXTALLA = 1000000, INCRTALLA = 100000, INITALLA = 100000, REPETICIONES = 1000;
  public static final double NMS = 1e3;  // relacion micro - nanosegundos

  // Para crear el array
  private static int[] crearArray(int talla) {
    int[] a = new int[talla];
    for (int i = 0; i < talla; i++) a[i] = i;
    return a;
  }

  public static void medidaBusquedaLineal() {   
    long ti = 0, tf = 0, tt = 0; // Tiempos inicial, final y total        
    // Imprimir cabecera de resultados
    System.out.println("# Busqueda lineal. Tiempos en microsegundos");
    System.out.printf("# Talla    Mejor     Peor    Promedio\n");
    System.out.printf("#------------------------------------\n");

    // Este bucle repite el proceso para varias tallas
    for (int t = INITALLA; t <= MAXTALLA; t += INCRTALLA) {
      // Crear el array
      int[] a = crearArray(t);
      
      AlgoritmosMedibles.busquedaLineal(a, a[0]);  // Llamada inicial para evitar sobrecarga en la primera medida
      
      // Estudio del Caso peor: buscar uno que no esté, por ejemplo -1
      tt = 0;                        // Tiempo acumulado inicial a 0
      for (int r = 0; r < REPETICIONES; r++) {
        ti = System.nanoTime();      // Tiempo inicial
        AlgoritmosMedibles.busquedaLineal(a, -1);
        tf = System.nanoTime();      // Tiempo final
        tt += (tf - ti);             // Actualizar tiempo acumulado
      }
      double tPeor = (double) tt / REPETICIONES;         // Tiempo promedio del caso peor
      
      // Estudio del Caso mejor: buscar a[0]
      // OJO: Como es demasiado rápido, las repeticiones se incluyen en la medida de tiempo     
      ti = System.nanoTime();        // Tiempo inicial
      for (int r = 0; r < REPETICIONES; r++)
        AlgoritmosMedibles.busquedaLineal(a, a[0]);
      tf = System.nanoTime();        // Tiempo final
      double tMejor = (double) (tf - ti) / REPETICIONES;   // Tiempo promedio del caso mejor
      
      // Estudio del Caso promedio: buscar un número aleatorio entre 0 y t-1
      tt = 0;                        // Tiempo acumulado inicial a 0
      for (int r = 0; r < REPETICIONES; r++) {
        int aux = (int) Math.floor(Math.random() * t);    // Número aleatorio a buscar
        ti = System.nanoTime();      // Tiempo inicial
        AlgoritmosMedibles.busquedaLineal(a, aux);
        tf = System.nanoTime();      // Tiempo final
        tt += (tf - ti);         // Actualizar tiempo acumulado
      }
      double tPromed = (double) tt / REPETICIONES;       // Tiempo promedio del caso promedio

      // Imprimir resultados
      System.out.printf(Locale.US, "%8d   %4.2f   %8.2f   %8.2f\n", t, tMejor/NMS, tPeor/NMS, tPromed/NMS);

    }
  }

}

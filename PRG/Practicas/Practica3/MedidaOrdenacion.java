/** Clase MedidaOrdenacion: Estudio empirico de costes de los metodos de ordenacion.
  * @author PRG ETSInf
  * @version Year 2014-2015
  */
import java.util.*; 
 
class MedidaOrdenacion {
  public static final int REPETICIONES = 50;
  public static final int INITALLA=10000, MAXTALLA=100000, INCRTALLA=10000;
  
  /* Genera un array de int de talla t con valores comprendidos entre 0 y t.
   * @param int la talla
   * @result int[] el array generado
   */
  private static int[] crearArrayAleatorio(int t) { 
    int[] a = new int[t];
    for(int i=0;i<t;i++){
        a[i]=(int)(Math.random()*t);
    }
    return a;
  }
  
  /* Genera un array de int de talla t ordenado de forma creciente.
   * @param int la talla
   * @result int[] el array generado
   */
  private static int[] crearArrayOrdCreciente(int t) { 
      int[] a= crearArrayAleatorio(t);
      AlgoritmosMedibles.insercion(a);
      return a;
  }

  /* Genera un array de int de talla t ordenado de forma decreciente.
   * @param int la talla
   * @result int[] el array generado
   */
  public static int[] crearArrayOrdDecreciente(int t) { 
    int[] a= crearArrayAleatorio(t);
    AlgoritmosMedibles.insercion(a);
    int[] aux = new int[a.length];
    for(int i=a.length-1,j=0;i>=0 || j<a.length ;i--,j++){
        aux[j]=a[i];
    }
      return aux;
  }

  public static void medidaSeleccion() { 
      System.out.println("# Selección directa. Tiempos en microsegundos");
      System.out.println("#  Talla    Promedio");
      System.out.println("# ------------------");
      for(int j = INITALLA;j<=MAXTALLA;j+=INCRTALLA){
          double tiempo = 0;
          int talla = j;
          for(int i=0;i<REPETICIONES;i+=INCRTALLA){
              int[] a = crearArrayAleatorio(talla);
              talla = a.length;
              double ti = System.nanoTime();
              AlgoritmosMedibles.seleccion(a);
              double tf = System.nanoTime();
              tiempo += (tf-ti);
            }
            System.out.println("   " + j + "   " +(tiempo/REPETICIONES)/1000);
      }
  }

  public static void medidaInsercion() { 
    System.out.println("# Inserción directa. Tiempos en microsegundos");
    System.out.println("#  Talla    Mejor     Peor    Promedio");
    System.out.println("# --------------------------------------");
    for(int j = INITALLA; j<=MAXTALLA; j+=INCRTALLA){
        double tempMed = 0;
        double tempMin = 0;
        double tempMax = 0;
        
        //Caso mejor
        for(int i=1; i<REPETICIONES; i++){
            int[] a = crearArrayOrdCreciente(j);
            double ti = System.nanoTime();
            AlgoritmosMedibles.insercion(a);
            double tf = System.nanoTime();
            tempMin += (tf-ti);
        }
        //Caso promedio
        for(int i=1; i<REPETICIONES; i++){
            int[] a = crearArrayAleatorio(j);
            double ti = System.nanoTime();
            AlgoritmosMedibles.insercion(a);
            double tf = System.nanoTime();
            tempMed += (tf-ti);
        }
        //Caso peor
        for(int i=1; i<REPETICIONES; i++){
            int[] a = crearArrayOrdDecreciente(j);
            double ti = System.nanoTime();
            AlgoritmosMedibles.insercion(a);
            double tf = System.nanoTime();
            tempMax += (tf-ti);
        }
        System.out.printf(Locale.US,"  %6d   %5.2f %10.2f   %9.2f\n",j,(tempMin/REPETICIONES)/1000,
        (tempMax/REPETICIONES)/1000,(tempMed/REPETICIONES)/1000);
    }
  }
  
  public static void medidaMergeSort() { 
     System.out.println("# MergeSort. Tiempos en microsegundos");
    System.out.println("#  Talla    Mejor     Peor    Promedio");
    System.out.println("# --------------------------------------");
    for(int j = INITALLA; j<=MAXTALLA; j+=INCRTALLA){
        double tempMed = 0;
        double tempMin = 0;
        double tempMax = 0;
        
        //Caso mejor
        for(int i=1; i<REPETICIONES; i++){
            int[] a = crearArrayOrdCreciente(j);
            double ti = System.nanoTime();
            AlgoritmosMedibles.mergeSort(a,0,a.length-1);
            double tf = System.nanoTime();
            tempMin += (tf-ti);
        }
        //Caso promedio
        for(int i=1; i<REPETICIONES; i++){
            int[] a = crearArrayAleatorio(j);
            double ti = System.nanoTime();
            AlgoritmosMedibles.mergeSort(a,0,a.length-1);
            double tf = System.nanoTime();
            tempMed += (tf-ti);
        }
        //Caso peor
        for(int i=1; i<REPETICIONES; i++){
            int[] a = crearArrayOrdDecreciente(j);
            double ti = System.nanoTime();
            AlgoritmosMedibles.mergeSort(a,0,a.length-1);
            double tf = System.nanoTime();
            tempMax += (tf-ti);
        }
        System.out.printf(Locale.US,"  %6d   %5.2f %10.2f   %9.2f\n",j,(tempMin/REPETICIONES)/1000,
        (tempMax/REPETICIONES)/1000,(tempMed/REPETICIONES)/1000);
    }
  }

  public static void uso() {
    System.out.println("Uso: java MedidaOrdenacion numero_algoritmo");
    System.out.println("   Donde numero_algoritmo es:");
    System.out.println("   1 -> Insercion");
    System.out.println("   2 -> Seleccion");
    System.out.println("   3 -> MergeSort");
  }

  public static void main(String[] args) {
    int a;
    if (args.length != 1) {
      uso();
      return;
    }
    
    try {
      a = Integer.parseInt(args[0]);
    } catch (Exception e) {
      uso(); return;
    }
    
    switch (a) {
      case 1: medidaInsercion();
              break;
      case 2: medidaSeleccion();
              break;
      case 3: medidaMergeSort();
              break;
      default: uso();
    }
  }

}

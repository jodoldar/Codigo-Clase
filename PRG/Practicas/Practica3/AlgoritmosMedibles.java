/** Clase AlgoritmosMedibles: clase de utilidades con los metodos a analizar.
  * @author PRG ETSInf
  * @version Year 2014-2015
  */
public class AlgoritmosMedibles {

  /** Busqueda lineal
    * @param a array de enteros
    * @param e elemento a buscar
    * @return int, posicion de e en a o -1 si e no esta en a
    */
  public static int busquedaLineal(int[] a, int e) {
    int i = 0;
    while (i < a.length && (a[i] != e)) i++;
    if (i < a.length) return i;
    else return -1;
  }
  
  /** Metodo de ordenacion por seleccion
    * @param a array de enteros
    */
  public static void seleccion(int[] a) {
    int posMin, aux;
    for (int i = 0; i < a.length - 1; i++) {
      posMin = i;
      for (int j = i + 1; j < a.length; j++)
        if (a[j] < a[posMin]) posMin = j;
      aux = a[posMin];
      a[posMin] = a[i];
      a[i] = aux;
    }
  }
  
  /** Metodo de ordenacion por insercion
    * @param a array de enteros
    */
  public static void insercion(int[] a) {
    int aux;
    for (int i = 1; i < a.length; i++) {
      int j = i - 1; aux = a[i];
      while (j >= 0 && a[j] > aux) {
        a[j + 1] = a[j];
        j--;
      }
      a[j + 1] = aux;
    }
  }
  
  /** mergeSort: metodo de ordenacion
    * @param a array de enteros
    * @param i posicion inicial a considerar
    * @param f posicion final a considerar
    */ 
  public static void mergeSort(int[] a, int i, int f) {
    int h;
    if (i < f) {
      h = (f + i) / 2;
      mergeSort(a, i, h);
      mergeSort(a, h + 1, f);
      mezclaNatural(a, i, h, f);
    }
  }

  /** Mezcla natural para mergesort
    * @param a array de enteros
    * @param i posicion inicial a considerar
    * @param f posicion final a considerar
    * @param h posicion mitad a considerar
    */ 
  private static void mezclaNatural(int[] a, int i, int h, int f) {
    int [] aux = new int[f - i + 1];
    int k = 0, iaux = i, jaux = h + 1, kaux;
    while (iaux <= h && jaux <= f) {
      if (a[iaux] < a[jaux]) { aux[k] = a[iaux]; iaux++; }
      else { aux[k] = a[jaux]; jaux++; }
      k++;
    }
    while (iaux <= h) { aux[k] = a[iaux]; iaux++; k++; }
    while (jaux <= f) { aux[k] = a[jaux]; jaux++; k++; }

    kaux = 0;
    for (iaux = i; iaux <= f; iaux++) {
      a[iaux] = aux[kaux];
      kaux++;
    }
  }

}

package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * Implementacion de una TablaHash Enlazada con Listas con PI 
 * @param <C>, tipo de las claves de la Tabla Hash, deben implementar hashCode
 * @param <V>, tipo de los valores asociados a las claves de una Tabla Hash
 * @author (EDA)
 * @version (Febrero 2016)
 */
public class TablaHash<C, V> implements Map<C, V> {

    /** El valor del factor de carga de una Tabla Hash 
     *  (valor por defecto en la clase java.util.HashMap) */
    public static final double FACTOR_CARGA = 0.75;
    
    // TIENE UN array de Listas Con PI de Tipo Generico EntradaHash<C, V>:
    // elArray[h] es la cubeta (lista de colisiones) asociada al indice hash h
    // elArray[h] contiene la referencia a la Lista Con PI donde se encuentran  
    //   todas las entradas cuya clave tiene un indice hash h 
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    
    // TIENE UNA talla que representa el numero de entradas almacenadas.
    protected int talla; 
            
    /** Crea una Tabla Hash vacia, con una capacidad (inicial) máxima  
     *  de tallaMaximaEstimada entradas y factor de carga 0.75
     * @param inicial Capacidad para la Tabla Hash que se va a crear
     */
    @SuppressWarnings("unchecked") 
    public TablaHash(int inicial) {
        int capacidad = siguientePrimo((int) (inicial / FACTOR_CARGA));
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) { 
            elArray[i] = new LEGListaConPI<EntradaHash<C,V>>();
        }
        talla = 0;
    }
    
    // Devuelve un numero primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
    @SuppressWarnings("empty-statement")
    public static final int siguientePrimo(int n) {
        int nn = n;
        if (nn % 2 == 0) { nn++; }
        for (;!esPrimo(nn); nn += 2);
        return nn;
    } 
    
    // Comprueba si n es un numero primo
    protected static final boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) { return false; } // n NO es primo
        }
        return true; // n SI es primo
    } 
    
    // Devuelve el indice hash de la clave c i.e. la cubeta en la que se 
    // debe encontrar la entrada de clave c
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % this.elArray.length;
        if (indiceHash < 0) { indiceHash += this.elArray.length; }
        return indiceHash;
    }
    
    /** Devuelve el valor de la entrada con clave c,
     *  o null si no hay una entrada con clave c en la Tabla
     * @param c Clave de la que se quiere obtener el Valor
     * @return Valor obtenido de la clave, o null si no existe.
     */
    @Override
    public V recuperar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V valor = null;        
        //Busqueda en cub de la entrada de clave c 
        //cuyo valor se quiere recuperar 
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        //Resolucion de la Busqueda: SII esta la Entrada se recupera su valor
        if (!l.esFin()) {
            valor = l.recuperar().valor;
        }
        return valor;
    }
    
    
    /** Elimina la entrada con clave c y devuelve su valor 
     *  asociado, o null si no hay ninguna entrada con clave c 
     * @param c Clave que se quiere eliminar.
     * @return Valor que contenia la clave eliminada, o null si no existe.
     */
    @Override
    public V eliminar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V valor = null;      
        //Busqueda en cubeta de la entrada de clave c a eliminar
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        //Resolucion de la Busqueda: 
        //    SII esta la entrada se elimina, tras recuperar su valor
        if (!l.esFin()) {
            valor = l.recuperar().valor;
            l.eliminar();
            talla--;
        }
        return valor;
    }
        
    /** Inserta la entrada (c, v)  a una Tabla Hash y devuelve  
     *  el antiguo valor asociado a c, o null si no hay ninguna 
     *  entrada con clave c en la Tabla
     * @param c Clave que se quiere insertar
     * @param v Valor asociado a la Clave a insertar
     * @return Valor que contenia la clave, o null si no estaba en la Tabla.
     */
    @Override
    public V insertar(C c, V v) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> l = elArray[pos];
        V antiguoValor = null;
        //Busqueda en cubeta de la Entrada de clave c 
        l.inicio();
        while (!l.esFin() && !l.recuperar().clave.equals(c)) { 
            l.siguiente(); 
        }
        //Resolucion de la busqueda: 
        //si la Entrada (c, v) ya existe se actualiza su valor y sino se inserta
        if (l.esFin()) { // si no esta, insercion efectiva de (c, v)
            l.insertar(new EntradaHash<C,V>(c, v));
            talla++;
            if (factorCarga() > FACTOR_CARGA) {
                rehashing();
            }
        }
        else { 
            //si ya esta, actualiza (valor de la) entrada y retorna el antiguo
            antiguoValor = l.recuperar().valor; l.recuperar().valor = v;
        }
        return antiguoValor;
    }
    /** 
     * Comprueba si una Tabla Hash esta vacia, i.e. si tiene 0 entradas.
     * @return  True - Si la Tabla está vacia. False - Si la tabla NO está vacía.
     */
    @Override
    public boolean esVacio() { return talla == 0; }
    
    /** 
     * Devuelve la talla, o numero de entradas, de una Tabla Hash.
     * @return Talla de la Tabla
     */
    @Override
    public int talla() { return talla; } 
    	
    /** Devuelve el factor de carga actual de una Tabla Hash, i.e. la longitud
     *  media de sus cubetas
     * @return Factor de Carga de una Tabla Hash.
     */
    public final double factorCarga() {
        return (double) talla/elArray.length;
    }
	
    // rehashing: crea una nueva tabla con un array de talla aprox.
    // el doble y reubica las entradas
    @SuppressWarnings("unchecked")
    protected final void rehashing() {
        TablaHash<C,V> aux = new TablaHash((int) (elArray.length*2*FACTOR_CARGA));
	ListaConPI<C> claves = claves();
        for(claves.inicio();!claves.esFin();claves.siguiente()){
            aux.insertar(claves.recuperar(), recuperar(claves.recuperar()));
        }
        this.elArray = aux.elArray;
    } 

    /** 
     * Devuelve una ListaConPI con las talla() claves de una Tabla Hash
     * @return Lista con el conjunto de todas las Claves de la Tabla.
     */
    @Override
    public ListaConPI<C> claves() {
        ListaConPI<C> res = new LEGListaConPI<>();
        for(int i=0;i<elArray.length;i++){
            if(!elArray[i].esVacia()){
                for(elArray[i].inicio();!elArray[i].esFin();elArray[i].siguiente()){
                    res.insertar(elArray[i].recuperar().clave);
                }
            }
        }
        return res;
    }
   
    /** 
     * Calcula la desviacion tipica de las longitudes de las listas
     * @return Desviación típica de la tabla.
     */
    public final double desviacionTipica() {
        double media = factorCarga();
        double sum = 0;
        for(int i=0;i<elArray.length;i++){
            sum += Math.pow((elArray[i].talla() - media),2);
        }
        sum = sum/elArray.length;
        return Math.sqrt(sum);
		
    }

    /** Devuelve un String que representa el histograma de ocupacion:
      * lineas, cada una de ellas con dos valores: 
      * longitudCubeta	NumeroDeCubetas 
      * donde:
      * - las lineas 0 a 8 contienen el numero de cubetas de esa longitud, 
      *   0<=longitud<9
      * la ultima linea (9) contiene el numero de cubetas de longitud 9 o mas
      */      
    public String histograma() {
        String aux = "";
        for(int i=0;i<10;i++){
            int repe = 0;
            if(i<9){
                for(int j=0;j<elArray.length;j++){
                    if(elArray[j].talla()==i){
                        repe++;
                    }
                }
            }else{
                for(int j=0;j<elArray.length;j++){
                    if(elArray[j].talla()>=9){
                        repe++;
                    }
                }
            }
            aux += Integer.toString(i) + "\t" + Integer.toString(repe) + "\n";
        }
        return aux;
		
    }
    
    /*public ListaConPI<C> clavesConValor(V valor){
       ListaConPI<C> res = new LEGListaConPI<>();
       for(int i=0; i<elArray.length; i++){
           EntradaHash<C,V> e = (EntradaHash<C,V>) elArray[i];
           while (e!=null){
               if(e.valor.equals(valor)){
                   res.insertar(e.clave);
               }
               e = e.siguiente;
           }
       }
       return res;
    }
    
    public Map<C,V> interseccion(Map<C,V> otro){
        Map<C,V> res = new TablaHash<C,V>(Math.min(talla,otro.talla()));
        for(int i=0; i<elArray.length; i++){
            EntradaHash<C,V> e = (EntradaHash<C,V>) elArray[i];
            while(e!=null){
                V valor = otro.recuperar(e.clave);
                if(valor != null && valor.equals(e.valor)){
                    res.insertar(e.clave, e.valor);
                }
                e = e.siguiente;
            }
        }
        return res;
    }*/
    
    public int masDeLaMedia(){
        int res = 0;
        double media = factorCarga();
        for(int i=0;i<elArray.length;i++){
            if(elArray[i].talla()>media){
                res++;
            }
        }
        return res;
    }
}
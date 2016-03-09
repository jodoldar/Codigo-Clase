package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/**
 *
 * @author Josep Dols
 */
public class TablaHash<C,V> implements Map<C,V> {
    //Array de listas (con PI) de entradas
    private ListaConPI<EntradaHash<C,V>> elArray[];
    //Número de datos almacenados en la tabla
    private int talla;
    
    public TablaHash(int tallaMaximaEstimada){
        int capacidad = siguientePrimo((int) (tallaMaximaEstimada/0.75));
        elArray = new LEGListaConPI[capacidad];
        for(int i=0; i<elArray.length; i++){
            elArray[i] = new LEGListaConPI<EntradaHash<C,V>>();
        }
        talla=0;
    }
    
    /**
     * Añade la entrada (c,v) y devuelve el antiguo valor 
     * que tenía dicha clave (o null si no tenía ningún 
     * valor asociado)
     * @param c clave
     * @param v valor
     * @return Antiguo valor de la clave
     */
    @Override
    @SuppressWarnings("empty-statement")
    public V insertar(C c, V v) {
        V antiguoValor = null;
        int pos = indiceHash(c); 
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[pos]; 
                                        //Busqueda en cubeta de la entrada de clave c
        for (cubeta.inicio(); !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); cubeta.siguiente()); 
        if (cubeta.esFin()) {           // Si no está insertamos la entrada
            cubeta.insertar(new EntradaHash<C,V>(c, v));
            talla++;                    // Haría falta rehashing si se excede el FC 
        } else {                        // Si ya estaba actualizamos el valor
            antiguoValor = cubeta.recuperar().valor; 
            cubeta.recuperar().valor = v;
        } 
        return antiguoValor;
    }
    /**
     * Elimina la entrada con clave c y devuelve su valor 
     * asociado (o null si no hay ninguna entrada con dicha 
     * clave)
     * @param c Clave a eliminar
     * @return Valor de la clave eliminada
     */
    @Override
    @SuppressWarnings("empty-statement")
    public V eliminar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[pos];
        V valor = null;
        //Busqueda en cubeta de la entrada de clave c
        for(cubeta.inicio();!cubeta.esFin() && !cubeta.recuperar().clave.equals(c);cubeta.siguiente());
        if(!cubeta.esFin()){        //Si la encotramos la borramos
            valor = cubeta.recuperar().valor;
            cubeta.eliminar();
            talla--;
        }
        return valor;
    }
    
    /**
     * Busca la clave c y devuelve su informacion asociada 
     * o null si no hay una entrada con dicha clave
     * @param c Clave que buscar
     * @return Valor de la clave a buscar
     */
    @Override
    @SuppressWarnings("empty-statement")
    public V recuperar(C c) {
        int pos = indiceHash(c); 
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[pos]; 
        // Búsqueda en la cubeta de la entrada de clave c 
        for (cubeta.inicio(); !cubeta.esFin() &&!cubeta.recuperar().clave.equals(c); cubeta.siguiente()); 
        if (cubeta.esFin()){
            return null; // No encontrado
        } else{
            return cubeta.recuperar().valor;
        } // Encontrado
    }

    /**
     * Devuelve true si el Map está vacío
     * @return  true - Si el mapa esta vacío
     *          false - Si el mapa NO está vacío
     */
    @Override
    public boolean esVacio() {
        return talla==0;
    }

    /**
     * Devuelve el número de entradas que contiene el Map
     * @return Valor de la talla del Map
     */
    @Override
    public int talla() {
        return talla;
    }

    @Override
    public ListaConPI<C> claves() {
    }

    private int siguientePrimo(int i) {
    }
    
    /** Calcula la cubeta en la que debe estar un elemento 
     * con clave c. Para ello primero obtiene el valor de 
     * hash (hashCode) y a continuación su índice hash
     * @param c Clave del dato a localizar
     * @return Cubeta en la que se encuentra el dato 
     */
    protected int indiceHash(C c){
        int indiceHash = c.hashCode()%this.elArray.length;
        if(indiceHash < 0){
            indiceHash += this.elArray.length;
        }
        return indiceHash;
    }
        
    public ListaConPI<C> clavesConValor(V valor){
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
    }
}

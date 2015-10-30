package Estructuras;

/**
 * Clase de metodos  para las estructuras ya creadas.
 * @author Josep Dols
 */
public class Principal {
    
    /**
     * Métodos que cuentan la cantidad de enteros iguales que hay en la pila.
     * @param p Pila sobre la que ejecuta la busqueda del entero.
     * @param x Entero a buscar en la pila.
     * @return Cantidad de enteros que hay repetidos en la pila.
     */
    //Version para PilaIntEnlazada.
    public static int contarIgualesPilaInt(PilaIntEnlazada p, int x){
        int cont = 0;
        if(!p.esVacia()){
            int aux = p.desapilar();
            if(aux==x){
                cont++;
            }
            cont += contarIgualesPilaInt(p,x);
            p.apilar(aux);
        }
        return cont;
    }
    //Version para PilaIntArray.
    public static int contarIgualesPilaInt(PilaIntArray p, int x){
        int cont = 0;
        if(!p.esVacia()){
            int aux = p.desapilar();
            if(aux==x){
                cont++;
            }
            cont += contarIgualesPilaInt(p,x);
            p.apilar(aux);
        }
        return cont;
    }
    
    /**
     * Método que cuenta la cantidad de enteros repetidos en la Cola introducida.
     * @param q Cola sobre la que realizar la busqueda.
     * @param x Valor a buscar repetido en la cola.
     * @return Veces que se repite el valor en la cola.
     */
    public static int contarIgualesColaIntEnlazada(ColaIntEnlazada q, int x){
        int cont = 0;
        if(!q.esVacia()){
            int num = q.talla();
            int aux;
            for(int i=0; i<num; i++){
                aux = q.desencolar();
                if(aux==x){
                    cont++;
                }
                q.encolar(aux);
            }
        }
        return cont;
    }
    
    /**
     * Método que elimina los elementos repetidos en una Pila.
     * @param p Pila sobre la que ejecutar el método.
     * @param x Valor del entero sobre el que eliminar los repetidos.
     * @return Elementos que se han eliminado.
     */
    public static int eliminarIgualesPilaIntEnlazada(PilaIntEnlazada p, int x){
        int cont = 0;
        if(!p.esVacia()){
            int aux = p.desapilar();
            cont += eliminarIgualesPilaIntEnlazada(p,x);
            if(aux!=x){
                p.apilar(aux);
            }
        }
        return cont;
    }
    
    /**
     * Método que elimina los enteros repetidos en una Cola.
     * @param p Cola introducida para aplicarle el metodo.
     * @param x Entero a buscar y eliminar repetidos.
     * @return Cantidad de elementos eliminados.
     */
    public static int eliminarIgualesColaIntEnlazada(ColaIntEnlazada p, int x){
        int cont = 0;
        if(!p.esVacia()){
            int aux = p.desencolar();
            cont += eliminarIgualesColaIntEnlazada(p,x);
            if(aux!=x){
                p.encolar(aux);
            }
        }
        return cont;
    }
    
    /**Ejercicio 1: Implementar el método estático "borraBasePila" que dada como argumento una
     * pila elimine la base de la pila dejando el resto de elementos donde están.
     * @param p PilaIntArray sobre la que ejecutar el borrado.
     */
    //Version iterativa para PilaIntArray.
    public static int borraBasePila(PilaIntArray p){
        PilaIntArray otra = new PilaIntArray();
        while(!p.esVacia()){
            otra.apilar(p.desapilar());
        }
        int res = otra.desapilar();
        while(!otra.esVacia()){
            p.apilar(otra.desapilar());
        }
        return res;
    }
    //Version iterativa para PilaIntEnlazada.
    public static int borraBasePila(PilaIntEnlazada p){
        PilaIntEnlazada otra = new PilaIntEnlazada();
        while(!p.esVacia()){
            otra.apilar(p.desapilar());
        }
        int res = otra.desapilar();
        while(!otra.esVacia()){
            p.apilar(otra.desapilar());
        }
        return res;
    }
    //Version recursiva para PilaIntEnlazada.
    public static int borraBasePilaR(PilaIntEnlazada p){
        if(p.talla()<=1){
            return p.desapilar();
        }else{
            int noUltimo = p.desapilar();
            int res = borraBasePilaR(p);
            p.apilar(noUltimo);
            return res;
        }
    }
    //Version recursiva para PilaIntArray.
    public static int borraBasePilaR(PilaIntArray p){
        if(p.talla()<=1){
            return p.desapilar();
        }else{
            int noUltimo = p.desapilar();
            int res = borraBasePilaR(p);
            p.apilar(noUltimo);
            return res;
        }
    }
    
    /**
     * Ejercicio 3.- Implementar el metodo estatico "invierteColaRecursivo" que daba como argumento
     * una cola invierta el orden de los elementos que la contienen de manera recursiva.
     * La inversa de una cola con 1 elemento es la propia cola y la inversa con 0 es la cola vacia.
     * @param q Cola sobre la que aplicar la inversion.
     */
    public static void invierteColaRecursivo(ColaIntEnlazada q){
        if(!q.esVacia()){
            int aux = q.desencolar();
            invierteColaRecursivo(q);
            q.encolar(aux);
        }
    }
    
    /**
     * P1 es sombrero de P2 si tiene los mismos elementos de P2 comenzando desde la cima y no tiene mas.
     * @param p1 Primera Pila para ser analizada.
     * @param p2 Segunda Pila para ser analizada.
     * @return true - Si la Pila P1 es sombrero de P2. false - Si no lo son.
     */
    public static boolean esSombrero(PilaIntEnlazada p1, PilaIntEnlazada p2){
        boolean res;
        if(p1.esVacia()){
            res = true;
        }else{
            if(p1.talla()>p2.talla()){
                res = false;
            }else{
                int v1 = p1.desapilar();
                int v2 = p2.desapilar();
                res = (v1==v2) && esSombrero(p1,p2);
                p1.apilar(v1);
                p2.apilar(v2);
            }
        }
        return res;
    }
}
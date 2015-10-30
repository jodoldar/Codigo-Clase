 /**
 *TAD de una cola implementado con un array circular de amplitud variable
 * 
 * @author (Professors LTPP) 
 * @version (curs 2015-16)
 */
import java.util.*;

public class ColaAC <T> implements Cola<T> { 
    //Definicion de los atributos necesarios:
    //elArray, una array de tipo generico \verb"T" para guardar los elementos de la cola
    private T elArray[];
  
    // dos atributos primero y ultimo de tipo entero que mantienen una referencia a los indices donde estan situados el primer y último elemento de la cola. 
    private int primero, ultimo;
  
    // un atributo talla para representar la cantidad de elementos de la cola.
    private int talla;
    
    final static int MAX = 200;
  
    /**Constructor de Cola */
    //El compilador nos avisa (warning) de que el tipo puro correspondiente a T en (1) se aplicara
    //en tiempo de ejecucion. Con esta directiva le decimos que no nos muestre el aviso ya que la coercion es segura.
    @SuppressWarnings({"unchecked"})
    public ColaAC () {
        elArray = (T[]) (new Object [MAX]); //(1)
        this.primero = 0;
        this.ultimo = -1;
        this.talla = 0;
        //COMPLETAR
    }//Fin del constructor ColaAC<T>

  
    /** Metodo privado para ampliar el array si hace falta**/
    @SuppressWarnings({"unchecked"})
    private void ampliarElArray(){
    
        T[]  arrayAux = (T[]) (new Object [elArray.length*2]);
        int i = 0,
        tallaAux=talla;
        while(!this.esVacia())
        try{arrayAux [i++] = this.desencolar();
        }catch(Exception e){
            System.out.println("Error al intentar ampliar una cola");
        }
        elArray = arrayAux;
        this.primero = 0;
        this.ultimo = tallaAux-1;
        this.talla = tallaAux;
   
    }//Fin del metodo void ampliarElArray()  
  
  
    // Implementacion de las operaciones del TAD definido en la interfaz Pila <T>:
    // Metodos modificadores del estado de una cola:
  
    /** Inserta el Elemento e en una cola situandolo al final 
     **/
    public void encolar(T e){
        //COMPLETAR
        if(talla==elArray.length){
            ampliarElArray();
        }
        ultimo = incrementar(ultimo);
        elArray[ultimo] = e;
        talla++;
    }//Fin del metodo void encolar(T)
  
    /** Consulta y extrae el primer elemento, solo si la cola no esta vacia.
     **/
    public T desencolar(){
         //COMPLETAR
         if(talla==0){return null;}
         T aux = elArray[primero];
         primero = incrementar(primero);
         talla--;
         return aux;
    }//Fin del metodo T desencolar()
  
    // Metodos consultores del estado de la cola
    /** Devuelve la cantidad de elementos  de la cola 
     **/
    public int talla(){
        //COMPLETAR
        return this.talla;
    }//Fin del metodo T talla
  
    /** Solo si la cola no esta vacia, consulta el primer elemento en cabeza,
     * (el primero en orden de insercion) **/
    public T primero() {
        //COMPLETAR
        if(this.talla==0){return null;}
        return this.elArray[primero];
    }//Fin del metodo T primero()
  
    /** Comprueba si una cola esta vacia 
     **/
    public boolean esVacia(){
         //COMPLETAR
         return this.talla==0;
    }//Fin del metodo boolean esVacia()

    /** Devuelve la siguiente posicion ocupada del array modulo MAX.
      Esta instruccion puede sustituirse por (i+1)%elArray.length**/
    private int incrementar (int i) {
        //COMPLETAR
        int j = (i+1)%MAX;
        return j;
    }

    /** Devuelve el contenido de la cola con el formato 
       <-elem0<-elem1<-elem2<-...<-elemN<- donde N = talla()-1
       Cada elemi se devuelve con el formato que este definido para su tipo
       **/ 
    public String toString (){
        //COMPLETAR
        String aux = "";
        for(int i=0; i<talla; i++) {
            aux += "<- " + (elArray[primero+i]) +" ";
        }
        return aux;
    }//Fin del metodo String toString()
}

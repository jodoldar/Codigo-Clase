import java.util.*;
import java.io.*;
/** Clase Planta: representa un conjunto de plazas de parking en una planta de un parking.
  * @author Josep
  * @version Curso 2014/2015
  */
public class Planta {  
    private Plaza[] plazas;
    private int planta;
    /** Constructor que crea una Planta dados un numero de planta y un numero de plazas por planta.
    * La planta, al comienzo, esta vacia.
    * @param numPlanta int numero de planta, numPlanta >= 0.
    * @param numPlazas int numero de plazas por planta, numPlazas > 0.
    */
    public Planta(int numPlanta, int numPlazas){
        this.planta = numPlanta;
        plazas = new Plaza[numPlazas];
        int i = 0;
        while(i<numPlazas){
            plazas[i] = new Plaza(numPlanta, i);
            i++;
        }
    }
    /** Devuelve el numero de planta.
    * @return int numero de planta del parking.
    */
    public int getPlanta(){
        return this.planta;
    }
    /** Devuelve la primera plaza libre en la planta, o -1 si no hay plazas libres. 
    * @return int, indice de la primera plaza libre en la planta o -1 si no hay plazas libres.
    */
    public int primeraLibre(){
        int i = 0;
        while(i<plazas.length && !(plazas[i].esVacia())){
            i++;
        }
        if(i<plazas.length){
            return i;
        }else {
            return -1;
        }
    }
    /** Entra un coche en la planta y devuelve true, o devuelve false cuando no hay plazas libres 
    * o el coche ya esta en la planta.
    * @param m String matricula del coche.
    * @param h Hora hora de entrada del coche.
    * @return boolean, true si el coche entra o false en caso contrario.
    */
    public boolean entrarCoche(String m, Hora h){
        if(this.primeraLibre()>=0){
            plazas[primeraLibre()].entrarCoche(m,h);
            return true;
        }else {
            return false;
        }
    }
    /** Comprueba si un coche dado esta en la planta.
    * @param m String matricula del coche a buscar.
    * @return Plaza, la plaza que ocupa el coche, si se encuentra, o null si no se encuentra.
    */
    public Plaza buscarCoche(String m){
        int i = 0;
        while(i<plazas.length && !m.equals(plazas[i].getMatricula())){
            i++;
        }
        if(i<plazas.length){
            return plazas[i];
        }else{
            return null;
        }
    }
    /** Saca el coche del parking, devolviendo cuantos minutos ha estado.
    * @param m String matricula del coche. Precondicion: siempre esta.
    * @param h Hora hora de salida. Precondicion: posterior a la hora de entrada del coche.
    * @return int, numero de minutos que el coche ha estado en el parking.
    */
    public int salirCoche(String m, Hora h){
        int a = ((buscarCoche(m)).getHoraEntrada()).aMinutos();
        int b = h.aMinutos();
        int c = b-a;
        (buscarCoche(m)).setHoraEntrada(null);
        (buscarCoche(m)).setMatricula(null);
        return c;
    }
    /** Vacia toda la planta y devuelve el numero total de minutos que los coches han permanecido 
    * en la planta hasta una hora dada.
    * @param h Hora hora en la que todos los coches deben salir. 
    * Precondicion: posterior a la hora de entrada.
    * @return int, numero total de minutos transcurridos.
    */
    public int vaciarPlanta(Hora h){
        int i = 0;
        int min = 0;
        while(i<plazas.length){
            if(!(plazas[i].esVacia())){
                min = min + salirCoche((plazas[i].getMatricula()),h);
            }
            i++;
        }
        return min;
    }
    /** Devuelve un String con la ocupacion del parking, con 'X' ocupada, ' ' libre. <br>
    * Formato: <pre> planta (ocupando 3 posiciones), espacio, ocupacion ("  X" o "   "), espacio, ..., 
    * ocupacion ("  X" o "   "), espacio, '\n'</pre>
    * Ejemplo de formato (5 plazas): <pre> "  2   X       X   X      " </pre>
    * @return String, representacion de la ocupacion de la planta.
    */
    @Override
    public String toString(){
        String marcas;
        marcas = " ";
        int i = 0;
        while(i<plazas.length){
            if(plazas[i].esVacia()){
                marcas = marcas + "  ";
            }else{
                marcas = marcas + " X";
            }
            i++;
        }
        return (" " + this.planta + marcas);
    }
    public int masDeDosHoras(Hora actual){
        int cont = 0;
        for(int i=0;i<plazas.length;i++){
            if((plazas[i].esVacia())&& actual.compareTo(plazas[i].getHoraEntrada())>120){
                cont++;
            }
        }
        return cont;
    }
}
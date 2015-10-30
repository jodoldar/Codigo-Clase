/**
 * Clase Concordancia
 * @author Josep Dols 
 */

package Practica5.concordancia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Concordancia
{
    private NodoCnc prim;
    private int talla;
    private boolean esOrd;
    private String separadores;
    //separadores = "[\\p{Space}\\p{Punct}\\p{Digit}!?]+";
    //private NodoCnc fin;
    
    /**Constructora de una Concordancia desde un Scanner.
     * @param ent Scanner desde el que se construye la Concordancia.
     * @param ord true si la Concordancia esta ordenada ascendentemente.
     * @param sep String con la descripcion de los separadores de palabras.
     */
    public Concordancia(Scanner ent, boolean ord, String sep){
        this.talla = 0;
        this.esOrd = ord;
        this.separadores = sep;
        this.prim = null;
        for(int i=1; ent.hasNext(); i++){
            String linea = ent.nextLine();
            String aux[] = linea.split(this.separadores);
            for(int j=0; j<aux.length; j++){
                if(this.esOrd){
                    insOrd(aux[j],i);
                }else{
                    insNoOrd(aux[j],i);
                }
            }
        }
    }
    
    /** Constructora de una Concordancia desde una String.
     * @param str String desde la que se construye la Concordancia.
     * @param ord true sii la Concordancia esta ordenada ascendentemente.
     * @param sep String con la descripción de los separadores de palabras. */
    public Concordancia(String str, boolean ord, String sep){
        this(new Scanner(str), ord, sep);
    }
    
    /** Devuelve el numero de elementos de la Concordancia.
     * @return int Talla de la clase Concordancia.                                     
     */
    public int talla(){
        return this.talla;
    }
    
    /** Devuelve si se trata de una Concordancia ordenada o no.
     * @return boolean true - si la Concordancia esta ordenada; false - si no lo esta.
     */
    public boolean esOrdenada(){
        return this.esOrd;
    }
    
    /** Devuelve una String que representa el contenido de la Concordancia.
     * @return String: el contenido de la Concordancia     
     */
    @Override
    public String toString(){
        String res = "";
        NodoCnc aux = prim;
        for(int i=0; i<talla-1; i++){
            String base = ("%-15s(%5d): " + aux.numLins.toString() + "\n");
            res += String.format(base,aux.palabra,aux.numLins.talla());
            aux = aux.siguiente;
        }
        return res;
    }
    
    /**
     * Metodo auxiliar que comprueba si exsiste una palabra.
     * @param pal Palabra a buscar en Concordancia.
     * @return true - Si existe. false - Si no existe.
     */
    private boolean existe(String pal){
        NodoCnc aux = prim;
        for(int i=0; i<talla; i++){
            if(aux.palabra.equals(pal)){
                return true;
            }else if(aux.siguiente != null){
                aux = aux.siguiente;
            }
        }return false;
    }
    
    /** Inserción en la Concordancia. Una nueva palabra se inserta al final si 
     * no existía previamente o si ya existía se añade la línea en la que aparece
     * en la información (cola de líneas) asociada a la palabra. 
     */
    private void insNoOrd(String pal, int numLin){
        boolean exists = false;
        if(prim==null){
            this.prim = new NodoCnc(pal,numLin);
            this.talla++;
        }else{
            exists = this.existe(pal);
            if(exists){
                NodoCnc aux2 = prim;
                NodoCnc ant = aux2;
                boolean encontrado = false;
                for(int i=0; i<talla && !encontrado; i++){
                    encontrado = aux2.palabra.equals(pal);
                    ant = aux2;
                    aux2 = aux2.siguiente;
                }
                ant.numLins.encolar(numLin);
            }else{
                NodoCnc fin = this.prim;
                for(int i=0;i<this.talla-1; i++){
                    fin = fin.siguiente;
                }
                fin.siguiente = new NodoCnc(pal, numLin);
                this.talla++;
            }
        }
    }
    
    /** Inserción en la Concordancia. Una nueva palabra se inserta
     * ordenadamente si no existía previamente o, si ya existía, se 
     * añade la línea en la que aparece en la información (cola de 
     * líneas) asociada a la palabra. 
     */
    private void insOrd(String pal, int numLin){
        boolean exists = false;
        if(prim==null){
            this.prim = new NodoCnc(pal,numLin);
            this.talla++;
        }else{
            exists = this.existe(pal);
            if(exists){
                NodoCnc aux2 = prim;
                NodoCnc ant = aux2;
                boolean encontrado = false;
                for(int i=0; i<this.talla && !encontrado; i++){
                    encontrado = aux2.palabra.equals(pal);
                    ant = aux2;
                    aux2 = aux2.siguiente;
                }
                ant.numLins.encolar(numLin);
            }else{
                boolean encontrado2 = false;
                NodoCnc aux = prim;
                NodoCnc ant2 = aux;
                for(int i=0; i<talla && !encontrado2; i++){
                    if(aux==null){
                        ant2.siguiente = new NodoCnc(pal,numLin, aux);
                    }else if(aux.palabra.compareTo(pal)<0){
                        ant2 = aux;
                        aux = aux.siguiente;
                    }else{
                        //aux = aux.siguiente;
                        ant2.siguiente = new NodoCnc(pal,numLin,aux);
                        encontrado2 = true;
                    }
                }
                this.talla++;
            }
        }
    }
    
    /**
     * Almacena la Concordancia en el fichero de objectos "fichero".
     * @param fichero String: Nombre del fichero de objectos donde almacenar la
     * Concordancia.
     */
    public void almacena(String fichero){
        try {
            File archivo = new File(fichero);
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        }catch (IOException e){
            System.err.println("Ha habido un error.");
        }
    }
    /**
     * Devuelve la Concordancia almacenada en el fichero de objetos "fichero".
     * @param fichero String: Nombre del fichero de objectos del que recuperar 
     * la Concordancia.
     * @return Concordancia
     */
    public static Concordancia recupera(String fichero){
        try{
            File archivo = new File(fichero);
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Concordancia recuperado = (Concordancia)ois.readObject();
            ois.close();
            return recuperado;
        }catch (IOException e) {
            System.err.println("Ha habido un error.");
        }catch(ClassNotFoundException e2){
            System.out.println("No existe la clase buscada.");
        }
        return null;
    }
}

package Estructuras;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Nueva clase creada para los ejercicios finales.
 * @author Josep Dols
 */

/**
 * Ejercicio resumen temas 3, 4 y 5.
 * Se desea implementar una nueva estructura denominada ListaIntOrdenada, en base a ListaPIIntEnlazada
 * -->Características:
 *        *Tiene un método "insertar(e)" que inserta ordenadamente el elemento en la lista.
 *        *Tiene un método "contains(e)" que devuelve cierto (de manera eficiente) si el elemento 
 *          pasado como parámetro está en la lista.
 *        *Tiene un método "eliminar(e)" que elimina el elemento e de la lista de forma eficiente, 
 *          pero si no lo encuentra lanza la excepción de usuario "ElementoNoEncontradoException".
 *        *Tiene un método "guardarT" que guarda la estructura en un fichero de texto cuyo nombre se 
 *          lo pide al usuario y al que pone la extension ".txt"
 *        *Tiene un método "guardarB" que guarda la estructura en un fichero binario cuyo nombre se
 *          lo pide el usuario y al que pone la extensión ".bin"
 *        *Tiene un método ESTÁTICO "cargaT" que devuelve una nueva ListaIntEnlazadaOrdenada leyendo
 *          los datos de un fichero de texto cuyo nombre se lo pide al usuario y al que pone la 
 *          extensión ".txt"
 *        *Tiene un método ESTÁTICO "cargaB" que devuelve una nueva ListaIntEnlazadaOrdenada leyendo
 *          los datos de un fichero binario cuyo nombre se lo pide al usuario y al que pone la
 *          extensión ".bin"
 */
public class ListaIntOrdenada extends ListaPIIntEnlazada implements Serializable{
    
    /**
     * Constructor por defecto para ListaIntOrdenada
     */
    public ListaIntOrdenada(){
        super();
    }
    
    /**
     * Método que inserta un elemento de forma ordenada en la Lista.
     * @param e Elemento que se va a insertar.
     */
    @Override
    public void insertar(int e){
        if(talla==0){
            super.insertar(e);
        }else{
            NodoInt actual = this.primero;
            NodoInt anterior = null;
            while(actual!=null || actual.dato<e){
                anterior = actual;
                actual = actual.siguiente;
            }
            if(anterior==null){
                this.primero = new NodoInt(e,primero);
                //Si estamos insertando en el primero y ese era el PI hay que modificar antPI.
                if(this.antPI==null){
                    this.antPI = primero;
                }
            }else{
                anterior.siguiente = new NodoInt(e,actual);
                //Si he insertado entre PI y antPI se debe de avanzar antPI;
                if(this.antPI.siguiente!=PI){
                    this.antPI = this.antPI.siguiente;
                }
            }
            this.talla++;
        }
    }
    
    /**
     * Método que indica si existe un elemento dentro de la Lista.
     * @param e Entero a buscar.
     * @return true - Si el elemento se encuentra en la lista. false - si no se encuentra.
     */
    public boolean contains(int e){
        boolean encontrado = false;
        NodoInt aux = this.primero;
        while(aux!=null && aux.dato!=e){
            aux = aux.siguiente;
        }
        encontrado = (aux!=null);
        return encontrado;
    }
    
    /**
     * Método que elimina un elemento de la Lista.
     * @param e Elemento a eliminar.
     * @return Elemento eliminado.
     */
    public int eliminar(int e) throws ElementoNoEncontradoException{
        if(this.PI!=null && e==this.PI.dato){
            return super.eliminar();
        }else{
            NodoInt actual = this.primero;
            NodoInt anterior = null;
            while(actual!=null && actual.dato<e){
                anterior = actual;
                actual = actual.siguiente;
            }
            if(actual!=null && actual.dato>e){
                throw new ElementoNoEncontradoException("No esta el elemento");
            }else{
                if(actual==this.primero){
                    this.primero = this.primero.siguiente;
                }else{
                    anterior.siguiente = actual.siguiente;
                    //Si lo que hemos borrado es antPI lo cambiamos a anterior.
                    if(antPI==actual){
                        antPI = anterior;
                    }
                }
                talla--;
                return actual.dato;
            }
        }
    }
    
    /**
     * Método para guardar la lista en un fichero de texto. Se guardará cada elemento en una linea.
     */
    public void guardarT(){
        System.out.println("Introduce un nombre para guardar el fichero de texto: ");
        Scanner teclado = new Scanner(System.in);
        String arc = teclado.nextLine();
        try{
            File f = new File(arc + ".txt");
            PrintWriter pw = new PrintWriter(f);
            NodoInt auxiliar = primero;
            while(auxiliar!=null){
                //Se podria valorar el usar el .toString() de la clase ListaPI para hacerlo todo en
                //una sola instrucción.
                pw.println(auxiliar.dato);
                auxiliar = auxiliar.siguiente;
            }
            pw.close();
        }catch(FileNotFoundException e){
            System.out.println("No se puede crear el archivo");
        }
    }
    
    /**
     * Método para guardar la lista en un fichero binario.
     */
    public void guardarB(){
        System.out.println("Introduce un nombre para guardar el fichero binario: ");
        Scanner teclado = new Scanner(System.in);
        String arc = teclado.nextLine();
        try{
            File f = new File(arc + ".bin");
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
            o.writeObject(this);
            o.close();
        }catch(IOException e){
            System.out.println("No se pudo crear el archivo");
        }
    }
    
    /**
     * Método que lee una ListaIntOrdenada desde un fichero de texto.
     * @return ListaIntOrdenada guardada en le fichero.
     */
    public static ListaIntOrdenada cargaT(){
        System.out.println("Introduce un nombre para abrir el fichero de texto: ");
        Scanner teclado = new Scanner(System.in);
        String arc = teclado.nextLine();
        try{
            File f = new File(arc + ".txt");
            Scanner s = new Scanner(f);
            ListaIntOrdenada l = new ListaIntOrdenada();
            while(s.hasNext()){
                l.insertar(s.nextInt());
            }
            s.close();
            return l;
        }catch(FileNotFoundException e){
            System.out.println("No se puede abrir el archivo");
            return null;
        }
    }
    
    public static ListaIntOrdenada cargaB(){
        System.out.println("Introduce un nombre para abrir el fichero binario: ");
        Scanner teclado = new Scanner(System.in);
        String arc = teclado.nextLine();
        try{
            File f = new File(arc + "-bin");
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
            ListaIntOrdenada l = (ListaIntOrdenada)o.readObject();
            o.close();
            return l;
        }catch(EOFException e1){
            System.out.println("---> Fin de fichero");
        }catch(IOException e2){
            System.out.println("El fichero no existe o no se puede leer");
        }catch(ClassNotFoundException e3){
            System.out.println("El fichero no tiene objetos serializables");
        }catch(ClassCastException e4){
            System.out.println("El fichero no es de tipo ListaIntOrdenda");
        }
        return null;
    }
}

package Practica5.concordancia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Clase concor para llamar a la clase Concordancia.
 * @author Josep Dols
 */
public class concor{
    
    /** Lee desde un fichero binario la Concordancia de un texto.
     * @param args Argumentos del main(-o Ordenado, Nombre de fichero).
     * @param fichero String: Nombre del fichero de objetos donde leer.
     * @return 0
     */
    public static void main(String[] args){
        if(args.length > 2){
            System.out.println("Uso: concor [Opciones] [nombreFichero]");
            System.exit(-1);
        }
        boolean ord = false;
        Scanner ent = new Scanner(System.in);
        if(args.length >= 1){
            if(args[0].equals("-o")){
                ord = true;
            }
            if(!args[args.length-1].equals("-o")){
                try{
                    ent = new Scanner(new File(args[args.length-1]));
                }catch(FileNotFoundException e){
                    System.err.println(e.getMessage());
                    System.exit(-1);
                }
            }
        }
        String sep = "[\\p{Space}\\p{Punct}\\p{Digit}!?]+";
        
        Concordancia cnc = new Concordancia(ent,ord,sep);
        System.out.print(cnc);
        
        if(ent !=null){
            ent.close();
        }
    }
}
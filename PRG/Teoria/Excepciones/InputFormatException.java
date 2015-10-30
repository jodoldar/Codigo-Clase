package Excepciones;

/**
 * Nueva excepcion generica que se crea a partir de la clase Exception
 * @author Josep Dols
 */
public class InputFormatException extends Exception{
    public InputFormatException(String err){
        super(err);
    }
}

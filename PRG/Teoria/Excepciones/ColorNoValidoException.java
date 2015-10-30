package Excepciones;

/**
 *  Nueva excepcion generica que hereda a la clase Exception
 * @author Josep Dols
 */

public class ColorNoValidoException extends Exception{
    public ColorNoValidoException(String err){
        super(err);
    }
}

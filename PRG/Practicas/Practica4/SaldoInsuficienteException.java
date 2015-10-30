/**
 * Una excepción SaldoInsuficienteException se lanza cuando en una Cuenta 
 * se determina que no hay saldo suficiente para realizar una transaccion.
 *
 * @author PRG
 * @version Curso 2014/15
 */
public class SaldoInsuficienteException extends Exception {

    /**
	 * Constructor que crea una excepción SaldoInsuficienteException 
	 * con un mensaje dado. 
     * @param mensaje String que indica el mensaje de error.
     */
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

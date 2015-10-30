/**
 *
 * @author Josep
 */
public class Plaza { 
    private String matricula;
    private Hora horaEntrada;
    private int planta, plaza;
    /** Constructor que inicializa una plaza de parking vacia
    * con un numero de planta y un numero de plaza dados.
    * @param p int numero de planta de la plaza.
    * @param n int numero de plaza.
    */
    public Plaza(int p, int n){
        this.planta = p;
        this.plaza = n;
        this.matricula = null;
        this.horaEntrada = null;
    }
    /** Constructor que inicializa una plaza de parking ocupada.
    * @param m String con la matricula del coche que ocupa la plaza.
    * @param h Hora hora de entrada del coche.
    * @param p int numero de planta de la plaza.
    * @param n int numero de plaza.
    */
    public Plaza(String m, Hora h, int p, int n){
        this.planta = p;
        this.plaza = n;
        this.matricula = m;
        this.horaEntrada = h;
    }
    /** Devuelve la matricula.
    * @return String, matricula del coche que ocupa la plaza o null si esta vacia. 
    */
    public String getMatricula(){
        return this.matricula;
    }
    /** Devuelve la hora de entrada.
    * @return Hora, hora de entrada del coche en la plaza o null si esta vacia. 
    */
    public Hora getHoraEntrada(){
        return this.horaEntrada;
    }
    /** Devuelve el numero de planta.
    * @return int, numero de planta de la plaza.
    */
    public int getPlanta(){
        return this.planta;
    }
    /** Devuelve el numero de plaza.
    * @return int, numero de plaza.
    */
    public int getPlaza(){
        return this.plaza;
    }
    /** Actualiza la matricula.
    * @param m String que indica la matricula del coche. 
    */
    public void setMatricula(String m){
        this.matricula = m;
    }
    /** Actualiza la hora de entrada.
    * @param h Hora que indica la hora de entrada del coche.
    */
    public void setHoraEntrada(Hora h){
        this.horaEntrada = h;
    }
    /** Entra el coche en la plaza.
    * @param m String con la matricula del coche a aparcar en la plaza.
    * @param h Hora que indica la hora de entrada del coche en el parking.
    */
    public void entrarCoche(String m, Hora h){
        this.matricula = m;
        this.horaEntrada = h;
    }
    /** Saca el coche de la plaza.
    */
    public void salirCoche(){
        this.matricula = null;
        this.horaEntrada = null;
    }
    /** Comprueba si la plaza esta vacia.
    * @return boolean, true si esta vacia o false en caso contrario.
    */
    public boolean esVacia(){
        if(this.matricula == null && this.horaEntrada == null){
            return true;
        }else{
            return false;
        }
    }
    /** Devuelve un String representando la plaza.<br>
    * Formato: "Coche con matricula MATRICULA en plaza PLANTA-PLAZA, entrada a las HORAENTRADA".<br>
    * Si la plaza esta vacia, "No hay ningun coche en esta plaza".
    * @return String, representacion de la plaza.
    */
    @Override 
    public String toString(){
        if(this.esVacia() == false){
            return ("Coche con la matricula " + this.matricula + " en plaza " + this.planta + "-" +
                this.plaza + ", entrada a las " + this.horaEntrada);
        }else{
            return ("No hay ningun coche en esta plaza");
        }
    } 
}
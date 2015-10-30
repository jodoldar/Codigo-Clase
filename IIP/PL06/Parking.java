import java.util.*;
import java.io.*;
/** Clase Parking: representa un conjunto de plantas de un parking junto con el coste por minuto.
  * @author Josep
  * @version Curso 2014/2015
  */
public class Parking { 
    private Planta[] plantas;
    private int numPlazas;
    private double coste;
    /** Constructor que crea un parking a partir del numero de plantas, 
    * numero de plazas por planta, y coste en euros por minuto.
    * El parking, al comienzo, esta vacio.
    * @param p int numero de plantas, p > 0.
    * @param n int numero de plazas por planta, n > 0.
    * @param c double coste en euros por minuto, c > 0.
    */
    public Parking(int p, int n, double c){
        int i = 0;
        plantas = new Planta[p];
        while(i<plantas.length){
            plantas[i]= new Planta(i,n);
            i++;
        }
        this.numPlazas = n;
        this.coste = c;
    }
    /** Constructor que crea un parking a partir de los datos de un fichero 
    * cuyo nombre se pasa como parametro.
    * Formato del fichero:
    * <pre>
    * plantas plazas
    * coste
    * planta matricula horas minutos
    * ...
    * planta matricula horas minutos
    * </pre>
    * Los datos son correctos (no hay coches ni plazas repetidos, plantas no completas y correctas, 
	* y horas correctas). 
    * @param nomFich String nombre del fichero con los datos.
    * @throws Exception si ocurre algun error de entrada/salida. 
    */
    public Parking(String nomFich) throws Exception {
        Scanner in = new Scanner(new File(nomFich)).useLocale(Locale.US);
        int p = in.nextInt(), n = in.nextInt(); 
	double c = in.nextDouble();
        plantas = new Planta[p];
        for (int i = 0; i < p; i++)
            plantas[i] = new Planta(i, n);
        numPlazas = n;
        coste = c;
	String mat; 
	int h, m;
        while (in.hasNext()) {
            p = in.nextInt(); mat = in.next(); h = in.nextInt(); m = in.nextInt();
            plantas[p].entrarCoche(mat, new Hora(h, m));
        }
        in.close();
    }
    /** Devuelve el numero de plantas.
    * @return int, numero de plantas del parking.
    */
    public int getPlantas(){
        return this.plantas.length;
    }
    /** Devuelve el numero de plazas por planta.
    * @return int, numero de plazas por planta en el parking.
    */
    public int getNumPlazasPlanta(){
        return this.numPlazas;
    }
    /** Devuelve el coste.
    * @return double, coste del parking (euros/minuto).
    */
    public double getCoste(){
        return this.coste;
    }
    /** Actualiza el coste.
    * @param c double nuevo coste (euros/minuto) del parking, c > 0.
    */
    public void setCoste(double c){
        this.coste = c;
    }
    /** Entra un coche dados su matricula, su hora de entrada y un numero de planta de preferencia, 
    * y devuelve true si el coche entra, o false en otro caso.
    * Si la planta de preferencia no esta disponible, busca plazas libres en las plantas mas cercanas.
    * @param m String matricula del coche a entrar.
    * @param h Hora hora de entrada del coche.
    * @param p int planta de preferencia.
    * @return boolean, true si el coche entra, o false en caso contrario.
    */
    public boolean entrarCoche(String m, Hora h, int p){
        if(plantas[p].primeraLibre() == -1){
            int i = p;
            while(i<plantas.length && (plantas[i].primeraLibre() == -1)){
                i++;
            }
            if(i<plantas.length){
                return plantas[i].entrarCoche(m,h);
            }else{
                i=p;
                while(i>=0 && (plantas[i].primeraLibre() == -1)){
                    i--;
                }
                if(i>-1){
                    return plantas[i].entrarCoche(m,h);
                }else{
                    return false;
                }
            }
        }else{
            return plantas[p].entrarCoche(m,h);
        }
    }
    /** Comprueba si un coche de matricula dada esta en el parking. 
    * @param m String matricula del coche a buscar.
    * @return Plaza, plaza ocupada por el coche, si se encuentra, o null si no se encuentra.
    */
    public Plaza buscarCoche(String m){
        int i = 0;
        while(i<plantas.length && plantas[i].buscarCoche(m) == null){
            i++;
        }
        if(i<plantas.length){
            return plantas[i].buscarCoche(m);
        }else{
            return null;
        }
    }
    /** Saca el coche del parking y devuelve su coste.
    * @param m String matricula del coche a salir. Precondicion: siempre esta.
    * @param h Hora hora de salida del coche. Precondicion: posterior a la hora de entrada.
    * @return double, coste en euros a pagar.
    */
    public double salirCoche(String m, Hora h){
        int a = buscarCoche(m).getHoraEntrada().aMinutos();
        buscarCoche(m).salirCoche();
        int b = h.aMinutos();
        return (b-a)*this.coste;
    }
    /** Vacia todo el parking, suponiendo que son las 23:59, y calcula y devuelve el coste total.
    * @return double, coste total en euros a pagar por todos los coches que salen del parking.
    */
    public double vaciarParking(){
        Hora h1 = new Hora(23,59);
        double a = 0;
        for(int i=0; i<plantas.length; i++){
            a += plantas[i].vaciarPlanta(h1);
        }
        return a*this.coste;
    }
    /** Devuelve un String que representa la ocupacion del parking, con 'X' ocupada, ' ' libre.
    * Debe colocar un encabezamiento con los numeros de plaza correspondientes.<br>
    * Ejemplo: <pre>
    *          "      0   1   2   3   4 
    *             0   X   X       X    
    *             1       X   X       X
    *             2   X   X             " </pre>
    * @return String, representacion del parking.
    */
    @Override
    public String toString(){
        String s1 = "   ";
        for(int i=0; i<getNumPlazasPlanta();i++){
            s1 += " "+i;
        }
        String s2 = "";
        for(int j=0;j<plantas.length;j++){
            s2 += plantas[j].toString() + "\n";
        }
        return (s1 + "\n" + s2);
    }
}
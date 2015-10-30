/** Clase Entrada.
 *  Practica 4 - IIP - ETSINF-UPV
 *  @author Josep Dols
 *  @version Curso 2014/15
 */
public class Entrada {
    // Precio Base a partir del cual se calcula el precio final
    public static final double PRECIO_BASE = 7.60; 
    // Descuento por espectador mayor de 65 años (SENIOR)
    public static final int SENIOR = 65;
    public static final double DESCUENTO_SENIOR = 0.3; 
    // Descuento por ser el dia del espectador 
    public static final double DESCUENTO_DIA_ESPECTADOR = 0.8;
    // Descuento por ser festivo
    public static final double DESCUENTO_DIA_FESTIVO = 1.2; 
    // Descuento por ser vispera de festivo
    public static final double DESCUENTO_VISPERA_FESTIVO = 1.1; 
    // Descuento por ser cliente
    public static final double DESCUENTO_CLIENTE = 0.8; 
    private String titulo, cine;
    private Hora horaSesion;
    /** Crea una Entrada con titulo t, cine c, horas hs,
    *  y minutos ms de la sesion.
    */
    public Entrada(String t, String c, int hs, int ms) {
        this.titulo = t;
        this.cine = c;
        this.horaSesion = new Hora(hs,ms);
    }
    /** Devuelve el titulo de la pelicula de la Entrada. */
    public String getTitulo(){
        return titulo; 
    }
    /** Devuelve el cine de la entrada. */
    public String getCine(){
        return cine; 
    }
    /** Devuelve la hora de la sesion de la Entrada. */
    public Hora getHoraSesion(){ 
        return horaSesion; 
    }
    /** Actualiza a nuevoTitulo el titulo de la pelicula de la Entrada. */
    public void setTitulo(String nuevoTitulo){ 
        titulo = nuevoTitulo; 
    }
    /** Actualiza a nuevoCine el cine de la Entrada. */
    public void setCine(String nuevoCine){ 
        cine = nuevoCine; 
    }
    /** Actualiza a hs la hora de la sesion de la Entrada. */
    public void setHoraSesion(Hora hs){ 
        horaSesion = hs; 
    } 
    /** Devuelve un String con los datos de la Entrada. */
    public String toString(){
        return "\"" + titulo + "\", proyectada en " + cine + ", a las " + horaSesion 
            + "\nPrecio base: " + PRECIO_BASE + " euros";
    }
    /** Comprueba si o es una Entrada con los mismos datos que la Entrada actual. */
    public boolean equals(Object o) {
        return o instanceof Entrada && (this.titulo).equals(((Entrada)o).getTitulo()) && 
            (this.cine).equals(((Entrada)o).getCine()) && 
                (this.horaSesion).equals(((Entrada)o).getHoraSesion());
    }       
    /** Calcula el precio final de la Entrada aplicando al precio base los descuentos segun 
    *  la edad del espectador, el dia de la sesion (espectador, festivo o vispera) y si 
    *  tiene tarjeta de cliente o no.
    */        
    public double precioFinal(int edad, boolean diaEspectador, boolean festivo,
            boolean vispera, boolean tarjetaCliente){
        double precioF;
        if(edad >= SENIOR){
            precioF = PRECIO_BASE*DESCUENTO_SENIOR;
        }else if(diaEspectador){
            precioF = PRECIO_BASE*DESCUENTO_DIA_ESPECTADOR;
        }else if(festivo){
            precioF = PRECIO_BASE*DESCUENTO_DIA_FESTIVO;
            if(tarjetaCliente){
                precioF = precioF*DESCUENTO_CLIENTE;
            }
        }else if(vispera){
            precioF = PRECIO_BASE*DESCUENTO_VISPERA_FESTIVO;
            if(tarjetaCliente){
                precioF = precioF*DESCUENTO_CLIENTE;
            }
        }else{
            if(tarjetaCliente){
                precioF = PRECIO_BASE*DESCUENTO_CLIENTE;
            }else{
                precioF = PRECIO_BASE;
            }
        }
    return precioF;
    }
}
        

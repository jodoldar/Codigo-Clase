/**
 * Clase Hora. 
 * 
 *  @author (Josep Dols) 
 *  @version (Curso 2014-15)
 */
import java.util.*;
public class Hora
{
    // ATRIBUTOS:
    private int h;
    private int m;
    // CONSTRUCTORES:
   /**
    *  Hora correspondiente a las hor horas y min minutos.
    *  Precondicion: 0<=hor<24, 0<=min<60.
    */
    public Hora(int hor, int min){
        h=hor;
        m=min;
    }
   /**
    * Hora (horas y minutos) actual UTC (tiempo coordinado universal).
    */
    public Hora(){
       int tRestante = (int) (System.currentTimeMillis() % (24*3600*1000));
       int segHoy = (int)tRestante/1000;
       int segHoy2 = (int)tRestante/1000;
       int minHoy = (int)segHoy/60;
       segHoy = segHoy%60;
       int horaHoy = minHoy/60;
       minHoy = minHoy%60;
       h = horaHoy;
       m = minHoy;
    } 
    // CONSULTORES Y MODIFICADORES:
   /** Consulta las horas de la Hora */ 
   public int getHora(){
       return h;
    }
   /** Consulta los minutos de la Hora */ 
   public int getMin(){
       return m;
    }   
   /** Modifica las horas de la Hora */ 
   public void setHora(int hor){
       this.h = hor;
    }   
   /** Modifica los minutos de la Hora */ 
   public void setMin(int min){
       this.m = min;
    }   
   // OTROS METODOS:
  /** Devuelve el numero de minutos transcurridos Desde las 00:00 hasta la Hora */
   public int aMinutos(){
       int HoraPasada = h*60;
       int MinPasado = m;
       int TiempoPasado = HoraPasada + MinPasado;
       return TiempoPasado;
    }
    
    /** Devuelve la hora en formato "hh:mm".
   */
   public String toString(){ 
       String horafHoy = "0"+ h;
       int larf1 = horafHoy.length();
       String horafiHoy = horafHoy.substring(larf1 - 2);
       String minfHoy = "0"+ m;
       int larf2 = minfHoy.length();
       String minfiHoy = minfHoy.substring(larf2 - 2);
       return (horafiHoy + ":" + minfiHoy);
   }
   
    /** Compara cronologicamente la Hora y hor. El resultado es un valor:
   *     - negativo si la Hora es anterior a hor,
   *     - cero si son iguales,
   *     - positivo si la Hora es posterior a hor.
   */
  public int compareTo(Hora hor){
      int PrimHora = hor.aMinutos();
      int SegHora = this.aMinutos();
      return (SegHora - PrimHora);
    }
 
  
  /** Devuelve true sii o y la Hora son dos horas iguales. 
   */
   public boolean equals(Object o){
       boolean b1 = o instanceof Hora && this.h == ((Hora) o).getHora() && this.m == ((Hora) o).getMin();
       return b1;
    } 
  
  // ACTIVIDAD EXTRA:      
  /** Devuelve una hora a partir de la descripcion 
   *  textual en formato "hh:mm".
   */ 
 
}

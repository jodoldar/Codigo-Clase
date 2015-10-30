/**
 *
 * @author Josep
 */
import java.util.*;
public class Hora{
    private int h;
    private int m;
    /**
    *  Hora correspondiente a las hor horas y min minutos.
    *  Precondicion: 0<=hor<24, 0<=min<60.
    * @param hor Horas del objeto.
    * @param min Minutos del objeto.
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
    /** Consulta las horas de la Hora 
    * @return h Hora del objeto.
    */ 
    public int getHora(){
        return h;
    }
    /** Consulta los minutos de la Hora 
    * @return m Minutos del objeto
    */ 
    public int getMin(){
        return m;
    }   
    /** Modifica las horas de la Hora 
    * @param hor Nueva hora del objeto
    */ 
    public void setHora(int hor){
        this.h = hor;
    }   
    /** Modifica los minutos de la Hora 
    * @param min Nuevos minutos del objeto
    */ 
    public void setMin(int min){
        this.m = min;
    }   

    /** Devuelve el numero de minutos transcurridos Desde las 00:00 hasta la Hora 
    * @return Tiempo en minutos desde las 00:00
    */
    public int aMinutos(){
        int HoraPasada = h*60;
        int MinPasado = m;
        int TiempoPasado = HoraPasada + MinPasado;
        return TiempoPasado;
    }
    /** Devuelve la hora en formato "hh:mm".
    *@return Hora actual en formato escrito HH:MM
    */
    @Override
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
    * @param hor Hora a comparar
    * @return Minutos de diferencia entre la hora actual y la introducida
    */
    public int compareTo(Hora hor){
        int PrimHora = hor.aMinutos();
        int SegHora = this.aMinutos();
        return (SegHora - PrimHora);
    }  
    /** Devuelve true sii o y la Hora son dos horas iguales. 
    * @param o Hora a comparar
    * @return true, si son la misma hora; false, si son diferentes
    */
    @Override
    public boolean equals(Object o){
        boolean b1 = o instanceof Hora && this.h==((Hora) o).getHora() && this.m==((Hora) o).getMin();
        return b1;
    }      
}


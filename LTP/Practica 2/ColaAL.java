
/**
 * Write a description of class ColaAL here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ColaAL<T> implements Cola<T>
{
    private ArrayList<T> elArray;
    
    private int primero, ultimo;
    private int talla;
    
    public ColaAL(){
        elArray = new ArrayList<T>();
    }
    
    public void encolar(T e){
        elArray.add(e);
    }
    
    public T desencolar(){
        if(elArray.size() == 0) {return null;}
        else {
            T aux = elArray.get(0);
            elArray.remove(0);
            return aux;
        }
    }
    
    public int talla(){
        return elArray.size();
    }
    
    public T primero(){
        return this.elArray.get(0);
    }
    
    public boolean esVacia(){
        return this.elArray.size()==0;
    }
    
    public String toString() {
        String res = "";
        for(int i=0; i<elArray.size(); i++) {
            res += "<- "+elArray.get(i)+" ";
        }
        return res;
    }
}

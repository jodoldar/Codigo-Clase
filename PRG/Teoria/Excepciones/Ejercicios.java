package Excepciones;

import java.io.*;
import java.util.*;

/** Clase Ejercicios, relacionada con el tratamiento de excepciones
 * @author Josep Dols
 */
public class Ejercicios {
/**
 * Programa que pide un número al usuario, y crea un array con la dimensión introducida
 * por el usuario. El programa debe de preveer los problemas posibles.
 */
    public void CreaArray(){
        Scanner t = new Scanner(System.in);
        int valor = -1;
        int[] respuesta = null;
        boolean salir;
        do{
            try{
                System.out.println("Escribe un valor entero positivo: ");
                valor = t.nextInt();
                respuesta = new int[valor];
                salir = true;
            }catch(InputMismatchException e){
                System.out.println("Lo introducido no es un entero");
                t.nextLine();
                salir = false;
            }catch(NegativeArraySizeException e){
                System.out.println("El entero introducido no es positivo o 0");
                salir = false;
            }
        }while(!salir);
    }
/**
 * Crear un nuevo metodo que:
 *  1.- Leer un entero en un rango (sin errores)
 *  2.- Leer un entero en un rango (propagando InputFormatException si hay algun error)
 *  3.- Lea un "color" siendo unicamente validos "AZUL" "VERDE" "ROJO" "AMARILLO" (sin errores)
 *  4.- Lea un "color" siendo unicamente validos "AZUL" "VERDE" "ROJO" "AMARILLO" 
 *      (lanzando ColorNoValidoException si no es valido)
 *  5.- Programa principal para probarlo
 */
    //Metodo 1
    public static void LeeNumero(){
        Scanner t = new Scanner(System.in);
        boolean correcto;
        System.out.println("Introduce el valor minimo posible: ");
        int rangoMin = t.nextInt();
        System.out.println("Introduce el valor maximo posible: ");
        int rangoMax = t.nextInt();
        do{
            try{
                System.out.println("Introduce el entero deseado: ");
                int num = t.nextInt();
                if(num<=rangoMax && num>=rangoMin){
                    correcto = true;
                }else{
                    System.out.println("El numero esta fuera de rango");
                    correcto= false;
                }
                
            }catch(InputMismatchException e1){
                System.out.println("Lo introducido no es un entero");
                correcto = false;
                t.nextLine();
            }
        }while(!correcto);
    }
    
    //Metodo 2
    public static void LeeNumero2() throws InputFormatException{
        Scanner tec = new Scanner(System.in);
        boolean correcto = false;
        System.out.println("Introduce el valor minimo posible: ");
        int rangoMin = tec.nextInt();
        System.out.println("Introduce el valor maximo posible: ");
        int rangoMax = tec.nextInt();
        do{
            System.out.println("Introduce el entero deseado: ");
            int num = tec.nextInt();
            if(num<=rangoMax && num>=rangoMin){
                correcto = true;
            }else{
                throw new InputFormatException("El numero esta fuera de rango");
            }
        }while(!correcto);
    }
    
    //Metodo 3
    public static void LeeColor(){
        Scanner t = new Scanner(System.in);
        boolean correcto;
        do{
            try{
                System.out.println("Introduce el color: ");
                String color = t.nextLine();
                if(color.equals("AZUL")||color.equals("ROJO")||
                        color.equals("VERDE")||color.equals("AMARILLO")){
                    correcto = true;
                }else{
                    System.out.println("El color no es valido");
                    correcto = false;
                }
            }catch(InputMismatchException e1){
                System.out.println("Esto no es un String");
                correcto = false;
                t.nextLine();
            }
        }while(!correcto);
    }
    
    //Metodo 4
    public static void LeeColor2() throws ColorNoValidoException{
        Scanner tec = new Scanner(System.in);
        boolean correcto = false;
        do{
            System.out.println("Introduce el color: ");
            String color = tec.nextLine();
            if(color.equals("AZUL")||color.equals("ROJO")||
                color.equals("VERDE")||color.equals("AMARILLO")){
                correcto = true;
            }else{
                throw new ColorNoValidoException("El color no es valido");
            }
        }while(!correcto);
    }
    
    //Metodo 5(Main)
    public static void main(String [] args){
        try{
            LeeColor2();
        }catch(ColorNoValidoException e1){
            System.out.println(e1);
        }
        
    }
}

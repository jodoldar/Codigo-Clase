
/**
 * Write a description of class PRGString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PRGString
{
    public static boolean esPrefijo(String a, String b){
        if(a.length()>b.length()){
            return false;
        }else if(a.length()==0){ 
            return true;            
        }else{
            return a.charAt(0)==b.charAt(0) && esPrefijo(a.substring(1),b.substring(1));
        }
    }
    public static boolean esSubcadena(String a, String b){
        if(a.length()==0){
            return true;
        }else if(a.length()>b.length()){
            return false;
        }else{
            return esPrefijo(a,b) || esSubcadena(a,b.substring(1));
        }
    }
    private static char quitaAcentos(char c){
        switch(c){
            case 'á':
                return 'a';
            case 'é':
                return 'e';
            case 'í':
                return 'i';
            case 'ó':
                return 'o';
            case 'ú':
                return 'u';
        }
        return c;
    }
    public static boolean esPalindromo(String a){
        if(a.length() <= 1){
            return true;
        }else{
            if(!Character.isLetter(a.charAt(0))){
                a=a.substring(1);
                if(!Character.isLetter(a.charAt(0))){
                a=a.substring(1);
               }
            }
            if(!Character.isLetter(a.charAt(a.length()-1))){
                a=a.substring(0,a.length()-1);
                if(!Character.isLetter(a.charAt(a.length()-1))){
                a=a.substring(0,a.length()-1);
               }
            }
            if(a.charAt(0)>126){
                a=a.replace(a.charAt(0),quitaAcentos(a.charAt(0)));
            }
            if(a.charAt(a.length()-1)>126){
                a=a.replace(a.charAt(a.length()-1),quitaAcentos(a.charAt(a.length()-1)));
            }
            return a.toLowerCase().charAt(0)==a.toLowerCase().charAt(a.length()-1) && esPalindromo(a.substring(1,a.length()-1));
        }
    }public static boolean esPalindromoSA(String a){
        if(a.length() <= 1){
            return true;
        }else{
            if(!Character.isLetter(a.charAt(0))){
                a=a.substring(1);
            }else if(!Character.isLetter(a.charAt(a.length()-1))){
                a=a.substring(0,a.length()-1);
            }
            return a.toLowerCase().charAt(0)==a.toLowerCase().charAt(a.length()-1) && esPalindromoSA(a.substring(1,a.length()-1));
        }
    }
}


/**
 * 
 * @author Josep Dols 
 * @version 1.0
 */
public class UsandoClasesEnvoltorio{
    public static void main(String[] args){
        byte b = new Byte("1");
        short s = new Short((short)2);
        int i = new Integer (3);
        float f = new Float(4.0);
        long l = new Long(5);
        double d = new Double(6.0);
        char c = new Character('a');
        boolean bo = new Boolean("True");
        
        System.out.println("byte b = "+ b);
        System.out.println("short s = "+ s);
        System.out.println("int i = "+ i);
        System.out.println("long l = "+ l);
        System.out.println("float f = "+ f);
        System.out.println("double d = "+ d);   
        System.out.println("boolean bo = "+ bo);
        System.out.println("char c = "+ c); 

        Byte B = (byte)1; 
        Short S = (short)2;
        Integer I = 3; 
        Long L = 4L;
        Float F = 5.0F; 
        Double D = 6.0;
        Boolean Bo = true;
        Character Ch = 'A';
            
        System.out.println("Byte B = "+ B);
        System.out.println("Short S = "+ S);
        System.out.println("Integer I = "+ I);
        System.out.println("Long L = "+ L);
        System.out.println("Float F = "+ F);
        System.out.println("Double D = "+ D);   
        System.out.println("Boolean Bo = "+ Bo);
        System.out.println("Character Ch = "+ Ch);             


    }
}

// CSD Mar 2013 Juansa Sendra
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class LefthandPhilo extends Philo { //ASSIMETRY
    public LefthandPhilo(int id, int cycles, int delay, Table table) {
        super(id,cycles,delay,table);
    }
    protected void delay(long msec) { 
        super.delay(msec);
    }
    public void run(){
        table.begin(id);
        for (int i=0; i<cycles; i++) {
            if(id%2==0){
                table.takeL(id); 
                delay(msegDelay); 
                table.takeR(id);
            }else{
                table.takeR(id); 
                delay(msegDelay); 
                table.takeL(id);
            } 
            delay(table.eat(id));
            table.dropR(id); 
            table.dropL(id);
            delay(table.ponder(id));
        }
        table.end(id);
    }
}
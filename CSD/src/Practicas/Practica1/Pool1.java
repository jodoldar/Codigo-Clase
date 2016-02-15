package Practicas.Practica1;

public class Pool1 extends Pool0{//there cannot be kids alone
    int kidSwimming, insSwimming;
    
    @Override
    public void make(Log log0){
        kidSwimming = 0;
        insSwimming = 0;
        super.make(log0);
    }
    
    @Override
    public synchronized long kidSwims(int id){
       while (insSwimming <= 0){//condicion de espera
           log.enterWait(id);
           espera();
        }
        kidSwimming++;
        notifyAll();
        
        return log.swims(id);
    }
    
    protected void espera(){
        try{
            wait();
        }catch(Exception e){}
    }
    
    @Override
    public synchronized long kidRests(int id) {
        kidSwimming--;
        notifyAll();
        return super.kidRests(id);
    }
    @Override
    public synchronized long instructorSwims(int id) {
        insSwimming++;
        notifyAll();
        return super.instructorSwims(id);
    }
    @Override
    public synchronized long instructorRests(int id) {
        while (kidSwimming != 0 && insSwimming ==1){
            log.leaveWait(id);
            espera();
        }
        insSwimming--;
        notifyAll();
        return log.rests(id);
    }
}
// CSD Mar 2013 Juansa Sendra
 
public class LimitedTable extends RegularTable { //max 4 in dinning-room
    int personas;
    public LimitedTable(Log log) {
        super(log);
        personas = 0;
    }
    public synchronized void enter(int id){
        while(!((personas+1)<=4)){
            log.wenter(id);
            waiting();
        }
        log.enter(id);
        personas++;
        notifyAll();
    }
    public synchronized void exit(int id){
        log.exit(id);
        personas--;
        notifyAll();
    }
}

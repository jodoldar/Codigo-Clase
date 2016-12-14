// CSD Mar 2013 Juansa Sendra
 
public class BothNoneTable extends RegularTable { //both or none
    public BothNoneTable(Log log) {
        super(log);
    }
    
    public synchronized void takeLR(int id){
        while(!log.leftFree(id) || !log.rightFree(id)){
            log.wtakeLR(id);
            waiting();
        }
        log.takeL(id);
        log.takeR(id);
    }
}

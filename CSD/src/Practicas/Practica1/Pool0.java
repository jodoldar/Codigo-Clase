package Practicas.Practica1;

public class Pool0 implements Pool {
	Log log;
        
        @Override
	public void make(Log log0) {
            log=log0;
        }
	
        @Override
	public long begin(int id) {
            return log.begin(id);
        }
        @Override
	public void end(int id)	{
            log.end(id);
        }
	
	@Override
	public long kidSwims(int id) {
            return log.swims(id);
	}
	@Override
	public long kidRests(int id) {
            return log.rests(id);
	}
	@Override
	public long instructorSwims(int id) {
            return log.swims(id);
	}
	@Override
	public long instructorRests(int id) {
            return log.rests(id);
	}
}
public abstract class  Algorithm {

	protected boolean done;
	private Statistics stats;
	
    abstract protected boolean covered();
    abstract public void move();
	abstract public void setData();
	public boolean done() { return done;   } 
}
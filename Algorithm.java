public abstract class  Algorithm {

	protected boolean done = false;
	protected CumulativeStats stats = new CumulativeStats();

    abstract public void setData(Sensor[] s);
    abstract public void move();
    abstract protected boolean covered();
    public boolean done() { return done;   }
}
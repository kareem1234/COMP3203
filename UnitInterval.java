//UnitInterval class
public class UnitInterval {
	
	//Private variables
	private float start = 0;
	private float end = 1;
	private int numSensors = 0;
	private float fOfn = 1;
	private Sensor[] sensors;
	//Test statistics ArrayList
	
	//Constructor
    public UnitInterval(int n, float fOf) {
    	numSensors = n;
    	fOfn = fOf;
    	sensors = new Sensor[numSensors];
    	setSensorRanges();
    	placeSensors();
    }
    
    //Sets sensor ranges based on fOfn value
    private void setSensorRanges()	{
    	float range = fOfn/(2*numSensors);
    	for(int i = 0; i < sensors.length; i++)	{
    		sensors[i] = new Sensor();
    		sensors[i].setRange(range);
    	}
    	System.out.println("Total range is: "+ numSensors*2*range);
    }
    
    //Places the sensors randomly within the interval
    private void placeSensors()	{
    	for(int i = 0; i < sensors.length; i++)	{
    		sensors[i].setX(randomCoordinate());
    	}    	
    }
    
    //Reset sensors to random placement
    
    
    //Provides random x coordinate within interval from start to end
    private float randomCoordinate()	{
    	float f = (float)(Math.random());
    	return  (float)(Math.random());
    }
    
    //Get and set functions
    public float getStart()			{	return start;	}
	public float getEnd()			{	return end;	}
	public int getNumSensors()		{	return numSensors;	}
	public float getfOfn()			{	return fOfn;	} 
	public Sensor[] getSensors()	{	return sensors;	}
	
	public void setStart(float st)	{	start = st;	}
	public void setEnd(float en)	{	end = en;	}
	public void setNumSensors(int n)	{	numSensors = n;	}
	public void setfOfn(float f)	{	fOfn = f;	}
	public void setSensors(Sensor[] s)		{	sensors = s;	}
	
}
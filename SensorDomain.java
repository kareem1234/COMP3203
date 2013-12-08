//SensorDomain class
public class SensorDomain {

	//Private variables
	private String type = "";
	private int numSensors = 0;
	private float fOfn = 1;
	private Sensor[] sensors;

	//Get and set functions
	public String getType()			{	return type;	}
	public int getNumSensors()		{	return numSensors;	}
	public float getfOfn()			{	return fOfn;	}
	public Sensor[] getSensors()	{	return sensors;	}

	public void setNumSensors(int n)	{	numSensors = n;	}
	public void setfOfn(float f)	{	fOfn = f;	}
	public void setSensors(Sensor[] s)		{	sensors = s;	}

	//Constructor
    public SensorDomain(int n, float fOf, String inttype) {
    	type = inttype;
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
    }

    //Places the sensors randomly within the interval
    private void placeSensors()	{
    	for(int i = 0; i < sensors.length; i++)	{
    		sensors[i].setX(randomCoordinate());
    	}

    	if(!type.equals("Unit Line"))	{
    		for(int s = 0; s < sensors.length; s++)	{
    			sensors[s].setY(randomCoordinate());
    		}
    	}
    }

     //Provides random x coordinate within interval [0,1). ****FIX THIS
    private float randomCoordinate()	{
    	return (float)(Math.random());
    }

    //Reset sensors to random placement
	public void resetSensors()	{
		sensors = new Sensor[numSensors];
		setSensorRanges();
		placeSensors();
	}
}
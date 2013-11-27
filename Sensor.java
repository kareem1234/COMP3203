//Sensor class
public class Sensor {

	//Private variables
	private float x;
	private float y;
	private float range;
	
	//Empty constructor
	public Sensor()	{
    	x = 0;
    	y = 0;
    	range = 0;
    }
    
    //Constructor that takes 3 parameters
    public Sensor(float x1, float y1, float ran) {
    	x = x1;
    	y = y1;
    	range = ran;    	
    }
    
    //Constructor that takes 2 parameters
    public Sensor(float x1, float ran) {
    	x = x1;
    	y = 0;
    	range = ran;    	
    }
    
    //Get and set functions  
    public float getX()				{	return x;	}
    public float getY()				{	return y;	}
    public float getRange()			{	return range;	}
    
    public void setX(float x1)		{	x = x1;	}
    public void setY(float y1)		{	y = y1;	}
    public void setRange(float ran)	{	range = ran;	}
    
    }
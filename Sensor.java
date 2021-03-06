//Sensor class
import java.util.Comparator;

public class Sensor implements Comparable<Sensor>{

	//Private variables
	private float x = 0;
	private float y = 0;
	private float range = 0;

	//Get and set functions
    public float getX()	{	return x;	}
    public float getY()	{	return y;	}
    public float getRange()	{	return range;	}

    public void setX(float x1)	{	x = x1;	}
    public void setY(float y1)	{	y = y1;	}
    public void setRange(float ran)	{	range = ran;	}

	//Empty constructor
	public Sensor()	{
    	x = 0;
    	y = 0;
    	range = 0;
    }

	//Constructor that takes 2 parameters
    public Sensor(float x1, float ran) {
    	x = x1;
    	y = 0;
    	range = ran;
    }
    //Constructor that takes 3 parameters
    public Sensor(float x1, float y1, float ran) {
    	x = x1;
    	y = y1;
    	range = ran;
    }

	public Sensor(Sensor as)	{
    	this(as.getX(), as.getY(), as.getRange());
    }

    public int compareTo(Sensor s){
    	if((getX() - s.getX()) < 0)			return -1;
    	else if((getX() - s.getX()) > 0)	return 1;
    	else								return 0;
    }
}
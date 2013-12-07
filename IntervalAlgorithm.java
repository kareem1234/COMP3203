/*

Algorithm #2

move2()	{
	for(each sensor in the array)	{
		if(sensor[i].x = (2*(i+1)-1)/(2n))	return
		sensor[i].x = (2*(i+1)-1)/(2n))
		statistics.movementsum++
	}
}
*/
import java.util.Arrays;

public class IntervalAlgorithm extends Algorithm	{
	private Sensor[] sensors;
	private String location;
	private int index;

    protected boolean covered()	{
    	Sensor[] c = new Sensor[sensors.length];
    	System.arraycopy(sensors, 0, c, 0, sensors.length);

    	Arrays.sort(c);
    	for(int i = 0; i < c.length; i++)	{
    		System.out.println("Sensor X coordinate: " + c[i].getX());
    	}
    	if( (c[0].getX()-c[0].getRange()) > 0)	return false;
    	if( (c[c.length-1].getX()+c[c.length-1].getRange()) < 1 )	return false;

    	for(int i = 0; i < c.length-1; i++)	{
    		if( (c[i].getX()+ c[i].getRange()) < (c[i+1].getX() - c[i+1].getRange()) )	return false;
    	}

    	done = true;
    	return true;
    }

    public void move(){
    	if(covered())	{
			stats.updateAverageStats();
    		return;
    	}
    	else if(location.equals("left")){
    		moveLeft();
    		location = "right";
    	}
    	else{
    		moveRight();
    		location = "left";
    		index++;
    	}
    }

    private void moveLeft(){
    	float oldXcoor = sensors[index].getX();
    	float oldYcoor = sensors[index].getY();

		System.out.println("Sensors range: " + sensors[sensors.length-1].getRange());
		System.out.println("Sensors range: " + sensors[sensors.length-1-index].getRange());

    	if(index == 0){
    		sensors[index].setX(sensors[index].getRange());
    	}else{
    		sensors[index].setX(sensors[index-1].getX() + (sensors[index-1].getRange()*2f));
    	}

    	stats.updateTestStats(oldXcoor, oldYcoor, sensors[index].getX(), sensors[index].getY());
    }

    private void moveRight(){
    	float oldXcoor = sensors[sensors.length-1-index].getX();
    	float oldYcoor = sensors[sensors.length-1-index].getY();

		System.out.println("Sensors range: " + sensors[sensors.length-1].getRange());
		System.out.println("Sensors range: " + sensors[sensors.length-1-index].getRange());
    	if(index == 0){
    		sensors[sensors.length-1].setX(1 - sensors[sensors.length-1].getRange());
    	}else{
    		sensors[sensors.length-1-index].setX(sensors[sensors.length-index].getX() - (sensors[sensors.length-index].getRange()*2f));
    	}

    	stats.updateTestStats(oldXcoor, oldYcoor, sensors[index].getX(), sensors[index].getY());
    }

    public void setData(Sensor[] s){
    	sensors = s;
		Arrays.sort(sensors);
    	location ="left";
    	index = 0;
    	stats.createTest();
    }
}

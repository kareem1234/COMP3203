/* **start = 0
**end = 1
**statistics = statistics class, tracks stats


Algorithm #1


move(i, location)	{
	base case: covered() which checks if interval's covered
	if (left)	{
		moveLeft(i) : move left sensor at index i
		move(i, right)
	else {
		moveRight(length-1-i) : move right sensor at index (length-1)-i
		move(i+1, left)
	}
}

covered()	{
	make a copy of the sensor array
	sort it in ascending x coordinate order
	go through the array 2 elements at a time,
		if the 1st element's x coordinate + it's range < 2nd element's x coordinate - it's range
		return false
		else check next 2 elements
}

moveLeft(i)	{
	if(i=0)	{
		sensor[i].x = start + sensor[i].range
	}
	else	{
		sensor[i].x = sensor[i-1].x + 2*sensor[i].range
	}
	statistics.movementsum++
}

moveRight(i)	{
	if(i=length-1)	{
		sensor[i].x = end - sensor[i].range
	}
	else	{
		sensor[i].x = sensor[i+1].x - 2*sensor[i].range
	}
	statistics.movementsum++
}

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
	private int start = 0;
	private int end = 1;
	private int index;

    protected boolean covered()	{
    	Sensor[] c = copy();
    	Arrays.sort(c);
    	if( (c[0].getX()-c[0].getRange()) > 0)
    			return false;
    	if( (c[c.length-1].getX()+c[c.length-1].getRange()) < 1 )
    			return false;

    	for(int i =0; i< c.length-1; i++)
    		if( (c[i].getX()+ c[i].getRange())  < c[i+1].getX())
    				return false;

    	done = true;
    	return true;
    }

    public Sensor[] copy()	{
    	Sensor[] a = new Sensor[sensors.length];
    	for(int i = 0; i< a.length; i++)
			a[i] = sensors[i];
			return a;

    }

    public void move(){
    	if(covered())	{
			stats.update();
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
    	if(index == 0){
    		sensors[index].setX(start + sensors[index].getRange());
    	}else{
    		sensors[index].setX(sensors[index-1].getX() + 1*sensors[index].getRange());
    	}
    }

    private void moveRight(){
    	if(index == 0){
    		sensors[sensors.length-1-index].setX(end - sensors[index].getRange());
    	}else{
    		sensors[sensors.length-1-index].setX(sensors[sensors.length-index].getX() - 1*sensors[index].getRange());
    	}
    }

    public void setData(Sensor[] s){
    	sensors = s;
		Arrays.sort(sensors);
    	location ="left";
    	index = 0;
    	stats.createTest();
    }
}

import java.util.Arrays;

public class UnitLineAlgorithm extends Algorithm	{
	private Sensor[] sensors;
	private String location;
	private int index;

    public boolean covered()	{
    	Sensor[] c = new Sensor[sensors.length];
    	System.arraycopy(sensors, 0, c, 0, sensors.length);

    	Arrays.sort(c);
    	if((c[0].getX() - c[0].getRange()) > 0)	return false;
    	if((c[c.length-1].getX() + c[c.length-1].getRange()) < 1 )	return false;

    	for(int i = 0; i < c.length-1; i++)	{
    		if(((c[i+1].getX() - c[i+1].getRange()) - (c[i].getX()+ c[i].getRange())) > 0.0000001f )	return false;
    	}

    	done = true;
    	return true;
    }

    public void move(){
    	if(covered())	{
    		return;
    	}

    	if(location.equals("left")){
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
    	float oldX = sensors[index].getX();
    	float oldY = sensors[index].getY();

    	if(index == 0){
    		sensors[index].setX(sensors[index].getRange());
    	}else{
    		sensors[index].setX(sensors[index-1].getX() + (sensors[index-1].getRange()*2f));
    	}

    	stats.updateTestStats(oldX, oldY, sensors[index].getX(), sensors[index].getY());
    }

    private void moveRight(){
    	float oldX = sensors[sensors.length-1-index].getX();
    	float oldY = sensors[sensors.length-1-index].getY();

    	if(index == 0){
    		sensors[sensors.length-1].setX(1 - sensors[sensors.length-1].getRange());
    	}else{
    		sensors[sensors.length-1-index].setX(sensors[sensors.length-index].getX() - (sensors[sensors.length-index].getRange()*2f));
    	}

    	stats.updateTestStats(oldX, oldY, sensors[sensors.length-1-index].getX(), sensors[sensors.length-1-index].getY());
    }

    public void setData(Sensor[] s){
    	sensors = s;
		Arrays.sort(sensors);
    	location ="left";
    	index = 0;
    	stats.createTest();
    }
}

import java.util.*;
import javax.swing.*;

public class UnitPerimeterAlgorithm  extends Algorithm {

	private Sensor[] sensors;
	private ArrayList<Sensor> leftOvers;
	ArrayList<Sensor> top;
	ArrayList<Sensor> bottom;
	ArrayList<Sensor> left;
	ArrayList<Sensor> right;
	private int index;
	private boolean edges;
	private boolean Top, Bottom, Right , Left;
   
	public boolean covered()	{
		if(Top&&Bottom&&Left&&Right)
			return true;
		else
			return false;
	}
	
	public boolean canBeCovered()	{
		float res = sensors.length*sensors[0].getRange()*2-4*sensors[0].getRange();
		if(res >= 4.0f)	return true;
		return false;
	}
	
	private boolean moveToEdges()	{
		if(index>= sensors.length){
			index = 0;
			edges = true;
			return true;
		}
		if(index%4 == 0 ){
			stats.updateTestStats(sensors[index].getX(),sensors[index].getY(),0,sensors[index].getY());
			sensors[index].setX(0);
			left.add(sensors[index]);
		}
		if(index%4 == 1){
			stats.updateTestStats(sensors[index].getX(),sensors[index].getY(),1,sensors[index].getY());
			sensors[index].setX(1);
			right.add(sensors[index]);
		}
		if(index%4 == 2){
			stats.updateTestStats(sensors[index].getX(),sensors[index].getY(),sensors[index].getX(),0);
			sensors[index].setY(0);
			top.add(sensors[index]);
		}
		if(index%4 == 3){
			stats.updateTestStats(sensors[index].getX(),sensors[index].getY(),sensors[index].getX(),1);
			sensors[index].setY(1);
			bottom.add(sensors[index]);
		}
		index++;
		return false;
	}
	
	private Sensor getNextAvailable(String s)	{
		 if(s.equals("left") ){
		 	stats.updateTestStats(right.get(0).getX(),right.get(0).getY(),0,right.get(0).getY() );
		 	right.get(0).setX(0);
			return right.remove(0);
		}else if(s.equals("right")){
			stats.updateTestStats(top.get(0).getX(),top.get(0).getY(),1,top.get(0).getY() );
			top.get(0).setX(1);
			return top.remove(0);
		}else if(s.equals("top")){
			stats.updateTestStats(bottom.get(0).getX(),bottom.get(0).getY(),bottom.get(0).getX(),0 );
			bottom.get(0).setY(0);
			return bottom.remove(0);
		}else{
			leftOvers.get(0).setY(1);
			stats.updateTestStats(leftOvers.get(0).getX(),leftOvers.get(0).getY(),leftOvers.get(0).getX(),1 );
			return leftOvers.remove(0);
		}
	}
	
	private void addLeftOvers(String s, int a)	{
		if(s.equals("left"))	{
			for(int i = a; i<left.size(); i++){
				leftOvers.add(left.get(i));
			}
		}
		if(s.equals("right"))	{
			for(int i = a; i<right.size(); i++){
				leftOvers.add(right.get(i));
			}
		}
	   	if(s.equals("top"))	{
			for(int i = a; i<top.size(); i++){
				leftOvers.add(top.get(i));
			}
	   	}
		if(s.equals("bottom"))	{
			for(int i = a;i<bottom.size(); i++){
				leftOvers.add(bottom.get(i));
			}
		}
	}
	
	private void moveLeft()	{
		if(index == 0)	{
			stats.updateTestStats(left.get(index).getX(),left.get(index).getY(),left.get(index).getX(),left.get(index).getRange() );
	 		left.get(index).setY(left.get(index).getRange());
		}else if( (left.get(index-1).getY()+left.get(index-1).getRange()) >= 1){
	 		addLeftOvers("left", index);
	 		index = 0;
	 		Left = true;
	 		return;
	   	}else if(left.size() == index){
	   	 	left.add(getNextAvailable("left"));
	   	 	stats.updateTestStats(left.get(index).getX(),left.get(index).getY(),left.get(index).getX(),left.get(index).getY()+left.get(index-1).getRange()*2 );
	   	 	left.get(index).setY(left.get(index-1).getY()+left.get(index-1).getRange()*2);
	   	}else if((left.get(index-1).getY()+left.get(index-1).getRange()*2) > 1)	{
	   		stats.updateTestStats(left.get(index).getX(),left.get(index).getY(),left.get(index).getX(),1);
	 		left.get(index).setY(1);
	 	}else {
	 		stats.updateTestStats(left.get(index).getX(),left.get(index).getY(),left.get(index).getX(),left.get(index-1).getY()+left.get(index-1).getRange()*2);
	   	 	left.get(index).setY(left.get(index-1).getY()+left.get(index-1).getRange()*2);
	   	}
	   	index++;
	}
	
	private void moveRight(){
		if(index == 0){
			stats.updateTestStats(right.get(index).getX(),right.get(index).getY(),right.get(index).getX(),right.get(index).getRange());
	 		right.get(index).setY(right.get(index).getRange());
		}else if( (right.get(index-1).getY()+right.get(index-1).getRange()) >= 1){
	 		addLeftOvers("right", index);
	 		index = 0;
	 		Right = true;
	 		return;
	 	}else if(right.size() == index){
	 		right.add(getNextAvailable("right"));
	 		stats.updateTestStats(right.get(index).getX(),right.get(index).getY(),right.get(index).getX(),right.get(index).getY()+right.get(index-1).getRange()*2);
	 		right.get(index).setY(right.get(index-1).getY()+right.get(index-1).getRange()*2);
	 	}else if((right.get(index-1).getY()+right.get(index-1).getRange()*2) > 1){
	 		stats.updateTestStats(right.get(index).getX(),right.get(index).getY(),right.get(index).getX(),1);
	 		right.get(index).setY(1);
	 	}else {
	 		stats.updateTestStats(right.get(index).getX(),right.get(index).getY(),right.get(index).getX(),right.get(index-1).getY()+right.get(index-1).getRange()*2);
	 		right.get(index).setY(right.get(index-1).getY()+right.get(index-1).getRange()*2);
	 	}
	 	index++;
	}
	
	private void moveTop()		{
		if(index == 0){
			stats.updateTestStats(top.get(index).getX(),top.get(index).getY(),top.get(index).getRange(),top.get(index).getY());
	 		top.get(index).setX(top.get(index).getRange());
	 	}else if( (top.get(index-1).getX()+top.get(index-1).getRange()) >= 1){
	 		addLeftOvers("top", index);
	 		index = 0;
	 		Top =  true;
	 		return;
	 	}else if(top.size() == index){
	 		top.add(getNextAvailable("top"));
	 		stats.updateTestStats(top.get(index).getX(),top.get(index).getY(),top.get(index-1).getX()+top.get(index-1).getRange()*2,top.get(index).getY());
	 		top.get(index).setX(top.get(index-1).getX()+top.get(index-1).getRange()*2);
	 	}else if((top.get(index-1).getX()+top.get(index-1).getRange()*2) > 1)	{
	 		stats.updateTestStats(top.get(index).getX(),top.get(index).getY(),1,top.get(index).getY());
	 		top.get(index).setX(1);
	 	}else {
	 		stats.updateTestStats(top.get(index).getX(),top.get(index).getY(),top.get(index-1).getX()+top.get(index-1).getRange()*2,top.get(index).getY());
	 		top.get(index).setX(top.get(index-1).getX()+top.get(index-1).getRange()*2);
	 	}
	 	index++;
	}
	
	private void moveBottom()	{
		if(index == 0){
			stats.updateTestStats(bottom.get(index).getX(),bottom.get(index).getY(),bottom.get(index).getRange(),bottom.get(index).getY());
	 		bottom.get(index).setX(bottom.get(index).getRange());
		}
		else if( (bottom.get(index-1).getX()+bottom.get(index-1).getRange()) >= 1)	{
	 		addLeftOvers("bottom", index);
	 		index = 0;
	 		Bottom = true;
	 		return;
	 	}else if(bottom.size() == index)	{
	 		bottom.add(getNextAvailable("bottom"));
	 		stats.updateTestStats(bottom.get(index).getX(),bottom.get(index).getY(),bottom.get(index-1).getX()+bottom.get(index-1).getRange()*2,bottom.get(index).getY());
	 		bottom.get(index).setX(bottom.get(index-1).getX()+bottom.get(index-1).getRange()*2);
	 	}else if(bottom.get(index-1).getX()+bottom.get(index-1).getRange()*2 > 1)	{
	 		stats.updateTestStats(bottom.get(index).getX(),bottom.get(index).getY(),1,bottom.get(index).getY());
	 		bottom.get(index).setX(1);
	 	}else	{
	 		stats.updateTestStats(bottom.get(index).getX(),bottom.get(index).getY(),bottom.get(index-1).getX()+bottom.get(index-1).getRange()*2,bottom.get(index).getY());
	 	  	bottom.get(index).setX(bottom.get(index-1).getX()+bottom.get(index-1).getRange()*2);
	 	}
	 	index++;
	}
	
	public void move()	{
		if(covered() || !canBeCovered())	{
			done = true;
			return;
		}
		if(!edges)
			moveToEdges();
		else if(!Left)
			moveLeft();
		else if(!Right)
			moveRight();
		else if(!Top)
			moveTop();
		else if(!Bottom)
			moveBottom();
	}
	
	
   public void setData(Sensor[] s)	{
   		done = false;
     	edges = Top = Bottom = Right= Left = false;
        top = new ArrayList<Sensor>();
   		bottom = new ArrayList<Sensor>();
  		left = new ArrayList<Sensor>();
  		right = new ArrayList<Sensor>();
  		leftOvers = new ArrayList<Sensor>();
    	sensors = s;
    	index = 0;
    	stats.createTest();
   		stats.setNumOfSensors(s.length);
    	stats.setRadius(s[0].getRange());
    }
}
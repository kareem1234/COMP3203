import java.util.*;

public class UnitPerimeterAlgorithm  extends Algorithm {

   private Sensor[] sensors;
   private ArrayList<Sensor> leftOvers;
   ArrayList<Sensor> top;
   ArrayList<Sensor> bottom;
   ArrayList<Sensor> left;
   ArrayList<Sensor> right;
   private int Index;
   private boolean edges;
   public boolean canBeCovered(){
   		float sum = sensors.length *sensors[0].getRange()*2;
   		if(sum >= 4.0f)return true;
   		else{
   			System.out.println("cant be covered");
   			return false;}
   }
   private boolean Top, Bottom, Right , Left;
   private boolean moveToEdges(){
   			if(Index>= sensors.length){
   					Index = 0;
   					edges = true;
   					return true;
   			}
   	 		if(Index%4 == 0 ){
   	 			sensors[Index].setX(0.0f);
   	 			left.add(sensors[Index]);
   	 		}
   	 		if(Index%4 == 1){
   	 			sensors[Index].setX(1.0f);
   	 			right.add(sensors[Index]);
   	 		}
   	 		if(Index%4 == 2){
   	 			sensors[Index].setY(0.0f);
   	 			top.add(sensors[Index]);
   	 		}
   	 		if(Index%4 == 3){
   	 			sensors[Index].setY(1.0f);
   	 			bottom.add(sensors[Index]);
   	 		}
   	 		Index++;
   	 		return false;

   }
   private Sensor getNextAvailable(String s){
   		System.out.println("got next");
   		 if(s.equals("left") ){
   		 	right.get(0).setX(0.0f);
   			return right.remove(0);
   		}else if(s.equals("right")){
   			top.get(0).setX(1.0f);
   			return top.remove(0);
   		}else if(s.equals("top")){
   			bottom.get(0).setY(0.0f);
   			return bottom.remove(0);
   		}else{
   			leftOvers.get(0).setY(1.0f);
   			return leftOvers.remove(0);
   		}
   }
   private void addLeftOvers( String s, int a){
   	System.out.println("adding leftOvers: "+s);
   		if(s.equals("left"))
   			for(int i = a; i<left.size(); i++){
   				System.out.println("added");
   				leftOvers.add(left.get(i));
   			}
	if(s.equals("right"))
   			for(int i = a; i<right.size(); i++){
   				leftOvers.add(right.get(i));
   			}
   	if(s.equals("top"))
   			for(int i = a; i<top.size(); i++){
   				leftOvers.add(top.get(i));
   			}
	if(s.equals("bottom"))
   			for(int i = a;i<bottom.size(); i++){
   				leftOvers.add(bottom.get(i));
   			}
   }
   private void moveLeft(){
   	System.out.println("called left");
   	 if(Index == 0)
   	 	left.get(Index).setY(left.get(Index).getRange());
   	  else if( (left.get(Index-1).getY()+left.get(Index-1).getRange()) >= 1){
   	 		addLeftOvers("left", Index);
   	 		Index = 0;
   	 		Left = true;
   	 		return;
   	 }
   	else if(left.size() == Index){
   	 		left.add(getNextAvailable("left"));

   	 		left.get(Index).setY(left.get(Index-1).getY()+left.get(Index-1).getRange()*2);
   	 }

   	 else{
   	 	  left.get(Index).setY(left.get(Index-1).getY()+left.get(Index-1).getRange()*2);
   	 }
   	  Index++;
   }

   private void moveRight(){
   		System.out.println("called right");
   	if(Index == 0){
   		System.out.println("right Index is 0");
   	 	right.get(Index).setY(right.get(Index).getRange());
   	}
   	 else if( (right.get(Index-1).getY()+right.get(Index-1).getRange()) >= 1){
   	 		addLeftOvers("right", Index);
   	 		Index =0;
   	 		Right = true;
   	 		return;
   	 }
    else if (right.size() == Index){
   	 		right.add(getNextAvailable("right"));
   	 		right.get(Index).setY(right.get(Index-1).getY()+right.get(Index-1).getRange()*2);
   	 }
   	 else{
   	 	  right.get(Index).setY(right.get(Index-1).getY()+right.get(Index-1).getRange()*2);
   	 }
   	  Index++;
   }
   public void move(){
   	System.out.println("Index is: "+ Index);
   		if(covered() || (!canBeCovered()) ){
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
   private void moveTop(){
   	 if(Index == 0){
   	 	top.get(Index).setX(top.get(Index).getRange());
   	 }
   	 else if( (top.get(Index-1).getX()+top.get(Index-1).getRange()) >= 1){
   	 		addLeftOvers("top", Index);
   	 		Index =0;
   	 		Top =  true;
   	 		return;
   	 }
   	 else if (top.size() == Index){
   	 		top.add(getNextAvailable("top"));
   	 		top.get(Index).setX(top.get(Index-1).getX()+top.get(Index-1).getRange()*2);
   	 }
   	 else{
   	 	  top.get(Index).setX(top.get(Index-1).getX()+top.get(Index-1).getRange()*2);
   	 }
   	  Index++;
   }
   public boolean covered(){
   	if(Top&&Bottom&&Left&&Right)
   		return true;
   	else
   		return false;
   }

   private void moveBottom(){
   	 if(Index == 0)
   	 	bottom.get(Index).setX(bottom.get(Index).getRange());
   	else if( (bottom.get(Index-1).getX()+bottom.get(Index-1).getRange()) >= 1){
   	 		addLeftOvers("bottom", Index);
   	 		Index =0;
   	 		Bottom = true;
   	 		return;
   	 }
    else if (bottom.size() == Index){
   	 		bottom.add(getNextAvailable("bottom"));
   	 		bottom.get(Index).setX(bottom.get(Index-1).getX()+bottom.get(Index-1).getRange()*2);
   	 }
   	 else{
   	 	  bottom.get(Index).setX(bottom.get(Index-1).getX()+bottom.get(Index-1).getRange()*2);
   	 }
   	 Index++;
   }
   public void setData(Sensor[] s){
   		done = false;
   		stats.createTest();
     	edges = Top = Bottom = Right= Left = false;
        top = new ArrayList<Sensor>();
   		bottom = new ArrayList<Sensor>();
  		left = new ArrayList<Sensor>();
  		right = new ArrayList<Sensor>();
  		leftOvers = new ArrayList<Sensor>();
    	sensors = s;
    	Index =0;
    }
}
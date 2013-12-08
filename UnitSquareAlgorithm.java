import java.util.*;
import javax.swing.*;

//Algorithm to cover area of square
public class UnitSquareAlgorithm extends Algorithm	{

	//Private variables
	private Sensor[] sensors;
	private ArrayList<Sensor> sortedSensors;
	private	int col = 0;
	private	int row = 0;
	private int numCols = 0;
	private int numRows = 0;
	private float dim = 0;

    public boolean covered()	{
    	if(col == 0 && row == numRows + 1)	{
    		done = true;
    		return true;
    	}
    	return false;
    }

	public void move()	{
		if(covered())	{
    		return;
		}

		Collections.sort(sortedSensors, new Comparator<Sensor>()	{
			public int compare(Sensor s1, Sensor s2)	{
				float idealX = (col*dim+dim/2);
				float idealY = (row*dim+dim/2);
				float distance1 = (float)(Math.sqrt(Math.pow((idealX - s1.getX()), 2) + Math.pow((idealY - s1.getY()), 2)));
				float distance2 = (float)(Math.sqrt(Math.pow((idealX - s2.getX()), 2) + Math.pow((idealY - s2.getY()), 2)));
				if(distance1 == distance2)	return 0;
				else if(distance1 < distance2)	return -1;
				else	return 1;
			}
		});

		float idealX = (col*dim+dim/2);
		float idealY = (row*dim+dim/2);
		if(idealX > 1) idealX = 1;
		if(idealY > 1) idealY = 1;
		if(sortedSensors.isEmpty())	{
			JOptionPane.showMessageDialog(null, "The domain cannot be covered with the given number of sensors and radius",
			"Error", JOptionPane.ERROR_MESSAGE);
			done = true;
			return;
		}
		Sensor closest = sortedSensors.get(0);
		stats.updateTestStats(closest.getX(), closest.getY(), idealX, idealY);
		closest.setX(idealX);
		closest.setY(idealY);
		sortedSensors.remove(0);

		if(col != numCols)	col++;
		else {
			row++;
			col = 0;
		}
	}

	public void setData(Sensor[] s)	{
		sensors = s;
		System.out.println(s.length);
		dim = (float)Math.sqrt(2*Math.pow(sensors[0].getRange(), 2));
		numRows = (int)Math.floor(1/dim);
		numCols = (int)Math.floor(1/dim);
		sortedSensors = new ArrayList<Sensor>(Arrays.asList(sensors));
		stats.createTest();
	}

}
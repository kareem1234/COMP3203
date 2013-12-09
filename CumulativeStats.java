import java.util.*;

public class CumulativeStats extends Statistics {

	//Private variables
	int numOfTests = 0;
	int numOfSensors = 0;
	float rad = 0;
	ArrayList<Statistics> testStats = new ArrayList<Statistics>();

	//Get and set methods
	public int getNumOfTests()		{	return numOfTests;	}
	public int getNumOfSensors()	{	return numOfSensors;	}
	public float getRadius()			{	return rad;		}
	public void setNumOfSensors(int num)	{	numOfSensors = num;	}
	public void setRadius(float ra)		{	rad = ra;	}
	public ArrayList<Statistics> getTestStats()	{	return testStats;	}

	public void displayAll()	{
		int i = 1;

		if(numOfTests == 0)	{
			System.out.println("No Statistics Available As No Tests Have Been Performed");
			return;
		}
		
		updateAverageStats();

		System.out.println("Test Case (n = " + numOfSensors + ", radius = " + rad + ")");

    	for(Statistics s: testStats)	{
         System.out.println();
    		System.out.println("---------------------------------------");
    		System.out.println("Test Run #" + i++);
    		System.out.println("Sum of Movements : " + s.getMovementSum());
	    	System.out.println("Max Movement Required : " + s.getMaxMovement());
	    	System.out.println("Min Movement Required : " + s.getMinMovement());
	    	System.out.println("Total Movement Distance : " + s.getTotalMovement());
	  		System.out.println("---------------------------------------");
         System.out.println();
    	}

    	System.out.println("Average Sum of Movements : " + getMovementSum());
		System.out.println("Average Max Movement Required : " + getMaxMovement());
		System.out.println("Average Min Movement Required : " + getMinMovement());
		System.out.println("Average Total Movement Distance : " + getTotalMovement());
      System.out.println();
	}

	public void displayLastTestStats()	{
		if(numOfTests == 0)	{
			System.out.println("No Statistics Available As No Tests Have Been Performed");
			return;
		}
		
		updateAverageStats();

    	Statistics s = testStats.get(0);
    	
    	System.out.println("Test Result (n = " + numOfSensors + ", radius = " + rad + ")");
    	System.out.println("Sum of Movements : " + s.getMovementSum());
	    System.out.println("Max Movement Required : " + s.getMaxMovement());
	    System.out.println("Min Movement Required : " + s.getMinMovement());
	    System.out.println("Total Movement Distance : " + s.getTotalMovement());
	}
	
	public void createTest()	{
		numOfTests++;
		testStats.add(new Statistics());
	}

	public void updateAverageStats()	{
		float avmovesum = 0;
		float avmaxmove = 0;
		float avminmove= 0;
		float avtotalmove = 0;

		for(Statistics s: testStats)	{
			avmovesum += s.getMovementSum();
			avmaxmove += s.getMaxMovement();
			avminmove += s.getMinMovement();
			avtotalmove += s.getTotalMovement();
		}

		setMovementSum(avmovesum/numOfTests);
		setMaxMovement(avmaxmove/numOfTests);
		setMinMovement(avminmove/numOfTests);
		setTotalMovement(avtotalmove/numOfTests);
	}

	public void updateTestStats(float oldX, float oldY, float newX, float newY)	{
		testStats.get(numOfTests-1).update(oldX, oldY, newX, newY);
	}
}
import java.util.*;
import java.util.logging.*;

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
		Logger logger = Logger.getLogger("Log File");
    	FileHandler fh;
    	
    	try	{
    		fh = new FileHandler("./LogFile.log");
    		logger.addHandler(fh);
    		SimpleFormatter format = new SimpleFormatter();
    		fh.setFormatter(format);
    	} catch(Exception e)	{
    		e.printStackTrace();
    	}
    	
		int i = 1;

		if(numOfTests == 0)	{
			logger.info("No Statistics Available As No Tests Have Been Performed");
			return;
		}
		
		updateAverageStats();

		logger.info("Test Case (n = " + numOfSensors + ", radius = " + rad + ")");

    	for(Statistics s: testStats)	{
    		logger.log(null, "");
    		logger.log(null, "---------------------------------------");
    		logger.log(null, "Test Run #" + i++);
    		logger.log(null, "Sum of Movements : " + s.getMovementSum());
	    	logger.log(null, "Max Movement Required : " + s.getMaxMovement());
	    	logger.log(null, "Min Movement Required : " + s.getMinMovement());
	    	logger.log(null, "Total Movement Distance : " + s.getTotalMovement());
	  		logger.log(null, "---------------------------------------");
    	}

    	logger.log(null, "Average Sum of Movements : " + getMovementSum());
		logger.log(null, "Average Max Movement Required : " + getMaxMovement());
		logger.log(null, "Average Min Movement Required : " + getMinMovement());
		logger.log(null, "Average Total Movement Distance : " + getTotalMovement());
		logger.log(null, "");
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
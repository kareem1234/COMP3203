import java.util.*;

public class Statistics {

	//Private variables
	int numOfTests = 0;
	int movementsum = 0;
	float maxMovement = 0;
	float minMovement = 0;
	float totalMovement = 0;
	ArrayList testmovements = new ArrayList();
	
	//Get and set methods
	public int getNumOfTests()		{	return numOfTests;	}
	public int getMovementSum()		{	return movementsum;	}
	public float getMaxMovement()	{	return maxMovement;	}
	public float getMinMovement()	{	return minMovement;	}
	public float getTotalMovement()	{	return totalMovement;	}
	public ArrayList getTestMovements()		{	return testmovements;	}
	
	public void setNumOfTests(int num)		{	numOfTests = num;	}
    public void setMovementSum(int m)		{	movementsum = m;	}
	public void setMaxMovement(float m)		{	maxMovement = m;	}
	public void setMinMovement(float m)		{	minMovement = m;	}
	public void setTotalMovement(float m)	{	totalMovement = m;	}
	public void setTestMovements(ArrayList a)	{	testmovements = a;	}
    
    //Function to display results of statistics
    public void printResults()	{
    	if(numOfTests == 0)	{	System.out.println("No Statistics Available As No Tests Have Been Performed Yet");	}
    	
    	System.out.print("Average Sum of Movements : ");
    	System.out.println(movementsum/numOfTests);
    	System.out.print("Average Max Movement Required : ");
    	System.out.println(maxMovement/numOfTests);
    	System.out.print("AverageMin Movement Required : ");
    	System.out.println(minMovement/numOfTests);
    	System.out.print("Average Total Movement Distance : ");
    	System.out.println(totalMovement/numOfTests);
    }
    
    public static void printAverageResults(ArrayList stats)	{
    	
    	
    }
}
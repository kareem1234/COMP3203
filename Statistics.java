public class Statistics {

	//Private variables
	private float movementsum = 0;
	private float maxMovement = 0;
	private float minMovement = 0;
	private float totalMovement = 0;

	//Get and set methods
	public float getMovementSum()	{	return movementsum;	}
	public float getMaxMovement()	{	return maxMovement;	}
	public float getMinMovement()	{	return minMovement;	}
	public float getTotalMovement()	{	return totalMovement;	}

	public void setMovementSum(float m)		{	movementsum = m;	}
	public void setMaxMovement(float m)		{	maxMovement = m;	}
	public void setMinMovement(float m)		{	minMovement = m;	}
	public void setTotalMovement(float m)	{	totalMovement = m;	}

    //Function to display results of statistics
    public void display()	{
		System.out.println("Sum of Movements : " + movementsum);
	    System.out.println("Max Movement Required : " + maxMovement);
	    System.out.println("Min Movement Required : " + minMovement);
	    System.out.println("Total Movement Distance : " + totalMovement);
    }

    public void update(float oldX, float oldY, float newX, float newY)	{

    	float diffX = Math.abs(oldX - newX);
		float diffY = Math.abs(oldY - newY);

		float distance = (float)(Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)));

		if(distance != 0)	movementsum++;
		if(distance > maxMovement)	maxMovement = distance;
		if(movementsum == 1)	minMovement = distance;
		else if(distance < minMovement)	minMovement = distance;
		totalMovement += distance;
    }
}
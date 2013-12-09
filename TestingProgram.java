import java.io.*;

public class TestingProgram {    
    public static void main(String[] args)	{
    	PrintStream out;
      Algorithm algorithm; 
      SensorDomain domain;     
      int i;
            
      try   {
         out = new PrintStream(new FileOutputStream("testoutput.log"));
      } catch(FileNotFoundException e) { 
         return;
      }
      
      System.setOut(out);
      
      algorithm = new UnitLineAlgorithm();
      domain = new SensorDomain(10, 1, "Unit Line");
      
      for(i=0; i<20; i++)  {
         algorithm.setData(domain.getSensors());
         while(!algorithm.done)  {
            algorithm.move();
         }
         domain.resetSensors();
      }
      algorithm.stats.displayAll();
    }
    
}
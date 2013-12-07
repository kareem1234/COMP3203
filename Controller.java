import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;;

public class Controller extends JFrame {

    public SensorDomain interval;
    public MyCanvas canvas;

    public Controller() throws InterruptedException {
    	interval = new SensorDomain(100,1,"unit line");
    	canvas = new MyCanvas(interval.getSensors(),"unit line");
    	this.setTitle("Sensor Domain Coverage Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
    	this.add(canvas);
    	this.setVisible(true);
    	this.setResizable(false);
    	canvas.draw();
    }
    public void start() throws InterruptedException	{
    	Algorithm unit = new IntervalAlgorithm();
    	unit.setData(interval.getSensors());
    	while(!unit.done()){
    		Thread.sleep(200);
    		unit.move();
    		canvas.draw();
    	}
    	unit.getStats().display();
    }

    public static void main(String[] args) throws InterruptedException {
       Controller c = new Controller();
       c.start();
    }
}

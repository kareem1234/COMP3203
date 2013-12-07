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
    	interval = new SensorDomain(500,30,"unit square");
    	canvas = new MyCanvas(interval.getSensors(),"unit square");
    	this.setTitle("Sensor Domain Coverage Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
    	this.add(canvas);
    	this.setVisible(true);
    	this.setResizable(false);
    	canvas.draw();
    }
    public void start() throws InterruptedException	{
    	Algorithm unit = new UnitSquareAlgorithm();
    	unit.setData(interval.getSensors());
    	while(!unit.done()){
    		Thread.sleep(100);
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

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

    public Controller() {
    	interval = new SensorDomain(12,2,"unit line");
    	canvas = new MyCanvas(interval.getSensors(),"unit line");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
    	this.add(canvas);
    	this.setVisible(true);
    	this.setResizable(false);
    	canvas.draw();
    }
    public void start(){
    	Algorithm unit = new IntervalAlgorithm();
    	unit.setData(interval.getSensors());
    	while(!unit.done()){
    		unit.move();
    		canvas.draw();
    	}
    }

    public static void main(String[] args) {
       Controller c = new Controller();
       c.start();
    }
}

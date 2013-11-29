import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;;

public class Controller extends JFrame {
      
    public UnitInterval interval;
    public MyCanvas canvs;
    
    public Controller() {
    	interval = new UnitInterval(12,2);
    	canvs = new MyCanvas(interval.getSensors(),"unit line");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
    	this.add(canvs);
    	this.setVisible(true);
    	this.setResizable(false);
    	canvs.draw();
    	
    	
    }
    public void start(){
    	Algorithm unit = new MoveInterval(interval.getSensors());
    	while(!unit.done()){
    		unit.move();
    		canvs.draw();
    	}
    	
    }
    
    public static void main(String[] args) {
       Controller c = new Controller();
       c.start();
    }
}

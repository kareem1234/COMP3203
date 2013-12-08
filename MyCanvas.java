import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class MyCanvas extends JPanel {

    private float range = 0;
    private Sensor[] sensors;
    private String map = "";
    private float h;
    private float w;

	public MyCanvas()	{	}

    public MyCanvas(Sensor[] g, String type) {
    	sensors = g;
    	map = type;
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }

 	public void draw() {
 		repaint();
 	}

 	public void setType( String type){
 		map = type;
 	}
 	public void setSensors(Sensor[] s){
 		sensors = s;
 	}

 	public void drawUnitLine(Graphics g){
 		g.setColor(Color.BLACK);
 		this.w = getSize().width - 200;
 		this.h = getSize().height;
 		g.drawLine(100,(int)h/2,(int)(w + 100),(int)h/2);

		int xCoor;

 		for(int i = 0; i < sensors.length; i++)	{
 			xCoor = (int)(sensors[i].getX() * w + 100);

 			g.drawLine(xCoor,(int)h/2, xCoor,(int)h/2);

 			// draw range around point
 			int rad = (int)(sensors[i].getRange() * w);
 			g.drawOval(xCoor - rad,(int)h/2 - rad, 2*rad, 2*rad);
 		}
 	}

 	public void drawSquare(Graphics g){
 		g.setColor(Color.BLACK);
 		this.w = getSize().width - 200;
 		this.h = getSize().height - 200;

		//Draw perimeter of square
		g.drawRect(100, 100, (int)w, (int)h);

		int xCoor, yCoor;

 		for(int i = 0; i < sensors.length; i++)	{
 			xCoor = (int)(sensors[i].getX() * w + 100);
 			yCoor = (int)(sensors[i].getY()* h + 100);

 			//draw point
 			g.drawLine(xCoor, yCoor, xCoor, yCoor);

 			// draw range around point
 			int rad = (int)(sensors[i].getRange() * w);
 			g.drawOval(xCoor - rad, yCoor - rad, 2*rad, 2*rad);
 		}
 	}

  	protected void paintComponent(Graphics g)	{
 		super.paintComponent(g);
 		if(map.equals("Unit Line"))
 			drawUnitLine(g);
 		else if(map.equals("Unit Square"))
 			drawSquare(g);
 		else if(map.equals("Unit Perimeter"))
 			drawSquare(g);
 	}

}

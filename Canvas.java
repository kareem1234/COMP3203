import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Canvas extends JPanel {
    
    private float range;
    private Sensor[] sensors;
    private String map;
    private float h;
    private float w;
    
        
   	
    public Canvas( Sensor[] g, String type ) {
    	sensors = g;
    	map = type;
    	setBorder(BorderFactory.createLineBorder(Color.black));
        
    }
    
     public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
    
 	public void draw(){
 		repaint();
 	}
 	
 	public void setType( String type){
 		map = type;
 	}
 	
 	public void drawUnitLine(Graphics g){
 		g.setColor(Color.BLACK);
 		this.w = getSize().width;
 		this.h = getSize().height;
 		g.drawLine(0,(int)h/2,(int)w,(int)h/2);
 		for(int i = 0; i < sensors.length; i++){
 			//draw point
 			g.drawLine((int)(sensors[i].getX()*w),(int)h/2,(int)(sensors[i].getX()*w),(int)h/2);
 			
 			// draw range around point
 			g.drawOval((int)(sensors[i].getX()*w),(int)h/2,(int)(sensors[i].getRange()*w),(int)(sensors[i].getRange()*h));
 			
 		}
 		
 		
 	}
 	public void drawUnitSquare(Graphics g){
 		g.setColor(Color.BLACK);
 		this.w = getSize().width;
 		this.h = getSize().height;
 		for(int i = 0; i < sensors.length; i++){
 			//draw point
 			g.drawLine((int)(sensors[i].getX()*w),(int)(sensors[i].getY()*h),(int)(sensors[i].getX()*w),(int)(sensors[i].getY()*h));
 			
 			// draw range around point
 			g.drawOval((int)(sensors[i].getX()*w),(int)(sensors[i].getY()*h),(int)(sensors[i].getRange()*w),(int)(sensors[i].getRange()*h));
 		}
 	}
 	
 	public void drawUnitSquarePerimeter(Graphics g){
 		g.setColor(Color.BLACK);
 		this.w = getSize().width;
 		this.h = getSize().height;
 		float offsetX = w/10;
 		float offsetY = h/10;
 		
 	
 		
 		g.drawLine( (int)offsetX, (int)offsetY,(int)(w-offsetX),(int)offsetY );
 		g.drawLine( (int)offsetX, (int)(h-offsetY),(int)(w-offsetX),(int)offsetY );
		g.drawLine( (int)offsetX, (int)offsetY,(int)offsetX,(int)(h-offsetY));
 		g.drawLine( (int)(w-offsetX), (int)offsetY,(int)(w-offsetX),(int)(h-offsetY));
 		
 		float wWidth = (w-offsetX) - offsetX;
 		float hHeight = (h-offsetY) - offsetY;
 		
 		for(int i=0; i< sensors.length; i++){
 			g.drawLine((int)(sensors[i].getX()*wWidth +offsetX),(int)(sensors[i].getY()*hHeight + offsetY),
 			(int)(sensors[i].getX()*wWidth + offsetX),(int)(sensors[i].getY()*hHeight + offsetY));
 			
 			// draw range around point
 			g.drawOval((int)(sensors[i].getX()*w),(int)(sensors[i].getY()*h),(int)(sensors[i].getRange()*w),(int)(sensors[i].getRange()*h));
 		}
 	}
 	
 	protected void paintComponent(Graphics g){
 		if( map.equals("unit line"))
 				drawUnitLine(g);
 		else if(map.equals("unit square"))
 				drawUnitSquare(g);
 	
 		else 
 			drawUnitSquarePerimeter(g);
 	}
   
}

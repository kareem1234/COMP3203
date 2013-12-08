import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller extends JFrame implements ActionListener	{

	private Algorithm algo;
    private SensorDomain domain;
    private MyCanvas canvas;
    private JButton startsim;
    private JButton nVal;
    private JButton fOfnVal;
    private JComboBox<String> simModel;
    private int numSensors = 1;
    private float fOfn = 1;
    private Timer timer;
    private boolean running;

    public Controller() {
    	setTitle("Sensor Domain Coverage Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setResizable(false);
        setLocationRelativeTo(null);

        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel panel = new JPanel();
        panel.setLayout(gridBag);

        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        String[] items = {"Unit Line", "Unit Perimeter", "Unit Square"};
    	simModel = new JComboBox<String>(items);
    	gridBag.setConstraints(simModel, constraints);
    	panel.add(simModel);

    	constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
    	nVal = new JButton("Enter Number of Sensors");
    	nVal.addActionListener(new ActionListener()	{
    		public void actionPerformed(ActionEvent e)	{
    			int value = 0;
    			String n = JOptionPane.showInputDialog("Enter a number of sensors > 0", numSensors);

    			if(n == null)	return;

    			try	{
    				value = Integer.parseInt(n);
    			} catch(NumberFormatException ex)	{
    				JOptionPane.showMessageDialog(null, "Invalid number entered", "Error", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			if(value > 0)	numSensors = value;
    			else	JOptionPane.showMessageDialog(null, "Invalid number entered", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	});

    	gridBag.setConstraints(nVal, constraints);
    	panel.add(nVal);

    	constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
    	fOfnVal = new JButton("Enter Value of F(N)");
    	fOfnVal.addActionListener(new ActionListener()	{
    		public void actionPerformed(ActionEvent e)	{
    			float value = 0;
    			String n = JOptionPane.showInputDialog("Enter a value of f(n) >= 1", fOfn);

    			if(n == null)	return;

    			try	{
    				value = Float.parseFloat(n);
    			} catch(NumberFormatException ex)	{
    				JOptionPane.showMessageDialog(null, "Invalid number entered", "Error", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			if(value >= 1)	fOfn = value;
    			else	JOptionPane.showMessageDialog(null, "Invalid number entered", "Error", JOptionPane.ERROR_MESSAGE);
    		}
    	});
    	gridBag.setConstraints(fOfnVal, constraints);
    	panel.add(fOfnVal);

    	constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
    	startsim = new JButton("START SIMULATION");
    	startsim.addActionListener(this);
    	gridBag.setConstraints(startsim, constraints);
    	panel.add(startsim);

    	constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
    	canvas = new MyCanvas();
    	gridBag.setConstraints(canvas, constraints);
    	panel.add(canvas);

    	add(panel);
    	setVisible(true);

    	timer = new Timer(100, this);
    	timer.setRepeats(true);
    	running = false;
    }

    public void actionPerformed(ActionEvent e)	{
    	if(!running)	{
	    	String domainType = (String)simModel.getSelectedItem();

	    	domain = new SensorDomain(numSensors, fOfn, domainType);
	    	canvas.setType(domainType);
	    	canvas.setSensors(domain.getSensors());

	    	if(domainType.equals("Unit Line"))	{
	    		algo = new UnitLineAlgorithm();
	    	}
	    	else if(domainType.equals("Unit Perimeter"))	{
	    		algo = new UnitPerimeterAlgorithm();
	    	}
	    	else {
	    		algo = new UnitSquareAlgorithm();
	    	}

	    	startsim.setEnabled(false);
	    	nVal.setEnabled(false);
	    	fOfnVal.setEnabled(false);
	    	simModel.setEnabled(false);

	    	algo.setData(domain.getSensors());

	    	timer.start();
	    	running = true;
    	}

    	algo.move();
    	canvas.draw();

    	if(algo.done())	{
    		timer.stop();
	    	running = false;
	    	
    		if(!algo.covered())	JOptionPane.showMessageDialog(null, "The domain cannot be covered with the given number of sensors and radius", "Error", JOptionPane.ERROR_MESSAGE);
    		algo.stats.displayLastTestStats();
	    	startsim.setEnabled(true);
	    	nVal.setEnabled(true);
	    	fOfnVal.setEnabled(true);
	    	simModel.setEnabled(true);
    	}
    }

    public static void main(String[] args)	{
       Controller c = new Controller();
    }
}

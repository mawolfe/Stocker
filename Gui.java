import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Gui extends JFrame
{
	private BorderLayout layout;
	
	private JPanel buttonJPanel;
	private JPanel textJPanel;
	private JPanel textLabelJPanel;
	private JPanel singletextJPanel;
	
	private JButton buttons[];
	private JLabel fieldLabels[];
	private JTextField fields[];
	
	private JLabel label;
	public JTextField price;
	
	runPeriodicTask rpt;
	
	public Gui()
	{
		//Create the Frame
		super( "Stocker" );
		layout = new BorderLayout(5, 5);
		setLayout(layout);
		
		//Add the buttons
		buttons = new JButton[4];
		buttonJPanel = new JPanel();
		buttonJPanel.setLayout(new GridLayout(1, buttons.length));
		
		buttons[0] = new JButton("Start");
		buttonJPanel.add(buttons[0]);
		buttons[0].addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					//add action events
					System.out.println("Start clicked.");
										
					System.out.println("ticker symbol = " + fields[0].getText());
					System.out.println("refresh rate = " + fields[1].getText());
					System.out.println("high = " + fields[2].getText());
					System.out.println("low = " + fields[3].getText());
					
					rpt = new runPeriodicTask(Integer.parseInt(fields[1].getText()), 
										fields[0].getText().toUpperCase(),
										Double.parseDouble(fields[2].getText()),
										Double.parseDouble(fields[3].getText()));					
				} 
			}
		);	
		
		buttons[1] = new JButton("Stop");
		buttonJPanel.add(buttons[1]);
		buttons[1].addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					//add action events
					System.out.println("Stop clicked.");
					rpt.quit=true;
				}
			}
		);
		
				
		buttons[2] = new JButton("Remove");		// GRAPH
		buttonJPanel.add(buttons[2]);
		buttons[2].addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					//add action events
					System.out.println("Graph clicked.");
					
					System.out.println("ticker symbol = " + fields[0].getText());
					
					rpt.disableTickerSymbol(fields[0].getText().toString().toUpperCase());
					
				}
			}
		);
		
				
		buttons[3] = new JButton("Email");
		buttonJPanel.add(buttons[3]);
		buttons[3].addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					//add action events
					System.out.println("Email clicked.");
				}
			}
		);
		
		
		add( buttonJPanel, BorderLayout.SOUTH);
		
		//Add the button field text
		fieldLabels = new JLabel[5];
		textLabelJPanel = new JPanel();
		textLabelJPanel.setLayout(new GridLayout(fieldLabels.length,1));
		
		fieldLabels[0] = new JLabel("Stock Name");
		textLabelJPanel.add(fieldLabels[0]);
		
		fieldLabels[1] = new JLabel("Refresh Rate");
		textLabelJPanel.add(fieldLabels[1]);
		
		fieldLabels[2] = new JLabel("High Value");
		textLabelJPanel.add(fieldLabels[2]);
		
		fieldLabels[3] = new JLabel("Low Value");
		textLabelJPanel.add(fieldLabels[3]);
		
		fieldLabels[4] = new JLabel("Email Address", 10);
		textLabelJPanel.add(fieldLabels[4]);
		
		add( textLabelJPanel, BorderLayout.WEST);
				
		//Add the Fields
		fields = new JTextField[5];
		textJPanel = new JPanel();
		textJPanel.setLayout(new GridLayout(fields.length, 1));
		
		fields[0] = new JTextField("Stock Name");
		textJPanel.add(fields[0]);
		
		fields[1] = new JTextField("Refresh Rate");
		textJPanel.add(fields[1]);
		
		fields[2] = new JTextField("High Value");
		textJPanel.add(fields[2]);
		
		fields[3] = new JTextField("Low Value");
		textJPanel.add(fields[3]);
		
		fields[4] = new JTextField("Email Address", 10);
		textJPanel.add(fields[4]);
		
		add( textJPanel, BorderLayout.CENTER);
		
		//Add the extras
		singletextJPanel = new JPanel();
		singletextJPanel.setLayout(new GridLayout(5,1));
		
		label = new JLabel("Current Price: ");
		label.setToolTipText("This is the Current Price.");
		singletextJPanel.add( label );
		
		//price = new JTextField("Current Price");
		price = new JTextField("Current Price", 10);
		price.setEditable(false);
		singletextJPanel.add( price );
		add(singletextJPanel, BorderLayout.EAST);
		
		
		//register event handlers
		FieldHandler handler = new FieldHandler();
		fields[0].addActionListener( handler );
		fields[1].addActionListener( handler );
		fields[2].addActionListener( handler );
		fields[3].addActionListener( handler );
		fields[4].addActionListener( handler );
	}
	
	public void setTickerandPrice(String tickerSymb, String curValue) {
		price.setText(tickerSymb + " " + curValue);
	}

	private class FieldHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String string = "";
			
			if(event.getSource() == fields[0])
			string = String.format("fields[0]: %s", 
				event.getActionCommand());
			
			else if(event.getSource() == fields[1])
			string = String.format("fields[1]: %s", 
				event.getActionCommand());
			
			else if(event.getSource() == fields[2])
			string = String.format("fields[2]: %s", 
				event.getActionCommand());
			
			else if(event.getSource() == fields[3])
			string = String.format("fields[0]: %s", 
				event.getActionCommand());
			
			else if(event.getSource() == fields[4])
			string = String.format("fields[4]: %s", 
				event.getActionCommand());
			
			JOptionPane.showMessageDialog(null, string);
		}
	}
}
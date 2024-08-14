package GooeyBits;
/**
 * Name: Brown, Jeffrey  
 * CMIS 242/6380  
 * Date: (02/01/2022)
 * The GooeyFrame class handles the setup, appearance, and functionality of the GUI.
 * The base class creates as many buttons as specified and a single scrollable text area
 * for output. The user must provide an array of listeners that implement ActionListener.
 * The user may use GooeyListener or define their own listener objects.
 */

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

/*
 * The base class for more useful GooeyFrames
 */
public class GooeyFrame {
	//Member variables
	protected JFrame frame;
	protected JPanel panel;    //main panel for holding GUI content
	protected JPanel buttonPanel;    //panel for displaying buttons
	protected JButton[] buttonArray;    //holds the button objects
//	protected JEditorPane textArea;    //Text area for output
//	protected JScrollPane scrollTextArea;    //Makes the text area scrollable

	
	/*CONSTRUCTOR*/
	
	/*
	 * This constructor takes a string as a parameter that sets the title of the window.
	 * It then initializes the JFrame, Jpanels, JScrollPane, and JButtons to be used
	 * in the interface.
	 */
	public <T extends ActionListener> GooeyFrame(String title, String[] buttonNameArray, T[] listenerArray) {
		//Check that handlerArray has the same length as buttonNameArray
		if(buttonNameArray.length != listenerArray.length) {
			throw new IllegalArgumentException("There must be an equal number of buttons and listeners.");
		}
		
		//Create Window (JFrame)
		frame = new JFrame(title);
		
		//Create the main panel
		panel = new JPanel(new BorderLayout());

		//Create the panel for the buttons
		buttonPanel = new JPanel();
		
		//Initialize buttonArray with the same number of elements as buttonNameArray
		buttonArray = new JButton[buttonNameArray.length];
		
		//loop through buttonNameArray, buttonArray, and handlerArray to create buttons
		for(int index = 0; index < buttonArray.length; index++) {
			buttonArray[index] = new JButton(buttonNameArray[index]);    //create the new button with the provided name
			buttonArray[index].addActionListener(listenerArray[index]);    //add the Listener to handle the button functionality
			buttonArray[index].setActionCommand(buttonNameArray[index]);    //set the ActionCommand to the button name
			buttonPanel.add(buttonArray[index]);    //add the button to the button panel
		}

		//Add buttons panel to content panel
		panel.add(buttonPanel, BorderLayout.SOUTH);
//		
		//Add content panel to window
		frame.setContentPane(panel);
//		
		//Set window size
		frame.setSize(800, 600);
		
		//Set window position to center of screen
		frame.setLocationRelativeTo(null);
		
		//display
		frame.setVisible(true);
	}
	
	public void resize(int width, int height) {
		frame.setSize(width, height);    //resize the frame
		frame.setVisible(true);    //redraw the frame
	}
	
	public void reposition(int x, int y) {
		frame.setLocation(x, y);
		frame.setVisible(true);
	}
	
	public void closeProgramOnExit() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void closeProgramOnExit(boolean bool) {
		if(bool)
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		else
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setVisible(Boolean vis) {
		frame.setVisible(vis);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void close() {
		frame.dispose();
	}
}

package GooeyBits;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Handlers.ExceptionHandler;

public class GooeyEntryFrame extends GooeyFrame{
	final static int COLUMNS = 50;
	final static int ROWS = 10;
	protected JTextField[] textFields;
	protected JLabel[] textFieldLabels;
	protected JPanel textFieldPanel;
	protected ArrayList<JTextPane> textAreasList;
	protected ArrayList<JComboBox<String>> dropDownLists;
	
    //constructor
	public <T extends ActionListener> GooeyEntryFrame(String title, String[] buttonNameArray, T[] listenerArray,
			                                          String textFieldNames[], String textFieldDisplayLabels[]) {
	    //call superclass constructor
		super(title, buttonNameArray, listenerArray);
		
		textAreasList = new ArrayList<JTextPane>();
		dropDownLists = new ArrayList<JComboBox<String>>();
	    
	    if(textFieldNames.length != textFieldDisplayLabels.length)
	    	throw new IllegalArgumentException("There must be an equal number of text field names and text field labels.");
	    
//	    textFieldPanel = new JPanel(new GridLayout(textFieldNames.length, 2));
	    textFieldPanel = new JPanel();
	    textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
	    
	    textFields = new JTextField[textFieldNames.length];
	    textFieldLabels = new JLabel[textFieldNames.length];
	    
		//loop through textFields and textFieldLabels to create the text fields
		for(int index = 0; index < textFields.length; index++) {
			if(textFieldDisplayLabels[index] != null) {
				//create the label for the text field
			    textFieldLabels[index] = new JLabel(textFieldDisplayLabels[index]);
			    //Add text field label to the left of the text field.
			    textFieldPanel.add(textFieldLabels[index]);
			}
			
			textFields[index] = new JTextField();    //create the new text field with the provided label
			textFields[index].setName(textFieldNames[index]);
			textFields[index].setMaximumSize(new Dimension(800, 30));

			textFieldPanel.add(textFields[index]);    //Add the text field to the right of the label
			textFieldPanel.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		}
		
		//Add the text field panel to the main panel.
        panel.add(textFieldPanel);
       
        frame.setSize(300, 500);
        
        frame.setVisible(true);
	}
	
	   //overloaded constructor allows the user to specify default values
		public <T extends ActionListener> GooeyEntryFrame(String title, String[] buttonNameArray, T[] listenerArray,
				                                          String textFieldNames[], String textFieldDisplayLabels[],
				                                          String defaultValues[]) {
		    //call superclass constructor
			super(title, buttonNameArray, listenerArray);
			
			textAreasList = new ArrayList<JTextPane>();
		    
		    if(textFieldNames.length != textFieldDisplayLabels.length)
		    	throw new IllegalArgumentException("There must be an equal number of text field names and text field labels.");
		    
		    if(textFieldNames.length != defaultValues.length)
		    	throw new IllegalArgumentException("There must be an equal number of text fields and default values.");
		    
//		    textFieldPanel = new JPanel(new GridLayout(textFieldNames.length, 2));
		    textFieldPanel = new JPanel();
		    textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		    
		    textFields = new JTextField[textFieldNames.length];
		    textFieldLabels = new JLabel[textFieldNames.length];
		    
			//loop through textFields and textFieldLabels to create the text fields
			for(int index = 0; index < textFields.length; index++) {
				textFieldLabels[index] = new JLabel(textFieldDisplayLabels[index]);    //create the label for the text field
				textFields[index] = new JTextField(defaultValues[index]);    //create the new text field with the provided label
				textFields[index].setName(textFieldNames[index]);
				textFields[index].setMaximumSize(new Dimension(800, 30));

				textFieldPanel.add(textFieldLabels[index]);    //Add text field label to the left of the text field.
				textFieldPanel.add(textFields[index]);    //Add the text field to the right of the label
				textFieldPanel.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
			}
			
			//Add the text field panel to the main panel.
	        panel.add(textFieldPanel);
	       
	        frame.setSize(300, 500);
	        
	        frame.setVisible(true);
		}
	
	//returns the text from the specified JTextField
	public String getText(String nm) throws IllegalArgumentException{
		//check the textFields
		for(int index = 0; index < textFields.length; index++) {
			if(textFields[index].getName().equals(nm)) {
				return textFields[index].getText();
			}
		}
		
		//check the textAreas
		if(!textAreasList.isEmpty()) {
			for(JTextPane item: textAreasList) {
				if(item.getName().equals(nm)) {
					return item.getText();
				}
			}
		}
		
		//check the textAreas
		if(!dropDownLists.isEmpty()) {
			for(JComboBox<String> item: dropDownLists) {
				if(item.getName().equals(nm)) {
					return (String) item.getSelectedItem();
				}
			}
		}
		throw new IllegalArgumentException(nm + " is not a valid text field. "
				+ "Please double check spelling.");    //textField not found
	}
	
	//sets the text from the specified JTextField
	public void setText(String nm, String txt){
		//check the textFields
		for(int index = 0; index < textFields.length; index++) {
			if(textFields[index].getName().equals(nm)) {
				textFields[index].setText(txt);
			}
		}
		
		//check the textAreas
		if(!textAreasList.isEmpty()) {
			for(JTextPane item: textAreasList) {
				if(item.getName().equals(nm)) {
					item.setText(txt);
				}
			}
		}
	}
	
	public void addTextArea(String nm, String lbl) {
		JTextPane textArea = new JTextPane();
		textArea.setName(nm);
		textArea.setEditable(true);
//		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JScrollPane scrollTextArea =  new JScrollPane(textArea);
		scrollTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTextArea.setMaximumSize(new Dimension(500,500));
		
		//add textArea to the ArrayList
		textAreasList.add(textArea);
		
		textFieldPanel.add(new JLabel(lbl));
		textFieldPanel.add(scrollTextArea);
		frame.setVisible(true);
	}
	
	//insert text area at specified index
	public void addTextArea(String nm, String lbl, int index) {
		//if index is out of bounds, set index to -1 
		//to add text area to the end.
		if(index > textFieldPanel.getComponentCount()) {
			index = -1;
		}
		JTextPane textArea = new JTextPane();
		textArea.setName(nm);
		textArea.setEditable(true);
//		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JScrollPane scrollTextArea =  new JScrollPane(textArea);
		scrollTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTextArea.setMaximumSize(new Dimension(800,500));
		
		//add textArea to the ArrayList
		textAreasList.add(textArea);
		
		textFieldPanel.add(scrollTextArea, index);
		textFieldPanel.add(new JLabel(lbl), index);
		frame.setVisible(true);
	}
	
	//insert text area at specified index with a default value
	public void addTextArea(String nm, String lbl, int index, String dfltVal) {
		//if index is out of bounds, set index to -1 
		//to add text area to the end.
		if(index > textFieldPanel.getComponentCount()) {
			index = -1;
		}
		 
		JTextPane textArea = new JTextPane();
		textArea.setText(dfltVal);
		textArea.setName(nm);
		textArea.setEditable(true);
//		textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JScrollPane scrollTextArea =  new JScrollPane(textArea);
		scrollTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTextArea.setMaximumSize(new Dimension(800,500));
		
		//add textArea to the ArrayList
		textAreasList.add(textArea);
		
		textFieldPanel.add(scrollTextArea, index);
		textFieldPanel.add(new JLabel(lbl), index);
		frame.setVisible(true);
	}
	
	//Adds a button panel at the specified location
	public <T extends ActionListener> void addButtonPanel(String[] buttonNameArray, T[] listenerArray, int index) 
			throws IllegalArgumentException {
		//Check that handlerArray has the same length as buttonNameArray
		if(buttonNameArray.length != listenerArray.length) {
			throw new IllegalArgumentException("There must be an equal number of buttons and listeners.");
		}
		
		//Create the panel for the buttons
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		//Initialize buttonArray with the same number of elements as buttonNameArray
		buttonArray = new JButton[buttonNameArray.length];
		
		//loop through buttonNameArray, buttonArray, and handlerArray to create buttons
		for(int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton(buttonNameArray[i]);    //create the new button with the provided name
			buttonArray[i].addActionListener(listenerArray[i]);    //add the Listener to handle the button functionality
			buttonArray[i].setActionCommand(buttonNameArray[i]);    //set the ActionCommand to the button name
			buttonPanel.add(buttonArray[i]);    //add the button to the button panel
		}
		
		textFieldPanel.add(buttonPanel, index);
		frame.setVisible(true);
	}
	
	//Overloaded method allows the user to add a label to the button group
	public <T extends ActionListener> void addButtonPanel(String groupLabel, 
			String[] buttonNameArray, T[] listenerArray, int index) 
			throws IllegalArgumentException {
		//Check that handlerArray has the same length as buttonNameArray
		if(buttonNameArray.length != listenerArray.length) {
			throw new IllegalArgumentException("There must be an equal number of buttons and listeners.");
		}
		
		//Create the panel for the buttons
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel(groupLabel);
		
		label.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		
		buttonPanel.add(label);    //add the label to the front
		
		//Initialize buttonArray with the same number of elements as buttonNameArray
		buttonArray = new JButton[buttonNameArray.length];
		
		//loop through buttonNameArray, buttonArray, and handlerArray to create buttons
		for(int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton(buttonNameArray[i]);    //create the new button with the provided name
			buttonArray[i].addActionListener(listenerArray[i]);    //add the Listener to handle the button functionality
			buttonArray[i].setActionCommand(buttonNameArray[i]);    //set the ActionCommand to the button name
			buttonPanel.add(new JLabel(" "));    //add a little space
			buttonPanel.add(buttonArray[i]);    //add the button to the button panel
		}
		
		textFieldPanel.add(buttonPanel, index);
		frame.setVisible(true);
	}
	
	//Add a dropdown list with a label right above it
	public void addDropdownList(String nm, String label, String[] items, int index) {
		//if index is out of bounds, set index to -1 
		//to add text area to the end.
		if(index > textFieldPanel.getComponentCount())
			index = -1;
		
		JComboBox<String> dropList = new JComboBox<String>(items);
		dropList.setName(nm);
		
		dropList.setSize(500, 30);
		dropList.setMaximumSize(new Dimension(800, 30));
		
		textFieldPanel.add(dropList, index);
		textFieldPanel.add(new JLabel(label), index);
		dropDownLists.add(dropList);    //add dropList to dropDownlists 
		
		frame.setVisible(true);
	}
	
	//Make one Text Field editable/uneditable
	public void setEditable(String nm, boolean edtable) {
		//check the textFields
		for(int index = 0; index < textFields.length; index++) {
			if(textFields[index].getName().equals(nm)) {
				textFields[index].setEditable(edtable);
				return;
			}
		}
		
		//check the textAreas
		if(!textAreasList.isEmpty()) {
			for(JTextPane item: textAreasList) {
				if(item.getName().equals(nm)) {
					item.setEditable(edtable);
					return;
				}
			}
		}
		throw new IllegalArgumentException(nm + " is not a valid text field. "
				+ "Please double check spelling.");    //textField not found
	}
}

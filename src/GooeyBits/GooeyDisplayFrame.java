package GooeyBits;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class GooeyDisplayFrame extends GooeyFrame{
	protected JEditorPane textArea;    //Text area for output
	protected JScrollPane scrollTextArea;    //Makes the text area scrollable
	protected JScrollPane scrollImageArea;
	protected JPanel displayPanel;    //panel for main display (center of main panel)
	protected GooeyImage image;    //the image to be displayed on the main display panel
	
    public <T extends ActionListener> GooeyDisplayFrame(String title, String[] buttonNameArray, T[] listenerArray) {
    	super(title, buttonNameArray, listenerArray);
    	
		//create a panel with the scroll area and the image, side by side
		displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
		displayPanel.setBorder(new EmptyBorder(5,5,5,5));
    	
		//Create the text area to display results and other information
		textArea = new JTextPane();
		//Make the text area uneditable
		textArea.setEditable(false);
        
        textArea.setMargin(new Insets(5,5,5,5));
        textArea.setSize(new Dimension(400, 500));
		textArea.setPreferredSize(new Dimension(400, 500));
        textArea.setMinimumSize(new Dimension(200, 250));
        textArea.setMaximumSize(new Dimension(600, 750));
        
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        textArea.setFont(font);
    	
		scrollTextArea =  new JScrollPane(textArea);
		scrollTextArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTextArea.setSize(new Dimension(400, 500));
		scrollTextArea.setPreferredSize(new Dimension(400, 500));
		scrollTextArea.setMinimumSize(new Dimension(200, 250));
		scrollTextArea.setMaximumSize(new Dimension(600, 750));
		
		displayPanel.add(scrollTextArea);
		
		//create a JLabel to hold an image

		image = new GooeyImage();
        image.setSize(new Dimension(400, 500));
        image.setPreferredSize(new Dimension(400, 500));
		image.setMinimumSize(new Dimension(200, 250));
		image.setMaximumSize(new Dimension(600, 750));
		
		//embed the JLabel in a scroll pane
//		scrollImageArea =  new JScrollPane(image);
//		scrollImageArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollImageArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//		scrollImageArea.setSize(new Dimension(400, 500));
//		scrollImageArea.setPreferredSize(new Dimension(400, 500));
//		scrollImageArea.setMinimumSize(new Dimension(400, 500));
//		scrollImageArea.setMaximumSize(new Dimension(400, 500));
		
		displayPanel.add(image);
		
		//add to main panel
		panel.add(displayPanel);
		
		//Add Scrollable Text Area to main panel
//        panel.add(scrollTextArea, BorderLayout.CENTER);
		
		//Add content panel to window
		frame.setContentPane(panel);
		
		//display
		frame.setVisible(true);
    }

	public GooeyImage getImage() {
        return image;
	}
    
	public void setFontSize(int size) {
        Font font = new Font("Times New Roman", Font.PLAIN, size);
        textArea.setFont(font);
        frame.setVisible(true);
	}
	
	public void print(String str) {
		textArea.setText(str);
        frame.setVisible(true);    //refresh the window
	}
	
	public void print(String str, boolean clearPrevious) {		
	    textArea.setText(str);
        frame.setVisible(true);    //refresh the window
	}
}

package Handlers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyFrame;
import GooeyBits.GooeyListener;
import Parsers.NodeParser;
import decisionTree.Node;

public class NewProjectHandler extends GooeyListener{
	GooeyEntryFrame geFrame;
	SaveProjectHandler spHandler;
	
	public void actionPerformed(ActionEvent actn) {
		spHandler = new SaveProjectHandler();
		
		geFrame = new GooeyEntryFrame("Enter Project Information",
				new String[] {"Save", "Cancel"}, new GooeyListener[] {spHandler, spHandler},
				new String[] {"title"}, new String[] {"Title"});
		
		geFrame.addTextArea("description", "Description");
				
		spHandler.setGooeyFrame(geFrame);
	}
//	
//	final static int COLUMNS = 50;
//	final static int ROWS = 10;
//	
//	JTextField title;
//	JTextArea description;
//	JPanel projPanel;
//	Node node;
//	public void actionPerformed(ActionEvent actn) {
//		title = new JTextField(COLUMNS);
//		description = new JTextArea(ROWS, COLUMNS);
//		description.setLineWrap(true);
//		description.setBorder(new LineBorder(Color.BLACK));
//		
//		projPanel = new JPanel();
//		projPanel.setLayout(new BoxLayout(projPanel, BoxLayout.Y_AXIS));
//		
//		//add info-specific text field
//		projPanel.add(new JLabel("Title"));
//		projPanel.add(title);
//		
//		projPanel.add(new JLabel("Description"));
//		projPanel.add(description);
//		
//		int result = JOptionPane.showConfirmDialog(null, projPanel, "Enter project information", JOptionPane.OK_CANCEL_OPTION);
//		
//		if( result != JOptionPane.OK_OPTION)
//			return;
//		
//		node = new Node("START", description.getText());
//		node.setNextNodeID("n_1");    //create the new dummy node
//		node.setTitle(title.getText());
//		
//		
//		/*
//         * Prompt the user for a directory in which to save the new Character file.
//         */
//        JFileChooser fileDialog = new JFileChooser("Please specify a directory.");
//    		
//		fileDialog.setSelectedFile(null);    //no file initially chosen
//		
//		/*
//		 * The following statement sets the JFileChooser so the user can
//		 * only choose a directory and not a file.
//		 */
//		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		
//		/*
//		 * If the user chooses the OK option, the following section will take
//		 * the selected directory and pass it as a File object to the Manager object's
//		 * loadDirectory() method.
//		 */
//		if(fileDialog.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
//		    return;
//		}
//		
//		fileDialog.setCurrentDirectory(fileDialog.getSelectedFile());    //Set the directory to the user selection
//		
//		//Create the project directory
//		String path = fileDialog.getCurrentDirectory().getPath() + "//" + title.getText() + "//";
//		
//        File file = new File(path);
//		file.mkdir();
//		
//		/*
//		 * Call the NodeParser's saveNodeFile() method to create or update the file in the directory.
//		 */
//		try {
//		    NodeParser.saveNodeFile(node, path);
//		}
//		catch(IllegalArgumentException e1) {
//			ExceptionHandler.handleException(e1);
//		}
//		catch(IOException e2) {
//			ExceptionHandler.handleException(e2);
//		}
//		
//		JOptionPane.showMessageDialog(null, "Project created successfully.");
//	}
}

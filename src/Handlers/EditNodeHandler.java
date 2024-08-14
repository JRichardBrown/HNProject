package Handlers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.CharacterParser;
import Parsers.NodeParser;
import decisionTree.IDGenerator;
import decisionTree.Node;

public class EditNodeHandler extends GooeyListener {
	//The Node Entry Frame
	GooeyEntryFrame geFrame;
	SaveNodeHandler snHandler;
	String projectDir;
	String nodeID;
	
	Node node;
	
//	EditNodeHandler(String dir) {
//		projectDir = dir;
//	}
	
	public void actionPerformed(ActionEvent actn) {
		File file = loadFile();
		
		//If the user chose cancel, the file will be null
		if(file == null) {
			return;
		}
		
		node = loadNode(file);
		
		if(node == null) {
			ExceptionHandler.handleException(new RuntimeException(
					"Failed to load a valid node file. Please check the file and try again."));
		}
		
		snHandler = new SaveNodeHandler(projectDir);
		
		switch(node.getNodeType()) {
		case "Node":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "nextNode", "image"}, new String[] {"Node ID", null, null}, 
					new String[] {node.getNodeID(), node.getNextNodeID(), node.getImageFilename()}
			);
			
			geFrame.addButtonPanel("Next Node", new String[] {"New Node", "Existing Node"}, 
		    		new GooeyListener[] {new CreateNodeHandler(geFrame, "nextNode", projectDir, 
		    				IDGenerator.genID(node.getNodeID())), 
		    				new LinkNodeHandler(geFrame, "nextNode", projectDir)}, 2);
			
			geFrame.addButtonPanel("Image", new String[] {"Select"}, 
		    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 6);
			
			snHandler.setNodeType("info");
      
			break;
			
		case "ChallengeNode":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "passNodeID", "failNodeID", "chtr1", "attr1", "difficulty", "image"}, 
					new String[] {"Node ID", null, null, null, "Attribute", "Difficulty", null},
					new String[] {node.getNodeID(), node.getPassNodeID(), node.getFailNodeID(), node.getCharName1(),
							node.getAttrName1(), Integer.toString(node.getDifficulty()), node.getImageFilename()}
			);
			
			 geFrame.addButtonPanel("Pass Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "passNodeID", projectDir,
			    				IDGenerator.genID(node.getNodeID(), "p")), 
			    				new LinkNodeHandler(geFrame, "passNodeID", projectDir)}, 2);
			 
			 geFrame.addButtonPanel("Fail Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "failNodeID", projectDir,
			    				IDGenerator.genID(node.getNodeID(), "f")), 
			    				new LinkNodeHandler(geFrame, "failNodeID", projectDir)}, 5);
			 
			 geFrame.addButtonPanel("Character", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkCharacterHandler(geFrame, "chtr1", projectDir)}, 8);
			 
			 geFrame.addButtonPanel("Image", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 15);
			 
//			 geFrame.addDropdownList("attr1", "Attribute", new String[] {
//					 "STRENGTH", "AGILITY", "PERCEPTION", "STEALTH", "CHARISMA", "LUCK", "INTELLIGENCE"}, 8);
			
			snHandler.setNodeType("challenge");

			break;
			
		case "ContestNode":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "passNodeID", "failNodeID", "chtr1", "attr1", "chtr2", "attr2", "image"}, 
					new String[] {"Node ID", null, null, null, "Attribute 1", null, "Attribute 2", null},
					new String[] {node.getNodeID(), node.getPassNodeID(), node.getFailNodeID(), node.getCharName1(),
							node.getAttrName1(), node.getCharName2(), node.getAttrName2(), node.getImageFilename()}
			);
			
			geFrame.addButtonPanel("Pass Node", new String[] {"New Node", "Existing Node"}, 
		    		new GooeyListener[] {new CreateNodeHandler(geFrame, "passNodeID", projectDir,
		    				IDGenerator.genID(node.getNodeID(), "p")), 
		    				new LinkNodeHandler(geFrame, "passNodeID", projectDir)}, 2);
		 
			geFrame.addButtonPanel("Fail Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "failNodeID", projectDir,
			    				IDGenerator.genID(node.getNodeID(), "f")), 
			    				new LinkNodeHandler(geFrame, "failNodeID", projectDir)}, 5);
			
			geFrame.addButtonPanel("Character 1", new String[] {"Select"}, 
		    		new GooeyListener[] {new LinkCharacterHandler(geFrame, "chtr1", projectDir)}, 8);
			
			geFrame.addButtonPanel("Character 2", new String[] {"Select"}, 
		    		new GooeyListener[] {new LinkCharacterHandler(geFrame, "chtr2", projectDir)}, 14);
			
			geFrame.addButtonPanel("Image", new String[] {"Select"}, 
		    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 19);
			 
//			geFrame.addDropdownList("attr1", "Attribute 1", new String[] {
//					 "STRENGTH", "AGILITY", "PERCEPTION", "STEALTH", "CHARISMA", "LUCK", "INTELLIGENCE"}, 8);
//			 
//			geFrame.addDropdownList("attr2", "Attribute 2", new String[] {
			
			snHandler.setNodeType("contest");

			break;
			
		case "DecisionNode":
			ArrayList<String> dfltVals = node.getNodeIDs();    //add the Option IDs to the dfltVals ArrayList
			
			//The number of elements must be 4. If there are fewer,
			//add null Strings until there are 4 elements.
			int toAdd = 4 - dfltVals.size();
			
			while(toAdd > 0) {
				dfltVals.add(null);
				toAdd--;
			}
			
			try {
				//add the node ID to the beginning of the list
				dfltVals.add(0, node.getNodeID());
				
				dfltVals.add(node.getImageFilename());    //add the image filename to the end of the list
			}
			catch(IndexOutOfBoundsException e) {
				ExceptionHandler.handleException(e);
			}

			
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "option1", "option2", "option3", "option4", "image"}, 
					new String[] {"Node ID", null, null, null, null, null},
					dfltVals.toArray(new String[4]));
			
			 geFrame.addButtonPanel("Option 1 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option1", projectDir,
			    				IDGenerator.genID(node.getNodeID(), 1)), 
			    				new LinkNodeHandler(geFrame, "option1", projectDir)}, 2);
			 
			 geFrame.addButtonPanel("Option 2 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option2", projectDir,
			    				IDGenerator.genID(node.getNodeID(), 2)), 
			    				new LinkNodeHandler(geFrame, "option2", projectDir)}, 5);
			 
			 geFrame.addButtonPanel("Option 3 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option3", projectDir,
			    				IDGenerator.genID(node.getNodeID(), 3)), 
			    				new LinkNodeHandler(geFrame, "option3", projectDir)}, 8);
			 
			 geFrame.addButtonPanel("Option 4 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option4", projectDir,
			    				IDGenerator.genID(node.getNodeID(), 4)), 
			    				new LinkNodeHandler(geFrame, "option4", projectDir)}, 11);
			 
			 geFrame.addButtonPanel("Image", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 14);
			
			snHandler.setNodeType("decision");
			
			break;
			
		default: System.out.println("Something wrong.");
		}
		
	    geFrame.addTextArea("description", "Description", 2, node.getDescription());
	    geFrame.resize(700, 500);
	    geFrame.setEditable("nodeID", false);    //make the nodeID uneditable
		snHandler.setGooeyFrame(geFrame);    //pass the GooeyFrame to the SaveNodeHandler
	}
	
	//used to load a node file for editing
	private Node loadNode(File file) {	        
	        NodeParser nParser = new NodeParser();
	        
	        try {
	            node = nParser.parseFile(file);    //load node for editing
	        } 
	        catch(Exception e) {
	        	ExceptionHandler.handleException(e);
	        	return null;
	        }
	        
	        return node;
	}
	
	private File loadFile() {
//        JFileChooser fileDialog = new JFileChooser("Please select a character file");
//		//Prompt the user to choose a file to load.
//        //If the user chooses "cancel," display a message and exit the program.
//        if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//        	return null;
//        }
//        
//        return fileDialog.getSelectedFile();
		
		File fProjDir = new File(projectDir + "nodes\\");
		
		JComboBox<String> dropList = new JComboBox<String>(fProjDir.list());
		
        int result = JOptionPane.showConfirmDialog(null, dropList, "Select a node", JOptionPane.OK_CANCEL_OPTION);
		
		if(result != JOptionPane.OK_OPTION)
			return null;
		
		return new File(projectDir + "nodes//" + dropList.getSelectedItem().toString());

		
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}
}

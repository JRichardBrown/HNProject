package Handlers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import decisionTree.IDGenerator;
import decisionTree.Node;

/*
 * Controls the functionality of the "Create Node" button
 */
public class CreateNodeHandler extends GooeyListener{
	//The node type selector
	JPanel nodePanel;
	JRadioButton infoButton, challengeButton, contestButton, decisionButton;
	ButtonGroup radioButtonGroup;
	JOptionPane jOpPane;
	
	//The Node Entry Frame
	GooeyEntryFrame geFrame;
//	GooeyEntryFrame geParentFrame;
	SaveNodeHandler snHandler;
	LinkNodeHandler lnHandler;
	CreateNodeHandler cnHandler;
	String projectDir;
	String destination;    //text field of parent directory to print saved-node ID
	String nodeID;
	String nextNodeID;
	
	public CreateNodeHandler(String projDir) {
		snHandler = new SaveNodeHandler(projDir);
		projectDir = projDir;
//		geParentFrame = null;
	}
	
	//Overloaded constructor for creating a linking node.
	//Pass name of text field as destination
	public CreateNodeHandler(GooeyEntryFrame geFr, String dest, String projDir, String ndID) {
//		geParentFrame = geFr;
//		destination = dest;
		
		nodeID = ndID;
		
		projectDir = projDir;
		
		snHandler = new SaveNodeHandler(geFr, dest, projDir);    //pass the parameters the the SaveNodeHandler
	}

	public void actionPerformed(ActionEvent actn) {
		jOpPane = new JOptionPane();
		jOpPane.setSize(500, 500);
		nodePanel = new JPanel();
		nodePanel.setLayout(new BoxLayout(nodePanel, BoxLayout.Y_AXIS));
		
		infoButton = new JRadioButton("Information");
		infoButton.setActionCommand("info");
		
		challengeButton = new JRadioButton("Challenge");
		challengeButton.setActionCommand("challenge");
		
		contestButton = new JRadioButton("Contest");
		contestButton.setActionCommand("contest");
		
		decisionButton = new JRadioButton("Decision");
		decisionButton.setActionCommand("decision");
		
		radioButtonGroup = new ButtonGroup();

		radioButtonGroup.add(infoButton);
		radioButtonGroup.add(challengeButton);
		radioButtonGroup.add(contestButton);
		radioButtonGroup.add(decisionButton);
		
		nodePanel.add(infoButton);
		nodePanel.add(challengeButton);
		nodePanel.add(contestButton);
		nodePanel.add(decisionButton);
		
		int result = JOptionPane.showConfirmDialog(null, nodePanel, "Choose an Event type", JOptionPane.OK_CANCEL_OPTION);
		
		if(result != JOptionPane.OK_OPTION)
			return;
		
		cnHandler = new CreateNodeHandler(projectDir);
		
		String nodeType = radioButtonGroup.getSelection().getActionCommand();
        
		snHandler.setNodeType(nodeType);
		
		nextNodeID = null;
//		
//		File fDir = new File(projectDir);
		
		switch(nodeType) {
		case "info":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "nextNode", "image"}, new String[] {"Node ID", null, null});
			
		    geFrame.addButtonPanel("Next Node", new String[] {"New Node", "Existing Node"}, 
		    		new GooeyListener[] {new CreateNodeHandler(geFrame, "nextNode", projectDir, 
		    				IDGenerator.genID(nodeID)), 
		    				new LinkNodeHandler(geFrame, "nextNode", projectDir)}, 2);
			
//			 geFrame.addButtonPanel("Next Node", new String[] {"New Node"}, 
//		    		new GooeyListener[] {new CreateNodeHandler(geFrame, "nextNode", projectDir, 
//		    				IDGenerator.genID(nodeID))}, 2);
//			 
//			 geFrame.addDropdownList("linkNode", "Existing Node", fDir.list(), 2);
		    
		    geFrame.addButtonPanel("Image", new String[] {"Browse"}, 
		    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 4);
		    
			break;
			
		case "challenge":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "passNodeID", "failNodeID", "chtr1", "difficulty", "image"}, 
					new String[] {"Node ID", null, null, null, "Difficulty", null});
			
		    geFrame.addButtonPanel("Pass Node", new String[] {"New Node", "Existing Node"}, 
		    		new GooeyListener[] {new CreateNodeHandler(geFrame, "passNodeID", projectDir, 
		    				IDGenerator.genID(nodeID, "p")), 
		    				new LinkNodeHandler(geFrame, "passNodeID", projectDir)}, 2);
		 
		    geFrame.addButtonPanel("Fail Node", new String[] {"New Node", "Existing Node"}, 
		    		new GooeyListener[] {new CreateNodeHandler(geFrame, "failNodeID", projectDir,
		    				IDGenerator.genID(nodeID, "f")), 
		    				new LinkNodeHandler(geFrame, "failNodeID", projectDir)}, 4);
		    
		    geFrame.addButtonPanel("Character", new String[] {"Select"}, 
		    		new GooeyListener[] {new LinkCharacterHandler(geFrame, "chtr1", projectDir)}, 6);
		 
		    geFrame.addDropdownList("attr1", "Attribute", new String[] {
				 "STRENGTH", "AGILITY", "PERCEPTION", "STEALTH", "CHARISMA", "LUCK", "INTELLIGENCE"}, 8);
		    
		    geFrame.addButtonPanel("Image", new String[] {"Select"}, 
		    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 12);

			break;
			
		case "contest":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "passNodeID", "failNodeID", "chtr1",  "chtr2", "image"}, 
					new String[] {"Node ID", null, null, null, null, null});

			 geFrame.addButtonPanel("Pass Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "passNodeID", projectDir,
			    				IDGenerator.genID(nodeID, "p")), 
			    				new LinkNodeHandler(geFrame, "passNodeID", projectDir)}, 2);
			 
			 geFrame.addButtonPanel("Fail Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "failNodeID", projectDir,
			    				IDGenerator.genID(nodeID, "f")), 
			    				new LinkNodeHandler(geFrame, "failNodeID", projectDir)}, 4);
			 
			 geFrame.addButtonPanel("Character 1", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkCharacterHandler(geFrame, "chtr1", projectDir)}, 6);
			 
			 geFrame.addDropdownList("attr1", "Attribute 1", new String[] {
					 "STRENGTH", "AGILITY", "PERCEPTION", "STEALTH", "CHARISMA", "LUCK", "INTELLIGENCE"}, 8);
			 
			 geFrame.addButtonPanel("Character 2", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkCharacterHandler(geFrame, "chtr2", projectDir)}, 10);
			 
			 geFrame.addDropdownList("attr2", "Attribute 2", new String[] {
					 "STRENGTH", "AGILITY", "PERCEPTION", "STEALTH", "CHARISMA", "LUCK", "INTELLIGENCE"}, 12);
			 
			 geFrame.addButtonPanel("Image", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 14);
			 
			break;
			
		case "decision":
			geFrame = new GooeyEntryFrame("Enter Event Information",
					new String[] {"Save", "Cancel"}, new GooeyListener[] {snHandler, snHandler},
					new String[] {"nodeID", "option1", "option2", "option3", "option4", "image"}, 
					new String[] {"Node ID", null, null, null, null, null});
			
			 geFrame.addButtonPanel("Option 1 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option1", projectDir, 
			    				IDGenerator.genID(nodeID, 1)), 
			    				new LinkNodeHandler(geFrame, "option1", projectDir)}, 2);
			 
			 geFrame.addButtonPanel("Option 2 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option2", projectDir,
			    				IDGenerator.genID(nodeID, 2)), 
			    				new LinkNodeHandler(geFrame, "option2", projectDir)}, 4);
			 
			 geFrame.addButtonPanel("Option 3 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option3", projectDir,
			    				IDGenerator.genID(nodeID, 3)), 
			    				new LinkNodeHandler(geFrame, "option3", projectDir)}, 6);
			 
			 geFrame.addButtonPanel("Option 4 Node", new String[] {"New Node", "Existing Node"}, 
			    		new GooeyListener[] {new CreateNodeHandler(geFrame, "option4", projectDir,
			    				IDGenerator.genID(nodeID, 4)), 
			    				new LinkNodeHandler(geFrame, "option4", projectDir)}, 8);
			
			 geFrame.addButtonPanel("Image", new String[] {"Select"}, 
			    		new GooeyListener[] {new LinkImageHandler(geFrame, "image", projectDir)}, 10);
			 
			break;
			
		default: System.out.println("Something wrong.");
		}
		
	    geFrame.addTextArea("description", "Description", 2);
	    geFrame.resize(500, 500);
	    geFrame.setText("nodeID", nodeID);
		snHandler.setGooeyFrame(geFrame);    //pass the GooeyFrame to the SaveNodeHandler
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}
	
	//return the ID of the saved node. Returns null if no node has been saved
	public String getSavedNodeID() {
		//if no node has been saved yet
		if(snHandler.getSavedNode() == null) {
		   return null;
		}
		
		return snHandler.getSavedNode().getNodeID();
            
	}
}

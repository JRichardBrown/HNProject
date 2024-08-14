package Handlers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import Parsers.NodeParser;
import decisionTree.ChallengeNode;
import decisionTree.ContestNode;
import decisionTree.DecisionNode;
import decisionTree.Node;

public class SaveNodeHandler extends GooeyListener{
	private GooeyEntryFrame geFrame;
	private GooeyEntryFrame geParentFrame;
	private Node node;
	private String projectDir;
	private String nodeType;
	private String destination;
	private NodeParser nParser;
	
	SaveNodeHandler(String dir) throws NullPointerException{
		if(dir == null)
			throw new NullPointerException("Node directory cannot be null.");
		
		node = null;
		projectDir = dir;
		nodeType = null;
		destination = null;
		geFrame = null;
		geParentFrame = null;
		nParser = new NodeParser();
	}
	
	SaveNodeHandler(GooeyEntryFrame geFr, String dest, String dir) {
		geParentFrame = geFr;
		destination = dest;
		projectDir = dir;
		nParser = new NodeParser();
	}
	
	public void setGooeyFrame(GooeyEntryFrame geFr) {
		geFrame = geFr;    //pass the owning handler's GooeyEntryFrame
	}
	
    public void actionPerformed(ActionEvent actn) {
    	if(actn.getActionCommand().equalsIgnoreCase("cancel")) {
    		geFrame.close();
    		return;
    	}
    	
    	//Owning listener must set node type first
    	if(nodeType == null) {
    		ExceptionHandler.handleException(new NullPointerException(
    				"Owner of saveNodeHandler must set node type before saving a node."));
    		return;
    	}
    	
    	//Check the form to ensure all necessary inputs were added.
    	try {
    		checkInputs(nodeType); 
    	}
    	
    	catch(IllegalArgumentException e) {
    		ExceptionHandler.handleException(e);
    		return;
    	}
        
        switch(nodeType) {
        
        case "info":
    		try {
        	    node = new Node(geFrame.getText("nodeID"), geFrame.getText("description"));
        	    node.setNextNodeID(geFrame.getText("nextNode"));
        	    node.setImageFilename(geFrame.getText("image"));
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	
        	try {
    		    NodeParser.saveNodeFile(node, projectDir + "nodes\\");
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	catch(IOException e2) {
    			ExceptionHandler.handleException(e2);
    			return;
    		}
        	
        	break;
        	
        case "challenge":
    		try {
        	    node = new ChallengeNode(geFrame.getText("nodeID"), 
        	    		geFrame.getText("description"), geFrame.getText("chtr1"),
        	    		geFrame.getText("attr1"), Integer.parseInt(geFrame.getText("difficulty")));
        	    
        	    node.setPassNodeID(geFrame.getText("passNodeID"));
        	    node.setFailNodeID(geFrame.getText("failNodeID"));
        	    node.setImageFilename(geFrame.getText("image"));
    		}
    		
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	
        	try {
    		    NodeParser.saveNodeFile(node, projectDir + "nodes\\");
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	catch(IOException e2) {
    			ExceptionHandler.handleException(e2);
    			return;
    		}
        	break;
        	
        case "contest":
    		try {
        	    node = new ContestNode(geFrame.getText("nodeID"), geFrame.getText("description"), 
        	    		geFrame.getText("chtr1"), geFrame.getText("attr1"), geFrame.getText("chtr2"), 
        	    		geFrame.getText("attr2"));
        	    node.setPassNodeID(geFrame.getText("passNodeID"));
        	    node.setFailNodeID(geFrame.getText("failNodeID"));
        	    node.setImageFilename(geFrame.getText("image"));
    		}
    		
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
    		
        	try {
    		    NodeParser.saveNodeFile(node, projectDir + "nodes\\");
    		}
        	
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	
        	catch(IOException e2) {
    			ExceptionHandler.handleException(e2);
    			return;
    		}
        	
        	break;
        	
        case "decision":
        	ArrayList<String> optionIDs = new ArrayList<String>();
        	
        	optionIDs.add(geFrame.getText("option1"));
        	optionIDs.add(geFrame.getText("option2"));
        	optionIDs.add(geFrame.getText("option3"));
        	optionIDs.add(geFrame.getText("option4"));
        	
        	optionIDs.removeIf(n -> (n.isBlank()));    //remove all blank nodes
        	
        	if(optionIDs.isEmpty()) {
				ExceptionHandler.handleException(new RuntimeException("No valid options entered."));
				return;
        	}
        	
    		try {
        	    node = new DecisionNode(geFrame.getText("nodeID"), geFrame.getText("description"), optionIDs);
        	    node.setImageFilename(geFrame.getText("image"));
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
    		
        	try {
    		    NodeParser.saveNodeFile(node, projectDir + "nodes\\");
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	catch(IOException e2) {
    			ExceptionHandler.handleException(e2);
    			return;
    		}
        	
        	break;
        	
        default: System.out.println("Something wrong.");
        }
        
        JOptionPane.showMessageDialog(null, "Event file saved successfully.");
        
        //If the CreateNode handler was called by a parent node
        if(destination != null && geParentFrame != null)
            geParentFrame.setText(destination, node.getNodeID());
        
        geFrame.close();
    }
    
    public void setProjectDir(String dir) {
    	projectDir = dir;
    }
    
    //Owner must set a node type before saving a node
    public void setNodeType(String type) {
    	nodeType = type;
    }
    
    public Node getSavedNode() {
    	return node;
    }
    
    public void setTextDestination(String dest) {
    	destination = dest;
    }
    
    //Check the inputs of the form to ensure they are valid.
    private void checkInputs(String nodeType) throws IllegalArgumentException {
    	
    	if(geFrame.getText("nodeID").isBlank())
			throw new IllegalArgumentException("NodeID cannot be blank.");
		
		if(geFrame.getText("description").isBlank())
			throw new IllegalArgumentException("Description cannot be blank.");
    	
    	switch(nodeType) {
    	
    	case "info":
    		
    		if(geFrame.getText("nextNode").isBlank())
    			throw new IllegalArgumentException("Next Node cannot be blank.");
    		break;
    		
    	case "challenge":    		
    		if(geFrame.getText("chtr1").isBlank())
    			throw new IllegalArgumentException("Character cannot be blank.");
    		
    		try {
    			Integer.parseInt(geFrame.getText("difficulty"));
    		}
    		catch(IllegalArgumentException e) {
    			throw new NumberFormatException("Difficulty must be an integer value and cannot be blank.");
    		}
    		
    		if(geFrame.getText("passNodeID").isBlank() || geFrame.getText("failNodeID").isBlank())
    			throw new IllegalArgumentException("Neither Pass Node nor Fail Node can be blank.");
    		
    		break;
    		
    	case "contest":
    		if(geFrame.getText("chtr1").isBlank())
    			throw new IllegalArgumentException("Character1 cannot be blank.");
    		
    		if(geFrame.getText("attr1").isBlank())
    			throw new IllegalArgumentException("Attribute1 cannot be blank.");
    		
    		if(geFrame.getText("chtr2").isBlank())
    			throw new IllegalArgumentException("Character2 cannot be blank.");
    		
    		if(geFrame.getText("attr2").isBlank())
    			throw new IllegalArgumentException("Attribute2 cannot be blank.");
    		
    		if(geFrame.getText("passNodeID").isBlank() || geFrame.getText("failNodeID").isBlank())
    			throw new IllegalArgumentException("Neither Pass Node nor Fail Node can be blank.");
    		
    		break;
    		
    	case "decision":
    		if(geFrame.getText("option1").isBlank() && 
    				geFrame.getText("option2").isBlank() &&
    				geFrame.getText("option3").isBlank() && 
    				geFrame.getText("option4").isBlank())
    		    throw new IllegalArgumentException("No valid options entered.");
    		break;
    	}
    }
}

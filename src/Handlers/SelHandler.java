package Handlers;

import GooeyBits.GooeyDisplayFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.NodeParser;
import Program.EventController;
import decisionTree.Node;
import decisionTree.Tree;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class SelHandler extends GooeyListener {
	Tree decisionTree;
	Node nextNode;
	String selection;
	NodeParser nParser;
	String fileName;
	Hashtable<String, MMCharacter>characterHT;
	String basePath;
	GooeyDisplayFrame gFrame;
	
	public SelHandler(Tree dTree, String fName, String bPath, Hashtable<String, MMCharacter>chtrHT) {
		decisionTree = dTree;
		Node nextNode = null;
		nParser = new NodeParser();
		fileName = fName;
        characterHT = chtrHT;
        basePath = bPath;
        gFrame = null;
	}
	
	public void setGFrame(GooeyDisplayFrame gf) {
		gFrame = gf;
	}
	
	public void actionPerformed(ActionEvent actn) {
		selection = actn.getActionCommand();
		String nodeType = decisionTree.getCurrentNode().getNodeType();
		
		if(nodeType.equals("DecisionNode"))
			handleDecision();
		else {    //Node or ChallengeNode
			handleNode();
		}
	}
	
	private void handleDecision() {	
		if(selection.equals("Next"))
			return;
		
		//check if the selection is within the valid range
		if(Integer.parseInt(selection) > decisionTree.getCurrentNode().optionCount())
			return;
		
		nextNode = decisionTree.getCurrentNode().getNextNode(Integer.parseInt(selection));
		
		//update filename with nextNode ID
        fileName = nextNode.getNodeID() + ".txt";
		
		//set the current node to the next node
        //DEBUG
        
        try {
        	decisionTree.setCurrentNode(nParser.parseFile(new File(basePath + "nodes//" + fileName), basePath, characterHT));
        }
        catch(Exception e) {
        	ExceptionHandler.handleException(e);
        	return;
        }   
        
        if(decisionTree.getCurrentNode().getNodeID().equals("n_0"))
		    return;
        
        //Print description
		gFrame.print(decisionTree.getCurrentNode().getDescription() + "\n", true);

		try {
			gFrame.getImage().setImage(EventController.getProjectDir() + "\\images\\" + decisionTree.getCurrentNode().getImageFilename());
		}
		catch(IOException e) {
			//if default image is not set, fail quietly
            if(decisionTree.getCurrentNode().getImageFilename().equals("default.png"))
                return;
			ExceptionHandler.handleException(e, "SelHandler.handleNode");
		}
	}
	
	private void handleNode() {	
		if(!selection.equals("Next"))
			return;
		
		nextNode = decisionTree.getCurrentNode().getNextNode();
		
		//update filename with nextNode ID
        fileName = nextNode.getNodeID() + ".txt";
        
        //set the current node to the next node
        try {
        	decisionTree.setCurrentNode(nParser.parseFile(new File(basePath + "nodes//" + fileName), basePath, characterHT));
        }
        catch(Exception e) {
        	ExceptionHandler.handleException(e);
        	return;
        } 
        
        if(decisionTree.getCurrentNode().getNodeID().equals("n_0"))
		    return;
        
		gFrame.print(decisionTree.getCurrentNode().getDescription() + "\n", true);

		try {
			gFrame.getImage().setImage(EventController.getProjectDir() + "\\images\\" + decisionTree.getCurrentNode().getImageFilename());
		}
		catch(IOException e) {
			//if default image is not set, fail quietly
            if(decisionTree.getCurrentNode().getImageFilename().equals("default.png"))
                return;
			ExceptionHandler.handleException(e, "SelHandler.handleNode");
		}
		
	}
}

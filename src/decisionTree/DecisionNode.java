package decisionTree;
import java.util.ArrayList;
import java.util.ListIterator;

//Multi-Choice node that holds an ArrayList of nodes for the user to choose from
public class DecisionNode extends Node {
    private ArrayList<Node> nodeList;    //The branches of this node are represented by an array list
    private ArrayList<String> nodeIDs;    //used for toString method
    private final String nodeType = "DecisionNode";
    
    public DecisionNode(String id, String desc) {
    	super(id, desc);
    	nodeList = new ArrayList<Node>();    //allocate memory for the ArrayList
    	nodeIDs = null;
    	selection = 0;    //no selection yet
    }
    
    //for creating the DecisionNode file
    public DecisionNode(String id, String desc, ArrayList<String> ids) {
    	super(id, desc);
    	nodeList = null;    //allocate memory for the ArrayList
    	nodeIDs = ids;
    }
    
    //This method adds an option item (i.e. a Node object) to the nodeList
    @Override
    public void addAction(Node node) {
    	nodeList.add(node);
    }
    
    //setNextNode() is not supported by ChallengeNode
    @Override
    public void setNextNode(Node nxt) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setNextNode");
    }
    
    @Override
    public String getNextNodeID() {
    	throw new UnsupportedMethod(this.nodeID, "getNextNodeID");
    }
    
    /*
     * This method takes an integer as an argument and returns the node represented by that number in the nodeList
     */
    @Override
    public Node getNextNode (int sel) throws RuntimeException, EmptyList {
    	if(nodeList.isEmpty()) {
    		throw new EmptyList(this.nodeID);
    	}
    	
    	selection = sel;    //
    	
    	try {
    	    return nodeList.get(sel - 1);
    	}
    	catch(IndexOutOfBoundsException E) {
    		throw new RuntimeException("Node ID: " + this.getNodeID() 
    		+ ". Invalid selection. No such option exists.");
    	}
    }
    
    public String getNodeType() {
    	return nodeType;
    }
    
    public ArrayList<String> getNodeIDs() {
    	return nodeIDs;
    }
    
    //returns the number of elements in the nodeList
    public int optionCount() {
    	return nodeList.size();
    }
    
    public int getSelection() {
    	return selection;
    }
    
    /*
     * toString() is used to print the Character's information to a file
     */
    @Override
    public String toString() {
    	String result = ("Decision->>" + (this.description == null || this.description.isEmpty()?
    			" " : this.description) + "->>|");
    	
    	ListIterator<String> iter = nodeIDs.listIterator();
    	
    	while(iter.hasNext()) {
    		result += "\n" + "Action->" + iter.next();
    	}
    	
    	result += "\n" + "Image->" + (this.imageFilename == null || this.imageFilename.isBlank()? 
				  "default.png" : this.imageFilename) + "\n";
    	
    	return result;
    }
}

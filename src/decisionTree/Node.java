package decisionTree;

import MMSimulator.MMCharacter;
import java.util.ArrayList;

/*
 * This is the base class for all other nodes to be used in the tree data structure. It can also be
 * used to simply display information before going to the next node.
 * It contains all the methods that must overridden by the child classes. 
 */

public class Node {
	protected String description;
    protected String nodeID;    //Unique ID for node
    protected Node nextNode;
    protected String imageFilename;
    protected int selection;    //used for nodeID creation
    private final String nodeType = "Node";
    private String title;

    protected String nextNodeID;    //used exclusively for the toString method
    
    // This constructor is used for making a DummyNode
    public Node(String id) {
    	nodeID = id;
    	description = null;
    	nextNode = null;
    	nextNodeID = null;
    	title = null;
    	imageFilename = null;
    }
    
    public Node(String id, String desc) {
    	nodeID = id;
    	description = desc;
    	nextNode = null;
    	nextNodeID = null;
    	title = null;
    	imageFilename = null;
    }
    
    public String getDescription() {
    	return description;
    }
    
    //subclasses should override to throw UnsupportedMethod exception if not using
    public void setNextNode(Node nxt){
    	nextNode = nxt;
    }
    
    // Used exclusively for the toString method
    public void setNextNodeID(String nxt) {
    	nextNodeID = nxt;
    }
    
    public Node getNextNode() {return nextNode;}
    
    //child classes should override
    public Node getNextNode(int selection) {return nextNode;}
    
    public MMCharacter getCharacter(String nm)throws UnsupportedMethod  {
    	throw new UnsupportedMethod(this.nodeID, "getCharacter");
    }
    
    public void setCharName1(String nm) throws UnsupportedMethod{
    	throw new UnsupportedMethod(this.nodeID, "setCharName1");
    }
    
    public void setCharName2(String nm) throws UnsupportedMethod{
    	throw new UnsupportedMethod(this.nodeID, "setCharName2");
    }
    
    public void setAttrName1(String attr) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setAttrName1");
    }
    
    public void setAttrName2(String attr) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setAttrName2");
    }
    
    public void addAction(Node node) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "addNode");
    }
    

    public void setPassNode(Node passOpt) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setPassNode");
    }
    
    public void setPassNodeID(String id) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setPassNodeID");
    }
    
    public void setFailNode(Node failOpt) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setFailNode");
    }
    
    public void setFailNodeID(String id) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setFailNodeID");
    }
    
    public boolean performTest() throws UnsupportedMethod{
    	throw new UnsupportedMethod(this.nodeID, "performTest");
    }
    
    //this overloaded version of test allows the user to specify the probability of success
    //probOfPass must be between 0 and 100.
    public boolean performTest(int probOfPass) throws IllegalArgumentException, UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "performTest");
    }
    
    public boolean performTest(MMCharacter.Attribute attr, int diff) throws NullPointerException{
    	throw new UnsupportedMethod(this.nodeID, "getResult");
    }
    
    public boolean performTest(MMCharacter.Attribute attr1, MMCharacter.Attribute attr2) {
    	throw new UnsupportedMethod(this.nodeID, "getResult");
    }
    
    public boolean getResult() throws UnsupportedMethod{
    	throw new UnsupportedMethod(this.nodeID, "getResult");
    }
    
    public String getPassNodeID() {
    	throw new UnsupportedMethod(this.nodeID, "getPassNodeID");
    }
    
    public String getFailNodeID() {
    	throw new UnsupportedMethod(this.nodeID, "getFailNodeID");
    }
    
    public String getCharName1() {
    	throw new UnsupportedMethod(this.nodeID, "getCharName1");
    }
    
    public String getAttrName1() {
    	throw new UnsupportedMethod(this.nodeID, "getAttrName1");
    }
    
    public String getCharName2() {
    	throw new UnsupportedMethod(this.nodeID, "getCharName2");
    }
    
    public String getAttrName2() {
    	throw new UnsupportedMethod(this.nodeID, "getAttr2");
    }
    
    public int getDifficulty() {
    	throw new UnsupportedMethod(this.nodeID, "getDifficulty");
    }
    
    public ArrayList<String> getNodeIDs() {
    	throw new UnsupportedMethod(this.nodeID, "getNodeIDs");
    }
    
    public int optionCount() {
    	throw new UnsupportedMethod(this.nodeID, "optionCount");
    }
    
    public String getNodeID( ) {return nodeID;}
    
    public String getNodeType() {
    	return nodeType;
    }
    
    public MMCharacter getCharacter() throws UnsupportedMethod{
    	throw new UnsupportedMethod(this.nodeID, "getCharacter");
    }
    
    public int getSelection() {
    	throw new UnsupportedMethod(this.nodeID, "getSelecion");
    }
    
    public void setTitle(String ttl) {
    	title = ttl;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public String getNextNodeID() {
    	return nextNodeID;
    }
    
    public void setImageFilename(String fn) {
    	imageFilename = fn;
    }
    
    public String getImageFilename() {
    	return imageFilename;
    }
    
//    public static String createNodeID(Node parentNd) {
//    	String newID = "n";    //node ID always starts with "n"
//    	
//        //Split the parent node ID into two parts: the string and the node count	
//    	String[] idParts = parentNd.getNodeID().split("_");
//    	
//    	int nodeCount = Integer.parseInt(idParts[1]);
//    	
//    	switch(parentNd.nodeType) {
//    	
//    	case("Node"):
//    		nodeCount++;    //increase the node count
//		    newID = newID.concat(idParts[0] + "_" + nodeCount);
//	    
//	        break;
//		
//    	case("ChallengeNode"):
//    	case("ContestNode"):
//    		nodeCount = 0;    //reset the node count
//    	    newID = newID.concat(idParts[0] + (parentNd.getResult() == true ? "p"  : "f") 
//    	    		+ "_" + nodeCount);
//    	    
//    	    break;
//    	    
//    	case("DecisionNode"):
//    		nodeCount = 0;
//    	    newID = newID.concat(idParts[0] + parentNd.getSelection()
//	    		+ "_" + nodeCount);
//    	    
//    	    break;
//    	    
//    	default: 
//    		throw new RuntimeException("NodeID " + parentNd.getNodeID() + ". Invalid node type: " + parentNd.getNodeType());
//    	}	
//    	
//    	return newID;
//    }
    
    /*
     * toString() is used to print the Character's information to a file
     */
    public String toString() {
    	String result;
    	
    	result = "Info->>" + (this.description == null || this.description.isEmpty()?
    			" " : this.description) + "->>|" + "\n"
      		  + "NextNode->" + this.nextNodeID + "\n"
      		  + "Image->" + (this.imageFilename == null || this.imageFilename.isBlank()? 
      				  "default.png" : this.imageFilename) + "\n";

    	if(title != null)
    	    result += ("Title->" + this.getTitle());
    	return result;
    }
}

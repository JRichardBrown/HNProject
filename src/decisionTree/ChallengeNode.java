package decisionTree;
import MMSimulator.MMSimulator;
import MMSimulator.MMCharacter;

/*
 * ChallengeNode puts one character up against a challenge, with two possible results: pass or fail
 */
public class ChallengeNode extends Node {
    private final String nodeType = "ChallengeNode";
    private Node passNode;    //the "pass" destination node
    private Node failNode;    //the "fail" destination node
    private boolean result;    //the result of the challenge
    private boolean alreadyPerformed;    //has the challenge already been performed
    private MMCharacter character1;    //the character being challenged
    private MMCharacter.Attribute attribute1;    //the character attribute being tested
    private int difficulty;    // the difficulty of the challenge
    
    //the following are used exclusively for the toString method
    private String passNodeID;
    private String failNodeID;
    private String charName1;
    private String attrName1;
    
    // Create a dummy node, to be replaced by a true node later
    public ChallengeNode(String id) {
    	super(id);
    }
    
    // For challenge event (character vs. challenge)
    public ChallengeNode(String id, String desc, MMCharacter chtr1, MMCharacter.Attribute attr1, int diff) {
    	super(id, desc);
    	passNode = null;
    	failNode = null;
    	result = false;
    	alreadyPerformed = false;
    	character1 = chtr1;
    	attribute1 = attr1;
    	difficulty = diff;
    	
    	passNodeID = null;
    	failNodeID = null;
    	charName1 = null;
    	attrName1 = null;
    }
    
 // For creating the Challenge Node file
    public ChallengeNode(String id, String desc, String chtr1, String attr1, int diff) {
    	super(id, desc);
    	
    	passNode = null;
    	failNode = null;
    	result = false;
    	alreadyPerformed = false;
    	character1 = null;
    	attribute1 = null;
    	difficulty = diff;
    	
    	passNodeID = null;
    	failNodeID = null;
    	charName1 = chtr1;
    	attrName1 = attr1;
    }
    
    @Override
    public MMCharacter getCharacter() {
    	return character1;    //MMCharacter not found
    }
    
    @Override
    public void setCharName1(String nm) {
    	charName1 = nm;
    }
    
    //Set the node to be retrieved under the pass condition
    @Override
    public void setPassNode(Node passOpt) {
    	passNode = passOpt;
    }
    
    @Override
    //Used exclusively for the toString method
    public void setPassNodeID(String id) {
    	passNodeID = id;
    }
    
    //Set the node to be retrieved under the fail condition
    @Override
    public void setFailNode(Node failOpt) {
    	failNode = failOpt;
    }
    
    @Override
    //Used exclusively for the toString method
    public void setFailNodeID(String id) {
    	failNodeID = id;
    }
    
    /*
     * Performs test and returns pass or fail (true or false). If the test has already been performed,
     * returns the result.
     */
    public boolean performTest() throws NullPointerException{
    	if(!alreadyPerformed) {
	    	if(character1 == null)
	    		throw new NullPointerException("Node ID: " + this.getNodeID() + ". Please load at least "
	    				+ "one character and attribute before calling this function.");
	    	
		    result = MMSimulator.challenge(character1.getAttribute(attribute1), difficulty);
	    	
	    	alreadyPerformed = true;
    	}
    	
    	return result;
    }
    
    public boolean getResult() {
    	return result;
    }
    
    //setNextNode() is not supported by ChallengeNode
    @Override
    public void setNextNode(Node nxt) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setNextNode");
    }
    
    @Override
    public void setNextNodeID(String nxt) throws UnsupportedMethod {
    	throw new UnsupportedMethod(this.nodeID, "setNextNodeID");
    }
    
    @Override
    public String getNextNodeID() {
    	throw new UnsupportedMethod(this.nodeID, "getNextNodeID");
    }
    
    public Node getNextNode() throws NullPointerException, RuntimeException {
    	//Check if either node is null. If so, throw an exception
    	if(failNode == null || passNode == null) {
    		throw new NullPointerException("Node ID: " + this.getNodeID() 
    				+ ". Pass or fail options cannot be null.");
    	}
    	
    	if(!alreadyPerformed)
    		performTest();
    		
        if(result)
        	return passNode;
        
        return failNode;
    }
    
    public String getNodeType() {
    	return nodeType;
    }
    
    public String getPassNodeID() {
    	return passNodeID;
    }
    
    public String getFailNodeID() {
    	return failNodeID;
    }
    
    public String getCharName1() {
    	return charName1;
    }
    
    public String getAttrName1() {
    	return attrName1;
    }
    
    public int getDifficulty() {
    	return difficulty;
    }
    
    @Override
    /*
     * toString() is used to print the Character's information to a file
     */
    public String toString() {
    	return ("Challenge->>" + (this.description == null || this.description.isEmpty()?
    			" " : this.description) + "->>|" + "\n"
    		  + "Character1->" + this.charName1 + "\n"
    		  + "Attribute1->" + this.attrName1 + "\n"
    		  + "Difficulty->" + this.difficulty + "\n"
    		  + "PassNode->" + this.passNodeID + "\n"
    		  + "FailNode->" + this.failNodeID + "\n"
    		  + "Image->" + (this.imageFilename == null || this.imageFilename.isBlank()? 
      				  "default.png" : this.imageFilename) + "\n");
    }
}

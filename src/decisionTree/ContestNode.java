package decisionTree;
import MMSimulator.MMSimulator;
import MMSimulator.MMCharacter;


/*
 * This node pits the main character against an enemy character with
 * two possible outcomes: pass or fail.
 */
public class ContestNode extends Node {
    private final String nodeType = "ContestNode";
    private Node passNode;    //the node retrieved under pass condition
    private Node failNode;    //the node retrieved under fail condition
    private boolean result;    //the result of the test
    private boolean alreadyPerformed;
    private MMCharacter character1;
    private MMCharacter.Attribute attribute1;
    private MMCharacter character2;
    private MMCharacter.Attribute attribute2;
    
    private String passNodeID;    //used for toString method
    private String failNodeID;    //used for toString method
    private String charName1;    //used for toString method
    private String attrName1;    //used for toString method
    private String charName2;    //used for toString method
    private String attrName2;    //used for toString method
    
    public ContestNode(String id) {
    	super(id);
    }
    
    //For contest event (character vs. character)
    public ContestNode(String id, String desc, MMCharacter chtr1, MMCharacter.Attribute attr1, 
    		MMCharacter chtr2, MMCharacter.Attribute attr2) {
    	super(id, desc);
    	passNode = null;
    	failNode = null;
    	result = false;
    	alreadyPerformed = false;
    	character1 = chtr1;
    	attribute1 = attr1;
    	character2 = chtr2;
    	attribute2 = attr2;
        charName1 = null;    //used for toString method
        attrName1 = null;    //used for toString method
        charName2 = null;    //used for toString method
        attrName2 = null;    //used for toString method
    }
    
    // For creating the Challenge Node file
    public ContestNode(String id, String desc, String chtr1, String attr1, String chtr2, String attr2) {
    	super(id, desc);
    	passNode = null;
    	failNode = null;
    	passNodeID = null;
    	failNodeID = null;
    	result = false;
    	alreadyPerformed = false;
    	character1 = null;
    	attribute1 = null;
    	charName1 = null;
    	attrName1 = null;
    	charName1 = chtr1;    //used for toString method
        attrName1 = attr1;    //used for toString method
        charName2 = chtr2;    //used for toString method
        attrName2 = attr2;    //used for toString method
    }
    
    public MMCharacter getCharacter(String nm) {
    	if(character1 == null)
    		throw new NullPointerException();
    	if(character1.getName().equals(nm))
    		return character1;
    	
    	if(character2 == null)
    		throw new NullPointerException();
    	if(character2.getName().equals(nm))
    		return character2;
    	
    	return null;    //MMCharacter not found
    }
    
    //Set the node to be retrieved under the pass condition
    @Override
    public void setPassNode(Node passOpt) {
    	passNode = passOpt;
    }
    
    //Set the node to be retrieved under the fail condition
    @Override
    public void setFailNode(Node failOpt) {
    	failNode = failOpt;
    }
    
    //Used for toString method
    @Override
    public void setPassNodeID(String id) {
    	passNodeID = id;
    }
    
  //Used for toString method
    @Override
    public void setFailNodeID(String id) {
    	failNodeID = id;
    }
    
  //Used for toString method
    @Override
    public void setCharName1(String nm) {
    	charName1 = nm;
    }
    
    //Used for toString method
    @Override
    public void setCharName2(String nm) {
    	charName2 = nm;
    }
    
  //Used for toString method
    @Override
    public void setAttrName1(String nm) {
    	attrName1 = nm;
    }
    
    //Used for toString method
    @Override
    public void setAttrName2(String nm) {
    	attrName2 = nm;
    }
    
    public boolean performTest() throws NullPointerException{
    	if(!alreadyPerformed) {
	    	if(character1 == null || character2 == null)
	    		throw new NullPointerException("Node ID: " + this.getNodeID() + ". Please load two "
	    				+ "characters and two attributes before calling this function.");
	    	
		    result = MMSimulator.contest(character1.getAttribute(attribute1), character2.getAttribute(attribute2));

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
    
    /*
     * performs the contest and returns the result. If the contest has already
     * been performed, returns the result.
     */
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
    
    public String getCharName2() {
    	return charName2;
    }
    
    public String getAttrName2() {
    	return attrName2;
    }
    
    @Override
    /*
     * toString() is used to print the Character's information to a file
     */
    public String toString() {
    	return ("Contest->>" + (this.description == null || this.description.isEmpty()?
    			" " : this.description) + "->>|" + "\n"
    		  + "Character1->" + this.charName1 + "\n"
    		  + "Attribute1->" + this.attrName1 + "\n"
    		  + "Character2->" + this.charName2 + "\n"
    		  + "Attribute2->" + this.attrName2 + "\n"
    		  + "PassNode->" + this.passNodeID + "\n"
    		  + "FailNode->" + this.failNodeID + "\n"
    	      + "Image->" + (this.imageFilename == null || this.imageFilename.isBlank()? 
      				  "default.png" : this.imageFilename) + "\n");
    }
}

package decisionTree;
/**
 * This class is an unchecked exception that is thrown when the MCNode's list is null and 
 * the user tries to invoke a method that requires the list be populated.
 */

public class EmptyList extends NullPointerException {
	
	//The thrower must pass the nodeID
    EmptyList(String nodeID) {
    	super("(nodeID: " + nodeID + ") nodeList cannot be empty.");
    }
    
    /*
     * overrides the Exception's toString class to give the name of the Exception and the message.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getMessage();
    }
}

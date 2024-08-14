package decisionTree;
/**
 * This class is an unchecked exception that is thrown when the user attempts to invoke a method that does
 * not apply to the node in question.
 */

public class UnsupportedMethod extends RuntimeException {
	
	//The thrower must pass the nodeID and the method name as parameters
    UnsupportedMethod(String nodeID, String methodName) {
    	super("(nodeID: " + nodeID + ") The method " + methodName + " is not supported by this node type.");
    }
    
    /*
     * overrides the Exception's toString class to give the name of the Exception and the message.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getMessage();
    }
}

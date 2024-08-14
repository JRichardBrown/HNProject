package decisionTree;
/**
 * This class is a checked exception that is thrown when the BinaryTree constructor is passed
 * a string with an invalid syntax--e.g. unbalanced parentheses.
 */

public class InvalidTreeSyntax extends Exception {
    InvalidTreeSyntax(String msg) {
    	super(msg);    //call the parent class's overloaded constructor
    }
    
    /*
     * overrides the Exception's toString class to give the name of the Exception and the message.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getMessage();
    }
}

package decisionTree;

//SyntaxError is an unchecked exception used for a parser
public class SyntaxError extends RuntimeException {
	public SyntaxError() {super();}    //default constructor
	public SyntaxError(String str) {super(str);}    //constructor with string parameter
}

package Parsers;

import java.io.EOFException;
import java.util.Scanner;

public abstract class Parser {
	protected Scanner fileScanner;
	protected String line;    //holds a line of text taken from the input file
	protected String[] tokenArray;    //holds tokens for processing
	protected String currentToken;
	protected int i;    //the array index
    
	/*
	 * Preconditions: The scanner object must already be loaded with a properly-formatted file.
     * 
     * Description: Sets currentToken to the next token of the loaded file.
     * 
     * End State: Returns the currentToken, which is now set to the next token.
	 */
	protected String iterateToken() throws EOFException {
		if((i + 1 >= tokenArray.length)) {   //if there are no more tokens in the array
			try {
				loadNextLine();    //load more tokens into tokenArray and reset the index
			}
		    catch(EOFException e) {
			    throw e;    //rethrow exception
		    }
		}
		currentToken = tokenArray[++i];
		
		return currentToken;
	}
	
	//Returns the last token retrieved by iterateToken(). Does not advance to the next token.
	protected String getCurrentToken() {
		return currentToken;
	}
	
	//Loads the next line of text so that the token array is properly split
	protected void loadNextLine() throws EOFException {    //throws an exception if method reaches EOF unexpectedly
        if(fileScanner.hasNext()) {
		    line = new String(fileScanner.nextLine()); 
		
		    if(line.contains("->>")) {
		    	if(line.contains("->>|")) {
		    		line = line.replace("->>|", "");    //indicates that this is a single line
		    	    tokenArray = line.split("->>");    //split the String at the -> tokens.
		    	}
		        
		    	else {
		    		tokenArray = line.split("->>");    //split the String at the -> tokens.
		            tokenArray[1] += loadMultiLines();
		    	}
		    }
		    
		    else {
			    tokenArray = line.split("->");    //split the String at the -> tokens.

		    }
		    
		    i = -1;    //reset i
		    return;
        }

        else
        	throw new EOFException("NodeParser reached the end of the file unexpectedly.");
	}
	
	protected String loadMultiLines() throws EOFException{
		StringBuilder strBldr = new StringBuilder();
		
		boolean exit = false;
		while(exit == false) {
			if(fileScanner.hasNextLine() == false) {
				throw new EOFException("Parser reached the end of file unexpectedly.");
			}
			line = fileScanner.nextLine();
			
			if(line.contains("->>|")) {
				line = line.replace("->>|", "");
				exit = true;
			}
			
			strBldr.append("\n");    //write each on a new line
			strBldr.append(line);
		}
		
		return strBldr.toString();
	}


}

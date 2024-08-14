package Parsers;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Handlers.ExceptionHandler;
import MMSimulator.MMCharacter;
import decisionTree.SyntaxError;

public class CharacterParser extends Parser{
	
	public MMCharacter parseCharacter(File file) throws FileNotFoundException, NumberFormatException, 
	                                             IllegalArgumentException, EOFException, SyntaxError {
    
		try {
			//Wrap the FileReader object in the Scanner object
			fileScanner = new Scanner(file);
		}
			catch(FileNotFoundException e) {
				throw e;
		}
		
		//FileIsEmptyException
		if(!fileScanner.hasNext())
			throw new RuntimeException("File is empty");
		
		try {
		    this.loadNextLine();
		}
		catch(NullPointerException e) {
			throw e;
		}
		
		MMCharacter character = null;    //the MMCharacter to be returned
		
		String name;
		
		String[] attrNames = new String[] {"Strength", "Agility", "Perception", "Stealth", "Charisma", "Luck", "Intelligence"};
		
		int[] attrVals = new int[attrNames.length];    //array of character attributes
		
		
		
		if(!iterateToken().equals("Name"))
			throw new SyntaxError("unable to find token \"Name\" in file " + file.getName());
		
		name = iterateToken();
		
		//populate the attribute list
		for(int i = 0; i < attrNames.length; i++) {
			if(!iterateToken().equals(attrNames[i]))
				throw new SyntaxError("unable to find token \"" + attrNames[i] + "\" in file " + file.getName());
			try {
			    attrVals[i] = Integer.parseInt(iterateToken());
			}
			catch(NumberFormatException e) {
				throw e;
			}	
		}
	    fileScanner.close();
		
		try {
			return new MMCharacter(name, attrVals);
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
    }
	
	/*
	 * saveCharacterFile() is used to save the Character information to a file in the specified directory.
	 */
	public static void saveCharacterFile (MMCharacter character, String directory) throws IllegalArgumentException {
		if(character == null)
			throw new IllegalArgumentException();
		
        PrintWriter writer = null;    //for writing to a file
    	
    	/* Construct the filename String from the directory and some information from the Media object. */
    	String filename = (directory + character.getName() + ".txt");
		
		try {
		    writer = new PrintWriter(new FileWriter(filename));    //Create the new .txt file.
		}
		catch(IOException e) {
			ExceptionHandler.handleException(e);    //Handle the I/O exception, if any.
			return;
		}
		
		/* Write the Media object's XML-formatted information to the .txt file */
		writer.println(character.toString());
		writer.flush();    //Make sure all the data is written to the file.
		writer.close();    //Close the output stream.
	}
}

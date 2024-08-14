package Parsers;
/**
 * Class Description: Parses a formatted file passed as a parameter to the public
 * parseFile method and returns a JFrame object modified IAW the file. The user is 
 * responsible for getting the file. The user is also responsible for setting the location, 
 * default close operation, and visibility of the JFrame.
 */

import MMSimulator.MMCharacter;
import decisionTree.ChallengeNode;
import decisionTree.ContestNode;
import decisionTree.DecisionNode;
import decisionTree.Node;
import decisionTree.SyntaxError;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class NodeParser extends Parser{
	private CharacterParser cParser = new CharacterParser();
	/* Preconditions: File passed as a parameter must be properly formatted per the
	 * grammar rules.
	 * 
     * Description: Parses the parameterized file to create a node with an information object attached.
     * 
     * End State: Returns a node object with the appropriate Information object attached to it. Any
     * follow-on nodes will also be linked to this node.
     */
	
	String projectDir;
	
    public Node parseFile(File file, String pDir, Hashtable<String, MMCharacter> chtrHT) throws RuntimeException, 
    FileNotFoundException, EOFException, IOException, NullPointerException {
    	//set the project directory
    	projectDir = pDir;
    	
    	if(chtrHT == null) {
    		throw new NullPointerException("Character Hashtable cannot be null.");
    	}
    	
		try {
			fileScanner = new Scanner(file);    //Associate this instance's Scanner with the passed-in file
		}
		catch(FileNotFoundException e) {    //if the passed-in file is not found
			throw e;    //rethrow exception to the caller
		}

		if(!fileScanner.hasNext())    //if the file is empty or blank
			throw new RuntimeException("File is empty.");
		
		loadNextLine();    //load the tokenArray with a line of text from the file
		
		Node newNode;    //the node to be returned
		
		String description;
		
		String id = file.getName().replace(".txt", "");    //used for setting the nodeID
		
		MMCharacter character1, character2;
		
		MMCharacter.Attribute attribute1, attribute2;
		
		//the next token must be "Information," "Test," or "Decision." The following switch block
		//creates the information object and attaches it to the appropriate node. 
		switch(iterateToken()) {
			case "Info":    //provides information to the user before proceeding to the next node
				description = iterateToken();
				
//				while(!iterateToken().equals("<-End")) {
//					System.out.println(currentToken);    //DEBUG
//					description += ("\n" + currentToken);
//				}
				
//				description = Files.readString(Path.of(projectDir + "\\content\\" + iterateToken()));
				
                newNode = new Node(id, description);
				
				if(!iterateToken().equals("NextNode"))
					throw new SyntaxError("unable to find token \"NextNode\" in file " + file.getName());
				
				newNode.setNextNode(new Node(iterateToken()));    //set the next node using a dummy node
				
				if(!iterateToken().equals("Image"))
					throw new SyntaxError("1 unable to find token \"Image\" in file " + file.getName());
				
				
				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				newNode.setImageFilename(iterateToken());    //set the image filename
				
				//Optional fields
				if(fileScanner.hasNext()) {
					if(iterateToken().equals("Title")) {
						newNode.setTitle(iterateToken());
					}
				}
				
				break;
				
			case "Challenge":    //routes the user to one of two node based on a pass/fail test
				
				description = iterateToken();
				
//				description = Files.readString(Path.of(projectDir + "\\content\\" + iterateToken()));
				
				if(!iterateToken().equals("Character1"))
					throw new SyntaxError("unable to find token \"Character1\" in file " + file.getName());
				
				//if the character is not in the Hashtable, add the character to the Hashtable
				if(!chtrHT.containsKey(iterateToken())) {
					try {
						chtrHT.put(getCurrentToken(), cParser.parseCharacter(new File(projectDir + 
								"characters\\" + getCurrentToken() + ".txt")));
					}
					catch(FileNotFoundException e) {
						throw e;
					}
				}
				
				character1 = chtrHT.get(getCurrentToken());
				
				if(!iterateToken().equals("Attribute1"))
					throw new SyntaxError("unable to find token \"Attribute1\" in file " + file.getName());
				
				attribute1 = MMCharacter.Attribute.valueOf(iterateToken());
				
				int difficulty;
				
				if(!iterateToken().equals("Difficulty"))
					throw new SyntaxError("unable to find token \"Difficulty\" in file " + file.getName());
				
				try {
				    difficulty = Integer.parseInt(iterateToken());
				}
				catch(NumberFormatException e) {
					throw e;
				}
				
				newNode = new ChallengeNode(id, description, character1, attribute1, difficulty);
				
				if(!iterateToken().equals("PassNode"))
					throw new SyntaxError("unable to find token \"passNode\" in file " + file.getName());
				
				newNode.setPassNode(new Node(iterateToken()));
				
				if(!iterateToken().equals("FailNode"))
					throw new SyntaxError("unable to find token \"failNode\" in file " + file.getName());
				
				newNode.setFailNode(new Node(iterateToken()));
				
				if(!iterateToken().equals("Image"))
					throw new SyntaxError("2 unable to find token \"Image\" in file " + file.getName());
				
				//if the character is not in the Hashtable, add the character to the Hashtable
//				if(!chtrHT.containsKey(iterateToken())) {
//					try {
//						chtrHT.put(getCurrentToken(), cParser.parseCharacter(new File(projectDir + 
//								"characters\\" + getCurrentToken() + ".txt")));
//					}
//					catch(FileNotFoundException e) {
//						throw e;
//					}
//				}
				
				//retrieve the character from the hashtable
//				character1 = chtrHT.get(getCurrentToken());
				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				newNode.setImageFilename(iterateToken());    //set the image filename
				
				break;
				
            case "Contest":    //routes the user to one of two node based on a pass/fail test
				
				description = iterateToken();
            	
//            	description = Files.readString(Path.of(projectDir + "\\content\\" + iterateToken()));
				
				if(!iterateToken().equals("Character1"))
					throw new SyntaxError("unable to find token \"Character1\" in file " + file.getName());
				
				//if the character is not in the Hashtable, add the character to the Hashtable
				if(!chtrHT.containsKey(iterateToken())) {
					try {
						chtrHT.put(getCurrentToken(), cParser.parseCharacter(new File(projectDir + 
								"characters\\" + getCurrentToken() + ".txt")));
					}
					catch(FileNotFoundException e) {
						throw e;
					}
				}
				
				//retrieve the character from the hashtable
				character1 = chtrHT.get(getCurrentToken());
				
				if(!iterateToken().equals("Attribute1"))
					throw new SyntaxError("unable to find token \"Attribute1\" in file " + file.getName());
				
				attribute1 = MMCharacter.Attribute.valueOf(iterateToken());
				
				if(!iterateToken().equals("Character2"))
					throw new SyntaxError("unable to find token \"Character1\" in file " + file.getName());
				
				//if the character is not in the Hashtable, add the character to the Hashtable
				if(!chtrHT.containsKey(iterateToken())) {
					try {
						chtrHT.put(getCurrentToken(), cParser.parseCharacter(new File(projectDir + 
								"characters\\" + getCurrentToken() + ".txt")));
					}
					catch(FileNotFoundException e) {
						throw e;
					}
				}
				
				character2 = chtrHT.get(getCurrentToken());
				
				if(!iterateToken().equals("Attribute2"))
					throw new SyntaxError("unable to find token \"Attribute1\" in file " + file.getName());
				
				attribute2 = MMCharacter.Attribute.valueOf(iterateToken());
				
				newNode = new ContestNode(id, description, character1, attribute1, character2, attribute2);
				
				if(!iterateToken().equals("PassNode"))
					throw new SyntaxError("unable to find token \"passNode\" in file " + file.getName());
				
				newNode.setPassNode(new Node(iterateToken()));
				
				if(!iterateToken().equals("FailNode"))
					throw new SyntaxError("unable to find token \"failNode\" in file " + file.getName());
				
				newNode.setFailNode(new Node(iterateToken()));
				
				if(!iterateToken().equals("Image"))
					throw new SyntaxError("3 unable to find token \"Image\" in file " + file.getName());
				

				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				newNode.setImageFilename(iterateToken());    //set the image filename
				
				break;
				
			case "Decision":    //routes the user to one of any number of nodes based on the user's decision
				
				description = iterateToken();
//				description = Files.readString(Path.of(projectDir + "\\content\\" + iterateToken()));
				
                newNode = new DecisionNode(id, description);
				
				if(!iterateToken().equals("Action"))    //exception if there are no actions (i.e. options) provided
					throw new SyntaxError(file.getName() + ". A DecisionNode must have at least one possible action.");
				
				//Add a new action onto the Decision object.
				//Note that this does not automatically make a new node. That step must be done separately/
				newNode.addAction(new Node(iterateToken()));
				
				//Keep parsing and adding each action and node until there are no more actions in the file
				while(fileScanner.hasNext() && iterateToken().equals("Action")) {				
					newNode.addAction(new Node(iterateToken()));    //add the action to the Decision object
				}
				
				if(!currentToken.equals("Image")) {
					throw new SyntaxError("4 unable to find token \"Image\" in file " + file.getName());
				}

				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				newNode.setImageFilename(iterateToken());    //set the image filename
				
				break;
				
			default:
				//If the file does not match any of the above, then the file is not properly formatted.
				//Throw an exception.
				throw new SyntaxError("File must start with \"Info,\" \"Challenge,\" \"Contest,\"or \"Decision.\"");
		}
		return newNode;
    }
    
    //Overloaded method for loading a node for editing
    public Node parseFile(File file) throws RuntimeException, 
    FileNotFoundException, EOFException, IOException, NullPointerException {
		try {
			fileScanner = new Scanner(file);    //Associate this instance's Scanner with the passed-in file
		}
		catch(FileNotFoundException e) {    //if the passed-in file is not found
			throw e;    //rethrow exception to the caller
		}

		if(!fileScanner.hasNext())    //if the file is empty or blank
			throw new RuntimeException("File is empty.");
		
		loadNextLine();    //load the tokenArray with a line of text from the file
		
		Node newNode;    //the node to be returned
		
		String description;
		
		String id = file.getName().replace(".txt", "");    //used for setting the nodeID
		
		String character1, character2;
		
		String attribute1, attribute2;
		
		String nextNodeID;
		
		//the next token must be "Information," "Test," or "Decision." The following switch block
		//creates the information object and attaches it to the appropriate node. 
		switch(iterateToken()) {
			case "Info":    //provides information to the user before proceeding to the next node
				description = iterateToken();
				
//				while(!iterateToken().equals("<-End")) {
//					System.out.println(currentToken);    //DEBUG
//					description += ("\n" + currentToken);
//				}
				
                newNode = new Node(id, description);
				
				if(!iterateToken().equals("NextNode"))
					throw new SyntaxError("unable to find token \"NextNode\" in file " + file.getName());
				
				newNode.setNextNodeID(iterateToken());    //set the next node ID
				
				if(!iterateToken().equals("Image"))
					throw new SyntaxError("5 unable to find token \"Image\" in file " + file.getName());

					iterateToken();    //Burn the image token for now
				
				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				// newNode.setImageFilename(currentToken);    //set the image filename
				
				//Optional fields
				if(fileScanner.hasNext()) {
					if(iterateToken().equals("Title")) {
						newNode.setTitle(iterateToken());
					}
				}
				
				break;
				
			case "Challenge":    //routes the user to one of two node based on a pass/fail test
				
				description = iterateToken();
				
//				description = Files.readString(Path.of(projectDir + "\\content\\" + iterateToken()));
				
				if(!iterateToken().equals("Character1"))
					throw new SyntaxError("unable to find token \"Character1\" in file " + file.getName());
				
				character1 = iterateToken();
				
				if(!iterateToken().equals("Attribute1"))
					throw new SyntaxError("unable to find token \"Attribute1\" in file " + file.getName());
				
				attribute1 = iterateToken();
				
				int difficulty;
				
				if(!iterateToken().equals("Difficulty"))
					throw new SyntaxError("unable to find token \"Difficulty\" in file " + file.getName());
				
				try {
				    difficulty = Integer.parseInt(iterateToken());
				}
				catch(NumberFormatException e) {
					throw e;
				}
				
				newNode = new ChallengeNode(id, description, character1, attribute1, difficulty);
				
				if(!iterateToken().equals("PassNode"))
					throw new SyntaxError("unable to find token \"passNode\" in file " + file.getName());
				
				newNode.setPassNodeID(iterateToken());
				
				if(!iterateToken().equals("FailNode"))
					throw new SyntaxError("unable to find token \"failNode\" in file " + file.getName());
				
				newNode.setFailNodeID(iterateToken());
				
				if(!iterateToken().equals("Image"))
					throw new SyntaxError("6 unable to find token \"Image\" in file " + file.getName());
				
				iterateToken();    //Burn the image token for now
				
				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				// newNode.setImageFilename(currentToken);    //set the image filename
				
				break;
				
            case "Contest":    //routes the user to one of two node based on a pass/fail test
				
				description = iterateToken();
            	
//            	description = Files.readString(Path.of(projectDir + "\\content\\" + iterateToken()));
				
				if(!iterateToken().equals("Character1"))
					throw new SyntaxError("unable to find token \"Character1\" in file " + file.getName());
				
				character1 = iterateToken();
				
				if(!iterateToken().equals("Attribute1"))
					throw new SyntaxError("unable to find token \"Attribute1\" in file " + file.getName());
				
				attribute1 = iterateToken();
				
				if(!iterateToken().equals("Character2"))
					throw new SyntaxError("unable to find token \"Character1\" in file " + file.getName());
				
				character2 = iterateToken();
				
				if(!iterateToken().equals("Attribute2"))
					throw new SyntaxError("unable to find token \"Attribute1\" in file " + file.getName());
				
				attribute2 = iterateToken();
				
				newNode = new ContestNode(id, description, character1, attribute1, character2, attribute2);
				
				if(!iterateToken().equals("PassNode"))
					throw new SyntaxError("unable to find token \"passNode\" in file " + file.getName());
				
				newNode.setPassNodeID(iterateToken());
				
				if(!iterateToken().equals("FailNode"))
					throw new SyntaxError("unable to find token \"failNode\" in file " + file.getName());
				
				newNode.setFailNodeID(iterateToken());
				
				if(!iterateToken().equals("Image"))
					throw new SyntaxError("7 unable to find token \"Image\" in file " + file.getName());
				
				iterateToken();    //Burn the image token for now

				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				// newNode.setImageFilename(currentToken);    //set the image filename
				
				break;
				
			case "Decision":    //routes the user to one of any number of nodes based on the user's decision
				
				description = iterateToken();
				
				if(!iterateToken().equals("Action"))    //exception if there are no actions (i.e. options) provided
					throw new SyntaxError(file.getName() + ". A DecisionNode must have at least one possible action.");
				
				ArrayList<String> actions = new ArrayList<String>();
				
				//Add a new action onto the Decision object.
				actions.add(iterateToken());
				
				//Keep parsing and adding each action and node until there are no more actions in the file
				while(fileScanner.hasNext() && iterateToken().equals("Action")) {				
					actions.add(iterateToken());    //add the action to the Decision object
				}
				
				newNode = new DecisionNode(id, description, actions);
				
				if(!currentToken.equals("Image")) {
					//DEBUG
					System.out.println(currentToken);
					throw new SyntaxError("8 unable to find token \"Image\" in file " + file.getName());
				}

				iterateToken();    //Burn the image token for now
				
				// try {
				//     newNode.setImage(projectDir + "images\\" + iterateToken());
				// }
				// catch(FileNotFoundException e) {
				// 	throw e;
				// }
				
				// newNode.setImageFilename(currentToken);    //set the image filename
				
				break;
				
			default:
				//If the file does not match any of the above, then the file is not properly formatted.
				//Throw an exception.
				throw new SyntaxError("File must start with \"Info,\" \"Challenge,\" \"Contest,\"or \"Decision.\"");
		}
		return newNode;
    }    
    
    public static void saveNodeFile(Node node, String directory) throws IllegalArgumentException, IOException, NullPointerException {
		if(node == null)
			throw new NullPointerException("NodeParser failed to save node. Node cannot be null.");
		
		if(directory == null) {
			throw new NullPointerException("NodeParser failed to save node. Directory cannot be null.");
		}
		
        PrintWriter writer = null;    //for writing to a file
    	
    	/* Construct the filename String from the directory and some information from the Media object. */
    	String filename = (directory + "//" + node.getNodeID() + ".txt");
		
		try {
		    writer = new PrintWriter(new FileWriter(filename));    //Create the new .txt file.
		}
		catch(IOException e) {
            throw e;
		}
		
		writer.print(node.toString());
		writer.flush();    //Make sure all the data is written to the file.
		writer.close();    //Close the output stream.
	}
    
    //Overloaded method for saving an edited file
    public static void saveNodeFile(Node node, File file) throws IllegalArgumentException, IOException, NullPointerException {
		if(node == null)
			throw new NullPointerException("NodeParser failed to save node. Node cannot be null.");
		
		if(file == null) {
			throw new NullPointerException("NodeParser failed to save node. File cannot be null.");
		}
		
        PrintWriter writer = null;    //for writing to a file
		
		try {
		    writer = new PrintWriter(new FileWriter(file.getAbsolutePath()));
		}
		catch(IOException e) {
            throw e;
		}
		
		writer.print(node.toString());
		writer.flush();    //Make sure all the data is written to the file.
		writer.close();    //Close the output stream.
	}
    
    
}
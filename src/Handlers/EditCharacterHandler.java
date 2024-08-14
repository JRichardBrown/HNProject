package Handlers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.CharacterParser;
import Parsers.NodeParser;
import decisionTree.ChallengeNode;
import decisionTree.ContestNode;
import decisionTree.DecisionNode;
import decisionTree.Node;

public class EditCharacterHandler extends GooeyListener {
	GooeyEntryFrame geFrame;
	SaveCharacterHandler scHandler;
	String projectDir;
	
	MMCharacter character;

	public void actionPerformed(ActionEvent actn) {
		File file = loadFile();
		
		//If the user chose cancel, the file will be null
		if(file == null) {
			return;
		}
		
		character = loadCharacter(file);
		
		if(character == null) {
			ExceptionHandler.handleException(new RuntimeException(
					"Failed to load a valid character file. Please check the file and try again."));
		}
		
		scHandler = new SaveCharacterHandler(projectDir);
		
		geFrame = new GooeyEntryFrame("Enter Character Information",
				new String[] {"Save", "Cancel"}, new GooeyListener[] {scHandler, scHandler},
				new String[] {"name", "strength", "agility", "perception", "stealth", "charisma", "luck", "intelligence"},
				new String[] {"Name", "Strength", "Agility", "Perception", "Stealth", "Charisma", "Luck", "Intelligence"},
				new String[] {character.getName(), Integer.toString(character.getStrength()), Integer.toString(character.getAgility()),
						Integer.toString(character.getPerception()), Integer.toString(character.getStealth()), 
								Integer.toString(character.getCharisma()), Integer.toString(character.getLuck()), 
								Integer.toString(character.getIntelligence())
								}
		);

		
		scHandler.setGooeyFrame(geFrame);    //pass the GooeyFrame to the SaveCharacterHandler
	}
	
	//used to load a node file for editing
	private MMCharacter loadCharacter(File file) {	        
	        CharacterParser cParser = new CharacterParser();
	        
	        try {
	            character = cParser.parseCharacter(file);    //load node for editing
	        } 
	        catch(Exception e) {
	        	ExceptionHandler.handleException(e);
	        	return null;
	        }
	        
	        return character;
	}
	
	private File loadFile() {
//        JFileChooser fileDialog = new JFileChooser("Please select a character file");
//		//Prompt the user to choose a file to load.
//        //If the user chooses "cancel," display a message and exit the program.
//        if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//        	return null;
//        }
//        
//        return fileDialog.getSelectedFile();
//      JFileChooser fileDialog = new JFileChooser("Please select a character file");
//		//Prompt the user to choose a file to load.
//      //If the user chooses "cancel," display a message and exit the program.
//      if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//      	return null;
//      }
//      
//      return fileDialog.getSelectedFile();
		
		File fProjDir = new File(projectDir + "characters\\");
		
		JComboBox<String> dropList = new JComboBox<String>(fProjDir.list());
		
        int result = JOptionPane.showConfirmDialog(null, dropList, "Select a character", JOptionPane.OK_CANCEL_OPTION);
		
		if(result != JOptionPane.OK_OPTION)
			return null;
		
		return new File(projectDir + "characters//" + dropList.getSelectedItem().toString());
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}
}

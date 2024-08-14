package Handlers;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.CharacterParser;
import Parsers.NodeParser;

public class SaveCharacterHandler extends GooeyListener{
	private GooeyEntryFrame geFrame;
	private MMCharacter character;
	private String characterDir;
	
	//constructor sets character directory
	SaveCharacterHandler(String dir) throws NullPointerException{
		if(dir == null)
			throw new NullPointerException("Character directory cannot be null.");
		
		characterDir = dir + "\\characters\\";
	}
	
	public void setGooeyFrame(GooeyEntryFrame geFr) {
		geFrame = geFr;    //pass the owning handler's GooeyEntryFrame
	}
	
    public void actionPerformed(ActionEvent actn) {
        if(actn.getActionCommand().equalsIgnoreCase("save")) {    		
    		/*
    		 * Call the NodeParser's saveNodeFile() method to create or update the file in the directory.
    		 */

    		try {
        	    character = new MMCharacter(geFrame.getText("name"), Integer.parseInt(geFrame.getText("strength")),
        			Integer.parseInt(geFrame.getText("agility")), Integer.parseInt(geFrame.getText("perception")), 
        			Integer.parseInt(geFrame.getText("stealth")), Integer.parseInt(geFrame.getText("charisma")), 
        			Integer.parseInt(geFrame.getText("luck")), Integer.parseInt(geFrame.getText("intelligence")));
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    		}
        	
        	try {
    		    CharacterParser.saveCharacterFile(character, characterDir);
    		}
    		catch(IllegalArgumentException e) {
    			ExceptionHandler.handleException(e);
    			return;
    		}
        	JOptionPane.showMessageDialog(null, "Character file saved successfully.");
        }
        geFrame.close();
    }
    
    public void setCharacterDir(String dir) {
    	characterDir = dir + "\\characters\\";
    }
}

package Handlers;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.CharacterParser;
import Program.EventController;

/*
 * Controls the functionality of the "Create Character" button
 */
public class CreateCharHandler extends GooeyListener{
	GooeyEntryFrame geFrame;
	SaveCharacterHandler scHandler;
	String projectDir;
	
	public void actionPerformed(ActionEvent actn) {
		scHandler = new SaveCharacterHandler(projectDir);
		
		geFrame = new GooeyEntryFrame("Enter Character Information",
				new String[] {"Save", "Cancel"}, new GooeyListener[] {scHandler, scHandler},
				new String[] {"name", "strength", "agility", "perception", "stealth", "charisma", "luck", "intelligence"},
				new String[] {"Name", "Strength", "Agility", "Perception", "Stealth", "Charisma", "Luck", "Intelligence"});
		
		scHandler.setGooeyFrame(geFrame);    //pass the GooeyFrame to the SaveCharacterHandler
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}
}

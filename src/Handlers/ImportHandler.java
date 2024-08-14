package Handlers;

import GooeyBits.GooeyListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImportHandler extends GooeyListener {
	String projectDir;
	
	//must set directory at creation
	public ImportHandler() {
		projectDir = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent actn) {
		//check if the file directory has been set
		if(projectDir == null)
			throw new NullPointerException("Project Directory cannot be null.");
		
	    JFileChooser fileDialog = new JFileChooser("Please select an image file");
	    
		//Prompt the user to choose a file to load.
	    //If the user chooses "cancel," display a message and exit the program.
	    if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
	  	    return;
	    
	    //create the destination path
	    File destination = new File(projectDir + "images\\" + fileDialog.getSelectedFile().getName());
	    
	    //copy the selected file to the destination path
	    try {
	        Files.copy(fileDialog.getSelectedFile().toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    }
	    catch(Exception e) {
	    	ExceptionHandler.handleException(e);
	    	return;
	    }
	    
	    JOptionPane.showMessageDialog(null, "Image imported successfully.");
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}
}

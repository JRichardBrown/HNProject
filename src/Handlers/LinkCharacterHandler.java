package Handlers;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import GooeyBits.GooeyEntryFrame;
import GooeyBits.GooeyListener;

public class LinkCharacterHandler extends GooeyListener {
	String projectDir;
	String destination;    //the name of the text component to write to
	GooeyEntryFrame geParentFrame;
	
	public LinkCharacterHandler(String dir) {
		projectDir = dir;
	}
	
	public LinkCharacterHandler(GooeyEntryFrame geFr, String dest, String projDir) {
		geParentFrame = geFr;
		destination = dest;
		projectDir = projDir;
	}
	
	public void actionPerformed(ActionEvent actn) {
		/*
         * Prompt the user for a directory in which to save the new Character file.
         */
//        JFileChooser fileDialog = new JFileChooser("Please choose a node file.");
//    		
//		fileDialog.setSelectedFile(null);    //no file initially chosen
//		
//		fileDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		
//		/*
//		 * If the user chooses the OK option, the following section will take
//		 * the selected directory and pass it as a File object to the Manager object's
//		 * loadDirectory() method.
//		 */
//		if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//		    return;
//		}
		
		File file = loadFile();
		
		if(file == null)
			return;
		
		String nodeID = file.getName().replace(".txt", "");    //used for setting the nodeID
		
		//If the CreateNode handler was called by a parent node 
        if(destination != null && geParentFrame != null)
            geParentFrame.setText(destination, nodeID);
	}
	
	private File loadFile() {
		File fProjDir = new File(projectDir + "characters\\");
		
		JComboBox<String> dropList = new JComboBox<String>(fProjDir.list());
		
        int result = JOptionPane.showConfirmDialog(null, dropList, "Select a node", JOptionPane.OK_CANCEL_OPTION);
		
		if(result != JOptionPane.OK_OPTION)
			return null;
		
		return new File(projectDir + dropList.getSelectedItem().toString());

		
	}
}

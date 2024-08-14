package Handlers;

import GooeyBits.GooeyFrame;
import GooeyBits.GooeyListener;
import Parsers.NodeParser;
import Program.EventController;
import decisionTree.Node;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
 
public class LoadProjectHandler extends GooeyListener {
	RunProjectHandler rpHandler;
	CreateCharHandler ccHandler;
	EditCharacterHandler ecHandler;
	EditNodeHandler enHandler;
	ImportHandler impHandler;
	GooeyFrame gFrame;
	//private String projectDir;
	
	@Override
	public void actionPerformed(ActionEvent actn) {
		rpHandler = new RunProjectHandler();
		ccHandler = new CreateCharHandler();
		ecHandler = new EditCharacterHandler();
		enHandler = new EditNodeHandler();
		impHandler = new ImportHandler();
		
		NodeParser nParser = new NodeParser();
		
		JFileChooser fileDialog = new JFileChooser("Please select your project folder."); 
		
		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if(fileDialog.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        //projectDir = fileDialog.getSelectedFile().toString() + "\\";
		EventController.setProjectDir(fileDialog.getSelectedFile().toString() + "\\");
        
        //set the project directory for the other listeners
        rpHandler.setProjectDir(EventController.getProjectDir());
        ccHandler.setProjectDir(EventController.getProjectDir());
        ecHandler.setProjectDir(EventController.getProjectDir());
        enHandler.setProjectDir(EventController.getProjectDir());
        impHandler.setProjectDir(EventController.getProjectDir());
        
        Node startNode = null;
		
        try {
            startNode = nParser.parseFile(new File(EventController.getProjectDir() + "nodes\\[START]n_1.txt"));
        }
        catch(IOException e) {
        	ExceptionHandler.handleException(e);
        	return;
        }
        
        gFrame = new GooeyFrame(startNode.getTitle(), 
        		new String[] {"Run Project", "Create Character", "Edit Character", "Edit Node", "Import Image"}, 
        		new GooeyListener[] {rpHandler, ccHandler, ecHandler, enHandler, impHandler});
        
        gFrame.resize(600, 100);
        
        gFrame.reposition(0, 100);
	}
}

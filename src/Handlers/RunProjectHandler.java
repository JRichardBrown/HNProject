package Handlers;

import GooeyBits.GooeyDisplayFrame;
import GooeyBits.GooeyListener;
import MMSimulator.MMCharacter;
import Parsers.NodeParser;
import Program.EventController;
import decisionTree.Tree;
import java.awt.event.ActionEvent;
import  java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class RunProjectHandler extends GooeyListener{
	private String projectDir;
    private GooeyDisplayFrame frame;
	
	@Override
    public void actionPerformed(ActionEvent actn) {
		
        Hashtable<String, MMCharacter>chtrHT = new Hashtable<String, MMCharacter>();
        
        NodeParser nParser = new NodeParser();
        
        String fileName = "[START]n_1.txt";    //set the first filename
        
        Tree dTree = new Tree();
        
        try {
            dTree.setCurrentNode(nParser.parseFile(new File(projectDir + "nodes//" + fileName), projectDir, chtrHT));
        } 
        catch(Exception e) {
        	ExceptionHandler.handleException(e);
        	return;
        }
        
        
    	//Set up the game GUI
    	String[] strArray = new String[]{"Next", "1", "2", "3", "4"};
    	
        SelHandler buttHandler = new SelHandler(dTree, fileName, projectDir, chtrHT);
        
    	GooeyListener[] gListener = new GooeyListener[]{buttHandler, buttHandler, buttHandler, 
    			buttHandler, buttHandler};
    	
        frame = new GooeyDisplayFrame(dTree.getCurrentNode().getTitle(), strArray, gListener);
        
        frame.print(dTree.getCurrentNode().getDescription());    //Display the opening screen
         
        buttHandler.setGFrame(frame);	

        try {
            frame.getImage().setImage(EventController.getProjectDir() + "\\" + "images\\" + dTree.getCurrentNode().getImageFilename());
        }
        catch(IOException e) {
            //if default image is not set, fail quietly
            if(dTree.getCurrentNode().getImageFilename().equals("default.png"))
                return;

            ExceptionHandler.handleException(e, "RunProjectHandler.ActionPerformed");
        }
       
	}
	
	public void setProjectDir(String dir) {
		projectDir = dir;
	}

    public void setImage(String path) throws IOException{
        try {
            frame.getImage().setImage(path);
        }
        catch(IOException e) {
            throw e;
        }   
    }
}

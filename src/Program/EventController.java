package Program;

import GooeyBits.GooeyFrame;
import GooeyBits.GooeyListener;
import Handlers.ExceptionHandler;
import Handlers.LoadProjectHandler;
import Handlers.NewProjectHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class EventController {
    static String projectDir = null;

    public static void setProjectDir(String dir) throws IllegalArgumentException {
        if (dir == null) {
            throw new IllegalArgumentException("Project directory cannot be null");
        }

        if (dir.isBlank()) {
            throw new IllegalArgumentException("Project directory cannot be blank.");
        }
        projectDir = dir;
    }

    public static String getProjectDir() {
        return projectDir;
    }
    
    /*
     * preconditions: none
     *
     */
    public static void main(String[] args) {
        //Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            ExceptionHandler.handleException(e, "main");
        }

        String[] mmNameArray = new String[] {"Load Project", "New Project"};
        
        LoadProjectHandler lpHandler = new LoadProjectHandler();
        
        NewProjectHandler npHandler = new NewProjectHandler();
        
        GooeyListener[] mmListener = new GooeyListener[] {lpHandler, npHandler};
        
        GooeyFrame mmFrame = new GooeyFrame("Hypernode 1.0", mmNameArray, mmListener);
        
        mmFrame.resize(400, 100);
        
        mmFrame.reposition(0, 0);
        
    //    ImageIcon imgIcon = new ImageIcon("default.png");
        
        // mmFrame.setIcon(imgIcon.getImage());
        
        mmFrame.closeProgramOnExit();
    }      
}

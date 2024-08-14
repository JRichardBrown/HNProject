package Handlers;

import javax.swing.JOptionPane;

/*
 * Displays an exception in a dialog box
 */
public class ExceptionHandler {
    //Receives an Exception object that it displays onscreen.
    public static void handleException(Exception e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }

    //Add the function name:
    public static void handleException(Exception e, String funcName) {
        JOptionPane.showMessageDialog(null, e.toString() + "[In function " + funcName + "]");
    }
}

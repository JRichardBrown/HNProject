package MMSimulator;
/**
 * Name: Brown, Jeffrey  
 * CMIS 242/6380  
 * Date: 02/20/2022
 * The Exceptions class holds all of the user-defined exceptions for this program.
 */

import java.io.*;
import java.util.Calendar;

public class Exceptions {
	
	/* Exceptions that extend IllegalArgumentException */
	
    public static class IllegalEventTypeException extends IllegalArgumentException {
        
        private String msg;
        
        public IllegalEventTypeException() {
           msg = "An invalid event type was included in one of the event files.";
        }
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " : " + msg;
       }
    }
	
    public static class IllegalFormatException extends IllegalArgumentException {
        
        private String msg;
        
        public IllegalFormatException() {
           msg = "The seclected file is incorrectly formatted for this program.";
        }
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " : " + msg;
       }
    }
	
	public static class IllegalNameArgumentException extends IllegalArgumentException {
	        
	        private String msg;
	        
	        public IllegalNameArgumentException(String ttl) {     
	            if(ttl == null)
	                msg = "Title cannot be null.";
	            else if (ttl.isEmpty())
	                msg = "Title cannot be empty.";
	            else if (ttl.isBlank())
	                msg = "Title cannot have all blank values.";
	        }
	        
	        @Override
	        public String toString() {
	            return this.getClass().getSimpleName() + " : " + msg;
	       }
	}
	
	/* Exceptions that extend FileNotFoundException */
	
	public static class DirectoryFailedToLoadException extends FileNotFoundException {
        
		 private String msg;
	        
	     public DirectoryFailedToLoadException() {      
	         msg = "The selected directory failed to load any Media objects.";
	     }
	        
	     @Override
	     public String toString() {
	         return this.getClass().getSimpleName() + " : " + msg;
	     }
	 }
	
	public static class FileDirectoryIsEmptyException extends FileNotFoundException {
        
	    private String msg;
	        
	    public FileDirectoryIsEmptyException() {      
	        msg = "The selected directory is empty.";
	    }
	        
	    @Override
	    public String toString() {
	        return this.getClass().getSimpleName() + " : " + msg;
	    }
	}
	
	public static class FileFailedToLoadException extends FileNotFoundException {
        
		 private String msg;
	        
	     public FileFailedToLoadException() {      
	         msg = "The selected file failed to load any Media objects.";
	     }
	        
	     @Override
	     public String toString() {
	         return this.getClass().getSimpleName() + " : " + msg;
	     }
	 }

	public static class FileIsEmptyException extends FileNotFoundException {
        
        private String msg;
        
        public FileIsEmptyException() {  
           msg = "The selected file is empty.";
        }
        
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " : " + msg;
        }
    }
}

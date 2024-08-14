package decisionTree;

import java.io.File;

import javax.swing.ImageIcon;

/** 
 * This is a general tree structure that supports any number of branches.
 */

public class Tree {
	private Node rootNode;
	private Node currentNode;
	private Node nextNode;    //do not confuse with member of Node class with same name
	private ImageIcon projectImage;

	//constructor
    public Tree() {
    	rootNode = null;
    	currentNode = null;
    	nextNode = null;
    	projectImage = null;
    }
    
    public Node getRoot() {return rootNode;}
    
    public Node getCurrentNode() {return currentNode;}
    
    public Node getNextNode() {return nextNode;}
    
    public void setRoot(Node rNode) {rootNode = rNode;}
    
    public void setCurrentNode(Node cNode) {currentNode = cNode;}
    
    public void setNextNode(Node nNode) {nextNode = nNode;}
    
    public void setImage(ImageIcon img) {
        projectImage = img;
    }
    
    public void setImage(String fp) {
        projectImage = new ImageIcon(fp);
    }
    
    public void setImage(File file) {
        projectImage = new ImageIcon(file.getAbsolutePath());
    }
    
    public ImageIcon getImage() {
    	return projectImage;
    }
}

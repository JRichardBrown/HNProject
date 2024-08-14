package decisionTree;

public class IDGenerator {
	//Generate ID when parent is Info-Type node
    public static String genID(String parentID) {
    	//If ID doesn't fit convention, let the user enter a custom ID
    	if(!parentID.contains("]") || !parentID.contains("_"))
    		return "Enter Custom ID";
    	
    	//remove the bracketed part
    	String[] temp = parentID.split("]");
    	
    	
        //Split the parent node ID into two parts: the string and the node count	
    	String[] idParts = temp[1].split("_");
    	
    	int nodeCount;
    	
    	try {
    	    nodeCount = Integer.parseInt(idParts[1]);
    	
		    nodeCount++;    //increase the node count
    	}
    	
    	//if string part is not an integer, set it to 0
    	catch(NumberFormatException e) {
    		return "Enter Custom ID";
    	}
		
	    return "[]" + idParts[0] + "_" + nodeCount;
    }
    
	//Generate ID when parent is Challenge-Type or Contest-Type node
    public static String genID(String parentID, String rslt) {
    	
    	//If ID doesn't fit convention, let the user enter a custom ID
    	if(!parentID.contains("]") || !parentID.contains("_"))
    		return "Enter Custom ID";
    	
    	//remove the bracketed part
    	String[] temp = parentID.split("]");
    	
    	
        //Split the parent node ID into two parts: the string and the node count	
    	String[] idParts = temp[1].split("_");
    	
		int nodeCount = 1;    //reset the node count
		
	    return "[]" + idParts[0] + rslt + "_" + nodeCount;
    }
    
	//Generate ID when parent is Decision-Type node
    public static String genID(String parentID, int sel) {
    	//If ID doesn't fit convention, let the user enter a custom ID
    	if(!parentID.contains("]") || !parentID.contains("_"))
    		return "Enter Custom ID";
    	
       	//remove the bracketed part
    	String[] temp = parentID.split("]");
    	
    	
        //Split the parent node ID into two parts: the string and the node count	
    	String[] idParts = temp[1].split("_");
    	

		int nodeCount = 1;    //reset the node count
		
		return "[]" + idParts[0] + sel + "_" + nodeCount;
    }
}

package MMSimulator;

import java.security.PublicKey;

/**
 * Name: Brown, Jeffrey  
 * CMIS 242/6380  
 * Date: (02/01/2022)
 * The MMCharacter class holds the character and attribute information of the simulation subjects.
 */
public class MMCharacter {
	
	/*MEMBER VARIABLES*/
	public static enum Attribute{STRENGTH, AGILITY, PERCEPTION, STEALTH, CHARISMA, LUCK, INTELLIGENCE}
	private String name;
    private int strength;
    private int agility;
    private int perception;  
    private int stealth;
	private int charisma;
    private int luck;
    private int intelligence;
    
    /*CONSTRUCTOR*/
    
    public MMCharacter(String nm, int str, int agl, int per, int stlth, int chrm, int lck, int intl) 
    		throws IllegalArgumentException {
    	//Initialize name
	    name = nm;
	    
	    //Initialize attributes
	    strength = str;
	    agility = agl;
		perception = per;
		stealth = stlth;
		charisma = chrm;
		luck = lck;
		intelligence = intl;
    }
    
    public MMCharacter(String nm, int[] attrList) 
    		throws IllegalArgumentException {
    	//Initialize name
	    name = nm;
	    
	    if(attrList.length != 7)
	    	throw new IllegalArgumentException("Attribute array must have exactly 7 elements.");
	    
	    //Initialize attributes
	    strength = attrList[0];
	    agility = attrList[1];
		perception = attrList[2];
		stealth = attrList[3];
		charisma = attrList[4];
		luck = attrList[5];
		intelligence = attrList[6];
    }
    
    /*MEMBER METHODS*/
    
    /*Get methods*/
    public String getName() {
    	return name;
    }
    public int getStrength() {
    	return strength;
    }
    
    public int getAgility() {
    	return agility;
    }  
    
    public int getPerception() {
    	return perception;
    }  
    
    public int getStealth() {
    	return stealth;
    }  
    
    public int getCharisma() {
    	return charisma;
    }  
    
    public int getLuck() {
    	return luck;
    }  
    
    public int getIntelligence() {
    	return intelligence;
    }
    
    public int getAttribute(Attribute atr) {
    	switch(atr) {
    	case STRENGTH:
    		return strength;
    	case AGILITY:
    		return agility;
    	case PERCEPTION:
    		return perception;
    	case STEALTH:
    		return stealth;
    	case CHARISMA:
    		return charisma;
    	case LUCK:
    		return luck;
    	case INTELLIGENCE:
    		return intelligence;
    	default:
    		throw new IllegalArgumentException("The attribute " 
    	    + atr.toString() + " does not exist.");
    	}
    }
    
    /*
     * toString() is used to print the Character's information to a file
     */
    public String toString() {
    	return ("Name->" + name + "\n"
    		  + "Strength->" + strength + "\n"
    	      + "Agility->" + agility + "\n"
    	      + "Perception->" + perception + "\n"
    	      + "Stealth->" + stealth + "\n"
    	      + "Charisma->" + charisma + "\n"
    	      + "Luck->" + luck + "\n"
    	      + "Intelligence->" + intelligence);
    }
}

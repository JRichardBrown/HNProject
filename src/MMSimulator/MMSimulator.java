package MMSimulator;
/**
 * Name: Brown, Jeffrey  
 * CMIS 242/6380  
 * Date: (02/01/2022)
 * Murder Mystery Simulator 2.0 simulates the events of a murder mystery scenario.
 * Success or failure of a character during a given event is based on a mix of
 * the character's ability, the difficulty of the task or contest, and random chance.
 */

import java.util.Random;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Murder Mystery Simulator class (MMSimulator. This class holds the information for 
 * the scenario and runs the simulation.
 */
public class MMSimulator {
	//Holds the dice constants
	public static class Dice {
		private static final int D_TEN = 11;    //Upper limit of random number
	}
	
	/*MEMBER VARIABLES*/
	
	private static Random rand = new Random();    //Used to add a random element to events
	
	/*MEMBER METHODS*/
	
	/*
	 * challenge() checks a character's ability, added to a random number,
	 * against a number representing the difficulty of an event to determine 
	 * success or failure. More difficult events have higher thresholds. It returns
	 * true if the character succeeds; it returns false if the character fails.
	 */
	public static boolean challenge(int abilityLvl, int difficulty) {
		return (abilityLvl + rand.nextInt(1, MMSimulator.Dice.D_TEN) >= difficulty);
	}
	
	/*
	 * Overloaded function lets you specify the degree of randomness
	 */
	public static boolean challenge(int abilityLvl, int difficulty, int maxRand) {
		return (abilityLvl + rand.nextInt(1, maxRand) >= difficulty);
	}
	
	/*
	 * contest() checks one characters ability score, plus a random number, against
	 * another player's ability score, plus a random number. The method returns
	 * true if contestant1 wins the contest and false if contestant2 wins. The method
	 * will recalculate in the event of a draw.
	 */
	public static boolean contest(int contestant1Lvl, int contestant2Lvl) {
		int score1;
		int score2;
		
		/*
		 * The do-while loop ensures that there is no draw.
		 */
		do {
			score1 = contestant1Lvl + rand.nextInt(1, MMSimulator.Dice.D_TEN);
			score2 = contestant2Lvl + rand.nextInt(1, MMSimulator.Dice.D_TEN);
		} while(score1 == score2);
		
		return(score1 > score2);
	}
	
	/*
	 * Overloaded function lets you specify the degree of randomness
	 */
	public static boolean contest(int contestant1Lvl, int contestant2Lvl, int maxRand) {
		int score1;
		int score2;
		
		/*
		 * The do-while loop ensures that there is no draw.
		 */
		do {
			score1 = contestant1Lvl + rand.nextInt(1, maxRand);
			score2 = contestant2Lvl + rand.nextInt(1, maxRand);
		} while(score1 == score2);
		
		return(score1 > score2);
	}
}

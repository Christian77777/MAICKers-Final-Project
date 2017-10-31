/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

import java.util.Scanner;

/**
 * @author kiana
 *
 */
public class UserInterface {
	private Scanner scanner;

	public UserInterface()
	{
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Display Welcome Message, and allow Main Menu Option
	 */
	public int welcomeMessage()
	{
		System.out.println("Message");
		int input = scanner.nextInt();
		if(input == 1)//Print Messages before anything if needed
		{
			System.out.println("Starting Game");
		}
		return input;
	}
	
	/**
	 * Present the Player with their options on their turn, and verify their syntax
	 * @return a verified integer corresponding to a action 
	 */
	public int pickTurn()
	{
		
	}
	
	/**
	 * Print out a congratulations or game over screen
	 */
	public void printGameOver(boolean victorious)
	{
		
	}
	
	/**
	 * Print if the player shot a ninja DEAD
	 */
	public void printShotResult()
	{
		
	}
}

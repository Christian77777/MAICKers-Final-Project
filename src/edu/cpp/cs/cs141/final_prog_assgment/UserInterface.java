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
	 * Display Welcome Message, and return a Main Menu Option
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
		return 0;
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
	
	/**
	 * Print warning message that the player lost a life, and returned to 0,0
	 */
	public void printDamaged()
	{
		
	}
	
	/**
	 * Print a query for the Difficulty to play the game at
	 * @return the selected difficulty
	 */
	public int offerDifficulty()
	{
		return 0;
	}
	
	public char queryShootingDirection()
	{
		return 'a';
	}
	
	public void printMap(Entities[][] map, char lookDirection, boolean debug)
	{
		System.out.println(map[0][0]);
	}
}

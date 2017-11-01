/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #Final
 *
 * Small Text Based Game of Spy versus Ninjas, where you get to shoot, move, and look
 *
 * Team MAICKers
 *	Isaiah Britto
 * 	Angela Gadon
 * 	Kiana Ziglari
 * 	Christian Devile
 * 	Michael John Bradford
 */
package edu.cpp.cs.cs141.final_prog_assgment;

import java.util.Scanner;

/**
 * This Class represents the User Console Based Interface, which prints info, and receives input from the Console 
 */
public class UserInterface {
	/**
	 * This Scanner scans the Console for Input from the User
	 */
	private Scanner scanner;

	public UserInterface()
	{
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Display Welcome Message, and return a Main Menu Option
	 * @return the Main Menu option selected by the user.
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
	 * @param canLook if the Player has not yet looked
	 * @param canShoot if the Player has ammo to shoot
	 * @return a verified integer corresponding to a action 
	 */
	public int pickTurn(boolean canLook, boolean canShoot)
	{
		return 0;
	}
	
	/**
	 * Print out a congratulations or game over screen
	 * @param victorious if the Player won, or AI won
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
	 * Ask User what to save name to this File as. Path is Locked
	 * @return the name user Request to Save the file As
	 */
	public String querySaveFileName()
	{
		return null;
	}
	
	/**
	 * Get List of Every Save File in Directory, query user, for which they wish to use.
	 * @return the file name
	 */
	public String queryLoadFileName()
	{
		return null;
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
	public boolean offerDifficulty()
	{
		return false;
	}
	
	public char queryMovingDirection()
	{
		return 'm';
	}
	public char queryLookingDirection()
	{
		return 'a';
	}
	public char queryShootingDirection()
	{
		return 'o';
	}
	
	public void printMap(Entities[][] map, char lookDirection, boolean debug)
	{
		System.out.println(map[0][0]);
	}
}

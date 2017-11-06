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

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This Class represents the User Console Based Interface, which prints info,
 * and receives input from the Console
 */
public class UserInterface {
	/**
	 * This Scanner scans the Console for Input from the User
	 */
	private Scanner scanner;

	public UserInterface() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Display Welcome Message, and return a Main Menu Option
	 * 
	 * @return the Main Menu option selected by the user.
	 */
	public int welcomeMessage() {
		System.out.println("Message");
		int input = scanner.nextInt();
		if (input == 1)// Print Messages before anything if needed
		{
			System.out.println("Starting Game");
		}
		return input;
	}

	/**
	 * Present the Player with their options on their turn, and verify their syntax
	 * 
	 * @param canLook
	 *            if the Player has not yet looked
	 * @param canShoot
	 *            if the Player has ammo to shoot
	 * @return a verified integer corresponding to a action
	 */
	public int pickTurn(boolean canLook, boolean canShoot) {
		return 0;
	}

	/**
	 * Print out a congratulations or game over screen
	 * 
	 * @param victorious
	 *            if the Player won, or AI won
	 */
	public void printGameOver(boolean victorious) {

	}

	/**
	 * Print if the player shot a ninja DEAD
	 */
	public void printShotResult() {

	}

	/**
	 * Ask User what to save name to this File as. Path is Locked
	 * 
	 * @return the name user Request to Save the file As
	 */
	public String querySaveFileName() {
		return null;
	}

	/**
	 * Get List of Every Save File in Directory, query user, for which they wish to
	 * use.
	 * 
	 * @return the file name
	 */
	public String queryLoadFileName() {
		System.out.println("What would you like to name your Save file to?");
		String result = scanner.nextLine();
		boolean namePermissible;
		try {
			Paths.get("dir", result, ".ser");//TODO verify this works
			namePermissible = true;
		}
		catch(InvalidPathException p)
		{
			namePermissible = false;
		}
		while (namePermissible){
			System.out.print("\nThats not a Option! Please enter again either (Y/N)\nResponse:");
			result = scanner.nextLine();
			try {
				Paths.get("dir", result, ".ser");//TODO verify this works
				namePermissible = true;
			}
			catch(InvalidPathException p)
			{
				namePermissible = false;
			}
		}
		System.out.println();
		return result;
	}

	/**
	 * Print warning message that the player lost a life, and returned to 0,0
	 */
	public void printDamaged() {
		System.out.println("A Ninja got you! You are forced to retreat to the Starting Point");
	}

	/**
	 * Print a query for the Difficulty to play the game at In Reality, define if
	 * the AI should be enabled
	 * 
	 * @return the selected difficulty, where true means enable AI
	 */
	public boolean offerDifficulty() {
		System.out.print("Would you like to enable the ninja AI? (Y/N)\nResponse: ");
		String result = scanner.nextLine();
		while (result.length() != 1 && (result.equalsIgnoreCase("N") || result.equalsIgnoreCase("Y"))) {
			System.out.print("\nThats not a Option! Please enter again either (Y/N)\nResponse:");
			result = scanner.nextLine();
		}
		System.out.println();
		return result.equalsIgnoreCase("Y");
	}

	/**
	 * Gets a Direction from the UI
	 * 
	 * @param actionType
	 *            What is the Direction entered in reference to
	 * @return The Direction the user specified, as a char of either n,s,w,e
	 */
	public char queryDirection(String actionType) {
		System.out.print("Which direction would you like to " + actionType + " in? (N/S/E/W)\nDirection: ");
		String result = scanner.nextLine();
		while (result.length() != 1 && (result.equalsIgnoreCase("N") || result.equalsIgnoreCase("S")
				|| result.equalsIgnoreCase("W") || result.equalsIgnoreCase("E"))) {
			System.out.print("\nThats not a Cardinal direction! Please enter again either (N/S/W/W)\nDirection:");
			result = scanner.nextLine();
		}
		System.out.println();
		return result.toLowerCase().charAt(0);
	}

	/**
	 * Prints out to the Console the Map as well as the Legend If the User looked in
	 * a direction,
	 * 
	 * @param map
	 *            the locations of all the entities
	 * @param lookDirection
	 *            'f' if no looking done. n/s/e/w otherwise to represent the 4
	 *            directions
	 * @param debug
	 *            if Everything should just be revealed
	 * @param radarActive
	 *            if the suitcase should be shown
	 */
	public void printMap(String[] map, char lookDirection, boolean debug, boolean radarActive) {
		char[][] board = formatMap(map, lookDirection, debug, radarActive);
		String console = "";
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				console += "[" + board[x][y] + "]";
			}
			console += "\n";
		}
		console += "\n\nLegend:\n\tP = player\n\tN = ninja\n\tA= bullet\n\tI = invincibility\n\tR = radar\n\tB = briefcase!\n";
		System.out.println(console);
	}

	/**
	 * Formats the Game Board to show a single entity, and if that entity should be
	 * shown based on the parameters
	 * 
	 * @param map
	 *            the locations of all the entities
	 * @param lookDirection
	 *            'f' if no looking done. n/s/e/w otherwise to represent the 4
	 *            directions
	 * @param debug
	 *            if Everything should just be revealed
	 * @param radarActive
	 *            if the suitcase should be shown
	 * @return a Matrix with each slot representing a character that should be shown
	 *         by the UI
	 */
	private char[][] formatMap(String[] map, char lookDirection, boolean debug, boolean radarActive) {
		// current coordinates
		int x = 0;
		int y = 0;
		// Player coordinates
		int i = 0;
		int j = 0;
		char[][] slots = new char[9][9];
		char[][] board = new char[9][9];
		for (int z = 0; z < 81; z++) {
			if (x < 9) {
				x++;
			} else {
				y++;
				x = 0;
			}
			if (!(map[z].charAt(1) == ('0'))) {
				slots[x][y] = map[z].charAt(2);
			}
			if (map[z].charAt(0) == ('1')) {
				slots[x][y] = 'p';
				i = x;
				j = y;
			}
			if (map[z].charAt(2) == ('1')) {
				slots[x][y] = 'n';
			}
		}
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				if (lookDirection == 'f') {
					if (slots[a][b] != 'p') {
						board[a][b] = 'X';
					} else {
						board[a][b] = 'p';
					}
				} else {
					boolean viewedSlots = false;
					if (lookDirection == 'n') {
						viewedSlots = (a == i && (b > j && b < j + 3));
					} else if (lookDirection == 's') {
						viewedSlots = (a == i && (b < j && b > j - 3));
					} else if (lookDirection == 'w') {
						viewedSlots = ((a < i && a > i - 3) && b == j);
					} else if (lookDirection == 'e') {
						viewedSlots = ((a > i && a < i + 3) && b == j);
					}
					if (slots[a][b] == 'p') {
						board[a][b] = 'p';
					} else if (slots[a][b] == 'n' && (viewedSlots || debug)) {
						board[a][b] = 'n';
					} else if ((slots[a][b] == 'a' || slots[a][b] == 'i' || slots[a][b] == 'r')
							&& (viewedSlots || debug)) {
						board[a][b] = slots[a][b];
					} else if (slots[a][b] == 'b' && (debug || (viewedSlots && (j + 1 == b && i == a)))) {
						board[a][b] = 'b';
					} else {
						board[a][b] = 'X';
					}
				}
			}
		}
		return board;
	}
}

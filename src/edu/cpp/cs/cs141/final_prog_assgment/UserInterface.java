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

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
	/**
	 * Coordinates that define a Special room, for both x and y axis
	 */
	private static int[] specialCoords = { 1, 4, 7 };

	/**
	 * Creates Scanner from Console Input
	 */
	public UserInterface() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Display Welcome Message, and return a Main Menu Option
	 * 
	 * @return the Main Menu option selected by the user.
	 */
	public int welcomeMessage() {
		System.out.println("Welcome to the Spy Game!\n1. Start New Game\n" + "2. Load Game\n" + "3. Help\n"
				+ "4. Quit?\nChoice: ");
		int input = 0;
		try {
			input = scanner.nextInt();
		} catch (Exception i) {

		}
		if (input == 1)// Print Messages before anything if needed
		{
			System.out.println("Starting Game");
		} else if (input == 4) {
			System.exit(0);
		}
		return input;
	}

	/**
	 * Display help to the User
	 */
	public void printHelp() {

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
		// TODO
		// add an option for the user to save the game.
		ArrayList<Integer> options = new ArrayList<Integer>();
		StringBuilder builder = new StringBuilder("What would you like to do?");
		builder.append("\n1. Move");
		options.add(1);
		if (canLook) {
			builder.append("\n2. Look");
			options.add(2);
		} else {
			builder.append("\n- Already Looked");
		}
		if (canShoot) {
			builder.append("\n3. Shoot");
			options.add(3);
		} else {
			builder.append("\n- No Ammo");
		}
		builder.append("\n4. Save");
		builder.append("\n5. Quit");
		options.add(4);
		options.add(5);
		options.add(-1);
		builder.append("\nOption: ");
		do {
			System.out.print(builder.toString());
			try {
				int result = scanner.nextInt();
				scanner.nextLine();
				if (options.contains(result)) {
					if (result == 5) {
						System.exit(0);
					}
					return result;
				}
				System.out.println("This is not a option");
			} catch (InputMismatchException i) {
				System.out.println("That is not a even a Number!");
				scanner.nextLine();
			}
		} while (true);
	}

	/**
	 * Print out a congratulations or game over screen
	 * 
	 * @param victorious
	 *            if the Player won, or AI won
	 * @return if the player wants to restart the game
	 */
	public boolean printGameOver(boolean victorious) {
		if (victorious) {
			System.out.println("Congratulations you won!");
		} else {
			System.out.println("You Lost all your lives. Game Over");
		}
		System.out.println("Would you like to play again? (Y/N)\n Response: ");
		String result = scanner.next();
		scanner.nextLine();
		while (!(result.equalsIgnoreCase("N") || result.equalsIgnoreCase("Y"))) {
			System.out.print("\nThats not a Option! Please enter again either (Y/N)\nResponse:");
			result = scanner.next();
			scanner.nextLine();
		}
		System.out.println();
		return result.equalsIgnoreCase("Y");

	}

	/**
	 * Print if the player shot a Ninja DEAD
	 */
	public void printShotResult(boolean result) {
		double dice = Math.random();
		if (result) {
			if (dice < .25) {
				System.out.println("You killed a Ninja");
			} else if (dice < .50) {
				System.out.println("A Ninja is now dead");
			} else if (dice < .75) {
				System.out.println("Ninja just said his last words at your hands");
			} else if (dice < 1.0) {
				System.out.println("One less Ninja to worry about");
			}
		} else {
			if (dice < .25) {
				System.out.println("Wasted a Bullet");
			} else if (dice < .50) {
				System.out.println("Ninjas are uneffected by you");
			} else if (dice < .75) {
				System.out.println("Stop shooting blindly, you missed them all");
			} else if (dice < 1.0) {
				System.out.println("Shooted in the wrong direction");
			}
		}
	}

	/**
	 * Ask User what to save name to this File as. Path is Locked
	 * 
	 * @return the name user Request to Save the file As
	 */
	public String querySaveFileName() {
		System.out.println("What would you like to name your Save file to?\nName: ");
		do {
			String result = scanner.nextLine();
			try {
				// TODO verify that entered Strings are acceptable in File Name (does this
				// actually work?)
				// sysout only to see the result, not to be reprinted
				// must pass in save directory
				System.out.println(Paths.get("dir" + File.separator + result + ".ser"));
				return result;
			} catch (InvalidPathException p) {

				System.out.print("\nThats not a Valid file Name! Please remove invalid characters\nName:");
			}
		} while (true);
	}

	public void printInvalidMove() {
		System.out.println("You can not move in that direction! Pick another Direction");
	}

	/**
	 * Get List of Every Save File in Directory, query user, for which they wish to
	 * use.
	 * 
	 * @return the file name
	 */
	public String queryLoadFileName() {
		return null;
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
		String result = scanner.next(); // changed from nextLine to next
		scanner.nextLine();
		// TODO this statement in the while loop should be checking if it DOESN't equal
		// yes or no
		while (!(result.equalsIgnoreCase("N") || result.equalsIgnoreCase("Y"))) {
			System.out.print("\nThats not a Option! Please enter again either (Y/N)\nResponse:");
			result = scanner.next(); // changed from nextLine to next
			scanner.nextLine();
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
		String result = scanner.next();// changed from nextLine to next
		while (result.length() != 1 && (result.equalsIgnoreCase("N") || result.equalsIgnoreCase("S")
				|| result.equalsIgnoreCase("W") || result.equalsIgnoreCase("E"))) {
			System.out.print("\nThats not a Cardinal direction! Please enter again either (N/S/W/W)\nDirection:");
			result = scanner.next();// changed from nextLine to next
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
		/*
		 * int z = 0; for (int x = 0; x < 9; x++) { for (int y = 0; y < 9; y++) {
		 * console += "[" + map[z] + "]"; z++; } console += "\n"; }
		 */
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				console += "[" + board[x][y] + "]";
			}
			console += "\n";
		}
		// console += "\nLegend:\n\tP = player\n\tN = ninja\n\tA = bullet\n\tI =
		// invincibility\n\tR = radar\n\tB = briefcase!\n";
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
			if (!(map[z].charAt(1) == ('0'))) {
				slots[y][x] = map[z].charAt(1);
			}
			if (map[z].charAt(0) == ('1')) {
				slots[y][x] = 'p';
				i = x;
				j = y;
			}
			if (map[z].charAt(2) == ('1')) {
				slots[y][x] = 'n';
			}
			if (x < 8) {
				x++;
			} else {
				y++;
				x = 0;
			}
		}
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				boolean viewedSlots = false;
				// most indices regarding slots in the matrixes need to be
				// [y][x] instead of [x][y]
				switch (lookDirection) {
				case 'n':
					viewedSlots = (b == i && (a < j && a > j - 3));
					break;
				case 's':
					viewedSlots = (b == i && (a > j && a < j + 3));
					break;
				case 'w':
					viewedSlots = ((b < i && b > i - 3) && a == j);
					break;
				case 'e':
					viewedSlots = ((b > i && b < i + 3) && a == j);
					break;
				case 'f':
					viewedSlots = false;
					break;
				default:
					throw new IllegalArgumentException();
				}
				if (slots[a][b] == 'p') {
					board[a][b] = 'P';
				} else if (slots[a][b] == 'n' && (viewedSlots || debug)) {
					board[a][b] = 'N';
				} else if ((slots[a][b] == 'a' || slots[a][b] == 'i' || slots[a][b] == 'r') && (viewedSlots || debug)) {
					board[a][b] = Character.toUpperCase(slots[a][b]);
				} else if (slots[a][b] == 'b'
						&& (debug || radarActive/* || (viewedSlots && ((j - 1) == b && i == a)) */))// Only
																									// Show
																									// Briefcase
																									// if
																									// Player
																									// is
																									// just
																									// above
																									// room
																									// and
																									// looks
																									// South
				{
					board[a][b] = 'B';
				} else if ((specialCoords[0] == a || specialCoords[1] == a || specialCoords[2] == a)
						&& (specialCoords[0] == b || specialCoords[1] == b || specialCoords[2] == b)
						&& board[a][b] != 'B') {
					board[a][b] = '#';
				} else if (!debug && !viewedSlots) {
					board[a][b] = '\u2022';
				}

			}
		}
		return board;
	}

	public void printRoomContents(boolean briefcase) {
		if (briefcase)
			System.out.println("The briefcase is in this room!");
		else
			System.out.println("This room is empty.");
	}

	public void printPowerUp(char item) {
		if (item == 'a')
			System.out.println("You've found a bullet! You now have max ammo");
		else if (item == 'i')
			System.out.println("You're invincible for 5 turns! Ninjas can't kill you.");
		else if (item == 'r')
			System.out.println("You've found a radar! You know where the briefcase is.");
	}
}

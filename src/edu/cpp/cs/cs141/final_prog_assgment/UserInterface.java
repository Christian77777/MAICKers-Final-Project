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

/**
 * @author Christian77777
 *
 */
public abstract class UserInterface{

	/**
	 * Coordinates that define a Special room, for both x and y axis
	 */
	private static int[] specialCoords = { 1, 4, 7 };
	
	public int[] getSpecialCoords()
	{
		return specialCoords;
	}
	
	public abstract int welcomeMessage();
	
	public abstract void printHelp();
	
	public abstract int pickTurn(boolean canLook, boolean canShoot);
	
	public abstract boolean printGameOver(boolean victorious);
	
	public abstract void printShotResult(boolean result);
	
	public abstract String querySaveFileName();
	
	public abstract void printInvalidMove();
	
	public abstract String queryLoadFileName();
	
	public abstract void printDamaged();
	
	public abstract boolean offerDifficulty();
	
	public abstract char queryDirection(String actionType);
	
	public abstract void printMap(String[] map, char lookDirection, boolean debug, boolean radarActive);
	
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
	public char[][] formatMap(String[] map, char lookDirection, boolean debug, boolean radarActive) {
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
	
	public abstract void printRoomContents(boolean briefcase);
	
	public abstract void printPowerUp(char item);
}

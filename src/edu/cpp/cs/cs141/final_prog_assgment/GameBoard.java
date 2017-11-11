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

import java.io.Serializable;
import java.util.Random;

import edu.cpp.cs.cs141.final_prog_assgment.Items.ItemType;

/**
 * This Class contains all the Data for a Save Game. Creating this Object also
 * creates all necessary Entities, and provides a Matrix Representation of the
 * Map. Also Keeps Track of the Turn Count of the active Game
 */
public class GameBoard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1601258203598047430L;
	
	private String[] board = new String[81];
	/**
	 * Puts Enemy in possible spaces Places Items in possible spaces
	 */
	public GameBoard() {
		for(int i =0; i < board.length; i++) {
			board[i] = "000";
		}
		board[72] = "100"; // The player always starts at spot 72
		for (int i = 0; i < 6; i++) {
			placeNinja();
		}
		placeItem(ItemType.BRIEFCASE);
		placeItem(ItemType.RADAR);
		placeItem(ItemType.INVINC);
		placeItem(ItemType.BULLET);
		
	}
	
	/**
	 * Places a ninja in a spot on the board, rules for placing are as follows
	 * 1. Cannot be placed in a room 2. Cannot be placed on another ninja 3. Cannot be placed
	 * within 3 spaces of the player.
	 * 
	 * Ninja place in data ("000") string is 3rd, 
	 * 1 - true ninja is there(001)
	 * 0 - false no ninja there.(000)
	 */
	private void placeNinja() {
		boolean InvalidSpot = true;
		Random r = new Random();
		int num = r.nextInt(81);
		while(InvalidSpot) {
			num = r.nextInt(81);
			if (!isRoom(num)) { 
				if(!inProxPlayer(num)) { 
					if(checkFlag(num, 3, '0')) {
						InvalidSpot = false;
					}
				}
			}
		}
		setFlag(num, 3, '1');
	}
	
	/**
	 * Places an item in a spot on the board, rules for placing are as follows
	 * 1. Cannot be placed on player 2. Cannot be placed in room if not Briefcase 
	 * 3. If Briefcase MUST be placed in room 4. Cannot be placed in spot with another item
	 * 
	 * Item place in data ("000") string is 2nd, 
	 * 0 - No item present (000)
	 * a - item present is ammo (0a0)
	 * i - item present is invincibility (0i0)
	 * r - item present is radar (0r0)
	 * b - item present is Briefcase (0b0)
	 */
	private void placeItem(ItemType type) {
		boolean InvalidSpot = true;
		Random r = new Random();
		int num = r.nextInt(81);
		if(type == ItemType.BRIEFCASE) {
			num = r.nextInt(9);
			switch (num) {
			case 0:
				setFlag(10, 2, 'b');
				break;
			case 1:
				setFlag(13, 2, 'b');
				break;
			case 2:
				setFlag(16, 2, 'b');
				break;
			case 3:
				setFlag(37, 2, 'b');
				break;
			case 4:
				setFlag(40, 2, 'b');
				break;
			case 5:
				setFlag(43, 2, 'b');
				break;
			case 6:
				setFlag(64, 2, 'b');
				break;
			case 7:
				setFlag(67, 2, 'b');
				break;
			case 8:
				setFlag(70, 2, 'b');
				break;
			}
		} else {
			while(InvalidSpot) {			
				num = r.nextInt(81);
				if (!isRoom(num)) { 
					if(!checkFlag(num, 2, 'a') && !checkFlag(num, 2, 'i') && !checkFlag(num, 2, 'r')) { 
						InvalidSpot = false;
					}
				}
			}
			switch (type) {
			case BULLET:
				setFlag(num, 2, 'a');
				break;
			case INVINC:
				setFlag(num, 2, 'i');
				break;
			case RADAR:
				setFlag(num, 2, 'r');
				break;				
			}
		}
	}
	
	/**
	 * Takes the board value at spot of three characters and sets the char at flagNumber to i.

	 * @param spot - spot on board to be changed.
	 * @param flagNumber - which flag to set 1-3
	 * @param i - what to set flag to.
	 */
	protected void setFlag(int spot, int flagNumber, char i) {
		char tmp1 = board[spot].charAt(0);
		char tmp2 = board[spot].charAt(1);
		char tmp3 = board[spot].charAt(2);
		if(flagNumber == 1) {
			board[spot] = "" + i + tmp2 + tmp3;
		}else if (flagNumber == 2) {
			board[spot] = "" + tmp1 + i + tmp3;
		}else
			board[spot] = "" + tmp1 + tmp2 + i;
	}
	
	/**
	 * Checks flagNumber at spot on the board to see if it equals i.

	 * @param spot - spot on board to be checked.
	 * @param flagNumber - which flag to check 1-3
	 * @param i - what to compare the flag to
	 * @return true - if flag == i otherwise false
	 */
	protected boolean checkFlag(int spot, int flagNumber, char i)
	{
		return (board[spot].charAt(flagNumber - 1) == i);
	}

	/**
	 * checks if spot in array is in proximity
	 * to the player (within 3 spaces).
	 * @param i - the spot to check
	 */
	private boolean inProxPlayer(int i) {
		if(i == 72 || i == 73 || i == 74 || i == 63 || i == 54 || i == 55 || i == 56 || i == 64 || i == 65) {
			return true;
		}else
			return false;
	}
	/**
	 * checks if spot in array is a room.
	 * @param i - the spot to check
	 */
	protected boolean isRoom(int i){			
		if(i == 10 || i == 13 || i == 16 || i == 37 || i == 40 || i == 43 || i == 64 || i == 67 || i == 70) {
			return true;
		}else
			return false;
	}
	
	/**
	 * 
	 * @return the array of strings that hold the data for the map
	 */
	protected String[] getMapData() {
		return board;
	}
	
	/**
	 * Loops through board array and checks for player position
	 * shouldn't ever return -1.
	 * @return int of player's location
	 */
	protected int getPlayerLoc() {
		for (int i=0; i < 81; i++) {
			if (checkFlag(i, 1, '1')) {
				return i;
			}
		}
		return -1;
	}
}

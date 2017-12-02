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
 * Main Class to Start Program
 */
public class Main {

	/**
	 * Start the Program/Game
	 * 
	 * @param args
	 *            from commands to enable the cui
	 */
	public static void main(String[] args) {
		boolean guiUsed = true;
		if (args.length == 1 && args[0].equals("-cui")) {
			guiUsed = false;
		}
		GameEngine gameEngine = new GameEngine(guiUsed);
		gameEngine.startGame();
	}

}

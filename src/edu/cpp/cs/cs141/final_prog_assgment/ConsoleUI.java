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
public class ConsoleUI extends UserInterface {
	/**
	 * This Scanner scans the Console for Input from the User
	 */
	private Scanner scanner;

	/**
	 * Creates Scanner from Console Input
	 */
	public ConsoleUI() {
		scanner = new Scanner(System.in);
	}

	@Override
	public void welcomeMessage() {
		printPicture(true);
		System.out.println("Welcome to the Spy Game!");
	}

	@Override
	public int printMainMenu() {
		System.out.print("Main Menu\n1. Start New Game\n" + "2. Load Game\n" + "3. Help\n" + "4. Quit?\nChoice: ");
		int input = 0;
		boolean validInput = false;
		while (!validInput) {
			try {
				input = scanner.nextInt();
				if (input < 1 || input > 4)
					System.out.println("Please Enter A Number From 0-4.");
				else if (input == 4)
					System.exit(0);
				else
					validInput = true;
			} catch (Exception i) {
				System.out.println("This Isn't A Number!");
				scanner.nextLine();
			}
		}
		clearScreen();
		return input;
	}

	@Override
	public void printHelp() {
		System.out
				.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("\n\t\t\t\t Welcome to the Spy Game!");
		System.out
				.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		printObject();
		System.out
				.print("\n__________________________________________________________________________________________");
		pressEnterToContinue();
		clearScreen();

		System.out
				.print("\n__________________________________________________________________________________________");
		printStartMenu();
		System.out
				.print("\n__________________________________________________________________________________________");
		pressEnterToContinue();
		clearScreen();

		System.out
				.print("\n__________________________________________________________________________________________");
		printStartGame();
		System.out
				.print("\n__________________________________________________________________________________________");
		pressEnterToContinue();
		clearScreen();

		System.out
				.print("\n__________________________________________________________________________________________");
		printLoadGame();
		System.out
				.print("\n__________________________________________________________________________________________");
		pressEnterToContinue();
		clearScreen();

		System.out
				.print("\n__________________________________________________________________________________________");
		printPowerUps();
		System.out
				.print("\n__________________________________________________________________________________________");
		pressEnterToContinue();
		clearScreen();

		System.out
				.print("\n__________________________________________________________________________________________");
		System.out.print("\nDo you think you have what it takes to find the room with the briefcase before a Ninja"
				+ "\nfinds YOU?! Enter 1 to start a new game and see for yourself if you are worthy to be"
				+ "\ncalled a Spy or are you just a wanna-be Spy?");
		System.out
				.print("\n__________________________________________________________________________________________");
		pressEnterToContinue();
		clearScreen();

		printPicture(false);
		pressEnterToContinue();
		clearScreen();

	}

	/**
	 * Print Power Up Help text
	 */
	private void printPowerUps() {
		System.out
				.print("\nPower-Ups:\n\tThroughout the game board there are three possible Power-Ups you may encounter"
						+ "\n\twhile playing. To capture these Power-Ups, simply walk over them! When you walk"
						+ "\n\tinto there space, they are activated. The three Power-Ups are:" + "\n\t\t1. Radar"
						+ "\n\t\t\tThe Radar Power-Up is indicated by [R] on the game board. This"
						+ "\n\t\t\tPower-Up is arguably the most helpful out of them all. Radar gives"
						+ "\n\t\t\tyou the ability to see what room the briefcase is in! If you are"
						+ "\n\t\t\table to find it, you will nolonger need to search each room! Now"
						+ "\n\t\t\tthe room which contains the briefcase will be seen as [B]."
						+ "\n\t\t2. Invincibility"
						+ "\n\t\t\tThe Invincibility Power-Up is indicated by [I] on the game board."
						+ "\n\t\t\tThis Power-Up give you ability to make 5 turns without worrying"
						+ "\n\t\t\tabout Ninjas getting you! You can't die for 5 turns!" + "\n\t\t3. Ammo"
						+ "\n\t\t\tThis Power-Up gives you extra ammo! *NOTE:You are only allowed a"
						+ "\n\t\t\tmax of 1 bullet at a time. If you collect this Power-Up without"
						+ "\n\t\t\tusing your intital bullet first, this Power-Up will have no effect"
						+ "\n\t\t\ton you. ");

	}

	/**
	 * Print Start of Game Help Text
	 */
	private void printStartGame() {
		System.out.print(
				"\nStarting a New Game:\n\t\t When starting a new game, you will then be asked if you'd like to enable"
						+ "\n\t      the AI for the ninjas. Enabling the AI makes the game slightly"
						+ " harder to win \n\t      as you have to outsmart the computer in your moves!");
		System.out.print(
				"\n\tOnce a game is started you are presented with 5 options, you can either:" + "\n\t\t1. Move");
		System.out.print("\n\t\t\tOnce you enter 1 to move, you'll then be asked if you want to move"
				+ "\n\t\t\tNorth, South, East or West. To move in such direction simply enter"
				+ "\n\t\t\tN for North, S for South, E for East and W for West. Your spy"
				+ "\n\t\t\tcharacter will then move acordingly on the board in the direction"
				+ "\n\t\t\tyou wanted to move.");
		System.out.print("\n\t\t2. Look");
		System.out.print("\n\t\t\tEntering 2 for Look will then ask you what direction you'd like to"
				+ "\n\t\t\tlook in similarly to how you are asked to move as explained above."
				+ "\n\t\t\tThis will come in handy considering this allows you to use your"
				+ "\n\t\t\tflashlight too see two squares in any one direction you wish!"
				+ "\n\t\t\tLooking before moves is handy to see if any Ninjas or power-ups" + "\n\t\t\tare around!");
		System.out.print("\n\t\t3. Shoot");
		System.out.print("\n\t\t\tEntering 3 for Shoot will ask you what direction as similarly"
				+ "\n\t\t\tdescribed above. When shooting, the bullet keeps going until it"
				+ "\n\t\t\teither hits a wall, a room or a Ninja. If you hit a Ninja, you've"
				+ "\n\t\t\tkilled one of the 6 Ninjas and now have one less Ninja to worry" + "\n\t\t\tabout.");
		System.out.print("\n\t\t4. Save");
		System.out.print("\n\t\t\tEntering 4 to Save will then prompt you to enter a name of which"
				+ "\n\t\t\tyou'd like to save your current game as. Once you enter a name,"
				+ "\n\t\t\tit's your choice of what to do next as you are presented with all"
				+ "\n\t\t\t1-5 options again.");
		System.out.print("\n\t\t5. Quit");
		System.out.print("\n\t\t\tEntering 5 to Quit simply ends the game on that turn.");
	}

	/**
	 * Print Game Loading Help Text
	 */
	private void printLoadGame() {
		System.out.print("\nLoading a Previous Game\n\t"
				+ "When loading a previous game, a list of all your saved games will appear. In order"
				+ "\n\tto select a saved game, simply enter in the corresponding number. Once you've done"
				+ "\n\tso, the game will load from the point at which you have saved it. Your location"
				+ "\n\talong with the location of the briefcase, ninjas and power-ups will be reloaded"
				+ "\n\tand ready to go. You'll be prompted from that point on with your choices available"
				+ "\n\tto you at every move and you may continue that game like you never left it."
				+ "\n\tA sample prompy of that this may look like is:"
				+ "\n\t\tWhich Save file do you wish to load?\r\n" + "\n\t\t	1. - I'm almost winning!\r\n"
				+ "\n\t\t	2. - I'm done for\r\n" + "\n\t\tSave File: ");
	}

	/**
	 * Print choice of Two Text Art Strings, because why not
	 * 
	 * @param first
	 *            if First image printed versus the Second
	 */
	private void printPicture(boolean first) {
		if (first) {
			System.out.println("\n      \u00B6\u00B6\r\n"
					+ "      \u00B6\u00B6              \u00B6\u00b6\u00B6\u00B6\u00B6\r\n"
					+ "      \u00B6\u00B6\u00B6            \u00B6\u00B6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "      \u00B6\u00B6\u00B6            \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00B6\u00B6\u00B6\u00B6           \u00B6\u00B6-1\u00B6\u00B6-1\u00B6\u00B6\r\n"
					+ "   \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6          \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "   \u00b6\u00b6\u00b6\u00b6             \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "    \u00b6\u00b6               \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "    \u00b6\u00b6             \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00b6\u00b6               \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00b6\u00b6          \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00b6\u00b6\u00b6       \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00b6\u00b6\u00b6   \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "      \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6  \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "      \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6    \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6   \u00b6\u00b6\u00b6\u00b6\r\n"
					+ "                  \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6    \u00b6\u00b6\u00b6\u00b6\r\n"
					+ "                   \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6      \u00b6\u00b6\u00b6\u00b6\r\n"
					+ "                    \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6       \u00b6\u00b6\u00b6\r\n"
					+ "                   \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6         \u00b6\u00b6\u00b6\r\n"
					+ "                  \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6         \u00b6\u00b6\u00b6\r\n"
					+ "                 \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6          \u00b6\u00b6\r\n"
					+ "                \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6          \u00b6\u00b6\r\n"
					+ "               \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6           \u00b6\u00b6\r\n"
					+ "              \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6           \u00b6\u00b6\r\n"
					+ "             \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6           \u00b6\u00b6\u00b6\r\n"
					+ "            \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6           \u00b6 \u00b6\u00b6\r\n"
					+ "            \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6           \u00b6\u00b6\r\n"
					+ "           \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6    \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "           \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6      \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "          \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6        \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "          \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6          \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "         \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6            \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "         \u00b6\u00b6\u00b6\u00b6\u00b6              \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "        \u00b6\u00b6\u00b6\u00b6\u00b6                \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "       \u00b6\u00b6\u00b6\u00b6\u00b6                  \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "      \u00b6\u00b6\u00b6\u00b6\u00b6                     \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00b6\u00b6\u00b6\u00b6\u00b6                       \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "     \u00b6\u00b6\u00b6\u00b6\u00b6                        \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "    \u00b6\u00b6\u00b6\u00b6\u00b6                          \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "    \u00b6\u00b6\u00b6\u00b6                            \u00b6\u00b6\u00b6\u00b6\u00b6\u00b6\r\n"
					+ "   \u00b6\u00b6\u00b6\u00b6                               \u00b6\u00b6\u00b6\u00b6\r\n"
					+ "   \u00b6\u00b6\u00b6\u00b6                                \u00b6\u00b6\u00b6\u00b6\r\n"
					+ "   \u00b6\u00b6\u00b6                                  \u00b6\u00b6\u00b6\r\n"
					+ "  \u00b6\u00b6\u00b6\u00b6                                   \u00b6\u00b6\u00b6\r\n"
					+ " \u00b6\u00b6\u00b6 \u00b6                                  \u00b6\u00b6\u00b6\u00b6\r\n"
					+ "\u00b6\u00b6\u00b6\u00b6 \u00b6                                 \u00b6\u00b6\u00b6 \u00b6\r\n"
					+ "                                      \u00b6\u00b6\u00b6\u00b6 \u00b6");
		} else {
			System.out.println("111111¶11111111111111111111111111111111111111 \r\n"
					+ "111111¶11111111111111111111111111111111111111\r\n"
					+ "11111 ¶¶1111111111111111111111111111111111111\r\n"
					+ "1111  ¶¶1111111111111111111111111111111111111\r\n"
					+ "11¶   ¶11111111111111111111111111111111111111\r\n"
					+ "11¶¶ ¶¶11111111111111111111111111111111111111\r\n"
					+ "111¶ ¶¶11111111111111111111111111111111111111\r\n"
					+ "111¶  ¶¶1111111111111111111111111111111111111\r\n"
					+ "11¶¶  ¶¶1111111111111111111111111111111111111\r\n"
					+ "11¶¶  ¶¶1111111111111111111111111111111111111\r\n"
					+ "1¶¶  ¶¶11111111111111111111111111111111111111\r\n"
					+ "¶¶  ¶¶111111111111111111111111111111111111111\r\n"
					+ "¶¶ ¶¶1111111111111111111111111111111111111111\r\n"
					+ "¶¶¶¶11111111111111111111111111111111111111111\r\n"
					+ "1¶¶111111111111111111111111111111111111111111\r\n"
					+ "11¶¶1111¶¶11111111111111111111111111111111111\r\n"
					+ "111¶11¶¶¶¶¶1111111111111111111111111111111111\r\n"
					+ "11111¶¶  ¶¶¶111111111111111111111111111111111\r\n"
					+ "1111¶¶¶    ¶¶¶1111111111111111111111111111111\r\n"
					+ "111¶¶¶¶¶¶    ¶¶¶11111111111111111111111111111\r\n"
					+ "1111¶¶¶¶¶¶¶    ¶¶¶111111111111111111111111111\r\n"
					+ "111111¶¶¶¶¶¶¶¶    ¶¶¶111111111111111111111111\r\n"
					+ "11111111¶¶¶¶¶¶¶¶    ¶¶¶1111111111111111111111\r\n"
					+ "11111111111¶¶¶¶¶¶¶¶    ¶¶¶¶111111111111111111\r\n"
					+ "1111111111111¶¶¶¶¶¶¶¶   ¶¶¶¶11111111111111111\r\n"
					+ "111111111111111¶¶¶¶¶¶¶¶  ¶¶¶¶¶¶11111111111111\r\n"
					+ "111111111111111111¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶111111111111\r\n"
					+ "1111111111111111111¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶111111111\r\n"
					+ "1111111111111111111¶¶ ¶¶¶ ¶¶¶¶¶¶ ¶¶¶¶¶¶111111\r\n"
					+ "1111111111111111111¶ ¶¶¶¶¶¶¶ ¶¶¶ ¶¶ ¶¶¶¶11111\r\n"
					+ "1111111111111111111¶¶¶¶¶¶¶¶¶¶¶ ¶¶ ¶¶ ¶¶¶11111\r\n"
					+ "1111111111111111111¶¶¶¶¶¶¶¶¶¶¶ ¶¶¶¶¶¶¶  ¶¶111\r\n"
					+ "1111111111111111111¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶111\r\n"
					+ "1111111111111111111111¶¶¶¶¶¶¶¶¶¶¶¶¶¶  ¶  ¶¶11\r\n"
					+ "111111111111111111111111¶¶¶¶¶¶¶¶¶¶¶¶¶¶ ¶¶¶¶¶¶\r\n"
					+ "111111111111111111111111¶¶¶¶¶¶¶¶¶   ¶¶¶¶   ¶¶\r\n"
					+ "111111111111111111111111¶¶¶   ¶¶¶     ¶¶¶1111\r\n"
					+ "111111111111111111111111¶¶¶   ¶¶¶¶      ¶¶111\r\n"
					+ "111111111111111111111111¶¶¶  ¶¶¶¶¶  ¶¶  ¶1111\r\n"
					+ "111111111111111111111111¶¶¶¶  ¶  ¶ ¶¶¶¶ ¶¶111\r\n"
					+ "11111111111111111111111111¶¶¶¶  ¶¶ ¶¶¶¶¶ ¶111\r\n"
					+ "1111111111111111111111111111¶¶¶¶¶  ¶¶¶¶¶ ¶¶11\r\n"
					+ "111111111111111111111111111111¶¶¶ ¶¶¶¶¶¶ ¶¶11\r\n"
					+ "111111111111111111111111111111¶¶  ¶¶¶¶¶¶ ¶¶11\r\n"
					+ "111111111111111111111111111111¶¶ ¶¶¶¶¶¶¶ ¶111\r\n"
					+ "11111111111111111111111111111¶¶ ¶¶¶¶¶¶¶¶ ¶111\r\n"
					+ "11111111111111111111111111111¶  ¶¶¶¶¶¶¶ ¶¶111\r\n"
					+ "1111111111111111111111111111¶¶  ¶¶¶¶¶¶  ¶¶111\r\n"
					+ "1111111111111111111111111111¶¶¶    ¶¶¶ ¶¶1111\r\n"
					+ "111111111111111111111111111111¶¶¶¶    ¶¶¶1111\r\n"
					+ "11111111111111111111111111111111¶¶¶¶¶¶¶¶11111");
		}
	}

	/**
	 * Print Game Objectives Help text
	 */
	public void printObject() {
		System.out.printf("\nWhat is the purpose of Spy Game?");
		System.out.printf("\n\t The object of the game is to move your Spy character around the 9 by 9 grid board"
				+ "\n\t safely. Watch out, there are Ninjas about! If you run into a Ninja you will lose"
				+ "\n\t one of your lives! Each game you are given 3 lives. If you are defeated by a"
				+ "\n\t ninja 3 times within a game, all of your lives are lost and you'll be forced to"
				+ "\n\t start over with a new game. Warning, it's not as easy as you think to win... "
				+ "\n\t Someone has turned off the lights! So you can't see where the ninjas are.. "
				+ "\n\t Luckily for you, since you have a flashlight, you may chose to look in one "
				+ "\n\t direction and see two spaces in that direction."
				+ "\n\t To win the game you must move your Spy player aroard safely and check each room,"
				+ "\n\t indicated by the [#] symbol, for the briefcase. Once you've found the correct room"
				+ "\n\t with the briefcase, congratulations! You have won the game."
				+ "\n\t\t *Please note that rooms can only be entered from the top, no other "
				+ "\n\t\t direction will allow you into the room to check for a briefcase.\n\t See the board"
				+ " layout below for reference. You start each new game in the bottom\n\t\t left corner as shown.");
		System.out.print("\n\t\t\t\t[\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][#][\u2022][\u2022][#][\u2022][\u2022][#][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][#][\u2022][\u2022][#][\u2022][\u2022][#][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022]\r"
				+ "\n\t\t\t\t[\u2022][#][\u2022][\u2022][#][\u2022][\u2022][#][\u2022]\r"
				+ "\n\t\t\t\t[P][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022][\u2022]");
	}

	/**
	 * Print Main Menu Help Text
	 */
	private void printStartMenu() {
		System.out.print("\nStart Menu: \n\t The startup menu will present you with 4 options, you can either: "
				+ "\n\t\t 1. Start New Game \n\t\t\t Starting a new game will allow you to begin playing Spy Game. \n"
				+ "\t\t\t Upon starting a new game you will be given the choice if you"
				+ "\n\t\t\t 'would like to enable the ninja AI'. Enter Y for yes and N for"
				+ "\n\t\t\t no. If you are unsure what AI means, This is simply asking if"
				+ "\n\t\t\t you would like to give the game an extra challenge by allowing"
				+ "\n\t\t\t the ninjas to see where you are and move in your direction"
				+ "\n\t\t\t verses moving randomly.");
		System.out.print("\n\t\t 2. Load Game\n\t\t\t Loading a game will allow you to reload a game that you have"
				+ "\n\t\t\t previously saved.");
		System.out.print("\n\t\t 3. Help\n\t\t\t Congratulations, you already know what help does because help"
				+ "\n\t\t\t simply brings up this help screen with all the information needed "
				+ "\n\t\t\t on how to play Spy Game. There is even a hint embedded in the "
				+ "\n\t\t\t pictures below that, when entered after a game is going, will"
				+ "\n\t\t\t allow you to see where everything is located on the board! Can "
				+ "\n\t\t\t you find the secrete code?");
		System.out.print("\n\t\t 4. Quit?\n\t\t\t Choosing this allows you to close the game before you begin. If"
				+ "\n\t\t\t you so wish to crawl into a ball and admit defeat to the nijas"
				+ "\n\t\t\t beforehand, this is your option to quit now.");
		System.out.print(
				"\n\t To choose any option simply enter the corresponding number followed by pressing\n\t the 'enter' key.");
	}

	/**
	 * CUI Only submethod, that waits for User to acknowledge Current Prompt
	 */
	private void pressEnterToContinue() {
		System.out.println("\nPress Enter key to continue...");
		try {
			System.in.read(new byte[System.in.available()]);
			System.in.read();
		} catch (Exception e) {
		}
	}

	/**
	 * CUI only method to simulate clearing the screen.
	 */
	private void clearScreen() {
		for (int i = 0; i < 80; i++) {
			System.out.println();
		}
	}

	@Override
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

	@Override
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
			System.out.print("\n\nThats not a Option! Please enter again either (Y/N)\nResponse:");
			result = scanner.next();
			scanner.nextLine();
		}
		System.out.println();
		clearScreen();

		return result.equalsIgnoreCase("Y");

	}

	@Override
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
				System.out.println("Shot in the wrong direction");
			}
		}

		pressEnterToContinue();
	}

	@Override
	public String querySaveFileName() {
		System.out.println("What would you like to name your Save file to?\nName: ");
		do {
			String result = scanner.nextLine();
			try {
				System.out.println(
						"Saving to: " + Paths.get(GameEngine.getSavePath() + File.separator + result + ".ser"));
				return result;
			} catch (InvalidPathException p) {

				System.out.print("\nThats not a Valid file Name! Please remove invalid characters\nName:");
			}
		} while (true);
	}

	@Override
	public void confirmSaveFile(String path) {
		System.out.println("Saving to: " + path);
		pressEnterToContinue();
		clearScreen();
	}

	@Override
	public void printInvalidMove() {
		System.out.println("You can not move in that direction! Pick another Direction");
	}

	@Override
	public String queryLoadFileName(String[] saves) {
		StringBuilder builder = new StringBuilder("\nWhich Save file do you wish to load?");
		ArrayList<Integer> options = new ArrayList<Integer>();
		for (int x = 0; x < saves.length; x++) {
			builder.append("\n\t" + (x + 1) + ". - " + saves[x].substring(0, saves[x].length() - 4));
			options.add(x + 1);
		}
		builder.append("\nSave File: ");
		do {
			System.out.print(builder.toString());
			try {
				int result = scanner.nextInt();
				scanner.nextLine();
				if (options.contains(result)) {
					return saves[result - 1];
				}
				System.out.println("This is not a Save File");
			} catch (InputMismatchException i) {
				System.out.println("That is not a even a Number!");
				scanner.nextLine();
			}
		} while (true);
	}

	@Override
	public void printDamaged() {
		System.out.println("A Ninja got you! You are forced to retreat to the Starting Point");
		pressEnterToContinue();
		clearScreen();
	}

	@Override
	public boolean offerDifficulty() {
		System.out.print("\nWould you like to enable the ninja AI? (Y/N)\nResponse: ");
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
		clearScreen();

		return result.equalsIgnoreCase("Y");
	}

	@Override
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
	@Override
	public void printMap(String[] map, char lookDirection, boolean debug, boolean radarActive) {
		clearScreen();
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

	@Override
	public void printRoomContents(boolean briefcase) {
		if (briefcase)
			System.out.println("The briefcase is in this room!");
		else
			System.out.println("This room is empty.");
	}

	@Override
	public void printPowerUp(char item) {
		if (item == 'a')
			System.out.println("You've found a bullet! You now have max ammo");
		else if (item == 'i')
			System.out.println("You're invincible for 5 turns! Ninjas can't kill you.");
		else if (item == 'r')
			System.out.println("You've found a radar! You know where the briefcase is.");
	}
}

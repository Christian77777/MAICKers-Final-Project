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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import edu.cpp.cs.cs141.final_prog_assgment.Items.ItemType;

/**
 * This Class Handles all Logic of the Game. And Executes the game actions.
 * Creates the UI as well as the GameBoard, and gives them directions.
 */
public class GameEngine {
	/**
	 * Displays infomation to the User/Get input from user
	 */
	private UserInterface ui;
	/**
	 * Stores all Game Data for Serialization
	 */
	private GameBoard game;
	/**
	 * Tells if a Winner has been Decided {@link #victory} Decides who won
	 */
	private boolean gameOver;
	/**
	 * Tells who won the game. (Player/AI)
	 */
	private boolean victory;
	/**
	 * Decides if Enemy AI is enabled
	 */
	private boolean hardmode;
	/**
	 * Decides if the Map is completely revealed to the user. Only toggleable when
	 * the Player is taking their inital turn
	 */
	private boolean debug = false;

	/**
	 * Initializes Private Variables to default values
	 */
	public GameEngine() {

	}

	/**
	 * Prints Welcome to User, and decides what to do
	 */
	public void newGame() {
		int mainMenuOption = ui.welcomeMessage();
		// TODO
	}

	/**
	 * Starts a new Game
	 */
	public void startGame() {
		game = new GameBoard();
		ui = new UserInterface();
		hardmode = ui.offerDifficulty();
		runGame();
	}

	/**
	 * Runs all the Gameplay
	 */
	public void runGame() {
		do {

		} while (!gameOver);
		if (victory) {
			// win
		} else {
			// lose
		}
	}

	/**
	 * Loads a game, saved in the File System
	 * 
	 * @param fileName
	 *            The name of the binary File to load
	 */
	public void loadGame(String fileName) {
		try {
			FileInputStream in = new FileInputStream("dir" + fileName);
			ObjectInputStream ois = new ObjectInputStream(in);
			game = (GameBoard) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Save the current game, give option for file title in ui
	 * 
	 * @param fileName
	 *            name of the File to save as.
	 */
	public void saveGame(String fileName) {
		try {
			FileOutputStream out = new FileOutputStream("dir" + fileName);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(game);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Quickly displays a help Menu, and reprint the main menu
	 */
	public void help() {

	}

	/**
	 * For all Remaining Alive ninjas, checks surrounding coordinates and if player
	 * exists, stab
	 * 
	 * @return if ninjas stabbed player
	 */
	public boolean attemptNinjaStab() {
		//possible way to see if a ninja stabs a player
		//it would require making a player object and an array of ninja objects of course
		//wanted to see if this was okay or if there's a better way to do it
		int playerX = player.getX();
		int playerY = player.getY();
		for (Ninja n : ninja) {
			ninjaX = n.getX();
			ninjaY = n.getY();
			if (ninjaX==playerX && (ninjaY-playerY==-1 || ninjaY-playerY==1)
				return true;
			else if(ninjaY==playerY && (ninjaX-plyerX==-1 || ninjaY-playerY==1))
				return true;
		}
		return false;
	}

	/**
	 * Call a showMap method with Extra visibility
	 * 
	 * @param direction
	 *            to look in
	 */
	public void lookInDirection(char direction) {

	}

	/**
	 * 1. Verify if Player object can shoot 2. Remove Ammo 3. Check if spaces in the
	 * given direction are shared with ninjas 4. If Ninja is found, remove from
	 * array, and immediately return true 6. Otherwise, return false
	 * 
	 * @param direction
	 *            shot in
	 * @return if a single ninja was removed from game
	 */
	public boolean shootInDirection(char direction) {
		return false;
	}

	/**
	 * VAGUE Verify if a entity is sharing a space with another entity
	 * 
	 * @return if Sharing a Space
	 */
	public boolean isSharingSpace() {
		return false;
	}

	/**
	 * VAGUE Verify if a entity is out of the Map Bounds
	 * 
	 * @return if out of bounds
	 */
	public boolean isOutOfBounds() {
		return false;
	}

	/**
	 * Carry out the action of a Item Suitcase means Game won Radar = Reveals
	 * location of Briefcase Invincibility = Player can not be Stabbed for 5 FULL
	 * turns
	 * 
	 * @param type
	 *            Type of Effect to apply
	 */
	public void giveEffect(ItemType type) {
		if (type == ItemType.BRIEFCASE)// Possibly group Win Condition here
		{

		}
	}

	/**
	 * First Check if any Ninja is adjacent to Player, and then stabs Then move all
	 * Ninjas to a new Possible Location Then increment turnCount {@link #game}
	 */
	public void executeEnemyTurn() {

	}

	/**
	 * 1. Display Turn Options 2. return Result 3. Execute Action
	 */
	public void executePlayerTurn() {
		ui.printMap(game.getMap(), /* TODO if value = a, did not look */'f', debug);
		int choice;
		while ((choice = ui.pickTurn(true, game.getPlayer().hasAmmo())) == -1) {
			debug = !debug;
			// reprint Map
		}
		if (choice == 1) {
			// TODO Look around, and Reprint Map
			ui.printMap(game.getMap(), ui.queryLookingDirection(), debug);
			choice = ui.pickTurn(false, game.getPlayer().hasAmmo());
		}
		if (choice == 2)// Moving around
		{
			playerMove();
			// If Player lands on Item, activate Item
		}
		if (choice == 3)// Shooting around
		{

		} else // Save, No loading allowed unless Main Menu
		{

		}

	}

	/**
	 * For Each Enemy, random move, or calculated move (depending on hardmode).
	 */
	public void enemyMove() {
		if (hardmode) {

		}
	}

	/**
	 * Prompts Player for Requested Move, and if allowable, move the Player
	 */
	public void playerMove() {
		int a;
		int b;
		boolean enterable;
		char choice;
		do {
			enterable = false;
			choice = ui.queryMovingDirection();
			a = game.getPlayer().newX(choice);
			b = game.getPlayer().newY(choice);
			if (game.getPlayer().isSpecialRoom(a, b)) {
				if (choice == 's') {
					enterable = true;
				}
			} else {
				enterable = true;
			}
		} while (game.getPlayer().isStillInBounds(a, b) && enterable);
		game.getPlayer().move(choice);
	}

	public boolean isSpecialRoom(int a, int b) {
		boolean room1 = a == 1 && b == 1;
		boolean room2 = a == 4 && b == 1;
		boolean room3 = a == 7 && b == 1;
		boolean room4 = a == 1 && b == 4;
		boolean room5 = a == 4 && b == 4;
		boolean room6 = a == 1 && b == 4;
		boolean room7 = a == 7 && b == 7;
		boolean room8 = a == 4 && b == 7;
		boolean room9 = a == 7 && b == 7;
		if (room1 || room2 || room3 || room4 || room5 || room6 || room7 || room8 || room9) {
			return true;
		} else {
			return false;
		}
	}
}

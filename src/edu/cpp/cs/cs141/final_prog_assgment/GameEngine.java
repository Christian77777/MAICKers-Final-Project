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
import java.util.Random;

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
	private Random rand = new Random;
	
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
		boolean stab = false;
		for (int i=0; i<81; i++) {
			if (game.checkFlag(i, 3, '1')) {
				if (game.checkFlag(i-1, 1, '1'))
					stab = true;
				else if (game.checkFlag(i+1, 1, '1'))
					stab = true;
				else if (game.checkFlag(i-9, 1, '1'))
					stab = true;
				else if (game.checkFlag(i+9, 1, '1'))
					stab = true;
			}
		}
		return stab;
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
		boolean hit = false;
		//TODO
		if (!ammo) {
			//would be helpful to check if they have ammo before giving them the option
			//to shoot
			ui.printOutOfAmmo();
			//something to make sure the user's new input is executed
			return false;
		}
		//something to remove ammo
		for (int i=0; i<81; i++) {
			if (game.checkFlag(i, 1, '1')) {
				switch (direction) {
				case 'n':
					for (int j=i-9; j>0; j-=9) {
						if (game.checkFlag(j, 3, '1')) {
							hit = true;
							game.setFlag(j, 3, '0');
							break;
						}
					}
				case 's':
					for (int j=i+9; j<81; j+=9)
						if (game.checkFlag(j, 3, '1')) {
							hit = true;
							game.setFlag(j, 3, '0');
							break;
						}
				case 'e':
					for (int j=i+1; (j%9)!=0; j++) {
						if (game.checkFlag(j, 3, '1')) {
							hit = true;
							game.setFlag(j, 3, '0');
							break;
						}
					}
				case 'w':
					for (int j=i-1; (j+1)%9!=0; j--) {
						if (game.checkFlag(j, 3, '1')) {
							hit = true;
							game.setFlag(j, 3, '0');
							break;
						}
					}
				}
			}
		}
		return hit;
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
		boolean stab = attemptNinjaStab();
		//need something to tell the player they were stabbed,
		//move them back to start space, and 
		//decrease their life total. (check if even still alive)
		if (hardmode)
			randomEnemyMove();
		else
			smartEnemyMove();
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
	 * For Each Enemy, random move.
	 */
	public void randomEnemyMove() {
		int numberOfNinjas=0;
		for (int i=0; i<81; i++) {
			if(game.checkFlag(i, 3, '1'))
				numberOfNinjas++;
		}
		int[] ninjaLoc = new int[numberOfNinjas];
		int[] newNinjaLoc = new int[numberOfNinjas];
		int j = 0;
		boolean validMove;
		for (int i=0; i<81; i++) {
			if (game.checkFlag(i, 3, '1')) {
				ninjaLoc[j]=i;
				j++;
			}
		}
		for (int i=0; i<ninjaLoc.length; i++) {
			do {
				validMove = true;
				int moveDirection = rand.nextInt(4);
				switch (moveDirection) {
				case 0:
					newNinjaLoc[i] = ninjaLoc[i]-9;
					break;
				case 1:
					newNinjaLoc[i] = ninjaLoc[i]+9;
					break;
				case 2:
					newNinjaLoc[i] = ninjaLoc[i]+1;
					break;
				case 3:
					newNinjaLoc[i] = ninjaLoc[i]-1;
					break;
				}
				if(newNinjaLoc[i]<0 || newNinjaLoc[i]>81 || game.isRoom(newNinjaLoc[i]))
					validMove = false;
				else if (true) {
					for (int n=i-1; n>=0; n--) {
						if (newNinjaLoc[i]==newNinjaLoc[n])
							validMove=false;
					}
				}
			}while(!validMove);
		}
		for (int i=0; i<ninjaLoc.length; i++) {
			game.setFlag(ninjaLoc[i], 3, '0');
			game.setFlag(newNinjaLoc[i], 3, '1');
		}
	}
	
	/**
	 * Moves the ninja towards the player if the player happens to be in the same
	 * row or column as them. 
	 */
	public void smartEnemyMove() {
		for (int i=0; i<81; i++) {
			boolean enemyMoved=false;
			//currently have it so that if ninja's would be stepping into a room
			//to follow the player, they would instead move randomly
			if (game.checkFlag(i, 3, '1')) {
				for (int j=i-9; j>0; j-=9) {
					if (game.checkFlag(j, 1, '1') && !game.isRoom(i=9)) {
						game.setFlag(i, 3, '0');
						game.setFlag(i-9, 3, '1');
						enemyMoved=true;
					}
				}
				if (!enemyMoved) {
					for (int j=i+9; j<81; j+=9) {
						if (game.checkFlag(j, 1, '1') && !game.isRoom(i+9)) {
							game.setFlag(i, 3, '0');
							game.setFlag(i+9, 3, '1');
							enemyMoved = true;
						}
					}
				}
				if (!enemyMoved) {
					for (int j=i+1; (j%9)!=0; j++) {
						if (game.checkFlag(j, 1, '1') && !game.isRoom(i+1)) {
							game.setFlag(i, 3, '0');
							game.setFlag(i+1, 3, '1');
							enemyMoved = true;
						}
					}
				}
				if (!enemyMoved) {
					for (int j=i-1; (j+1)%9!=0; j--) {
						if (game.checkFlag(j, 1, '1') && !game.isRoom(i-1)) {
							game.setFlag(i, 3, '0');
							game.setFlag(i, 3, '1');
							enemyMoved = true;
						}
					}
				}
				if(!enemyMoved) {
					//this would move all the enemies randomly. need to just move one
					randomEnemyMove();
				}
			}
		}
	}

	/**
	 * Prompts Player for Requested Move, and if allowable, move the Player
	 */
	public void playerMove() {
		char direction;
		boolean validMove=false;
		int playerLoc=0;
		for (int i=0; i<81; i++) {
			if (game.checkFlag(i, 1, '1')) {
				playerLoc = i;
				break;
			}
		}
		do{
			direction = ui.queryMovingDirection();
			int newPlayerLoc;
			switch (direction){
			case 'n':
				newPlayerLoc = playerLoc-9;	
			case 's':
				newPlayerLoc = playerLoc+9;
			case 'e':
				newPlayerLoc = playerLoc+1;
			case 'w':
				newPlayerLoc = playerLoc-1;
			}
			if (newPlayerLoc<0 || newPlayerLoc>81)
				ui.printInvalidMove();
			else if (game.isRoom(newPlayerLoc) && direction!='s')
				ui.printInvalidMove();
			else {
				validMove = true;
				game.setFlag(playerLoc, 1, '0');
				game.setFlag(newPlayerLoc, 1, '1');
			}
		}while (!validMove);
	}
}

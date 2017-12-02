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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * This Class Handles all Logic of the Game. And Executes the game actions.
 * Creates the UI as well as the GameBoard, and gives them directions.
 */
public class GameEngine {
	/**
	 * Displays information to the User/Get input from user
	 */
	private UserInterface ui;
	/**
	 * Stores all Game Data for Serialization
	 */
	private GameBoard game;

	/**
	 * Allows us to set the player's life total and ammo.
	 */
	private Player player;

	/**
	 * Keeps track of how many ninjas are on the board (Should be between 4 and 6)
	 */
	private int numberOfNinjas;

	/**
	 * Keeps track of turn count (invincibility lasts five turns)
	 */
	private int turnCount;

	/**
	 * If a player is invincible they can't be stabbed by ninjas.
	 */
	private boolean invincibility;

	/**
	 * If true, the user can see the location of the briefcase.
	 */
	private boolean radar;

	/**
	 * Stores location of Player inside of single array Matrix in GameBoard
	 */
	private int playerLoc;

	/**
	 * Tells if a Winner has been Decided {@link #victory} Decides who won
	 */
	private boolean gameOver;
	/**
	 * Tells who won the game. (Player/AI)
	 */
	private boolean victory;
	/**
	 * If the player saved this "turn"
	 */
	private boolean saved;
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
	private Random rand = new Random();

	/**
	 * Holds Array of ALL Tokens
	 */
	private String[] board;

	/**
	 * Path to Save Directory, System Independent by use of getProperty()
	 */
	private static final String savePath = new String(
			System.getProperty("user.home") + File.separator + "Documents" + File.separator + "MaickerGames");

	public GameEngine(boolean gui) {
		if (gui) {
			ui = new GraphicalUI();
		} else {
			ui = new ConsoleUI();
		}
	}

	/**
	 * Prints Welcome to User, and decides what to do
	 */
	public void startGame() {
		ui.welcomeMessage();
		int mainMenuOption = ui.printMainMenu();
		if (mainMenuOption == 1)
			newGame();
		else if (mainMenuOption == 2) {
			String x = ui.queryLoadFileName(new File(savePath).list());
			if (x != null) {
				loadGame(x);
			} else {
				newGame();
			}
		} else if (mainMenuOption == 3)
			help();
		else if (mainMenuOption == 4)
			System.exit(0);
	}

	/**
	 * Starts a new Game
	 */
	public void newGame() {
		game = new GameBoard();
		player = new Player();
		numberOfNinjas = 6;
		turnCount = 0;
		invincibility = false;
		victory = false;
		gameOver = false;
		radar = false;
		playerLoc = game.getPlayerLoc();
		getMapData();
		hardmode = ui.offerDifficulty();
		runGame();
	}

	/**
	 * Runs all the Gameplay
	 */
	public void runGame() {
		boolean playAgain;
		do {
			executePlayerTurn();
			if (!victory && !saved)
				executeEnemyTurn();
		} while (!gameOver);
		if (saved) {
			saved = false;
		}
		if (victory) {
			playAgain = ui.printGameOver(true);
		} else {
			playAgain = ui.printGameOver(false);
		}
		if (playAgain) {
			newGame();
		} else {
			System.exit(0);
		}

	}

	/**
	 * Loads a game, saved in the File System
	 * 
	 * @param fileName
	 *            The name of the binary File to load
	 */
	public void loadGame(String fileName) {
		new File(savePath).mkdirs();
		try (FileInputStream in = new FileInputStream(savePath + File.separator + fileName);
				ObjectInputStream ois = new ObjectInputStream(in);) {
			game = (GameBoard) ois.readObject();
			player = (Player) ois.readObject();
			numberOfNinjas = (int) ois.readInt();
			turnCount = (int) ois.readInt();
			invincibility = (boolean) ois.readBoolean();
			radar = (boolean) ois.readBoolean();
			playerLoc = (int) ois.readInt();
			hardmode = (boolean) ois.readBoolean();
			board = (String[]) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Oops, failed to Load File!");
			e.printStackTrace();
		} finally {
			runGame();
		}
	}

	/**
	 * Save the current game, give option for file title in ui
	 * 
	 * @param fileName
	 *            name of the File to save as.
	 */
	public void saveGame(String fileName) {
		new File(savePath).mkdirs();
		String path = (savePath + File.separator + fileName + ".ser");
		ui.confirmSaveFile(path);
		try (FileOutputStream out = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(out);) {
			oos.writeObject(game);
			oos.writeObject(player);
			oos.writeInt(numberOfNinjas);
			oos.writeInt(turnCount);
			oos.writeBoolean(invincibility);
			oos.writeBoolean(radar);
			oos.writeInt(playerLoc);
			oos.writeBoolean(hardmode);
			oos.writeObject(board);
			out.close();
		} catch (IOException e) {
			System.out.println("Oops, failed to Save File!");
			e.printStackTrace();
		}
		saved = true;
	}

	/**
	 * Quickly displays a help Menu, and reprint the main menu
	 */
	public void help() {
		ui.printHelp();
		startGame();
	}

	/**
	 * 1. Display Turn Options 2. return Result 3. Execute Action
	 */
	public void executePlayerTurn() {
		ui.printMap(board, 'f', debug, radar);
		int choice;
		choice = ui.pickTurn(true, player.hasAmmo());
		while (choice == -1) {
			debug = !debug;
			ui.printMap(board, 'f', debug, radar);
			choice = ui.pickTurn(true, player.hasAmmo());
		}
		// Look
		if (choice == 2) {
			// TODO Look around, and Reprint Map
			ui.printMap(board, ui.queryDirection("Look"), debug, radar);
			choice = ui.pickTurn(false, player.hasAmmo());
		}
		while (choice == -1) {
			debug = !debug;
			ui.printMap(board, 'f', debug, radar);
			choice = ui.pickTurn(true, player.hasAmmo());
		}
		// Move
		if (choice == 1) {
			playerMove();
			boolean item = checkForItems();
			if (item)
				giveEffect();
		}
		// Shoot (Already Checks if Player Has Ammo)
		else if (choice == 3) {
			boolean hit = shootInDirection(ui.queryDirection("Shoot"));
			ui.printShotResult(hit);
			if (hit)
				numberOfNinjas--;
		}
		// Save, No Loading allowed unless in Main Menu
		else if (choice == 4) {
			saveGame(ui.querySaveFileName());
		} else if (choice == 5) {
			System.exit(0);
		}

	}

	/**
	 * First Check if any Ninja is adjacent to Player, and then stabs Then move all
	 * Ninjas to a new Possible Location Then increment turnCount {@link #game}
	 */
	public void executeEnemyTurn() {
		boolean stab;
		if (invincibility && turnCount < 5) {
			turnCount++;
			stab = false;
		} else {
			stab = attemptNinjaStab();
		}
		if (stab) {
			ui.printDamaged();
			player.loseLife();
			game.setFlag(playerLoc, 1, '0');
			playerLoc = 72;
			game.setFlag(playerLoc, 1, '1');
			// System.out.println(game.checkFlag(playerLoc, 3, '1'));//TEST
			if (player.getLife() <= 0) {
				gameOver = true;
				victory = false;
			}
		}
		if (hardmode) {
			smartEnemyMove();
		} else {
			randomEnemyMove();
		}
		if (stab) {
			if (game.checkFlag(playerLoc, 3, '1')) {
				game.setFlag(playerLoc, 3, '0');
				game.placeNinja();
			}
		}
	}

	/**
	 * For all Remaining Alive ninjas, checks surrounding coordinates and if player
	 * exists, stab
	 * 
	 * @return if ninjas stabbed player
	 */
	public boolean attemptNinjaStab() {
		boolean stab = false;
		int[] ninjaLoc = game.getNinjaLoc(numberOfNinjas);
		for (int i = 0; i < ninjaLoc.length; i++) {
			if (ninjaLoc[i] - 9 == playerLoc)
				stab = true;
			else if (ninjaLoc[i] + 9 == playerLoc)
				stab = true;
			else if (ninjaLoc[i] + 1 == playerLoc && playerLoc % 9 != 0)
				stab = true;
			else if (ninjaLoc[i] - 1 == playerLoc && i % 9 != 0)
				stab = true;
			else if (ninjaLoc[i] == playerLoc)
				stab = true;
		}
		return stab;
	}

	/**
	 * 1. Verify if Player object can shoot 2. Remove Ammo 3. Check if spaces in the
	 * given direction are shared with ninjas or are blocked by rooms. 4. If Ninja
	 * is found, remove from array, and immediately return true 6. Otherwise, return
	 * false
	 * 
	 * @param direction
	 *            shot in
	 * @return if a single ninja was removed from game
	 */
	public boolean shootInDirection(char direction) {
		boolean hit = false;
		player.setAmmo(false);
		switch (direction) {
		case 'n':
			for (int i = playerLoc - 9; i >= 0; i -= 9) {
				if (game.isRoom(i))
					break;
				else if (game.checkFlag(i, 3, '1')) {
					hit = true;
					game.setFlag(i, 3, '0');
					break;
				}
			}
			break;
		case 's':
			for (int i = playerLoc + 9; i < 81; i += 9)
				if (game.isRoom(i))
					break;
				else if (game.checkFlag(i, 3, '1')) {
					hit = true;
					game.setFlag(i, 3, '0');
					break;
				}
			break;
		case 'e':
			for (int i = playerLoc + 1; (i % 9) != 0; i++) {
				if (game.isRoom(i))
					break;
				else if (game.checkFlag(i, 3, '1')) {
					hit = true;
					game.setFlag(i, 3, '0');
					break;
				}
			}
			break;
		case 'w':
			for (int i = playerLoc - 1; (i + 1) % 9 != 0; i--) {
				if (game.isRoom(i))
					break;
				else if (game.checkFlag(i, 3, '1')) {
					hit = true;
					game.setFlag(i, 3, '0');
					break;
				}
			}
			break;
		}
		return hit;
	}

	/**
	 * Carry out the action of a Item Suitcase means Game won Radar = Reveals
	 * location of Briefcase Invincibility = Player can not be Stabbed for 5 FULL
	 * turns
	 * 
	 */
	public void giveEffect() {
		if (game.checkFlag(playerLoc, 2, 'a')) {
			player.setAmmo(true);
			ui.printPowerUp('a');
		} else if (game.checkFlag(playerLoc, 2, 'i')) {
			invincibility = true;
			ui.printPowerUp('i');
		} else if (game.checkFlag(playerLoc, 2, 'r')) {
			radar = true;
			ui.printPowerUp('r');
		}
		game.setFlag(playerLoc, 2, '0');
	}

	/**
	 * @return if there is a item where the player is
	 */
	public boolean checkForItems() {
		boolean item = false;
		if (!game.checkFlag(playerLoc, 2, '0'))
			item = true;
		return item;
	}

	/**
	 * For Each Enemy, random move.
	 */
	public void randomEnemyMove() {
		int[] ninjaLoc = game.getNinjaLoc(numberOfNinjas);
		int[] newNinjaLoc = new int[numberOfNinjas];
		boolean validMove;
		for (int i = 0; i < ninjaLoc.length; i++) {
			do {
				validMove = true;
				int moveDirection = rand.nextInt(4);
				switch (moveDirection) {
				case 0:
					newNinjaLoc[i] = ninjaLoc[i] - 9;
					break;
				case 1:
					newNinjaLoc[i] = ninjaLoc[i] + 9;
					break;
				case 2:
					newNinjaLoc[i] = ninjaLoc[i] + 1;
					break;
				case 3:
					newNinjaLoc[i] = ninjaLoc[i] - 1;
					break;
				}
				if (newNinjaLoc[i] < 0 || newNinjaLoc[i] > 80 || game.isRoom(newNinjaLoc[i]))
					validMove = false;
				else if (newNinjaLoc[i] % 9 == 0 && moveDirection == 2)
					validMove = false;
				else if ((newNinjaLoc[i] + 1) % 9 == 0 && moveDirection == 3)
					validMove = false;
				else {
					if (i > 0) {
						for (int n = i - 1; n >= 0; n--) {
							if (newNinjaLoc[i] == newNinjaLoc[n]) {
								validMove = false;
								break;
							}
						}
					}
				}
			} while (!validMove);
		}
		for (int i = 0; i < ninjaLoc.length; i++) {
			game.setFlag(ninjaLoc[i], 3, '0');
		}
		for (int i = 0; i < ninjaLoc.length; i++) {
			game.setFlag(newNinjaLoc[i], 3, '1');
		}
	}

	/**
	 * Moves the ninja towards the player if the player happens to be in the same
	 * row or column as them AND they isn't a room in between them.
	 */
	public void smartEnemyMove() {
		int[] ninjaLoc = game.getNinjaLoc(numberOfNinjas);
		int[] newNinjaLoc = new int[numberOfNinjas];
		for (int i = 0; i < ninjaLoc.length; i++) {
			boolean enemyMoved = false;
			for (int j = ninjaLoc[i] - 9; j >= 0; j -= 9) {
				if (game.isRoom(j)) {
					break;
				}
				if (j == playerLoc) {
					newNinjaLoc[i] = ninjaLoc[i] - 9;
					enemyMoved = true;
				}
			}
			if (!enemyMoved) {
				for (int j = ninjaLoc[i] + 9; j < 81; j += 9) {
					if (game.isRoom(j))
						break;
					if (j == playerLoc) {
						newNinjaLoc[i] = ninjaLoc[i] + 9;
						enemyMoved = true;
					}
				}
			}
			if (!enemyMoved) {
				for (int j = ninjaLoc[i] + 1; j % 9 != 0; j++) {
					if (game.isRoom(j))
						break;
					if (j == playerLoc) {
						newNinjaLoc[i] = ninjaLoc[i] + 1;
						enemyMoved = true;
					}
				}
			}
			if (!enemyMoved) {
				for (int j = ninjaLoc[i] - 1; (j + 1) % 9 != 0; j--) {
					if (game.isRoom(j))
						break;
					if (j == playerLoc) {
						newNinjaLoc[i] = ninjaLoc[i] - 1;
						enemyMoved = true;
					}
				}
			}
			if (enemyMoved) {
				for (int n = i - 1; n >= 0; n--) {
					if (newNinjaLoc[i] == newNinjaLoc[n]) {
						enemyMoved = false;
						break;
					}
				}
			}
			if (!enemyMoved) {
				boolean validMove;
				do {
					validMove = true;
					newNinjaLoc[i] = singleRandomEnemyMove(ninjaLoc[i]);
					for (int n = i - 1; n >= 0; n--) {
						if (newNinjaLoc[i] == newNinjaLoc[n]) {
							validMove = false;
							break;
						}
					}
				} while (!validMove);
			}
		}
		for (int i = 0; i < ninjaLoc.length; i++) {
			game.setFlag(ninjaLoc[i], 3, '0');
		}
		for (int i = 0; i < ninjaLoc.length; i++) {
			game.setFlag(newNinjaLoc[i], 3, '1');
		}
	}

	/**
	 * Moves only one ninja randomly. Used for the AI when the ninja can't see the
	 * player
	 * 
	 * @param location
	 *            location of ninja to move
	 * @return the new location
	 */
	public int singleRandomEnemyMove(int location) {
		int ninjaLoc = location;
		int newNinjaLoc = 0;
		boolean validMove;
		do {
			int direction = rand.nextInt(4);
			switch (direction) {
			case 0:
				newNinjaLoc = ninjaLoc - 9;
				break;
			case 1:
				newNinjaLoc = ninjaLoc + 9;
				break;
			case 2:
				newNinjaLoc = ninjaLoc + 1;
				break;
			case 3:
				newNinjaLoc = ninjaLoc - 1;
				break;
			}
			if (newNinjaLoc < 0 || newNinjaLoc > 80 || game.isRoom(newNinjaLoc))
				validMove = false;
			else if (newNinjaLoc % 9 == 0 && direction == 2)
				validMove = false;
			else if ((newNinjaLoc + 1) % 9 == 0 && direction == 3)
				validMove = false;
			// else if (game.checkFlag(newNinjaLoc, 3, '1'))
			// validMove = false;
			else
				validMove = true;
		} while (!validMove);
		return newNinjaLoc;
	}

	/**
	 * Prompts Player for Requested Move, and if allowable, move the Player
	 */
	public void playerMove() {
		char direction;
		boolean validMove = false;
		do {
			direction = ui.queryDirection("move");
			int newPlayerLoc = -1;
			switch (direction) {
			case 'n':
				newPlayerLoc = playerLoc - 9;
				break;
			case 's':
				newPlayerLoc = playerLoc + 9;
				break;
			case 'e':
				newPlayerLoc = playerLoc + 1;
				break;
			case 'w':
				newPlayerLoc = playerLoc - 1;
				break;
			}

			if (newPlayerLoc < 0 || newPlayerLoc > 80)
				ui.printInvalidMove();
			else if (playerLoc % 9 == 0 && direction == 'w') {
				ui.printInvalidMove();
			} else if (newPlayerLoc % 9 == 0 && direction == 'e') {
				ui.printInvalidMove();
			} else if (game.isRoom(newPlayerLoc) && direction != 's')
				ui.printInvalidMove();
			else if (game.isRoom(newPlayerLoc) && direction == 's') {
				checkRoom();
				validMove = true;
			} else {
				validMove = true;
				game.setFlag(playerLoc, 1, '0');
				game.setFlag(newPlayerLoc, 1, '1');
			}
		} while (!validMove);
		playerLoc = game.getPlayerLoc();
	}

	/**
	 * Checks if the Player has found the Briefcase in the Room
	 */
	public void checkRoom() {
		if (game.checkFlag(playerLoc + 9, 2, 'b')) {
			gameOver = true;
			victory = true;
			ui.printRoomContents(true);
		} else {
			ui.printRoomContents(false);
		}
	}

	/**
	 * Gets Map Data from the GameBoard
	 */
	public void getMapData() {
		board = game.getMapData();
	}

	/**
	 * @return the Save Directory Path
	 */
	public static String getSavePath() {
		return savePath;
	}
}

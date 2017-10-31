/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import edu.cpp.cs.cs141.final_prog_assgment.Items.ItemType;

/**
 * @author kiana
 *
 */
public class GameEngine
{
	/**
	 * Displays infomation to the User/Get input from user
	 */
	private UserInterface ui;
	private GameBoard game;

	/**
	 * Creates board
	 */
	public GameEngine()
	{
		
	}

	public void newGame()
	{
		int mainMenuOption = ui.welcomeMessage();

	}

	/**
	 * Starts a new Game
	 */
	public void startGame()
	{
		game = new GameBoard();
		ui = new UserInterface();
	}

	/**
	 * Loads a game, saved in the File System
	 */
	public void loadGame(String fileName)
	{
		try
		{
			FileInputStream in = new FileInputStream("dir" + fileName);
			ObjectInputStream ois = new ObjectInputStream(in);
			game = (GameBoard)ois.readObject();
			ois.close();
		}
		catch (IOException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Save the current game, give option for file title in ui
	 */
	public void saveGame(String fileName)
	{
		try
		{
			FileOutputStream out = new FileOutputStream("dir" + fileName);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(game);
			out.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Quickly displays a help Menu, and reprint the main menu
	 */
	public void help()
	{

	}

	/**
	 * For all Remaining Alive ninjas, checks surrounding coordinates and if
	 * player exists, stab
	 * 
	 * @return if ninjas stabbed player
	 */
	public boolean attemptNinjaStab()
	{
		return false;
	}

	/**
	 * Call a showMap method with Extra visibility
	 * 
	 * @param direction
	 *            to look in
	 */
	public void lookInDirection(char direction)
	{
		
	}

	/**
	 * 1. Verify if Player object can shoot 2. Remove Ammo 3. Check if spaces in
	 * the given direction are shared with ninjas 4. If Ninja is found, remove
	 * from array, and immediately return true 6. Otherwise, return false
	 * 
	 * @param direction
	 *            shot in
	 * @return if a single ninja was removed from game
	 */
	public boolean shootInDirection(char direction)
	{
		return false;
	}

	/**VAGUE
	 * Verify if a entity is sharing a space with another entity
	 * 
	 * @return if Sharing a Space
	 */
	public boolean isSharingSpace()
	{
		return false;
	}

	/**VAGUE
	 * Verify if a entity is out of the Map Bounds
	 * @return if out of bounds
	 */
	public boolean isOutOfBounds()
	{
		return false;
	}
	
	/**
	 * Carry out the action of a Item
	 * Suitcase means Game won
	 * Radar = Reveals location of Briefcase
	 * Invincibility = Player can not be Stabbed for 5 FULL turns
	 * 
	 * @param type
	 */
	public void giveEffect(ItemType type)
	{
		if (type == ItemType.BRIEFCASE)// Possibly group Win Condition here
		{

		}
	}

	public boolean isSpecialRoom(int a, int b)
	{
		boolean room1 = a == 1 && b == 1;
		boolean room2 = a == 4 && b == 1;
		boolean room3 = a == 7 && b == 1;
		boolean room4 = a == 1 && b == 4;
		boolean room5 = a == 4 && b == 4;
		boolean room6 = a == 1 && b == 4;
		boolean room7 = a == 7 && b == 7;
		boolean room8 = a == 4 && b == 7;
		boolean room9 = a == 7 && b == 7;
		if (room1 || room2 || room3 || room4 || room5 || room6 || room7 || room8 || room9)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

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
import java.util.ArrayList;
import edu.cpp.cs.cs141.final_prog_assgment.Items.ItemType;

/**
 * This Class contains all the Data for a Save Game. Creating this Object also creates all necessary Entities,
 *  and provides a Matrix Representation of the Map.
 *  Also Keeps Track of the Turn Count of the active Game
 */
public class GameBoard implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1601258203598047430L;
	/**
	 * Player Token
	 */
	private Player player = new Player();
	/**
	 * List of Enemies, Only the ones left alive
	 */
	private ArrayList<Ninja> enemies = new ArrayList<Ninja>();
	/**
	 * List of Items, still not grabbed by Player
	 */
	private ArrayList<Items> items = new ArrayList<Items>();
	private int turnCount = 0;

	/**
	 * Puts Enemy in possible spaces
	 * Places Items in possible spaces
	 */
	public GameBoard()
	{
		Ninja enemy;
		for (int x = 0; x < 6; x++)
		{
			do
			{
				enemy = new Ninja();
			}
			while (!enemy.isSpawnableHere() && isSharingHere(enemy.getX(), enemy.getY()));
			enemies.add(enemy);
		}

		Items temp;
		do
		{
			temp = new Items(ItemType.BRIEFCASE);
		}
		while (!temp.isSpawnableHere() && isSharingHere(temp.getX(), temp.getY()));
		items.add(temp);
		do
		{
			temp = new Items(ItemType.BULLET);
		}
		while (!temp.isSpawnableHere() && isSharingHere(temp.getX(), temp.getY()));
		items.add(temp);
		do
		{
			temp = new Items(ItemType.INVINC);
		}
		while (!temp.isSpawnableHere() && isSharingHere(temp.getX(), temp.getY()));
		items.add(temp);
		do
		{
			temp = new Items(ItemType.RADAR);
		}
		while (!temp.isSpawnableHere() && isSharingHere(temp.getX(), temp.getY()));
		items.add(temp);
	}
	
	/**
	 * Create a Matrix Representation of the Map to display
	 * For every Entity stored, get the x and y position, and match it to a slot in the Matrix
	 * @return a Matrix form of the Map
	 */
	public Entities[][] getMap()
	{
		Entities[][] map = new Entities[9][9];
		int x = player.getX();
		int y = player.getY();
		map[x][y] = player;
		return null;
	}

	/**
	 * Does any other entity share a space here (TBD what entities need to be concidered)
	 * @param x the horizontal value
	 * @param y the vertical value
	 * @return if Sharing a Spot
	 */
	public boolean isSharingHere(int x, int y)
	{
		return false;
	}

	/**
	 * @return the Turn Count
	 */
	public int getTurnCount()
	{
		return turnCount;
	}
	
	/**
	 * Increase Turn Count by one
	 * @return the new number of Turns
	 */
	public int incrementTurns()
	{
		turnCount++;
		return turnCount;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * @return the enemies
	 */
	public ArrayList<Ninja> getEnemies()
	{
		return enemies;
	}

	/**
	 * @return the items
	 */
	public ArrayList<Items> getItems()
	{
		return items;
	}
}

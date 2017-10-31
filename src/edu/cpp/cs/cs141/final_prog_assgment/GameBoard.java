package edu.cpp.cs.cs141.final_prog_assgment;

import java.io.Serializable;
import java.util.ArrayList;
import edu.cpp.cs.cs141.final_prog_assgment.Items.ItemType;

public class GameBoard implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1601258203598047430L;
	private Player player;
	private ArrayList<Ninja> enemies = new ArrayList<Ninja>();
	private ArrayList<Items> items = new ArrayList<Items>();
	private int turnCount;

	public GameBoard()
	{
		player = new Player();
		for (int x = 0; x < 6; x++)
		{
			enemies.add(new Ninja());
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
	 * TODO
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isSharingHere(int x, int y)
	{
		return false;
	}

	/**
	 * Returns if coordinates are a special room
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
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

	public int getTurnCount()
	{
		return turnCount;
	}
	
	public int incrementTurns()
	{
		turnCount++;
		return turnCount;
	}
}

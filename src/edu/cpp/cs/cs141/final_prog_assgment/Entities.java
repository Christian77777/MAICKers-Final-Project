/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

import java.io.Serializable;

/**
 * @author kiana
 * 
 */
public abstract class Entities implements Serializable
{

	/**
	 * Horizontal Position of this Entity
	 */
	private int x;
	/**
	 * Vertical Position of this Entity
	 */
	private int y;

	/**
	 * Set up position of this Ninja, and Item
	 */
	public Entities()
	{
		x = (int) (Math.random() * 9.0);
		y = (int) (Math.random() * 9.0);
	}

	/**
	 * Set up position of this Player
	 * @param x The Horizonal Position, restricted between 0-8
	 * @param y	The Vertical Postion, restricted between 0-8
	 */
	public Entities(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Verify that the specific Entity created, is allowed to spawn in the spot it exists in
	 * It is the Game Boards job to check if it is sharing a Space
	 * Verify the already given Values
	 * @return if the spot is allowed
	 */
	public abstract boolean isSpawnableHere();

	/**
	 * Change the current Position of this Entity, can go out of bounds
	 * 
	 * @param direction
	 *            which direction to move one space into
	 */
	public void move(char direction)
	{
		switch (direction)
		{
		case 'n':
			y++;
			break;
		case 's':
			y--;
			break;
		case 'w':
			x--;
			break;
		case 'e':
			x++;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the horizontal Position, should be between 0-8
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return the vertical Position, should be between 0-8
	 */
	public int getY()
	{
		return y;
	}
}

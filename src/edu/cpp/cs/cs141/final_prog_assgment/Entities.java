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

/**
 * This Super Class represents a Token on the Game Board, that has position, rules on Spawn and Move locations.
 */
public abstract class Entities implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -676799148847028235L;
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
	 * Returns if coordinates are a special room
	 * 
	 * @param a the Horizontal Coordinate
	 * @param b the Vertical Coordinate
	 * @return if the coordinates corrispond to a special room
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
	
	/**
	 * Check if this new proposed Spot is In Bounds of the board
	 * @param a the horizontal position
	 * @param b the vertical position
	 * @return if Tn Bounds
	 */
	public boolean isStillInBounds(int a, int b)
	{
		if (a < 0 || a > 8 || b < 0 || b > 8)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Change the current Position of this Entity, will not restrict entity to go out of bounds
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
	 * Get proposed new X location
	 * @param direction Which Axis will move
	 * @return the new X coordinate
	 */
	public int newX(char direction)
	{
		if(direction == 'w')
		{
			return (x - 1);
		}
		else if(direction == 'e')
		{
			return (x + 1);
		}
		else
		{
			return x;
		}
	}
	
	/**
	 * Get proposed new Y Location
	 * @param direction Which Axis will move
	 * @return the new Y coordinate
	 */
	public int newY(char direction)
	{
		if(direction == 'n')
		{
			return (y + 1);
		}
		else if(direction == 's')
		{
			return (y - 1);
		}
		else
		{
			return y;
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

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
 * This Class Represents Powerups or the Briefcase in the GameBoard
 */
public class Items extends Entities
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4403682892293323135L;

	/**
	 * Possible Types of Items
	 */
	public static enum ItemType
	{
		BRIEFCASE, RADAR, BULLET, INVINC
	};

	/**
	 * What this Item is
	 */
	public ItemType type;

	/**
	 * Create Item of this type
	 * @param t the Type
	 */
	public Items(ItemType t)
	{
		super();
		this.type = t;
		if (t == ItemType.BRIEFCASE)
		{

		}
		else
		{

		}
	}

	/**
	 * @deprecated SHOULD NOT BE CALLED OF A ITEM
	 */
	@Override
	public final void move(char direction)
	{
		throw new IllegalArgumentException();
	}

	/**
	 * Decide Where the Item may spawn
	 * 1. Can not spawn in Room if not a suitcase, or the opposite if a suitcase
	 * 2. Can not spawn in 0,0
	 */
	@Override
	public boolean isSpawnableHere()
	{
		getX();
		getY();
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @return a String representation of this Object
	 */
	@Override
	public String toString()
	{
		if(type == ItemType.BRIEFCASE)
		{
			return "B";
		}
		else if(type == ItemType.RADAR)
		{
			return "r";
		}
		else if(type == ItemType.BULLET)
		{
			return "b";
		}
		else if(type == ItemType.INVINC)
		{
			return "i";
		}
		return null;
	}
}

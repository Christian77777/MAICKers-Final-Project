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
 * This Class represents a Enemy on the board. With Specific Spawn and Location Change Restrictions
 */
public class Ninja extends Entities{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8644297507406393482L;

	/**
	 * @return if Ninja is allowed to Spawn here
	 * 1. Ninja can not Spawn in Room
	 * 2. Ninja can not Spawn 
	 */
	@Override
	public boolean isSpawnableHere()
	{
		isSpecialRoom(getX(),getY());
		return false;
	}
	
	/**
	 * Check if Ninja can move into this space
	 * @param a horizontal space
	 * @param b vertical space
	 * @return if can move into the Space
	 */
	public boolean isMoveableHere(int a, int b)
	{
		return false;
	}

	/**
	 * @return what this Entity Is when on the Map
	 */
	@Override
	public String toString()
	{
		return "N";
	}
}

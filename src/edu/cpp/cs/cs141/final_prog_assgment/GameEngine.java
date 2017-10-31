/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

/**
 * @author kiana
 *
 */
public class GameEngine {

	public GameEngine
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

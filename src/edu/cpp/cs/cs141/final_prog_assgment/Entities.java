/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

/**
 * @author kiana
 * 
 */
public abstract class Entities
{

	/**
	 * Horizontal Position of this Entity
	 */
	private int x;
	/**
	 * Vertical Postition of this Entity
	 */
	private int y;

	/**
	 * Set up position of this Ninja, and Item
	 */
	public Entities()
	{
		do
		{
			x = (int) (Math.random() * 9.0);
			y = (int) (Math.random() * 9.0);
		} while (!isSpawnableHere(x,y));
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
	 * 
	 * @return
	 */
	public abstract boolean isSpawnableHere(int x, int y);

	/**
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

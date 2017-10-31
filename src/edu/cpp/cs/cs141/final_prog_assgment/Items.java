/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

/**
 * @author kiana
 *
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

	public ItemType type;

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

	@Override
	public void move(char direction)
	{
		throw new IllegalArgumentException();
	}

	@Override
	public boolean isSpawnableHere()
	{
		getX();
		getY();
		// TODO Auto-generated method stub
		return false;
	}
}

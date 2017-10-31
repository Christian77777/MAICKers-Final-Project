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
	 * Possible Types of Items
	 */
	public static enum ItemType
	{
		BRIEFCASE, RADAR, BULLET, INVINC
	};

	public ItemType type;

	public Items(ItemType t)
	{
		super(x, y);
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
}

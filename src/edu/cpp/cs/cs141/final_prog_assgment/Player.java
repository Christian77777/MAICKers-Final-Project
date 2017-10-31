/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

/**
 * @author kiana
 *
 */
public class Player extends Entities
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2091432889491962930L;
	private boolean ammo = true;
	private int life = 3;
	
	public Player()
	{
		super(0,0);
	}
	
	/**
	 * return is the Player is able to shoot
	 * @return if carrying Ammo
	 */
	private boolean hasAmmo()
	{
		return ammo;
	}
	/**
	 * Either return Ammo to player when over Power up
	 * Or Remove Ammo from player when shot is used
	 * @param a if Player should now have ammo
	 */
	private void setAmmo(boolean a)
	{
		ammo = a;
	}
	
	/**
	 * Return what this Entity Is when on the Map
	 */
	@Override
	public String toString()
	{
		return "S";
	}
	
	/**
	 * Reduce Life by one
	 * @return the new Life Value
	 */
	private int loseLife()
	{
		life--;
		return life;
	}
	
	/**
	 * @return the Life the player has
	 */
	private int getLife()
	{
		return life;
	}

	@Override
	public boolean isSpawnableHere()
	{
		// TODO Auto-generated method stub
		return false;
	}
}

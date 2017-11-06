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
 * This Class represents the User, and their charactor. Decisions for the Player
 * are made in the UI class, and verified by the Game Engine
 */
public class Player extends Entities {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2091432889491962930L;
	/**
	 * if gun has single shot
	 */
	private boolean ammo = true;
	/**
	 * amount of life player has
	 */
	private int life = 3;

	/**
	 * Sets Player at 0,0 with Max Health and Ammo
	 */
	public Player() {
		super(0, 0);
	}

	/**
	 * return if the Player is able to shoot
	 * 
	 * @return if carrying Ammo
	 */
	public boolean hasAmmo() {
		return ammo;
	}

	/**
	 * Either return Ammo to player when over Power up Or Remove Ammo from player
	 * when shot is used
	 * 
	 * @param a
	 *            if Player should now have ammo
	 */
	public void setAmmo(boolean a) {
		ammo = a;
	}

	/**
	 * Return what this Entity Is when on the Map
	 */
	@Override
	public String toString() {
		return "P";
	}

	/**
	 * Reduce Life by one
	 * 
	 * @return the new Life Value
	 */
	public int loseLife() {
		life--;
		return life;
	}

	/**
	 * @return the Life the player has
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @deprecated Not used for Spawning because space is 0,0 always
	 */
	@Override
	public boolean isSpawnableHere() {
		// TODO Auto-generated method stub
		return false;
	}
}

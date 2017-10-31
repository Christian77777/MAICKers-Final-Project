package edu.cpp.cs.cs141.final_prog_assgment;

import java.util.ArrayList;
import edu.cpp.cs.cs141.final_prog_assgment.Items.ItemType;

public class GameBoard {
	private Player player;
	private ArrayList<Ninja> enemies = new ArrayList<Ninja>();
	private ArrayList<Items> items = new ArrayList<Items>();
	
	public GameBoard() {
		player = new Player();
		for(int x = 0; x < 6; x++)
		{
			enemies.add(new Ninja());
		}
		items.add(new Items(ItemType.BRIEFCASE));
	}

}

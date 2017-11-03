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
 * Main Class to Start Program
 */
public class Main {

	/**
	 * Start the Program/Game
	 * 
	 * @param args
	 *            from commands to enable the gui
	 */
	public static void main(String[] args) {
		/*
		GameEngine engine = new GameEngine();
		engine.newGame();
		*/
		//Testing below//
		GameBoard b = new GameBoard();
		//b.qPrint();
		b.displayData(false);
		
		
//		System.out.println(b.checkFlag(72, 1, '1'));
//		System.out.println(b.checkFlag(10, 1, '1'));
//		System.out.println(b.checkFlag(10, 1, '0'));
//		System.out.println(b.checkFlag(72, 1, '0'));
//		//InProx
//		System.out.println(b.inProxPlayer(64));
//		System.out.println(b.inProxPlayer(54));
//		System.out.println(b.inProxPlayer(63));
//		System.out.println(b.inProxPlayer(72));
//		System.out.println(b.inProxPlayer(73));
//		System.out.println(b.inProxPlayer(74));
//		System.out.println(b.inProxPlayer(21));
//		System.out.println(b.inProxPlayer(49));
//		System.out.println(b.inProxPlayer(10));
//		//isRoom
//		System.out.println(b.isRoom(10));
//		System.out.println(b.isRoom(13));
//		System.out.println(b.isRoom(16));
//		System.out.println(b.isRoom(37));
//		System.out.println(b.isRoom(40));
//		System.out.println(b.isRoom(43));
//		System.out.println(b.isRoom(64));
//		System.out.println(b.isRoom(67));
//		System.out.println(b.isRoom(70));
//		
//		System.out.println(b.isRoom(14));
//		System.out.println(b.isRoom(1));
//		System.out.println(b.isRoom(80));
//		System.out.println(b.isRoom(78));
//		System.out.println(b.isRoom(77));
		
	}

}

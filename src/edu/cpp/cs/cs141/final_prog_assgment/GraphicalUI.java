/**
 * 
 */
package edu.cpp.cs.cs141.final_prog_assgment;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Christian77777
 *
 */
public class GraphicalUI extends UserInterface {

	
	public GraphicalUI(){
		System.out.println("GUI Enabled, Ignoring Console");
		JOptionPane.showMessageDialog(null, "GUI Enabled, but Incomplete", "Incomplete Code", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/edu/cpp/cs/cs141/final_prog_assgment/logo.png")));
	}
	
	@Override
	public int welcomeMessage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printHelp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int pickTurn(boolean canLook, boolean canShoot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean printGameOver(boolean victorious) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printShotResult(boolean result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String querySaveFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printInvalidMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String queryLoadFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printDamaged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean offerDifficulty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public char queryDirection(String actionType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printMap(String[] map, char lookDirection, boolean debug, boolean radarActive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printRoomContents(boolean briefcase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printPowerUp(char item) {
		// TODO Auto-generated method stub
		
	}

}

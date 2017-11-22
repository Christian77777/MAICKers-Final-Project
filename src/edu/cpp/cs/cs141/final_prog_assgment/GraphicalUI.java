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

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

/**
 * This Class is the Implementation of the Option GUI Interface for the Program
 */
public class GraphicalUI extends UserInterface {
	private JFrame frame;
	private JPanel mapPanel;
	private JPanel menuPanel;
	private JLabel[][] map = new JLabel[9][9];
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnHelp;
	private JButton btnQuit;
	private JLabel lblMainMenu;
	private JTextArea txtArea;
	private int menuOption;
	private CountDownLatch latch;
	/**
	 * Mode of GUI
	 * 0 = Main Menu
	 * 1 = Prompt Turn
	 */
	private int mode = 0;
	private JSeparator separator1;

	public GraphicalUI() {
		frame = new JFrame("Spy Game");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GraphicalUI.class.getResource("/edu/cpp/cs/cs141/final_prog_assgment/logo.png")));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[::500px][][][grow]"));
		frame.setSize(800, 800);

		mapPanel = new JPanel();
		mapPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		mapPanel.setLayout(new MigLayout("", "[][][][][][][][][]", "[][][][][][][][][]"));

		menuPanel = new JPanel();
		menuPanel.setBorder(null);
		menuPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][]"));

		lblMainMenu = new JLabel("Main Menu");
		menuPanel.add(lblMainMenu, "flowy,cell 0 0 4 1,alignx center");

		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuOption = 1;
				latch.countDown();
			}
		});
		menuPanel.add(btnNewGame, "cell 0 1,growx");

		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuOption = 2;
				latch.countDown();
			}
		});
		menuPanel.add(btnLoadGame, "cell 1 1,growx");

		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuOption = 3;
				latch.countDown();
			}
		});
		menuPanel.add(btnHelp, "cell 2 1,growx");

		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuOption = 4;
				latch.countDown();
			}
		});
		
		separator1 = new JSeparator();
		frame.getContentPane().add(separator1, "cell 0 1,growx");
		menuPanel.add(btnQuit, "cell 3 1,growx");
		
		frame.getContentPane().add(menuPanel, "cell 0 2,grow");
		menuPanel.setVisible(false);

		txtArea = new JTextArea();
		txtArea.setText("Welcome to the Spy Game");
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		frame.getContentPane().add(txtArea, "cell 0 3,grow");

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				map[x][y] = new JLabel(new ImageIcon("/edu/cpp/cs/cs141/final_prog_assgment/logo.png"));
				map[x][y].setIcon(
						new ImageIcon(GraphicalUI.class.getResource("/edu/cpp/cs/cs141/final_prog_assgment/logo.png")));
				mapPanel.add(map[x][y], "cell " + x + " " + y);
			}

		}
		mapPanel.setVisible(false);
		System.out.println("GUI Enabled, Ignoring Console");
		frame.setVisible(true);
		welcomeMessage();
		JOptionPane.showMessageDialog(null, "GUI Enabled, but Incomplete", "Incomplete Code",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/edu/cpp/cs/cs141/final_prog_assgment/logo.png")));
	}
	
	@Override
	public void welcomeMessage()
	{
		txtArea.setText("Welcome to the game!");
	}

	@Override
	public int printMainMenu() {
		menuPanel.setVisible(true);
		txtArea.setText("Please Select a Main Menu Option");
		latch = new CountDownLatch(1);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menuPanel.setVisible(false);
		return menuOption;
	}

	@Override
	public void printHelp() {
		// TODO Auto-generated method stub

	}

	@Override
	public int pickTurn(boolean canLook, boolean canShoot) {

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
	public String queryLoadFileName(String[] saves) {
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
		char[][] formattedMap = formatMap(map,lookDirection,debug,radarActive);
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				
			}
		}

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

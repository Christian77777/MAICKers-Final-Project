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

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import net.miginfocom.swing.MigLayout;

/**
 * This Class is the Implementation of the Option GUI Interface for the Program
 */
public class GraphicalUI extends UserInterface
{
	private JFrame frame;
	private JPanel mapPanel;
	private JPanel menuPanel;
	private JLabel[][] Labelmap = new JLabel[9][9];
	private char[][] oldmap = new char[9][9];
	private JTextArea txtArea;
	private int menuOption;
	private CountDownLatch latch;
	private int pageNumber;
	private JPanel pickTurnPanel;
	private int pickTurnOption;
	private JPanel pickDirectionPanel;
	private char pickDirectionOption;
	private JSeparator separator1;

	public GraphicalUI()
	{
		frame = new JFrame("Spy Game");
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GraphicalUI.class.getResource("/edu/cpp/cs/cs141/final_prog_assgment/logo.png")));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][][][grow]"));
		frame.setSize(800, 800);

		// Create MapPanel
		mapPanel = new JPanel();
		mapPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		mapPanel.setLayout(new MigLayout("", "[][][][][][][][][]", "[][][][][][][][][]"));

		// Create MenuPanel
		menuPanel = new JPanel();
		menuPanel.setBorder(null);
		menuPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][]"));

		JLabel lblMainMenu = new JLabel("Main Menu");
		menuPanel.add(lblMainMenu, "flowy,cell 0 0 4 1,alignx center");
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuOption = 1;
				latch.countDown();
			}
		});
		menuPanel.add(btnNewGame, "cell 0 1,growx");
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuOption = 2;
				latch.countDown();
			}
		});
		menuPanel.add(btnLoadGame, "cell 1 1,growx");
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				menuOption = 3;
				latch.countDown();
			}
		});
		menuPanel.add(btnHelp, "cell 2 1,growx");
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		menuPanel.add(btnQuit, "cell 3 1,growx");

		// Create PickTurnPanel
		pickTurnPanel = new JPanel();
		pickTurnPanel.setBorder(null);
		pickTurnPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][]"));
		JLabel lblPickTurn = new JLabel("What Action do you WIsh to take");
		lblPickTurn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				pickTurnOption = -1;
				latch.countDown();
			}

			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }
		});
		pickTurnPanel.add(lblPickTurn, "flowy,cell 0 0 5 1,alignx center", -1);
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickTurnOption = 1;
				latch.countDown();
			}
		});
		pickTurnPanel.add(btnMove, "cell 0 1,growx", -1);
		JButton btnLook = new JButton("Look");
		btnLook.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickTurnOption = 2;
				latch.countDown();
			}
		});
		pickTurnPanel.add(btnLook, "cell 1 1,growx", -1);
		JButton btnShoot = new JButton("Shoot");
		btnShoot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickTurnOption = 3;
				latch.countDown();
			}
		});
		pickTurnPanel.add(btnShoot, "cell 2 1,growx", -1);
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickTurnOption = 4;
				latch.countDown();
			}
		});
		pickTurnPanel.add(btnSave, "cell 3 1,growx", -1);
		JButton btnQuit2 = new JButton("Quit");
		btnQuit2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		pickTurnPanel.add(btnQuit2, "cell 4 1,growx", -1);
		
		//Create Direction Panel
		pickDirectionPanel = new JPanel();
		pickDirectionPanel.setBorder(null);
		pickDirectionPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[][][]"));
		JLabel lblPickDirection = new JLabel("Which Direction?");
		pickDirectionPanel.add(lblPickDirection, "flowy,cell 1 1,alignx center", -1);
		JButton btnNorth = new JButton("North");
		btnNorth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickDirectionOption = 'n';
				latch.countDown();
			}
		});
		pickDirectionPanel.add(btnNorth, "cell 1 0,growx", -1);
		JButton btnEast = new JButton("East");
		btnEast.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickDirectionOption = 'e';
				latch.countDown();
			}
		});
		pickDirectionPanel.add(btnEast, "cell 2 1,growx", -1);
		JButton btnSouth = new JButton("South");
		btnSouth.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickDirectionOption = 's';
				latch.countDown();
			}
		});
		pickDirectionPanel.add(btnSouth, "cell 1 2,growx", -1);
		JButton btnWest = new JButton("West");
		btnWest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pickDirectionOption = 'w';
				latch.countDown();
			}
		});
		pickDirectionPanel.add(btnWest, "cell 0 1,growx", -1);

		separator1 = new JSeparator();
		frame.getContentPane().add(separator1, "cell 0 1,growx");

		txtArea = new JTextArea();
		txtArea.setText("Welcome to the Spy Game");
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		frame.getContentPane().add(txtArea, "cell 0 3,grow");

		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				Labelmap[x][y] = new JLabel();
				mapPanel.add(Labelmap[x][y], "cell " + y + " " + x);
				oldmap[x][y] = 'u';
			}

		}
		frame.getContentPane().add(mapPanel, "cell 0 0");
		System.out.println("GUI Enabled, Ignoring Console");
		frame.setVisible(true);
		welcomeMessage();
		JOptionPane.showMessageDialog(frame, "GUI Enabled, but Incomplete", "Incomplete Code",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/edu/cpp/cs/cs141/final_prog_assgment/logo.png")));
	}

	@Override
	public void welcomeMessage()
	{
		txtArea.setText("Welcome to the game!");
	}

	@Override
	public int printMainMenu()
	{
		try
		{
			EventQueue.invokeAndWait(new Runnable()
			{

				@Override
				public void run()
				{
					frame.getContentPane().add(menuPanel, "cell 0 2,grow");
					txtArea.setText("Please Select a Main Menu Option");
					frame.validate();
				}
			});
			latch = new CountDownLatch(1);
			latch.await();
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					frame.getContentPane().remove(menuPanel);
					frame.validate();
				}
			});
		}
		catch (InvocationTargetException | InterruptedException e1)
		{
			e1.printStackTrace();
			System.exit(1);
		}
		return menuOption;
	}

	@Override
	public void printHelp()
	{
		try
		{
			String[] messages = new String[6];
			messages[0] = new String(
					"\n\t The object of the game is to move your Spy character around the 9 by 9 grid board"
							+ "\n\t safely. Watch out, there are Ninjas about! If you run into a Ninja you will lose"
							+ "\n\t one of your lives! Each game you are given 3 lives. If you are defeated by a"
							+ "\n\t ninja 3 times within a game, all of your lives are lost and you'll be forced to"
							+ "\n\t start over with a new game. Warning, it's not as easy as you think to win... "
							+ "\n\t Someone has turned off the lights! So you can't see where the ninjas are.. "
							+ "\n\t Luckily for you, since you have a flashlight, you may chose to look in one "
							+ "\n\t direction and see two spaces in that direction."
							+ "\n\t To win the game you must move your Spy player aroard safely and check each room,"
							+ "\n\t indicated by the [#] symbol, for the briefcase. Once youve found the correct room"
							+ "\n\t with the briefcase, congratulations! You have won the game."
							+ "\n\t\t *Please note that rooms can only be entered from the top, no other "
							+ "\n\t\t direction will allow you into the room to check for a breifcase.\n\t See the board"
							+ " layout below for reference. You start each new game in the bottom\n\t\t left corner as shown.");
			messages[1] = new String(
					"\nStart Menu: \n\t The startup menu will present you with 4 options, you can either: "
							+ "\n\t\t 1. Start New Game \n\t\t\t Starting a new game will allow you to begin playing Spy Game. \n"
							+ "\t\t\t Upon starting a new game you will be given the choice if you"
							+ "\n\t\t\t 'would like to enable the ninja AI'. Enter Y for yes and N for"
							+ "\n\t\t\t no. If you are unsure what AI means, This is simply asking if"
							+ "\n\t\t\t you would like to give the game an extra challange by allowing"
							+ "\n\t\t\t the ninjas to see where you are and move in your direction"
							+ "\n\t\t\t verses moving randomly."
							+ "\n\t\t 2. Load Game\n\t\t\t Loading a game will allow you to reload a game that you have"
							+ "\n\t\t\t previously saved."
							+ "\n\t\t 3. Help\n\t\t\t Congratulations, you already know what help does because help"
							+ "\n\t\t\t simply brings up this help screen with all the information needed "
							+ "\n\t\t\t on how to play Spy Game. There is even a hint embedded in the "
							+ "\n\t\t\t pictures below that, when entered after a game is going, will"
							+ "\n\t\t\t allow you to see where everything is located on the board! Can "
							+ "\n\t\t\t you find the secrete code?"
							+ "\n\t\t 4. Quit?\n\t\t\t Choosing this allows you to close the game before you begin. If"
							+ "\n\t\t\t you so wish to crawl into a ball and admit defeat to the nijas"
							+ "\n\t\t\t beforehand, this is your option to quit now."
							+ "\n\t To choose any option simply enter the corresponding number followed by pressing\n\t the 'enter' key.");
			messages[2] = new String(
					"\nStarting a New Game:\n\t\t When starting a new game, you will then be asked if you'd like to enable"
							+ "\n\t      the AI for the ninjas. Enabling the AI makes the game slightly"
							+ " harder to win \n\t      as you have to outsmart the computer in your moves!"
							+ "\n\tOnce a game is started you are presented with 5 options, you can either:"
							+ "\n\t\t1. Move"
							+ "\n\t\t\tOnce you enter 1 to move, you'll then be asked if you want to move"
							+ "\n\t\t\tNorth, South, East or West. To move in such direction simply enter"
							+ "\n\t\t\tN for North, S for South, E for East and W for West. Your spy"
							+ "\n\t\t\tcharacter will then move acordingly on the board in the direction"
							+ "\n\t\t\tyou wanted to move." + "\n\t\t2. Look"
							+ "\n\t\t\tEntering 2 for Look will then ask you what direction you'd like to"
							+ "\n\t\t\tlook in similarly to how you are asked to move as explained above."
							+ "\n\t\t\tThis will come in handy considering this allows you to use your"
							+ "\n\t\t\tflashlight too see two squares in any one direction you wish!"
							+ "\n\t\t\tLooking before moves is handy to see if any Ninjas or power-ups"
							+ "\n\t\t\tare around!" + "\n\t\t3. Shoot"
							+ "\n\t\t\tEntering 3 for Shoot will ask you what direction as similarly"
							+ "\n\t\t\tdescribed above. When shooting, the bullet keeps going until it"
							+ "\n\t\t\teither hits a wall, a room or a Ninja. If you hit a Ninja, you've"
							+ "\n\t\t\tkilled one of the 6 Ninjas and now have one less Ninja to worry"
							+ "\n\t\t\tabout." + "\n\t\t4. Save"
							+ "\n\t\t\tEntering 4 to Save will then prompt you to enter a name of which"
							+ "\n\t\t\tyou'd like to save your current game as. Once you enter a name,"
							+ "\n\t\t\tit's your choice of what to do next as you are presented with all"
							+ "\n\t\t\t1-5 options again." + "\n\t\t5. Quit"
							+ "\n\t\t\tEntering 5 to Quit simply ends the game on that turn.");
			messages[3] = new String("\nLoading a Previous Game\n\t...");
			messages[4] = new String(
					"\nPower-Ups:\n\tThroughout the game board there are three possible Power-Ups you may encounter"
							+ "\n\twhile playing. To capture these Power-Ups, simply walk over them! When you walk"
							+ "\n\tinto there space, they are activated. The three Power-Ups are:" + "\n\t\t1. Radar"
							+ "\n\t\t\tThe Radar Power-Up is indicated by [R] on the game board. This"
							+ "\n\t\t\tPower-Up is arguably the most helpful out of them all. Radar gives"
							+ "\n\t\t\tyou the ability to see what room the briefcase is in! If you are"
							+ "\n\t\t\table to find it, you will nolonger need to search each room! Now"
							+ "\n\t\t\tthe room which contains the briefcase will be seen as [B]."
							+ "\n\t\t2. Invincibility"
							+ "\n\t\t\tThe Invincibility Power-Up is indicated by [I] on the game board."
							+ "\n\t\t\tThis Power-Up give you ability to make 5 turns without worrying"
							+ "\n\t\t\tabout Ninjas getting you! You can't die for 5 turns!" + "\n\t\t3. Ammo"
							+ "\n\t\t\tThis Power-Up gives you extra ammo! *NOTE:You are only allowed a"
							+ "\n\t\t\tmax of 1 bullet at a time. If you collect this Power-Up without"
							+ "\n\t\t\tusing your intital bullet first, this Power-Up will have no effect"
							+ "\n\t\t\ton you. ");
			messages[5] = new String(
					"\nDo you think you have what it takes to find the room with the briefcase before a Ninja"
							+ "\nfinds YOU?! Enter 1 to start a new game and see for yourself if you are worthy to be"
							+ "\ncalled a Spy or are you just a wanna-be Spy?");
			txtArea.setText(messages[0]);
			pageNumber = 0;
			JButton continueHelp = new JButton("Next Page");
			continueHelp.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					pageNumber++;
					if (pageNumber <= 5)
					{
						txtArea.setText(messages[pageNumber]);
					}
					else
					{
						frame.getContentPane().remove(continueHelp);
						frame.validate();
						txtArea.setText("");
						latch.countDown();
					}
				}

			});
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					frame.getContentPane().add(continueHelp, "cell 0 2,grow");
					frame.validate();
				}
			});
			latch = new CountDownLatch(1);
			latch.await();
			pageNumber = 0;
		}
		catch (InvocationTargetException | InterruptedException e1)
		{
			e1.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public int pickTurn(boolean canLook, boolean canShoot)
	{
		try
		{
			JButton tempLookButton = (JButton) pickTurnPanel.getComponent(2);
			JButton tempShootButton = (JButton) pickTurnPanel.getComponent(3);
			if (canLook)
			{
				tempLookButton.setEnabled(true);
				tempLookButton.setText("Look");
			}
			else
			{
				tempLookButton.setEnabled(false);
				tempLookButton.setText("Already Looked");
			}
			if (canShoot)
			{
				tempShootButton.setEnabled(true);
				tempShootButton.setText("Shoot");
			}
			else
			{
				tempShootButton.setEnabled(false);
				tempShootButton.setText("No Ammo");
			}
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					if(!mapPanel.isVisible())
					{
						mapPanel.setVisible(true);
					}
					frame.getContentPane().add(pickTurnPanel, "cell 0 2,grow");
					txtArea.setText("Please Select a Main Menu Option");
					frame.validate();
				}
			});
			latch = new CountDownLatch(1);
			latch.await();
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					frame.getContentPane().remove(pickTurnPanel);
					frame.validate();
				}
			});
		}
		catch (InvocationTargetException | InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return pickTurnOption;
	}

	@Override
	public boolean printGameOver(boolean victorious)
	{
		String message;
		if (victorious)
		{
			message = new String("You have Won the Game!\nWould you like to play Again");
		}
		else
		{
			message = new String("You Lost all your lives...\nWould you like to Try Again?");
		}
		int option = JOptionPane.showConfirmDialog(frame, message, "Game Over", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (option == 0)
		{
			// Yes = Play again
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void printShotResult(boolean result)
	{
		String message;
		double dice = Math.random();
		if (result) {
			if (dice < .25) {
				message = new String("You killed a Ninja");
			} else if (dice < .50) {
				message = new String("A Ninja is now dead");
			} else if (dice < .75) {
				message = new String("Ninja just said his last words at your hands");
			} else {
				message = new String("One less Ninja to worry about");
			}
		} else {
			if (dice < .25) {
				message = new String("Wasted a Bullet");
			} else if (dice < .50) {
				message = new String("Ninjas are uneffected by you");
			} else if (dice < .75) {
				message = new String("Stop shooting blindly, you missed them all");
			} else {
				message = new String("Shot in the wrong direction");
			}
		}
		txtArea.setText(message);
	}

	class FileNameQueryDialog extends JDialog
	{
		
		/**
		 * ID of this version of the Dialog
		 */
		private static final long serialVersionUID = 2782276262318905787L;

		/**
		 * JTextField where the User inputs the File Name
		 */
		private JTextField fileNameField;

		/**
		 * Points to String in JTextField
		 */
		private String enteredFileName;
		
		/**
		 * Internal Panel inside JDialog
		 */
		private JOptionPane optionPane;

		/**
		 * JButton to Confirm User finished entering File Name
		 */
		private JButton btn1 = new JButton("Verify");

		/**
		 * @return the verified Username
		 */
		public String getUsername()
		{
			return enteredFileName;
		}
		
		/** Creates the reusable dialog. */
		public FileNameQueryDialog(JFrame window)
		{
			super(window, true);
			setTitle("File Name Query");

			fileNameField = new JTextField();
			fileNameField.setText("");
			
			String msgString1 = "What do you want to name your Save File to?";
			Object[] array = { msgString1, fileNameField };

			btn1.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btn1.setEnabled(false);
					enteredFileName = fileNameField.getText();
					boolean saveable = false;
					try
					{
						System.out.println(
								Paths.get(GameEngine.getSavePath() + File.separator + enteredFileName + ".ser"));
						saveable = true;
					}
					catch (InvalidPathException e)
					{
						
					}
					if (saveable)
					{
						setVisible(false);
					}
					else
					{
						fileNameField.selectAll();
						JOptionPane.showMessageDialog(FileNameQueryDialog.this,
								"SaveFile Name invalid, please remove invalid characters",
								"Bad Filename", JOptionPane.ERROR_MESSAGE);
						fileNameField.requestFocusInWindow();
						btn1.setEnabled(true);
					}
				}

			});
			;
			Object[] options = { btn1 };

			optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options,
					options[0]);
			setContentPane(optionPane);

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent we)
				{
					setVisible(false);
				}
			});

			addComponentListener(new ComponentAdapter()
			{
				public void componentShown(ComponentEvent ce)
				{
					fileNameField.requestFocusInWindow();
				}
			});
		}
	}

	@Override
	public String querySaveFileName()
	{
		FileNameQueryDialog dialog = new FileNameQueryDialog(frame);
		dialog.pack();
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);//Blocks until Option made
		return dialog.getUsername();
	}

	@Override
	public void printInvalidMove()
	{
		JOptionPane.showMessageDialog(frame, "Can Not Choose this Direction!", "Invalid Direction", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public String queryLoadFileName(String[] saves)
	{
		if(saves.length == 0)
		{
			JOptionPane.showMessageDialog(frame, "No Save Files Found!\nStarting new Game...", "No Save Data", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return (String)JOptionPane.showInputDialog(frame, "Which Save File would you like to Load?", "Load Save", JOptionPane.QUESTION_MESSAGE, null, saves, saves[0]);
	}

	@Override
	public void printDamaged()
	{
		JOptionPane.showMessageDialog(frame, "You have been Struck!\nYou must retreat to the first Room...", "Life Lost", JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public boolean offerDifficulty()
	{
		int option = JOptionPane.showConfirmDialog(frame, "Would you like to Enable the Ninja AI?", "Difficulty Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public char queryDirection(String actionType)
	{
		try
		{
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					frame.getContentPane().add(pickDirectionPanel, "cell 0 2,grow");
					txtArea.setText("Please Select a Direction to " + actionType + " In");
					frame.validate();
				}
			});
			latch = new CountDownLatch(1);
			latch.await();
			EventQueue.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					frame.getContentPane().remove(pickDirectionPanel);
					frame.validate();
				}
			});
		}
		catch (InvocationTargetException | InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return pickDirectionOption;
	}

	@Override
	public void printMap(String[] map, char lookDirection, boolean debug, boolean radarActive)
	{
		char[][] formattedMap = formatMap(map, lookDirection, debug, radarActive);
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				if(!(oldmap[x][y] == formattedMap[x][y]))
				{
					String path;
				switch (formattedMap[x][y]) {
	            case 'P': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/spy.png");//?
	                     break;
	            case 'N': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/ninja.png");//https://vignette4.wikia.nocookie.net/clubpenguin/images/d/d8/Ninja_Old_Header.png/revision/latest?cb=20140616202121
	            		break;
	            case 'A': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/bullet.jpg");//https://images-na.ssl-images-amazon.com/images/I/31%2BRCLzutKL.jpg
	            		break;
	            case 'I': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/invincibility.jpg");//https://i.pinimg.com/736x/40/86/2e/40862e0ac7efb875d416c7b03ab8dca2--super-mario-party-super-mario-bros.jpg
                		break;
	            case 'R': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/radar.png");//https://pixabay.com/p-38078/?no_redirect
                		break;
	            case 'B': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/briefcase.gif");//http://www.aperfectworld.org/clipart/office/briefcase08.gif
                		break;
	            case '#': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/room.jpg");//https://pbs.twimg.com/media/C8pOpUXXsAARLsf.jpg
                		break;
	            case '\u2022': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/fog.png");//http://moziru.com/images/drawn-fog-1.png
                		break;
	            case '\u0000': path = new String("/edu/cpp/cs/cs141/final_prog_assgment/whiteSquare.jpg");//https://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=65691568
                		break;
	            default:  throw new IllegalArgumentException();
				}
				Labelmap[x][y].setIcon(new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)));
				}
				oldmap[x][y] = formattedMap[x][y];
			}
		}

	}

	@Override
	public void printRoomContents(boolean briefcase)
	{
		String message;
		if(briefcase)
		{
			message = new String("The briefcase is in this room!");
		}
		else
		{
			message = new String("This room is empty.");
		}
		JOptionPane.showMessageDialog(frame, message, "Room Contents", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void printPowerUp(char item)
	{
		String message;
		if (item == 'a')
			message = new String("You've found a bullet!\nYou now have max ammo");
		else if (item == 'i')
			message = new String("You're invincible for 5 turns!\nNinjas can't kill you.");
		else if (item == 'r')
			message = new String("You've found a radar!\nYou know where the briefcase is.");
		else
			throw new IllegalArgumentException();
		JOptionPane.showMessageDialog(frame, message, "Powerup Get!", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void confirmSaveFile(String path)
	{
		//Does nothing in GUI
	}
}

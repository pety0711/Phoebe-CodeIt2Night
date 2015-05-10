package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Arena;
import main.CoordVector;

public class GameWindow extends JFrame {

	private Arena arena;
	
	private GamePanel gamePanel;
	private SideBar rightSideBar;
	private SideBar leftSideBar;
	private InfoBar infoBar;
	private MenuBar menuBar;
	private JLabel titleBar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @param coordVector
	 */
	public GameWindow(CoordVector coord, Arena aarena) {
		this.arena = aarena;
		initialize(coord);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param coord
	 */
	private void initialize(CoordVector coord) {

		// Frame
		setPreferredSize(new Dimension(450, 150));
		setTitle("Phoebe Game");
		setResizable(false);
		setSize(new Dimension(450, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setFocusable(true);
		this.setLayout(new BorderLayout(0, 0));
		this.setFocusable(true);
		// Title label
		titleBar = new JLabel("Phoebe");
		titleBar.setHorizontalTextPosition(SwingConstants.CENTER);
		titleBar.setHorizontalAlignment(SwingConstants.CENTER);
		titleBar.setFont(new Font("Calibri Light", Font.BOLD, 18));
		this.add(titleBar, BorderLayout.NORTH);

		// Left side bar
		leftSideBar = new SideBar(arena.getGamers().get(0));
		leftSideBar.setSize(new Dimension(100, 100));
		leftSideBar.setPreferredSize(new Dimension(100, 100));
		leftSideBar.setMinimumSize(new Dimension(100, 100));
		this.add(leftSideBar, BorderLayout.WEST);

		// Right side Bar
		rightSideBar = new SideBar(arena.getGamers().get(1));
		rightSideBar.setSize(new Dimension(100, 100));
		rightSideBar.setPreferredSize(new Dimension(100, 100));
		rightSideBar.setMinimumSize(new Dimension(100, 100));
		this.add(rightSideBar, BorderLayout.EAST);

		// Info Bar
		infoBar = new InfoBar();
		this.add(infoBar, BorderLayout.SOUTH);

		// Game panel
		gamePanel = new GamePanel(coord);
		this.add(gamePanel, BorderLayout.CENTER);

		// Menu bar
		menuBar = new MenuBar();
		this.setJMenuBar(menuBar);

		this.setVisible(true);
	}

	public void registerKeyListener(KeyListener l) {
		addKeyListener(l);
		gamePanel.registerKeyListener(l);
	}

	public void draw(CoordVector coord, String type) {
		gamePanel.draw(coord, type);
	}

	public void drawTime(int min, int sec) {
		if (sec < 10) {
			infoBar.refreshTime(Integer.toString(min) + " : " + "0"
					+ Integer.toString(sec));
		} else {
			infoBar.refreshTime(Integer.toString(min) + " : "
					+ Integer.toString(sec));
		}
	}

	public void drawPoints() {
		rightSideBar.refreshDatas();
		leftSideBar.refreshDatas();
	}
}

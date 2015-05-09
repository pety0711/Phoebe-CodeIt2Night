package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.JLabel;

public class GameWindow extends JFrame {

	private GamePanel gamePanel;
	private SideBar rightSideBar;
	private SideBar leftSideBar;
	private InfoBar infoBar;
	private MenuBar menuBar; 

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Frame
		setPreferredSize(new Dimension(450, 150));
		setTitle("Phoebe Game");
		setResizable(false);
		setSize(new Dimension(450, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLayout(new BorderLayout(0, 0));

		// Title label
		JLabel titleBar = new JLabel("Phoebe");
		titleBar.setHorizontalTextPosition(SwingConstants.CENTER);
		titleBar.setHorizontalAlignment(SwingConstants.CENTER);
		titleBar.setFont(new Font("Calibri Light", Font.BOLD, 18));
		this.add(titleBar, BorderLayout.NORTH);

		// Left side bar
		JPanel leftSideBar = new SideBar();
		leftSideBar.setSize(new Dimension(100, 100));
		leftSideBar.setPreferredSize(new Dimension(100, 100));
		leftSideBar.setMinimumSize(new Dimension(100, 100));
		this.add(leftSideBar, BorderLayout.WEST);

		// Right side Bar
		JPanel rightSideBar = new SideBar();
		rightSideBar.setSize(new Dimension(100, 100));
		rightSideBar.setPreferredSize(new Dimension(100, 100));
		rightSideBar.setMinimumSize(new Dimension(100, 100));
		this.add(rightSideBar, BorderLayout.EAST);

		// Info Bar
		JPanel infoBar = new InfoBar();
		this.add(infoBar, BorderLayout.SOUTH);

		// Game panel
		JPanel gamePanel = new GamePanel();
		this.add(gamePanel, BorderLayout.CENTER);

		// Menu bar
		JMenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);

		this.setVisible(true);
		
	}
	
	public void draw(){
		
	}
}

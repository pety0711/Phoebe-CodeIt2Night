package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class GameWindow {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel gamePanel = new JPanel();
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		gamePanel.add(table);
		
		JPanel titleBar = new JPanel();
		frame.getContentPane().add(titleBar, BorderLayout.NORTH);
		
		JLabel lblPhoebe = new JLabel("Phoebe");
		titleBar.add(lblPhoebe);
		
		JPanel infoBar = new JPanel();
		frame.getContentPane().add(infoBar, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("2 : 0 0");
		infoBar.add(label);
		
		JPanel leftSideBar = new JPanel();
		frame.getContentPane().add(leftSideBar, BorderLayout.WEST);
		
		JLabel lblGamer = new JLabel("Gamer 1");
		leftSideBar.add(lblGamer);
		
		JPanel rightSideBar = new JPanel();
		frame.getContentPane().add(rightSideBar, BorderLayout.EAST);
		
		JLabel lblGamer_1 = new JLabel("Gamer 2");
		rightSideBar.add(lblGamer_1);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game (CTRL + N)");
		mnFile.add(mntmNewGame);
		
		JMenuItem mntmExit = new JMenuItem("Exit (CTRL + Q)");
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmControls = new JMenuItem("Controls");
		mnHelp.add(mntmControls);
	}

}

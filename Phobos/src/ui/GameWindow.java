package ui;

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

public class GameWindow extends JFrame{

	private GamePanel gamePanel;
	private SideBar rightSideBar;
	private SideBar leftSideBar;
	private InfoBar infoBar;
	private MenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.setVisible(true);
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
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel gamePanel = new GamePanel();
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
	
		
		JPanel titleBar = new JPanel();
		this.getContentPane().add(titleBar, BorderLayout.NORTH);
		
		JLabel lblPhoebe = new JLabel("Phoebe");
		titleBar.add(lblPhoebe);
		
		JPanel infoBar = new JPanel();
		this.getContentPane().add(infoBar, BorderLayout.SOUTH);
		
/*		JLabel label = new JLabel("2 : 0 0");
		infoBar.add(label);*/
		
		JPanel leftSideBar = new SideBar();
		this.getContentPane().add(leftSideBar, BorderLayout.WEST);
		
		//JLabel lblGamer = new JLabel("Gamer 1");
		//leftSideBar.add(lblGamer);
		
		JPanel rightSideBar = new SideBar();
		this.getContentPane().add(rightSideBar, BorderLayout.EAST);
		
		//JLabel lblGamer_1 = new JLabel("Gamer 2");
		//rightSideBar.add(lblGamer_1);
		
		JMenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		this.setVisible(true);

	}

}

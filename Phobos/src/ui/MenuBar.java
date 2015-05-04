package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	
	JMenu file;
	JMenuItem newGame;
	JMenuItem exit;
	
	JMenu help;
	JMenuItem controls;
	
	// TODO: ActionListener-ek beállítása + gyors gombok
	
	public MenuBar(){
		file = new JMenu("File");
		newGame = new JMenuItem("New Game  (CTRL + N)");
		exit = new JMenuItem("Exit (CTRL + Q)");
		
		help = new JMenu("Help");
		controls = new JMenuItem("Controls");
		
		this.add(file);
		file.add(newGame);
		file.add(exit);
		
		this.add(help);
		help.add(controls);
		
		this.setVisible(true);
	}

}

package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	JMenu file;
	JMenuItem newGame;
	JMenuItem exit;

	JMenu help;
	JMenuItem controls;

	// TODO: ActionListener-ek beállítása + gyors gombok

	public MenuBar() {

		JMenu file = new JMenu("File");
		this.add(file);

		JMenuItem newGame = new JMenuItem("New Game");
		file.add(newGame);

		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		JMenu help = new JMenu("Help");
		this.add(help);

		JMenuItem controls = new JMenuItem("Controls");
		help.add(controls);

		this.setVisible(true);
	}

}

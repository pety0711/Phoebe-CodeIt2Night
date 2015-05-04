package ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
		KeyStroke keyStrokeToNewGame = KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_DOWN_MASK);
		newGame.setAccelerator(keyStrokeToNewGame);
		file.add(newGame);

		JMenuItem exit = new JMenuItem("Exit");
		KeyStroke keyStrokeToExitGame = KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				KeyEvent.CTRL_DOWN_MASK);
		exit.setAccelerator(keyStrokeToExitGame);
		file.add(exit);

		JMenu help = new JMenu("Help");
		this.add(help);

		JMenuItem controls = new JMenuItem("Controls");
		help.add(controls);

		this.setVisible(true);
	}

}

package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//import java.util.Timer;
import javax.swing.Timer;

import main.Arena;
import main.Field;
import main.Robot;

public class GameEventListener {

	private Arena mainArena;
	private ArrayList<Robot> robots;

	public GameWindow gameWindow;
	public ActionListener buttonListener;
	public Timer timer;
	private ActionListener taskPerformer;

	public GameEventListener(Arena arena) {
		mainArena = arena;
		robots = arena.getRobots();

		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("tick \n");
				mainArena.tick();
			}
		};

		timer = new Timer(1000, taskPerformer);
		timer.setRepeats(true);
	}

	public void start() {
		timer.start();
	}

	public void exit() {
		timer.stop();
	}

	public void keyPressed(KeyEvent e) {
		if (true)
			switch (e.getKeyCode()) {
			// UP change speed (x,y)=>(x,y+1)
			case KeyEvent.VK_NUMPAD8:
				break;
			case KeyEvent.VK_W:
				break;

			// DOWN speed (x,y)=>(x,y-1)
			case KeyEvent.VK_NUMPAD2:
				break;
			case KeyEvent.VK_S:
				break;

			// LEFT speed (x,y)=>(x-1,y)
			case KeyEvent.VK_NUMPAD4:
				break;
			case KeyEvent.VK_A:
				break;

			// RIGHT speed (x,y)=>(x+1,y)
			case KeyEvent.VK_NUMPAD6:
				break;
			case KeyEvent.VK_D:
				break;

			// PUT OIL
			case KeyEvent.VK_NUMPAD7:

				break;
			case KeyEvent.VK_Q:
				break;

			// PUT PUTTY
			case KeyEvent.VK_NUMPAD9:
				break;
			case KeyEvent.VK_E:
				break;
			}

	}

	private void processArena(ArrayList<Field> fields) {

	}

}

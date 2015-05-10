package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//import java.util.Timer;
import javax.swing.Timer;

import main.Arena;
import main.Field;
import main.Patch;
import main.Robi;

public class GameEventListener {

	private Arena mainArena;
	private ArrayList<Robi> robots;

	public GameWindow gameWindow;
	public ActionListener buttonListener;
	public static Timer timer;
	private ActionListener taskPerformer;
	private static int seconds;
	private static int minutes;

	public GameEventListener(Arena arena) {
		gameWindow = new GameWindow();
		gameWindow.setVisible(true);
		mainArena = arena;
		robots = arena.getGamers();
		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("tick \n");
				gameWindow.drawTime(minutes, seconds);
				if (seconds <= 0) {
					minutes--;
					seconds = 59;
				} else {
					seconds--;
				}
				mainArena.tick();
				processArena(mainArena.getFields());
				gameWindow.drawPoints();
			}
		};

		timer = new Timer(1000, taskPerformer);
		timer.setRepeats(true);
	}

	public static void start() {
		seconds = 0;
		minutes = 2;
		timer.start();
	}

	public static void exit() {
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
		for (Field f : fields) {
			if (f.hasChanged) {
				if (f.hasRobi()) {
					gameWindow.draw(f.getCoord(), f.getRobotId().get(0));
				} else if (f.hasCleaner()) {
					gameWindow.draw(f.getCoord(), f.getRobotId().get(0));
				} else if (f.getPatches().size() > 0) {
					ArrayList<Patch> patches = f.getPatches();
					boolean hasOil = false;
					boolean hasPutty = false;
					for (Patch p : patches) {
						if ("Oil".equals(p.getClass().getSimpleName()))
							hasOil = true;
						else if ("Putty".equals(p.getClass().getSimpleName()))
							hasPutty = true;
					}

					if (hasOil && hasPutty) {
						gameWindow.draw(f.getCoord(), "Mixed");
					} else if (hasOil) {
						gameWindow.draw(f.getCoord(), "Oil");
					} else if (hasPutty) {
						gameWindow.draw(f.getCoord(), "Putty");
					}
				}
			}
		}
	}
}

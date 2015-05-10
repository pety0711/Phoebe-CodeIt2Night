package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
//import java.util.Timer;
import javax.swing.Timer;

import main.Arena;
import main.CoordVector;
import main.Field;
import main.Patch;
import main.Robi;

public class GameEventListener implements KeyListener {

	private Arena mainArena;
	private ArrayList<Robi> robots;

	public GameWindow gameWindow;
	public ActionListener buttonListener;
	public Timer timer;
	private ActionListener taskPerformer;
	private int seconds;
	private int minutes;
	private boolean ok;

	private static GameEventListener instance = null;

	protected GameEventListener() {
	}

	public void Initialize(Arena arena) {
		ok = true;
		mainArena = arena;
		gameWindow = new GameWindow(arena.getDim(), mainArena);
		gameWindow.registerKeyListener(this);
		gameWindow.setVisible(true);
		robots = mainArena.getGamers();

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
				gameWindow.draw(new CoordVector(3, 3), "");

				if (seconds == 0 && minutes == 0) {
					gameWindow.drawTime(minutes, seconds);
					mainArena.finishGame();
					exit();
				}
			}
		};

		timer = new Timer(1000, taskPerformer);
		timer.setRepeats(true);
		start();
	}

	public static GameEventListener getInstance() {
		if (instance == null) {
			instance = new GameEventListener();
		}
		return instance;
	}

	public void start() {
		seconds = 0;
		minutes = 2;
		timer.start();
	}

	public void exit() {
		timer.stop();
		ok = false;
		String messageString = "";
		if (robots.get(0).getPoints() < robots.get(1).getPoints()) {
			messageString += "Winner is:  " + robots.get(1).id;
		} else {
			if (robots.get(0).getPoints() > robots.get(1).getPoints()) {
				messageString += "Winner is:  " + robots.get(0).id;
			} else {
				messageString += "It's a Tie!";
			}
		}
		messageString += "\n";
		messageString += robots.get(0).id + " has: "
				+ Integer.toString(robots.get(0).getPoints()) + " points";
		messageString += "\n";
		messageString += robots.get(1).id + " has: "
				+ Integer.toString(robots.get(1).getPoints()) + " points";

		JOptionPane.showMessageDialog(gameWindow, messageString);
	}

	public void keyPressed(KeyEvent e) {

		if (robots.size() == 2) {
			switch (e.getKeyCode()) {
			// UP change speed (x,y)=>(x,y+1)
			case KeyEvent.VK_NUMPAD8:
				robots.get(1).stepUp();
				break;
			case KeyEvent.VK_W:
				robots.get(0).stepUp();
				break;

			// DOWN speed (x,y)=>(x,y-1)
			case KeyEvent.VK_NUMPAD2:
				robots.get(1).stepDown();
				break;
			case KeyEvent.VK_S:
				robots.get(0).stepDown();
				break;

			// LEFT speed (x,y)=>(x-1,y)
			case KeyEvent.VK_NUMPAD4:
				robots.get(1).stepLeft();
				break;
			case KeyEvent.VK_A:
				robots.get(0).stepLeft();
				break;

			// RIGHT speed (x,y)=>(x+1,y)
			case KeyEvent.VK_NUMPAD6:
				robots.get(1).stepRight();
				break;
			case KeyEvent.VK_D:
				robots.get(0).stepRight();
				break;

			// PUT OIL
			case KeyEvent.VK_NUMPAD7:
				robots.get(1).putOil();
				break;
			case KeyEvent.VK_Q:
				robots.get(0).putOil();
				break;

			// PUT PUTTY
			case KeyEvent.VK_NUMPAD9:
				robots.get(1).putPutty();
				break;
			case KeyEvent.VK_E:
				robots.get(0).putPutty();
				break;
			}
		}
	}

	private void processArena(ArrayList<Field> fields) {
		for (Field f : fields) {
			if (f.hasChanged) {
				if (f.hasRobi()) {
					gameWindow.draw(f.getCoord(), f.getRobotId().get(0));
				} else if (f.hasCleaner()) {
					gameWindow.draw(f.getCoord(), "CleanerMaster");
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
				} else if ("DangerZone".equals(f.getClass().getSimpleName())) {
					gameWindow.draw(f.getCoord(), "DangerZone");
				} else {
					gameWindow.draw(f.getCoord(), "SafeZone");
				}
				f.setHasChanged(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

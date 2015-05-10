package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;



//import java.util.Timer;
import javax.swing.Timer;

import main.Arena;
import main.CoordVector;
import main.Field;
import main.Patch;
import main.Robi;

public class GameEventListener implements KeyListener{

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
				gameWindow.draw(new CoordVector(3,3), "");
			}
		};

		timer = new Timer(1000, taskPerformer);
		timer.setRepeats(true);
		start();
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
		
		if (robots.size()>1){
			CoordVector old0 = robots.get(0).getSpeed();
			CoordVector old1 = robots.get(1).getSpeed();
			
			switch (e.getKeyCode()) {
			// UP change speed (x,y)=>(x,y+1)
			case KeyEvent.VK_NUMPAD8:
				robots.get(1).setSpeed(new CoordVector(old1.getX(), old1.getY()+1));
				break;
			case KeyEvent.VK_W:
				robots.get(0).setSpeed(new CoordVector(old0.getX(), old0.getY()+1));
				break;

			// DOWN speed (x,y)=>(x,y-1)
			case KeyEvent.VK_NUMPAD2:
				robots.get(1).setSpeed(new CoordVector(old1.getX(), old1.getY()-1));
				break;
			case KeyEvent.VK_S:
				robots.get(0).setSpeed(new CoordVector(old0.getX(), old0.getY()-1));
				break;

			// LEFT speed (x,y)=>(x-1,y)
			case KeyEvent.VK_NUMPAD4:
				robots.get(1).setSpeed(new CoordVector(old1.getX()-1, old1.getY()));
				break;
			case KeyEvent.VK_A:
				robots.get(0).setSpeed(new CoordVector(old0.getX()-1, old0.getY()));
				break;

			// RIGHT speed (x,y)=>(x+1,y)
			case KeyEvent.VK_NUMPAD6:
				robots.get(1).setSpeed(new CoordVector(old1.getX()+1, old1.getY()));
				break;
			case KeyEvent.VK_D:
				robots.get(0).setSpeed(new CoordVector(old0.getX()+1, old0.getY()));
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

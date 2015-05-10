package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Time;
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
	public static Timer timer;
	private ActionListener taskPerformer;
	private int seconds;
	private int minutes;


	public GameEventListener(Arena arena) {
		gameWindow = new GameWindow();
		gameWindow.setVisible(true);
		mainArena = arena;
		seconds = 0;
		minutes = 2;
		robots = arena.getRobots();
		timer = new Timer(1000, taskPerformer);
		timer.setRepeats(true);
		
		
		
		
		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("tick \n");	
				gameWindow.drawTime(minutes, seconds);
				if(seconds <= 0){
					minutes--;
					seconds = 59;
				}else {
					seconds--;
				}
				mainArena.tick();
				
			}
		};
	}

	public static void start() {
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

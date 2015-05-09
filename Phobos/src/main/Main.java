package main;

import java.awt.EventQueue;

import ui.GameEventListener;
import ui.GameWindow;

public class Main {
	static Arena mainArena;
	public static void main(String[] args){
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainArena = new Arena("@C:\\map.csv");
					GameWindow window = new GameWindow();
					GameEventListener GEL = new GameEventListener();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

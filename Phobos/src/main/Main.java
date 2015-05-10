package main;

import java.awt.EventQueue;

import ui.GameEventListener;
import ui.GameWindow;

public class Main {
	static Arena mainArena;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainArena = new Arena("asdasd", "map.csv");
					
					GameEventListener GEL = new GameEventListener(mainArena);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

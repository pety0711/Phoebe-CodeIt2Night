package main;

import java.awt.EventQueue;

import ui.GameEventListener;

public class Main {
	static Arena mainArena;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainArena = new Arena("asdasd", "big_map.csv");

					GameEventListener GEL = GameEventListener.getInstance();
					GEL.Initialize(mainArena);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

package ui;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.CoordVector;

public class GamePanel extends JPanel{

	Map<String, String> objectStyle;
	JTable gameArena;
	
	public GamePanel(){
		
		gameArena = new JTable();
		gameArena.setRowHeight(20);
		gameArena.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		this.add(gameArena);
	}
	
	public void draw(CoordVector coord, String type){
		
	}
}

package ui;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import main.CoordVector;

public class GamePanel extends JPanel{

	Map<String, String> objectStyle;
	JTable gameArena;
	
	public GamePanel(){
		
		gameArena = new JTable();
		gameArena.setRowHeight(15);
		gameArena.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		gameArena.setRowSelectionAllowed(false);
		gameArena.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		gameArena.getColumnModel().getColumn(0).setResizable(false);
		gameArena.getColumnModel().getColumn(0).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(1).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(2).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(3).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(4).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(5).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(6).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(7).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(8).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(9).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(10).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(11).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(12).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(13).setPreferredWidth(15);
		gameArena.getColumnModel().getColumn(14).setPreferredWidth(15);
		this.add(gameArena);
	}
	
	public void draw(CoordVector coord, String type){
		
	}
}

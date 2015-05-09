package ui;

import java.awt.Color;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import main.CoordVector;

public class GamePanel extends JPanel{

	private Map<String, String> objectStyle;
	private JTable gameArena;
	
	public GamePanel(){
		
		gameArena = new JTable();
		gameArena.setRowHeight(15);
		gameArena.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		gameArena.setRowSelectionAllowed(false);
		gameArena.setModel(new DefaultTableModel(
			new Object[][] {
				{"a", null, null, null, null, null, null, null, null, null, null, null, null, null, null},
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
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		JTextField textBox=new JTextField();
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
		gameArena.getColumnModel().getColumn(14).setPreferredWidth(15);/*
		TableColumn tc = gameArena.getColumnModel().getColumn(5);
		textBox.setBackground(Color.RED);
		tc.setCellEditor(new DefaultCellEditor(textBox));
		textBox.setBackground(Color.BLUE);*/
		this.add(gameArena);
		MyCellRenderer mcr = new MyCellRenderer();
		for (int columnIndex = 0; columnIndex < gameArena.getColumnCount(); columnIndex ++) {
		            gameArena.getColumnModel().getColumn(columnIndex).setCellRenderer(mcr);
		        }
	}
	public class MyCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
	    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        cellComponent.setBackground(java.awt.Color.YELLOW);
	        return cellComponent;
	    }
	}
	public void draw(CoordVector coord, String type){
		
	}
}

package ui;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import main.CoordVector;

public class GamePanel extends JPanel {

	private Map<String, String> objectStyle;
	private JTable gameArena;

	public GamePanel() {
		int height = 50;
		int width = 50;

		gameArena = new JTable();
		gameArena.setRowHeight(15);
		gameArena.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		gameArena.setRowSelectionAllowed(false);

		Object[][] model = new Object[height][width];
		String[] s = new String[width];
		boolean[] columnEditables = new boolean[width];

		for (int i = 0; i < height; i++) {
			Object[] col = new Object[width];
			for (int j = 0; j < width; j++) {
				col[j] = null; // Feltöltés null értékkekkel
			}
			model[i] = col; // sor hozzáadása
			s[i] = ""; // cellák tartalma üres legyen
			columnEditables[i] = false; // ne legyenek szerkeszthetõek
		}

		DefaultTableModel tableModel = new DefaultTableModel(model, s) {
			/*boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false };
*/
			boolean[] columnEditables = new boolean[] { true, true, true,
					true, true, true, true, true, true, true, true,
					true, true, true, true };
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		gameArena.setModel(tableModel);
		for (int i = 0; i < height; i++) {
			gameArena.getColumnModel().getColumn(i).setResizable(false);
			gameArena.getColumnModel().getColumn(i).setPreferredWidth(15);
		}

		this.add(gameArena);

	}
	
	/*
	public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
		  @Override
		  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		    //Cells are by default rendered as a JLabel.
		    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		    //Get the status for the current row.
		    CustomTableModel tableModel = (CustomTableModel) table.getModel();
		    if (tableModel.getStatus(row) == CustomTableModel.APPROVED) {
		      l.setBackground(Color.GREEN);
		    } else {
		      l.setBackground(Color.RED);
		    }

		  //Return the JLabel which renders the cell.
		  return l;

		}
*//*
	public class cellColorRenderer extends
			javax.swing.table.DefaultTableCellRenderer {
		public java.awt.Component getTableCellRendererComponent(
				javax.swing.JTable table, java.lang.Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			java.awt.Component cellComponent = super
					.getTableCellRendererComponent(table, value, isSelected,
							hasFocus, row, column);
			cellComponent.setBackground(java.awt.Color.YELLOW);
			return cellComponent;
		}
	}*/
		  /*
	  class CustomRenderer extends DefaultTableCellRenderer 
	  {
	      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	      {
	          Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	          c.setBackground(new java.awt.Color(255, 72, 72));
	          return c;
	      }
	  }*/
	/*
	  class CustomRenderer extends DefaultTableCellRenderer {
//http://stackoverflow.com/questions/2780573/change-table-cell-color-in-java	  
		    int col; 
		    int row;
		    public CustomRenderer (int col, int row) 
		    {
		       this.col = col;
		       this.row = row;
		    }
		    public Component getTableCellRendererComponent
		(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        Component c = super.getTableCellRendererComponent
		                          (table, value, isSelected, hasFocus, row, column);

		        setForeground( (column == this.col && row == this.row) 
		                                   ? Color.red : Color.black );
		        setBackground(Color.RED);
		        return c;
		    }
		}*/
	  class CustomRenderer extends DefaultTableCellRenderer 
	  {
	      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	      {
	          Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	          c.setBackground(Color.BLUE);
	          // Formatting here

	          return c;
	      }
	  }
	public void draw(CoordVector coord, String type) {
		//test
		gameArena.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());
		//model.setRowColour(1, Color.YELLOW);
		
		//gameArena.setDefaultRenderer(String.class, new CustomRenderer());
		// TODO coord megfeleltetése cellának
		// TODO string megfeleltetése színnek, pl SafeZone -> sárga? DangerZone
		// -> fekete? vagy akármi, stb...
		
	}
	static class MyTableModel extends DefaultTableModel {

	    List<Color> rowColours = Arrays.asList(
	        Color.RED,
	        Color.GREEN,
	        Color.CYAN
	    );

	    public void setRowColour(int row, Color c) {
	        rowColours.set(row, c);
	        fireTableRowsUpdated(row, row);
	    }

	    public Color getRowColour(int row) {
	        return rowColours.get(row);
	    }

	    @Override
	    public int getRowCount() {
	        return 3;
	    }

	    @Override
	    public int getColumnCount() {
	        return 3;
	    }

	    @Override
	    public Object getValueAt(int row, int column) {
	        return String.format("%d %d", row, column);
	    }
	}
	
	static class MyTableCellRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        MyTableModel model = (MyTableModel) table.getModel();
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        c.setBackground(model.getRowColour(row));
	        return c;
	    }
	}
	
	
}

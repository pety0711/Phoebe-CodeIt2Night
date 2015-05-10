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
				col[j] = null; // Felt�lt�s null �rt�kkekkel
			}
			model[i] = col; // sor hozz�ad�sa
			s[i] = ""; // cell�k tartalma �res legyen
			columnEditables[i] = false; // ne legyenek szerkeszthet�ek
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
			gameArena.getColumnModel().getColumn(i).setCellRenderer(createNewColorRenderer(Color.RED));
		}

		this.add(gameArena);

	}
	
	
	
	  class ColorRenderer extends DefaultTableCellRenderer 
	  {
		  private Color color;
		  public void setColor(Color cc){
			  color=cc;
		  }
		  
	      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	      {
	          Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	          c.setBackground(color);
	          // Formatting here

	          return c;
	      }
	  }
	  ColorRenderer createNewColorRenderer(Color color){
			ColorRenderer nColor = new ColorRenderer();
			nColor.setColor(color);
			return nColor;
		}
	public void draw(CoordVector coord, String type) {
		//test
		gameArena.getColumnModel().getColumn(1).setCellRenderer(createNewColorRenderer(Color.RED));
		//model.setRowColour(1, Color.YELLOW);
		
		//gameArena.setDefaultRenderer(String.class, new CustomRenderer());
		// TODO coord megfeleltet�se cell�nak
		// TODO string megfeleltet�se sz�nnek, pl SafeZone -> s�rga? DangerZone
		// -> fekete? vagy ak�rmi, stb...
		
	}
}

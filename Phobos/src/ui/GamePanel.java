package ui;

import java.awt.Color;
import java.awt.Component;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import main.CoordVector;

public class GamePanel extends JPanel {

	private Map<String, String> objectStyle;
	private JTable gameArena;

	int height = 15;
	int width = 15;

	public GamePanel(CoordVector coord) {
		height = coord.getY();
		width = coord.getX();
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
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false };

			/*
			 * boolean[] columnEditables = new boolean[] { true, true, true,
			 * true, true, true, true, true, true, true, true, true, true, true,
			 * true };
			 */
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		gameArena.setModel(tableModel);
		for (int i = 0; i < height; i++) {
			gameArena.getColumnModel().getColumn(i).setResizable(false);
			gameArena.getColumnModel().getColumn(i).setPreferredWidth(15);
		}
		gameArena.setDefaultRenderer(Object.class, new ColorRenderer());
		this.add(gameArena);
	}

	private class ColorRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, "",
					isSelected, hasFocus, row, column);
			if (value == null) {
				return c;
			}
			switch (((Character) value).toString().toUpperCase()) {
			case "S":
				c.setBackground(Color.GREEN);
				break;
			case "D":
				c.setBackground(Color.RED);
				break;
			case "O":
				c.setBackground(Color.ORANGE);
				break;
			case "P":
				c.setBackground(Color.BLACK);
				break;
			case "1":
				c.setBackground(Color.MAGENTA);
				break;
			case "0":
				c.setBackground(Color.CYAN);
				break;
			default:
				c.setBackground(Color.BLACK);
				break;
			}
			return c;
		}
	}

	public void draw(CoordVector coord, String type) {
		int x = coord.getX();
		int y = coord.getY();
		/*
		 * Component c = gameArena.getComponentAt(2,2);
		 * c.setBackground(Color.RED);
		 */

		/*
		 * gameArena.getColumnModel().getColumn(3).setCellRenderer(
		 * createNewColorRenderer(Color.RED));
		 * gameArena.getColumnModel().getColumn
		 * (5).setCellRenderer(createNewColorRenderer(Color.BLACK));
		 */
		if (!type.isEmpty()) {
			if (type.toLowerCase().startsWith("r")) {
				gameArena.setValueAt(type.charAt(type.length() - 1), height
						- coord.getY() - 1, coord.getX());
			} else {
				gameArena.setValueAt(type.charAt(0), height - coord.getY() - 1,
						coord.getX());
			}
		}

		gameArena.updateUI();
		// gameArena.setDefaultRenderer(String.class, new CustomRenderer());
		// TODO coord megfeleltetése cellának
		// TODO string megfeleltetése színnek, pl SafeZone -> sárga? DangerZone
		// -> fekete? vagy akármi, stb...

	}
}

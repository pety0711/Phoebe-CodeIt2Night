package ui;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
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
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false };

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
	}

	public void draw(CoordVector coord, String type) {
		// TODO coord megfeleltetése cellának
		// TODO string megfeleltetése színnek, pl SafeZone -> sárga? DangerZone
		// -> fekete? vagy akármi, stb...
	}
}

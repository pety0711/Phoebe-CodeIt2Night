package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
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
		setLayout(new GridLayout());
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
				col[j] = null; // Felt�lt�s null �rt�kkekkel
			}
			model[i] = col; // sor hozz�ad�sa
			s[i] = ""; // cell�k tartalma �res legyen
			columnEditables[i] = false; // ne legyenek szerkeszthet�ek
		}

		DefaultTableModel tableModel = new DefaultTableModel(model, s) {
			public boolean isCellEditable(int row, int column) {
				return false;
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

	public void registerKeyListener(KeyListener l) {
		this.addKeyListener(l);
		gameArena.addKeyListener(l);
	}

	/**
	 * The color distribution
	 */
	private class ColorRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, "",
					isSelected, hasFocus, row, column);
			setBorder(noFocusBorder);
			if (value == null) {
				return c;
			}
			switch (((Character) value).toString().toUpperCase()) {
			// SafeZone
			case "S":
				c.setBackground(Color.GREEN);
				break;

			// DangerZone
			case "D":
				c.setBackground(Color.RED);
				break;

			// Oil
			case "O":
				c.setBackground(Color.BLACK);
				break;

			// Mixed
			case "M":
				c.setBackground(Color.ORANGE.darker());
				break;

			// Putty
			case "P":
				c.setBackground(Color.ORANGE);
				break;

			// Player1
			case "1":
				c.setBackground(Color.MAGENTA);
				break;

			// Player0
			case "0":
				c.setBackground(Color.CYAN);
				break;

			// CleanerMaster
			case "C":
				c.setBackground(Color.WHITE);
				break;

			// Default
			default:
				c.setBackground(Color.BLACK);
				break;
			}
			return c;
		}
	}

	/**
	 * Here happens the field drawing
	 */
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
		// TODO coord megfeleltet�se cell�nak
		// TODO string megfeleltet�se sz�nnek, pl SafeZone -> s�rga? DangerZone
		// -> fekete? vagy ak�rmi, stb...

	}
}

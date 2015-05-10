package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Robi;

public class SideBar extends JPanel {

	private JLabel gamer;
	private JLabel points;
	private JLabel putty;
	private JLabel oil;
	private Image image;
	private JLabel imageLabel;

	private Robi player;

	private JLabel pointsValue;
	private JLabel puttySupply;
	private JLabel oilSupply;

	public SideBar() {

		gamer = new JLabel("GamerID");
		points = new JLabel("Points");
		putty = new JLabel("Putty");
		oil = new JLabel("Oil");

		pointsValue = new JLabel("0");
		puttySupply = new JLabel("0");
		oilSupply = new JLabel("0");

		image = (new ImageIcon("iron_man.jpg")).getImage();
		imageLabel = new JLabel();
		image = image.getScaledInstance(190, 280, 0);
		imageLabel.setIcon(new ImageIcon(image));

		init();
	}

	public SideBar(Robi robot) {

		player = robot;

		gamer = new JLabel(player.id);
		points = new JLabel("Points");
		putty = new JLabel("Putty");
		oil = new JLabel("Oil");

		pointsValue = new JLabel("0");
		puttySupply = new JLabel("0");
		oilSupply = new JLabel("0");

		if (robot.id.contains("1")) {
			image = (new ImageIcon("robi.png")).getImage();
			image = image.getScaledInstance(190, 190, 0);
		}

		else {
			image = (new ImageIcon("cyanRobi.png")).getImage();
			image = image.getScaledInstance(190, 190, 0);
		}
		imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(image));

		init();
	}

	public SideBar(String gamerID, int gamerPoints, int gamerPutty,
			int gamerOil, ImageIcon gamerImage) {

		gamer = new JLabel(gamerID);
		points = new JLabel("Points");
		putty = new JLabel("Putty");
		oil = new JLabel("Oil");

		pointsValue = new JLabel(Integer.toString(gamerPoints));
		puttySupply = new JLabel(Integer.toString(gamerPutty));
		oilSupply = new JLabel(Integer.toString(gamerOil));

		// image = gamerImage;
		imageLabel = new JLabel(new ImageIcon(image));

		init();
	}

	public void refreshDatas(int points, int oil, int putty) {

		this.pointsValue.setText(Integer.toString(points));
		this.oilSupply.setText(Integer.toString(oil));
		this.puttySupply.setText(Integer.toString(putty));

	}
	
	public void refreshPlayer(Robi newPlayer){
		this.player = newPlayer;
	}

	public void refreshDatas() {

		this.pointsValue.setText(Integer.toString(player.getPoints()));
		this.oilSupply.setText(Integer.toString(player.getOilSupply()));
		this.puttySupply.setText(Integer.toString(player.getPuttySupply()));

	}

	public void init() {

		// Ebbe helyezzük el egymás alá az információkat
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setMinimumSize(new Dimension(100, 100));
		verticalBox.setMaximumSize(new Dimension(100, 100));
		this.add(verticalBox);

		// Gamer label
		gamer.setAlignmentX(Component.CENTER_ALIGNMENT);
		gamer.setHorizontalTextPosition(SwingConstants.LEFT);
		gamer.setHorizontalAlignment(SwingConstants.CENTER);
		gamer.setMaximumSize(new Dimension(100, 25));
		gamer.setMinimumSize(new Dimension(100, 25));
		gamer.setSize(new Dimension(100, 25));
		gamer.setPreferredSize(new Dimension(100, 25));
		gamer.setFont(new Font("Calibri Light", Font.BOLD, 13));
		verticalBox.add(gamer);

		// Points és az értékét tároló horizontálisan tároló box
		Box pointBox = Box.createHorizontalBox();
		verticalBox.add(pointBox);

		// Points label
		points.setAlignmentX(Component.CENTER_ALIGNMENT);
		points.setFont(new Font("Calibri", Font.PLAIN, 12));
		points.setHorizontalAlignment(SwingConstants.TRAILING);
		points.setMinimumSize(new Dimension(48, 16));
		points.setMaximumSize(new Dimension(48, 16));
		points.setSize(new Dimension(48, 16));
		points.setPreferredSize(new Dimension(48, 16));
		pointBox.add(points);

		// Pontszám label
		pointsValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		pointsValue.setHorizontalAlignment(SwingConstants.CENTER);
		pointsValue.setFont(new Font("Calibri", Font.PLAIN, 13));
		pointsValue.setMinimumSize(new Dimension(48, 16));
		pointsValue.setMaximumSize(new Dimension(48, 16));
		pointsValue.setSize(new Dimension(48, 16));
		pointsValue.setPreferredSize(new Dimension(48, 16));
		pointBox.add(pointsValue);

		// Putty és az értékét tartalmazó Box
		Box puttyBox = Box.createHorizontalBox();
		verticalBox.add(puttyBox);

		// Putty label
		putty.setAlignmentX(Component.CENTER_ALIGNMENT);
		putty.setFont(new Font("Calibri", Font.PLAIN, 13));
		putty.setHorizontalAlignment(SwingConstants.TRAILING);
		putty.setMaximumSize(new Dimension(48, 16));
		putty.setMinimumSize(new Dimension(48, 16));
		putty.setSize(new Dimension(48, 16));
		putty.setPreferredSize(new Dimension(48, 16));
		puttyBox.add(putty);

		// Putty supply label
		puttySupply.setAlignmentX(Component.CENTER_ALIGNMENT);
		puttySupply.setFont(new Font("Calibri", Font.PLAIN, 13));
		puttySupply.setHorizontalAlignment(SwingConstants.CENTER);
		puttySupply.setMaximumSize(new Dimension(48, 16));
		puttySupply.setMinimumSize(new Dimension(48, 16));
		puttySupply.setSize(new Dimension(48, 16));
		puttySupply.setPreferredSize(new Dimension(48, 16));
		puttyBox.add(puttySupply);

		// Oil és az értékét tartalmazó Box
		Box oilBox = Box.createHorizontalBox();
		verticalBox.add(oilBox);

		// Oil label
		oil.setAlignmentX(Component.CENTER_ALIGNMENT);
		oil.setFont(new Font("Calibri", Font.PLAIN, 13));
		oil.setHorizontalAlignment(SwingConstants.TRAILING);
		oil.setMaximumSize(new Dimension(48, 16));
		oil.setMinimumSize(new Dimension(48, 16));
		oil.setSize(new Dimension(48, 16));
		oil.setPreferredSize(new Dimension(48, 16));
		oilBox.add(oil);

		// Oil supply label
		oilSupply.setAlignmentX(Component.CENTER_ALIGNMENT);
		oilSupply.setFont(new Font("Calibri", Font.PLAIN, 13));
		oilSupply.setHorizontalAlignment(SwingConstants.CENTER);
		oilSupply.setMaximumSize(new Dimension(48, 16));
		oilSupply.setMinimumSize(new Dimension(48, 16));
		oilSupply.setSize(new Dimension(48, 16));
		oilSupply.setPreferredSize(new Dimension(48, 16));
		oilBox.add(oilSupply);

		// Image-t tartalmazó label
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(imageLabel);

	}

}

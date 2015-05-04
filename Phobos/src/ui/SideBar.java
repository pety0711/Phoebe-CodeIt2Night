package ui;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SideBar extends JPanel{
	
	private JLabel gamer;
	private JLabel points;
	private JLabel putty;
	private JLabel oil;
	private ImageIcon image;
	JLabel imageLabel;
	
	private JLabel pointsValue;
	private JLabel puttySupply;
	private JLabel oilSupply;
	
	public SideBar(){
		
		gamer = new JLabel();
		points = new JLabel();
		putty = new JLabel();
		oil = new JLabel();
		image = new ImageIcon();		

		pointsValue = new JLabel();
		puttySupply = new JLabel();
		oilSupply = new JLabel();
		
		imageLabel = new JLabel(image);
		
/*		this.add(gamer);
		this.add(points);
		this.add(putty);
		this.add(oil);
		this.add(imageLabel);*/
		
		this.setVisible(true);
		
		init();
	}
	
	public SideBar(String gamerID, int gamerPoints, int gamerPutty, int gamerOil, ImageIcon gamerImage){
		
		gamer = new JLabel(gamerID);
		points = new JLabel(Integer.toString(gamerPoints));
		putty = new JLabel(Integer.toString(gamerPutty));
		oil = new JLabel(Integer.toString(gamerOil));
		image = gamerImage;
		
		imageLabel = new JLabel(image);
		
/*		this.add(gamer);
		this.add(points);
		this.add(putty);
		this.add(oil);
		this.add(imageLabel);*/
		
		this.setVisible(true);
	}	
	
	public void refreshDatas(int points, int oil, int putty){
		
		this.points.setText(Integer.toString(points));
		this.oil.setText(Integer.toString(oil));
		this.putty.setText(Integer.toString(putty));

	}
	
	public void init(){
		
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
		gamer.setFont(new Font("Calibri Light", Font.BOLD, 13));
		verticalBox.add(gamer);
		
		// Points és az értékét tároló box 
		Box pointBox = Box.createHorizontalBox();
		verticalBox.add(pointBox);
		
		// Points label
		points.setAlignmentX(Component.CENTER_ALIGNMENT);
		points.setFont(new Font("Calibri", Font.PLAIN, 12));
		points.setHorizontalAlignment(SwingConstants.TRAILING);
		points.setMinimumSize(new Dimension(48, 16));
		points.setMaximumSize(new Dimension(48, 16));
		pointBox.add(points);
		
		// Pontszám label
		pointsValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		pointsValue.setHorizontalAlignment(SwingConstants.CENTER);
		pointsValue.setFont(new Font("Calibri", Font.PLAIN, 13));
		pointsValue.setMinimumSize(new Dimension(48, 16));
		pointsValue.setMaximumSize(new Dimension(48, 16));
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
		puttyBox.add(putty);
		
		// Putty supply label
		puttySupply.setAlignmentX(Component.CENTER_ALIGNMENT);
		puttySupply.setFont(new Font("Calibri", Font.PLAIN, 13));
		puttySupply.setHorizontalAlignment(SwingConstants.TRAILING);
		puttySupply.setMaximumSize(new Dimension(48, 16));
		puttySupply.setMinimumSize(new Dimension(48, 16));
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
		oilBox.add(oil);
		
		// Oil supply label
		oilSupply.setAlignmentX(Component.CENTER_ALIGNMENT);
		oilSupply.setFont(new Font("Calibri", Font.PLAIN, 13));
		oilSupply.setHorizontalAlignment(SwingConstants.TRAILING);
		oilSupply.setMaximumSize(new Dimension(48, 16));
		oilSupply.setMinimumSize(new Dimension(48, 16));
		oilBox.add(oilSupply);
	
		// Image-t tartalmazó label
		verticalBox.add(imageLabel);
	
	}

}

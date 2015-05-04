package ui;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SideBar extends JPanel{
	
	JTextField gamer;
	JTextField points;
	JTextField putty;
	JTextField oil;
	ImageIcon image;
	
	public SideBar(){
		
		gamer = new JTextField();
		points = new JTextField();
		putty = new JTextField();
		oil = new JTextField();
		image = new ImageIcon();
		
		JLabel imageLabel = new JLabel(image);
		
		this.add(gamer);
		this.add(points);
		this.add(putty);
		this.add(oil);
		this.add(imageLabel);
		
		this.setVisible(true);
	}
	
	public SideBar(String gamerID, int gamerPoints, int gamerPutty, int gamerOil, ImageIcon gamerImage){
		
		gamer = new JTextField(gamerID);
		points = new JTextField(gamerPoints);
		putty = new JTextField(gamerPutty);
		oil = new JTextField(gamerOil);
		image = gamerImage;
		
		JLabel imageLabel = new JLabel(image);
		
		this.add(gamer);
		this.add(points);
		this.add(putty);
		this.add(oil);
		this.add(imageLabel);
		
		this.setVisible(true);
	}	
	
	public void refreshDatas(int points, int oil, int putty){
		
		this.points.setText(Integer.toString(points));
		this.oil.setText(Integer.toString(oil));
		this.putty.setText(Integer.toString(putty));

	}

}

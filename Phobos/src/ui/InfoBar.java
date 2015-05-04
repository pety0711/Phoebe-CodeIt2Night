package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoBar extends JPanel{

	JLabel time;
	
	public InfoBar(){
		
		time = new JLabel("Time");
		time.setFont(new Font("Calibri Light", Font.BOLD, 16));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(time);
		this.setVisible(true);
	}
	
	public void refreshTime(){
		
		time.setText(" Time ");

	}
}

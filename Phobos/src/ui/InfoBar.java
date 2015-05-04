package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoBar extends JPanel{

	JTextField time;
	
	public InfoBar(){
		
		time = new JTextField();
		this.add(time);
		this.setVisible(true);
	}
	
	public void refreshTime(){
		
		time.setText(" Time ");

	}
}

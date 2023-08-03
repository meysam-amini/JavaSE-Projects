package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class workPanel extends JPanel{

	public static final int wpWidth=400,wpHeight=300;
	
	
	public workPanel(){
		super();
		this.setPreferredSize(new Dimension(wpWidth, wpHeight));
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setOpaque(true);
	}
	
}

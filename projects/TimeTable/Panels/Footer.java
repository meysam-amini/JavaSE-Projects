package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TimeTable.DataAcsses.TimeTableManager;
import TimeTable.Main.Main;

public class Footer extends JPanel{


	private JButton[] b = new JButton[7];
//	private myPanel p;
	private JLabel L;
	private JLabel Lup;
	private JLabel Ldown;
    private JLabel P[]=new JLabel[7];
	

	public Footer() {
		super();
		//this.p = p
		this.setOpaque(true);
		this.setBounds(0, Main.height-90, Main.width, 90);
		this.setBackground(Color.darkGray);
		this.setLayout(null);
		
		
		
		L=new JLabel();
		L.setLayout(new FlowLayout());
		L.setPreferredSize(new Dimension(Main.width, 90));           //Bounds(0, 0, Main.width, 60);
		L.setBackground(Color.darkGray);
		L.setOpaque(true);
		
		
		Lup=new JLabel();
		Lup.setLayout(new FlowLayout());
		Lup.setBounds(0, 3, Main.width, 1);//setPreferredSize(new Dimension(Main.width, 100));           //Bounds(0, 0, Main.width, 60);
		Lup.setBackground(Color.lightGray);
		Lup.setOpaque(true);
		
		Ldown=new JLabel();
		Ldown.setLayout(new FlowLayout());
		Ldown.setBounds(0, 69, Main.width, 4); //setPreferredSize(new Dimension(Main.width, 100));           //Bounds(0, 0, Main.width, 60);
		Ldown.setBackground(Color.lightGray);
		Ldown.setOpaque(true);
		
		this.add(Ldown);
		this.add(Lup);
		set();

	}
int ci=1;
	public void set() {}
	
	

}

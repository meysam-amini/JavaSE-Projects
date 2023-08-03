package TimeTable.Main;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import TimeTable.Panels.AddNewCourse;
import TimeTable.Panels.itemPanel;
import TimeTable.Panels.AddNewCelas;
import TimeTable.Panels.timeTable;
import TimeTable.Panels.AddNewSakhteman;
import TimeTable.Panels.AddNewTeacher;
import TimeTable.Panels.AddNewTime;
import TimeTable.Panels.Footer;
import TimeTable.Panels.menuBar;

public class Main extends JFrame {

	public static final int width = 1200, height = 670;

	public static AddNewSakhteman s;
	public static AddNewCelas c;
	public static AddNewCourse addc;
	public static AddNewTeacher t;
	public static AddNewTime T;
	public static menuBar m;
	private static Main Main;
	public static itemPanel ip;
	public static Footer F;
	public static timeTable tt;
	
	public static JPanel MainPanel ;

	private JPanel p;

	public Main() throws SQLException, InterruptedException {
		s = new AddNewSakhteman();
		t = new AddNewTeacher();
		c=new AddNewCelas();
		addc = new AddNewCourse();
		
		tt=new timeTable();
		p = new JPanel();//(width, height);
		p.setBounds(0, 0, width, height);
		p.setLayout(null);
		p.setOpaque(true);
		p.setBackground(Color.white);
	
	
		
		
		F=new Footer();
		p.add(F);
		this.setBounds(100, 30, width, height);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ip = new itemPanel("Ostad", addc,c,t,s);
		T=new AddNewTime(ip);
		m = new menuBar(ip,tt,T);
		GoToForm(0);
		
		p.add(s);
		p.add(t);
		p.add(T);
        p.add(c);		
        p.add(addc);
        p.add(m);
        p.add(tt);
        p.add(ip);
	    this.add(p);
		
		p.repaint();
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, InterruptedException  {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		new Main();

	}

	

	public static Font MyFont(int i) {
		return new Font("Tahoma", 5, i);
	}

	public static void GoToForm(int a) {
		tt.setVisible(false);
	///	ip.setVisible(false);
		//t.disable();
		t.setVisible(false);
		//s.disable();
		s.setVisible(false);
		//addc.disable();
		addc.setVisible(false);
	//	c.disable();
		c.setVisible(false);
	//	T.disable();
		T.setVisible(false);
		

		switch (a) {
		
		case 0:
			
			ip.setVisible(true);
			break;
		
		case 1:
			//addc.enable();
			addc.set();
			addc.setVisible(true);
			break;

		case 2:
			//c.enable();
			c.set();
			c.setVisible(true);
			break;
		case 3:
			//t.enable();
			t.set();
			t.setVisible(true);
			break;

		case 4:
			//T.enable();
			T.set();
			T.setVisible(true);
			break;
			
			
		case 5:
			//T.enable();
			s.set();
			s.setVisible(true);
			break;
			
			
		case 6:
			//T.enable();
			tt.setVisible(true);
			break;
		}

	}
	
	
	public static void setItempanel(String p){
		ip.set(p,"");	
		}
}

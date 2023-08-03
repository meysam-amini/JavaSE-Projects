package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import TimeTable.DataAcsses.TimeTableManager;
import TimeTable.Main.Main;

public class menuBar extends JPanel implements ActionListener{

	private JButton[] b = new JButton[7];
//	private myPanel p;
	private JLabel L;
	private JLabel Lup;
	private JLabel Ldown;
	private itemPanel ip;
	private timeTable tt;
	private AddNewTime T;
	String w[] = { "اساتید", "دروس", "کلاسها", "زمانها", "جدول زمانی","ساختمانها", "درباره" };
    private JLabel P[]=new JLabel[7];
	

	public menuBar(itemPanel ip,timeTable tt,AddNewTime T) {
		super();
		//this.p = p
		this.ip=ip;
		this.tt=tt;
		this.T=T;
		this.setOpaque(true);
		this.setBounds(0, 0, Main.width, 100);
		this.setBackground(Color.darkGray);
		this.setLayout(null);
		
		
		
		L=new JLabel();
		L.setLayout(new FlowLayout());
		L.setBounds(550, 7, 700, 100);//setPreferredSize(new Dimension(Main.width, 100));           //Bounds(0, 0, Main.width, 60);
		L.setBackground(Color.darkGray);
		URL y = getClass().getResource("../images/header2.png");
		ImageIcon m= new ImageIcon(y);
		//L.setIcon(m);
		L.setOpaque(true);
		
		JLabel L2;
		L2=new JLabel();
		L2.setLayout(new FlowLayout());
		URL y2 = getClass().getResource("../images/header.png");
		ImageIcon mm = new ImageIcon(y2);
		L2.setIcon(mm);
		L2.setBounds(0, -2, 600, 100);//setPreferredSize(new Dimension(Main.width, 100));           //Bounds(0, 0, Main.width, 60);
		L2.setBackground(Color.darkGray);
		L2.setOpaque(true);
		this.add(L2);
		Lup=new JLabel();
		Lup.setLayout(new FlowLayout());
		Lup.setBounds(0, 3, Main.width, 1);//setPreferredSize(new Dimension(Main.width, 100));           //Bounds(0, 0, Main.width, 60);
		Lup.setBackground(Color.lightGray);
		Lup.setOpaque(true);
		
		Ldown=new JLabel();
		Ldown.setLayout(new FlowLayout());
		Ldown.setBounds(0, 97, Main.width, 1); //setPreferredSize(new Dimension(Main.width, 100));           //Bounds(0, 0, Main.width, 60);
		Ldown.setBackground(Color.lightGray);
		Ldown.setOpaque(true);
		
		this.add(Ldown);
		this.add(Lup);
		set();

	}
int ci=7;
	public void set() {

		for (int i = 6; i >=0; i--) {
			b[i] = new JButton();
			b[i].setActionCommand(w[i]); //setText("");
			b[i].setPreferredSize(new Dimension(78, 78));
			b[i].setBackground(Color.lightGray);
		    b[i].addActionListener(this);
		    b[i].setLayout(null);
			P[i] = new JLabel();
			P[i].setOpaque(false);
			P[i].setBounds(5, 2, 60, 77);  //PreferredSize(new Dimension(60, 60));;
			P[i].setBackground(Color.red);
			P[i].setLayout(new FlowLayout());
			URL y2 = getClass().getResource("../images/"+ci--+".png");
			ImageIcon mm = new ImageIcon(y2);
			P[i].setIcon(mm);
			b[i].add(P[i]);
			L.add(b[i]);
		}
this.add(L);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().matches("اساتید")){
			ip.set("Ostad","");
			Main.GoToForm(0);
			tt.TTFlag=0;

		}
		
		if (e.getActionCommand().matches("دروس")){
			ip.set("Cours","");
			Main.GoToForm(0);
			tt.TTFlag=0;

		}
		
		if (e.getActionCommand().matches("کلاسها")){
			ip.set("Celas","");
			Main.GoToForm(0);
			tt.TTFlag=0;

		}
		
		if( e.getActionCommand().matches("زمانها")){
			T.isEdit = false;
			Main.setItempanel("Time");
			T.setFirsts();
			T.timesCount = T.listOfTimes.size();
			Main.GoToForm(4);
			T.set();
			T.setForEdit();
			itemPanel.disableItems();
			tt.TTFlag=0;

		}
		
		if( e.getActionCommand().matches("ساختمانها")){
			ip.set("Sakhteman","");
			Main.GoToForm(0);
			tt.TTFlag=0;
		}
		
		
		if( e.getActionCommand().matches("جدول زمانی")){
		
			
			try 
			{
				
				if(tt.TTFlag==0)
				TimeTableManager.setMatrisLimits();
			tt.changeClassFlag=0;
			tt.startFlag=0;
			tt.setClassList();
			tt.setLists();
			tt.set();
			tt.TTFlag=1;
			Main.GoToForm(6);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
	
}

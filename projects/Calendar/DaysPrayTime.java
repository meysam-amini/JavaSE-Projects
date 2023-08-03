package Calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Date;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class DaysPrayTime extends JLabel {

	private static JButton t1[]; 
	private static JButton t2[]; 
	private static  JComponent L;
	private static  JComponent L2;
	public static JButton timer;
	private static JButton Date;
	private static JButton TZ;
	private JButton back;

	private static date d;
	
	public DaysPrayTime(){
		this.setOpaque(true);
		this.setBounds(0, 0, main.width, main.height);
		this.setLayout(null);
		 URL y2 = getClass().getResource("image/4.jpg"); ImageIcon mm = new
				 ImageIcon(y2); this.setIcon(mm);
				 
		////////////////////////
		
		timer = new JButton();
		timer.setBounds(60, 10, 100, 50);
		timer.setBackground(Color.black);
		timer.setForeground(Color.LIGHT_GRAY);
		timer.setOpaque(true);
		this.add(timer);
		////////////////////////
		Date = new JButton();
		Date.setBounds(160, 10, 130, 50);
		Date.setForeground(Color.LIGHT_GRAY);
		Date.setBackground(Color.black);
		Date.setOpaque(true);
		this.add(Date);
		////////////////////////////
		back = new JButton();
		back.setBounds(290, 10, 62, 50);
		back.setForeground(Color.LIGHT_GRAY);
		back.setBackground(Color.black);
		back.setText("Back");
		back.setOpaque(true);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				main.MainMenu();
				
			}
		});
		this.add(back);
		///////////////////////////
		TZ = new JButton();
		TZ.setBounds(60, 470, 300, 70);
		TZ.setForeground(Color.LIGHT_GRAY);
		TZ.setBackground(Color.black);
		TZ.setFont(new Font("tahoma", 1, 16));
		TZ.setOpaque(true);
		this.add(TZ);
		
		
		L=new JComponent() {
		};
		L.setBounds(60, 70, 150, 450);
		L.setLayout(new FlowLayout());
		L.setBackground(Color.red);
		L.setOpaque(true);
		this.add(L);
		////////////////////////
		L2=new JComponent() {
		};
		L2.setBounds(210, 70, 150, 450);
		L2.setLayout(new FlowLayout());
		L2.setBackground(Color.red);
		L2.setOpaque(true);
		this.add(L2);
		setdayspraytim();
		
	}

	public static void setdayspraytim() {
		
		//////////////////////
		t1=new JButton[13];
		for(int i=0;i<6;i++){
			t1[i]=new JButton();
			t1[i].setOpaque(true);
			t1[i].setPreferredSize(new Dimension(150, 60));
			t1[i].setBackground(Color.black);
			t1[i].setForeground(Color.LIGHT_GRAY);
			t1[i].setFont(new Font("tahoma",1, 18));
			L.add(t1[i]);
		}
		
		t2=new JButton[13];
		for(int i=0;i<6;i++){
			t2[i]=new JButton();
			t2[i].setOpaque(true);
			t2[i].setPreferredSize(new Dimension(150, 60));
			t2[i].setBackground(Color.black);
			t2[i].setForeground(Color.LIGHT_GRAY);
			t2[i].setFont(new Font("tahoma",1, 18));
			L2.add(t2[i]);
		}
		String s[]={"���� ���","����","���� ���","���","����","���� ����","���"};
		for(int i=0;i<6;i++)
		{
			t2[i].setText(s[i]);
		}
		
	}
	 
	public static void settimes(){

		dateConverter d=new dateConverter();
		d.PersianToGregorian(Month.d.getPyear(), Month.d.getPmonth(), Month.getCurrentPressedday());
		Date D=new Date(d.getYear(), d.getMonth(), d.getDay());
		System.out.println("Ptimes:"+D.toString());
		PrayTime P=new PrayTime();
		for(int i=0;i<6;i++)
		t1[i].setText(P.getOghat(D)[i]);
		Date.setText(Month.getCurrentPressedday()+" "+ Month.d.getMonthName()+" "+ Month.d.getPyear());
	TZ.setText("Time Zone:"+ TimeZon.CityName);
	}
	

}

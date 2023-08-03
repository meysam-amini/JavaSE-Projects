package Calendar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.management.loading.MLet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 * @author joker
 *
 */
public class Top extends JComponent implements ActionListener {
	private JButton Previus = new JButton();
	private JButton Next = new JButton();
	private static  JButton day;
	private JButton monthYear = new JButton();
	private JButton dayNum = new JButton();
	private int d = 0;
	public  static JButton dayname;
	public static JButton GDate;
	public static JButton timer;
	private static JButton MonthName;
	private Month M;
	public static date Date;
	private int c = 0;
	private JMenuBar menuBar=new JMenuBar();
	private JMenu menu=new JMenu();
	private static Allreminders PR;
	private Allnotes AN;
	private TimeZon TZ;
	private static Oghat O;
	public static JButton Gyear ;
	

	public Top(date da, Month m,Allreminders PR,Allnotes AN,TimeZon TZ,Oghat O) {
		this.Date = da;
		this.M = m;
		this.O=O;
		this.AN=AN;
		this.TZ=TZ;
		this.PR=PR;
		this.setTop();
		this.GDate.setText(Month.d.getGDate());
		this.Gyear.setText(Month.d.getMonthName()+"  "+Month.d.getPyear());
		this.dayname.setText(Month.DayName);

	}


	public void setTop() {
		
		dayname = new JButton();
		dayname.setBounds(225, 60, 110, 70);
		dayname.setOpaque(true);
		dayname.setFont(new Font("tahoma", 1, 15));
		dayname.setForeground(Color.lightGray);
		dayname.setBackground(Color.black);
		dayname.setText(M.DayName);
		//////////////////////////////////////////////////////////////////
		timer = new JButton();
		timer.setBounds(270, 0, 130, 55);
		timer.setOpaque(true);
		timer.setFont(new Font("tahoma", 1, 15));
		timer.setForeground(Color.LIGHT_GRAY);
		timer.setBackground(Color.black);
		
	    Gyear = new JButton();
		Gyear.setBounds(62, 0, 205, 55);
		Gyear.setOpaque(true);
		Gyear.setForeground(Color.LIGHT_GRAY);
		Gyear.setFont(new Font("tahoma", 1, 18));
		
		Gyear.setBackground(Color.black);
		this.add(Gyear);
		//PDate.setText("   "+M.getDayName());
		/////////////////////////////////////////////////////////////////
		GDate = new JButton();
		GDate.setBounds(63, 60, 110, 70);
		GDate.setOpaque(true);
		GDate.setFont(new Font("tahoma", 1, 15));
		GDate.setBackground(Color.black);
		GDate.setForeground(Color.lightGray);
		//GDate.setText("   "+M.getDayName());

		// ///////////////////////////////////////////////////////////////
		Next.setOpaque(true);
		Next.setBounds(340, 60, 60, 70);//
		Next.setBackground(Color.black);
		Next.setForeground(Color.lightGray);
		Next.setText("N");
		Next.setVisible(true);
		Next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				M.d.setNextMonth();
				try {
					M.setMonth();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GDate.setText(M.d.getGDate());
				Gyear.setText(M.d.getMonthName()+"  "+Month.d.getPyear());
			}
		});
		// ////////////////////////////////////////////////////////////////
		Previus.setOpaque(true);
		Previus.setBounds(0, 60, 59, 70);
		Previus.setBackground(Color.black);
		Previus.setForeground(Color.LIGHT_GRAY);
		Previus.setText("P");
		Previus.setVisible(true);
		
		Previus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				M.d.setPreviusMonth();
				try {
					M.setMonth();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GDate.setText(M.d.getGDate());
				Gyear.setText(M.d.getMonthName()+"  "+Month.d.getPyear());
			}
		});
		// ////////////////////////////////////////////////////////////////
		
		
		day = new JButton();
		day.setBounds(177, 60, 43, 70);
		day.setBackground(Color.black);
		day.setForeground(Color.yellow);
		day.setLayout(null);
		day.setOpaque(true);
		day.setText(""+M.getCurrentday());
		
		//////////////////////////////////////////////////////////////////
		JButton manage=new JButton();
		manage.setBounds(0, 1, 59, 55);
		manage.setBackground(Color.black);
		manage.setForeground(Color.yellow);
		manage.setLayout(null);
		manage.setOpaque(true);
		
		//////////////////////////////////////
		
		//////////////
		JMenuBar l1 = new JMenuBar();
		l1.setBounds(5, 8, 15, 15);
		l1.setLayout(null);

		l1.setOpaque(true);
		JMenu m = new JMenu();
		m.setOpaque(true);
		m.setBounds(-5, 0, 20, 15);
		URL y2 = getClass().getResource("image/arrow.png");
		ImageIcon mm = new ImageIcon(y2);
		m.setIcon(mm);
		
		JMenuItem notes = new JMenuItem("Notes");
		notes.addActionListener(this);
		JMenuItem reminder = new JMenuItem("Reminders");
		reminder.addActionListener(this);
		JMenuItem timeZone = new JMenuItem("Set TimeZone");
		timeZone.addActionListener(this);
		JMenuItem search = new JMenuItem("Search");
		search.addActionListener(this);
		m.add(notes);
		m.add(reminder);
		m.add(timeZone);
		m.add(search);
		l1.add(m);
		manage.add(l1);
		/////////////////////////////////////////////////////////////////////		
	
		this.add(manage);
		
		// ///////////////////////////////////////////////////////////////
		this.add(day);
		this.add(timer);
		this.add(GDate);
		this.add(dayname);
		this.add(Next);
		this.add(Previus);
		

	}

	
	public static void setcurrentDay(int a) {
		day.setText("     " + a);
	}
	public static void setdaynametext(String a){
		dayname.setText("   "+a);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().matches("Reminders"))
		{
			main.RemindersMenu();
			try {
				PR.setReminders();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().matches("Set TimeZone"))
		{
			main.TimeZonMenu();
			
		}
		if(e.getActionCommand().matches("Search"))
		{
			
		}
		
		if(e.getActionCommand().matches("Notes"))
		{
			main.AllNotesMenu();
			try {
				AN.setNotes();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void refreshReminders() throws Exception{
		PR.setReminders();
	}
	public static void refreshOghat(){
		O.setOghat();
	}
	public static void setdayname(String a){
		dayname.setText(a);
	}
	
	public static void set(){
		
	}
}

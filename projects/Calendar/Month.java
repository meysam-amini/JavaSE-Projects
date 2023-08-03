package Calendar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

public class Month extends JComponent implements MouseMotionListener,
		MouseListener,ActionListener {
	public static date d;
	public int dNums;
	public static int CurrentPDay;
	private static JMenu[] days;
	private JButton week[];
	private JComponent t;
	private Component k;
	public static int flag = 0;
	private static int startday;
	private JMenuBar P;
	private Top T;
	public static String DayName;
	private JButton b = new JButton();
	private JLabel b2;
	private JButton b3;
	private static Color daytextcolor = Color.orange;
	private int i = 0;
	private static String currentpresedDay;
	private NoteMenu N;
	private JButton Notifies;
	private JButton Notes;
	private Notifications Notifi;

	public Month(date da, NoteMenu N, Notifications Notifi) throws IOException {
		super();
		this.d = da;
		this.N = N;
		System.out.println(d.getGyear());
		this.Notifi=Notifi;
		P = new JMenuBar();
		this.add(P);
		this.setDnums();
		this.setstartday();
		this.setcurrentPday(0);
		
		
		this.setLayout(null);
		this.setMonth();
		Notifies = new JButton();
		Notifies.setOpaque(true);
		Notifies.setBackground(Color.black);
		Notifies.setBounds(0, 248, 400, 40);
		Notifies.setText("");
		Notifies.setFont(new Font("Tahoma", 7, 11));
		Notifies.setForeground(Color.WHITE);
		this.add(Notifies);
		
		Notes = new JButton();
		Notes.setOpaque(true);
		Notes.setBackground(Color.black);
		Notes.setBounds(0, 292, 400, 40);
		Notes.setText("");
		Notes.setFont(new Font("Tahoma", 7, 11));
		Notes.setForeground(Color.WHITE);
		this.add(Notes);
		
	}

	public void setMonth() throws IOException {

		P.disable();
		P.setVisible(false);
		P = new JMenuBar();
		P.addMouseMotionListener(this);
		//P.addMouseListener(this);
		/*
		 * URL y2 = getClass().getResource("image/5.jpg"); ImageIcon mm = new
		 * ImageIcon(y2); P.setIcon(mm);
		 */
		P.setBounds(0, 0, 400, 240);
		P.setLayout(null);
		P.setOpaque(true);
		
			
		
		String w[] = { "Fri", "Sat", "Sun", "Mon", "Tue", "Wed", "Thu" };
		int j = 0;
		week = new JButton[8];
		t = new JComponent() {
		};
		t.setLayout(new FlowLayout());
		t.setBounds(0, 0, 400, 50);
		for (int i = 0; i < 7; i++) {
			week[i] = new JButton();
			week[i].setOpaque(true);
			week[i].setBackground(Color.black);
			week[i].setPreferredSize(new Dimension(50, 35));
			week[i].setText(w[j++]);
			week[i].setFont(new Font("Arial", 7, 11));
			week[i].setForeground(Color.lightGray);
			t.add(week[i]);
		}
		P.add(t);

		days = new JMenu[42];
		for (i = 0; i < 42; i++) {

			days[i] = new JMenu();
			days[i].setText(null);
			days[i].setOpaque(true);
			days[i].setLayout(new FlowLayout());
			days[i].setBorderPainted(false);
			days[i].setFont(new Font("tahoma", 1, 12));
			days[i].setBackground(Color.black);
			days[i].setForeground(Color.lightGray);
			days[i].setFocusable(true);
			days[i].setVisible(true);
    
			JMenuItem note = new JMenuItem("New Note");
			note.addActionListener(this);
			JMenuItem prays = new JMenuItem("Pray Times");
			prays.addActionListener(this);
			JMenuItem notifi = new JMenuItem("New Notification");
			notifi.addActionListener(this);
			days[i].add(note);
			days[i].add(notifi);
			days[i].add(prays);
			days[i].addMouseListener(this);
			P.add(days[i]);

		}
		
		JLabel l = new JLabel();
		
		l.setBounds(0, 0, 400, 240);
		l.setLayout(null);
	//	P.setBorder(null);
		 /*URL y2 = getClass().getResource("image/2.jpg"); ImageIcon mm = new
				 ImageIcon(y2); l.setIcon(mm);*/
		l.setBackground(Color.black);
		l.setOpaque(true);
		Border raisedBorder = BorderFactory.createLineBorder(Color.lightGray);
		l.setBorder(raisedBorder);
		P.add(l);

		
		for (i = 0; i < 7; i++)
			days[i].setBounds(10 + (i * 55), 45, 50, 28);
		for (i = 7; i < 14; i++)
			days[i].setBounds(10 + ((i - 7) * 55), 78, 50, 28);
		for (i = 14; i < 21; i++)
			days[i].setBounds(10 + ((i - 14) * 55), 111, 50, 28);
		for (i = 21; i < 28; i++)
			days[i].setBounds(10 + ((i - 21) * 55), 144, 50, 28);
		for (i = 28; i < 35; i++)
			days[i].setBounds(10 + ((i - 28) * 55), 177, 50, 28);
		for (i = 35; i < 42; i++)
			days[i].setBounds(10 + ((i - 35) * 55), 210, 50, 28);
		this.add(P);
		this.setDnums();
		this.setstartday();
		this.setcurrentPday(0);
		setdaystext();
		setDayName(CurrentPDay);
		
		for(int i=startday;i<dNums;i++){
			if(days[i].getForeground()!=Color.DARK_GRAY)
				if(i%7==0)
					days[i].setForeground(Color.green);
		}
		
		MyFile f=new MyFile();
		int k=0;
		for(int i=startday-1;i<=dNums;i++){
			f.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Notifications/Notifications/"+ k+++ " " + d.getMonthName()
					+ " " + d.getPyear()+".txt");
			String s=""+f.getInfo();
			if(s.indexOf("(H)")!=-1){
				days[i].setForeground(Color.green);
				}
			
		}

	}

	public void setT(String s){
		String f="";
		int h=s.length()-2;
		while(s.charAt(h)!='=')
			f=s.charAt(h--)+f;
		h=toint(f);
		setcurrentPday(h);
		setDayName(h);
		currentpresedDay=f;
		
		
		
	}
	
	public void setDnums() {
		this.dNums = d.getMonthNum();
	}

	public void setstartday() {
		this.startday = d.getStartDay();
	}

	public void setcurrentPday(int a) {
		if (a == 0)
			this.CurrentPDay = d.getPday();
		else
			this.CurrentPDay = a;
	}

	public static int getCurrentday() {
		return CurrentPDay;
	}

	public static  int getCurrentPressedday() {
		return toint(currentpresedDay);
	}

	private static void  setDayName(int a) {
		a = a + startday - 1;
		if (days[a].getForeground()!=Color.DARK_GRAY) {
			if (a % 7 == 0)
				DayName = "Friday";
			if (a % 7 == 1)
				DayName = "Saturday";
			if (a % 7 == 2)
				DayName = "Sunday";
			if (a % 7 == 3)
				DayName = "Monday";
			if (a % 7 == 4)
				DayName = "Tuesday";
			if (a % 7 == 5)
				DayName = "Wednesday";
			if (a % 7 == 6)
				DayName = "Thursday";
		}
		
	}

	
	private void setdaystext() {
		int i = 0, c = 1;
		for (i = 0; i < dNums; i++) {
			days[i + startday].setText("" + (c++) + "");
			if (c == CurrentPDay)
				days[c - 1 + startday].setForeground(Color.yellow);
		}

		int k = 1;
		for (i = dNums + startday; i < 42; i++) {
			days[i].setText("" + k++);
			days[i].setForeground(Color.darkGray);
			days[i].disable();
		}
		k = startday;
		d.setPreviusMonth();
		c = d.getMonthNum() - k + 1;
		for (i = 0; i < startday; i++)
			if (days[i].getText() == null) {
				days[i].setText("" + c++);
				days[i].setForeground(Color.darkGray);
				days[i].disable();
			}

		d.setNextMonth();


	}

	
	private static int toint(String s) {
		int n = 0;
		for (int i = 0; i < s.length(); i++)
			n = n * 10 + (s.charAt(i) - '0');
		return n;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {


		if (e.getComponent().getForeground() != Color.darkGray){
			daytextcolor = Color.lightGray;
			}
		else
			daytextcolor = Color.darkGray;
		    
		
		if (daytextcolor != Color.DARK_GRAY) {
			setT(e.getComponent().toString());
		setDayName(toint(currentpresedDay));
		//
		try {
			if(e.getComponent().getForeground()==Color.green)
				this.Notifies.setText("holidays");
			else
			showNotification(getCurrentPressedday() + " " + d.getMonthName()
					+ " " + d.getPyear());
			showNote(getCurrentPressedday() + " " + d.getMonthName()
					+ " " + d.getPyear());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	}
	
	private void showNote(String s) throws IOException{
		//D:\Calendar files\Calendar Notes\Notes Text
		
		MyFile f=new MyFile();
		f.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Text/"+s+".txt");
		if(f.getInfo()!=null)
		this.Notes.setText(f.getInfo());
		
			
		
	}

	private void showNotification(String string) throws IOException {
		
		MyFile f2=new MyFile();
		f2.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Notifications/Notifications/"+string+".txt");
		if(f2.getInfo()!=null)
		this.Notifies.setText(f2.getInfo());
		
			
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.Notes.setText("no notes found");
		this.Notifies.setText("no notification found");

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().matches("New Note"))
			{
			try {
				main.NoteMenu();
				N.setDayOfNot(getCurrentPressedday() + " " + d.getMonthName()
						+ " " + d.getPyear());
				N.setAttachment();
				N.newText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			    
			}
		if(e.getActionCommand().matches("Pray Times"))
		{
			 main.PrayTimesMenu();
			 DaysPrayTime.settimes();
		}
		
		if(e.getActionCommand().matches("New Notification"))
		{
			main.NotificatoinMenu();
			try {
				Notifi.setDayOfNot(getCurrentPressedday() + " " + d.getMonthName()
							+ " " + d.getPyear());
				Notifi.newText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}

package Calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TimeZon extends JPanel implements ActionListener, MouseListener {

	private static JLabel city;
	private JLabel P = new JLabel();
	public static JButton Time;
	private JButton B[];
	private int cB;
	public static String cityname;
	public static String Long;
	public static String Zon;
	public static String Lat;
	private JButton importcity;

	private static double tool = 47;
	private static double arz = 36;
	private static double zon = 3.30;
	public static String CityName;

	public TimeZon() throws Exception {

		super();
		
		this.setBounds(0, 0, main.width, main.height);
		this.setOpaque(true);
		this.setBackground(Color.black);
		this.setLayout(null);
		Time = new JButton();
		Time.setBounds(25, 10, 100, 40);
		Time.setBackground(Color.black);
		Time.setForeground(Color.LIGHT_GRAY);
		Time.setOpaque(true);
		this.add(Time);
		this.set(0);
		this.setCities();
		this.setTimeZone();
		

	}
	public void set(int u) {

		P.setVisible(false);
		P = new JLabel();
		P.setOpaque(true);
		P.setBounds(0, 0, main.width, main.height);
		P.setBackground(Color.red);
		P.setLayout(null);
		URL y2 = getClass().getResource("image/4.jpg");
		ImageIcon mm = new ImageIcon(y2);
		P.setIcon(mm);
		this.add(P);
		// //////////////////////////////////////////
		JButton back = new JButton();
		back.setOpaque(true);
		back.setBackground(Color.black);
		back.setForeground(Color.LIGHT_GRAY);
		back.setBounds(300, 10, 94, 40);
		back.setText("Back");
		P.add(back);
		back.addActionListener(this);
		// /////////////////////////////////////////

		JButton Pr = new JButton();
		Pr.setOpaque(true);
		Pr.setBackground(Color.black);
		Pr.setForeground(Color.LIGHT_GRAY);
		Pr.setBounds(128, 10, 170, 40);
		Pr.setText("Choosing Time Zone");
		P.add(Pr);
		// ///////////////////////////////////////////

		JButton ex = new JButton();
		ex.setOpaque(true);
		ex.setBackground(Color.black);
		ex.setForeground(Color.LIGHT_GRAY);
		ex.setBounds(25, 55, 369, 40);
		ex.setText("City's name  (Longitude) (Latitude) (TimeZone)");
		P.add(ex);
		// //////////////////////////////////////////
		city = new JLabel();
		int height;

		height = u * 40;
		city.setText("");
		city.setBackground(Color.black);
		city.setPreferredSize(new Dimension(350, height + 2));
		city.setForeground(Color.lightGray);
		city.setLayout(new FlowLayout());
		city.setOpaque(true);
		JScrollPane pane2 = new JScrollPane(city,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setBorder(null);
		pane2.setForeground(Color.black);

		pane2.setBounds(30, 100, 360, 360);
		P.add(pane2);

		// //////////////////////////////////////

		importcity = new JButton();
		importcity.setOpaque(true);
		importcity.setBackground(Color.black);
		importcity.setForeground(Color.LIGHT_GRAY);
		importcity.setBounds(30, 465, 360, 40);
		importcity.setText("   Import new city");
		importcity.addActionListener(this);
		P.add(importcity);
		// ////////////////////////////////////////////
		this.add(P);
	}
	
	
	
	public void setTimeZone() throws Exception{
		MyFile f2=new MyFile();
		f2.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Time Zones/choosed.txt");
		String s="";
		s=f2.getInfo(1);
		setDetail(",="+s+",");
		set4(cityname,toint(Long),toint(Lat),StrToDouble(Zon));
		
	}
	
	
	

	public void setCities() throws Exception {
		MyFile f1 = new MyFile();
		f1.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Time Zones/Zones.txt");
		int c = f1.getLines();
		set(c);
		cB = c;

		MyFile f2=new MyFile();
		f2.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Time Zones/choosed.txt");
		;
		set(c + 1);
		B = new JButton[c + 1];
		for (int i = 1; i <= c; i++) {
			B[i] = new JButton ();
			if(f2.getInfo(1).compareTo(f1.getInfo(i))==0){
			JLabel l1 = new JLabel();
			l1.setBounds(279, 13, 5, 5);
			l1.setLayout(null);
			l1.setBackground(Color.yellow);
			l1.setOpaque(true);
			JLabel l2 = new JLabel();
			l2.setBounds(19, 13, 5, 5);
			l2.setLayout(null);
			l2.setBackground(Color.yellow);
			l2.setOpaque(true);
			B[i].add(l2);
			B[i].add(l1);}
			B[i].setLayout(null);
			B[i].setForeground(Color.LIGHT_GRAY);
			B[i].setBackground(Color.black);
			B[i].setPreferredSize(new Dimension(300, 30));
			B[i].setOpaque(true);
			B[i].setText(f1.getInfo(i));
			B[i].addMouseListener(this);
			B[i].addActionListener(this);

			city.add(B[i]);

		}

	}

	private void setDetail(String s) {
		int i = 0;
		cityname = "";
        Long="";
        Lat="";
        Zon="";
		while (s.charAt(i) != '(')
			i++;
		while (s.charAt(i) != '=')
			i--;
		i++;
		while(s.charAt(i)!='(')
			cityname+=s.charAt(i++);
		i++;
		while(s.charAt(i)!=')')
			Long+=s.charAt(i++);
		i+=2;
		while(s.charAt(i)!=')')
			Lat+=s.charAt(i++);
		i+=2;
		while(s.charAt(i)!=')')
			Zon+=s.charAt(i++);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setDetail(e.getComponent().toString());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().matches("Back")) {
			main.MainMenu();
		}

		if (e.getActionCommand().compareTo(cityname+"("+Long+")"+"("+Lat+")"+"("+Zon+")")==0) {
			MyFile f1=new MyFile();
			try {
				f1.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Time Zones/choosed.txt");
			f1.clear();
			f1.add(cityname+"("+Long+")"+"("+Lat+")"+"("+Zon+")");
			set4(cityname,toint(Long),toint(Lat),StrToDouble(Zon));
			setCities();
			Top.refreshOghat();
			
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getActionCommand().matches("   Import new city")){
			main.ImportCityMenu();
		}


	}

	public static double getTool() {
		return tool;
	}

	public static double getArz() {
		return arz;
	}

	public static double getZone() {
		return zon;
	}

	public static void set4(String c,double t,double a,double z){
		tool=t;
		arz=a;
		zon=z;
		CityName=c;
		
	}
	
	public static double StrToDouble(String s){
		  float a=0,b=0,u=0,c=(float) 0.1;int i=0,j=0;
		 int h=0;
		 for(int g=0;g<s.length();g++)
			 if(s.charAt(g)=='.')
				 h=1;
		  
		 if(h==1){
		  while(s.charAt(i)!='.')
				  a=a*10+s.charAt(i++)-'0';
			  
			  for(j=i+1;j<s.length();j++)
				  b=b*10+s.charAt(j)-'0';
			  for(i=0;i<j-1;i++)
				  c*=10;
			 
			  b=b/c;
		 
			 u= a+b;
			 }
		 
		 else
			 u=toint(s);
		 return u;
	  }

	private static int toint(String s) {
		int n = 0;
		for (int i = 0; i < s.length(); i++)
			n = n * 10 + (s.charAt(i) - '0');
		return n;
	}
}

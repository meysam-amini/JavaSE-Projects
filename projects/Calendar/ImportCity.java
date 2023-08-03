package Calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class ImportCity extends JLabel implements ActionListener {

	private TimeZon TZ;
	private JLabel P = new JLabel();
	public static JButton Time;
	public static String cityname;
	public static String Long;
	public static String Zon;
	public static String Lat;
	private JButton confirm;
	private JTextArea t;
	private JTextArea a;
	private JTextArea city;
	private JComboBox ZonChooser = new JComboBox();

	
	public  ImportCity(TimeZon TZ) {


		super();
		this.TZ=TZ;
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
		this.set();

	
		
	}
	
	
		public void set() {

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
			Pr.setText("Importing New TimeZon");
			P.add(Pr);
			// ///////////////////////////////////////////

			ZonChooser.setBackground(Color.black);
			Pr.setForeground(Color.LIGHT_GRAY);
			ZonChooser.setFont(new Font("nimbus", 1, 16));
			ZonChooser.setBounds(240, 70, 140, 50);
			for (int y = 1; y <= 12; y++) {
					ZonChooser.addItem("GMT +" + y);
			}
			for (double y = 3.30; y <12; y++) {
				ZonChooser.addItem("GMT +" + y);
		}
			P.add(ZonChooser);
			////////////////////////////////////////////
			t = new JTextArea();
			t.setBackground(Color.black);
			t.setFont(new Font("nimbus", 1, 14));
			t.setForeground(Color.LIGHT_GRAY);
			t.setBounds(240, 150, 140, 50);
			P.add(t);
			////////////////////////////////////////////////
			a = new JTextArea();
			a.setBackground(Color.black);
			a.setFont(new Font("nimbus", 1, 14));
			a.setForeground(Color.LIGHT_GRAY);
			a.setBounds(240, 230, 140, 50);
			P.add(a);
			//////////////////////////////////////////////////
			city = new JTextArea();
			city.setBackground(Color.black);
			city.setFont(new Font("nimbus", 1, 14));
			city.setForeground(Color.LIGHT_GRAY);
			city.setBounds(240, 310,140, 50);
			P.add(city);
			///////////////////////////////////////////////////
			JButton Z = new JButton();
			Z.setOpaque(true);
			Z.setBackground(Color.black);
			Z.setFont(new Font("nimbus", 1, 14));
			Z.setForeground(Color.LIGHT_GRAY);
			Z.setBounds(30, 72, 140, 50);
			Z.setText("Time Zone:");
			P.add(Z);
			///////////////////////////////////////////////////
			JButton T = new JButton();
			T.setOpaque(true);
			T.setBackground(Color.black);
			T.setFont(new Font("nimbus", 1, 14));
			T.setForeground(Color.LIGHT_GRAY);
			T.setBounds(30, 152, 140, 50);
			T.setText("Longitude:");
			P.add(T);
			///////////////////////////////////////////////////
			JButton A = new JButton();
			A.setOpaque(true);
			A.setBackground(Color.black);
			A.setForeground(Color.LIGHT_GRAY);
			A.setFont(new Font("nimbus", 1, 14));
			A.setBounds(30, 232, 140, 50);
			A.setText("Latitude:");
			P.add(A);
			/////////////////////////////////////////////////
			JButton C = new JButton();
			C.setOpaque(true);
			C.setBackground(Color.black);
			C.setForeground(Color.LIGHT_GRAY);
			C.setFont(new Font("nimbus", 1, 14));
			C.setBounds(30, 311, 140, 50);
			C.setText("City's Name:");
			P.add(C);
			// //////////////////////////////////////////
			

			confirm = new JButton();
			confirm.setOpaque(true);
			confirm.setBackground(Color.black);
			confirm.setForeground(Color.LIGHT_GRAY);
			confirm.setBounds(30, 425, 360, 60);
			confirm.setFont(new Font("nimbus", 1, 14));
			confirm.setText("Confirm");
			confirm.addActionListener(this);
			P.add(confirm);
			// ////////////////////////////////////////////
			this.add(P);
		}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().matches("Back")){
			main.TimeZonMenu();
		}
		if(e.getActionCommand().matches("Confirm")){
			try {
				saveNewZone();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	private  void saveNewZone() throws Exception{
		MyFile f=new MyFile();
		f.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Time Zones/Zones.txt");
		String s="";
		String Z="";
		for(int i=5;i<ZonChooser.getSelectedItem().toString().length();i++)
			Z+=ZonChooser.getSelectedItem().toString().charAt(i);
		s+=city.getText()+"("+t.getText()+")"+"("+a.getText()+")"+"("+Z+")";
		if(f.search(s)!=-1)
		{
			JOptionPane.showMessageDialog(null,
					"The Time Zone u entered is already saved !", "Title",
					JOptionPane.ERROR_MESSAGE);
		}
		
		else{
			f.add(s);
			JOptionPane.showMessageDialog(null,
					"The Time Zone u entered saved!", "Title",
					JOptionPane.INFORMATION_MESSAGE);
			TZ.setCities();
		}
	}

}

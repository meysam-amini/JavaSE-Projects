package Calendar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicArrowButton;

public class main extends JFrame {
	public static int width = 430;
	public static int height = 600;
	private JPanel Form;
	public static Top T;
	public static Month M;
	public static Oghat O;
	private JLabel p;
	private static date da;
	public static Allreminders PReminder;
	public static NoteMenu Notes;
	public static DaysPrayTime prays;
	public static ReminderDetail RDetail;
	public static NoteDetail NDetail;
	public static Allnotes AN;
	public static TimeZon TZ;
	public static ImportCity IC;
	public static Notifications Notifi;
	public static String FILES_ADDRESS = "/home/meysam";

	public main() throws Exception {
		super("Calendar");
		setLayout(new GridLayout(0, 5));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500, 100, width, height);
		this.setLayout(null);

		// ///////////////////////////////////////////////////
		p = new JLabel();
		p.setBounds(0, 0, width, height);
		p.setLayout(null);
		p.setOpaque(true);
		p.setBackground(Color.black);
		
		 URL y2 = getClass().getResource("image/4.jpg"); ImageIcon mm = new
		 ImageIcon(y2); p.setIcon(mm);
		 

		// ////////////////////////////////////////////////////
		Form = new JPanel();
		Form.setBounds(0, 0, width * 20, height * 20);
		Form.setLayout(null);
		Form.setOpaque(true);
		// //////////////////////////////////////////////////////
		prays = new DaysPrayTime();
		Form.add(prays);
		prays.setVisible(false);
		// /////////////////////////////////////////////////////
		Notes = new NoteMenu();
		Form.add(Notes);
		Notes.setVisible(false);
		// /////////////////////////////////////////////////////
		RDetail=new ReminderDetail();
		Form.add(RDetail);
		RDetail.setVisible(false);
		
		// /////////////////////////////////////////////////////
		PReminder = new Allreminders(RDetail);
		Form.add(PReminder);
		PReminder.setVisible(false);
		//////////////////////////////////////////////////////
		NDetail=new NoteDetail();
		Form.add(NDetail);
		NDetail.setVisible(false);
		//////////////////////////////////////////////////////
		AN=new Allnotes(NDetail);
		Form.add(AN);
		AN.setVisible(false);
		//////////////////////////////////////////////////////
		TZ=new TimeZon();
		Form.add(TZ);
		TZ.setVisible(false);
		//////////////////////////////////////////////////////
		IC=new ImportCity(TZ);
		Form.add(IC);
		IC.setVisible(false);
		//////////////////////////////////////////////////////
		Notifi=new Notifications();
		Form.add(Notifi);
		Notifi.setVisible(false);
		//////////////////////////////////////////////////////
		M = new Month(da, Notes,Notifi);
		M.setBounds(12, 150, 400, 345);
		M.setBackground(Color.black);
		M.setOpaque(true);
		Form.add(M);
		///////////////////////////////////////////////////////
		O = new Oghat();
		O.setBounds(7, 485, 410, 90);
		O.setBackground(Color.DARK_GRAY);
		O.setOpaque(true);
		Form.add(O);
		
		// ////////////////////////////////////////////////////
		T = new Top(da, M,PReminder,AN,TZ,O);
		T.setBounds(12, 10, 400, 150);
		T.setOpaque(true);
		T.setLayout(null);
		Form.add(T);
		// /////////////////////////////////////////////////////
		

		Form.add(p);
		this.add(Form);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		// setDefaultLookAndFeelDecorated(true);

		date d = new date();
		da = d;
		new main();
		Timer();
		
	}

	private static void Timer() {
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new DisplayTime(), 0, 1000);
	}

	public static void NoteMenu() {
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		PReminder.setVisible(false);
		AN.setVisible(false);
		prays.setVisible(false);
		Notes.setVisible(true);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

	}

	public static void MainMenu() {
		T.setVisible(true);
		O.setVisible(true);
		M.setVisible(true);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		PReminder.setVisible(false);
		AN.setVisible(false);
		prays.setVisible(false);
		Notes.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

	}

	public static void PrayTimesMenu() {
		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		AN.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(true);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

	}

	public static void RemindersMenu() {
		
		PReminder.setVisible(true);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		AN.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

	}
	
	public static void ReminderDetailMenu(){

		
		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(true);
		AN.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

	
	}
	
	public static void AllNotesMenu(){

		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		AN.setVisible(true);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

			
	}
	
	public static void NoteDetailMenu(){

		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(true);
		RDetail.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		AN.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(false);

	
	
	}
	
	public static void TimeZonMenu(){

		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		AN.setVisible(false);
		TZ.setVisible(true);
		IC.setVisible(false);
		Notifi.setVisible(false);

	
	
	}
	
	public static void ImportCityMenu(){

		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		AN.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(true);
		Notifi.setVisible(false);
	
	}
	
	
	public static void NotificatoinMenu(){

		PReminder.setVisible(false);
		T.setVisible(false);
		O.setVisible(false);
		M.setVisible(false);
		NDetail.setVisible(false);
		RDetail.setVisible(false);
		Notes.setVisible(false);
		prays.setVisible(false);
		AN.setVisible(false);
		TZ.setVisible(false);
		IC.setVisible(false);
		Notifi.setVisible(true);
	
	}
	
	

}

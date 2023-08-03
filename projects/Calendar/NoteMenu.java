package Calendar;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.JTextComponent;

public class NoteMenu extends JPanel implements ActionListener,MouseListener {

	private JTextArea text;
	private JButton save;
	private JButton back;
	public static JButton timer;
	private JButton DateForNot;
	private static String noteName;
	private MyFile f;
	private JCheckBox reminder = new JCheckBox();
	private JButton attach;
	private boolean Usreminder;
	private JPanel AttachedFiles;
	private JButton A[];
	private JComponent l = new JComponent() {
	};
	private int countAttached = 0;
	private JLabel P;
	private int u;
	private JComponent remind = new JComponent() {
	};
	private JScrollPane pane2;
	private JScrollPane pane;
	private JComboBox timeH = new JComboBox();
	private JComboBox timeM = new JComboBox();
	private JComboBox DateY = new JComboBox();
	private JComboBox DateM = new JComboBox();
	private JComboBox DateD = new JComboBox();
	private String fileAdressAttach = "";
	private String fileAdressReminds = "";
	private String fileAdressNoteName = "";
	private String fileAdressNoteText = "";
	private String filename;

	public NoteMenu() throws Exception {
		super();
		this.setBounds(0, 0, main.width, main.height);
		this.setOpaque(true);
		this.setBackground(Color.red);
		this.setLayout(null);
		P = new JLabel();
		// //////////////adding timer:

		timer = new JButton();
		timer.setBounds(25, 10, 100, 45);
		timer.setBackground(Color.black);
		timer.setForeground(Color.lightGray);
		timer.setOpaque(true);
		this.add(timer);
		setNoteMenu(0);

	}

	public void setNoteMenu(int u) throws Exception {

		P.setVisible(false);
		P = new JLabel();
		P.setOpaque(true);
		P.setBounds(0, 0, main.width, main.height);
		P.setLayout(null);
		URL y2 = getClass().getResource("image/4.jpg");
		ImageIcon mm = new ImageIcon(y2);
		P.setIcon(mm);
		this.add(P);

		// //////////////////////////////////adding text area writing new note:
		text = new JTextArea();
		text.setBackground(Color.black);
		text.setForeground(Color.LIGHT_GRAY);
		text.setBounds(50, 40, main.width - 100, main.height - 200);
		pane = new JScrollPane(text,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// pane.setBorder(null);

		pane.setBounds(30, 57, main.width - 60, main.height - 300);
		P.add(pane);
		// //////////////////////////////////adding DateForNote for showing date
		// of note:
		DateForNot = new JButton();
		DateForNot.setBackground(Color.black);
		DateForNot.setForeground(Color.lightGray);//25, 10, 100, 40
		DateForNot.setOpaque(true);
		DateForNot.setBounds(128, 10, 270, 45);
		P.add(DateForNot);
		// ////////////////////////////////////////////////////check box &&
		// combo boxes for reminder:
		remind=new JLabel() {
		};
		remind.setBounds(20, 445, 381, 46);
		remind.setOpaque(true);
		remind.setBackground(Color.black);
		remind.setLayout(null);

		reminder.setBounds(0, 20, 17, 13);
		reminder.setBackground(Color.black);
		reminder.setOpaque(true);
		reminder.setSelected(false);
		reminder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				setreminderboolean();

			}
		});

		remind.add(reminder);
		// ///////////////////////////////////////////////
		JButton r = new JButton();
		r.setBackground(Color.black);
		r.setForeground(Color.lightGray);//25, 10, 100, 40
		r.setOpaque(true);
		r.setBounds(18, 8, 90, 35);
		r.setText("Reminder:");
		remind.add(r);
		//////////////////////////////////////////////////

		timeH.setBackground(Color.black);
		timeH.setBounds(110, 8, 50, 35);
		for (int y = 0; y <= 23; y++) {
			if (y < 10)
				timeH.addItem("0" + y);
			else
				timeH.addItem("" + y);
		}

		timeM.setBackground(Color.black);
		timeM.setBounds(161, 8, 50, 35);
		for (int y = 0; y <= 59; y++) {
			if (y < 10)
				timeM.addItem("0" + y);
			else
				timeM.addItem("" + y);
		}

		remind.add(timeH);
		remind.add(timeM);

		DateY.setBackground(Color.black);
		DateY.setBounds(217, 8, 65, 35);
		for (int y = 1393; y <= 9999; y++)
			DateY.addItem("" + y);
		remind.add(DateY);

		DateM.setBackground(Color.black);
		DateM.setBounds(282, 8, 50, 35);
		for (int y = 1; y <= 12; y++) {
			if (y < 10)
				DateM.addItem("0" + y);
			else
				DateM.addItem("" + y);
		}
		remind.add(DateM);

		DateD.setBackground(Color.black);
		DateD.setBounds(332, 8, 50, 35);
		for (int y = 1; y <= 31; y++) {
			if (y < 10)
				DateD.addItem("0" + y);
			else
				DateD.addItem("" + y);
		}

		remind.add(DateD);

		P.add(remind);
		// ///////////////////////////////////////////////////attaching button
		attach = new JButton();
		attach.setBackground(Color.black);
		attach.setBounds(27, 361, 372, 30);
		attach.setOpaque(true);
		attach.setForeground(Color.LIGHT_GRAY);
		attach.setText("Attach file to note");
		attach.addActionListener(this);
		P.add(attach);

		// /////////////////////////////////////////////////for show attached
		// files

		AttachedFiles = new JPanel();
		AttachedFiles.setBackground(Color.black);
		if (u == 0)
			u = u + 50;
		AttachedFiles.setPreferredSize(new Dimension(330, u));
		AttachedFiles.setOpaque(true);
		l.setOpaque(true);
		AttachedFiles.add(l);
		l.setBackground(Color.black);
		AttachedFiles.setLayout(new FlowLayout());
		pane2 = new JScrollPane(AttachedFiles,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// pane2.setBorder(null);
		pane2.setBounds(30, 395, 367, 50);
		P.add(pane2);
		// ///////////////////////////////////adding save & back buttons:
		save = new JButton();
		save.setBackground(Color.black);
		save.setForeground(Color.LIGHT_GRAY);
		save.setBounds(30, main.height - 100, 170, 50);
		save.setOpaque(true);
		save.setText("Save");
		save.addActionListener(this);
		P.add(save);
		// ///////////////////
		back = new JButton();
		back.setBackground(Color.black);
		back.setForeground(Color.LIGHT_GRAY);
		back.setBounds(215, main.height - 100, 185, 50);
		back.setOpaque(true);
		back.setText("Back");
		back.addActionListener(this);
		P.add(back);

	}

	public void setDayOfNot(String d) throws Exception {
		DateForNot.setText("New note for: " + d);
		noteName = d;
		setAdreses();
	}

	public void setAdreses() {
		fileAdressAttach = main.FILES_ADDRESS+"/Calendar files/Calendar Attached Files/"
				+ noteName + ".txt";
		fileAdressReminds = main.FILES_ADDRESS+"/Calendar files/Calendar Reminders/Reminders.txt";
		fileAdressNoteName = main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Name/Notes Names.txt";
		fileAdressNoteText = main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Text/"
				+ noteName + ".txt";
	}

	public void setAttachment() throws Exception {

		f = new MyFile();
		f.SetFileAdress(fileAdressAttach);
		if (f.getLines() > 0)
			setAttachedFiles(1);
		if (f.getLines() == 0)
			setAttachedFiles(0);
	}

	public void newText() throws IOException {
		f = new MyFile();
		f.SetFileAdress(fileAdressNoteText);
		text.setText(f.getInfo());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("Attach file to note")) {

			JFileChooser chooser = new JFileChooser();
			File f1 = null;
			chooser.setSelectedFile(f1);
			chooser.showOpenDialog(null);
			File curFile = chooser.getSelectedFile();// /Calendar Attached Files
			f = new MyFile();
			try {
				f.SetFileAdress(fileAdressAttach);
				if (curFile != null)
					if (f.search(curFile.toString()) == -1)
						f.add(curFile.toString());

			} catch (IOException e1) {
			}

			try {
				if (curFile != null)
					setAttachedFiles(1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (e.getActionCommand().matches("Save")) {

			try {
				f = new MyFile();
				f.SetFileAdress(fileAdressNoteText);
				String s = text.getText();
				f.clear();
				f.add(s);
				f = new MyFile();
				f.SetFileAdress(fileAdressNoteName);
				if (f.search(noteName) == -1)
					f.add(noteName);
				if (getReminderBoolean() == false)
					JOptionPane.showMessageDialog(null, "Ur notes saved!",
							"Title", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if (getReminderBoolean()) {

				try {
					SaveReminder();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		if (e.getActionCommand().matches("Back")) {

			main.MainMenu();
		}

		
		if (e.getActionCommand().matches("show file")) {
			Desktop d = Desktop.getDesktop();

			try {

				d.open(new File(filename));
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().matches("delete file")) {
			MyFile f = new MyFile();
			try {
				f.SetFileAdress(fileAdressAttach);
				int r = f.search(filename);
				f.delete(r);
				setAttachedFiles(2);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
		
	}

	public void SaveReminder() throws Exception {
		String hour = timeH.getSelectedItem().toString(), Minut = timeM
				.getSelectedItem().toString(), year = DateY.getSelectedItem()
				.toString(), month = DateM.getSelectedItem().toString(), day = DateD
				.getSelectedItem().toString();
		Time reminderTime = new Time(toint(hour), toint(Minut), 0);
		String reminderDate = year + "-" + month + "-" + day;

		Date now = new Date(System.currentTimeMillis());
		Time nowtTime = new Time(System.currentTimeMillis());

		if (DateCompare(reminderDate, reminderTime.toString()) == 1) {

			f = new MyFile();
			f.SetFileAdress(fileAdressReminds);
			if (f.search(reminderDate + "|" + reminderTime.toString() + "|"
					+ noteName) == -1)
				f.add(reminderDate + "|" + reminderTime.toString() + "|"
						+ noteName);
			JOptionPane.showMessageDialog(null,
					"Ur notes & its reminder saved!", "Title",
					JOptionPane.INFORMATION_MESSAGE);
			Top.refreshReminders();

		} else {
			JOptionPane.showMessageDialog(null,
					"The date & time u selected is already past!", "Title",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static int DateCompare(String d1, String t1) {

		Time t = new Time(System.currentTimeMillis());
		Date d = new Date(System.currentTimeMillis());
		String t2 = t.toString();
		String d2 = d.toString();

		int d1Year = toint(cut(d1, 0, 3)), d1Month = toint(cut(d1, 5, 6)), d1Day = toint(cut(
				d1, 8, 9)), d2Year = toint(cut(d2, 0, 3)), d2Month = toint(cut(
				d2, 5, 6)), d2Day = toint(cut(d2, 8, 9));
		int t1Hour = toint(cut(t1, 0, 1)), t1Minut = toint(cut(t1, 3, 4)), t1Seconds = toint(cut(
				t1, 6, 7)), t2Hour = toint(cut(t2, 0, 1)), t2Minut = toint(cut(
				t2, 3, 4)), t2Seconds = toint(cut(t2, 6, 7));

		dateConverter Dc = new dateConverter();
		Dc.GregorianToPersian(d2Year, d2Month, d2Day);
		d2Year = Dc.getYear();
		d2Month = Dc.getMonth();
		d2Day = Dc.getDay();
		d2 = Dc.toString();// (d1.matches(d2)) && (t1.matches(t2))
		// t.toString().compareTo(PerformReminders.Rt[i])==0&&D.toString().compareTo(PerformReminders.Rd[i])==0
		if (d2.compareTo(d1) == 0 && t2.toString().compareTo(t1) == 0)
			return 0;
		else {

			/*
			 * System.out.println("d1Year:"+d1Year+"  d2Year:"+d2Year);
			 * System.out.println("d1Month:"+d1Month+"  d2Month:"+d2Month);
			 * System.out.println("d1Day:"+d1Day+"  d2Day:"+d2Day);
			 */

			if (d1Year < d2Year)
				return -1;
			if (d1Year == d2Year) {
				if (d1Month < d2Month)
					return -1;
				if (d1Month == d2Month) {
					if (d1Day < d2Day)
						return -1;
					if (d1Day == d2Day) {
						if (t1Hour < t2Hour)
							return -1;
						if (t1Hour == t2Hour) {
							if (t1Minut < t2Minut)
								return -1;
							if (t1Minut == t2Minut)
								if (t1Seconds < t2Seconds)
									return -1;

						}

					}
				}
			}

		}

		return 1;// 1 means date isn't past yes & -1 means date is already past
					// & 0 means d1 is now!
	}

	public void setAttachedFiles(int d) throws Exception {

		if (d == 0)
			l.setVisible(true);
		if (d != 0)
			l.setVisible(false);
		f = new MyFile();
		f.SetFileAdress(fileAdressAttach);
		int c = f.getLines() + 1;
		setNoteMenu(c * 40);
		setDayOfNot(Month.getCurrentPressedday() + " " + Month.d.getMonthName()
				+ " " + Month.d.getPyear());
		A = new JButton[c + 1];
		for (int i = 1; i < c; i++) {
			A[i] = new JButton();
			A[i].setBackground(Color.black);
			A[i].setForeground(Color.LIGHT_GRAY);
			A[i].setFont(new Font("tahoma", 1, 8));
			A[i].setLayout(null);
			A[i].setPreferredSize(new Dimension(300, 30));

			JMenuBar l1 = new JMenuBar();
			l1.setBounds(279, 8, 15, 15);
			l1.setLayout(null);

			l1.setOpaque(true);
			JMenu m = new JMenu();
			m.setOpaque(true);
			m.setBounds(-5, 0, 20, 15);
			URL y2 = getClass().getResource("image/arrow.png");
			ImageIcon mm = new ImageIcon(y2);
			m.setIcon(mm);
			JMenuItem play = new JMenuItem("show file");
			play.addActionListener(this);
			JMenuItem delete = new JMenuItem("delete file");
			delete.addActionListener(this);
			m.add(play);
			m.add(delete);
			l1.add(m);
			A[i].add(l1);

			A[i].setOpaque(true);
			if (c > 1)
				A[i].setText(f.getInfo(i));
			else
				A[i].setText("No files attached yet");
			A[i].addActionListener(this);
			A[i].addMouseListener(this);
			AttachedFiles.add(A[i]);

		}
	}

	public void setreminderboolean() {
		Usreminder = reminder.isSelected();
	}

	public boolean getReminderBoolean() {
		return Usreminder;
	}

	static String split(String a, char c) {
		String s1 = "";
		for (int x = 0; x < a.length(); x++)
			if (a.charAt(x) != c)
				s1 = s1 + a.charAt(x);
			else
				s1 = s1 + " ";
		return s1;
	}

	private static int toint(String s) {
		int n = 0;
		for (int i = 0; i < s.length(); i++)
			n = n * 10 + (s.charAt(i) - '0');
		return n;
	}

	private static String cut(String s, int a1, int a2) {

		String k = "";
		for (int i = a1; i <= a2; i++)
			k += s.charAt(i);

		return k;

	}
	
	
	private void setDetail(String s) {
		int i = 0;
		filename = "";

		while (s.charAt(i) != ':')
			i++;
		while (s.charAt(i) != '=')
			i--;
		i++;

		while (s.charAt(i) != ',')
			filename += s.charAt(i++);

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
}

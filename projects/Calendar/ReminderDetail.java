package Calendar;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

public class ReminderDetail extends JPanel implements ActionListener,
		MouseListener {
	private JTextArea text;
	private JButton OK;
	private JButton back;
	public static JButton timer;
	private JButton DateForNot;
	private static String noteName;
	private MyFile f;
	private JPanel AttachedFiles;
	private JButton attach;
	private JButton A[];
	private JLabel l = new JLabel("No files attached yet");
	private JLabel P;
	private int u;

	private JButton sTime;
	private JButton sDate;

	private JScrollPane pane2;
	private JScrollPane pane;

	public static int flag = 0;
	private String filename;

	private String fileAdressAttach = "";
	private String fileAdressReminds = "";
	private String fileAdressNote = "";

	public ReminderDetail() throws Exception {
		super();
		this.setBounds(0, 0, main.width, main.height);
		this.setOpaque(true);
		this.setBackground(Color.red);
		this.setLayout(null);
		P = new JLabel();

		// //////////////adding timer:

		timer = new JButton();
		timer.setBounds(28, 10, 100, 40);
		timer.setBackground(Color.black);
		timer.setForeground(Color.LIGHT_GRAY);
		timer.setOpaque(true);
		this.add(timer);

		set(0);

	}

	public void set(int u) {

		P.setVisible(false);
		P = new JLabel();
		P.setOpaque(true);
		P.setBounds(0, 0, main.width, main.height);
		P.setBackground(Color.black);
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
		text.setFocusable(false);
		pane.setBounds(30, 100, 357, main.height - 320);
		P.add(pane);
		// //////////////////////////////////adding DateForNote for showing date
		// of note:
		DateForNot = new JButton();
		DateForNot.setBackground(Color.black);
		DateForNot.setForeground(Color.LIGHT_GRAY);
		DateForNot.setOpaque(true);
		DateForNot.setBounds(128, 10, 257, 40);
		P.add(DateForNot);
		// /////////////////////////////////////////////////////////
		sTime = new JButton();
		sTime.setBackground(Color.black);
		sTime.setBounds(30, 52, 175, 45);
		sTime.setOpaque(true);
		sTime.setForeground(Color.LIGHT_GRAY);
		sTime.addActionListener(this);
		P.add(sTime);
		// ////////////////////////////////////////////
		sDate = new JButton();
		sDate.setBackground(Color.black);
		sDate.setBounds(210, 52, 175, 45);
		sDate.setOpaque(true);
		sDate.setForeground(Color.LIGHT_GRAY);
		sDate.addActionListener(this);
		P.add(sDate);
		// //////////////////////////////////////////////
		attach = new JButton();
		attach.setBackground(Color.black);
		attach.setBounds(30, 381, 360, 37);
		attach.setForeground(Color.LIGHT_GRAY);
		attach.setFont(new Font("nimbus", 1, 14));
		attach.setOpaque(true);
		attach.setText("  Attached files are: ");
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
		AttachedFiles.setForeground(Color.LIGHT_GRAY);
		l.setOpaque(true);
		AttachedFiles.add(l);
		l.setBackground(Color.black);
		AttachedFiles.setLayout(new FlowLayout());
		pane2 = new JScrollPane(AttachedFiles,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// pane2.setBorder(null);
		pane2.setBounds(30, 420, 360, 80);
		P.add(pane2);
		// ///////////////////////////////////adding save & back buttons:

		OK = new JButton();
		OK.setBackground(Color.black);
		OK.setBounds(30, 505, 150, 50);
		OK.setOpaque(true);
		OK.setForeground(Color.LIGHT_GRAY);
		OK.setText("OK");
		OK.addActionListener(this);
		P.add(OK);
		if (flag == 0)
			OK.setVisible(false);
		else
			OK.setVisible(true);
		// ///////////////////
		back = new JButton();
		back.setBackground(Color.black);
		back.setForeground(Color.LIGHT_GRAY);
		if (flag == 0)
			back.setBounds(30, 505, 360, 50);
		if (flag != 0)
			back.setBounds(215, 505, 150, 50);
		back.setOpaque(true);
		back.setText("Back");
		back.addActionListener(this);
		P.add(back);

	}

	public void setDayOfNot(String d) throws Exception {
		DateForNot.setText("Reminder note is for: " + d);
		sTime.setText("Alarm time: " + Allreminders.SavedTime);
		sDate.setText("Alarm date: " + Allreminders.SavedDate);
		noteName = d;
		setAdreses();
	}

	public void setAdreses() {
		fileAdressAttach = main.FILES_ADDRESS+"/Calendar files/Calendar Attached Files/"
				+ noteName + ".txt";
		fileAdressReminds = main.FILES_ADDRESS+"/Calendar files/Calendar Reminders/Reminders.txt";
		fileAdressNote = main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Text/" + noteName
				+ ".txt";
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
		f.SetFileAdress(fileAdressNote);
		text.setText(f.getInfo());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().matches("OK")) {
			Alarm.clip.stop();
		}
		if (e.getActionCommand().matches("Back")) {

			if (flag == 0)
				main.RemindersMenu();
			if (flag != 0)
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

	}

	public void setAttachedFiles(int d) throws Exception {

		if (d == 0)
			l.setVisible(true);
		if (d != 0)
			l.setVisible(false);
		f = new MyFile();
		f.SetFileAdress(fileAdressAttach);

		int c = f.getLines() + 1;
		set(c * 40);
		setDayOfNot(Allreminders.NoteName);
		A = new JButton[c + 1];
		for (int i = 1; i < c; i++) {
			A[i] = new JButton();
			A[i].setLayout(null);
			A[i].setBackground(Color.black);
			A[i].setFont(new Font("tahoma", 1, 8));
			A[i].setForeground(Color.LIGHT_GRAY);

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
			m.add(play);
			l1.add(m);
			A[i].add(l1);

			A[i].setPreferredSize(new Dimension(300, 30));
			A[i].setOpaque(true);
			if (c > 1)
				A[i].setText(f.getInfo(i));
			else
				A[i].setText("No files attached");
			A[i].addMouseListener(this);
			AttachedFiles.add(A[i]);

		}
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

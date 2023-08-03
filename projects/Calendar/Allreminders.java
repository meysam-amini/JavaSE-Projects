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
import java.util.Timer;

import javax.naming.ldap.Rdn;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Allreminders extends JLabel implements ActionListener,
		MouseListener {

	public static JButton Time;
	public static String Rd[];// reminders saved date
	public static String Rt[];// reminders saved time
	public static String Rnd[];// reminder saved note
	public static int cR;
	private static JButton B[];
	private static JLabel reminds;
	private JLabel P = new JLabel();
	public static String SavedDate = "";
	public static String SavedTime = "";
	public static String NoteName = "";
	private static ReminderDetail RDetail;

	public Allreminders( ReminderDetail RDetail) throws Exception {
		super();
		this.RDetail=RDetail;
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
		this.setbuttons(0);
		this.setReminders();

	}

	public void setbuttons(int u) {
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
		Pr.setText("Calendar Reminders");
		P.add(Pr);
		// ///////////////////////////////////////////

		JButton ex = new JButton();
		ex.setOpaque(true);
		ex.setBackground(Color.black);
		ex.setForeground(Color.LIGHT_GRAY);
		ex.setBounds(25, 55, 369, 40);
		ex.setText("Saved Date | Saved Time | Note Name");
		P.add(ex);
		// //////////////////////////////////////////
		reminds = new JLabel();
		int height;
		if (u == 0) {
			height = 40;
			reminds.setText("                                        No Reminders Found");
		} else {
			height = u * 40;
			reminds.setText("");
		}
		reminds.setBackground(Color.black);
		reminds.setPreferredSize(new Dimension(350, height + 2));
		reminds.setForeground(Color.lightGray);
		reminds.setLayout(new FlowLayout());
		reminds.setOpaque(true);
		JScrollPane pane2 = new JScrollPane(reminds,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setBorder(null);
		pane2.setForeground(Color.black);

		pane2.setBounds(30, 100, 360, 380);
		P.add(pane2);
		this.add(P);

	}

	public void setReminders() throws Exception {
		MyFile f = new MyFile();
		f.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Reminders/Reminders.txt");
		int c = f.getLines();
		setbuttons(c);
		cR = c;
		if (c == 0) {

		} else {
			Rd = new String[c + 1];
			Rt = new String[c + 1];
			Rnd = new String[c + 1];
			for (int i = 1; i <= c; i++) {
				Rd[i] = "" + cut(f.getInfo(i), 0, 9);
				Rt[i] = "" + cut(f.getInfo(i), 11, 18);
				Rnd[i] = "" + cut(f.getInfo(i), 20, f.getInfo(i).length() - 1);

			}

			setbuttons(c + 1);
			B = new JButton[c + 1];
			for (int i = 1; i <= c; i++) {
				B[i] = new JButton();
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
				JMenuItem detail = new JMenuItem("detail");
				detail.setBackground(Color.black);
				detail.addActionListener(this);
				JMenuItem delete = new JMenuItem("delete");
				delete.setBackground(Color.black);
				delete.addActionListener(this);
				m.add(detail);
				m.add(delete);
				l1.add(m);
				B[i].add(l1);
				B[i].setLayout(null);
				B[i].setForeground(Color.LIGHT_GRAY);
				B[i].setBackground(Color.black);
				B[i].setPreferredSize(new Dimension(300, 30));
				B[i].setOpaque(true);
				if (c > 0)
					B[i].setText(f.getInfo(i));
				else
					B[i].setText("No files attached yet");
				B[i].addMouseListener(this);

				reminds.add(B[i]);

			}

		}

	}

	private static String cut(String s, int a1, int a2) {

		String k = "";
		for (int i = a1; i <= a2; i++)
			k += s.charAt(i);

		return k;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().matches("Back")) {
			main.MainMenu();
		}

		if (e.getActionCommand().matches("delete")) {
			MyFile f = new MyFile();
			try {
				f.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Reminders/Reminders.txt");
				int r = f.search(SavedDate + "|" + SavedTime + "|" + NoteName);
				f.delete(r);
				setbuttons(cR - 1);
				setReminders();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

		if (e.getActionCommand().matches("detail")) {

			main.ReminderDetailMenu();
			try {
				RDetail.setDayOfNot(NoteName);
				RDetail.flag=0;
				RDetail.setAttachment();
				RDetail.newText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// this.currenteR=e.getComponent();
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
	
	public static void playreminder(int a) throws Exception{
		RDetail.setDayOfNot(NoteName);
		RDetail.flag=a;
		RDetail.setAttachment();
		RDetail.newText();
	}

	public static void setDetail(String s) {
		int i = 0;
		SavedDate = "";
		SavedTime = "";
		NoteName = "";

		while (s.charAt(i) != '|')
			i++;
		while (s.charAt(i) != '=')
			i--;
		i++;

		while (s.charAt(i) != '|')
			SavedDate += s.charAt(i++);
		i++;
		while (s.charAt(i) != '|')
			SavedTime += s.charAt(i++);
		i++;

		while (s.charAt(i) != ',')
			NoteName += s.charAt(i++);

	}

}

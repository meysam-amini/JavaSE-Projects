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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class NoteDetail extends JPanel implements MouseListener, ActionListener {
	private JTextArea text;
	private JButton back;
	public static JButton timer;
	private JButton DateForNot;
	private static String noteName;
	private MyFile f;
	private JLabel AttachedFiles;
	private JButton attach;
	private JButton A[];
	private JLabel P;

	private JScrollPane pane2;
	private JScrollPane pane;

	private String filename;

	private String fileAdressAttach = "";
	private String fileAdressNoteName = "";
	private String fileAdressNoteText = "";

	public NoteDetail() {

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
		pane.setBounds(30, 50, 357, main.height - 270);
		P.add(pane);
		// //////////////////////////////////adding DateForNote for showing date
		// of note:
		DateForNot = new JButton();
		DateForNot.setBackground(Color.black);
		DateForNot.setForeground(Color.LIGHT_GRAY);
		DateForNot.setOpaque(true);
		DateForNot.setBounds(128, 10, 260, 40);
		P.add(DateForNot);

		// //////////////////////////////////////////////
		attach = new JButton();
		attach.setBackground(Color.black);
		attach.setBounds(30, 385, 360, 37);
		attach.setForeground(Color.LIGHT_GRAY);
		attach.setFont(new Font("nimbus", 1, 14));
		attach.setOpaque(true);
		attach.setText("  Attached files are: ");
		attach.addActionListener(this);
		P.add(attach);

		// /////////////////////////////////////////////////for show attached
		// files

		AttachedFiles = new JLabel();
		AttachedFiles.setBackground(Color.black);
		if (u == 0)
			u = u + 50;
		AttachedFiles.setPreferredSize(new Dimension(330, u));
		AttachedFiles.setOpaque(true);
		AttachedFiles.setForeground(Color.LIGHT_GRAY);
		AttachedFiles.setLayout(new FlowLayout());
		pane2 = new JScrollPane(AttachedFiles,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// pane2.setBorder(null);
		pane2.setBounds(30, 425, 360, 80);
		P.add(pane2);

		// ///////////////////
		back = new JButton();
		back.setBackground(Color.black);
		back.setForeground(Color.LIGHT_GRAY);
		back.setBounds(30, 510, 360, 50);
		back.setOpaque(true);
		back.setText("Back");
		back.addActionListener(this);
		P.add(back);

	}

	public void setDayOfNot(String d) throws Exception {
		DateForNot.setText("The note is for: " + d);
		noteName = d;
		setAdreses();
	}

	public void setAdreses() {
		fileAdressAttach = main.FILES_ADDRESS+"/Calendar files/Calendar Attached Files/"
				+ noteName + ".txt";
		fileAdressNoteName = main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Name/Notes Names.txt";
		fileAdressNoteText = main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Text/"
				+ noteName + ".txt";
	}

	public void setAttachment() throws Exception {

		f = new MyFile();
		f.SetFileAdress(fileAdressNoteName);
	    setAttachedFiles();
		
	}

	public void newText() throws IOException {
		MyFile f2 = new MyFile();
		f2.SetFileAdress(fileAdressNoteText);
		if(f2.getLines()>0){
		f2.SetFileAdress(fileAdressNoteText);
		text.setText(f2.getInfo());}
		else
			text.setText("                                       No notes found!");
			
	}

	public void setAttachedFiles() throws Exception {
		
		f = new MyFile();
		f.SetFileAdress(fileAdressAttach);

		int c = f.getLines();
		set(c*50);
		setDayOfNot(Allnotes.notename);
		newText();
		A = new JButton[c + 1];
		for (int i = 1; i <=c; i++) {
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
			JMenuItem delete = new JMenuItem("delete file");
			delete.addActionListener(this);
			m.add(play);
			m.add(delete);
			l1.add(m);
			A[i].add(l1);

			A[i].setPreferredSize(new Dimension(300, 30));
			A[i].setOpaque(true);
			A[i].setText(f.getInfo(i));
			A[i].addMouseListener(this);
			AttachedFiles.add(A[i]);
			

		}if(c==0)
			AttachedFiles.setToolTipText("          No files attached yet");
		else
			AttachedFiles.setToolTipText("");
		
			
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
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("Back")) {

			main.AllNotesMenu();
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
				setAttachedFiles();
			} catch (Exception e1) {

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

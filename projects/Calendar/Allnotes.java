package Calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class Allnotes extends JLabel implements ActionListener,MouseListener {

	private static JLabel notes;
	private JLabel P = new JLabel();
	public static JButton Time;
	private JButton B[];
	private int cB;
	public static String notename;
	private NoteDetail ND;
	
	public  Allnotes(NoteDetail ND) throws Exception {
		super();
		this.ND=ND;
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
		this.setNotes();
	}
	
	public void set(int u){
		

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
		Pr.setText("  Calendar Notes");
		P.add(Pr);
		// ///////////////////////////////////////////

		JButton ex = new JButton();
		ex.setOpaque(true);
		ex.setBackground(Color.black);
		ex.setForeground(Color.LIGHT_GRAY);
		ex.setBounds(25, 55, 369, 40);
		ex.setText("  N O T E S :");
		P.add(ex);
		// //////////////////////////////////////////
		notes = new JLabel();
		int height;
		if (u <2) {
			height = 40;
			notes.setText("                                          No Notes Found");
		} else {
			height = u * 40;
			notes.setText("");
		}
		notes.setBackground(Color.black);
		notes.setPreferredSize(new Dimension(350, height + 2));
		notes.setForeground(Color.lightGray);
		notes.setLayout(new FlowLayout());
		notes.setOpaque(true);
		JScrollPane pane2 = new JScrollPane(notes,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// pane.setBorder(null);
		pane2.setForeground(Color.black);

		pane2.setBounds(30, 100, 360, 430);
		P.add(pane2);
		this.add(P);

	
		
	}
	
	
	
	
	public void setNotes() throws Exception {
		MyFile f1 = new MyFile();
		f1.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Name/Notes Names.txt");
		int c = f1.getLines();
		set(c);
		cB=c;

			set(c + 1);
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
				B[i].setText(f1.getInfo(i));
				B[i].addMouseListener(this);

				notes.add(B[i]);

			}

		}

	
	private void setDetail(String s) {
		int i = 0;
		notename="";

		while (s.charAt(i) != ' ')
			i++;
		while (s.charAt(i) != '=')
			i--;
		i++;

		while (s.charAt(i) != ',')
			notename += s.charAt(i++);
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

		if (e.getActionCommand().matches("delete")) {
			MyFile f = new MyFile();
			try {
				f.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Name/Notes Names.txt");
				int r = f.search(notename);
				f.delete(r);
				MyFile f2=new MyFile();
				f2.SetFileAdress(main.FILES_ADDRESS+"/Calendar files/Calendar Notes/Notes Text/"+notename+".txt");
				f2.clear();
				set(cB-1);
				setNotes();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

		if (e.getActionCommand().matches("detail")) {

			main.NoteDetailMenu();
			try {
				ND.setDayOfNot(notename);
				ND.setAttachment();
				ND.newText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		
	}

}}

package Calendar;

import java.awt.Checkbox;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Notifications extends JPanel implements ActionListener,MouseListener {
	
	private JTextArea text;
	private JButton save;
	private JButton back;
	public static JButton timer;
	private JButton DateForNot;
	private static String noteName;
	private JButton Holiday = new JButton();
	private JCheckBox isholiday=new JCheckBox();

	private JLabel P;
	private int u;

	private JScrollPane pane;

	private String fileAdressNotification = "";
	private String fileadress2="";//for notification's name
	private String filename;
	
	public Notifications(){
		
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
		set();
		
	}
	public void set(){


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

		pane.setBounds(30, 57, main.width - 65, main.height - 250);
		P.add(pane);
		// //////////////////////////////////adding DateForNote for showing date
		// of note:
		DateForNot = new JButton();
		DateForNot.setBackground(Color.black);
		DateForNot.setForeground(Color.lightGray);//25, 10, 100, 40
		DateForNot.setOpaque(true);
		DateForNot.setBounds(128, 10, 270, 45);
		P.add(DateForNot);
	
	//////////////////////////////////////////////////////is holiday or not
		Holiday = new JButton();
		Holiday.setBackground(Color.black);
		Holiday.setBounds(30, main.height - 190, 365, 50);
		Holiday.setOpaque(true);
		Holiday.setForeground(Color .lightGray);
		Holiday.setText("Is Holiday Or Not :");
		Holiday.setFont(new Font("tahoma", 1, 18));
		Holiday.setLayout(null);
		Holiday.addActionListener(this);
		
		isholiday=new JCheckBox();
		isholiday.setBackground(Color.darkGray);
		isholiday.setBounds(320, 20, 17, 13);
		isholiday.setOpaque(true);
		isholiday.setSelected(false);
		Holiday.add(isholiday);
		P.add(Holiday);		
		// ///////////////////////////////////adding save & back buttons:
		save = new JButton();
		save.setBackground(Color.black);
		save.setFont(new Font("tahoma", 1, 18));
		save.setBounds(30, main.height - 135, 185, 80);
		save.setOpaque(true);
		save.setText("Save");
		save.setForeground(Color .lightGray);
		save.addActionListener(this);
		P.add(save);
		// ///////////////////
		back = new JButton();
		back.setBackground(Color.black);
		back.setBounds(215, main.height - 135, 185, 80);
		back.setOpaque(true);
		back.setText("Back");
		back.setFont(new Font("tahoma", 1, 18));
		back.setForeground(Color .lightGray);
		back.addActionListener(this);
		P.add(back);

	
	}
	

	public void setDayOfNot(String d) throws Exception {
		DateForNot.setText("New note for: " + d);
		noteName = d;
		setAdreses();
	}

	public void setAdreses() {
				
		fileAdressNotification = main.FILES_ADDRESS+"/Calendar files/Calendar Notifications/Notifications/"+noteName+".txt";
		fileadress2=main.FILES_ADDRESS+"/Calendar files/Calendar Notifications/Notifications Name.txt";
	}
	
	public void newText() throws IOException {
		MyFile f = new MyFile();
		f.SetFileAdress(fileAdressNotification);
		String s="";
		if(f.getInfo().indexOf("(H)")!=-1)
			for(int i=0;i<f.getInfo().length()-5;i++)
				s+=f.getInfo().charAt(i);
		else
			s+=f.getInfo();
		text.setText(s);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	

		if (e.getActionCommand().matches("Save")) {

			try {
				MyFile f = new MyFile();
				f.SetFileAdress(fileAdressNotification);
				String s = text.getText();
				f.clear();
				if(isholiday.isSelected())
				f.add(s+"(H)");
				f = new MyFile();
				f.SetFileAdress(fileadress2);
				if (f.search(noteName) == -1)
					f.add(noteName);
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		

		}
		if (e.getActionCommand().matches("Back")) {

			main.MainMenu();
		}
		
	}

}

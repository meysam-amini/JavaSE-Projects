package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;

import TimeTable.Common.Celas;
import TimeTable.Common.Time;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.TimeManager;
import TimeTable.DataAcsses.TimeTableManager;
import TimeTable.DataAcsses.coursmanager;
import TimeTable.Main.Main;

public class timeTable extends JPanel implements ActionListener {

	private String days[] = { "شنبه", "یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه", "جمعه" };
	public  int TTFlag=0;

	private JLabel table[];
	private int ct;
	private JLabel tableBack = new JLabel();
	private JButton tt[][];
	int ctime;
	private ArrayList<Celas> C;
	private ArrayList<Celas> C2;
	private ArrayList<Time> T;
	private JComboBox<String> classes = new JComboBox<>();
	private JButton Print;
	private JButton showAllClasses;
	private JButton editTimeTable;
	private JScrollPane pane2 = new JScrollPane();
	public int changeClassFlag = 0;
	private editTimeTable ett=new editTimeTable();
	private JPanel P=new JPanel();
	

	public int startFlag = 0;

	public timeTable() throws SQLException {

		this.setBounds(0, 0, Main.width, Main.height);
		this.setOpaque(true);
		this.setLayout(null);
		this.setBackground(Color.white);
		setClassList();
		//////////////////////
		ett=new editTimeTable();
		this.add(ett);
		ett.setVisible(false);
		///////////////////////////////////////////////
	
	}
	
	public void setClassList(){
		ett.setVisible(false);
		P.disable();
		P.setVisible(false);
		P=new JPanel();
		P.setBounds(0, 0, Main.width, Main.height);
		P.setBackground(Color.white);
		P.setOpaque(true);
		P.setLayout(null);
		this.add(P);
		////////////////////////////////////////////////////
		classes.disable();
		classes.setVisible(false);
		classes = new JComboBox<>();
		classes.setBounds(1070, 130, 100, 40);
		classes.setActionCommand("changeClass");
		classes.setFont(Main.MyFont(13));
		classes.addActionListener(this);
		C2 = (ArrayList<Celas>) ClassManager.selectAll();
		for(int i=0;i<C2.size();i++)
			if((int)C2.get(i).getId()==18)
				C2.remove(i);
		for (int i = 0; i < C2.size(); i++)
			classes.addItem(C2.get(i).getNam());
		P.add(classes);
		/////////////////////////////////////////////
		Print = new JButton("تمام کلاسها");
		Print.setBounds(1070, 180, 100, 40);
		Print.addActionListener(this);
		Print.setFont(Main.MyFont(12));
		P.add(Print);
		///////////////////////////////////////////////
		showAllClasses = new JButton("چاپ برنامه");
		showAllClasses.setBounds(1070, 230, 100, 40);
		showAllClasses.addActionListener(this);
		showAllClasses.setFont(Main.MyFont(12));
		P.add(showAllClasses);
		//////////////////////////////////////////////
		editTimeTable = new JButton("ویرایش برنامه");
		editTimeTable.setBounds(1070, 280, 100, 40);
		editTimeTable.addActionListener(this);
		editTimeTable.setFont(Main.MyFont(12));
		P.add(editTimeTable);
		
	}

	public void setLists() throws SQLException {

		if (changeClassFlag == 0) {
			C = (ArrayList<Celas>) ClassManager.selectAll();
			for(int i=0;i<C.size();i++)
				if((int)C.get(i).getId()==18)
					C.remove(i);
			ct = ClassManager.getClassCount()-1;
		} else {

			C = (ArrayList<Celas>) ClassManager.selectCurrentClass(classes.getSelectedItem() + "");
			ct = 1;
		}
	}

	public void set() throws SQLException {
		int bw = 100, bh = 50;
		// setLists();
		T = (ArrayList<Time>) TimeManager.selectAll();
		ctime = TimeManager.getTimeCount() + 1;
		//////////////////////////////////
	
		
		///////////////////////////////////////////////
		
		////////////////////////////////////////////////

		table = new JLabel[ct];
		tt = new JButton[8][ctime];
		tableBack.disable();
		tableBack.setVisible(false);
		tableBack = new JLabel();
		tableBack.setOpaque(true);
		tableBack.setPreferredSize(new Dimension(((bw * 8) + 20) + 200, ct * (((ctime * bh) + 100) + 20)));
		tableBack.setLayout(new FlowLayout());
		tableBack.setBackground(Color.white);
		int co = 0;
		for (int x = 0; x < ct; x++) {
			table[x] = new JLabel();
			table[x].setPreferredSize(new Dimension((bw * 8) + 200, (ctime * bh) + 100));
			table[x].setBackground(Color.white);
			table[x].setOpaque(true);
			table[x].setLayout(null);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < ctime; j++) {
					tt[i][j] = new JButton();
					tt[i][j].setFont(Main.MyFont(12));
					tt[i][j].setBounds(i * (bw + 5) + 50, j * (bh + 5) + 30, bw, bh);// setPreferredSize(new
																						// Dimension(bw,
																						// bh));
					tt[i][j].setOpaque(false);
					tt[i][j].setText("");
					for (int h = 0; h < TimeTableManager.AllSelectedOlaviats.size(); h++)
						if (TimeTableManager.AllSelectedOlaviats.get(h).getCelasID() == C.get(x).getId() && i < 7
								&& i > 0)
							if (TimeTableManager.AllSelectedOlaviats.get(h).getRuz().matches(days[i - 1]) && j < 6
									&& j > 0)
								if (TimeTableManager.AllSelectedOlaviats.get(h).getTimesID() == T.get(j - 1).getId()) {
									if (tt[i][j].getText().length() >= 1&&coursmanager.getName(
											(int) TimeTableManager.AllSelectedOlaviats.get(h).getDarsID()).compareTo(tt[i][j].getText())!=0)
										tt[i][j].setText(tt[i][j].getText() + "/" + coursmanager.getName(
												(int) TimeTableManager.AllSelectedOlaviats.get(h).getDarsID()));
									else
										tt[i][j].setText(coursmanager.getName(
												(int) TimeTableManager.AllSelectedOlaviats.get(h).getDarsID()));

								}
					tt[i][j].setBackground(Color.lightGray);
					if (j == 0 && i > 0) {
						tt[i][j].setBackground(Color.darkGray);
						tt[i][j].setForeground(Color.white);
						tt[i][j].setText(days[i - 1]);
						tt[i][j].setFont(Main.MyFont(17));

					}
					if (i == 0) {
						tt[i][j].setBackground(Color.gray);
						tt[i][j].setForeground(Color.black);
						tt[i][j].setText("" + j);
						tt[i][j].setFont(Main.MyFont(17));
					}
					if (i == 0 && j == 0) {
						tt[i][j].setBackground(Color.lightGray);
						tt[i][j].setForeground(Color.DARK_GRAY);
						tt[i][j].setText(C.get(x).getNam());
					}

					table[x].add(tt[i][j]);
				}
			}
			tableBack.add(table[x]);
		}
		pane2.disable();
		pane2.setVisible(false);
		pane2 = new JScrollPane(tableBack, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// pane.setBorder(null);

		pane2.setOpaque(true);
		pane2.setBounds(50, 120, 1000, 450);// setPreferredSize(new
											// Dimension(620, 400));
		P.add(pane2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("چاپ برنامه")) {
			PrintUtilities.printComponent(tableBack);
		}
		
		
		if (e.getActionCommand().matches("ویرایش برنامه")) {
				P.setVisible(false);
			try {
				ett.setMatrix();
				ett.set();
				TTFlag=1;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ett.setVisible(true);
			Main.GoToForm(6);
		
			
		}

		if (e.getActionCommand().matches("تمام کلاسها")) {
			changeClassFlag=0;
			
			try {
				setLists();
				set();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().matches("changeClass")) {

			if (startFlag == 0) {
				classes.removeAllItems();
				classes.addItem(C2.get(0).getNam());
				startFlag = 1;
			}
			changeClassFlag = 1;
			try {
				setLists();
				set();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

	}

}

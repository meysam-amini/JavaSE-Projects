package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import TimeTable.Common.Celas;
import TimeTable.Common.DayTime;
import TimeTable.Common.Time;
import TimeTable.Common.Vorudi;
import TimeTable.Common.Time;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.DayTimeManager;
import TimeTable.DataAcsses.TimeManager;
import TimeTable.DataAcsses.vorudiManager;
import TimeTable.Main.Main;

public class AddNewTime extends JLabel implements ActionListener {

	private myPanel p;
	private JTextField start = new JTextField("ابتدا");
	private JTextField end = new JTextField("انتها");

	private JLabel Label[] = new JLabel[8];
	private JTextField Starttexts[] = new JTextField[8];
	private JTextField Endtexts[] = new JTextField[8];
	private JLabel namLabel = new JLabel();
	private String w[] = { "ابتدا :", "انتها :" };
	private JPanel Inputs;
	private JButton b1 = new JButton();
	private JButton c1 = new JButton();
	private int font = 13;
	private int width = 800, height = 450;
	private Time t;
	public boolean isEdit;
	public Time currentTime;
	private boolean backFlag;

	private JLabel starts;
	private JLabel ends;
	public int timesCount = 1;
	private int AddCount = 0;
	private int DeleteCount = 0;
	private int currentTimeCount = 0;
	private JButton SE[] = new JButton[timesCount];

	private JComboBox startSaat[] = new JComboBox[timesCount];
	private JComboBox startDaghighe[] = new JComboBox[timesCount];
	private JComboBox endSaat[] = new JComboBox[timesCount];
	private JComboBox endDaghighe[] = new JComboBox[timesCount];
	public ArrayList<Time> listOfTimes;
	private ArrayList<DayTime> listOfDayTime;

	private JPanel weekTimes;
	private JLabel weekTimesBack;
	private JButton WT[][] = new JButton[7][7];
	private int wt[][] = new int[7][7];
	private JLabel weekDays;
	private int chekBoxesWidth = 70;
	private int chekBoxesHeight = 55;

	private itemPanel ip;

	private String days[] = { "شنبه", "یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه", "جمعه" };

	private int currentVorudi;
	private JLabel vorudiBack;

	public AddNewTime(itemPanel ip) {

		super();
		// this.setClosable(true);
		this.ip = ip;
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(250, 120, Main.width, Main.height);
		this.setOpaque(true);
		Inputs = new JPanel();
		starts = new JLabel();
		ends = new JLabel();
		weekTimes = new JPanel();
		weekTimesBack = new JLabel();
		weekTimes.add(weekTimesBack);
		this.setBackground(Color.white);
		this.add(weekTimes);
		// this.setResizable(true);
		this.add(Inputs);
		// this.setOpaque(false);
		this.setLayout(null);
		this.setVisible(true);
		vorudiBack = new JLabel();
		this.add(vorudiBack);
		vorudiBack.setVisible(false);
		setFirsts();
		timesCount = currentTimeCount;
		set();
		setForEdit();

		this.setVisible(true);
	}

	private JComboBox<Object> vorudi = new JComboBox<>();
	private ArrayList<Vorudi> C2;
	private JButton newVorudi = new JButton();
	private JButton deleteVorudi = new JButton();

	private void setVorudiList() {
		vorudiBack.setEnabled(false);
		vorudiBack.setVisible(false);
		vorudiBack.setBounds(780, 50, 150, 300);
		vorudiBack.setLayout(null);
		vorudiBack.setVisible(true);
		this.add(vorudiBack);
		////////////////////////////////////////////////////
		vorudi.disable();
		vorudi.setVisible(false);
		vorudi = new JComboBox<>();
		vorudi.setBounds(10, 10, 120, 40);
		vorudi.setActionCommand("changeVorudi");
		vorudi.setFont(Main.MyFont(13));
		vorudi.addActionListener(this);
		System.out.println("befor");
		C2 = (ArrayList<Vorudi>) vorudiManager.selectAll();
		System.out.println("after");
		for (int i = 0; i < C2.size(); i++)
			vorudi.addItem(C2.get(i).getSal());
		vorudiBack.add(vorudi);
		/////////////////////////////////////////////
		newVorudi = new JButton("افزودن ورودی جدید");
		newVorudi.setBounds(10, 60, 120, 40);
		newVorudi.addActionListener(this);
		newVorudi.setFont(Main.MyFont(11));
		vorudiBack.add(newVorudi);
		///////////////////////////////////////////////
		deleteVorudi = new JButton("حذف ورودی ها");
		deleteVorudi.setBounds(10, 110, 120, 40);
		deleteVorudi.addActionListener(this);
		deleteVorudi.setFont(Main.MyFont(11));
		vorudiBack.add(deleteVorudi);
		//////////////////////////////////////////////
	}

	public void set2() {
		itemPanel.disableItems();
		backFlag = true;
		Inputs.setVisible(false);
		weekTimes.disable();
		weekTimes.setVisible(false);
		weekTimes = new JPanel();
		weekTimes.setBounds(0, 0, width, height);
		weekTimes.setLayout(null);
		weekTimes.setBackground(Color.white);
		weekTimes.setOpaque(true);

		/////////////////////////////////////////////
		setChekBoxesSiz(timesCount);
		//////////////////////////////////////////////
		weekTimesBack.disable();
		weekTimesBack.setVisible(false);
		weekTimesBack = new JLabel();
		weekTimesBack.setBounds(50, 10, width - 195, height - 125); // setPreferredSize(new
																	// Dimension(width
																	// - 105,
																	// height -
																	// 125));
		weekTimesBack.setLayout(new FlowLayout());
		weekTimesBack.setBackground(Color.white);
		weekTimesBack.setOpaque(true);
		// weekTimes.add(weekTimesBack);
		weekDays = new JLabel();
		weekTimesBack.add(weekDays);
		///////////////////////////////////////////////
		WT = new JButton[9][timesCount + 2];
		int i = 0;
		int j = 0;
		for (i = 1; i <= 8; i++)
			for (j = timesCount + 1; j >= 2; j--) {
				WT[i][j] = new JButton();
				WT[i][j].setPreferredSize(new Dimension(chekBoxesWidth, chekBoxesHeight));
				WT[i][j].setBackground(Color.green);
				// WT[i][j].setText(j+"");
				WT[i][j].setFont(Main.MyFont(10));
				if (j != 1 && i != 1)
					WT[i][j].addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {

							((AbstractButton) e.getComponent()).setText("");
							if (e.getComponent().getBackground() == Color.lightGray
									|| e.getComponent().getBackground() == Color.red)
								e.getComponent().setBackground(Color.green);
							else
								e.getComponent().setBackground(Color.red);

						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseClicked(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
				// WT[i][j].setOpaque(true);
				weekTimesBack.add(WT[i][j]);

			}

		for (i = 2; i <= timesCount + 1; i++) {
			WT[1][i].setText(i - 1 + "");
			WT[1][i].setBackground(Color.white);
		}
		// 10, 10,width - 105, height - 125
		weekDays.disable();
		weekDays.setVisible(false);
		weekDays = new JLabel();
		weekDays.setLayout(new FlowLayout());
		weekDays.setBounds(650, 43, 90, 264); // setPreferredSize(new
												// Dimension(90, 264));

		for (i = 2; i <= 8; i++) {
			WT[i][1] = new JButton();
			WT[i][1].setText(days[i - 2] + "");
			WT[i][1].setBackground(Color.white);
			WT[i][1].setPreferredSize(new Dimension(80, 28));
			weekDays.add(WT[i][1]);
		}

		/////////////////////////////////////////////////
		JButton sabt = new JButton("ثبت");
		sabt.setFont(Main.MyFont(font));
		sabt.setBounds(220, 300, 90, 30);
		sabt.addActionListener(this);
		weekTimes.add(sabt);
		///////////////////////////////////////////////
		JButton back = new JButton("بازگشت");
		back.setFont(Main.MyFont(font));
		back.setBounds(350, 300, 90, 30);
		back.addActionListener(this);
		weekTimes.add(back);
		////////////////////////////////////////////////

		weekTimes.add(weekDays);
		weekTimes.add(weekTimesBack);
		this.add(weekTimes);

	}

	public void set() {

		weekTimes.setVisible(false);
		backFlag = false;
		Inputs.disable();
		Inputs.setVisible(false);
		Inputs = new JPanel();
		Inputs.setBounds(0, 0, width, height);
		Inputs.setLayout(new FlowLayout());
		Inputs.setBackground(Color.white);
		Inputs.setOpaque(true);

		starts.disable();
		starts.setVisible(false);
		starts = new JLabel();
		starts.setLayout(new FlowLayout());
		starts.setBackground(Color.white);
		starts.setOpaque(true);
		starts.setPreferredSize(new Dimension(400, 130 * (timesCount / 2)));
		// starts.setBackground(Color.gray);

		ends.disable();
		ends.setVisible(false);
		ends = new JLabel();
		ends.setLayout(new FlowLayout());
		// ends.setBounds(0, 180, width-100, 50);
		ends.setBackground(Color.yellow);
		SE = new JButton[timesCount + 1];
		for (int i = 1; i <= timesCount; i++) {
			SE[i] = new JButton();
			SE[i].setPreferredSize(new Dimension(140, 120));
			// SE[i].setBackground(Color.white);
			SE[i].setLayout(new FlowLayout());
			starts.add(SE[i]);
		}

		// Inputs.disable();
		int u = 0;
		Label = new JLabel[timesCount + 1];
		for (int i = 1; i <= timesCount; i++) {
			Label[i] = new JLabel();
			Label[i].setPreferredSize(new Dimension(70, 20)); // setBounds(i *
																// 160 + 125, (j
																// * 90) + 20,
																// 80, 60);
			Label[i].setFont(Main.MyFont(font));
			Label[i].setText(i + "");
			SE[i].add(Label[i]);
		}

		u = 0;

		startSaat = new JComboBox[timesCount + 1];
		startDaghighe = new JComboBox[timesCount + 1];
		endSaat = new JComboBox[timesCount + 1];
		endDaghighe = new JComboBox[timesCount + 1];
		for (int i = 1; i <= timesCount; i++) {
			startSaat[i] = new JComboBox();
			startSaat[i].setPreferredSize(new Dimension(50, 35));
			AddTimeComboBoxesItems(startSaat[i], 2);
			startSaat[i].setBackground(Color.green);
			SE[i].add(startSaat[i]);
			startDaghighe[i] = new JComboBox();
			startDaghighe[i].setPreferredSize(new Dimension(50, 35));
			AddTimeComboBoxesItems(startDaghighe[i], 1);
			startDaghighe[i].setBackground(Color.green);
			SE[i].add(startDaghighe[i]);
			endSaat[i] = new JComboBox();
			endSaat[i].setPreferredSize(new Dimension(50, 35));
			AddTimeComboBoxesItems(endSaat[i], 2);
			endSaat[i].setBackground(Color.red);
			SE[i].add(endSaat[i]);
			endDaghighe[i] = new JComboBox();
			endDaghighe[i].setPreferredSize(new Dimension(50, 35));
			AddTimeComboBoxesItems(endDaghighe[i], 1);
			endDaghighe[i].setBackground(Color.red);
			SE[i].add(endDaghighe[i]);
		}

		JScrollPane pane2 = new JScrollPane(starts, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// pane.setBorder(null);

		// pane2.setOpaque(false);
		pane2.setPreferredSize(new Dimension(width - 50, height - 150));
		pane2.setOpaque(true);
		// ends.add(end);
		Inputs.add(pane2);
		// Inputs.add(ends);
		this.add(Inputs);
		//////////////////////////////////////////////
		// b.disable();
		b1 = new JButton("ثبت");
		b1.setFont(Main.MyFont(font));
		b1.setBounds(70, 300, 90, 30);
		b1.addActionListener(this);
		Inputs.add(b1);
		///////////////////////////////////////////////
		c1 = new JButton("بازگشت");
		c1.setFont(Main.MyFont(font));
		c1.setBounds(200, 300, 90, 30);
		c1.addActionListener(this);
		Inputs.add(c1);
		///////////////////////////////////////////////
		JButton b3 = new JButton();
		b3 = new JButton("افزودن تایم جدید");
		b3.setFont(Main.MyFont(font));
		b3.setBounds(330, 300, 120, 30);
		b3.addActionListener(this);
		Inputs.add(b3);
		////////////////////////////////////////////////
		JButton b4 = new JButton();
		b4 = new JButton("کاهش تایم ها");
		b4.setFont(Main.MyFont(font));
		b4.setBounds(480, 300, 120, 30);
		b4.addActionListener(this);
		Inputs.add(b4);
	}

	private void setChekBoxesSiz(int tc) {
		chekBoxesHeight = 1;
		chekBoxesWidth = 1;
		// tc++;
		while (chekBoxesWidth * tc < (width - 195) - tc * 6)
			chekBoxesWidth++;

		chekBoxesHeight = 28;
	}

	public void setForEdit() {
		for (int i = 1; i <= timesCount; i++) {

			currentTime = listOfTimes.get(i - 1);
			this.Label[i].setText(currentTime.getNam() + "");
			this.startSaat[i].setSelectedItem(currentTime.getStartSaat());
			this.startDaghighe[i].setSelectedItem(currentTime.getStartDaghighe());
			this.endSaat[i].setSelectedItem(currentTime.getEndSaat());
			this.endDaghighe[i].setSelectedItem(currentTime.getEndDaghighe());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().matches("changeVorudi")) {
			setFirsts();
			setForEdit();
			set2();
			firstLoadFlag=0;
			setListOfDayTime();
		}

		if (e.getActionCommand().matches("ثبت")) {

			if (backFlag == false) {

				if (timesCount == 0) {
					timesCount = 1;
					currentTimeCount = 1;
					setTimeAtributes(1);
					TimeManager.Insert(t);
				}

				if ((timesCount - currentTimeCount) > 0) {
					for (int i = currentTimeCount + 1; i <= timesCount; i++) {
						setTimeAtributes(i);
						TimeManager.Insert(t);
					}
					for (int i = 1; i <= currentTimeCount; i++) {
						setTimeAtributes(i);
						TimeManager.Edit(t);
					}
				} else if ((timesCount - currentTimeCount) < 0) {
					for (int i = currentTimeCount; i > timesCount; i--) {
						TimeManager.delete(listOfTimes.get(i - 1).getNam() + "");
					}

					for (int i = 1; i < timesCount; i++) {
						setTimeAtributes(i);
						TimeManager.Edit(t);
					}
				}

				else if (timesCount == currentTimeCount) {
					for (int i = 1; i <= timesCount; i++) {
						setTimeAtributes(i);
						TimeManager.Edit(t);
					}

				}
				setFirsts();
				setForEdit();
				Inputs.disable();
				Inputs.setVisible(false);
				setVorudiList();
				set2();
				firstLoadFlag=1;
				setListOfDayTime();

			} else {

				try {
					InsertDayTimes();
					weekTimes.setVisible(false);
					weekTimes.disable();
					vorudiBack.setVisible(false);

					setFirsts();
					timesCount = listOfTimes.size();
					set();
					setForEdit();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

		if (e.getActionCommand().matches("بازگشت")) {

			if (backFlag == false) {

				// ip.set("Ostad","");
				// Main.GoToForm(0);
			}

			if (backFlag == true) {
				weekTimes.setVisible(false);
				weekTimes.disable();
				setFirsts();
				timesCount = listOfTimes.size();
				vorudiBack.setVisible(false);
				set();
				setForEdit();
			}

		}

		if (e.getActionCommand().matches("افزودن تایم جدید")) {

			timesCount++;
			set();
			setForEdit();
		}

		if (e.getActionCommand().matches("کاهش تایم ها")) {

			timesCount--;
			set();
			setFirsts();
			setForEdit();

		}

		Main.setItempanel("Time");
		itemPanel.disableItems();
	}

	public int firstLoadFlag = 0;

	private void setListOfDayTime() {
		if (firstLoadFlag == 1)
			listOfDayTime = (ArrayList<DayTime>) DayTimeManager
					.selectAllBySal((int) vorudiManager.selectAll().get(0).getSal());
		else

			listOfDayTime = (ArrayList<DayTime>) DayTimeManager
					.selectAllBySal(Integer.parseInt(vorudi.getSelectedItem().toString()));
		String u;
		int y = 0, x = 0;

		for (int i = 2; i <= 8; i++)
			for (int j = timesCount + 1; j >= 2; j--)
				WT[i][j].setBackground(Color.red);

		for (int i = 0; i < listOfDayTime.size(); i++) {
			y = listOfDayTime.get(i).getTimeName();
			u = listOfDayTime.get(i).getDay();
			System.out.println("time: " + y + " day: " + u);
			for (int k = 0; k < 7; k++)
				if (days[k].matches(u))
					x = k;
			WT[x + 2][y + 1].setBackground(Color.green);

		}

	}

	private void InsertDayTimes() throws SQLException {

		// int a = DayTimeManager.countDayTimes();

		DayTimeManager.deleteAll(Integer.parseInt(vorudi.getSelectedItem() + ""));

		for (int i = 2; i <= 8; i++) {
			for (int j = 2; j <= timesCount + 1; j++) {
				if (WT[i][j].getBackground().getGreen() != 0) {
					DayTime dt = new DayTime();
					dt.setTimeName(j - 1);
					dt.setDay(days[i - 2]);
					dt.setVorudiSal(Integer.parseInt(vorudi.getSelectedItem() + ""));
					DayTimeManager.Insert(dt);
				}
				//
			}
		}

	}

	public void setFirsts() {
		this.listOfTimes = (ArrayList<Time>) TimeManager.selectAll();
		currentTimeCount = listOfTimes.size();
	}

	public void setTimeAtributes(int i) {
		t = new Time();

		t.setNam(toint(this.Label[i].getText()));
		t.setStartSaat((int) this.startSaat[i].getSelectedItem());
		t.setStartDaghighe((int) this.startDaghighe[i].getSelectedItem());
		t.setEndSaat((int) this.endSaat[i].getSelectedItem());
		t.setEndDaghighe((int) this.endDaghighe[i].getSelectedItem());

		// c.set

	}

	private void deleteTime() {

		TimeManager.delete(t.getNam() + "");
	}

	static int toint(String a) {
		int x = 0;
		for (int i = 0; i < a.length(); i++)
			x = x * 10 + (a.charAt(i) - '0');
		return x;
	}

	private void AddTimeComboBoxesItems(JComboBox a, int b) {
		if (b == 1) {
			for (int i = 0; i <= 60; i++)
				a.addItem(i);
		} else {
			for (int i = 1; i <= 24; i++)
				a.addItem(i);
		}
	}
}

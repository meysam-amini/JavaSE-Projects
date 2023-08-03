package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import TimeTable.Common.Celas;
import TimeTable.Common.Cours;
import TimeTable.Common.DayTime;
import TimeTable.Common.Olaviat;
import TimeTable.Common.Time;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.DayTimeManager;
import TimeTable.DataAcsses.OlaviatManager;
import TimeTable.DataAcsses.TimeManager;
import TimeTable.DataAcsses.TimeTableManager;
import TimeTable.DataAcsses.coursmanager;
import TimeTable.Main.Main;

public class editTimeTable extends JPanel implements ActionListener {

	private JPanel P = new JPanel();
	private ArrayList<Celas> Classes;
	private ArrayList<Cours> Courses;
	private ArrayList<Time> Times;
	private ArrayList<DayTime> allDayTime;
	private JScrollPane pane2 = new JScrollPane();
	private JLabel table = new JLabel();
	public static JButton t[][] = new JButton[1][1];
	public static JButton t1[] = new JButton[1];
	public static JButton t2[] = new JButton[1];
	private JButton days[];
	private JLabel coursName[];
	private JLabel coursGoruh[];
	private JLabel coursVorudi[];
	public static JButton coursDetail = new JButton();
	public static JButton coursDetail2 = new JButton();
	private static JButton changeCourses = new JButton();
	private String w[] = { "شنبه", "یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه", "جمعه" };
	private String cName;
	private int cVorudi;
	private int cGoruh;

	private JButton times[];
	private JButton classes[];

	private int firstDarsFlag = 0;
	private int secondDarsFlag = 0;
	private String actionCommand = "";

	private Olaviat dars1;
	private Olaviat dars2;
	private Olaviat cDars;

	private static JLabel darsName = new JLabel();;
	private static JLabel darsCode = new JLabel();;
	private static JLabel goruh = new JLabel();;
	private static JLabel vorudi = new JLabel();;
	private static JLabel ostad = new JLabel();;
	private static JLabel timee = new JLabel();;
	private static JLabel celass = new JLabel();;
	private static JLabel ruzz = new JLabel();;
	/////////////////////////////////////////////////
	private static JLabel darsName2 = new JLabel();;
	private static JLabel darsCode2 = new JLabel();;
	private static JLabel goruh2 = new JLabel();;
	private static JLabel vorudi2 = new JLabel();;
	private static JLabel ostad2 = new JLabel();;
	private static JLabel timee2 = new JLabel();;
	private static JLabel celass2 = new JLabel();;
	private static JLabel ruzz2 = new JLabel();;

	private Olaviat matrix[][][][];
	private int day1, class1, time1, day2, class2, time2;
	private Olaviat dars1temp;

	int isZojFardDars = 0;
	Olaviat otherZojFard;

	public editTimeTable() throws SQLException {
		this.setBounds(0, 100, Main.width, Main.height - 190);
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setOpaque(true);

		setMatrix();

		set1();
		set();

	}

	private void set1() {
		// coursDetail.disable();
		// coursDetail.setVisible(false);
		coursDetail = new JButton();
		coursDetail.setLayout(new FlowLayout());
		coursDetail.setText("درس اول را انتخاب کنید");
		coursDetail.setActionCommand("coursDetail");
		coursDetail.addActionListener(this);
		coursDetail.setBounds(Main.width - 150, 20, 130, 180);
		this.add(coursDetail);
		/////////////////////////////////////////////////
		// coursDetail2.disable();
		// coursDetail2.setVisible(false);
		coursDetail2 = new JButton();
		coursDetail2.setLayout(new FlowLayout());
		coursDetail2.setText("");
		coursDetail2.setActionCommand("coursDetail");
		coursDetail2.addActionListener(this);
		coursDetail2.setBounds(Main.width - 150, 210, 130, 180);
		this.add(coursDetail2);
		/////////////////////////
		changeCourses = new JButton();
		changeCourses.setLayout(new FlowLayout());
		changeCourses.setText("تعویض زمان دو درس");
		changeCourses.setActionCommand("changeCourses");
		changeCourses.addActionListener(this);
		changeCourses.setBounds(Main.width - 150, 400, 130, 50);
		changeCourses.setVisible(false);
		this.add(changeCourses);
	}

	public void setMatrix() throws SQLException {
		Classes = (ArrayList<Celas>) ClassManager.selectAll();
		for(int i=0;i<Classes.size();i++)
			if(Classes.get(i).isSpecial())
				Classes.remove(i);
		Times = (ArrayList<Time>) TimeManager.selectAll();
		matrix = new Olaviat[7][Classes.size()][Times.size()][2];
		allDayTime = (ArrayList<DayTime>) DayTimeManager.selectAllBySal(92);
		ZF = "";
		for (int k = 0; k < 7; k++)
			for (int i = 0; i < Classes.size(); i++) {
				for (int j = 0; j < Times.size(); j++) {
					getCours2(k, i, j);
					// if (cDars != null && otherZojFard != null &&
					// ZF.indexOf(cDars + "") != -1) {
					// cDars.setZojFard(0);
					// otherZojFard.setZojFard(0);
					// }
					if (isZojFardDars > 0) {
						matrix[k][i][j][0] = cDars;//
						matrix[k][i][j][1] = otherZojFard;
						// System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmm");
						// System.out.println(cDars);
						// System.out.println(otherZojFard);
						// System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
						// ZF += otherZojFard + "." + cDars + ".";

					} else {

						matrix[k][i][j][0] = cDars;//

					}
				}
			}
	}

	int o = 0;

	private String ZF = "";

	public void set() throws SQLException {
		o = 0;
		int bw = 80, bh = 80;
		Classes = (ArrayList<Celas>) ClassManager.selectAll();
		for(int i=0;i<Classes.size();i++)
			if(Classes.get(i).isSpecial())
				Classes.remove(i);
		Times = (ArrayList<Time>) TimeManager.selectAll();
		Courses = (ArrayList<Cours>) coursmanager.selectAll();

		P.disable();
		P.setVisible(false);
		P = new JPanel();
		P.setBounds(0, 0, Main.width - 160, Main.height - 190);
		P.setBackground(Color.white);
		P.setLayout(null);
		P.setOpaque(true);
		//////////////////////////////////////////////////////////
		table.disable();
		table.setVisible(false);
		table = new JLabel();
		table.setPreferredSize(new Dimension(7 * (7 * bw), bh * Classes.size() + 200));
		table.setBackground(Color.white);
		table.setOpaque(true);
		table.setLayout(null);
		int zfc = coursmanager.getZFCount();
		////////////////////////////////////////////////////////////
		coursName = new JLabel[Courses.size() * 3 * Classes.size() * Times.size() + 1 + zfc];
		coursVorudi = new JLabel[Courses.size() * 3 * Classes.size() * Times.size() + 1 + zfc];
		coursGoruh = new JLabel[Courses.size() * 3 * Classes.size() * Times.size() + 1 + zfc];

		/////////////////////////////////////////////////////

		t = new JButton[Times.size() + 2][Classes.size() + 2];
		t1 = new JButton[zfc*2];
		t2 = new JButton[zfc*2];
		int o2 = 0;
		for (int k = 0; k < 7; k++)
			for (int i = 0; i < Classes.size(); i++) {
				for (int j = 0; j < Times.size(); j++) {
					// System.out.println("class: " + Classes.get(i).getId() + "
					// times: " + Times.get(j).getNam());

					getCours(k, i, j);
					coursName[o] = new JLabel();
					coursGoruh[o] = new JLabel();
					coursVorudi[o] = new JLabel();
					t[j][i] = new JButton("");
					t[j][i].setLayout(new FlowLayout());
					if (cDars != null || otherZojFard != null) {
						if (cDars.isZojFard() > 0) {
							t[j][i].setLayout(null);
							t1[o2] = new JButton();
							
							t1[o2].setBounds(2, 2, (bw / 2) - 1, bh - 5);// PreferredSize(new
																			// Dimension(30,
																			// bh-15));
							t1[o2].setLayout(new FlowLayout());
							t2[o2] = new JButton();
							t2[o2].setBounds((bw / 2), 2, (bw / 2) - 3, bh - 5);// PreferredSize(new
																				// Dimension(30,
																				// bh-15));
							t2[o2].setLayout(new FlowLayout());

							coursName[o].setText(cName);

							coursVorudi[o].setText(cVorudi + "");////

							coursGoruh[o].setText(cGoruh + "");
							t1[o2].add(coursName[o]);
							t1[o2].add(coursVorudi[o]);
							t1[o2].add(coursGoruh[o]);
							t1[o2].addMouseListener(n);
							t1[o2].addActionListener(m);
							t1[o2].setActionCommand(k + "." + i + "." + j);
							t[j][i].add(t1[o2]);
							//////////////////////////////////////////
							o++;
							coursName[o] = new JLabel();
							coursGoruh[o] = new JLabel();
							coursVorudi[o] = new JLabel();
							coursName[o].setText(cName2);

							coursVorudi[o].setText(cVorudi2 + "");

							coursGoruh[o].setText(cGoruh2 + "");

							t2[o2].add(coursName[o]);
							t2[o2].add(coursVorudi[o]);
							t2[o2].add(coursGoruh[o]);
							t2[o2].setActionCommand(k + "." + i + "." + j + "o");
							t2[o2].addMouseListener(n);
							t2[o2].addActionListener(m);
							t[j][i].add(t2[o2]);
							o++;

							if (firstDarsFlag == 1) {
								day2 = k;
								class2 = i;
								time2 = j;
								secondDarsZF = 0;
								dars2 = cDars;
								if (dars2 != dars1) {

									remove();
									if (testCourses() && testPriorities()) {
										t1[o2].setBackground(Color.green);
										System.out.println(dars2);
									}
									else{
										System.out.println("#: "+cDars);
										System.out.println("#: "+otherZojFard);
									}
									addAgain();
								}
								secondDarsZF = 1;
								dars2 = otherZojFard;
								if (dars2 != dars1) {
									remove();
									if (testCourses() && testPriorities()) {
										t2[o2].setBackground(Color.green);
										System.out.println(dars2);
									}
									else{
										System.out.println("#: "+cDars);
										System.out.println("#: "+otherZojFard);
									}
									addAgain();
								}

							}
							if (firstDarsFlag == 1) {
								if (otherZojFard == dars1 && firstDarsZF == 1)
									t2[o2].setBackground(Color.yellow);
								if (cDars==dars1)
									t1[o2].setBackground(Color.yellow);
							}
							o2++;
						} else {
							coursName[o].setText(cName + "           ");

							coursVorudi[o].setText("ترم      " + cVorudi);////

							coursGoruh[o].setText("گروه      " + cGoruh);
							t[j][i].setLayout(new FlowLayout());

							dars2 = cDars;
							if (firstDarsFlag == 1)
								if (dars2 != null && dars2 != dars1) {
									day2 = k;
									class2 = i;
									time2 = j;
									secondDarsZF = 0;
									remove();
									if (testCourses() && testPriorities())
										t[j][i].setBackground(Color.green);
									addAgain();
								}
							if (cDars == dars1)
								t[j][i].setBackground(Color.yellow);
							t[j][i].add(coursName[o]);
							t[j][i].add(coursVorudi[o]);
							t[j][i].add(coursGoruh[o]);
							o++;
							t[j][i].setActionCommand(k + "." + i + "." + j);
							t[j][i].setFont(Main.MyFont(10));
						}
					} else {
						t[j][i].setBackground(new Color(204, 255, 204));
						t[j][i].setActionCommand(k + "." + i + "." + j);
						dars2 = cDars;
						if (firstDarsFlag == 1) {
							day2 = k;
							time2 = j;
							class2 = i;
							if (dars2 != dars1) {

								secondDarsZF = 0;
								remove();
								if (testCourses() && testPriorities())
									t[j][i].setBackground(Color.green);

								addAgain();
							}
							if (day2 == day1 && time1 == time2 && class1 == class2)
								t[j][i].setBackground(Color.yellow);
						}
					}

					t[j][i].addActionListener(m);
					t[j][i].addMouseListener(n);
					t[j][i].setBounds((j * bw) + (k * ((Times.size()) * (bw))) + bw, (i * bh) + 100, bw, bh);

					table.add(t[j][i]);
					// }

				}
			}

		//////////////////////////////////////////////////////////////
		days = new JButton[7];
		for (int i = 0; i < 7; i++) {
			days[i] = new JButton();
			days[i].setBounds(i * ((Times.size()) * bw) + bw, 10, bw * (Times.size()), 55);
			// days[i].setBorder(raisedBorder);
			// days[i].setOpaque(true);
			days[i].setFont(Main.MyFont(20));
			days[i].setBackground(Color.lightGray);// new Color(204, 255, 255));
			days[i].setForeground(Color.black);
			days[i].setText(w[i]);// +" ");
			table.add(days[i]);

		}

		times = new JButton[Times.size()];
		for (int k = 0; k < 7; k++)
			for (int i = 0; i < Times.size(); i++) {
				times[i] = new JButton();
				times[i].setBounds((i * bw) + (k * ((Times.size()) * (bw))) + bw, 64, bw, 35);
				// times[i].setBorder(raisedBorder);
				// times[i].setOpaque(false);
				times[i].setFont(Main.MyFont(16));
				times[i].setBackground(Color.lightGray);// new Color(204, 255,
														// 255));
				times[i].setForeground(Color.black);
				times[i].setText("تایم" + Times.get(i).getNam());// +" ");
				table.add(times[i]);

			}
		////////////////////////////////////////////////////////////////
		classes = new JButton[Classes.size()];
		for (int i = 0; i < Classes.size(); i++) {
			classes[i] = new JButton();
			classes[i].setBounds((5), i * bh + 100, 70, bh);
			// classes[i].setBorder(raisedBorder);
			// classes[i].setOpaque(false);
			classes[i].setFont(Main.MyFont(15));
			classes[i].setBackground(Color.lightGray);// new Color(204, 255,
														// 255));
			classes[i].setForeground(Color.black);
			classes[i].setText(Classes.get(i).getNam());// +" ");
			table.add(classes[i]);}

		

		///////////////////////////////////////////////////////////////

		//////////////////////////////////

		///////////////////////////////////////////////////////////
		// changeCourses.disable();
		// changeCourses.setVisible(false);

		//////////////////////////////////////////////////////////////
		// firstDarsFlag = 0;
		// secondDarsFlag = 0;
		// dars1=null;
		// dars2=null;
		Border raisedBorder = BorderFactory.createLineBorder(Color.black, 1);
		pane2 = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane2.setBounds(10, 10, Main.width - 180, Main.height - 210);
		pane2.setBorder(raisedBorder);
		// pane2.add(table);
		pane2.setOpaque(true);
		P.add(pane2);
		this.add(P);
		this.setVisible(true);
	}

	private MouseListener n = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			e.getComponent().setBackground(Color.yellow);

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
	};

	private int zfDarsSelected = 0;
	private int firstDarsZF = 0;
	private int secondDarsZF = 0;
	private ActionListener m = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent v) {

			zfDarsSelected = 0;
			System.out.println(v.getActionCommand() + "");
			if (!(v.getActionCommand().matches(actionCommand))) {
				actionCommand = v.getActionCommand();
				int i = 0;
				String day = "", celas = "", time = "";
				while (actionCommand.charAt(i) != '.')
					day += actionCommand.charAt(i++);
				i++;
				while (actionCommand.charAt(i) != '.')
					celas += actionCommand.charAt(i++);
				i++;
				if (actionCommand.indexOf("o") == -1) {
					zfDarsSelected = 0;
					while (i < actionCommand.length())
						time += actionCommand.charAt(i++);
				} else {
					zfDarsSelected = 1;
					while (actionCommand.charAt(i) != 'o')
						time += actionCommand.charAt(i++);
				}

				try {
					getCours(toint(day), toint(celas), toint(time));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//
				if (firstDarsFlag == 0) {
					firstDarsFlag = 1;
					secondDarsFlag = 0;
					if (zfDarsSelected == 1) {
						dars1 = otherZojFard;
						firstDarsZF = 1;
						dars1temp = otherZojFard;
					} else {
						dars1 = cDars;
						firstDarsZF = 0;
						dars1temp = dars1;
					}
					day1 = toint(day);
					class1 = toint(celas);
					time1 = toint(time);
					setCoursDetail2Text(0);
					coursDetail2.setText("درس دوم را انتخاب کنید");
					coursDetail.setText("");
					setCoursDetail1Text(1);
					changeCourses.setVisible(false);
					if (dars1 == null)
						addDetail1(1);
					else
						addDetail1(0);//
					try {
						set();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					coursDetail2.setText("");
					setCoursDetail2Text(1);
					day2 = toint(day);
					class2 = toint(celas);
					time2 = toint(time);
					secondDarsFlag = 1;
					if (zfDarsSelected == 0) {
						dars2 = cDars;
						secondDarsZF = 0;
					} else {
						dars2 = otherZojFard;
						secondDarsZF = 1;
					}
					firstDarsFlag = 0;
					if (dars2 == null)
						addDetail2(1);
					else
						addDetail2(0);
					changeCourses.setVisible(true);

				}

			} else {
			}

			zfDarsSelected = 0;

		}
	};

	private String celas = "", ruz = "", Stime = "", Ftime = "";
	private int firstZojfardDarsSelected = 0;

	private void getCours2(int day, int celas, int time) throws SQLException {
		firstZojfardDarsSelected = 0;
		isZojFardDars = 0;
		cDars = null;
		otherZojFard = null;
		// int a = 0;
		// System.out.println("last:
		// "+TimeTableManager.AllSelectedOlaviats.get(TimeTableManager.AllSelectedOlaviats.size()));
		for (int k = 0; k < TimeTableManager.AllSelectedOlaviats.size(); k++) {
			if (TimeTableManager.AllSelectedOlaviats.get(k).getRuz().matches(w[day])
					&& TimeTableManager.AllSelectedOlaviats.get(k).getCelasID() == Classes.get(celas).getId()
					&& TimeTableManager.AllSelectedOlaviats.get(k).getTimeName() == Times.get(time).getNam()) {
				Olaviat o = TimeTableManager.AllSelectedOlaviats.get(k);

				if (o.isZojFard() > 0 && firstZojfardDarsSelected == 1) {
					this.otherZojFard = o;
					this.isZojFardDars = 1;
					// System.out.println("222222222222222222222222222222222222222222");
					firstZojfardDarsSelected = 0;
					// System.out.println(otherZojFard);

					break;
				}

				// a = 1;
				if (o.isZojFard() > 0 && firstZojfardDarsSelected == 0) {
					this.isZojFardDars = 1;
					this.cDars = o;
					// System.out.println("1111111111111111111111111111111111111111");
					// System.out.println(cDars);

					firstZojfardDarsSelected = 1;
					// continue;
				}
				if (o.isZojFard() == 0) {
					///////// edame baraye peyda kardane badii
					this.isZojFardDars = 0;
					if (firstZojfardDarsSelected == 0) {
						firstZojfardDarsSelected = 0;
						this.cDars = o;
						this.otherZojFard = null;
						break;
					} else
						continue;
				}

			}
			// else {
			// if(firstZojfardDarsSelected==0){
			// cDars = null;
			// otherZojFard = null;
			// isZojFardDars=0;
			// }
			// else{
			//
			// }
			//
			//
			// }

		}
		if (firstZojfardDarsSelected == 1) {
			otherZojFard = null;
			isZojFardDars = 1;
		}

		// if (a == 0) {
		//
		// cName = "null";
		// cGoruh = -1;
		// cVorudi = 0;//
		// coursmanager.getVorudi((int)TimeTableManager.AllSelectedOlaviats.get(k).getDarsID());
		//
		// }

	}

	Olaviat T;
	Olaviat T2;
	private String cName2;
	private int cGoruh2;
	private int cVorudi2;
	private Cours cCours2;
	private String celas2;
	private String ruz2;
	private String Stime2;
	private String Ftime2;

	private void getCours(int day, int celas, int time) throws SQLException {
		T = matrix[day][celas][time][0];
		T2 = matrix[day][celas][time][1];

		if (T == null) {
			cDars = null;
			cCours = null;
			cName = "null";
			cGoruh = -1;
			cVorudi = 0;// coursmanager.getVorudi((int)TimeTableManager.AllSelectedOlaviats.get(k).getDarsID());

		} else {
			cDars = T;
			this.cCours = coursmanager.getCourse((int) T.getDarsID() + "");
			this.cGoruh = coursmanager.getGoruh((int) T.getDarsID());
			this.cVorudi = coursmanager.getVorudiid((int) T.getDarsID());//getVorudiSal((int) T.getDarsID());
			this.cName = cCours.getNam();
			this.celas = ClassManager.getClassName((int) T.getCelasID());
			this.ruz = w[day];
			this.Stime = Times.get(time).getStartSaat() + ":" + Times.get(time).getStartDaghighe();
			this.Ftime = Times.get(time).getEndSaat() + ":" + Times.get(time).getEndDaghighe();

		}

		if (T2 == null) {
			otherZojFard = null;
			cName2 = "null";
			cGoruh2 = -1;
			cVorudi2 = 0;
			cCours2 = null;// coursmanager.getVorudi((int)TimeTableManager.AllSelectedOlaviats.get(k).getDarsID());

		} else {
			otherZojFard = T2;
			this.cCours2 = coursmanager.getCourse((int) T2.getDarsID() + "");
			this.cGoruh2 = coursmanager.getGoruh((int) T2.getDarsID());
			this.cVorudi2 = coursmanager.getVorudiid((int) T2.getDarsID());//getVorudiSal((int) T2.getDarsID());
			this.cName2 = cCours2.getNam();
			this.celas2 = ClassManager.getClassName((int) T2.getCelasID());
			this.ruz2 = w[day];
			this.Stime2 = Times.get(time).getStartSaat() + ":" + Times.get(time).getStartDaghighe();
			this.Ftime2 = Times.get(time).getEndSaat() + ":" + Times.get(time).getEndDaghighe();

		}

	}

	private Cours cCours;

	private void addDetail2(int k) {

		if (k == 0) {
			darsName2.setText("     " + cCours.getNam());
			darsName2.setFont(Main.MyFont(14));
			darsCode2.setText("کد درس:   " + cCours.getCode());
			ruzz2.setText("روز:    " + this.ruz);
			celass2.setText("کلاس:    " + this.celas);
			timee2.setText("زمان:    " + this.Stime + " تا " + this.Ftime);
			goruh2.setText("گروه:    " + cGoruh + "");
			vorudi2.setText("ترم:    " + cVorudi + "");
			ostad2.setText("استاد:   " + cCours.getOstadFamily());

			coursDetail2.add(darsName2);
			coursDetail2.add(darsCode2);
			coursDetail2.add(goruh2);
			coursDetail2.add(vorudi2);
			coursDetail2.add(ostad2);
			coursDetail2.add(timee2);
			coursDetail2.add(celass2);
			coursDetail2.add(ruzz2);
		}

		else {

			if (cCours2 != null) {
				darsCode2.setText("کد درس:   " + cCours2.getCode());
				ostad2.setText("استاد:   " + cCours2.getOstadFamily());
			} else {
				darsCode2.setText("کد درس:   " + "null");
				ostad2.setText("استاد:   " + "null");
			}

			darsName2.setText("     " + cName2);
			darsName2.setFont(Main.MyFont(14));
			ruzz2.setText("روز:    " + this.ruz2);
			celass2.setText("کلاس:    " + this.celas2);
			timee2.setText("زمان:    " + this.Stime2 + " تا " + this.Ftime2);
			goruh2.setText("گروه:    " + cGoruh2 + "");
			vorudi2.setText("ترم:    " + cVorudi2 + "");

			coursDetail2.add(darsName2);
			coursDetail2.add(darsCode2);
			coursDetail2.add(goruh2);
			coursDetail2.add(vorudi2);
			coursDetail2.add(ostad2);
			coursDetail2.add(timee2);
			coursDetail2.add(celass2);
			coursDetail2.add(ruzz2);
		}

	}

	private void addDetail1(int k) {

		if (k == 0) {
			darsName.setText("     " + cCours.getNam());
			darsName.setFont(Main.MyFont(14));
			darsCode.setText("کد درس:   " + cCours.getCode());
			ruzz.setText("روز:    " + this.ruz);
			celass.setText("کلاس:    " + this.celas);
			timee.setText("زمان:    " + this.Stime + " تا " + this.Ftime);
			goruh.setText("گروه:    " + cGoruh + "");
			vorudi.setText("ترم:    " + cVorudi + "");
			ostad.setText("استاد:   " + cCours.getOstadFamily());

			coursDetail.add(darsName);
			coursDetail.add(darsCode);
			coursDetail.add(goruh);
			coursDetail.add(vorudi);
			coursDetail.add(ostad);
			coursDetail.add(timee);
			coursDetail.add(celass);
			coursDetail.add(ruzz);
		}

		else {

			darsName.setText("     " + cName2);
			darsName.setFont(Main.MyFont(14));
			if (cCours2 != null) {
				darsCode.setText("کد درس:   " + cCours2.getCode());
				ostad.setText("استاد:   " + cCours2.getOstadFamily());
			} else {
				darsCode.setText("کد درس:   " + "null");
				ostad.setText("استاد:   " + "null");
			}

			ruzz.setText("روز:    " + this.ruz2);
			celass.setText("کلاس:    " + this.celas2);
			timee.setText("زمان:    " + this.Stime2 + " تا " + this.Ftime2);
			goruh.setText("گروه:    " + cGoruh2 + "");
			vorudi.setText("ترم:    " + cVorudi2 + "");

			coursDetail.add(darsName);
			coursDetail.add(darsCode);
			coursDetail.add(goruh);
			coursDetail.add(vorudi);
			coursDetail.add(ostad);
			coursDetail.add(timee);
			coursDetail.add(celass);
			coursDetail.add(ruzz);

		}

	}

	private void setCoursDetail2Text(int a) {

		if (a == 0) {
			darsName2.setVisible(false);
			darsCode2.setVisible(false);
			goruh2.setVisible(false);
			vorudi2.setVisible(false);
			ostad2.setVisible(false);
			timee2.setVisible(false);
			celass2.setVisible(false);
			ruzz2.setVisible(false);
		} else {
			darsName2.setVisible(true);
			darsCode2.setVisible(true);
			goruh2.setVisible(true);
			vorudi2.setVisible(true);
			ostad2.setVisible(true);
			timee2.setVisible(true);
			celass2.setVisible(true);
			ruzz2.setVisible(true);
		}

	}

	private void setCoursDetail1Text(int a) {

		if (a == 0) {
			darsName.setVisible(false);
			darsCode.setVisible(false);
			goruh.setVisible(false);
			vorudi.setVisible(false);
			ostad.setVisible(false);
			timee.setVisible(false);
			celass.setVisible(false);
			ruzz.setVisible(false);
		} else {
			darsName.setVisible(true);
			darsCode.setVisible(true);
			goruh.setVisible(true);
			vorudi.setVisible(true);
			ostad.setVisible(true);
			timee.setVisible(true);
			celass.setVisible(true);
			ruzz.setVisible(true);
		}

	}

	static int toint(String a) {
		int x = 0;
		for (int i = 0; i < a.length(); i++)
			x = x * 10 + (a.charAt(i) - '0');
		return x;
	}

	private ArrayList<Olaviat> temp;
	private ArrayList<Olaviat> all = new ArrayList<Olaviat>();

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "changeCourses") {
			try {
				int b = JOptionPane.showConfirmDialog(null, null, "آیا برای  تعویض دو درس مطمئنید؟", 0);
				if (b == 0) {
		
					changeCourses();

					dars1 = null;
					dars2 = null;
					otherZojFard = null;
					cDars = null;
					firstDarsFlag = 0;
					secondDarsFlag = 0;
					changeCourses.setVisible(false);
					// Changable.clear();
					set();
					setCoursDetail1Text(0);
					coursDetail.setText("درس اول را انتخاب کنید");
					coursDetail2.setText("");
					setCoursDetail2Text(0);
				
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void changeCourses() throws SQLException {

	remove();

		System.out.println("day1: "+day1+" class1: "+class1+" time1: "+time1+" 1sZOjfard: "+firstDarsZF);
		System.out.println("day2: "+day2+" class2: "+class2+" time2: "+time2+" 2sZOjfard: "+secondDarsZF);

		dars1 = matrix[day1][class1][time1][firstDarsZF];
		dars2 = matrix[day2][class2][time2][secondDarsZF];
		try {

			if (testCourses() && testPriorities()) {
				if (dars1 != null) {
					dars1.setCelasID(Classes.get(class2).getId());
					dars1.setRuz(w[day2]);
					dars1.setTimeName(Times.get(time2).getNam());
					dars1.setTimesID(Times.get(time2).getId());

				}
				if (dars2 != null) {
					dars2.setRuz(w[day1]);
					dars2.setCelasID(Classes.get(class1).getId());
					dars2.setTimeName(Times.get(time1).getNam());
					dars2.setTimesID(Times.get(time1).getId());
				}

				if (dars1 != null)
					if (dars2 != null) {

						TimeTableManager.AllSelectedOlaviats.add(dars1);
						TimeTableManager.AllSelectedOlaviats.add(dars2);

						// reg>cash>ram>hard
					} else {

						TimeTableManager.AllSelectedOlaviats.add(dars1);
					}
				else {
					if (dars2 != null)
						TimeTableManager.AllSelectedOlaviats.add(dars2);
					else {

					}

				}
				matrix[day1][class1][time1][firstDarsZF] = dars2;
				matrix[day2][class2][time2][secondDarsZF] = dars1;
			}

			else {
				addAgain();

				System.err.println("errrrrrrrrrrrrrrrrrrrrrrr");
				// TimeTableManager.AllSelectedOlaviats = temp;

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// set();
	}

	private boolean testPriorities() {
		if (testRuzP() && testTimeP() && testClassP())
			return true;
		else
			return false;
	}

	private boolean testRuzP() {
		if (dars1 != null && dars2 != null) {
			if (dars1.isRuzp() || dars2.isRuzp())
				if (dars1.getRuz() == dars2.getRuz())
					return true;
				else
					return false;
			return true;
		} else if (dars1 != null && dars2 == null) {
			if (dars1.isRuzp())
				if (dars1.getRuz() == w[day2])
					return true;
				else
					return false;
			else
				return true;
		} else if (dars2 != null && dars1 == null) {
			if (dars2.isRuzp())
				if (dars2.getRuz() == w[day1])
					return true;
				else
					return false;
			else
				return true;
		}
		return true;

	}

	private boolean testTimeP() {

		if (dars1 != null && dars2 != null) {
			if (dars1.isTimesp() || dars2.isTimesp())
				if (dars1.getTimesID() == dars2.getTimesID())
					return true;
				else
					return false;
			return true;
		} else if (dars1 != null && dars2 == null) {
			if (dars1.isTimesp())
				if ((int) dars1.getTimesID() == time2)
					return true;
				else
					return false;
			else
				return true;
		} else if (dars2 != null && dars1 == null) {
			if (dars2.isTimesp())
				if ((int) dars2.getTimesID() == time1)
					return true;
				else
					return false;
			else
				return true;
		}
		return true;

	}

	private boolean testClassP() {

		if (dars1 != null && dars2 != null) {
			if (dars1.isCelasp() || dars2.isCelasp())
				if (dars1.getCelasID() == dars2.getCelasID())
					return true;
				else
					return false;
			return true;
		} else if (dars1 != null && dars2 == null) {
			if (dars1.isCelasp())
				if ((int) dars1.getCelasID() == class2)
					return true;
				else
					return false;
			else
				return true;
		} else if (dars2 != null && dars1 == null) {
			if (dars2.isCelasp())
				if ((int) dars2.getCelasID() == class1)
					return true;
				else
					return false;
			else
				return true;
		}
		return true;

	}

	private boolean testCourses() throws SQLException {

		if (dars1 != null) {
			if (dars2 != null) {
				if (dars1.isZojFard() == dars2.isZojFard()
						&& TimeTableManager.test2((int) dars1.getDarsID(), dars2.getTimeName(), dars2.getRuz(),
								dars1.getTedadJalasat())
						&& TimeTableManager.test2((int) dars2.getDarsID(), dars1.getTimeName(), dars1.getRuz(),
								dars2.getTedadJalasat()))
//						&& TimeTableManager.testVorudi((int) Classes.get(class2).getId(), w[day2],
//								(int) Times.get(time2).getId(), dars1.getTerm(),dars1.isZojFard())
//						&& TimeTableManager.testVorudi((int) Classes.get(class1).getId(), w[day1],
//								(int) Times.get(time1).getId(), dars2.getTerm(),dars2.isZojFard()))

					return true;
				else
					return false;
			} else {
				if (dars1.isZojFard() == secondDarsZF && TimeTableManager.test2((int) dars1.getDarsID(),
						Times.get(time2).getNam(), w[day2], dars1.getTedadJalasat())&& TimeTableManager.testVorudi((int) Classes.get(class2).getId(), w[day2],
								(int) Times.get(time2).getId(), dars1.getTerm(),dars1.isZojFard()))
					return true;
				else
					return false;
			}
		} else {
			if (dars2 != null) {
				if (dars2.isZojFard() == firstDarsZF && TimeTableManager.test2((int) dars2.getDarsID(),
						Times.get(time1).getNam(), w[day1], dars2.getTedadJalasat())&& TimeTableManager.testVorudi((int) Classes.get(class1).getId(), w[day1],
								(int) Times.get(time1).getId(), dars2.getTerm(),dars2.isZojFard()))
					return true;
				else
					return false;
			} else {

			}
		}

		return false;
	}

	private void addAgain() {
		if (dars1 != null)
			if (dars2 != null) {

				TimeTableManager.AllSelectedOlaviats.add(dars1);
				TimeTableManager.AllSelectedOlaviats.add(dars2);

				// reg>cash>ram>hard
			} else {

				TimeTableManager.AllSelectedOlaviats.add(dars1);
			}
		else {
			if (dars2 != null)
				TimeTableManager.AllSelectedOlaviats.add(dars2);
			else {

			}

		}
	}

	private void remove() {
		if (dars1 != null && dars2 == null)
			TimeTableManager.AllSelectedOlaviats.remove(TimeTableManager.AllSelectedOlaviats.indexOf(dars1));
		else if (dars2 != null && dars1 == null)
			TimeTableManager.AllSelectedOlaviats.remove(TimeTableManager.AllSelectedOlaviats.indexOf(dars2));

		else if (dars1 != null && dars2 != null) {
			TimeTableManager.AllSelectedOlaviats.remove(TimeTableManager.AllSelectedOlaviats.indexOf(dars1));
			TimeTableManager.AllSelectedOlaviats.remove(TimeTableManager.AllSelectedOlaviats.indexOf(dars2));

		}
	}

	private ArrayList<Olaviat> Changable = new ArrayList<Olaviat>();

	private void setChangable() throws SQLException {
		Changable.clear();
		Changable = new ArrayList<Olaviat>();
		int d1 = day1, c1 = class1, t1 = time1, d2 = day2, c2 = class2, t2 = time2;
		// for (int i = 0; i < TimeTableManager.AllSelectedOlaviats.size(); i++)
		// temp.add(TimeTableManager.AllSelectedOlaviats.get(i));
		for (int k = 0; k < 7; k++)
			for (int i = 0; i < Classes.size(); i++) {
				for (int j = 0; j < Times.size(); j++) {

					getCours(k, i, j);//
					// day2 = k;
					// class2 = (int) Classes.get(i).getId();
					// time2 = (int) Times.get(j).getId();
					// day1=k;
					// class1=(int) Classes.get(i).getId();
					// time1=(int) Times.get(j).getId();
					dars2 = cDars;
					if (dars2 != null)
						if (testCourses()) {
							Changable.add(dars2);
							// System.err.println(coursmanager.getName((int)
							// cDars.getDarsID()));
							// System.out.println(i+" "+j);
							// t[j][i].setBackground(Color.green);
						}

					dars2 = otherZojFard;
					if (dars2 != null)
						if (testCourses()) {
							Changable.add(dars2);
							// System.err.println(coursmanager.getName((int)
							// cDars.getDarsID()));
							// System.out.println(i+" "+j);
							// t[j][i].setBackground(Color.green);
						}

				}
			}

		// day1=d1;
		// class1=c1;
		// time1=t1;
		// day2 = d2;
		// class2 = c2;
		// time2 = t2;
		set();
		getCours(day1, class1, time1);
		dars1 = cDars;
		setCoursDetail2Text(0);
		coursDetail2.setText("درس دوم را انتخاب کنید");
		coursDetail.setText("");
		changeCourses.setVisible(false);
		addDetail1(zfDarsSelected);
	}
}

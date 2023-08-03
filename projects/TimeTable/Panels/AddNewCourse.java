package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLEditorKit.Parser;

import TimeTable.Common.Celas;
import TimeTable.Common.Cours;
import TimeTable.Common.Olaviat;
import TimeTable.Common.Ostad;
import TimeTable.Common.Time;
import TimeTable.Common.Vorudi;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.OlaviatManager;
import TimeTable.DataAcsses.OstadManager;
import TimeTable.DataAcsses.coursmanager;
import TimeTable.DataAcsses.vorudiManager;
import TimeTable.Main.Main;
import TimeTable.exception.gException;

public class AddNewCourse extends JInternalFrame implements ActionListener {

	private JLabel Label[] = new JLabel[8];
	private JTextField texts[] = new JTextField[8];
	private String w[] = { "نام درس :", "کد درس :", "جلسات :", "فاصله روز :", "فاصله ساعت :", "زوج و فرد :", "ترم :",
			"گروه :" };
	private String days[] = { "شنبه", "یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه", "جمعه" };

	private JPanel Olaviats;
	private JPanel Inputs;
	private int font = 13;
	private double olaviatCount1 = 0;
	private int olaviatCount2 = 1;
	private JLabel olaviatNumber = new JLabel();
	private int olaviatlistCount = 0;
	private int olaviatlistCount2 = 0;

	private JComboBox Ol[] = new JComboBox[11];

	private JLabel Olaviat2 = new JLabel();
	private JLabel Olaviat3 = new JLabel();
	private JLabel Ostadlabel = new JLabel();
	private JLabel specialCelaslabel = new JLabel();
	private JButton b1 = new JButton();
	private JButton c1 = new JButton();
	private JButton b2 = new JButton();
	private JButton c2 = new JButton();
	private JButton b3 = new JButton();

	private JButton etmam = new JButton();

	private JComboBox specialCelas = new JComboBox();
	private JComboBox Ostad = new JComboBox();
	private Cours c;
	private ArrayList<Celas> ClassList;
	private ArrayList<Ostad> TeacherList;
	private ArrayList<Celas> specialClasses;

	//////////////////////////////////////////// olaviat lists:
	private ArrayList<Celas> OlaviatCelas;
	private ArrayList<Time> OlaviatTime;
	private JCheckBox is1Ghatii[] = new JCheckBox[4];
	private JCheckBox is2Ghatii[] = new JCheckBox[4];
	private JCheckBox is3Ghatii[] = new JCheckBox[4];
	private JComboBox ol1[] = new JComboBox[4];
	private JComboBox ol2[] = new JComboBox[4];
	private JComboBox ol3[] = new JComboBox[4];

	private ArrayList<Olaviat> OlaviatList;

	private int classCount = 0;
	private int teacherCount = 0;
	public Cours CurrentCours;
	public boolean isEdit;

//	private JComboBox<Integer> vorudi=new JComboBox<>();
	private JTextField Term=new JTextField();
	private ArrayList<Olaviat> tempO = new ArrayList<Olaviat>();

	private int width = 700, height = 390;

	public AddNewCourse() {

		super();
		this.setClosable(true);
	///	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(false);
		this.setBounds(200, 150, width, height);
		Inputs = new JPanel();
		Olaviats = new JPanel();
		this.setResizable(true);
		this.add(Olaviats);
		this.add(Inputs);

		this.setLayout(null);
		this.setVisible(true);

		// setOlaviats();
		set();
		Olaviats.setVisible(false);
		Olaviats.disable();
		// set();
		repaint();

	}

	ArrayList<Vorudi>  C2;

	public void set() {

		Olaviats.setVisible(false);
		Inputs.disable();
		Inputs.setVisible(false);
		Inputs = new JPanel();
		Inputs.setBounds(0, 0, width, height);
		Inputs.setLayout(null);
		Inputs.setOpaque(true);

		int u = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				Label[i] = new JLabel();
				Label[i].setBounds(i * 160 + 125, (j * 90) + 20, 80, 60);
				Label[i].setFont(Main.MyFont(11));
				Label[i].setText(w[u++]);
				Inputs.add(Label[i]);
			}
		}
		u = 0;

	//	C2= (ArrayList<Vorudi>) vorudiManager.selectAll();
		Term.setEnabled(false);
		Term.setVisible(false);
	//	vorudi=new JComboBox<>();
		Term=new JTextField();
		
		Term.setBounds(3 * 165 + 35, (0 * 90) + 25, 70, 35);
	//	for (int i = 0; i < C2.size(); i++)
	//		vorudi.addItem(C2.get(i).getSal());		
		
		Inputs.add(Term);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				texts[u] = new JTextField();
				texts[u].setBounds(i * 160 + 30, (j * 90) + 20, 90, 40);
				texts[u].setFont(Main.MyFont(font));
				if (!isEdit)
					if (u == 3 || u == 4)
						texts[u].setEnabled(false);

				if(u==6){
					texts[u].setEnabled(false);
					texts[u].setVisible(false);}
				
				Inputs.add(texts[u++]);
			}
		}

		texts[2].addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				olaviatCount1 = StrToDouble(texts[2].getText());
				if (StrToDouble(texts[2].getText()) > 0) {

					b3.enable();
					b3.setVisible(true);
					olaviatlistCount = (int) StrToDouble(texts[2].getText() + 0 / 5);
					texts[3].enable();

					if (StrToDouble(texts[3].getText()) == 0) {
						texts[4].enable();
					} else {
						texts[4].setText(0 + "");
						texts[4].disable();
					}
				} else {
					b3.disable();
					b3.setVisible(false);
					texts[3].setText(0 + "");
					texts[4].setText(0 + "");
					texts[3].disable();
					texts[4].disable();
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		texts[3].addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

				if (StrToDouble(texts[3].getText()) == 0) {
					texts[4].enable();
				} else {
					texts[4].setText(0 + "");
					texts[4].disable();
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		b2 = new JButton("ثبت بدون اولویت");
		b2.setFont(Main.MyFont(font));
		b2.setBounds(250, 300, 120, 30);
		b2.addActionListener(this);
		b2.setEnabled(true);
		Inputs.add(b2);
		///////////////////////////////////////////////
		c2 = new JButton("بازگشت");
		c2.setFont(Main.MyFont(font));
		c2.setBounds(150, 300, 90, 30);
		c2.addActionListener(this);
		Inputs.add(c2);
		this.add(Inputs);
		///////////////////////////////////////////////

		b3 = new JButton("ثبت اولویتها");
		b3.setFont(Main.MyFont(font));
		b3.setBounds(380, 300, 120, 30);
		b3.setEnabled(true);
		if (isEdit == true || toint(texts[2].getText()) == 0) {
			b3.disable();
			b3.setVisible(false);
		}
		Inputs.add(b3);
		b3.addActionListener(this);
		// namText.disable();

		///////////////////////////////////////////////
		Ostadlabel = new JLabel();
		Ostadlabel.setBounds(580, 190, 190, 30);
		Ostadlabel.setFont(Main.MyFont(font));
		Ostadlabel.setText("استاد :");
		Inputs.add(Ostadlabel);

		Ostad = new JComboBox();
		Ostad.setBounds(420, 190, 140, 30);
		Ostad.setFont(Main.MyFont(font + 3));
		Ostad.setEditable(true);
		AddTeachers();
		for (int i = 0; i < teacherCount; i++) {
			Ostad.addItem(TeacherList.get(i).getFamily());
		}

		Inputs.add(Ostad);
		/////////////////////////////////////////
		specialCelaslabel = new JLabel();
		specialCelaslabel.setBounds(280, 190, 190, 30);
		specialCelaslabel.setFont(Main.MyFont(font));
		specialCelaslabel.setText("کلاس خاص :");
		Inputs.add(specialCelaslabel);

		specialCelas = new JComboBox();
		specialCelas.setBounds(120, 190, 140, 30);
		specialCelas.setFont(Main.MyFont(font + 3));
		specialCelas.setEditable(true);
		AddSpecialClasses();
		for (int i = 0; i < specialClasses.size(); i++) {
			specialCelas.addItem(specialClasses.get(i).getNam());
		}

		Inputs.add(specialCelas);
		repaint();
	}

	void setOlaviats() throws SQLException {

		Olaviats.disable();
		Olaviats.setVisible(false);
		Olaviats = new JPanel();
		Olaviats.setOpaque(false);
		Olaviats.setBounds(0, 0, width, height);
		Olaviats.setLayout(null);

		// namLabel.disable();
		JLabel namLabel1 = new JLabel();
		namLabel1.setBounds(340, 40, 140, 30);
		namLabel1.setFont(Main.MyFont(font));
		namLabel1.setText(" اولویت اول");
		namLabel1.setEnabled(true);
		namLabel1.setOpaque(true);
		Olaviats.add(namLabel1);
		//////////////////////////////////////////////

		Olaviat2 = new JLabel();
		Olaviat2.setBounds(340, 120, 140, 30);
		Olaviat2.setFont(Main.MyFont(font));
		Olaviat2.setText(" اولویت دوم");
		Olaviat2.setVisible(true);
		Olaviat2.setOpaque(true);
		Olaviats.add(Olaviat2);
		//////////////////////////////////////////////

		// namLabel.disable();
		Olaviat3 = new JLabel();
		Olaviat3.setBounds(340, 200, 140, 30);
		Olaviat3.setFont(Main.MyFont(font));
		Olaviat3.setText(" اولویت سوم");
		Olaviat3.setVisible(true);
		Olaviat3.setOpaque(true);
		Olaviats.add(Olaviat3);
		repaint();
		///////////////////////////////////////////////////////

		// namLabel.disable();

		////////////////////////// BOUTENS ////////////////////
		// b.disable();
		b1 = new JButton("ثبت");
		b1.setFont(Main.MyFont(font));
		b1.setBounds(250, 300, 90, 30);
		b1.setEnabled(true);
		b1.addActionListener(this);
		Olaviats.add(b1);
		///////////////////////////////////////////////
		etmam = new JButton("ثبت و اتمام عملیات");
		etmam.setFont(Main.MyFont(font));
		etmam.setBounds(350, 300, 90, 30);
		etmam.disable();
		etmam.setVisible(false);
		etmam.addActionListener(this);
		Olaviats.add(etmam);

		////////////////////////////////////////////////
		c1 = new JButton("بازگشت");
		c1.setActionCommand("b2");
		c1.setFont(Main.MyFont(font));
		c1.setBounds(150, 300, 90, 30);
		c1.addActionListener(this);
		Olaviats.add(c1);
		this.add(Olaviats);
		int i = 1;
		for (int j = 1; j <= 3; j++) {
			ol1[j] = new JComboBox();
			ol1[j].setBounds(j * 120 + (j - 1) * 60, i * 80, 120, 35);
			ol1[j].setFont(Main.MyFont(font));
			ol1[j].setEditable(true);
			Olaviats.add(ol1[j]);
			is1Ghatii[j] = new JCheckBox();
			is1Ghatii[j].setBounds(250 + (j - 1) * 180, i * 85, 20, 20);
			Olaviats.add(is1Ghatii[j]);

		}
		i = 2;
		for (int j = 1; j <= 3; j++) {
			ol2[j] = new JComboBox();
			ol2[j].setBounds(j * 120 + (j - 1) * 60, i * 80, 120, 35);
			ol2[j].setFont(Main.MyFont(font));
			ol2[j].setEditable(true);
			Olaviats.add(ol2[j]);
			is2Ghatii[j] = new JCheckBox();
			is2Ghatii[j].setBounds(250 + (j - 1) * 180, i * 85, 20, 20);
			Olaviats.add(is2Ghatii[j]);

		}
		i = 3;
		for (int j = 1; j <= 3; j++) {
			ol3[j] = new JComboBox();
			ol3[j].setBounds(j * 120 + (j - 1) * 60, i * 80, 120, 35);
			ol3[j].setFont(Main.MyFont(font));
			ol3[j].setEditable(true);
			Olaviats.add(ol3[j]);
			is3Ghatii[j] = new JCheckBox();
			is3Ghatii[j].setBounds(250 + (j - 1) * 180, i * 85, 20, 20);
			Olaviats.add(is3Ghatii[j]);

		}

		setOlaviatTexts();
		setShanbeTimes();

		olaviatNumber.disable();
		olaviatNumber.setVisible(false);
		olaviatNumber = new JLabel();
		if (!isEdit || olaviatlistCount == 0)
			olaviatNumber.setText( "ساعت"+olaviatCount2);
		else
			olaviatNumber.setText("ساعت"+olaviatlistCount2 );//
		olaviatNumber.setBounds(100, 20, 100, 40);
		Olaviats.add(olaviatNumber);
		this.add(Olaviats);
		/////////////////// COMBOBOX ///////////////////////////

		Inputs.setVisible(false);
		repaint();
	}

	private void setShanbeTimes() throws SQLException {
		ol1[3].removeAllItems();
		ol2[3].removeAllItems();
		ol3[3].removeAllItems();
		OlaviatTime = (ArrayList<Time>) OlaviatManager.getTimes("شنبه",92);
		for (int i = 0; i < OlaviatTime.size(); i++) {
			ol1[3].addItem(OlaviatTime.get(i).getNam());
			ol2[3].addItem(OlaviatTime.get(i).getNam());
			ol3[3].addItem(OlaviatTime.get(i).getNam());
		}
	}

	private void setOlaviatTexts() throws SQLException {
		OlaviatCelas = (ArrayList<Celas>) ClassManager.selectAll();
		// Ol[1].addItem("");
		for (int i = 0; i < OlaviatCelas.size(); i++) {
			ol1[1].addItem(OlaviatCelas.get(i).getNam());
			ol2[1].addItem(OlaviatCelas.get(i).getNam());
			ol3[1].addItem(OlaviatCelas.get(i).getNam());
		}
		// OlaviatTime=(ArrayList<Time>) OlaviatManager.getTimes(days[i]);

		for (int i = 0; i <= 6; i++) {
			ol1[2].addItem(days[i]);
			ol2[2].addItem(days[i]);
			ol3[2].addItem(days[i]);

		}
		ol1[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					ol1[3].removeAllItems();
					OlaviatTime = (ArrayList<Time>) OlaviatManager.getTimes((String) ol1[2].getSelectedItem(),92);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < OlaviatTime.size(); i++)
					ol1[3].addItem(OlaviatTime.get(i).getNam());

			}
		});
		ol2[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ol2[3].removeAllItems();
					OlaviatTime = (ArrayList<Time>) OlaviatManager.getTimes((String) ol2[2].getSelectedItem(),92);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < OlaviatTime.size(); i++)
					ol2[3].addItem(OlaviatTime.get(i).getNam());

			}
		});
		ol3[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ol3[3].removeAllItems();
					OlaviatTime = (ArrayList<Time>) OlaviatManager.getTimes((String) ol3[2].getSelectedItem(),92);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < OlaviatTime.size(); i++)
					ol3[3].addItem(OlaviatTime.get(i).getNam());

			}
		});

	}

	public void setOlaviatList(int s) {
		OlaviatList = (ArrayList<Olaviat>) OlaviatManager.selectAllOlaviats((int) CurrentCours.getId(), s);

	}

	public void setOlaviatListCount() throws SQLException {
		olaviatlistCount = OlaviatManager.getTadadSaat((int) CurrentCours.getId());
	}

	public void setForEditOlaviats() {
		// hengame edit ,bad az har bar sabte olaviat bayad chek shavad ke saate
		// badi chand ast va haman setforEditOlaviat shavad
		if (OlaviatList.size() > 0)
			for (int i = 1; i <= 3; i++) {

				if (OlaviatList.get(i - 1).getShomare() == 1) {
					System.out.println(OlaviatList.get(i - 1).getId());

					ol1[1].setSelectedItem(OlaviatList.get(i - 1).getCelasName());
					ol1[2].setSelectedItem(OlaviatList.get(i - 1).getRuz());
					ol1[3].setSelectedItem(OlaviatList.get(i - 1).getTimeName());
					is1Ghatii[1].setSelected(OlaviatList.get(i - 1).isCelasp());
					is1Ghatii[2].setSelected(OlaviatList.get(i - 1).isRuzp());
					is1Ghatii[3].setSelected(OlaviatList.get(i - 1).isTimesp());

				}
				if (OlaviatList.get(i - 1).getShomare() == 2) {

					ol2[1].setSelectedItem(OlaviatList.get(i - 1).getCelasName());
					ol2[2].setSelectedItem(OlaviatList.get(i - 1).getRuz());
					ol2[3].setSelectedItem(OlaviatList.get(i - 1).getTimeName());
					is2Ghatii[1].setSelected(OlaviatList.get(i - 1).isCelasp());
					is2Ghatii[2].setSelected(OlaviatList.get(i - 1).isRuzp());
					is2Ghatii[3].setSelected(OlaviatList.get(i - 1).isTimesp());
				}
				if (OlaviatList.get(i - 1).getShomare() == 3) {

					ol3[1].setSelectedItem(OlaviatList.get(i - 1).getCelasName());
					ol3[2].setSelectedItem(OlaviatList.get(i - 1).getRuz());
					ol3[3].setSelectedItem(OlaviatList.get(i - 1).getTimeName());
					is3Ghatii[1].setSelected(OlaviatList.get(i - 1).isCelasp());
					is3Ghatii[2].setSelected(OlaviatList.get(i - 1).isRuzp());
					is3Ghatii[3].setSelected(OlaviatList.get(i - 1).isTimesp());
				}
			}

	}

	public void setForEdit() {

		if (CurrentCours.getTedadjalasat() > 0) {
			b3.enable();
			b3.setVisible(true);
		} else {

			b3.disable();
			b3.setVisible(false);
		}

		
	//	vorudi.removeAllItems();
		
	//	C2= (ArrayList<Vorudi>) vorudiManager.selectAll();
	//	for (int i = 0; i < C2.size(); i++)
	//		vorudi.addItem(C2.get(i).getSal());
	//	vorudi.setSelectedItem(CurrentCours.getVorudiSal());
		
		Term.setText(CurrentCours.getTerm()+"");
		this.texts[0].setText(CurrentCours.getNam());
		this.texts[1].setText(CurrentCours.getCode());
		this.texts[2].setText(CurrentCours.getTedadjalasat() + "");
		this.texts[3].setText(CurrentCours.getFaseleruz() + "");
		this.texts[4].setText(CurrentCours.getFaselesaat() + "");
		this.texts[5].setText(CurrentCours.getZojfard()+"");
		this.texts[7].setText(CurrentCours.getGoruh() + "");
		Ostad.setSelectedItem(CurrentCours.getOstadFamily());
		this.specialCelas.setSelectedItem(CurrentCours.getSpecialClass());

		if (StrToDouble(texts[2].getText()) <= 1) {
			texts[3].disable();
			texts[4].disable();
		}
		if (StrToDouble(texts[3].getText()) >= 1)
			texts[4].disable();

		olaviatCount1 = StrToDouble(texts[2].getText());
		olaviatCount2 = 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().matches("ثبت و اتمام عملیات")) {

			if (!isEdit) {

				coursmanager.Insert(c);
				for (int i = 0; i < tempO.size() - 3; i++) {
					OlaviatManager.Insert(tempO.get(i), c);
				}
			}

			else {

				setCoursAtributes();

				coursmanager.Edit(c);

				try {
					setOlaviatListCount();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (olaviatlistCount > 0) {
					OlaviatManager.delete((int) c.getId());

					try {
						for (int i = 0; i < tempO.size() - 3; i++) {
							//OlaviatManager.Edit(c, tempO.get(i));
							OlaviatManager.Insert(tempO.get(i), c);
							System.out.println("i: " + i);
						}
					} catch (Exception j) {
						// TODO: handle exception
					}
				} else {
					for (int i = 0; i < tempO.size(); i++) {
						OlaviatManager.Insert(tempO.get(i), c);
					}
				}
			}

			Main.setItempanel("Cours");
			Main.GoToForm(0);
		}

		if (e.getActionCommand().matches("ثبت")) {

			try {

				if (!isEdit || olaviatlistCount == 0) {

					if (olaviatCount2 <= ((int) (olaviatCount1 + 0.5))) {
						InsertOlaviats();
						olaviatCount2++;
						olaviatlistCount2++;
						if (olaviatCount2 <= ((int) (olaviatCount1 + 0.5)))
							setOlaviats();
						System.out.println("olaviatCount2: " + olaviatCount2 + " olaviatCount1: " + olaviatCount1);
					}

					if (olaviatCount2 > ((int) (olaviatCount1 + 0.5))) {
						etmam.enable();
						etmam.setVisible(true);
						b1.disable();
						c1.disable();
						b1.setVisible(false);
						c1.setVisible(true);
					}

				} else {

					if (olaviatlistCount2 <= olaviatlistCount) {
						InsertOlaviats();
						olaviatlistCount2++;

						if (olaviatlistCount2 <= olaviatlistCount) {
							// InsertOlaviats();
							setOlaviatList(olaviatlistCount2);
							setOlaviats();
							setForEditOlaviats();
						} else {
							InsertOlaviats();
							etmam.enable();
							etmam.setVisible(true);
							b1.disable();
							c1.disable();
							b1.setVisible(false);
							c1.setVisible(true);

						}

					}

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (e.getActionCommand().matches("ثبت اولویتها")) {
			// s Olaviats.setVisible(true);
			setCoursAtributes();

			try {
				if (isEdit)
					setOlaviatListCount();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			olaviatCount2 = 1;//
			olaviatCount1 = StrToDouble(texts[2].getText());
			
	
			tempO.clear();
			try {

//				if (olaviatlistCount != olaviatCount1) {
//
//					OlaviatManager.delete((int) c.getId());
//				}
				setOlaviatListCount();
				// if (!isEdit)
				// coursmanager.Insert(c);
				// else {
				// coursmanager.Edit(c);
				//
				// }
				// Main.setItempanel("Cours");

				// s setCoursAtributes();

				if (isEdit) {
					c = CurrentCours;
					if (olaviatlistCount > 0) {
						setOlaviatList(1);
						olaviatlistCount2 = 1;
						setOlaviats();
						setForEditOlaviats();
					} else {
						olaviatlistCount2 = 1;
						olaviatCount2 = 1;
						setCoursAtributes();
						setOlaviats();
					}

				} else {
					setCoursAtributes();
					setOlaviats();
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Inputs.setVisible(false);

		}

		if (e.getActionCommand().matches("ثبت بدون اولویت")) {
			setCoursAtributes();
			if (!isEdit)
				coursmanager.Insert(c);
			else {
				coursmanager.Edit(c);

			}
			if (isEdit)
				OlaviatManager.delete((int) c.getId());

			Main.setItempanel("Cours");
			Main.GoToForm(0);

		}

		if (e.getActionCommand().matches("بازگشت")) {
			Main.GoToForm(0);
			Main.setItempanel("Cours");

		}

		if (e.getActionCommand().matches("b2")) {
			System.out.println(olaviatCount2);
			if (olaviatlistCount2 > 1) {
				try {
					setOlaviats();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				olaviatlistCount2--;

				setOlaviatList(olaviatlistCount2);
				setForEditOlaviats();
			} else {

				Olaviats.setVisible(false);
				Inputs.setVisible(true);

			}
		}
	}

	private void InsertOlaviats() throws SQLException {

		Olaviat o1 = new Olaviat();
		o1.setRuz((String) ol1[2].getSelectedItem());
		o1.setCelasName((String) ol1[1].getSelectedItem());
		o1.setSaat(olaviatlistCount2);
		o1.setTimeName((int) ol1[3].getSelectedItem());
		o1.setCelasp(is1Ghatii[1].isSelected());
		o1.setRuzp(is1Ghatii[2].isSelected());
		o1.setTimesp(is1Ghatii[3].isSelected());
		o1.setShomare(1);

		Olaviat o2 = new Olaviat();
		o2.setRuz((String) ol2[2].getSelectedItem());
		o2.setCelasName((String) ol2[1].getSelectedItem());
		o2.setSaat(olaviatlistCount2);
		o2.setTimeName((int) ol2[3].getSelectedItem());
		o2.setCelasp(is2Ghatii[1].isSelected());
		o2.setRuzp(is2Ghatii[2].isSelected());
		o2.setTimesp(is2Ghatii[3].isSelected());
		o2.setShomare(2);

		Olaviat o3 = new Olaviat();
		o3.setRuz((String) ol3[2].getSelectedItem());
		o3.setCelasName((String) ol3[1].getSelectedItem());
		o3.setSaat(olaviatlistCount2);
		o3.setTimeName((int) ol3[3].getSelectedItem());
		o3.setCelasp(is3Ghatii[1].isSelected());
		o3.setRuzp(is3Ghatii[2].isSelected());
		o3.setTimesp(is3Ghatii[3].isSelected());
		o3.setShomare(3);

		tempO.add(o1);
		tempO.add(o2);
		tempO.add(o3);

	}

	public void setCoursAtributes() {
		c = new Cours();

		c.setNam(this.texts[0].getText());
		c.setCode(this.texts[1].getText());
		c.setTedadjalasat(StrToDouble(this.texts[2].getText()));
		c.setFaseleruz(StrToDouble(this.texts[3].getText()));
		c.setFaselesaat(StrToDouble(this.texts[4].getText()));
		c.setZojfard(toint(this.texts[5].getText()));
	//	c.setVorudiSal(toint(this.vorudi.getText()));//SelectedItem().toString()));
		c.setGoruh(toint(this.texts[7].getText()));
		c.setTerm(toint(this.Term.getText()));
		c.setOstadFamily(Ostad.getSelectedItem() + "");
		c.setSpecialClass(specialCelas.getSelectedItem()+"");

		// c.set

	}

	private void Addclasses() {
		ClassList = (ArrayList<Celas>) ClassManager.selectAll();
		classCount = ClassList.size();

	}

	private void AddTeachers() {
		TeacherList = (ArrayList<Ostad>) OstadManager.selectAll();
		teacherCount = TeacherList.size();

	}
	
	private void AddSpecialClasses() {
		specialClasses = (ArrayList<Celas>) ClassManager.selectAllSpecial();

	}

	static int toint(String a) {
		int x = 0;
		for (int i = 0; i < a.length(); i++)
			x = x * 10 + (a.charAt(i) - '0');
		return x;
	}

	public static double StrToDouble(String s) {
		float a = 0, b = 0, u = 0, c = (float) 0.1;
		int i = 0, j = 0;
		int h = 0;
		for (int g = 0; g < s.length(); g++)
			if (s.charAt(g) == '.')
				h = 1;

		if (h == 1) {
			while (s.charAt(i) != '.')
				a = a * 10 + s.charAt(i++) - '0';

			for (j = i + 1; j < s.length(); j++)
				b = b * 10 + s.charAt(j) - '0';
			for (i = 0; i < j - 1; i++)
				c *= 10;

			b = b / c;

			u = a + b;
		}

		else
			u = toint(s);
		return u;
	}
}
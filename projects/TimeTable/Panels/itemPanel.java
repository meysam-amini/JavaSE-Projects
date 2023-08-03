package TimeTable.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import TimeTable.Common.Ostad;
import TimeTable.Common.Sakhteman;
import TimeTable.Common.Time;
import TimeTable.Common.Celas;
import TimeTable.Common.Cours;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.DataAccessUC;
import TimeTable.DataAcsses.OstadManager;
import TimeTable.DataAcsses.SakhtemanManager;
import TimeTable.DataAcsses.TimeManager;
import TimeTable.DataAcsses.coursmanager;
import TimeTable.Main.Main;

public class itemPanel extends JComponent implements ActionListener {

	private JButton b[] = new JButton[3];
	private String w[] = { "افزودن", "ویرایش", "حذف" };
	private JButton edit = new JButton();
	private JButton delete = new JButton();
	private JComponent L = new JComponent() {
	};
	private JLabel items = new JLabel();
	private static JButton[] item;
	private static int itemsCount = 0;
	private ArrayList<Ostad> OstadList;
	private ArrayList<Time> TimeList;
	private ArrayList<Celas> CelasList;
	private ArrayList<Cours> CoursList;
	private ArrayList<Sakhteman> SakhtemanList;
	private String listTexts[];
	private String I = "Ostad";
	private JComponent P;
	private String currentObj;
	private AddNewCourse Addc;
	private AddNewCelas Addcelas;
	private AddNewTeacher Addteacher;
	private AddNewSakhteman AddSakhteman;
	private Cours CurrentCours;
	private Ostad CurrentOstad;
	private Celas CurrentCelas;
	private Sakhteman CurrentSakhteman;
	private Object CurrentObject;
	private int editFlag = -1;
	JLabel Q[]=new JLabel[3];

	private JTextField search;
	private JButton searchButton;
	private static int searchFlag = 0;
	private String q="";

	
	private JLabel background=new JLabel();
	private JButton Obj=new JButton();
	
	
	public itemPanel(String I, AddNewCourse addc, AddNewCelas addcelas, AddNewTeacher addteacher ,			AddNewSakhteman addSakhteman) {
		super();

		this.Addc = addc;
		this.Addcelas = addcelas;
		this.Addteacher = addteacher;
		this.AddSakhteman = addSakhteman;
		this.I = I;
		this.setBounds(0, 100, 1100, 500);
		// this.setBackground(Color.gray);
		this.setLayout(null);
		this.setOpaque(false);

		P = new JComponent() {
		};
		L = new JComponent() {
		};
		P.add(L);
		this.add(P);

		search = new JTextField();
		search.setText("جستجو");
		search.setBounds(282, 20, 590, 35);
		search.setEditable(true);
		search.setFont(Main.MyFont(13));
		search.setBackground(Color.white);
		search.setActionCommand("searchText");
		search.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				Search();

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.add(search);

		JButton sb = new JButton();
		sb = new JButton();
		sb.setBounds(872, 20, 35, 35);
		sb.setActionCommand("delSearch");
		sb.addActionListener(this);
		sb.setLayout(null);
		JLabel sbl=new JLabel();
		sbl.setOpaque(false);
		sbl.setBounds(0, 1, 35, 35);  //QreferredSize(new Dimension(60, 60));;
		sbl.setLayout(new FlowLayout());
		URL y2 = getClass().getResource("../images/delSearch.png");
		ImageIcon mm = new ImageIcon(y2);
		sbl.setIcon(mm);
		sb.add(sbl);
		this.add(sb);

		searchButton = new JButton();
		searchButton.setBounds(818, 20, 110, 35);
		searchButton.setBackground(Color.lightGray);
		searchButton.setText("جستجو");
		searchButton.addActionListener(this);
		//this.add(searchButton);

		set(I, "");
	}

	public void Search() {
		searchFlag = 1;
		set(I, search.getText());
	}

	public void set(String I, String q) {
		this.I = I;
		this.q=q;
		P.disable();
		P.setVisible(false);
		P = new JComponent() {
		};
		P.setLayout(new FlowLayout());
		P.setBounds(280, 50, 800, 500);

		// P.setBackground(Color.yellow);
		// P.setOpaque(true);
		L.disable();
		L.setVisible(false);
		L = new JComponent() {
		};
		L.setLayout(new FlowLayout());
		L.setPreferredSize(new Dimension(170, 400));

		for (int i = 0; i < 3; i++) {
			b[i] = new JButton();
			b[i].setText(w[i]);
			b[i].setPreferredSize(new Dimension(110, 90));
			b[i].setBackground(Color.lightGray);

			b[i].setFont(new Font("Arial", 1, 20));
			b[i].addActionListener(this);
			
			Q[i] = new JLabel();
			Q[i].setOpaque(false);
			Q[i].setBounds(0, 3, 90, 90);  //QreferredSize(new Dimension(60, 60));;
			Q[i].setBackground(Color.red);
			Q[i].setLayout(new FlowLayout());
			URL y2 = getClass().getResource("../images/b_"+i+".png");
			ImageIcon mm = new ImageIcon(y2);
			Q[i].setIcon(mm);
			b[i].add(Q[i]);
			
			
			
			L.add(b[i]);
		}
		P.add(L);

		items.disable();
		items.setVisible(false);
		items = new JLabel();
		items.setOpaque(true);
		items.setBackground(Color.lightGray);
		items.setLayout(null);
		getItems();

		
		if (searchFlag == 1) {
			int cSearcehd = 0;
			String listTexts2[];
			for (int i = 0; i < itemsCount; i++) {
				if (listTexts[i].indexOf(q) != -1)
					cSearcehd++;
			}//
			int cC = 0;
			listTexts2 = new String[cSearcehd];
			for (int j = 0; j < itemsCount; j++) 
				if (listTexts[j].indexOf(q) != -1){
					listTexts2[cC++] = listTexts[j];
				
				
			}
			listTexts = listTexts2;
			itemsCount = cC;
			itemsCount2=cC;
		}
		

		int height;
		if (itemsCount < 1) {
			height = 400;
			items.setFont(Main.MyFont(30));
			items.setText(
					"                            Not Found");
		} else {
			height = itemsCount * 40;
			items.setText("");
		}

		
		

		item = new JButton[itemsCount];
		for (int i = 0; i < itemsCount; i++) {
			item[i] = new JButton();
			// item[i].setBackground(Color.darkGray);
			item[i].setText(listTexts[i]);
			// item[i].setEnabled(false);
			// item[i].setOpaque(false);
			item[i].setBounds(50, (i * 35) + 10, 500, 30);
			item[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					editFlag = 1;
					currentObj = e.getActionCommand();
					setObj();//
					setCurrentObj();
					// System.out.println(currentObj.toString());
				}
			});
			items.add(item[i]);

		}
		background.disable();
		background.setVisible(false);
		 background=new JLabel(); //JButton();
		 background.setBackground(Color.white);
		background.setOpaque(false);
		background.setBounds(30, 222, 230, 230);  //QreferredSize(new Dimension(60, 60));;
		background.setLayout(new FlowLayout());
		URL y2 = getClass().getResource("../images/"+I+".png");
		ImageIcon mm = new ImageIcon(y2);
		background.setIcon(mm);
		this.add(background);
		//getItems();
	//	setItemCount();
		items.setPreferredSize(new Dimension(520, height));
		JScrollPane pane2 = new JScrollPane(items, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// pane.setBorder(null);

		pane2.setOpaque(true);
		pane2.setPreferredSize(new Dimension(620, 400));
		P.add(pane2);
		P.add(L);
		this.add(P);
		setObj();
		
	}

	private void setObj(){
		Obj.disable();
		Obj.setVisible(false);
		Obj=new JButton();
		Obj.setBackground(Color.white);
		Obj.setBounds(30, 20, 230, 200);
		Obj.setOpaque(true);
	//	this.add(Obj);
	}
	
	private void setItemCount(){
		
	}
	
	
	protected void setCurrentObj() {
		
		getItems();
		if (I.matches("Ostad")) {

			for (int i = 0; i < itemsCount; i++) {
				if (OstadList.get(i).toString().matches(currentObj)) {
					CurrentOstad = OstadList.get(i);
					OstadManager.currentOstadID = (int) (CurrentOstad.getId());
				Obj.setText(CurrentOstad.toString2());
				}
			}
		

		}

		if (I.matches("Cours")) {
			
			for (int i = 0; i < itemsCount; i++) {
				if (CoursList.get(i).toString().matches(currentObj)) {
					CurrentCours = CoursList.get(i);
					coursmanager.currentCoursID = (int) CurrentCours.getId();
					Obj.setText(CurrentCours.toString2());//
				}
			//	CurrentObject = CurrentCours;
			}
		
		}

		if (I.matches("Celas")) {
			for (int i = 0; i < itemsCount; i++) {
				if (CelasList.get(i).toString().matches(currentObj)) {
					CurrentCelas = CelasList.get(i);
					ClassManager.currentCelasID = (int) CurrentCelas.getId();
					Obj.setText(CurrentCelas.toString2());
				}

			}
			
		}

//		if (I.matches("Time")) {
//			for (int i = 0; i < itemsCount; i++) {
//				if (TimeList.get(i).toString().matches(currentObj)) {
//					CurrentTime = TimeList.get(i);
//					TimeManager.currentTimeID = (int) CurrentTime.getId();
//					Obj.setText(CurrentTime.toString2());
//				}
//			}
//
//		}

		if (I.matches("Sakhteman")) {
			for (int i = 0; i < itemsCount; i++) {
				if (SakhtemanList.get(i).toString().matches(currentObj)) {
					CurrentSakhteman = SakhtemanList.get(i);
					SakhtemanManager.currentSakhtemanID = (int) CurrentSakhteman.getId();
					Obj.setText(CurrentSakhteman.toString2());
				}
			}

		}

	}

	private void getItems() {
		if (I.matches("Ostad")) {
			OstadList = (ArrayList<Ostad>) OstadManager.selectAll();
			itemsCount = OstadList.size();
			setListTexts(OstadList);
		}

		if (I.matches("Cours")) {
			CoursList = (ArrayList<Cours>) coursmanager.selectAll();
			itemsCount = CoursList.size();
			setListTexts(CoursList);
		}

		if (I.matches("Celas")) {
			CelasList = (ArrayList<Celas>) ClassManager.selectAll();
			itemsCount = CelasList.size();
			setListTexts(CelasList);
		}

		if (I.matches("Time")) {
			TimeList = (ArrayList<Time>) TimeManager.selectAll();
			itemsCount = TimeList.size();
			setListTexts(TimeList);
		}

		if (I.matches("Sakhteman")) {
			SakhtemanList = (ArrayList<Sakhteman>) SakhtemanManager.selectAll();
			itemsCount = SakhtemanList.size();
			setListTexts(SakhtemanList);
		}
	}

	private void setListTexts(ArrayList a) {
		listTexts = new String[itemsCount];
		for (int i = 0; i < a.size(); i++)
			listTexts[i] = a.get(i).toString();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("افزودن")) {
			add();
			disableItems();
		}

		if (e.getActionCommand().matches("delSearch")) {
			search.setText("");
			set(I, "");
		}

		if (e.getActionCommand().matches("ویرایش")) {
			try {
				disableItems();
				edit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().matches("حذف")){
			delete();
			search.setText("");
			set(I, "");}
	//	set(I, q);

	}

	private void edit() throws SQLException {
		if (editFlag == 1) {
			if (I.matches("Ostad")) {

				Addteacher.isEdit = true;
				Addteacher.set();
				Main.GoToForm(3);
				editFlag = -1;
				Addteacher.currentOstad = this.CurrentOstad;
				Addteacher.setForEdit();
			}

			if (I.matches("Cours")) {
				Addc.isEdit = true;
				Addc.set();
				Main.GoToForm(1);
				editFlag = -1;
				Addc.CurrentCours = this.CurrentCours;
				Addc.setOlaviatListCount();
				Addc.setForEdit();

			}

			if (I.matches("Celas")) {
				Addcelas.isEdit = true;
				Addcelas.set();
				Main.GoToForm(2);
				editFlag = -1;
				Addcelas.currentCelas = this.CurrentCelas;
				Addcelas.setForEdit();

			}

			if (I.matches("Sakhteman")) {
				AddSakhteman.isEdit = true;
				AddSakhteman.set();
				Main.GoToForm(5);
				editFlag = -1;
				AddSakhteman.currentSakhteman = this.CurrentSakhteman;
				AddSakhteman.setForEdit();

			}

//			if (I.matches("Time")) {
//
//				addtime.isEdit = true;
//				addtime.setFirsts();
//				addtime.setForEdit();
//				addtime.set();
//				Main.GoToForm(4);
//				editFlag = -1;
//				addtime.currentTime = this.CurrentTime;
//				// addtime.setForEdit();
//			}
		}
	}

	private void delete() {
		editFlag = -1;
		if (I.matches("Ostad")) {

			deleteOstad();

		}

		if (I.matches("Cours")) {

			deleteCours();
		}

		if (I.matches("Celas")) {

			deleteClass();
		}

		if (I.matches("Sakhteman")) {

			deleteSakhteman();
		}

		if (I.matches("Time")) {

			deleteTime();

		}

	}

	private void add() {
		if (I.matches("Ostad")) {

			Addteacher.isEdit = false;
			Addteacher.set();
			Main.GoToForm(3);
		}

		if (I.matches("Cours")) {

			Addc.isEdit = false;
			Addc.set();
			Main.GoToForm(1);
		}

		if (I.matches("Celas")) {

			Addcelas.isEdit = false;
			Addcelas.set();
			Main.GoToForm(2);
		}

		if (I.matches("Sakhteman")) {

			AddSakhteman.isEdit = false;
			AddSakhteman.set();
			Main.GoToForm(5);
		}

		if (I.matches("Time")) {

//			addtime.isEdit = false;
//			addtime.setFirsts();
//			addtime.timesCount = addtime.listOfTimes.size();
//			addtime.set();
//			Main.GoToForm(4);
//			addtime.setForEdit();
			// editFlag = -1;

		}

	}

	private void deleteOstad() {

		OstadManager.delete(CurrentOstad.getCode().toString());
	}

	private void deleteCours() {

		coursmanager.delete(CurrentCours.getCode().toString(), CurrentCours.getNam().toString(),
				CurrentCours.getGoruh());
	}

	private void deleteClass() {

		ClassManager.delete(CurrentCelas.getNam());
	}

	private void deleteSakhteman() {

		SakhtemanManager.delete(CurrentSakhteman.getNam());
	}

	private void deleteTime() {

	//	TimeManager.delete(CurrentTime.getNam() + "");
	}
private static int itemsCount2=0;
	public static  void disableItems(){
int u=0;
if(searchFlag==1)
	u=itemsCount2;
	else
		u=itemsCount;
		
		for (int i = 0; i < u; i++) {
			item[i].setFocusable(false);
			item[i].setEnabled(false);
		}
	}
	
	public static void enableItems(){
		for (int i = 0; i < itemsCount; i++) {
			item[i].setEnabled(true);
			item[i].setFocusable(true);
		}
	}
}

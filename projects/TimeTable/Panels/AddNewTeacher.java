package TimeTable.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TimeTable.Common.Celas;
import TimeTable.Common.Cours;
import TimeTable.Common.Ostad;
import TimeTable.DataAcsses.OstadManager;
import TimeTable.DataAcsses.coursmanager;
import TimeTable.Main.Main;

public class AddNewTeacher extends JInternalFrame implements ActionListener {

	private myPanel p;
	private JTextField namText = new JTextField();
	private JTextField familyText = new JTextField();
	private JTextField emailText = new JTextField();
	private JTextField codeText = new JTextField();
	private JTextField phoneText = new JTextField();
	private JTextField addresText = new JTextField();
	private JLabel Label[] = new JLabel[8];
	private JTextField texts[] = new JTextField[8];
	private JLabel namLabel = new JLabel();
	private String w[] = { "نام استاد :", "نام خانوادگی :", "کد استاد :" };
	private JPanel Inputs;
	private JButton b1 = new JButton();
	private JButton c1 = new JButton();
	private int font = 13;
	private int width = 550, height = 200;
	private Ostad o;
	public boolean isEdit;
	public Ostad currentOstad;

	public AddNewTeacher() {

		super();
		this.setClosable(true);
	//	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(false);
		this.setBounds(200, 200, width, height);
		Inputs = new JPanel();
		this.setResizable(true);
		this.add(Inputs);
     //   this.setOpaque(false);
		this.setLayout(null);
		this.setVisible(true);

		// setOlaviats();
		set();
		repaint();

	}

	public void set() {
		Inputs.disable();
		Inputs.setVisible(false);
		Inputs = new JPanel();
		Inputs.setBounds(0, 0, width, height);
		Inputs.setLayout(null);
		// Inputs.setBackground(Color.yellow);
		Inputs.setOpaque(true);

		// Inputs.disable();
		int u = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				Label[i] = new JLabel();
				Label[i].setBounds(i * 160 + 125, (j * 90) + 20, 80, 60);
				Label[i].setFont(Main.MyFont(font));
				Label[i].setText(w[u++]);

				Inputs.add(Label[i]);
			}
		}
		u = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				texts[u] = new JTextField();
				texts[u].setBounds(i * 160 + 30, (j * 90) + 20, 90, 40);
				texts[u].setFont(Main.MyFont(font));
				Inputs.add(texts[u++]);
			}
		}
		this.add(Inputs);
		//////////////////////////////////////////////
		// b.disable();
		b1 = new JButton("ثبت");
		b1.setFont(Main.MyFont(font));
		b1.setBounds(70, 100, 90, 30);
		b1.addActionListener(this);
		this.add(b1);
		///////////////////////////////////////////////
		c1 = new JButton("بازگشت");
		c1.setFont(Main.MyFont(font));
		c1.setBounds(200, 100, 90, 30);
		c1.addActionListener(this);
		this.add(c1);
		///////////////////////////////////////////////
	}

	public void setForEdit() {

		this.texts[0].setText(currentOstad.getNam());	
		this.texts[1].setText(currentOstad.getFamily());
		this.texts[2].setText(currentOstad.getCode());
	
		// cours

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().matches("ثبت")) {

			setOstadAtributes();
			if (!isEdit)
				OstadManager.Insert(o);
			else {
				OstadManager.Edit(o);

			}
			Main.setItempanel("Ostad");
			itemPanel.disableItems();
		}

		if (e.getActionCommand().matches("بازگشت")) {

			Main.GoToForm(0);
			Main.setItempanel("Ostad");

		}
	}

	public void setOstadAtributes() {
		o = new Ostad();

		o.setNam(this.texts[0].getText());
		o.setFamily(this.texts[1].getText());
		o.setCode(this.texts[2].getText());
		
		// c.set

	}

}
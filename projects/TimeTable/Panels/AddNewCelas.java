package TimeTable.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TimeTable.Common.Celas;
import TimeTable.Common.Celas;
import TimeTable.Common.Sakhteman;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.SakhtemanManager;
import TimeTable.DataAcsses.coursmanager;
import TimeTable.Main.Main;

public class AddNewCelas extends JInternalFrame implements ActionListener {

	private JTextField className;
	private JComboBox sakhteman;
	private JLabel special;
	private JLabel classNameLabel;
	private JLabel sakhtemanNameLabel;
	private JPanel Inputs;
	private ArrayList<Sakhteman> sakhtemanList;
	private int sakhtemanCount;
	private JButton b1 = new JButton();
	private JButton c1 = new JButton();
	private int font = 13;
	private int width = 350, height = 200;
	private Celas c;
	public boolean isEdit;
	public Celas currentCelas;
	private JCheckBox isSpecial = new JCheckBox();

	public AddNewCelas() {

		super();
		this.setClosable(true);
		// this.setDefaultCloseOperation(close());
		this.setClosable(false);
		this.setBounds(200, 200, width, height);
		Inputs = new JPanel();
		// this.setResizable(true);
		this.setOpaque(true);
		this.add(Inputs);

		this.setLayout(null);
		// this.setVisible(true);

		// setOlaviats();
		set();
		// set();
		repaint();

	}

	private int close() {
		// .enableItems();
		System.out.println("celsa.close");
		// return 2;
		return 0;
	}

	public void set() {

		Inputs.disable();
		Inputs.setVisible(false);
		Inputs = new JPanel();
		Inputs.setBounds(0, 0, width, height);
		Inputs.setLayout(null);
		// Inputs.setBackground(Color.yellow);
		Inputs.setOpaque(true);

		// Inputs.disable()
		classNameLabel = new JLabel();
		classNameLabel.setBounds(275, 20, 90, 40);
		classNameLabel.setFont(Main.MyFont(font));
		classNameLabel.setText("نام کلاس :");

		special = new JLabel();
		special.setBounds(265, 80, 90, 40);
		special.setFont(Main.MyFont(font));
		special.setText("کلاس خاص :");

		isSpecial = new JCheckBox();
		isSpecial.setBounds(220, 80, 90, 40);

		Inputs.add(isSpecial);
		Inputs.add(special);
		Inputs.add(classNameLabel);

		className = new JTextField();
		className.setBounds(180, 20, 90, 40);
		className.setFont(Main.MyFont(font));
		Inputs.add(className);

		b1 = new JButton("ثبت");
		b1.setFont(Main.MyFont(font));
		b1.setBounds(150, 120, 90, 30);
		b1.addActionListener(this);
		b1.setEnabled(true);
		Inputs.add(b1);
		///////////////////////////////////////////////
		c1 = new JButton("بازگشت");
		c1.setFont(Main.MyFont(font));
		c1.setBounds(40, 120, 90, 30);
		c1.addActionListener(this);
		Inputs.add(c1);
		this.add(Inputs);
		///////////////////////////////////////////////

		// namText.disable();

		///////////////////////////////////////////////
		sakhtemanNameLabel = new JLabel();
		sakhtemanNameLabel.setBounds(135, 20, 90, 40);
		sakhtemanNameLabel.setFont(Main.MyFont(font));
		sakhtemanNameLabel.setText("ساختمان :");
		Inputs.add(sakhtemanNameLabel);

		sakhteman = new JComboBox();
		sakhteman.setBounds(20, 20, 110, 40);
		sakhteman.setFont(Main.MyFont(font + 3));
		sakhteman.setEditable(true);
		AddSakhteman();
		for (int i = 0; i < sakhtemanCount; i++) {
			sakhteman.addItem(sakhtemanList.get(i).getNam());
		}
		Inputs.add(sakhteman);

	}

	private void AddSakhteman() {
		sakhtemanList = (ArrayList<Sakhteman>) SakhtemanManager.selectAll();
		sakhtemanCount = sakhtemanList.size();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().matches("ثبت")) {
			setCelasAtributes();
			if (!isEdit)
				ClassManager.Insert(c);
			else {
				ClassManager.Edit(c);

			}
			Main.setItempanel("Celas");
			itemPanel.disableItems();
		}

		if (e.getActionCommand().matches("بازگشت")) {
			Main.GoToForm(0);
			Main.setItempanel("Celas");
		}

	}

	public void setCelasAtributes() {
		c = new Celas();
		c.setSpecial(this.isSpecial.isSelected());
		c.setNam(this.className.getText());
		c.setSakhtemanName(sakhteman.getSelectedItem() + "");

	}

	public void setForEdit() {

		this.className.setText(currentCelas.getNam());
		this.sakhteman.setSelectedItem(currentCelas.getSakhtemanName());
		this.isSpecial.setSelected(currentCelas.isSpecial());

	}

}

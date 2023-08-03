package TimeTable.Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TimeTable.Common.Celas;
import TimeTable.Common.Sakhteman;
import TimeTable.DataAcsses.ClassManager;
import TimeTable.DataAcsses.SakhtemanManager;
import TimeTable.Main.Main;

public class AddNewSakhteman extends JInternalFrame implements ActionListener {

	
	private JTextField namText;//=new JTextField();
	private JLabel namLabel=new JLabel();
	private JButton b=new JButton();
	private JButton b2=new JButton();
	private int font=22;
	private Sakhteman c;
	public boolean isEdit;
	public Sakhteman currentSakhteman;
	private JPanel p;
	
	
	public AddNewSakhteman(){
		super();
	    this.setClosable(true);
	  //  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(false);
	    this.setBounds(400, 300, 300, 200);
	    this.setLayout(null);
	    this.setOpaque(true);
		this.setVisible(true);
		p=new JPanel();
		p.setBounds(0, 0, 400, 250);
		p.setLayout(null);
		p.setOpaque(true);
		set();
	}
	
	public void set(){
		p.disable();
		p.setVisible(false);
		p = new JPanel();
		p.setBounds(0, 0, 400, 250);
		p.setLayout(null);
		// Inputs.setBackground(Color.yellow);
		p.setOpaque(true);
		
		namText=new JTextField();
		namText.setOpaque(true);
		namText.setBounds(70, 20, 140, 30);
		namText.setFont(Main.MyFont(font));
		p.add(namText);
		///////////////////////////////////////////////
		namLabel=new JLabel();
		namLabel.setBounds(230, 20, 100, 30);
		namText.setFont(Main.MyFont(font));
		namLabel.setText("نام ساختمان:");
		p.add(namLabel);
		//////////////////////////////////////////////
		b=new JButton("ثبت");
		b.setFont(Main.MyFont(16));
		b.setBounds(120, 70, 90, 30);
		b.addActionListener(this);
		p.add(b);
		///////////////////////////////////////////////
		b2=new JButton("بازگشت");
		b2.setFont(Main.MyFont(16));
		b2.setBounds(20, 70, 90, 30);
		b2.addActionListener(this);
		p.add(b2);
		//////////////////////////////////////
		this.add(p);
	}
	
	
	
	public void setSakhtemanAtributes() {
		c = new Sakhteman();
		c.setNam(this.namText.getText());
	
	}

	public void setForEdit() {

		this.namText.setText(currentSakhteman.getNam());

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().matches("ثبت")) {
			setSakhtemanAtributes();
			if (!isEdit)
				SakhtemanManager.Insert(c);
			else {
				SakhtemanManager.Edit(c);

			}
			Main.setItempanel("Sakhteman");
			itemPanel.disableItems();
		}

		if (e.getActionCommand().matches("بازگشت")) {
Main.GoToForm(0);
Main.setItempanel("Sakhteman");

		}
	}
}

package TimeTable.Panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TimeTable.Main.Main;

public class course extends JInternalFrame {

	private myPanel p;
	private JTextField namText = new JTextField();
	private JLabel namLabel = new JLabel();
	private JButton b = new JButton();
	private JComboBox c = new JComboBox();
	private int font=22;

	public course() {

		super();
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 700, 390);
		this.setLayout(null);
		this.setVisible(true);
		set();
	}

	public void set() {
		
		
		namText = new JTextField();
		namText.setBounds(350, 20, 300, 300);
		namText.setFont(Main.MyFont(font));
		this.add(namText);
		
		///////////////////////////////////////////////

		b = new JButton("افزودن درس جدید");
		b.setFont(Main.MyFont(font));
		b.setBounds(70, 20, 180, 30);
		this.add(b);
		///////////////////////////////////////////////
		b = new JButton("ویرایش درس");
		b.setFont(Main.MyFont(font));
		b.setBounds(70, 60, 180, 30);
		this.add(b);
		///////////////////////////////////////////////
		b = new JButton("حذف درس");
		b.setFont(Main.MyFont(font));
		b.setBounds(70, 100, 180, 30);
		this.add(b);
		///////////////////////////////////////////////
	}

}

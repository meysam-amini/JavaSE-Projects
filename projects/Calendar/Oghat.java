package Calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Oghat extends JComponent {

	private JButton t1[];
	private date d;
	private double tool=46;
	private double arz=37;
	private double zone=3.30;
	private JComponent b=new JComponent() {
	};
	
	public Oghat(){
		super();
		this.setOghat();
		this.setLayout(null);
		
	}
	
	
	public void setOghat(){
		t1=new JButton[13];
		b.setVisible(false);
		b=new JComponent() {
		};
		b.setOpaque(true);
		b.setBounds(0, 0, 410, 90);
		b.setLayout(new FlowLayout());
		
		
		for(int i=0;i<12;i++){
			t1[i]=new JButton();
			t1[i].setOpaque(true);
			t1[i].setPreferredSize(new Dimension(62, 35));
			t1[i].setForeground(Color.LIGHT_GRAY);
			t1[i].setBackground(Color.black);
			b.add(t1[i]);
		}
		
		Date t=new Date(System.currentTimeMillis());
		System.out.println("Oghat:"+t);
		String s[]={"���� ���","����","���� ���","���","����","���� ����","���"};
		PrayTime p=new PrayTime();
		for(int i=6;i<12;i++)
		t1[i].setText(p.getOghat(t)[i-6]);
		for(int i=0;i<6;i++){
			t1[i].setText(s[i]);
			t1[i].setFont(new Font("tahoma", 3, 6));
			}
		
		this.add(b);
	}
}

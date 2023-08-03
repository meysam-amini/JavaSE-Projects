package Paint.gPaint00;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InfoPanel extends JPanel {
	private JButton[] infos;

	public InfoPanel() {
		super();
		setOpaque(true);
		this.setLayout(new FlowLayout());
		infos = new JButton[10];
		for (int i = 0; i <6; i++) {
			infos[i] = new JButton();
			infos[i].setFont(new Font("tahoma", 1, 12));
			infos[i].setPreferredSize(new Dimension(192, 20));
			infos[i].setOpaque(true);
			infos[i].setBackground(Color.getHSBColor(2893, 889, 7099));
			infos[i].setForeground(Color.getHSBColor(23, 2, 3242));
			if(i==5){
				 JButton about=new JButton("About");
					infos[i].setPreferredSize(new Dimension(196, 21));
				 about.setBackground(Color.getHSBColor(2893, 889, 7099));
					about.setBounds(0, 0, 196, 21);
					about.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							JFrame a=new JFrame("About");
							a.setBounds(950, 610, 330, 80);
							a.setLayout(null);
							JLabel p=new JLabel();
							p.setOpaque(true);
							p.setBounds(0, 0, 330, 50);
							p.setBackground(Color.getHSBColor(2893, 889, 7099));
							p.setLayout(null);
							p.setText("      By Meysam Amini-University of Bonab-92353105");
							p.setForeground(Color.getHSBColor(23, 2, 3242));
							a.add(p);
							a.setVisible(true);
							a.setResizable(false);
						
							
						}
					});
			        infos[i].setLayout(null);
			        infos[i].add(about);
					about.setForeground(Color.getHSBColor(23, 2, 3242));

			}
			this.add(infos[i]);
		}
       
		
	}
	
	
	

	public void setInfo(String s, int i) {
		infos[i].setText("  "+s);
	}

}

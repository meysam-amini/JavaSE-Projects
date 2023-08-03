package Maze;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MazePanel extends JPanel {
	public static JLabel l[][];
	private int i = 0;
	private Component c;
	private boolean s;
	private boolean F;
	private int clabels = 1;
	private JPanel p = new JPanel();
	private int Lsize = 400;

	public MazePanel() {
	
       
		this.setOpaque(true);
		this.setLayout(null);
		this.add(p);
		this.setmazepanel();


	}

	public void setmazepanel() {
		int f =(int) Math.sqrt(this.clabels) ;
		p.setVisible(false);
		p.disable();
		p = new JPanel();
		p.setBackground(Color.black);
		p.setOpaque(true);
		p.setLayout(new FlowLayout());
		p.setBounds(0, 0, 410, 410);
			Border raisedBorder = BorderFactory.createLineBorder(Color.lightGray);
			p.setBorder(raisedBorder);
		this.add(p);
		l = new JLabel[100][100];
		for (int i = 0; i < f; i++)
		 for(int j=0;j<f;j++){
			l[i][j] = new JLabel();
			l[i][j].setPreferredSize(new Dimension(Lsize, Lsize));
			l[i][j].setOpaque(true);
			l[i][j].setBackground(Color.red);
			if(clabels==1)
			{
				l[i][j].setText("    W E L C O M");
				l[i][j].setFont(new Font("Tahoma", 3, 40));
			}
			else{
				
				l[i][j].setFont(new Font("Tahoma", 8, Lsize/5));
				l[i][j].setForeground(Color.yellow);
			}
				
			l[i][j].addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent arg0) {

				}

				@Override
				public void mouseDragged(MouseEvent e) {

					if (getflag() == 1) {
						if (getCom().getBackground() == Color.red)
							getCom().setBackground(Color.black);
						
						else
							getCom().setBackground(Color.red);
						setflag(0);
					}

				}
			});
			
			l[i][j].addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					setCom(e);
					setflag(1);
				}

				@Override
				public void mouseClicked(MouseEvent e) {

					if (s == true) {
						setStart(e);
						
					}
					if (F == true) {
						setFinish(e);
					}
				}
			});

			p.add(l[i][j]);
		}

	}

	private void setflag(int i) {
		this.i = i;
	}

	private int getflag() {
		return i;
	}

	private void setCom(MouseEvent e) {
		this.c = e.getComponent();
	}

	private Component getCom() {
		return c;
	}

	private void setStart(MouseEvent e) {
		if(e.getComponent().getBackground()==(Color.red)){
		e.getComponent().setBackground(Color.yellow);
		this.s = false;}
	}

	public void Sets(boolean a) {
		this.s = a;
	}

	private void setFinish(MouseEvent e) {
		if(e.getComponent().getBackground()==(Color.red)){
		e.getComponent().setBackground(Color.green);
		this.F = false;}
	}

	public void Setf(boolean a) {
		this.F = a;
	}

	public void setClabels(int i) {
		this.clabels = i;
	}
	
	public int getClabels(){
		return clabels;
	}

	
	public void setLsize(int b){
		Lsize=0;
		while((b*Lsize)<402-(b*5)-3)
			Lsize++;
	}
	
	public void setWay(String s[]){
		int i2=0,j2=0,u=1;
		while(u<toint(s[0])) {
			l[toint1(s[u])][toint2(s[u])].setBackground(Color.cyan);
			u++;
			System.out.println(s[u]+"=>");
			
		}
			
		
	}
	static int toint(String a) {
		int x = 0;
		for (int i = 0; i < a.length(); i++)
			x = x * 10 + (a.charAt(i) - '0');
		return x;
	}
	
	static int toint1(String a) {
		int x = 0,i=0;
		while(a.charAt(i)!=','){
			x = x * 10 + (a.charAt(i) - '0');
			i++;}
		return x;
	}
	
	static int toint2(String a) {
		int x = 0,i=0;
		for (i = 0; i < a.length(); i++)
			if(a.charAt(i)==',')
				break;
		for (int j = i+1; j< a.length(); j++)
			x = x * 10 + (a.charAt(j) - '0');
		return x;
	}
	
}

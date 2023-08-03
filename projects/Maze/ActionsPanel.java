package Maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ActionsPanel extends JPanel {

	private JButton B[] = new JButton[7];
	private Color Choosed;
	private Color other;
	private MazePanel x;
	private int a[][];
	private Point start;
	private Point Fin;
//	private WaysPanel w;

	public ActionsPanel(MazePanel m,WaysPanel W) {
		super();
		this.x = m;
//		this.w=W;
		this.setOpaque(true);
		this.setLayout(new FlowLayout());
		this.setBounds(35, 530, 400, 100);
		for (int i = 0; i < 5; i++) {
			B[i] = new JButton();
			B[i].setBackground(Color.BLACK);
			B[i].setPreferredSize(new Dimension(77, 45));
			B[i].setForeground(Color.lightGray);
			B[i].setFont(new Font("Tahoma", 88, 15));
			B[i].setFocusable(false);
			B[i].setBorderPainted(false);
			B[i].setOpaque(true);
			if (i == 0) {
				B[i].setText("Start");
				B[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						m.Sets(true);

					}
				});
			}
			if (i == 1) {
				B[i].setText("Finish");
				B[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						m.Setf(true);

					}
				});
			}
			if (i == 2) {
				B[i].setText("Wall");

			}
			if (i == 3) {
				B[i].setText("Size");
				B[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String String = JOptionPane
								.showInputDialog("Pleas Enter Ur Text");
						int b = Integer.parseInt(String);
						m.setClabels(b * b);
						m.setLsize(b);
						m.setmazepanel();

					}
				});
			}
			if (i == 4) {
				B[i].setText("Play");
				B[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						int j = m.getClabels(), x = 0, y = 0, b = (int) Math
								.sqrt(j);
						a = new int[b ][b ];

						for (int n = 0; n < b; n++)
						    for(int t=0;t<b;t++){
							/*if (y ==b ) {
								x++;
								y = 0;
							}*/
							System.out.println("x:"+x+"y:"+y);
							if (m.l[n][t].getBackground() == Color.red)
								a[n][t] = 0;
							if (m.l[n][t].getBackground() == Color.black)
								a[n][t] = 1;
							if (m.l[n][t].getBackground() == Color.yellow){
								a[n][t] = 6;
								start=new Point(n,t);
								}
							if (m.l[n][t].getBackground() == Color.green){
								a[n][t] = 9;
								Fin=new Point(n,t);}

						//	y++;
						}

						setWalls(a);
					//	aroundMatris(a, b);
					//	show(a,b+2);
					//	W.showWays(a);
						
					}
				});

			}

			this.add(B[i]);
		}

	}
	
	public void setWalls(int a[][]){
		int c=0;
	
		for(int i=0;i<a.length;i++)
			for(int j=0;j<a.length;j++)
				if(a[i][j]==1)
					c++;
		System.out.println("c:"+c);
		int i2=0,j2=0;
		int b[][]=new int[c][2];
		for(int i=0;i<a.length;i++)
			for(int j=0;j<a.length;j++)
				if(a[i][j]==1){
					b[i2][0]=i;
					b[i2][1]=j;
					i2++;
					if(i2==c)
						break;
				}
					
		AStar A = new AStar();
		String S[] ;
		System.out.println("walls:");
		for(int i=0;i<b.length;i++){
			for(int j=0;j<2;j++)
				System.out.println(b[i][j]);
			System.out.println();}
		System.out.println("lengtha:"+a.length+" "+"Fin:"+Fin+" "+"start:"+start);
		S = A.test(2, a.length, a.length, Fin.x, Fin.y, start.x, start.y, b);
		int u=1;
	/*	while(S[u].charAt(0)!=' ') {
			System.out.print(S[u] + "=>");
			u++;
			
		}*/
	x.setWay(S);		
	}

	private void aroundMatris(int a[][], int n) {
		int i = 1, j = 1, q = 0, c = 1;
		while (j < n - q)
			a[i][j++] = c;
		while (i < n - q)
			a[i++][j] = c;
		while (j > q + 1)
			a[i][j--] = c;
		while (i > q + 1)
			a[i--][j] = c;
		q++;
	}

	private void show(int a[][], int b) {
		for (int i = 1; i <= b; i++) {
			for (int j = 1; j <= b; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
	}
}

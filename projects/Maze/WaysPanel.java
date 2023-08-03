package Maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Container;

public class WaysPanel extends JPanel {
	private Point p[] = new Point[10000000];
	private Point Nodes[] = new Point[1000000];
	private Point start;
	private Point end;
	private int c = 0;
	private int n;
	private JLabel l[] = new JLabel[1000];
	private JLabel Label = new JLabel();

	public WaysPanel() {
		super();
		this.setLayout(new FlowLayout());
		Label.setOpaque(true);
		Label.setBackground(Color.black);
		Label.setPreferredSize(new Dimension(160, 3000));
		Label.setLayout(new FlowLayout());

		for (int i = 0; i < 100; i++) {
			l[i] = new JLabel();
			l[i].setPreferredSize(new Dimension(150, 150));
			l[i].setBackground(Color.red);
			l[i].setOpaque(true);

			Label.add(l[i]);

		}
		this.add(Label);
	}

	public void showWays(int a[][]) {
		c=0;n=0;
		int i = 0, j = 0;
		for (i = 0; i < a.length - 1; i++)
			for (j = 0; j < a.length - 1; j++) {
				if (a[i][j] == 6)
					start = new Point(i, j);
				if (a[i][j] == 9)
					end = new Point(i, j);
			}

		a[start.x][start.y] = 0;
		a[end.x][end.y] = 0;
		for (i = 1; i < a.length - 1; i++)
			for (j = 1; j < a.length - 1; j++) {
				if (IsNod(a, new Point(i, j)) == true)
					Nodes[n++] = new Point(i - 2, j - 2);
			}

		for (i = 0; i < n; i++)
			System.out.println(Nodes[i]);

		/*
		 * p[c++] = new Point(i, j); while (wayFinded()==false) {
		 * 
		 * if( IsNod(a,new Point(p[c].x,p[c].y))) Nodes[n++]=new
		 * Point(p[c].x,p[c].y);
		 * 
		 * 
		 * 
		 * p[c++] = (new Point(i, j + 1));
		 * 
		 * }
		 * 
		 * this.showresult(a, p);
		 */
	}

	private boolean wayFinded() {
		return false;
	}

	private void GoRight(Point P) {
		int x = P.x, y = P.y;
		p[c++] = new Point(x, y + 1);
	}

	private void GoLeft(Point P) {
		int x = P.x, y = P.y;
		p[c++] = new Point(x, y - 1);
	}

	private void GoDown(Point P) {
		int x = P.x, y = P.y;
		p[c++] = new Point(x + 1, y);
	}

	private void GoUp(Point P) {
		int x = P.x, y = P.y;
		p[c++] = new Point(x - 1, y);
	}

	private boolean IsNod(int a[][], Point p) {
		int x = p.x, y = p.y, c = 0;
		if (a[x][y] == 0) {
			if (a[x][y + 1] == 0 && a[x + 1][y] == 0)
				return true;
			if (a[x - 1][y] == 0 && a[x][y + 1] == 0)
				return true;
			if (a[x][y - 1] == 0 && a[x - 1][y] == 0)
				return true;
			if (a[x + 1][y] == 0 && a[x][y - 1] == 0)
				return true;
		}
		return false;
	}

	private void showresult(int a[][], Point p[]) {
		for (int i = 0; i < p.length; i++)
			a[p[i].x][p[i].y] = 4;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++)
				System.out.print(a[i][j]);
			System.out.println();
		}

	}
}

package Paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class gPen extends gShape {
	public Point points[] = new Point[10000000];
	public int cPoints;
	private Color c;
	private float d; 

	public gPen(Color ShapeColor, float width, int dashtype) {
		cPoints = 0;
		this.c = ShapeColor;
		this.d=width;
		setDashtype(dashtype);
	}

	public void Draw(Graphics g) {
		g.setColor(c);
		 Graphics2D g2d = (Graphics2D) g;
		    float strokeThickness = d;
		    BasicStroke stroke = new BasicStroke(strokeThickness);
		    g2d.setStroke(stroke);
		for (int i = 0; i < cPoints - 1; i++)
			g.drawLine(points[i].x, points[i].y, points[i + 1].x,
					points[i + 1].y);

	}

	public void setNextPoint(Point p) {
		points[cPoints++] = new Point(p);
		if(cPoints==100000)
			cPoints=0;
	}

}

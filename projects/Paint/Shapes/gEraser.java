package Paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class gEraser extends gShape {
	public Point points2[] = new Point[10000000];
	public int cPoints;
	private Color c;
	private float d; 

	
	public gEraser(Color ShapeColor, float width, int dashtype) {
		cPoints = 0;
		this.c = ShapeColor;
		this.d=(width);
		setDashtype(dashtype);
	}

	public void Draw(Graphics g) {
		g.setColor(Color.white);
		 Graphics2D g2d = (Graphics2D) g;
		    float strokeThickness = d+5;
		    BasicStroke stroke = new BasicStroke(strokeThickness);
		    g2d.setStroke(stroke);
		for (int i = 0; i < cPoints - 1; i++)
			g.drawLine(points2[i].x, points2[i].y, points2[i + 1].x,
					points2[i + 1].y);

	}

	public void setNextPoint2(Point p) {
		points2[cPoints++] = new Point(p);
		if(cPoints==100000)
			cPoints=0;
	}

}

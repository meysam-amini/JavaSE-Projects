package Paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class gSpray extends gShape {
	public Point points[] = new Point[1000000];
	public int cPoints;
	private Color c;

	public gSpray(Color ShapeColor, int width, int dashtype) {
		cPoints = 0;
		this.c = ShapeColor;
		setWidth(width);
		setDashtype(dashtype);
	}

	public void Draw(Graphics g) {

		g.setColor(c);
		
		for (int i = 0; i < cPoints - 1; i++) {

			g.fillOval(points[i].x , points[i].y , 2,2);

		}
	}

	public void setNextPoint(Point p) {
		points[cPoints++] = new Point(p);
	}

}

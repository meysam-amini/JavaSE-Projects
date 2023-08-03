package Paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class gLine extends g2Shape
{
	private float d; 

	
	public gLine(int x1, int y1, int x2, int y2, float size, Color bordercolor,
			int dashtype)
	{
		this.setX1(x1);
		this.setY1(y1);
		this.setX2(x2);
		this.setY2(y2);
		this.d=(size);
		this.setDashtype(dashtype);
		this.setBordercolor(bordercolor);
		
	}
	
	
	public void Draw(Graphics g)
	{ Graphics2D g2d = (Graphics2D) g;
    float strokeThickness = d;
    BasicStroke stroke = new BasicStroke(strokeThickness);
    g2d.setStroke(stroke);
		g.setColor(this.getBordercolor());
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	}
}

package Paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class gOval extends gRectShape
{
	private float d; 

	
	public gOval(int x1, int y1, int x2, int y2, float width, Color bordercolor,
			int dashtype, Boolean isFilled, Color backColor)
	{
		this.setX1(x1);
		this.setY1(y1);
		this.setX2(x2);
		this.setY2(y2);
		this.d=(width);
		this.setDashtype(dashtype);
		this.setBordercolor(bordercolor);
		this.setBackcolor(backColor);
		this.setIsFilled(isFilled);
		
	}
	
	
	public void Draw(Graphics g)
	{
		g.setColor(this.getBordercolor());
		 Graphics2D g2d = (Graphics2D) g;
		    float strokeThickness = d;
		    BasicStroke stroke = new BasicStroke(strokeThickness);
		    g2d.setStroke(stroke);
		if (getIsFilled())
		{
			if((getX2()<getX1())&&(getY2()<getY1()))
				g.fillOval(getX2(), getY2(), getX1() - getX2(), getY1() - getY2());
			else

			g.fillOval(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
			
		}
		else{
			if((getX2()<getX1())&&(getY2()<getY1()))
				g.drawOval(getX2(), getY2(), getX1() - getX2(), getY1() - getY2());
			else
			g.drawOval(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
		}
	}
	
}

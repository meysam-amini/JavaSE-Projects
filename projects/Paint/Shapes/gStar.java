package Paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class gStar extends gRectShape
{
	
	private float d; 

	
	public gStar(int x1, int y1, int x2, int y2, float width, Color bordercolor,
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
		 Graphics2D g2d = (Graphics2D) g;
		    float strokeThickness = d;
		    BasicStroke stroke = new BasicStroke(strokeThickness);
		    g2d.setStroke(stroke);
		
		
		g.setColor(this.getBordercolor());
		g.drawLine((getX2()-getX1())/2+getX1(),getY1(),getX1(),getY2());
		g.drawLine(getX1(),getY2(),getX2(),(getY2()-getY1())/2+getY1());
		g.drawLine(getX2(),(getY2()-getY1())/2+getY1(),getX1(),(getY2()-getY1())/2+getY1());
		g.drawLine(getX1(),(getY2()-getY1())/2+getY1(),getX2(),getY2());
		g.drawLine(getX2(),getY2(),(getX2()-getX1())/2+getX1(),getY1());


		

	}
}

package Paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class gShape
{
	private Color bordercolor;
	private float width;
	private int dashtype;
	private Point point1;
	private Point point2;

	
	public void Draw(Graphics g)
	{
		
	}
	
	
	
	public Point getpoint1(){
		return point1;
	}
	
	public Point getpoint2(){
		return point2;
	}
	
	public Color getBordercolor()
	{
		return bordercolor;
	}
	
	public void setBordercolor(Color bordercolor)
	{
		this.bordercolor = bordercolor;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public void setWidth(float width2)
	{
		this.width = width2;
	}
	
	public int getDashtype()
	{
		return dashtype;
	}
	
	public void setDashtype(int dashtype)
	{
		this.dashtype = dashtype;
	}
	
}

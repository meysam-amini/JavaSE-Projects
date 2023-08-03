package Paint.Shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JTextField;

public class gText extends gRectShape
{
	private String text;

	private int  f;
	private Color color;
	
	public gText(String s,int x1, int y1,int size,Color c)
	{
		this.setX1(x1);
		this.setY1(y1);
		this.text=s;
		this.f=size;
		this.color=c;
		
	}
	
	
	public void Draw(Graphics g)
	{
		Font font = new Font("Serif", Font.PLAIN, f+15);
	   g.setColor(color);
		g.setFont(font);
	    g.drawString(text, getX1(), getY1());

	   
		
		
		
		//g.drawString(text, getX1(), getY1());
		
	}
}

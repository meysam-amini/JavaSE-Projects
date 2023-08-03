package TimeTable.Panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class snow  {
	
    private int size;
	private int x;
	private int y;
	private Color c;
	int flag=0;
	
	
	public snow(int x,int y,int size,Graphics g,Color c){
		this.x=x;
		this.y=y;
		this.size=size;
		this.c=c;
		draw(g);
		
	}
	
	public void draw(Graphics g){
		Font font = new Font("Serif", Font.PLAIN, size);
		g.setFont(font);
		g.setColor(c);
		g.drawString(".", x, y);
		
	
		
	}
	

}

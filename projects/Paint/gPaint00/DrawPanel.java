package Paint.gPaint00;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JColorChooser;
import javax.swing.JComponent;

import Paint.Shapes.gArc;
import Paint.Shapes.gEraser;
import Paint.Shapes.gLine;
import Paint.Shapes.gOval;
import Paint.Shapes.gPen;
import Paint.Shapes.gRect;
import Paint.Shapes.gShape;
import Paint.Shapes.gSpray;
import Paint.Shapes.gStar;
import Paint.Shapes.gText;

public class DrawPanel extends JComponent implements MouseMotionListener,
		MouseListener {

	gShape x = null;
	TextField x2 = null;

	private InfoPanel info;
	private int ShapeType = ShapeTools.Line;
	private Color ShapeColor = Color.cyan;
	private boolean filled = true;
	public float size = 2;
	private String StringText = "hi";
	private gShape shapes[] = new gShape[10000000];
	private Point point1[] = new Point[10000000];
	private Point point2[] = new Point[10000000];
	private int cpoints1 = 0, cpoints2 = 0;
	private int cShapesALL = 0;
	private int cShapes = 0;

	private int x1, y1;

	public DrawPanel(InfoPanel info) {
		super();
		this.setBounds(0, 0, 900, 500);
		this.info = info;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);// vital

		//Graphics2D g2 = (Graphics2D) g;

		gInitialize(g);

		for (int i = 0; i < cShapes; i++) {
			shapes[i].Draw(g);
		}

	}

	public void SetShapeTools(int tNO) {
		this.ShapeType = tNO;
	}

	public void deletshape(Point a) {

		for (int i = 0; i < cShapes; i++)
			if ((a.x >= point1[i].x && a.y >= point1[i].y)
					&& (a.x <= point2[i].x && a.y <= point2[i].y)) {
				if(i==cShapes-1){
					cShapes--;
					cShapesALL--;
					cpoints1--;
					cpoints2--;
				}
				else{
					shapes[i] = shapes[cShapes-1];
					cShapes--;
					cShapesALL--;

					point1[i] = point1[cpoints1 - 1];
					point2[i] = point2[cpoints2 - 1];
					cpoints1--;
					cpoints2--;}
				if(cShapes==1){
					cShapes=0;
					cShapesALL=0;
					cpoints1=0;
					cpoints2=0;
				}

				

			}
		repaint();

	}

	public void SetShapeColor(Color a) {

		this.ShapeColor = a;

	}

	public void SetString(String s) {
		this.StringText = s;
	}

	public void setfiiled(boolean e) {
		this.filled = e;
	}

	public void Upersiz() {
		size++;
	}

	public void Lowersize() {
		size--;
		if (size < 0)
			size = 2;
	}

	public void Clear() {
		cShapes = 0;
		repaint();
		cpoints1=0;
		cpoints2=0;
	}

	public void UndoClear() {
		cShapes = cShapesALL;
		repaint();
	}

	public void ClearLast() {
		if (cShapes > 0)
			cShapes--;
		repaint();
	}

	public void UndoLast() {
		if (cShapes < cShapesALL)
			cShapes++;
		repaint();
	}

	private void gInitialize(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0,0, 1183,500);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		info.setInfo("(x,y): (" + e.getX() + "," + e.getY() + ")", 0);

		if (ShapeType == ShapeTools.Pencil) {
			((gPen) shapes[cShapes - 1]).setNextPoint(new Point(e.getX(), e
					.getY()));
			shapes[cShapes++]=x;
			cShapesALL++;
			repaint();
			

		}

		if (ShapeType == ShapeTools.Eraser) {
			((gEraser) shapes[cShapes - 1]).setNextPoint2(new Point(e.getX(), e
					.getY()));
			shapes[cShapes++]=x;
			cShapesALL++;repaint();
		}

		if (ShapeType == ShapeTools.Spray) {
			
			float r;
			for (int u = 1; u <5; u++) {
				 r = 1;	
				r = (int) (Math.random() * (u));
				((gSpray) shapes[cShapes - 1]).setNextPoint(new Point(e.getX()
						+ (int) r , e.getY() + (int) r ));
				

			}shapes[cShapes++]=x;
			cShapesALL++;
repaint();

		}
		
		
		if (ShapeType == ShapeTools.Text) {
			x = new gText(StringText, e.getX(), e.getY(), (int) size,ShapeColor);
			

		}

		if (ShapeType == ShapeTools.Line){
			x = new gLine(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1);
			}

		if (ShapeType == ShapeTools.Rect){
			x = new gRect(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);}
		
		if (ShapeType == ShapeTools.Arc){
			x = new gArc(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);}
		
		
		
		if (ShapeType == ShapeTools.Star){
			x = new gStar(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);
			}
		
		if (ShapeType == ShapeTools.Oval){
			x = new gOval(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);
	
			}
		if(ShapeType != ShapeTools.DeletOne&&ShapeType != ShapeTools.Eraser&&ShapeType != ShapeTools.Pencil&&ShapeType != ShapeTools.Spray){
		shapes[cShapes++]=x;
		cShapesALL++;
		if(cShapes>100000){
			cShapes=0; 
			cShapesALL=0;
		}
		repaint();
		if(cShapes>=2){
		shapes[cShapes-2]=shapes[cShapes-1];
		cShapes--;
		cShapesALL--;}}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		info.setInfo("(x,y): (" + e.getX() + "," + e.getY() + ")", 0);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		info.setInfo("(x1,y1): (" + e.getX() + "," + e.getY() + ")", 1);
		x1 = e.getX();
		y1 = e.getY();

		if (ShapeType == ShapeTools.Pencil) {
			if (ShapeType == ShapeTools.Pencil)
				x = new gPen(ShapeColor, size, 1);

			((gPen) x).setNextPoint(new Point(e.getX(), e.getY()));
			shapes[cShapes++] = x;
			cShapesALL++;
			

			// cPPP++;
		}

		if (ShapeType == ShapeTools.Eraser) {
			if (ShapeType == ShapeTools.Eraser)
				x = new gEraser(ShapeColor, size, 1);

			((gEraser) x).setNextPoint2(new Point(e.getX(), e.getY()));
			shapes[cShapes++] = x;
			cShapesALL++;
			// cPPP++;
		}

		if (ShapeType == ShapeTools.Spray) {
			if (ShapeType == ShapeTools.Spray)
				x = new gSpray(ShapeColor, 1, 1);

			((gSpray) x).setNextPoint(new Point(e.getX(), e.getY()));
			shapes[cShapes++] = x;
			cShapesALL++;
			
			

		}

		if (ShapeType == ShapeTools.DeletOne) {
			this.deletshape(new Point(e.getX(), e.getY()));

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		info.setInfo("(x2,y2): (" + e.getX() + "," + e.getY() + ")", 2);

		if (ShapeType == ShapeTools.Text) {
			x = new gText(StringText, e.getX(), e.getY(), (int) size,ShapeColor);
			point1[cpoints1++] = new Point(x1, y1);
			point2[cpoints2++] = new Point(e.getX()+50, e.getY()+20);

		}

		if (ShapeType == ShapeTools.Line){
			x = new gLine(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1);
			point1[cpoints1++] = new Point(x1, y1);
			point2[cpoints2++] = new Point(e.getX(), e.getY());}

		if (ShapeType == ShapeTools.Rect){
			x = new gRect(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);
			point1[cpoints1++] = new Point(x1, y1);
			point2[cpoints2++] = new Point(e.getX(), e.getY());}
		
		if (ShapeType == ShapeTools.Arc){
			x = new gArc(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);
			point1[cpoints1++] = new Point(x1, y1);
			point2[cpoints2++] = new Point(e.getX(), e.getY());}
		
		
		if (ShapeType == ShapeTools.Star){
			x = new gStar(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);
			point1[cpoints1++] = new Point(x1, y1);
			point2[cpoints2++] = new Point(e.getX(), e.getY());}
		
		if (ShapeType == ShapeTools.Oval){
			x = new gOval(x1, y1, e.getX(), e.getY(), size, ShapeColor, 1,
					filled, ShapeColor);
			point1[cpoints1++] = new Point(x1, y1);
			point2[cpoints2++] = new Point(e.getX(), e.getY());
			
			}
		if(ShapeType != ShapeTools.Pencil&&ShapeType != ShapeTools.Spray&&ShapeType != ShapeTools.DeletOne)
		shapes[cShapes++]=x;
		cShapesALL++;
		repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		info.setInfo("Entered from: (" + e.getX() + "," + e.getY() + ")", 3);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		info.setInfo("Exited from: (" + e.getX() + "," + e.getY() + ")", 4);

	}

}

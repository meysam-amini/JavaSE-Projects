package TimeTable.Panels;

import java.awt.Color;
import java.awt.Graphics;

//import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TimeTable.Main.Main;

public class myPanel extends JPanel{

private	int x = 100;
private	int y = 5;
private	int flag = 0;
private	static int snowSize[] = new int[1000];
private	int X[] = new int[1000];
private	int X2[] = new int[1000];
private	int Y[] = new int[1000];
private	int r[] = new int[200];
private	int speed[] = new int[200];
private	int Ir = 0;
private	int g = 0;
private int u[]=new int[200];
	
	
	public myPanel(int w,int h){
		this.setBounds(0, 0, w, h);
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		int i = 0;
		

//		for (int f = 0; f <Main.width ; f += 10) {
//			snowSize[i] = (int) (Math.random() * 100) + 15;
//			X[i] = f;
//			speed[i]=(int) (Math.random() * 10)+1;
//			X2[i] = f;
//			u[i]=1;
//			Y[i++] = Main.height+300+(int) (Math.random() * 100)+1;
//			
//
//		}
	//	setmovements();
		stepdraw();
repaint();
	}
	
	private void setmovements() {
		int i = 0;
		for (int u = 0; u < 300; u += 3)
			r[i++] = u;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
	g.fillRect(0, 0, this.getWidth(), this.getHeight());
//
//		for (int i = 0; i < 100; i++) {
//			
//
//			Y[i] -= speed[i] ;
//			X[r[i]]--;
//			Ir++;
//			u[i]+=speed[i]/4;
//
//			if (Y[i] < 0) {
//				snowSize[i] = (int) (Math.random() * 100) + 15;
//				Y[i] = Main.height+300;
//				X[i] = X2[i];
//				u[i]=1;
//				this.g++;
//				if (this.g == 10) {
//					setmovements();
//					this.g = 0;
//				}
//
//			}
//			new snow(X[i], Y[i], u[i]+6, g,Color.YELLOW);
//		//	new snow(X[i]+3, Y[i]-1, snowSize[i]-4, g,Color.black);
//		    
//
//		
//			}
//		
	}
	
	
	private void stepdraw() {
		
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		th.start();

	}

	
}

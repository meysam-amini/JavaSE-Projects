package Paint.gPaint00;

import java.awt.Color;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GMain extends JFrame {
	public static final int gWidth =1200;
	public static final int gHeight = 643;

	private ToolBox tools;
	private InfoPanel info;
	private DrawPanel draw;
	JPanel p;
	JLabel m;

	public GMain() {
		super("Paint");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, gWidth, gHeight);
		this.setLayout(null);
		p = new JPanel();
		p.setBounds(0, 0, gWidth, gHeight);
		p.setBackground(Color.gray);
		p.setLayout(null);

		// ///////////////////////// TOOLS
		info = new InfoPanel();
		info.setBounds(0, 580,1188, 25);
		info.setBackground(Color.gray);
		draw = new DrawPanel(info);
		draw.setBounds(4, 80,1180, 500);
		draw.setBackground(Color.white);
		tools = new ToolBox(draw);
		tools.setBackground(Color.darkGray);
		tools.setBounds(0, 0, 1200, 200);
	
		// /////////////////////////

		p.add(tools);
		p.add(draw);
		p.add(info);
		this.add(p);
	   // this.pack();
		setVisible(true);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		setDefaultLookAndFeelDecorated(true);
		new GMain();
	}// end of Main
}// end of class

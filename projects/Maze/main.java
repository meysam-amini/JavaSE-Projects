package Maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

public class main extends JFrame {
	public static final int whidth = 480, height = 550;

	private JPanel FormPanel;
	private MazePanel Mpanel;
//	private WaysPanel WPanel;
	private ActionsPanel Apanel;

	public main() {
		super("M A Z E");
		
		this.setBounds(300, 100, whidth, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		// /////////////////////////////////////////////////FormPanel:
		FormPanel = new JPanel();
		FormPanel.setBackground(Color.getHSBColor(2803, 2324, 5992));
		
		FormPanel.setBounds(0, 0, whidth + 100, height + 100);
		FormPanel.setLayout(null);
		// //////////////////////////////////////////////////Mpanel:
		Mpanel = new MazePanel();
		Mpanel.setOpaque(true);
		Mpanel.setBounds(35, 30, 410, 410);
		
			
		JLabel P = new JLabel();
		P.setOpaque(true);
		P.setBounds(0, 0, whidth + 100, height + 100);
		P.setLayout(null);
		URL y2 = getClass().getResource("1.jpg");
		ImageIcon mm = new ImageIcon(y2);
		P.setIcon(mm);
		this.add(P);
		// //////////////////////////////////////////////////WaysPanel:
	/*	WPanel = new WaysPanel();
		WPanel.setBackground(Color.BLACK);
		WPanel.setBounds(0, 0, 100, 1000);
	    JScrollPane pane = new JScrollPane(WPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    pane.setBorder(null);
		pane.setBounds(465, 30, 200, 462);*/
		// ///////////////////////////////////////////////////Apanel
		Apanel = new ActionsPanel(Mpanel,null);
		Apanel.setBackground(Color.black);
		Apanel.setBounds(7, 440, 465, 52);
		// ///////////////////////////////////////////////////////

		P.add(Mpanel);
	//	FormPanel.add(pane);
		P.add(Apanel);
		this.add(P);
		this.setVisible(true);
		this.setResizable(false);

	}

	public static void main(String[] args) {
		
		 try {
		      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    } catch (Exception e) {
		      System.err.println("Look and feel not set.");
		    }
		
		//setDefaultLookAndFeelDecorated(true);
		new main();
	}

}

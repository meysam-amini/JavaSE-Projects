package _8Queens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public  class  _8queens extends JFrame{

  private static int[] b = new int[8];
  private static int s =-1;
 private JLabel p;
 public static JButton q[]=new JButton[64];
 private JButton Nbutton;
 private JButton Pbutton;
 private int h=-1;
 private JLabel back;

 public _8queens(){
	 super("8 Queeens");
	 this.setBounds(100, 40, 430, 500);
	 this.setLayout(new FlowLayout());
	 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 this.setResizable(false);
	 this.setpanel();




	 Pbutton=new JButton();
	 Pbutton.setPreferredSize(new Dimension(195, 45));
	 Pbutton.setText("P R E V I U O S    S  O  L  U  T  I  O  N");
	 Pbutton.setFont(new Font("Tahoma", 8, 8));
	 Pbutton.setFocusable(false);
	 Pbutton.setBackground(Color.black);
	 Pbutton.setForeground(Color.lightGray);
	 Pbutton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			;
			if(h>0)
			nextsolution(--h);
			if(s>0)
			setTitle("8 Queeens Solution " + (--s));
		}
	});
	 back.add(Pbutton);

	 Nbutton=new JButton();
	 Nbutton.setPreferredSize(new Dimension(195, 45));
	 Nbutton.setText("N  E  X  T    S  O  L  U  T  I  O  N");
	 Nbutton.setFont(new Font("Tahoma", 8, 9));
	 Nbutton.setFocusable(false);
	 Nbutton.setBackground(Color.black);
	 Nbutton.setForeground(Color.lightGray);
	 Nbutton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(h<=92)
			nextsolution(++h);
			if(s<92)
			setTitle("8 Queeens Solution " + (++s));
		}
	});
	 back.add(Nbutton);
	 this.setVisible(true);
 }


 public void setpanel(){
	 back=new JLabel();
	 back.setPreferredSize(new Dimension(430, 550));
	 back.setOpaque(true);
	 back.setLayout(new FlowLayout());
	 back.setBackground(Color.black);

	 p=new JLabel();
	 p.setPreferredSize(new Dimension(400, 400));
	 p.setOpaque(true);
	 p.setLayout(new FlowLayout());
	 p.setBackground(Color.black);
	 java.net.URL y3 = getClass().getResource("1.jpg");
	 ImageIcon m = new ImageIcon(y3);
	 p.setIcon(m);

	 q=new JButton[64];
	 for(int i=0;i<64;i++)
	 {
		 q[i]=new JButton();
		 q[i].setOpaque(true);
		 q[i].setFocusable(false);
		 q[i].setBackground(Color.black);
		 q[i].setForeground(Color.lightGray);
		 q[i].setPreferredSize(new Dimension(44, 44));
		 q[i].setFont(new Font("tahoma", 6, 15));
		 p.add(q[i]);
	 } back.add(p);

	 this.add(back);

 }

  static boolean unsafe(int y) {
    int x = b[y];
    for (int i = 1; i <= y; i++) {
      int t = b[y - i];
      if (t == x ||
          t == x - i ||
          t == x + i) {
        return true;
      }
    }

    return false;
  }

  public static void putboard() {

   int c=0;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if(b[y] == x)
        	q[c++].setText("Q");
        else
        	q[c++].setText(null);
      }

    }
  }

  public static void main(String[] args) {
	  try {
	      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	    } catch (Exception e) {
	      System.err.println("Look and feel not set.");
	    }

	  //setDefaultLookAndFeelDecorated(true);
	  new _8queens();
  }




  public static void nextsolution(int k){

	    int y = 0;
	    b[0] = -1;
	    int g=0;
	    while (y >= 0) {
	      do {
	        b[y]++;
	      } while ((b[y] < 8) && unsafe(y));
	      if (b[y] < 8) {
	        if (y < 7) {
	          b[++y] = -1;
	        } else {
	          putboard();
	        if(g==k)
	        	break;
	          g++;

	        }
	      } else {
	        y--;
	      }
	;
	 }


  }
}
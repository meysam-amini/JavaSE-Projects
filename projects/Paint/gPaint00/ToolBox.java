package Paint.gPaint00;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToolBox extends JComponent {
	JButton btns[] = new JButton[100];
	JButton c[] = new JButton[100];
	private JLabel L1,L2,L3;
	private DrawPanel draw;

	public ToolBox(DrawPanel draw) {
		super();
		this.draw = draw;
		setOpaque(true);
		setPreferredSize(new Dimension(100, GMain.gHeight - 20));
		this.setLayout(null);

		for (int i = 0; i < 19; i++) {
			btns[i] = new JButton();
			btns[i].setForeground(Color.getHSBColor(23, 2, 3242));

			btns[i].setFont(new Font("tahoma", 1, 9));
			btns[i].setOpaque(true);
			btns[i].setBackground(Color.getHSBColor(2893, 889, 7099));
		}

		btns[0].setText("Clear");
		btns[0].setBounds(341, 3, 90, 37);
		btns[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.Clear();
			}
		});
		this.add(btns[0]);

		btns[1].setText("UndoClear");

		btns[1].setBounds(341, 41, 90, 37);

		btns[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.UndoClear();
			}
		});
		this.add(btns[1]);

		btns[2].setText("ClearLast");
		btns[2].setBounds(250, 3, 90, 37);

		btns[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.ClearLast();
			}
		});
		this.add(btns[2]);

		btns[3].setText("UndoLast");
		btns[3].setBounds(250, 41, 90, 37);

		btns[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.UndoLast();
			}
		});
		this.add(btns[3]);

		btns[4].setText("Line");
		btns[4].setBounds(3, 3, 60, 37);

		btns[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Line);
				L1.setText("      Shape Type :       Line");
			}
		});
		this.add(btns[4]);

		btns[5].setText("Rect");
		btns[5].setBounds(125, 3, 60, 37);

		btns[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Rect);
				L1.setText("      Shape Type :       Rect");
			}
		});
		this.add(btns[5]);

		btns[6].setText("Oval");
		btns[6].setBounds(186, 3, 60, 37);

		btns[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Oval);
				L1.setText("      Shape Type :       Oval");
			}
		});
		this.add(btns[6]);

		btns[7].setText("Pencil");
		btns[7].setFont(new Font("tahoma", 1, 8));

		btns[7].setBounds(64, 3, 60, 37);
		btns[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Pencil);
				L1.setText("      Shape Type :       Pencil");
			}
		});
		this.add(btns[7]);

		btns[8].setText("Star");
		btns[8].setBounds(3, 41, 60, 37);

		btns[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				draw.SetShapeTools(ShapeTools.Star);
				L1.setText("      Shape Type :       Star");
			}
		});
		this.add(btns[8]);

		btns[9].setText("Color");
		btns[9].setBounds(938, 3, 67, 75);

		btns[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Color initialBackground = btns[9].getBackground();
				Color background = JColorChooser.showDialog(null,
						"Change Shapes Color", initialBackground);
				if (background != null) {
					btns[9].setBackground(background);
				}
				draw.SetShapeColor(background);

			}
		});
		this.add(btns[9]);

		btns[10].setText("Fill Shape");
		btns[10].setBounds(620, 3, 90, 37);

		btns[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.setfiiled(true);
				L3.setText("      Shape Mode :       Filled");
			}
		});
		this.add(btns[10]);

		btns[11].setText("Not Filled");
		btns[11].setBounds(620, 41, 90, 37);

		btns[11].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.setfiiled(false);
				L3.setText("      Shape Mode :      Not Filled");
			}
		});
		this.add(btns[11]);
		btns[12].setText("Eraser");
		btns[12].setBounds(432, 3, 90, 37);
		btns[12].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Eraser);
				L1.setText("      Shape Type :       Eraser");
			}
		});
		this.add(btns[12]);

		btns[13].setText("Spray");
		btns[13].setFont(new Font("tahoma", 1, 8));

		btns[13].setBounds(64, 41, 60, 37);
		btns[13].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Spray);
				L1.setText("      Shape Type :       Spray");
			}
		});
		this.add(btns[13]);

		btns[14].setText("Text");
		btns[14].setBounds(125, 41, 60, 37);
		btns[14].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Text);
				L1.setText("      Shape Type :       Text");
				String String = JOptionPane
						.showInputDialog("Pleas Enter Ur Text");
				draw.SetString(String);

			}
		});
		this.add(btns[14]);

		btns[15].setText("Delet One");
		btns[15].setBounds(432, 41, 90, 37);

		btns[15].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.DeletOne);
				L1.setText("      Shape Type :       DeletOne");
			}
		});
		this.add(btns[15]);

		btns[16].setText("Upper Size");
		btns[16].setBounds(526, 3, 90, 37);

		btns[16].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.Upersiz();
				L2.setText("      Shape Size :          "+draw.size);

			}
		});
		this.add(btns[16]);

		btns[17].setText("Lower Size");
		btns[17].setBounds(526, 41, 90, 37);

		btns[17].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.Lowersize();
				L2.setText("      Shape Size :          "+draw.size);

			}
		});
		this.add(btns[17]);

		btns[18].setText("Arc");
		btns[18].setBounds(186, 41, 60, 37);

		btns[18].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.SetShapeTools(ShapeTools.Arc);
				L1.setText("      Shape Type :       Arc");
			}
		});
		this.add(btns[18]);

		// ////////////////////////////////////////////////////////////////color
		// buttons

		for (int i = 0; i < 6; i++) {
			c[i] = new JButton();
			c[i].setBounds(1005 + (i * 30), 3, 30, 25);
			c[i].setOpaque(true);
			this.add(c[i]);
			if (i == 0) {
				c[i].setBackground(Color.BLACK);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.black);
						btns[9].setBackground(Color.black);

					}
				});
			}
			if (i == 1) {
				c[i].setBackground(Color.yellow);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.yellow);
						btns[9].setBackground(Color.yellow);

					}
				});
			}
			if (i == 2) {
				c[i].setBackground(Color.BLUE);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.BLUE);
						btns[9].setBackground(Color.BLUE);

					}
				});

			}
			if (i == 3) {
				c[i].setBackground(Color.cyan);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.cyan);
						btns[9].setBackground(Color.cyan);

					}
				});

			}
			if (i == 4) {
				c[i].setBackground(Color.magenta);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.magenta);
						btns[9].setBackground(Color.magenta);

					}
				});
			}
			if (i == 5) {
				c[i].setBackground(Color.lightGray);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.lightGray);
						btns[9].setBackground(Color.lightGray);

					}
				});
			}
		}

		for (int i = 6; i < 12; i++) {
			c[i] = new JButton();
			c[i].setBounds(1005 + ((i - 6) * 30), 28, 30, 25);
			c[i].setOpaque(true);
			this.add(c[i]);

			if (i == 6) {
				c[i].setBackground(Color.green);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.green);
						btns[9].setBackground(Color.green);

					}
				});
			}
			if (i == 7) {
				c[i].setBackground(Color.orange);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.orange);
						btns[9].setBackground(Color.orange);

					}
				});
			}
			if (i == 8) {
				c[i].setBackground(Color.red);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.red);
						btns[9].setBackground(Color.red);

					}
				});

			}
			if (i == 9) {
				c[i].setBackground(Color.pink);
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.pink);
						btns[9].setBackground(Color.pink);

					}
				});

			}
			if (i == 10) {
				c[i].setBackground(Color.getHSBColor(23, 2, 3242));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.getHSBColor(23, 2, 3242));
						btns[9].setBackground(Color.getHSBColor(23, 2, 3242));

					}
				});
			}
			if (i == 11) {
				c[i].setBackground(Color.getHSBColor(2893, 889, 792));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.getHSBColor(2893, 889, 792));
						btns[9].setBackground(Color.getHSBColor(2893, 889, 792));

					}
				});
			}
		}

		for (int i = 12; i < 18; i++) {
			c[i] = new JButton();
			c[i].setBounds(1005 + ((i - 12) * 30), 53, 30, 25);
			c[i].setOpaque(true);
			this.add(c[i]);

			if (i == 12) {
				c[i].setBackground(Color.getHSBColor(2803, 0, 79992));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.getHSBColor(2803, 0, 79992));
						btns[9].setBackground(Color.getHSBColor(2803, 0, 79992));

					}
				});
			}
			if (i == 13) {
				c[i].setBackground(Color.getHSBColor(2803, 2324, 5992));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(Color.getHSBColor(2803, 2324, 5992));
						btns[9].setBackground(Color.getHSBColor(2803, 2324, 5992));


					}
				});
			}
			if (i == 14) {
				c[i].setBackground(new Color(0, 90, 4));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(new Color(0, 90, 4));
						btns[9].setBackground(new Color(0, 90, 4));

					}
				});

			}
			if (i == 15) {
				c[i].setBackground(new Color(3, 39, 6));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(new Color(3, 39, 6));
						btns[9].setBackground(new Color(3, 39, 6));

					}
				});

			}
			if (i == 16) {
				c[i].setBackground(new Color(0, 90, 90));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(new Color(0, 90, 90));
						btns[9].setBackground(new Color(0, 90, 90));

					}
				});
			}
			if (i == 17) {
				c[i].setBackground(new Color(3, 30, 90));
				c[i].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						draw.SetShapeColor(new Color(3, 30, 90));
						btns[9].setBackground(new Color(3, 30, 90));

					}
				});
			}
		}

		// ////////////////////////////////////////////////////////////////end
		// of color btns

		JButton load = new JButton("load");
		load.setForeground(Color.getHSBColor(23, 2, 3242));
		load.setBackground(Color.getHSBColor(2893, 889, 7099));
		load.setBounds(714, 3, 50, 25);
		load.setOpaque(true);
		load.setFont(new Font("tahoma", 1, 7));
		this.add(load);

		JButton save = new JButton("save");
		save.setForeground(Color.getHSBColor(23, 2, 3242));
		save.setBackground(Color.getHSBColor(2893, 889, 7099));
		save.setBounds(714, 29, 50, 24);
		save.setOpaque(true);
		save.setFont(new Font("tahoma", 1, 7));
		this.add(save);

		
		
		JButton print = new JButton("print");
		print.setForeground(Color.getHSBColor(23, 2, 3242));
		print.setBackground(Color.getHSBColor(2893, 889, 7099));
		print.setBounds(714, 54, 50, 24);
		print.setOpaque(true);
		print.setFont(new Font("tahoma", 1, 7));
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
           //   print.draw.getGraphics();				
			}
		});
		this.add(print);

		// //////////////////////////////////////////////////////////////////
		L1 = new JLabel();
		L1.setText("      Shape Type :       Line");
		L1.setBounds(768, 4, 166, 23);
		L1.setOpaque(true);
		L1.setFont(new Font("tahoma", 1, 11));
		L1.setForeground(Color.getHSBColor(23, 2, 3242));
		L1.setBackground(Color.getHSBColor(2893, 889, 7099));
		this.add(L1);
		
		
		L2 = new JLabel();
		L2.setText("      Shape Size :          "+draw.size);
		L2.setBounds(768, 29, 166, 23);
		L2.setOpaque(true);
		L2.setFont(new Font("tahoma", 1, 11));
		L2.setForeground(Color.getHSBColor(23, 2, 3242));
		L2.setBackground(Color.getHSBColor(2893, 889, 7099));
		this.add(L2);
		
		
		
		L3 = new JLabel();
		L3.setText("      Shape Mode :       Filled");
		L3.setBounds(768, 54, 166, 23);
		L3.setOpaque(true);
		L3.setFont(new Font("tahoma", 1, 11));
		L3.setForeground(Color.getHSBColor(23, 2, 3242));
		L3.setBackground(Color.getHSBColor(2893, 889, 7099));
		this.add(L3);
		

	}

	
}

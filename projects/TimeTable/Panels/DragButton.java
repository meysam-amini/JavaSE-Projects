package TimeTable.Panels;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DragButton extends JButton{

    private volatile int draggedAtX, draggedAtY;

    public DragButton(String text){
        super(text);
        setDoubleBuffered(false);
        setMargin(new Insets(0, 0, 0, 0));
        setSize(25, 25);
        setPreferredSize(new Dimension(25, 25));

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                setLocation(e.getX() - draggedAtX + getLocation().x,
                        e.getY() - draggedAtY + getLocation().y);
            }
        });
    }}
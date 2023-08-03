package TimeTable.test;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** @see https://stackoverflow.com/questions/7026822 */
public class PanelPaint extends JPanel {

    private static final double SCALE = 0.5;

    public PanelPaint() {
        super(new GridLayout(0, 1));
        final MyPanel panel = new MyPanel();
        JScrollPane scroll = new JScrollPane(panel);
        scroll.getViewport().setPreferredSize(new Dimension(320, 240));
        this.add(scroll);
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                add(new JLabel(new ImageIcon(createImage(panel))));
            }
        });
    }

    private BufferedImage createImage(MyPanel panel) {
        Dimension size = panel.getPreferredSize();
        BufferedImage image = new BufferedImage(
            size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        AffineTransform at = new AffineTransform();
        at.scale(SCALE, SCALE);
        AffineTransformOp scaleOp =
            new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(image, null);
    }

    private static class MyPanel extends JPanel {

        private static final int N = 16;

        public MyPanel() {
            super(true);
            this.setLayout(new GridLayout(N, N));
            for (int i = 0; i < N * N; i++) {
                this.add(new JLabel(String.valueOf(i) + " "));
            }
        }
    }

    private void display() {
        JFrame f = new JFrame("PanelPaint");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PanelPaint().display();
            }
        });
    }
}
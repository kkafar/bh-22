package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel implements ActionListener {

    private final int width;
    private final int height;
    private final Timer timer;

    private final MapInfo mapInfo = new MapInfo();

    public MapPanel(int width, int height){
        this.width = width;
        this.height = height;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.repaint();

        // background
        g2D.setPaint(new Color(236, 195, 161));
        g2D.fillRect(0, 0, this.width, this.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {

        }
    }
}

package visualizer;

import javax.swing.*;
import java.awt.*;

public class JetPanel extends JPanel {

    private final int width;
    private final int height;

    public JetPanel(int width, int height, MapInfo mapInfo){
        this.width = width;
        this.height = height;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.repaint();

        // background
        g2D.setPaint(new Color(88, 92, 107));
        g2D.fillRect(0, 0, this.width, this.height);
    }
}

package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MapPanel extends JPanel implements ActionListener {

    private final int width;
    private final int height;

    private int squareWidth;
    private int squareHeight;

    private final int tractorSize = 50;
    private final Image tractorImage = new ImageIcon("pics/tractor.png").getImage().getScaledInstance(tractorSize, tractorSize, Image.SCALE_DEFAULT);

    private final Timer timer;
    private boolean heatMapShowing = false;

    private final MapInfo mapInfo = new MapInfo();
    private final int[][] heatMap = mapInfo.getHeatMap();

    public MapPanel(int width, int height){

        this.width = width;
        this.height = height;

        squareHeight = height/heatMap.length;
        squareWidth = width/heatMap[0].length;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        timer = new Timer(50, this);
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                heatMapShowing = !heatMapShowing;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.repaint();

        // background
        g2D.setPaint(new Color(88, 92, 107));
        g2D.fillRect(0, 0, this.width, this.height);

        // map
        for (int i = 0; i < heatMap.length; i++){
            for (int j = 0; j < heatMap[0].length; j++){
                if (heatMap[i][j] < 0) g2D.setPaint(new Color(88, 92, 107));
                else g2D.setPaint(new Color(229, 193, 165));
                g2D.fillRect(j*squareWidth, i*squareHeight, squareWidth, squareHeight);
            }
        }

        // heatmap
        if (heatMapShowing) {
            for (int i = 0; i < heatMap.length; i++) {
                for (int j = 0; j < heatMap[0].length; j++) {
                    if (heatMap[i][j] < 0) continue;

                    g2D.setPaint(new Color(0, heatMap[i][j] * 30, 0));
                    g2D.fillRect(j * squareWidth, i * squareHeight, squareWidth, squareHeight);
                }
            }
        }

        // grid
        g2D.setPaint(new Color(225, 217, 217));
        for (int i = 0; i < heatMap.length; i++){
            for (int j = 0; j < heatMap[0].length; j++){
                g2D.drawRect(j*squareWidth, i*squareHeight, squareWidth, squareHeight);
            }
        }

        // sensors
        g2D.setPaint(new Color(21, 36, 143));
        ArrayList<Point> sensors = mapInfo.getSensors();
        for (Point sensor: sensors){
            g2D.fillOval((int)sensor.x, (int)sensor.y, 12, 12);
        }

        //tractor
        Point tractorPos = mapInfo.getTractorPosition();
        if (tractorPos != null){
            g2D.drawImage(tractorImage, (int)(tractorPos.x - tractorSize/2), (int)(tractorPos.y - tractorSize/2), tractorSize, tractorSize, null,this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {

        }
    }
}

package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MapPanel extends JPanel implements ActionListener {

    public final int width;
    public final int height;

    private final int squareWidth;
    private final int squareHeight;

    private final int tractorSize = 50;
    private final Image tractorImage = new ImageIcon("pics/tractor.png").getImage().getScaledInstance(tractorSize, tractorSize, Image.SCALE_DEFAULT);

    private final Timer timer;
    private boolean heatMapShowing = false;

    private final MapInfo mapInfo;
    private final double[][] heatMap;
    private double maxValue;
    private final ArrayList<Point> sensors;

    public MapPanel(int width, int height, MapInfo mapInfo){
        this.mapInfo = mapInfo;
        this.width = width;
        this.height = height;
        this.heatMap = mapInfo.getHeatMap();

        squareHeight = height/heatMap.length;
        squareWidth = width/heatMap[0].length;

        maxValue = -1;

        for (int i = 0; i < heatMap.length; i++){
            for (int j = 0; j < heatMap[0].length; j++){
                maxValue = Math.max(maxValue, heatMap[i][j]);
            }
        }

        sensors = mapInfo.translate(mapInfo.getSensors(), width, height);

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
                    g2D.setPaint(getHeatHue(heatMap[i][j]));
                    g2D.fillRect(j * squareWidth, i * squareHeight, squareWidth, squareHeight);
                }
            }
        } else {
            // grid
            g2D.setPaint(new Color(225, 217, 217));
            for (int i = 0; i < heatMap.length; i++) {
                for (int j = 0; j < heatMap[0].length; j++) {
                    g2D.drawRect(j * squareWidth, i * squareHeight, squareWidth, squareHeight);
                }
            }
        }

        // sensors
        g2D.setPaint(new Color(21, 36, 143));
        for (Point sensor: sensors){
            g2D.fillOval((int)sensor.x, (int)sensor.y, 12, 12);
        }

        //tractor
        Point tractorPos = mapInfo.translate(mapInfo.getTractorPosition(), width, height);
        if (tractorPos != null){
            g2D.drawImage(tractorImage, (int)(tractorPos.x - tractorSize/2), (int)(tractorPos.y - tractorSize/2), tractorSize, tractorSize, null,this);
        }
    }

    private Color getHeatHue(double value){
        return Color.getHSBColor((float)(225./360.), (float)value/(float)maxValue, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {

        }
    }
}

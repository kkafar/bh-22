package visualizer;

import javax.swing.*;
import java.awt.*;

public class JetPanel extends JPanel {

    private final int width;
    private final int height;

    private final int mapPanelWidth;
    private final int mapPanelHeight;

    private double[] jetValues = {30, 40, 20, 10, 80};

    public JetPanel(int width, int height, int mapPanelWidth, int mapPanelHeight, MapInfor mapInfo){
        this.width = width;
        this.height = height;

        this.mapPanelHeight = mapPanelHeight;
        this.mapPanelWidth = mapPanelWidth;

        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.repaint();

        // background
        g2D.setPaint(new Color(255, 255, 255));
        g2D.fillRect(0, 0, this.width, this.height);

        //jets
        jetValues = getJetValues();

        int spaceBetween = width/jetValues.length;
        int downLine = (int) (height*.75);
        int barWidth = 30;

        for (int i = 0; i < jetValues.length; i++){
            int x = spaceBetween*i + spaceBetween/2 - barWidth/2;

            g2D.setColor(new Color(138, 151, 234));
            g2D.fillRect(x, (int)(downLine - jetValues[i]), barWidth, (int)jetValues[i]);
        }
    }

    private int[] getJetValues(){
        int[][] heatMap = mapInfo.getHeatMap();
        Point leftJetPosition = mapInfo.getLeftJetPosition();
        Point rightJetPosition = mapInfo.getRightJetPosition();






    }
}

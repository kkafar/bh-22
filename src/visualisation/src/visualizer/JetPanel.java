package visualizer;

import javax.swing.*;
import java.awt.*;

public class JetPanel extends JPanel {

    private final int width;
    private final int height;

    private final int mapPanelWidth;
    private final int mapPanelHeight;

    private final MapInfo mapInfo;
    private final Tractor tractor;

    private final int jetCount = 5;

    public JetPanel(int width, int height, int mapPanelWidth, int mapPanelHeight, MapInfo mapInfo, Tractor tractor){
        this.width = width;
        this.height = height;

        this.mapPanelHeight = mapPanelHeight;
        this.mapPanelWidth = mapPanelWidth;

        this.mapInfo = mapInfo;
        this.tractor = tractor;

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
        double[]jetValues = getJetValues();

        int margin = 100;
        int spaceBetween = (width-2*margin)/jetValues.length;
        int downLine = (int) (height*.9);
        int barWidth = 30;

        for (int i = 0; i < jetValues.length; i++){
            int x = margin+spaceBetween*i + spaceBetween/2 - barWidth/2;

            g2D.setColor(new Color(138, 151, 234));
            g2D.fillRect(x, (int)(downLine - jetValues[i]), barWidth, (int)jetValues[i]);
        }
    }

    private double[] getJetValues(){
        double [][] heatMap = mapInfo.getHeatMap();
        Point leftJetPosition = tractor.getLeftJetPosition();
        Point rightJetPosition = tractor.getRightJetPosition();

        double jetWidth = (rightJetPosition.x - leftJetPosition.x)/jetCount;
        double jetHeight = (rightJetPosition.y - leftJetPosition.y)/jetCount;

        double[] jetValues = new double[jetCount];

        for (int i = 0; i < jetCount; i++){
            jetValues[i] = getValueUnderJet(heatMap, new Point(i*jetWidth + leftJetPosition.x, i*jetHeight + leftJetPosition.y));
        }
        return jetValues;
    }

    private double getValueUnderJet(double [][] heatMap, Point jetPos){
        int i = (int)(jetPos.x/mapPanelWidth*heatMap[0].length);
        int j = (int)(jetPos.y/mapPanelHeight*heatMap.length);

        return heatMap[j][i] == -1 ? 0 : heatMap[j][i];
    }
}

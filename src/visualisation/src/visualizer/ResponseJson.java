package visualizer;

import java.util.ArrayList;
import java.util.Arrays;

public class ResponseJson {
    private double[][] heatMap;
    private double[] lowerLeft;
    private double[] upperRight;
    private double[][] sensors;

    @Override
    public String toString() {
        return "ResponseJson{" +
                "heatMap=" + heatMap[0][0] +
                ", lowerLeft=" + Arrays.toString(lowerLeft) +
                ", upperRight=" + Arrays.toString(upperRight) +
                ", sensors=" + sensors[0][0] +
                '}';
    }

    public double[][] getHeatMap() {
        return heatMap;
    }

    public double[] getLowerLeft() {
        return lowerLeft;
    }

    public double[] getUpperRight() {
        return upperRight;
    }

    public ArrayList<Point> getSensors() {
        ArrayList<Point> sensorArrayList = new ArrayList<>();
        for (int i = 0; i < sensors.length; ++i) {
            sensorArrayList.add(new Point(sensors[i][0], sensors[i][1]));
        }
        return sensorArrayList;
    }
}

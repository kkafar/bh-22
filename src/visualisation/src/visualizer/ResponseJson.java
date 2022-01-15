package visualizer;

import java.util.Arrays;

public class ResponseJson {
    double [][] heatMap;
    double[] lowerLeft;
    double[] upperRight;
    double[][] sensors;

    @Override
    public String toString() {
        return "ResponseJson{" +
                "heatMap=" + heatMap[0][0] +
                ", lowerLeft=" + Arrays.toString(lowerLeft) +
                ", upperRight=" + Arrays.toString(upperRight) +
                ", sensors=" + sensors[0][0] +
                '}';
    }
}

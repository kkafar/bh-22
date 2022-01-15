package visualizer;

import java.util.Arrays;

public class ResponseJson {
    int [][] heatMap;
    int[] lowerLeft;
    int[] upperRight;
    int[][] sensors;

    @Override
    public String toString() {
        return "ResponseJson{" +
                "heatMap=" + Arrays.toString(heatMap) +
                ", lowerLeft=" + Arrays.toString(lowerLeft) +
                ", upperRight=" + Arrays.toString(upperRight) +
                ", sensors=" + Arrays.toString(sensors) +
                '}';
    }
}

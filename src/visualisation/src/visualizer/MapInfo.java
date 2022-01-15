package visualizer;

import java.util.ArrayList;
import java.util.List;

public class MapInfo {

    public ArrayList<Point> getSensors(){
        return new ArrayList<Point>(List.of(new Point[]{
                new Point(100, 200),
                new Point(400, 500),
                new Point(300, 200)
        }));
    }

    public int [][] getHeatMap(){
        int[][] map = {
                {-1, -1, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, 0, 0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 4, 3, 0, 0, 0},
                {0, 0, 1, 0, 0, 3, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, -1, -1},
                {0, 0, 1, 0, 0, 0, 0, 0, -1, -1}
        };

        return map;
    }

    public Point getTractorPosition(){
        return new Point(500, 300);
    }
}

package visualizer;

import java.util.ArrayList;
import java.util.List;

public class MapInfo {

    public ArrayList<Point> getSensors(){
        return new ArrayList<Point>(List.of(new Point[]{
                new Point(1, 2),
                new Point(4, 5),
                new Point(3, 2)
        }));
    }

    public ArrayList<Point> getMapVertices(){
        return null;
    }

    public int [][] getHeatMap(){
        return null;
    }

    public int getHeatMapWidth(){
        return 20;
    }

    public int getHeatMapHeight(){
        return 10;
    }

    public Point getTractorPosition(){
        return new Point(0, 0);
    }

}

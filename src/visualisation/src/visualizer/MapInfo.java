package visualizer;

import java.util.ArrayList;
import java.util.List;

public class MapInfo {

    public Point upperLeft = new Point(0, 0);
    public Point lowerRight = new Point(1000, 600);

    private int [][] map;

    public MapInfo(){
        map = new int[][]{
                {-1, -1, 0, 0, 2, 0, 0, 0, 0, 0},
                {-1, -1, 0, 2, 3, 2, 0, 0, 0, 0},
                {0, 0, 0, 2, 3, 4, 3, 2, 0, 0},
                {0, 0, 1, 0, 2, 3, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 0, -1, -1},
                {0, 0, 1, 0, 0, 0, 0, 0, -1, -1}
        };
    }

    public Point translate(Point point, int mapWidth, int mapHeight){
        int translatedX = (int)(point.x/(lowerRight.x - upperLeft.x)*mapWidth);
        int translatedY = (int)(point.y/(lowerRight.y - upperLeft.y)*mapHeight);

        return new Point(translatedX, translatedY);
    }

    public ArrayList<Point> translate(ArrayList<Point> points, int mapWidth, int mapHeight){
        ArrayList<Point> translatedPoints = new ArrayList<Point>();
        for (Point point: points){
            translatedPoints.add(translate(point, mapWidth, mapHeight));
        }
        return translatedPoints;
    }

    public ArrayList<Point> getSensors(){
        ArrayList<Point> points =  new ArrayList<Point>(List.of(new Point[]{
                new Point(100, 200),
                new Point(400, 500),
                new Point(300, 200)
        }));

        return points;
    }

    public int [][] getHeatMap(){
        return map;
    }

    public Point getTractorPosition(){
        return new Point(500, 300);
    }
}

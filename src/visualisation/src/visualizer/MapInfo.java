package visualizer;
import java.net.URL;
import java.util.ArrayList;


public class MapInfo {
    private final Point upperRight;
    private final Point lowerLeft;

    private URL url;
    private double [][] heatMap;
    private ArrayList<Point> sensors = new ArrayList<>();

    private final int clientId;
    private final int mapId;


    public MapInfo(
        double[][] heatMap,
        ArrayList<Point> sensors,
        int clientId,
        int mapId,
        final Point lowerLeft,
        final Point upperRight
    ) {
        this.heatMap = heatMap;
        this.sensors = sensors;
        this.clientId = clientId;
        this.mapId = mapId;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
    }

    public Point getUpperRight() {
        return this.upperRight;
    }

    public Point getLowerLeft() {
        return this.lowerLeft;
    }


    public Point translate(Point point, int mapWidth, int mapHeight){
        int translatedX = (int)((point.x - lowerLeft.x)/(upperRight.x - lowerLeft.x)*mapWidth);
        int translatedY = (int)((point.y - lowerLeft.y)/(upperRight.y - lowerLeft.y)*mapHeight);

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
        return sensors;
    }

    public double [][] getHeatMap(){
        return heatMap;
    }

    public Point getTractorPosition(){
        return new Point(63, 45);
    }
}

package visualizer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapInfo {

    public Point upperLeft = new Point(0, 0);
    public Point lowerRight = new Point(1000, 600);

    private URL url;
    private int [][] map;

    private final int clientID = 1;
    private final int mapID = 1;

    public MapInfo() {

        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map = new int[][]{
                {-1, -1, 0, 0, 2, 0, 0, 0, 0, 0},
                {-1, -1, 0, 2, 3, 2, 0, 0, 0, 0},
                {0, 0, 0, 2, 3, 4, 3, 2, 0, 0},
                {0, 0, 1, 0, 2, 3, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 0, -1, -1},
                {0, 0, 1, 0, 0, 0, 0, 0, -1, -1}
        };
    }

    private void connect() throws IOException{
        url = new URL("http://10.230.128.212:5000");

        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        byte[] out = ("{\"clientId\":\"" + clientID + "\",\"mapId\":\"" + mapID +"\"}").getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        byte[] bytes = http.getInputStream().readAllBytes();
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
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

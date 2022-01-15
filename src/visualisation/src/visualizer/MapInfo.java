package visualizer;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MapInfo {

    public Point upperRight = new Point(0, 0);
    public Point lowerLeft = new Point(1000, 600);

    private URL url;
    private double [][] map;
    private ArrayList<Point> sensors = new ArrayList<>();

    private final int clientID = 6;
    private final int mapID = 1;

    private final Gson gson = new Gson();

    public MapInfo() {

        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String response = new String(bytes, StandardCharsets.UTF_8);

        ResponseJson responseJson = gson.fromJson(response, ResponseJson.class);

        System.out.println(responseJson);
        map = responseJson.heatMap;
        lowerLeft = new Point(responseJson.lowerLeft[0], responseJson.lowerLeft[1]);
        upperRight = new Point(responseJson.upperRight[0], responseJson.upperRight[1]);

        System.out.println(lowerLeft);
        System.out.println(upperRight);

        double[][] sensorArray = responseJson.sensors;
        for (int i = 0; i < sensorArray.length; i++){
            sensors.add(new Point(sensorArray[i][0], sensorArray[i][1]));
        }
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
        return map;
    }

    public Point getTractorPosition(){
        return new Point(63, 45);
    }
}

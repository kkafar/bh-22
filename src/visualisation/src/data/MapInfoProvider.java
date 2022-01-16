package data;

import visualizer.MapInfo;
import visualizer.Point;
import visualizer.ResponseJson;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;

public class MapInfoProvider {
  private final DatabaseMetadata dbData;

  private final Gson gsonInstance = new Gson();

  public MapInfoProvider(DatabaseMetadata dbData) {
    this.dbData = dbData;
  }

  private byte[] getRequest(int clientId, int mapId) {
    return ("{\"clientId\":\"" + clientId + "\",\"mapId\":\"" + mapId +"\"}").getBytes(StandardCharsets.UTF_8);
  }

  public MapInfo getInfo(final int clientId, final int mapId) throws IOException {
    HttpURLConnection httpConnection = (HttpURLConnection) dbData.getUrl().openConnection();
    httpConnection.setRequestMethod("POST");
    httpConnection.setDoOutput(true);

    byte[] request = getRequest(clientId, mapId);

    httpConnection.setFixedLengthStreamingMode(request.length);
    httpConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    httpConnection.connect();

    try (OutputStream os = httpConnection.getOutputStream()) {
      os.write(request);
    }

    byte[] bytes = httpConnection.getInputStream().readAllBytes();
    String response = new String(bytes, StandardCharsets.UTF_8);

    ResponseJson responseJson = gsonInstance.fromJson(response, ResponseJson.class);
    System.out.println(responseJson);

    Point lowerLeft = new Point(responseJson.getLowerLeft()[0], responseJson.getLowerLeft()[1]);
    Point upperRight = new Point(responseJson.getUpperRight()[0], responseJson.getUpperRight()[1]);

    System.out.println(lowerLeft);
    System.out.println(upperRight);

    return new MapInfo(
        responseJson.getHeatMap(),
        responseJson.getSensors(),
        clientId,
        mapId,
        lowerLeft,
        upperRight
    );
  }
}

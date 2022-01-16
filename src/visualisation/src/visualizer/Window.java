package visualizer;
import data.DatabaseMetadata;
import data.MapInfoProvider;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;


public class Window extends JFrame {

    public final int width = 1000;
    public final int height = 800;

    private final static int clientId = 6;
    private final static int mapId = 1;

    private final DatabaseMetadata dbMetadata;

    public static void main(String[]  args) throws IOException {
        new Window();
    }

    public Window() throws IOException {
        dbMetadata = new DatabaseMetadata();

        setTitle("Fertisave");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        setBounds(0, 0, width, height+50);
        setPreferredSize(new Dimension(width, height+50));

        MapInfoProvider mapInfoProvider = new MapInfoProvider(dbMetadata);
        MapInfo mapInfo = mapInfoProvider.getInfo(clientId, mapId);

        MapPanel mapPanel = new MapPanel(width, 3*height/4, mapInfo);
        add(mapPanel);

        JetPanel jetPanel = new JetPanel(width, height/4, mapInfo);
        add(jetPanel);

        pack();

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/2 - getSize().width/2, screenDim.height/2 - getSize().height/2);

        setResizable(false);
        setVisible(true);
    }
}

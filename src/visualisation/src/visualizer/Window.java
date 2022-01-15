package visualizer;
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

    public static void main(String[]  args) throws IOException {
        new Window();
    }

    public Window() {
        setTitle("Fertisave");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        setBounds(0, 0, width, height+50);
        setPreferredSize(new Dimension(width, height+50));

        MapPanel mapPanel = new MapPanel(width, 3*height/4);
        add(mapPanel);

        JetPanel jetPanel = new JetPanel(width, height/4);
        add(jetPanel);

        pack();

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/2 - getSize().width/2, screenDim.height/2 - getSize().height/2);

        setResizable(false);
        setVisible(true);
    }
}

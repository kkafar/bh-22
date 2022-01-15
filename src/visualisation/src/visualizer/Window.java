package visualizer;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public final int width = 1000;
    public final int height = 600;

    public static void main(String[]  args){
        new Window();
    }

    public Window() {
        setTitle("Fertisave");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(0, 0, width, height);
        setPreferredSize(new Dimension(width, height));

        MapPanel mapPanel = new MapPanel(width, height);
        add(mapPanel);

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenDim.width/2 - getSize().width/2, screenDim.height/2 - getSize().height/2);

        setResizable(false);
        setVisible(true);
    }
}

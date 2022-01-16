package visualizer;

public class Tractor {

    private Point leftPos = new Point(0, 0);
    private Point rightPos = new Point(50, 0);

    private final int UP = -1;
    private final int DOWN = 1;

    private int direction = DOWN;
    private int speed = 1;

    private final int mapWidth, mapHeight;

    public Tractor(int mapWidth, int mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void move(){
        leftPos.y += direction*speed;
        rightPos.y += direction*speed;

        if (speed > 0 && (leftPos.y < 0 || leftPos.y > mapHeight-40)){
            direction *= -1;
            double width = rightPos.x - leftPos.x;
            leftPos.x += width;
            rightPos.x += width;
        }

        if (rightPos.x > mapWidth-80) speed = 0;
    }

    public Point getTractorPosition(){
        Point left = getLeftJetPosition();
        Point right = getRightJetPosition();
        return new Point((left.x + right.x)/2, (left.y+right.y)/2) ;
    }

    public Point getLeftJetPosition() {
        return leftPos;
    }

    public Point getRightJetPosition() {
        Point pos = rightPos;
        move();

        return pos;
    }
}

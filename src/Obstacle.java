import java.awt.*;

/***
 * Obstacles class creates obstacle objects and is an extension of the Square class
 */
public class Obstacle extends Square  {

    private int R;
    private int G;
    private int B;

    public Obstacle(int x, int y) {

        super(20);
        setLocation(x, y);
        //Sets the colour of the obstacles to a random colour
        R = (int) (Math.random() * 256);
        G = (int) (Math.random() * 256);
        B = (int) (Math.random() * 256);

    }


    public void draw(Graphics2D g) {
        g.setColor(new Color(R,G,B));
        g.fillRect(getX(), getY(), side, side);
    }
}
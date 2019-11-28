/***
 * Rectangle class is a concrete class which creates a Rectangle object
 */
public class Rectangle{
    // properties
    private int width;
    private int height;
    private int x;
    private int y;
    private int area;
    private boolean moving;

    /**
     *  Rectangle constructor sets the width and height of the rectangle and sets moving initially to false
     */
    public Rectangle(int width, int height) {

        this.width = width;
        this.height = height;
        moving = false;

    }

    // methods
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public double getArea() {

        return area;
    }


    public boolean getMovingObstacle() {

        return moving;
    }


    public void setMovingObstacle(boolean moving) {

        this.moving = moving;
    }
    /**
     *
     * @param x sets the location on the x-axis
     * @param y sets the location on the y axis
     */
    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @param x is the location of Rectangle on x axis
     * @param y is the location of Rectangle on y axis
     * @return is collision is detected
     */

    public boolean contains(int x, int y) {


        if (x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height)
            return true;


        return false;
    }




}

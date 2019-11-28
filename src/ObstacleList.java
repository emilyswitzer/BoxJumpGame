import java.util.ArrayList;
import java.util.Iterator;

/**
 * ObstacleList provides Game with an arraylist containing all of the obstacles in the game.
 */

public class ObstacleList implements Iterable {

    // properties
    private ArrayList<Obstacle> obstacles;

    /***
     * The ObstacleList constructor constructs a new arraylist
     * called obstacles
     */
    public ObstacleList() {

        obstacles = new ArrayList<Obstacle>();
    }

    /**
     *The add() method adds an obstacle to the ObstacleList array
     * @param o is an obstacle object
     */
    public void add(Obstacle o) {

        obstacles.add(o);
    }
    /**
     *The remove() method removes an obstacle from the ObstacleList
     */

    public void remove() {

        for (int i = 0; i < obstacles.size(); i++) {
            if ((obstacles.get(i)).getMovingObstacle()) {
                obstacles.remove(i);
                i--;
            }
        }
    }
    /**
     * @return an array containing all of the obstacles.
     */


    public int size() {

        return obstacles.size();
    }

    //Iterator (supports hasNext, next, remove)
    public Iterator<Obstacle> iterator() {

        return obstacles.iterator(); //list calls iterator function and assigns it to iterator variable
    }

    /**
     *
     * @param i
     * @return
     */
    public Obstacle getObstacle(int i) {
        return obstacles.get(i);
    }
}
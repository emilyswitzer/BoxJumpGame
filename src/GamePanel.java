import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.*;

/**
 * GamePanel is an instantiable class which is an extension of the Serializable interface
 * and creates the various elements of the BoxJump Game
 */


public class GamePanel extends JPanel implements Serializable {


    /**The value of ground {@GROUND};*/
    public static final int GROUND = 250;
     /**The size of the smallest space between obstacles is {@SMALL_SPACE};*/
     public static final int SMALL_SPACE = 175;
     /** The size of the largest space between obstacles is {@MAX_SPACE};*/
     public static final int MAX_SPACE = 350;
    private ObstacleList obstacles; //an arraylist of obstacles
    private Timer obstacleTimer, jumpTimer; //String Timers
    private static int randomGap = (int) ( Math.random() * (MAX_SPACE - SMALL_SPACE)) + SMALL_SPACE;
    private boolean jumping;
    private static int jumpHeight = 0;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private int score;
    private int highScore;
    private Rectangle player; //the player box which the user controls
    private boolean isGameOver;
    private Color playerColour = new Color(57,52,88);


    /**
     * The Game constructor sets up the games components and initializes
     * the timing elements
     */
    public GamePanel(){

        setPreferredSize( new Dimension( 800, 320));
        setFocusable(true); //To fire keyboard events, a component must have the keyboard focus.
        requestFocus(); //so it receives key events
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Verdana", Font.BOLD,20));
        scoreLabel.setForeground(new Color(134, 159, 119));
        highScoreLabel = new JLabel();
        highScoreLabel.setFont(new Font("Verdana", Font.BOLD,20));
        highScoreLabel.setForeground(new Color(253, 233, 234));
        start();//start the game
        add(scoreLabel);
        add(highScoreLabel);
        //adding controls to the game
        this.addKeyListener( new JumpKeyListener());
        this.addMouseListener(new JumpMouseListener());


    }//end of GamePanel

    /***
     * The start() method initiates the game
     */
     private void start() {

        score = 0;//always starts the score on 0

        try {
            //reading in the current highscore through scanner as Filereader would read in the first numbers ascii code
            File f = new File("highscores.txt");
            Scanner s = new Scanner(f);
            while(s.hasNextInt()){
                highScore = s.nextInt();

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        scoreLabel.setText( "Score: " + score);
        highScoreLabel.setText( "  HighScore: " + highScore);
         //The player is set to jumping false constantly until it jumps
         jumping = false;
        // the swing timer fires one or more action events after the specified delay
         jumpTimer = new Timer( 16,  new JumpActionListener()); //1ms delay between each iteration
        player = new Rectangle( 35, 60);
        player.setLocation(50, GROUND - 80 - jumpHeight);
        isGameOver = false;
        obstacles = new ObstacleList();//instantiates the arraylist
        obstacles.add( new Obstacle( 780, GROUND - 20));//Adds the obstacles to desired start point on the panel
         //Starts a new timer for the obstacle which sets the number of milliseconds between action events.
        obstacleTimer = new Timer(2, new ObstacleTimerActionListener());
        obstacleTimer.start();

    }//end of start()

    /***
     * paintComponent class is responsible for drawing the contents of game on the panel.
     *
     */

    public void paintComponent( Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("images/backgroundimage.jpg");
        img.paintIcon(this,g,0,0);
        Graphics2D g2 = ( Graphics2D) g;

        g2.setColor(playerColour);
        g2.drawRect( 50, GROUND -35 - jumpHeight, 35, 35);
        g2.fillRect( 50, GROUND -35 - jumpHeight, 35, 35);
        player.setLocation(50, GROUND - 80 - jumpHeight);

        /**
         * Iterator is used to draw obstacles endlessly to the JPanel
         */
        Iterator i = obstacles.iterator();
        while( i.hasNext()) { //while there's another obstacle in the list hasNext gets the items
            ((Obstacle)i.next()).draw(g2);//gets next obstacle and draws to screen




        }//end Iterator



    }//end of paintComponent()

    /**
     * ObstacleTimerActionListener is responsible for generating obstacles and making the obstacles move
     * across the JPanel and eventually remove each obstacle from the JPanel. It gets the coordinates of the obstacles and if the
     * players coordinates match the coordinates of the obstacle it will detect collision between the player and the obstacle and
     * game over
     */

    public class ObstacleTimerActionListener implements ActionListener {
        public void actionPerformed( ActionEvent e) {

            /**
             * The obstacles are drawn one at a time in a for loop
             */
                for ( int i = 0; i < obstacles.size(); i++) {
                Obstacle o = ( obstacles.getObstacle(i) ); // gets Obstacle from Obstacle List
                o.setLocation( o.getX() - 1, o.getY()); //set its location and -1 to start the obstacle moving

                    if ( o.getX() == -20) { //-20 so that the obstacles exit the screen
                        o.setMovingObstacle( true); //obstacle are moving across the screen
                }

            }//end for loop

            Obstacle o = ( obstacles.getObstacle( obstacles.size() - 1) ); //using the iterator
            if ( 800 - o.getX() == randomGap) { // when the obstacle has reached size of minimum gap
                obstacles.add( new Obstacle( 780, GROUND - 20)); //where the obstacles start width of panel - width of obstacles
                randomGap = (int) ( Math.random() * (MAX_SPACE - SMALL_SPACE)) + SMALL_SPACE; //randomises the size of gap between each obstacle

                score++; //score automatically goes up every second
                scoreLabel.setText( "Score: " + score);
            }

            obstacles.remove(); //removes an obstacle from list

            for ( int i = 0; i < 20; i++) {

                /*Detecting collision gets the x-coordinates and y-coordinates. getX + i will detect collision for
                full area of the obstacles If you leave out + i it will still work just wont detect collision accurately the end of each obstacle

                 */
                if ( player.contains(  (obstacles.getObstacle(0)).getX() + i , (obstacles.getObstacle(0)).getY())) {


                    isGameOver = true; /* If the box has hit the player tell the game which will save the score
                     and begin again. */
                }
            }//end for loop

            /*The game is terminated when the gameOver boolean is set to true.
              This stops any further updates to the active entities via start( ) and disables the timers */


            if ( isGameOver) {


                obstacleTimer.stop(); //stop timers
                jumpTimer.stop();


                if (score > highScore) {
                    highScore = score;
                    JOptionPane.showMessageDialog(null, "New Highscore!");
                    //FileWriter writes the highscore to a text file
                    File f = new File("highscores.txt");
                    try {
                        FileWriter fw = new FileWriter(f);
                        fw.write("" + highScore);
                        fw.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                int confirm = JOptionPane.showConfirmDialog(null, scoreLabel.getText() + "\n" + "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if ( confirm == JOptionPane.YES_OPTION)
                    start();
                else

                    System.exit(0);// window disappears
            }//end gameover

            repaint(); //refreshes the component
        }//end actionPerformed()
    }//end of ObstacleTimerActionListener()


    /**
     * JumpMouseListener provides the game with input from the mouse
     */
    public class JumpMouseListener extends MouseAdapter {

        /**
         * mouseClicked( ) method handles mouse presses on the canvas, which will allow the player to jump.
         */
        public void mouseClicked( MouseEvent e) {

            jumpTimer.setDelay(3);
            jumpTimer.start();


        }//end of mouseClicked()

    }//end of JumpMouseListener()

    /**
     * JumpActionListener is responsible for player jumping in the game
     */

    public class JumpActionListener implements ActionListener {

        public void actionPerformed( ActionEvent e) {


            //invokes the jumping method
            if ( jumpHeight == 50) {
                jumping = true;
            }

            if (jumping) {
                jumpHeight--; // brings the player down after jumping
                if ( jumpHeight == 0) {
                    jumpTimer.stop();
                    jumping = false;
                    score += 2; //scores 2 points for every jump
                    scoreLabel.setText( "Score: " + score);
                }
            }
            else {
                jumpHeight++; //allows user to jump again
            }




        }//end of actionPerformed()
    }//end of JumpActionListener()

    /***
     * JumpKeyListener provides the game with input from the keyboard
     */

    public class JumpKeyListener extends KeyAdapter {
        /**
         * keyPressed( ) method handles keyboard presses on the canvas, which will allow the player to jump.
         */
        public void keyPressed( KeyEvent e){
            if ( e.getExtendedKeyCode() == e.VK_SPACE) {
                jumpTimer.setDelay(3);
                jumpTimer.start();
            }

        }//end of keyPressed()
    }//end of JumpKeyListener()


}//end of GamePanel()
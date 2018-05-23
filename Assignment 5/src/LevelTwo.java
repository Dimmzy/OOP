import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * LevelTwo class contains information to design the second level.
 */
public class LevelTwo implements LevelInformation {


    private List<Ball> ballList;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelTwo object. Simply initializes the ball's list and their velocities list.
     */
    public LevelTwo() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();

    }


    /**
     * Creates the balls that will be a part of the level. 10 Balls in an arch pattern. Also populates the initial
     * velocities list with the ball's velocities.
     */
    private void createBalls() {
        final int BALL_START_X = 400, BALL_START_Y = 330;
        final int startOffset = 50;
        int deltaX = 40;
        final int deltaY = 20;
        for(int i = 0; i < 5; i++){
            Ball ballLeft = new Ball(new Point(BALL_START_X - startOffset - i * deltaX,
                    BALL_START_Y + i * deltaY), 5);
            ballLeft.setVelocity(Velocity.fromAngleAndSpeed(315,3));
            deltaX -= 3; // decreases the deltaX by a bit each iteration to create an arch
            this.ballList.add(ballLeft);
            this.initialVList.add(ballLeft.getVelocity());

        }
        deltaX = 40;
        for (int i = 0; i < 5; i++){
            Ball ballRight = new Ball(new Point(BALL_START_X + startOffset + i * deltaX,
                    BALL_START_Y + i * deltaY), 5);
            ballRight.setVelocity(Velocity.fromAngleAndSpeed(45,3));
            deltaX -= 3; // decreases the deltaX by a bit each iteration to create an arch
            this.ballList.add(ballRight);
            this.initialVList.add(ballRight.getVelocity());
        }
    }


    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * @return Returns the inital ball velocities list.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialVList;
    }

    /**
     * @return Returns the speed of the level's paddle.
     */
    public int paddleSpeed() {
        return 3;
    }

    /**
     * @return Returns the width of the level's paddle.
     */
    public int paddleWidth() {
        return 650;
    }

    /**
     * @return Returns the string representation of the level's name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return Returns the level's background which is created through the Backgrounds class with 2 as a parameter.
     */
    public Sprite getBackground() { return new Backgrounds(2); }


    /**
     * Creates the game blocks the player will be destroying.  A single row of 15 colorful blocks.
     * We'll be creating them in different colors according to the order in the colorOrder list which is created
     * using the classes's colorOrder method.
     * @return returns the completed list with all of the level's destructible blocks.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colorOrder = this.colorOrder();
        int j = 0;
        // Iterates through the color lists, creating the corresponding blocks in order.
        for(int i = 25; i < 775; i += 50) {
            try {
                Block gameBlock = new Block(new Point(i, 280), 50, 20, colorOrder.get(j), 1);

            j++;
            blockList.add(gameBlock);
            } catch (Exception e) {
                System.out.println("Caught Exception: Not enough colors specified for the blocks");
                break;
            }
        }
        return blockList;
    }

    /**
     * Creates a list that will dictate the order of the coloring of the blocks.
     * @return Returns the color order list.
     */
    private List<Color> colorOrder() {
        List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.RED);
        colorList.add(Color.RED);
        colorList.add(Color.ORANGE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        colorList.add(Color.GREEN);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.BLUE);
        colorList.add(Color.PINK);
        colorList.add(Color.PINK);
        colorList.add(Color.CYAN);
        colorList.add(Color.CYAN);
        return colorList;


    }


    /**
     * If the current ball list isn't empty (a new round is beginning) we'll clear it and recreate the balls.
     * Otherwise we simply create the needed balls.
     * Note: this isn't the list of the actual game's balls, but a placeholder list we'll use to check if we need to
     * recreate the balls.
     * @return Returns a list with all of the game's intended balls.
     */
    public List<Ball> balls() {
        if (!(this.ballList.isEmpty())) {
            this.ballList.clear();
        }
        this.createBalls();
        return this.ballList;
    }

    /**
     * @return returns the number of the blocks we need to remove to complete the level.
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}

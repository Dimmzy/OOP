import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * LevelThree class contains information to design the third level.
 */
public class LevelThree implements LevelInformation{

    private List<Ball> ballList;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelThree object. Simply initializes the ball's list and their velocities list.
     */
    public LevelThree() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();

    }

    /**
     * Creates the balls that will be a part of the level. 3 Balls in an arch pattern. Also populates the initial
     * velocities list with the ball's velocities.
     */
    private void createBalls() {
        Ball ballOne = new Ball(new Point(350,485),5);
        ballOne.setVelocity(Velocity.fromAngleAndSpeed(315,3));
        Ball ballTwo = new Ball(new Point(450,485),5);
        ballTwo.setVelocity(Velocity.fromAngleAndSpeed(45,3));
        this.ballList.add(ballOne);
        this.ballList.add(ballTwo);
        for(Ball ball : ballList) {
            this.initialVList.add(ball.getVelocity());
        }
    }

    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return 2;
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
        return 5    ;
    }

    /**
     * @return Returns the width of the level's paddle.
     */
    public int paddleWidth() {
        return 75;
    }

    /**
     * @return Returns the string representation of the level's name.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * @return Returns the level's background which is created through the Backgrounds class with 3 as a parameter.
     */
    public Sprite getBackground() {
        return new Backgrounds(3);
    }

    /**
     * Creates the game blocks the player will be destroying. 40 blocks arranged in a decreasing pattern.
     * We'll be creating them in different colors according to the order in the colorBlock list which is created
     * using the classes's colorOrder method.
     * @return returns the completed list with all of the level's destructible blocks.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colorOrder = this.colorOrder();
        int colorIndex = 0;
        int buildPosition = 275;
        for (int i = 175; i < 275; i += 20) {
            for (int j = buildPosition; j < 775; j += 50) {
                Block gameBlock = new Block(new Point(j,i), 50, 20, colorOrder.get(colorIndex), 1);
                blockList.add(gameBlock);
            }
            colorIndex++;
            buildPosition += 50;
        }
        return blockList;
    }


    /**
     * Creates a list that will dictate the order of the coloring of the blocks.
     * @return Returns the color order list.
     */
    private List<Color> colorOrder() {
        List<Color> colorOrder = new ArrayList<Color>();
        colorOrder.add(Color.GRAY);
        colorOrder.add(Color.RED);
        colorOrder.add(Color.YELLOW);
        colorOrder.add(Color.BLUE);
        colorOrder.add(Color.WHITE);
        return colorOrder;
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
        return 40;
    }
}

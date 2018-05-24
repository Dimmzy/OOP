import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelOne class contains the information to design the first level.
 */
public class LevelOne implements LevelInformation{

    private static final int BALL_SPEED = 5;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelOne object. Simply initializes the ball's list and their velocities list.
     */
    public LevelOne() {
        this.initialVList = new ArrayList<Velocity>();
    }

    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return initialVList.size();
    }

    /**
     * @return Returns the inital ball velocities list.
     */
    public List<Velocity> initialBallVelocities() {
        Velocity ballOne = Velocity.fromAngleAndSpeed(0,BALL_SPEED);
        this.initialVList.add(ballOne);
        return this.initialVList;
    }

    /**
     * @return Returns the speed of the level's paddle.
     */
    public int paddleSpeed() {
        return 5;
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
        return "Direct Hit";
    }


    /**
     * @return Returns the level's background which is created through the Backgrounds class with 1 as a parameter.
     */
    public Sprite getBackground() {
        return new Backgrounds(1);
    }



    /**
     * Creates the game blocks the player will be destroying.
     * In this level, simply a single red square block.
     * @return returns the completed list with all of the level's destructible blocks.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        blockList.add(new Block(new Point(390,140),20,20,Color.RED,1));
        return blockList;
    }


    /**
     * @return returns the number of the blocks we need to remove to complete the level.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}

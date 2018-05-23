import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelOne class contains the information to design the first level.
 */
public class LevelOne implements LevelInformation{

    private static final int BALL_START_X = 400, BALL_START_Y = 500;
    private List<Ball> ballList;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelOne object. Simply initializes the ball's list and their velocities list.
     */
    public LevelOne() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();
    }

    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return ballList.size();
    }

    /**
     * @return Returns the inital ball velocities list.
     */
    public List<Velocity> initialBallVelocities() {
        return initialVList;
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
     * Creates the balls that will be a part of the level. One ball shooting straight up. Also populates the initial
     * velocities list with the ball's velocities.
     */
    private void createBalls() {
        Ball ballOne = new Ball(new Point(BALL_START_X,BALL_START_Y),5);
        ballOne.setVelocity(0,-2);
        this.ballList.add(ballOne);
        for (Ball ball : this.ballList) {
            this.initialVList.add(ball.getVelocity());
        }
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

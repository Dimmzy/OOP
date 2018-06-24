package levels;

import collidables.Block;
import hitters.Velocity;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The level class contains the information that each level holds.
 */
public class Level implements LevelInformation {

    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numOfBlocks;
    private int numOfBalls;

    /**
     * Constructs the level.
     * @param levelName level name.
     * @param ballVelocities a list of the balls velocities.
     * @param paddleSpeed the speed of the paddle.
     * @param paddleWidth the width of the paddle.
     * @param background the background of the level.
     * @param numOfBlocks the number of blocks in the level.
     * @param numOfBalls the number of balls in the level.
     */
    public Level(String levelName, List<Velocity> ballVelocities, int paddleSpeed, int paddleWidth, Sprite
            background, int numOfBlocks, int numOfBalls) {
        this.levelName = levelName;
        this.ballVelocities = ballVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.background = background;
        this.blocks = new ArrayList<Block>();
        this.numOfBlocks = numOfBlocks;
        this.numOfBalls = numOfBalls;
    }


    /**
     * Adds blocks to the level.
     * @param block the block to add to the level.
     */
    public void addBlocks(Block block) {
        this.blocks.add(block);
    }

    /**
     * @return returns the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * @return returns a list of the balls starting velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * @return returns the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return returns the paddle's width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return returns the level's name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return returns the level's background.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return returns a list of the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return returns the number of blocks we need to remove to complete the level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}

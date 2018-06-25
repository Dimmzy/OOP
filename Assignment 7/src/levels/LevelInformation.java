package levels;

import collidables.Block;
import hitters.Velocity;
import sprites.Sprite;

import java.util.List;

/**
 * Interface for the level information. Contains methods that design each level.
 */
public interface LevelInformation {

    /**
     * @return Returns the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return Returns a list of the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return Returns the level's paddle speed.
     */
    int paddleSpeed();

    /**
     * @return Returns the level's paddle width.
     */
    int paddleWidth();

    /**
     * @return Returns the level's name string representation.
     */
    String levelName();

    /**
     * @return Returns a sprite that will be drawn as a background for the level
     */
    Sprite getBackground();

    /**
     * @return Returns a list of the blocks that will be created in the level.
     */
    List<Block> blocks();

    /**
     * @return Returns the number of blocks that need to be removed to finish the level.
     */
    int numberOfBlocksToRemove();
}
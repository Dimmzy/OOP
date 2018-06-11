package levels;

import collidables.Block;
import geometry.Point;
import hitters.Velocity;
import sprites.Backgrounds;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * LevelThree class contains information to design the third level.
 */
public class LevelThree implements LevelInformation {

    private static final int BALL_SPEED = 5;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelThree object. Simply initializes the ball's list and their velocities list.
     */
    public LevelThree() {
        this.initialVList = new ArrayList<Velocity>();
        this.createVelocities();
    }


    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return this.initialVList.size();
    }

    /**
     * Creates the initial velocities of the game's balls.
     */
    private void createVelocities() {
        Velocity velocityOne = Velocity.fromAngleAndSpeed(60, BALL_SPEED);
        Velocity velocityTwo = Velocity.fromAngleAndSpeed(-60, BALL_SPEED);
        this.initialVList.add(velocityOne);
        this.initialVList.add(velocityTwo);
    }

    /**
     * @return Returns the initial ball velocities list.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialVList;
    }

    /**
     * @return Returns the speed of the level's paddle.
     */
    public int paddleSpeed() {
        return 300;
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
                Block gameBlock = new Block(new Point(j, i), 50, 20, colorOrder.get(colorIndex), 1);
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
     * @return returns the number of the blocks we need to remove to complete the level.
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}

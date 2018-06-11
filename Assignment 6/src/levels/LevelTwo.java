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
 * LevelTwo class contains information to design the second level.
 */
public class LevelTwo implements LevelInformation {

    private static final int BALL_SPEED = 5;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelTwo object. Simply initializes the ball's list and their velocities list.
     */
    public LevelTwo() {
        this.initialVList = new ArrayList<Velocity>();
        this.createVelocities();
    }

    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return initialVList.size();
    }

    /**
     * Creates the initial velocities of the game's balls.
     */
    private void createVelocities() {
        // Right Side Balls
        for (int i = 25; i < 125; i += 25) {
            Velocity vel = Velocity.fromAngleAndSpeed(i, BALL_SPEED);
            this.initialVList.add(vel);
        }
        // Left Side Balls
        for (int i = 25; i > -125; i -= 25) {
            Velocity vel = Velocity.fromAngleAndSpeed(i, BALL_SPEED);
            this.initialVList.add(vel);
        }
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
        return 550;
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
        for (int i = 25; i < 775; i += 50) {
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
     * @return returns the number of the blocks we need to remove to complete the level.
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}

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
 * LevelFour class contains the information to design the fourth level.
 */
public class LevelFour implements LevelInformation {

    private static final int BALL_SPEED = 5;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelFour object. Simply initializes the ball's list and their velocities list.
     */
    public LevelFour() {
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
        Velocity velocityOne = Velocity.fromAngleAndSpeed(0, BALL_SPEED + 1);
        Velocity velocityTwo = Velocity.fromAngleAndSpeed(30, BALL_SPEED);
        Velocity velocityThree = Velocity.fromAngleAndSpeed(-30, BALL_SPEED);
        this.initialVList.add(velocityOne);
        this.initialVList.add(velocityTwo);
        this.initialVList.add(velocityThree);
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
        return "Final Four";
    }

    /**
     * @return Returns the level's background which is created through the Backgrounds class with 4 as a parameter.
     */
    public Sprite getBackground() {
        return new Backgrounds(4);
    }

    /**
     * Creates the game blocks the player will be destroying. A matrix of 15x7 blocks.
     * We'll be creating them in different colors according to the order in the colorBlock list which is created
     * using the classes's colorOrder method.
     * @return returns the completed list with all of the level's destructible blocks.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colorBlock = this.colorOrder();
        int colorIndex = 0;
        final int blockHeight = 50, blockWidth = 50;
        for (int i = 25; i < 775; i += 50) {
            for (int j = 100; j < 220; j += 20) {
                try {
                    Block gameBlock = new Block(new Point(i, j), 50, 20, colorBlock.get(colorIndex), 1);
                    blockList.add(gameBlock);
                    colorIndex++;
                } catch (Exception e) {
                    System.out.println("Caught Exception: Not enough colors specified for the block list");
                    break;
                }
            }
            colorIndex = 0; // resets the color index since we iterate through columns.
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
        colorOrder.add(Color.GREEN);
        colorOrder.add(Color.WHITE);
        colorOrder.add(Color.PINK);
        colorOrder.add(Color.CYAN);
        return colorOrder;
    }

    /**
     * @return returns the number of the blocks we need to remove to complete the level.
     */
    public int numberOfBlocksToRemove() {
        return 105;
    }
}

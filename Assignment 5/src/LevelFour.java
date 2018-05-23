import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * LevelFour class contains the information to design the fourth level.
 */
public class LevelFour implements LevelInformation {

    private List<Ball> ballList;
    private List<Velocity> initialVList;

    /**
     * Constructs the LevelFour object. Simply initializes the ball's list and their velocities list.
     */
    public LevelFour() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();
    }


    /**
     * @return returns the number of balls in the level.
     */
    public int numberOfBalls() {
        return 3;
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
        Ball ballThree = new Ball(new Point(400,435), 5);
        ballThree.setVelocity(Velocity.fromAngleAndSpeed(0,3));
        this.ballList.add(ballOne);
        this.ballList.add(ballTwo);
        this.ballList.add(ballThree);
        for(Ball ball : ballList) {
            this.initialVList.add(ball.getVelocity());
        }

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
        for(int i = 25; i < 775; i +=50) {
            for(int j = 100; j < 220; j += 20) {
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
    private List<Color> colorOrder (){
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
        return 105;
    }
}

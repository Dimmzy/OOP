import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class LevelTwo implements LevelInformation {


    private List<Ball> ballList;
    private List<Velocity> initialVList;

    public LevelTwo() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();

    }


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

    public int numberOfBalls() {
        return 10;
    }

    public List<Velocity> initialBallVelocities() {
        return this.initialVList;
    }

    public int paddleSpeed() {
        return 3;
    }

    public int paddleWidth() {
        return 650;
    }

    public String levelName() {
        return "Wide Easy";
    }

    public Sprite getBackground() { return new Backgrounds(2); }

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
     * Returns a list containing all of the levels balls. If the list is empty it first creates them (After losing
     * life for example).
     * @return returns the list containing the level's balls.
     */
    public List<Ball> balls() {
        if (!(this.ballList.isEmpty())) {
            this.ballList.clear();
        }
        this.createBalls();
        return this.ballList;
    }

    public int numberOfBlocksToRemove() {
        return 15;
    }
}

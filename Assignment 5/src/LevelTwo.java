import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class LevelTwo implements LevelInformation {


    private List<Ball> ballList;
    private List<Velocity> initialVList;

    public LevelTwo() {
        this.ballList = new ArrayList<Ball>();
        this.createBalls();
        this.initialVList = new ArrayList<Velocity>();

    }


    public void createBalls() {
        final int BALL_START_X = 400, BALL_START_Y = 330;
        int deltaX = 40;
        int deltaY = 35;
        for(int i = 0; i < 5; i++){
            Ball ballLeft = new Ball(new Point(BALL_START_X - i * deltaX, BALL_START_Y + i * deltaY), 5);
            ballLeft.setVelocity(Velocity.fromAngleAndSpeed(280,3));
            ballList.add(ballLeft);
        }
        for (int i = 0; i < 5; i++){
            Ball ballRight = new Ball(new Point(BALL_START_X + 50 + i * deltaX, BALL_START_Y + i * deltaY), 5);
            ballRight.setVelocity(Velocity.fromAngleAndSpeed(45,3));
            ballList.add(ballRight);
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
        return 50;
    }

    public String levelName() {
        return "Wide Easy";
    }

    public Sprite getBackground() { return new Backgrounds(2); }

    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colorOrder = this.colorOrder();
        int j = 0;
        for(int i = 25; i < 775; i += 50) {
            try {
                Block scoreBlock = new Block(new Point(i, 280), 50, 20, colorOrder.get(j), 1);

            j++;
            blockList.add(scoreBlock);
            } catch (Exception e) {
                System.out.println("ERROR: Not enough colors specified for the blocks");
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
    public List<Ball> balls() {
        return this.ballList;
    }

    public int numberOfBlocksToRemove() {
        return 15;
    }
}

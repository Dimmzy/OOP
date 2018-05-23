import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class LevelThree implements LevelInformation{

    private List<Ball> ballList;
    private List<Velocity> initialVList;

    public LevelThree() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();

    }

    private void createBalls() {
        Ball ballOne = new Ball(new Point(350,485),5);
        ballOne.setVelocity(Velocity.fromAngleAndSpeed(315,3));
        Ball ballTwo = new Ball(new Point(450,485),5);
        ballTwo.setVelocity(Velocity.fromAngleAndSpeed(45,3));
        this.ballList.add(ballOne);
        this.ballList.add(ballTwo);
        for(Ball ball : ballList) {
            this.initialVList.add(ball.getVelocity());
        }
    }

    public int numberOfBalls() {
        return 2;
    }

    public List<Velocity> initialBallVelocities() {
        return this.initialVList;
    }

    public int paddleSpeed() {
        return 5    ;
    }

    public int paddleWidth() {
        return 75;
    }

    public String levelName() {
        return "Green 3";
    }

    public Sprite getBackground() {
        return new Backgrounds(3);
    }

    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        List<Color> colorOrder = this.colorOrder();
        int colorIndex = 0;
        int buildPosition = 275;
        for (int i = buildPosition; i < 775; i += 50) {
            for (int j = 175; j < 275; j += 20) {
                try {
                    Block gameBlock = new Block(new Point(i, j), 50, 20, colorOrder.get(colorIndex), 1);
                    blockList.add(gameBlock);
                    colorIndex++;
                } catch (Exception e) {
                    System.out.println("Caught Exception: Not enough colors specified for the blocks");
                    break;
                }
            }
            colorIndex = 0;
            buildPosition += 50;
        }
        return blockList;
    }

    private List<Color> colorOrder() {
        List<Color> colorOrder = new ArrayList<Color>();
        colorOrder.add(Color.GRAY);
        colorOrder.add(Color.RED);
        colorOrder.add(Color.YELLOW);
        colorOrder.add(Color.BLUE);
        colorOrder.add(Color.WHITE);
        return colorOrder;
    }

    public List<Ball> balls() {
        if (!(this.ballList.isEmpty())) {
            this.ballList.clear();
        }
        this.createBalls();
        return this.ballList;
    }

    public int numberOfBlocksToRemove() {
        return 40;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class LevelFour implements LevelInformation {

    private List<Ball> ballList;
    private List<Velocity> initialVList;

    public LevelFour() {
        this.ballList = new ArrayList<Ball>();
        this.initialVList = new ArrayList<Velocity>();
    }

    public int numberOfBalls() {
        return 3;
    }

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

    public List<Velocity> initialBallVelocities() {
        return this.initialVList;
    }

    public int paddleSpeed() {
        return 5;
    }

    public int paddleWidth() {
        return 75;
    }

    public String levelName() {
        return "Final Four";
    }

    public Sprite getBackground() {
        return new Backgrounds(4);
    }

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

    public List<Ball> balls() {
        if (!(this.ballList.isEmpty())) {
            this.ballList.clear();
        }
        this.createBalls();
        return this.ballList;
    }

    public int numberOfBlocksToRemove() {
        return 105;
    }
}

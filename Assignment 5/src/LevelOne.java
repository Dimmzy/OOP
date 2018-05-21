import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LevelOne implements LevelInformation{

    private static final int BALL_START_X = 400, BALL_START_Y = 500;
    private List<Ball> ballList;
    private List<Velocity> initialVList;

    public LevelOne() {
        this.ballList = new ArrayList<Ball>();
        Ball ballOne = new Ball(new Point(BALL_START_X,BALL_START_Y),5);
        ballOne.setVelocity(0,-2);
        this.ballList.add(ballOne);
        this.initialVList = new ArrayList<Velocity>();
        for (Ball ball : this.ballList) {
            this.initialVList.add(ball.getVelocity());
        }

    }

    public int numberOfBalls() {
        return ballList.size();
    }

    public List<Velocity> initialBallVelocities() {
        return initialVList;
    }

    public int paddleSpeed() {
        return 3;
    }

    public int paddleWidth() {
        return 50;
    }

    public String levelName() {
        return "Direct Hit";
    }

    public Sprite getBackground() {
        return new Backgrounds(1);
    }

    public List<Ball> balls() {
        return this.ballList;
    }

    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        blockList.add(new Block(new Point(390,140),20,20,Color.RED,1));
        return blockList;
    }

    public int numberOfBlocksToRemove() {
        return 1;
    }
}

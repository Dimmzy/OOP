import java.util.ArrayList;
import java.util.List;

public class LevelTwo implements LevelInformation {


    private List<Ball> ballList;
    private List<Velocity> initalVList;

    public LevelTwo() {
        this.ballList = new ArrayList<Ball>(ballList);

    }


    public void createBalls() {
        int delta = 20;
        for(int i = 0; i < 3; i++){
            Ball ballLeft = new Ball(new Point(400 - delta, 300), 5);
            ballList.add(ballLeft);
        }
    }

    public int numberOfBalls() {
        return 10;
    }

    public List<Velocity> initialBallVelocities() {
        return null;
    }

    public int paddleSpeed() {
        return 0;
    }

    public int paddleWidth() {
        return 0;
    }

    public String levelName() {
        return null;
    }

    public Sprite getBackground() {
        return null;
    }

    public List<Block> blocks() {
        return null;
    }

    public List<Ball> balls() {
        return null;
    }

    public int numberOfBlocksToRemove() {
        return 0;
    }
}

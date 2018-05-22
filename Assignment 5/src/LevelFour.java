import java.util.List;

public class LevelFour implements LevelInformation {

    private List<Ball> ballList;
    private List<Velocity> initialVList;

    public int numberOfBalls() {
        return 3;
    }

    private void createBalls() {

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
        if (!(this.ballList.isEmpty())) {
            this.ballList.clear();
        }
        this.createBalls();
        return this.ballList;
    }

    public int numberOfBlocksToRemove() {
        return 0;
    }
}

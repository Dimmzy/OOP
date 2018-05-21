import java.util.List;

public interface LevelInformation {
    int numberOfBalls();
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    String levelName();
    Sprite getBackground();
    List<Block> blocks();
    List<Ball> balls();
    int numberOfBlocksToRemove();
}
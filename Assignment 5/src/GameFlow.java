import java.util.List;
import biuoop.KeyboardSensor;

/**
 * GameFlow class is in charge of controlling the flow of the game creating levels and moving the player through them.
 */
public class GameFlow {

    private final static int STARTING_LIVES = 7;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter livesCounter, totalScore;
    private boolean gameCompleted;

    /**
     *
     * @param ar
     * @param ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.livesCounter = new Counter();
        this.livesCounter.increase(STARTING_LIVES);
        this.totalScore = new Counter();
        this.gameCompleted = false;

    }

    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.livesCounter,
                    this.totalScore);
            level.initialize();
            while (level.getLivesCounter().getValue() >= 0 && level.getBlockCounter().getValue() > 0) {
                level.playOneTurn();
            }
            if (level.getLivesCounter().getValue() < 0) {
                break;
            }
        }
        if (this.livesCounter.getValue() >= 0) {
            this.gameCompleted = true;
        }
    }

    public boolean getGameCompleted() {
        return this.gameCompleted;
    }

    public int getScore() {
        return this.totalScore.getValue();
    }
}

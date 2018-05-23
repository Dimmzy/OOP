import java.util.List;

/**
 * GameFlow class is in charge of controlling the flow of the game creating levels and moving the player through them.
 */
public class GameFlow {

    AnimationRunner animationRunner;
    private biuoop.KeyboardSensor keyboardSensor;

    /**
     *
     * @param ar
     * @param ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, ...) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;

    }

    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner);

            level.initialize();

            while (level has more blocks and player has more lives) {
                level.playOneTurn();
            }

            if (no more lives) {
                break;
            }

        }
    }
}

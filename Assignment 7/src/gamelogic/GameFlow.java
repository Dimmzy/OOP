package gamelogic;

import java.util.List;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import indicators.Counter;
import levels.LevelInformation;

/**
 * GameFlow class is in charge of controlling the flow of the game creating levels and moving the player through them.
 */
public class GameFlow {

    private static final int STARTING_LIVES = 3;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter livesCounter, totalScore;
    private boolean gameCompleted;

    /**
     * The GameFlow constructor. Receives an AnimationRunner that will run the level's animations and the keyboard
     * scanner to check for keystrokes.
     * @param ar The game's Animation Runner to run the animations.
     * @param ks GUI's keyboard sensor to scan for keystrokes.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        // Initializes the number of starting lives (from the saved const field) and the score (from 0)
        this.livesCounter = new Counter();
        this.livesCounter.increase(STARTING_LIVES);
        this.totalScore = new Counter();
        this.gameCompleted = false;

    }

    /**
     * Runs the levels in the levels list in order. If the player loses all their lives it ends the game prematurely.
     * Otherwise, goes on until all the levels have been iterated through.
     * @param levels The list of levels we'll play through.
     */
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
            totalScore.increase(100);
        }
        // If the loop finished with lives still remaining, set the game completed to true.
        if (this.livesCounter.getValue() >= 0) {
            this.gameCompleted = true;
        }
    }

    /**
     * @return Returns a boolean value whether the game has been completely fully or not. (Finished last level)
     */
    public boolean getGameCompleted() {
        return this.gameCompleted;
    }

    /**
     * @return Returns the final score of the game so we can draw it on the end screen.
     */
    public int getScore() {
        return this.totalScore.getValue();
    }

    /**
     * Restarts the values of the level (for each game iteration).
     */
    public void restart() {
        this.livesCounter = new Counter();
        this.livesCounter.increase(3);
        this.totalScore = new Counter();
        this.gameCompleted = false;
    }

}

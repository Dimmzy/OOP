package gamelogic;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import hitters.Velocity;
import indicators.Counter;
import utilities.ExtractImage;

/**
 * GameFlow class is in charge of controlling the flow of the game creating levels and moving the player through them.
 */
public class GameFlow {

    private static final int STARTING_LIVES = 0;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter livesCounter, totalScore, levelsCleared;
    private List<Image> alienImages;

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
        this.levelsCleared = new Counter();
        this.totalScore = new Counter();
        this.alienImages = new ArrayList<Image>();
        this.alienImages.add(new ExtractImage("AlienSprite1.png").getImage());
        this.alienImages.add(new ExtractImage("AlienSprite2.png").getImage());
    }

    /**
     * runs the level, increasing alien speed by 25% each time the player finishes the game with lives remaining.
     */
    public void runLevel() {
        Velocity alienSpeed = new Velocity(50, 0);
        while (true) {
            GameLevel levelStart = new GameLevel(this.keyboardSensor, this.animationRunner, this.livesCounter, this
                    .totalScore, alienSpeed, this.alienImages);
            levelStart.initialize();
            while (levelStart.getLivesCounter().getValue() >= 0 && levelStart.getBlockCounter().getValue() > 0) {
                levelStart.playOneTurn();
            }
            if (levelStart.getLivesCounter().getValue() < 0) {
                break;
            }
            if (this.livesCounter.getValue() >= 0) {
                alienSpeed = new Velocity(alienSpeed.getDx() * 1.25, 0);
                this.levelsCleared.increase(1);
            }
        }
    }


    /**
     * @return Returns the final score of the game so we can draw it on the end screen.
     */
    public int getScore() {
        return this.totalScore.getValue();
    }

    /**
     * @return Returns the amount of levels cleared at the end of the game.
     */

    public int getLevelsCleared() { return this.levelsCleared.getValue(); }

    /**
     * Restarts the values of the level (for each game iteration).
     */
    public void restart() {
        this.livesCounter = new Counter();
        this.livesCounter.increase(3);
        this.totalScore = new Counter();
    }

}

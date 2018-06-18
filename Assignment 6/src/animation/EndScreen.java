package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * EndScreen is an animation class that draws the final screen showing whether the player won or lost, and the final
 * score.
 */
public class EndScreen implements Animation {

    private boolean gameCompleted;
    private int finalScore;
    private KeyboardSensor keySensor;
    private boolean shouldStop;

    /**
     * The EndScreen constructor.
     * @param gameCompleted Boolean value whether the game was won or not.
     * @param score The final score of the player.
     */
    public EndScreen(boolean gameCompleted, int score) {
        this.gameCompleted = gameCompleted;
        this.finalScore = score;
        this.shouldStop = false;
    }


    /**
     * Draws a frame of the game completion screen.
     * @param d The DrawSurface we'll draw the sprites on.
     * @param dt isn't used.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (gameCompleted) {
            Color victoryGreen = new Color(96, 203, 86);
            d.setColor(victoryGreen);
            d.drawText(200, 300, "You Win! Your score is " + Integer.toString(finalScore), 25);
        } else {
            Color defeatBlue = new Color(64, 120, 178);
            d.setColor(defeatBlue);
            d.drawText(200, 300, "Game Over. Your score is " + Integer.toString(finalScore), 25);
        }
    }

    /**
     * @return Returns whether the animation should stop after this frame.
     */
    public boolean shouldStop() { return this.shouldStop; }

}

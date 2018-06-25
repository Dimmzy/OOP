package animation;

import biuoop.DrawSurface;

import utilities.ExtractImage;

import java.awt.Color;
import java.awt.Image;

/**
 * EndScreen is an animation class that draws the final screen showing whether the player won or lost, and the final
 * score.
 */
public class EndScreen implements Animation {

    private int finalScore;
    private int levelsCleared;
    private boolean shouldStop;
    private Image gameOverImg;
    /**
     * The EndScreen constructor.
     * @param score The final score of the player.
     * @param levelsCleared the final amount of levels the player cleared before losing all of his lives.
     */
    public EndScreen(int score, int levelsCleared) {
        this.gameOverImg = new ExtractImage("gameoverImage.png").getImage();
        this.finalScore = score;
        this.levelsCleared = levelsCleared;
        this.shouldStop = false;
    }


    /**
     * Draws a frame of the game completion screen.
     * @param d The DrawSurface we'll draw the sprites on.
     * @param dt isn't used.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.GREEN);
        d.drawText(250, 100, "SPACE INVADERS", 40);
        d.setColor(Color.GREEN);
        d.drawText(249, 99, "SPACE INVADERS", 40);
        d.setColor(Color.RED);
        d.drawText(300, 350, "Your final score is " + Integer.toString(this.finalScore), 25);
        d.drawText(299, 349, "Your final score is " + Integer.toString(this.finalScore), 25);
        d.drawText(300, 400, "And you cleared " + Integer.toString(this.levelsCleared) + " level(s)!", 25);

        d.drawImage(300, 100, this.gameOverImg);
    }

    /**
     * @return Returns whether the animation should stop after this frame.
     */
    public boolean shouldStop() { return this.shouldStop; }

}

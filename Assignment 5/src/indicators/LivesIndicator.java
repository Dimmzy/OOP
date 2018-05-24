package indicators;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * LivesIndicator class is a text sprite that prints the number of lives remaining.
 */
public class LivesIndicator implements Sprite {

    private static final int LIVES_X = 200, LIVES_Y = 13, LIVES_FONT = 12;
    private Counter livesCounter;

    /**
     * Constructs the LivesIndicatior, initializes the counter using the counter parameter passed.
     * @param livesCounter The counter containing the amount of life the sprite will draw.
     */
    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }

    /**
     * Draws the sprite on the screen at the constant location and font size.
     * @param d The surface we'll draw the indicator on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(LIVES_X, LIVES_Y, "Lives: " + Integer.toString(livesCounter.getValue()), LIVES_FONT);
    }

    /**
     * Isn't used since the drawing doesn't change overtime (the counter does).
     */
    public void timePassed() { }
}

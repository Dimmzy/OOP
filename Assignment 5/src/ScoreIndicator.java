import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The ScoreIndicator class is a text sprite that prints the player's score.
 */
public class ScoreIndicator implements Sprite {

    private static final int SCOREBOARD_X = 350, SCOREBOARD_Y = 13, SCOREBOARD_FONT = 12;
    private Counter scoreCounter;

    /**
     * Constructs the ScoreIndiciator object using the passed Counter.
     * @param scoreCounter The counter we'll use to draw the current score frmo
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * Draws the sprite on the screen at the constant location and font.
     * @param d The surface we'll draw the indicator on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(SCOREBOARD_X,SCOREBOARD_Y,"Score: " + Integer.toString(scoreCounter.getValue()), SCOREBOARD_FONT);
    }

    /**
     * Isn't used since the drawing doesn't change overtime (the counter does).
     */
    public void timePassed() { }
}

package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * LevelIndicator class is responsible for drawing the level name on the upper left area of the screen.
 */
public class LevelIndicator implements Sprite {

    private static final int LEVEL_NAME_X = 500, LEVEL_NAME_Y = 13, LEVEL_NAME_FONT = 12;
    private String levelName;

    /**
     * LevelIndicator constructor. Receives the level's name string representation and saves it as a field.
     * @param levelName The level's name string representation.
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draws the levels name on the location specified in the class's fields.
     * @param d The surface to draw the object on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawText(LEVEL_NAME_X, LEVEL_NAME_Y, "Level Name: " + this.levelName, LEVEL_NAME_FONT);
    }

    /**
     * Isn't used since the name is a static sprite.
     * @param dt not used.
     */
    public void timePassed(double dt) { }
}

package sprites;

import biuoop.DrawSurface;

/**
 * Sprite Interface. Sprite objects are objects that can be drawn on the screen and change their behavior.
 */
public interface Sprite {

    /**
     * Draws the object on the provided DrawSurface.
     * @param d The surface tod raw the object on.
     */
    void drawOn(DrawSurface d);

    /**
     * Tells the object that "time" has passed (per intervals specified in the GameLevel class).
     */
    void timePassed(double dt);
}
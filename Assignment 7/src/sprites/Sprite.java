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
     * @param dt let's use normalize the speed of the moving objects to be pixels per second rather than fps.
     */
    void timePassed(double dt);
}
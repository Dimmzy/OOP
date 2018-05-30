package animation;

import biuoop.DrawSurface;

/**
 * Interfaces that provides method signatures for objects that'll be animated.
 */
public interface Animation {

    /**
     * Draws the sprites for a single frame.
     * @param d The DrawSurface we'll draw the sprites on.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return Returns a boolean value whether the animation should stop after this frame.
     */
    boolean shouldStop();
}
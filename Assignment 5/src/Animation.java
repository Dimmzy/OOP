import biuoop.DrawSurface;

/**
 * Interfaces that provides method signatures for objects that'll be animated.
 */
public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}
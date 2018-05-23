import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The PauseScreen class provides logic to creating a pause screen animation when the player pressed the P key.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructs the PauseScreen object with a passed keyboard sensor.
     * @param k The keyboard sensor we'll use to see if there's a need to stop the pausing.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Each frame we draw the pause screen, if SPACE has been pressed we'll finish the animation.
     * @param d The surface we'll draw the pause screen on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * @return Returns if the animation should stop to the animation runner.
     */
    public boolean shouldStop() { return this.stop; }
}
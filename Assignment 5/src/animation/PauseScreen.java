package animation;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

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
        // Small pause sprite
        d.setColor(Color.BLUE);
        d.fillRectangle(335, 150, 15, 50);
        d.fillRectangle(365, 150, 15, 50);
        d.setColor(Color.RED);
        d.drawText(300, (int) (d.getHeight() / 2.5), "Paused", 35);
        d.setColor(Color.BLACK);
        d.drawText(200, d.getHeight() / 2, " Press SPACE to continue", 25);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    /**
     * @return Returns if the animation should stop to the animation runner.
     */
    public boolean shouldStop() { return this.stop; }
}
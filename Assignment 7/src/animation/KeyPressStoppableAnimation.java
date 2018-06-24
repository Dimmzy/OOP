package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A class that we use to wrap other classes which are animations we'd like to stop with a keystroke.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keySensor;
    private String terminateKey;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The class constructor.
     * @param sensor the keyboard sensor we use.
     * @param key the key that stops the animation.
     * @param animation the animation the object is in charge of stopping.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keySensor = sensor;
        this.terminateKey = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Stops the animation in case the terminating key is pressed.
     * @param d The DrawSurface we'll draw the sprites on.
     * @param dt Frame dependant speed.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keySensor.isPressed(terminateKey)) {
            if (this.isAlreadyPressed) {
                return;
            }
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * @return returns whether this animation should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
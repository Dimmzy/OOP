package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keySensor;
    private String terminateKey;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keySensor = sensor;
        this.terminateKey = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d,dt);
        if (this.keySensor.isPressed(terminateKey)) {
            if (this.isAlreadyPressed) {
                return;
            }
            this.stop = true;
        }
        this.isAlreadyPressed = false;
    }

    public boolean shouldStop() {
        return this.stop;
    }



}
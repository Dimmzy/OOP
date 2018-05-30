package animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountDownAnimation class handles the countdown between rounds of the game. Implements Animation.
 */
public class CountdownAnimation implements Animation {
    private int countFrom;
    private long startTime;
    private long counterUptime;
    private SpriteCollection gameScreen;
    private boolean stop;


    /**
     * Constructs the CountDownAnimation using variables that dictate the length and hiatus of the countdown.
     * @param numOfSeconds Number of seconds between each numeral in the countdown.
     * @param countFrom The number we count down from.
     * @param gameScreen The game screen we'll draw in the background of the countdown (so we can see the game screen
     *                  before it starts).
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.counterUptime = ((long) (numOfSeconds * 1000) / countFrom);
        this.stop = false;
    }

    /**
     * An animation method that dictates what happens in a single frame. In this case we'll be drawing the number
     * we're currently at in the countdown, and if (num of seconds / countfrom) seconds have passed, we'll decrease
     * the amount of numbers we still have to go.
     * @param d The DrawSurface where we'll draw our countdown on.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        gameScreen.drawAllOn(d);
        // Adds a small shadow to the countdown timer.
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 + 2, d.getHeight() / 2 - 2, Integer.toString(countFrom), 40);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 40);
        long timePassed = System.currentTimeMillis() - this.startTime;
        if (timePassed > this.counterUptime) {
            this.countFrom--;
            this.startTime = System.currentTimeMillis();
        }
        if (countFrom <= 0) {
            this.stop = true;
        }
    }

    /**
     * @return Returns a boolean value whether the animation should stop after this frame.
     */
    public boolean shouldStop() { return this.stop; }
}
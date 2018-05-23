import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The CountDownAnimation class handles the countdown between rounds of the game. Implements Animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private long startTime;
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
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.stop = false;
    }

    /**
     * An animation method that dictates what happens in a single frame. In this case we'll be drawing the number
     * we're currently at in the countdown, and if (num of seconds / countfrom) seconds have passed, we'll decrease
     * the amount of numbers we still have to go.
     * @param d The DrawSurface where we'll draw our countdown on.
     */
    public void doOneFrame(DrawSurface d) {
        if (countFrom <= 0) {
            this.stop = true;
            // Draw the game to avoid a frame where we see nothing when the counter finishes.
            gameScreen.drawAllOn(d);
            return;
        }
        gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 40);
        long timePassed = System.currentTimeMillis() - this.startTime;
        long counterUptime = ((long)this.numOfSeconds / this.countFrom) * 1000;
        if (timePassed < counterUptime) {
            return;
        }
        this.countFrom--;
    }
    public boolean shouldStop() { return this.stop; }
}
import biuoop.DrawSurface;
import java.awt.Color;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private long startTime;
    private SpriteCollection gameScreen;
    private boolean stop;


    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.stop = false;
    }

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
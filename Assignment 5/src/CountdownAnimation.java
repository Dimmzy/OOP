import biuoop.DrawSurface;
import biuoop.Sleeper;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int countLeft;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.countLeft = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    public void doOneFrame(DrawSurface d) {
        if (countLeft <= 0) {
            this.stop = true;
        }
        gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countLeft), 32);
        sleeper.sleepFor((((long) this.numOfSeconds / this.countFrom) * 1000));
        this.countLeft--;
    }
    public boolean shouldStop() { return this.stop; }
}
import biuoop.DrawSurface;

public class LivesIndicator implements Sprite {
    private static final int LIVES_X = 250, LIVES_Y = 13, LIVES_FONT = 12;
    private Counter livesCounter;

    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }
    public void drawOn(DrawSurface d) {
        d.drawText(LIVES_X,LIVES_Y,"Lives: " + Integer.toString(livesCounter.getValue()), LIVES_FONT);
    }

    public void timePassed() { }
}

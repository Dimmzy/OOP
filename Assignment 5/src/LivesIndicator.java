import biuoop.DrawSurface;
import java.awt.Color;

public class LivesIndicator implements Sprite {
    private static final int LIVES_X = 250, LIVES_Y = 13, LIVES_FONT = 12;
    private Counter livesCounter;

    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(LIVES_X,LIVES_Y,"Lives: " + Integer.toString(livesCounter.getValue()), LIVES_FONT);
    }

    public void timePassed() { }
}

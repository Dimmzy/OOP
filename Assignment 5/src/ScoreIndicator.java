import biuoop.DrawSurface;
import java.awt.Color;

public class ScoreIndicator implements Sprite {
    private static final int SCOREBOARD_X = 300, SCOREBOARD_Y = 13, SCOREBOARD_FONT = 12;
    private Counter scoreCounter;

    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(SCOREBOARD_X,SCOREBOARD_Y,"Score: " + Integer.toString(scoreCounter.getValue()), SCOREBOARD_FONT);
    }

    public void timePassed() { }
}

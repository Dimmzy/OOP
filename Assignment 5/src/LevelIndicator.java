import java.awt.Color;
import biuoop.DrawSurface;

public class LevelIndicator implements Sprite{

    private static final int LEVEL_NAME_X = 500, LEVEL_NAME_Y = 13, LEVEL_NAME_FONT = 12;
    private String levelName;

    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(LEVEL_NAME_X, LEVEL_NAME_Y, "Level Name: " + this.levelName, LEVEL_NAME_FONT);
    }

    public void timePassed() {

    }
}

import biuoop.DrawSurface;
import java.awt.Color;

public class Backgrounds implements Sprite{

    int levelNumber;

    public Backgrounds(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void drawOn(DrawSurface d) {
        if (levelNumber == 1) {
            d.setColor(Color.BLUE);
            d.fillRectangle(20,20,780,600);
            d.setColor(Color.BLUE);
            d.drawCircle(400,150,40);
            d.drawCircle(400,150,60);
            d.drawCircle(400,150,80);
            d.drawLine(380,150 ,300, 150);
            d.drawLine(420, 150, 500, 150);
            d.drawLine(400, 130, 400, 50);
            d.drawLine(400, 170, 400, 250);
        }
    }

    /**
     * Does nothing since it's a static background image.
     */
    public void timePassed() {

    }
}

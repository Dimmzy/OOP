import biuoop.DrawSurface;
import java.awt.Color;

public class
Backgrounds implements Sprite{

    int levelNumber;

    public Backgrounds(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void drawOn(DrawSurface d) {
        switch(levelNumber) {
            case(1):
                d.setColor(Color.BLUE);
                d.fillRectangle(25, 25, 775, 600);
                d.setColor(Color.BLUE);
                d.drawCircle(400, 150, 40);
                d.drawCircle(400, 150, 60);
                d.drawCircle(400, 150, 80);
                d.drawLine(380, 150, 300, 150);
                d.drawLine(420, 150, 500, 150);
                d.drawLine(400, 130, 400, 50);
                d.drawLine(400, 170, 400, 250);
                break;

            case(2):
                d.setColor(Color.WHITE);
                d.fillRectangle(25, 25, 775, 600);
                Color yellowish = new Color(204, 204, 51);
                d.setColor(yellowish);
                for(int i = 50; i > 45; i--) { // Outer Circle
                    d.drawCircle(200, 150, i);
                }
                for(int i = 20; i < 720; i += 20) { // Sun Rays
                    d.drawLine(200,150,i,200);
                }
                Color orangeish = new Color(230, 179, 25);
                d.setColor(orangeish);
                d.fillCircle(200,150,45); // Inner Circle
                d.setColor(Color.YELLOW);
                d.fillCircle(200,150,30); // Innermost Circle
                break;

            case(3):



        }

    }

    /**
     * Does nothing since the class draws a static background image.
     */
    public void timePassed() { }
}

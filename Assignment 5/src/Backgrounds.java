import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

public class
Backgrounds implements Sprite{

    private int levelNumber;
    private Random rand = new Random();

    public Backgrounds(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void drawOn(DrawSurface d) {
        switch(levelNumber) {
            case(1):
                d.setColor(Color.BLUE);
                d.fillRectangle(25, 25, 775, 600); // Background
                d.setColor(Color.BLUE);
                d.drawCircle(400, 150, 40); // Innermost Circle
                d.drawCircle(400, 150, 60); // Inner Circle
                d.drawCircle(400, 150, 80); // Outer Circle
                d.drawLine(380, 150, 300, 150); // Left Line
                d.drawLine(420, 150, 500, 150); // Right Line
                d.drawLine(400, 130, 400, 50); // Upper Line
                d.drawLine(400, 170, 400, 250); // Bottom Line
                break;

            case(2):
                d.setColor(Color.WHITE);
                d.fillRectangle(25, 25, 775, 600); // Background
                Color outer = new Color(239, 231, 176);
                d.setColor(outer);
                for(int i = 50; i > 39; i--) { // Outer Circle
                    d.drawCircle(200, 150, i);
                }
                for(int i = 20; i < 720; i += 20) { // Sun Rays
                    d.drawLine(200,150,i,280);
                }
                Color inner = new Color(236, 215, 73);
                d.setColor(inner);
                d.fillCircle(200,150,40); // Inner Circle
                Color innerMost = new Color(255,225,24);
                d.setColor(innerMost);
                d.fillCircle(200,150,30); // Innermost Circle
                break;

            case(3):
                Color backgroundThree = new Color(42,130,21);  // Background
                d.setColor(backgroundThree);
                d.fillRectangle(25, 25, 775, 600);

                Color building = new Color(46,42,41); // Building
                d.setColor(building);
                d.fillRectangle(65,400,100,200);

                d.setColor(Color.WHITE); // Nested loop to create windows
                for(int i = 77; i < 150; i += 20) {
                    for (int j = 415; j < 600; j += 30) {
                        d.fillRectangle(i,j,15,25);
                    }
                }

                Color antBase = new Color(62,58,57); // Antenna Base
                d.setColor(antBase);
                d.fillRectangle(105,370,20,30);

                Color antenna = new Color(78,74,73); // Antenna
                d.setColor(antenna);
                d.fillRectangle(113,245,4,125);

                Color outerCircle = new Color(216,172,102); // Outer Light Circle
                d.setColor(outerCircle);
                d.fillCircle(115,235,10);

                Color innerCircle = new Color(246,77,54); // Inner Light Circle
                d.setColor(innerCircle);
                d.fillCircle(115,235,5);

                d.setColor(Color.WHITE);
                d.fillCircle(115,235,2); // Light Core
                break;

            case(4):
                Color backgroundFour = new Color(23,136,208);
                d.setColor(backgroundFour);
                d.fillRectangle(25, 25, 775, 600); // Background

        }

    }

    /**
     * Does nothing since the class draws a static background image.
     */
    public void timePassed() { }
}

package sprites;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The backgrounds class provides us with the drawings for each level's backgrounds. Implements sprite.
 */
public class Backgrounds implements Sprite {

    private int levelNumber;

    /**
     * The backgrounds constructors, receives the number of the level.
     * @param levelNumber The level number we'll drawn on the DrawSurface.
     */
    public Backgrounds(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Using a switch statement corresponding to the level number, we'll draw the correct background.
     * @param d The surface we'll draw the background on.
     */
    public void drawOn(DrawSurface d) {
        switch(levelNumber) {

            // Level One
            case(1):
                d.setColor(Color.BLACK);
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

            //Level Two
            case(2):
                d.setColor(Color.WHITE);
                d.fillRectangle(25, 25, 775, 600); // Background

                Color outer = new Color(239, 231, 176);
                d.setColor(outer);
                for (int i = 50; i > 39; i--) { // Outer Circle
                    d.drawCircle(200, 150, i);
                }
                for (int i = 20; i < 720; i += 20) { // Sun Rays
                    d.drawLine(200, 150, i, 280);
                }
                Color inner = new Color(236, 215, 73);
                d.setColor(inner);
                d.fillCircle(200, 150, 40); // Inner Circle

                Color innerMost = new Color(255, 225, 24);
                d.setColor(innerMost);
                d.fillCircle(200, 150, 30); // Innermost Circle
                break;

             //Level Three
            case(3):
                Color backgroundThree = new Color(42, 130, 21);
                d.setColor(backgroundThree);
                d.fillRectangle(25, 25, 775, 600);  // Background

                Color building = new Color(46, 42, 41);
                d.setColor(building);
                d.fillRectangle(65, 400, 100, 200); // Building

                d.setColor(Color.WHITE); // Nested loop to create windows
                for (int i = 77; i < 150; i += 20) {
                    for (int j = 415; j < 600; j += 30) {
                        d.fillRectangle(i, j, 15, 25);
                    }
                }

                Color antBase = new Color(62, 58, 57);
                d.setColor(antBase);
                d.fillRectangle(105, 370, 20, 30); // Antenna Base

                Color antenna = new Color(78, 74, 73);
                d.setColor(antenna);
                d.fillRectangle(113, 245, 4, 125); // Antenna

                Color outerCircle = new Color(216, 172, 102); // Outer Light Circle
                d.setColor(outerCircle);
                d.fillCircle(115, 235, 10);

                Color innerCircle = new Color(246, 77, 54); // Inner Light Circle
                d.setColor(innerCircle);
                d.fillCircle(115, 235, 5);

                d.setColor(Color.WHITE);
                d.fillCircle(115, 235, 2); // Light Core
                break;

            //Level Four
            case(4):
                Color backgroundFour = new Color(23, 136, 208);
                d.setColor(backgroundFour);
                d.fillRectangle(25, 25, 775, 600); // Background

                Color lighterGray = new Color(200, 204, 208);

                // Left Cloud
                d.setColor(Color.WHITE);
                for (int j = 0; j < 70; j += 15) { // Rain Stream
                    d.drawLine(80 + j, 360, 50 + j, 600);
                }
                d.setColor(lighterGray);
                d.fillCircle(75, 325, 40);
                d.fillCircle(100, 345, 40);
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(95, 318, 44);
                d.setColor(Color.GRAY);
                d.fillCircle(145, 325, 35);
                d.fillCircle(135, 355, 35);

                // Middle Cloud
                d.setColor(Color.YELLOW);
                d.drawLine(430, 400, 400, 500);
                d.drawLine(400, 500, 420, 500);
                d.drawLine(420, 500, 400, 550);
                d.drawLine(480, 400, 450, 500);
                d.drawLine(450, 500, 470, 500);
                d.drawLine(470, 500, 450, 550);
                d.setColor(lighterGray);
                d.fillCircle(420, 400, 40);
                d.fillCircle(450, 410, 35);
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(450, 390, 40);
                d.setColor(Color.GRAY);
                d.fillCircle(390, 390, 30);
                d.fillCircle(420, 360, 30);

                // Right Cloud
                d.setColor(Color.WHITE);
                for (int j = 0; j < 70; j += 15) { // Rain Drops
                    for (int k = 450; k < 600; k += 10) {
                        d.drawLine(650 + j, k, 645 + j, k + 5);
                    }
                }
                d.setColor(lighterGray);
                d.fillCircle(650, 445, 40);
                d.fillCircle(680, 420, 35);
                d.setColor(Color.GRAY);
                d.fillCircle(665, 455, 35);
                d.fillCircle(690, 435, 25);
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(650, 440, 35);
                d.fillCircle(670, 435, 30);
                break;
            default:
                break;
        }
    }

    /**
     * Does nothing since the class draws a static background image.
     */
    public void timePassed() { }
}

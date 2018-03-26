import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 *
 */
public class AbstractArtDrawing {

    /**
     * @return a new line object with random values
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        int xStart = rand.nextInt(800);
        int yStart = rand.nextInt(600);
        int xEnd = rand.nextInt(800);
        int yEnd = rand.nextInt(600);
        Line newLine = new Line(xStart, yStart, xEnd, yEnd);
        return newLine;
    }

    /**
     *
     */
    public void drawLines() {
        GUI gui = new GUI("Random Lines", 800, 600);
        DrawSurface surface = gui.getDrawSurface();
        surface.setColor(Color.BLACK);
        Line[] lineArray = new Line[10];
        for (int i = 0; i < 10; i++) {
            Line randLine = generateRandomLine();
            lineArray[i] = randLine;
            int xStart = (int) randLine.start().getX();
            int yStart = (int) randLine.start().getY();
            int xEnd = (int) randLine.end().getX();
            int yEnd = (int) randLine.end().getY();
            surface.drawLine(xStart, yStart, xEnd, yEnd);
        }
        surface.setColor(Color.RED);
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 10; j++) {
                if (lineArray[i].isIntersecting(lineArray[j]) && lineArray[j].isIntersecting(lineArray[i])){
                    Point intersect = lineArray[i].intersectionWith(lineArray[j]);
                    surface.fillCircle((int) intersect.getX(), (int) intersect.getY(),3);
                }
            }
        }
        for (int k = 0; k < 10; k++) {
            int xMid = (int) lineArray[k].middle().getX();
            int yMid = (int) lineArray[k].middle().getY();
            surface.setColor(Color.BLUE);
            surface.fillCircle(xMid, yMid, 3);
        }
        gui.show(surface);
    }

    /**
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawLines();
    }
}

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Draws an abstract art of ten randomized lines.
 */
public class AbstractArtDrawing {

    // Constant variables that define the programs operation
    private static final int WIDTH = 800, HEIGHT = 600, NUM_OF_LINES = 10, CIRCLE_RADIUS = 3;
    /**
     * Creates a random line using the random integer generator function, that will be drawn in our frame.
     * @return a new line object with random values.
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        int xStart = rand.nextInt(WIDTH);
        int yStart = rand.nextInt(HEIGHT);
        int xEnd = rand.nextInt(WIDTH);
        int yEnd = rand.nextInt(HEIGHT);
        Line newLine = new Line(xStart, yStart, xEnd, yEnd);
        return newLine;
    }

    /**
     * Using the GUI and DrawSurface classes, we'll be drawing on a 800 by 600 pixel frame ten lines in random
     * locations and with random lengths. We'll mark the intersection points and the middle point of the lines.
     */
    public void drawLines() {
        GUI gui = new GUI("Random Lines", WIDTH, HEIGHT);
        DrawSurface surface = gui.getDrawSurface();
        surface.setColor(Color.BLACK);
        Line[] lineArray = new Line[NUM_OF_LINES];
        for (int i = 0; i < NUM_OF_LINES; i++) {
            Line randLine = generateRandomLine();
            lineArray[i] = randLine;
            int xStart = (int) randLine.start().getX();
            int yStart = (int) randLine.start().getY();
            int xEnd = (int) randLine.end().getX();
            int yEnd = (int) randLine.end().getY();
            surface.drawLine(xStart, yStart, xEnd, yEnd);
        }
        surface.setColor(Color.RED);
        for (int i = 0; i < NUM_OF_LINES - 1; i++) {
            for (int j = i + 1; j < NUM_OF_LINES; j++) {
                if (lineArray[i].isIntersecting(lineArray[j]) && lineArray[j].isIntersecting(lineArray[i])) {
                    Point intersect = lineArray[i].intersectionWith(lineArray[j]);
                    surface.fillCircle((int) intersect.getX(), (int) intersect.getY(), CIRCLE_RADIUS);
                }
            }
        }
        for (int k = 0; k < NUM_OF_LINES; k++) {
            int xMid = (int) lineArray[k].middle().getX();
            int yMid = (int) lineArray[k].middle().getY();
            surface.setColor(Color.BLUE);
            surface.fillCircle(xMid, yMid, CIRCLE_RADIUS);
        }
        gui.show(surface);
    }

    /**
     * The main function which creates a new AbstractArtDrawing object and then draws lines on it.
     * @param args not used.
     */
    public static void main(String[] args) {
        AbstractArtDrawing newArt = new AbstractArtDrawing();
        newArt.drawLines();
    }
}

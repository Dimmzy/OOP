import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

// Need to check edge cases (Balls too large and stuck in the corner)
// Try and Catch if given arguments aren't integers.

/**
 * A class correlating to a program that creates two frames in which half of the balls given as argument bounce in the
 * first frame and the other in the second frame.
 */
public class MultipleFramesBouncingBallsAnimation {

    private static final int LEFT_BOUND_GRAY = 50, RIGHT_BOUND_GRAY = 500, LEFT_BOUND_YELLOW = 450,
            RIGHT_BOUND_YELLOW = 600;
    /**
     * The main function that draws the graphics on screen and populates the ball array.
     * @param args radii of the balls we want to draw on screen.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments received, exiting...");
            return;
        }
            MultipleFramesBouncingBallsAnimation newGfx = new MultipleFramesBouncingBallsAnimation();
            newGfx.generateGraphics(args);
    }

    /**
     * Calls the createBall method to populate the ballls array an then creates the required objects for drawing the
     * bouncing balls and frames, finally drawing the animation.
     * @param args the array of the radii given as user input
     */
    public void generateGraphics(String[] args) {

        Ball[] ballsArray = new Ball[args.length];
        GUI gui = new GUI("Two Frames Bouncy Balls", 800, 600);
        Sleeper sleeper = new Sleeper();
        try {
            for (int i = 0; i < args.length; i++) {
                ballsArray[i] = createBall(i, args, i % 2);
            }
        } catch (Exception outOfBounds) {
            System.out.println("Caught Exception: " + outOfBounds.getMessage());
            gui.close();
            return;
        }
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            surface.setColor(Color.GRAY);
            surface.fillRectangle(50, 50, 450, 450);
            surface.setColor(Color.YELLOW);
            surface.fillRectangle(450, 450, 150, 150);
            for (int i = 0; i < ballsArray.length; i++) {
                ballsArray[i].moveOneStep();
                ballsArray[i].drawOn(surface);
            }
            gui.show(surface);
            sleeper.sleepFor(50);
        }
    }

    /**
     * Creates a ball object from the given parameters and returns it to the calling method.
     * @param index the index of the taken radius from the main arguments array.
     * @param args the string array created from user input.
     * @param rectangle which rectangle the ball belongs to, effects the balls bound area and starting location
     *                  (0 is the grey rectangle, otherwise it's the yellow one).
     * @throws Exception throws an exception if the given radius is out of the bounds of the rectangle
     * @return returns the ball object that was created.
     */

    public Ball createBall(int index, String[] args, int rectangle) throws Exception {
        Random rand = new Random();
        if (rectangle == 0) {
            int radius = Integer.parseInt(args[index]);
            if (radius <= 0 || radius >= 225) {
                throw new Exception("Given radius is larger than the rectangle or smaller than zero");
            }
            int centerX = rand.nextInt(450) - radius + 50;
            int centerY = rand.nextInt(450) - radius + 50;
            int angle = rand.nextInt(360);
            int speed;
            if (radius < 51) {
                speed = 51 / radius;
            } else {
                speed = 51;
            }
            Point center = new Point(centerX, centerY);
            Ball newBall = new Ball(center, radius, java.awt.Color.BLUE, LEFT_BOUND_GRAY, RIGHT_BOUND_GRAY,
                    LEFT_BOUND_GRAY, RIGHT_BOUND_GRAY);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            newBall.setVelocity(v);
            return newBall;
        } else {
            int radius = Integer.parseInt(args[index]);
            if (radius <= 0 || radius >= 75) {
                throw new Exception("Given radius is larger than the rectangle or smaller than zero");
            }
            int centerX = rand.nextInt(150) - radius + 450;
            int centerY = rand.nextInt(150) - radius + 450;
            int angle = rand.nextInt(360);
            int speed;
            if (radius < 51) {
                speed = 51 / radius;
            } else {
                speed = 51;
            }
            Point center = new Point(centerX, centerY);
            Ball newBall = new Ball(center, radius, java.awt.Color.GREEN, LEFT_BOUND_YELLOW, RIGHT_BOUND_YELLOW,
                    LEFT_BOUND_YELLOW, RIGHT_BOUND_YELLOW);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            newBall.setVelocity(v);
            return newBall;
        }
    }
}

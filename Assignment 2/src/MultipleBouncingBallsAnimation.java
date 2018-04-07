import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

// Need to check edge cases (Balls too large and stuck in the corner)
// Try and Catch if given arguments aren't integers.

/**
 * A class correlating to a program that draws multiple bouncing balls inside a single frame.
 */
public class MultipleBouncingBallsAnimation {

    // Constant variables that define the programs operation
    private static final int WIDTH = 800, HEIGHT = 600, ANGLE_BOUND = 360, SLEEP_TIMER = 50, SPEED_CONST = 51;
    /**
     * the main method that populates the ball arrays from given input and draws it on screen.
     * @param args the radii of the balls we'll draw, given as user input.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments received, exiting...");
            return;
        }
        Ball[] ballsArray = new Ball[args.length];
        GUI gui = new GUI("Many Bouncy Balls", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        try {
            for (int i = 0; i < args.length; i++) {
                ballsArray[i] = createBall(i, args);
                // If the createBall function failed to parseInt from the array, exits.
                if (ballsArray[i] == null) {
                    gui.close();
                    return;
                }
            }
        // "Deals" with the exception thrown when the ball created would've been out of bounds/
        } catch (Exception outOfBounds) {
            System.out.println("Caught Exception: " + outOfBounds.getMessage());
            gui.close();
            return;
        }
        // Creates a drawing surface and then loops through all the balls, draws them and then moves them
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            for (int i = 0; i < ballsArray.length; i++) {
                ballsArray[i].drawOn(surface);
                ballsArray[i].moveOneStep();
            }
            gui.show(surface);
            sleeper.sleepFor(SLEEP_TIMER);
        }
    }

    /**
     * Creates a ball object from the given parameters and returns it to the calling method.
     * @param index the index we'll extract the radius from the arguments array.
     * @param args the arguments array we're given from user input through main.
     * @throws Exception throws an exception if the given radius is out of the bounds of the rectangle.
     * @return returns the created ball object.
     */
    public static Ball createBall(int index, String[] args) throws Exception {
        Random rand = new Random();
        // Calculates the maximum radius of the ball to check with
        int maxRadius = (int) Math.sqrt(WIDTH * WIDTH + HEIGHT * HEIGHT);
        final int topBound = 0;
        int radius;
        // Tries to parse int for the arguments, if it's not an int throws exception and returns null
        try {
            radius = Integer.parseInt(args[index]);
        } catch (Exception e) {
            System.out.println("Found non-integer number, exiting!");
            return null;
        }
        // Checks if our radius isn't too large for the frame
        if (radius <= 0 || radius >= maxRadius) {
            throw new Exception("Given radius is larger than possible in the rectangle or smaller than zero");
        }
        int centerX = rand.nextInt(WIDTH - HEIGHT) - radius;
        int centerY = rand.nextInt(WIDTH - HEIGHT) - radius;
        int angle = rand.nextInt(ANGLE_BOUND);
        int speed;
        // We'll use 51 as a reference constant that we'll derive the balls speed by size from
        if (radius < SPEED_CONST) {
            speed = SPEED_CONST / radius;
        } else {
            speed = SPEED_CONST;
        }
        Point center = new Point(centerX, centerY);
        Ball newBall =  new Ball(center, radius, java.awt.Color.BLUE, topBound, WIDTH, topBound, HEIGHT);
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        newBall.setVelocity(v);
        return newBall;
    }
}

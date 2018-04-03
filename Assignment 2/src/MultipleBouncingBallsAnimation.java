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
        int width = 800;
        int height = 600;
        GUI gui = new GUI("Many Bouncy Balls", width, height);
        Sleeper sleeper = new Sleeper();
        try {
            for (int i = 0; i < args.length; i++) {
                ballsArray[i] = createBall(i, args, 800, 600);
            }
        } catch (Exception outOfBounds) {
            System.out.println("Caught Exception: " + outOfBounds.getMessage());
            gui.close();
            return;
        }
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
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
     * @param index the index we'll extract the radius from the arguments array.
     * @param args the arguments array we're given from user input through main.
     * @param width the width of the drawn frame.
     * @param height the height of the drawn frame.
     * @throws Exception throws an exception if the given radius is out of the bounds of the rectangle.
     * @return returns the created ball object.
     */
    public static Ball createBall(int index, String[] args, int width, int height) throws Exception {
        Random rand = new Random();
        int maxRadius = (int) Math.sqrt(width * width + height * height);
        final int topBound = 0;
        int radius = Integer.parseInt(args[index]);
        if (radius <= 0 || radius >= maxRadius) {
            throw new Exception("Given radius is larger than the rectangle or smaller than zero");
        }
        int centerX = rand.nextInt(200) - radius;
        int centerY = rand.nextInt(200) - radius;
        int angle = rand.nextInt(360);
        int speed;
        if (radius < 51) {
            speed = 51 / radius;
        } else {
            speed = 51;
        }
        Point center = new Point(centerX, centerY);
        Ball newBall =  new Ball(center, radius, java.awt.Color.BLUE, topBound, width, topBound, height);
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        newBall.setVelocity(v);
        return newBall;
    }
}

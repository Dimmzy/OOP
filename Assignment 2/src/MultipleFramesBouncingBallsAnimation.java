import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;


/**
 * A class correlating to a program that creates two frames in which half of the balls given as argument bounce in the
 * first frame and the other in the second frame.
 */
public class MultipleFramesBouncingBallsAnimation {

    // Constant variables that define the programs operation
    private static final int LEFT_BOUND_GRAY = 50, RIGHT_BOUND_GRAY = 500, LEFT_BOUND_YELLOW = 450,
            RIGHT_BOUND_YELLOW = 600, SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600, ANGLE_BOUND = 360, SLEEP_TIMER = 50,
            SPEED_CONST = 51;
    /**
     * The main function that draws the graphics on screen and populates the ball array.
     * @param args radii of the balls we want to draw on screen.
     */
    public static void main(String[] args) {
        // Checks we actually have any balls to draw
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
        GUI gui = new GUI("Two Frames Bouncy Balls", SCREEN_WIDTH, SCREEN_HEIGHT);
        Sleeper sleeper = new Sleeper();
        // We'll use try/catch to find any issues with the parameters given.
        try {
            for (int i = 0; i < args.length; i++) {
                ballsArray[i] = createBall(i, args, i % 2);
                // ballsArray[i] will be null if createBall found that the argument is not an integer.
                if (ballsArray[i] == null) {
                    gui.close();
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
            gui.close();
            return;
        }
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            surface.setColor(Color.GRAY);
            surface.fillRectangle(LEFT_BOUND_GRAY, LEFT_BOUND_GRAY, RIGHT_BOUND_GRAY - LEFT_BOUND_GRAY,
                    RIGHT_BOUND_GRAY - LEFT_BOUND_GRAY);
            surface.setColor(Color.YELLOW);
            surface.fillRectangle(LEFT_BOUND_YELLOW, LEFT_BOUND_YELLOW, RIGHT_BOUND_YELLOW - LEFT_BOUND_YELLOW,
                    RIGHT_BOUND_YELLOW - LEFT_BOUND_YELLOW);
            for (int i = 0; i < ballsArray.length; i++) {
                ballsArray[i].moveOneStep();
                ballsArray[i].drawOn(surface);
            }
            gui.show(surface);
            sleeper.sleepFor(SLEEP_TIMER);
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
        int radius;
        if (rectangle == 0) {
            try {
                radius = Integer.parseInt(args[index]);
            } catch (Exception e) {
                System.out.println("Found non-integer number, exiting!");
                return null;
            }
            // Checks if the ball's radius is negative/zero or larger than the frame times 2
            if (radius <= 0 || radius >= (RIGHT_BOUND_GRAY - LEFT_BOUND_GRAY) / 2) {
                throw new Exception("Given radius is larger than possible in the rectangle or smaller than zero");
            }
            // Sets the center of the ball to be a value inside of the frame
            int centerX = rand.nextInt(RIGHT_BOUND_GRAY - LEFT_BOUND_GRAY) - radius + LEFT_BOUND_GRAY;
            int centerY = rand.nextInt(RIGHT_BOUND_GRAY - LEFT_BOUND_GRAY) - radius + LEFT_BOUND_GRAY;
            int angle = rand.nextInt(ANGLE_BOUND);
            int speed;
            // We'll use 51 as a reference constant that we'll derive the balls speed by size from
            if (radius < SPEED_CONST) {
                speed = SPEED_CONST / radius;
            } else {
                speed = SPEED_CONST;
            }
            Point center = new Point(centerX, centerY);
            Ball newBall = new Ball(center, radius, java.awt.Color.BLUE, LEFT_BOUND_GRAY, RIGHT_BOUND_GRAY,
                    LEFT_BOUND_GRAY, RIGHT_BOUND_GRAY);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            newBall.setVelocity(v);
            return newBall;
        } else {
            try {
                radius = Integer.parseInt(args[index]);
            } catch (Exception e) {
                System.out.println("Found non-integer number, exiting!");
                return null;
            }
            if (radius <= 0 || radius >= (RIGHT_BOUND_YELLOW - LEFT_BOUND_YELLOW) / 2) {
                throw new Exception("Given radius is larger than possible in the rectangle or smaller than zero");
            }
            int centerX = rand.nextInt(RIGHT_BOUND_YELLOW - LEFT_BOUND_YELLOW) - radius + LEFT_BOUND_YELLOW;
            int centerY = rand.nextInt(RIGHT_BOUND_YELLOW - LEFT_BOUND_YELLOW) - radius + LEFT_BOUND_YELLOW;
            int angle = rand.nextInt(ANGLE_BOUND);
            int speed;
            if (radius < SPEED_CONST) {
                speed = SPEED_CONST / radius;
            } else {
                speed = SPEED_CONST;
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

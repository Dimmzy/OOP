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

    /**
     * The main function that draws the graphics on screen and populates the ball array.
     * @param args radii of the balls we want to draw on screen.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments received, exiting...");
            return;
        }
        Ball[] ballsArray = new Ball[args.length];
        GUI gui = new GUI("Two Frames Bouncy Balls", 800, 600);
        Sleeper sleeper = new Sleeper();
        for (int i = 0; i < args.length; i++) {
            ballsArray[i] = createBall(i, args, i % 2);
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
     * @return returns the ball object that was created.
     */

    public static Ball createBall(int index, String[] args, int rectangle) {
        Random rand = new Random();
        if (rectangle == 0) {
            int radius = Integer.parseInt(args[index]);
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
            Ball newBall = new Ball(center, radius, java.awt.Color.BLUE);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            newBall.setVelocity(v);
            newBall.setBounds(50, 500, 50, 500);
            return newBall;
        } else {
            int radius = Integer.parseInt(args[index]);
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
            Ball newBall = new Ball(center, radius, java.awt.Color.GREEN);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            newBall.setVelocity(v);
            newBall.setBounds(450, 600, 450, 600);
            return newBall;
        }
    }
}

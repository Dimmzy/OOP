import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

// Need to check edge cases (Balls too large and stuck in the corner)

public class MultipleBouncingBallsAnimation {
    public static void main(String[] args) {
        Ball[] ballsArray = new Ball[args.length];
        Random rand = new Random();
        int width = 200;
        int height = 200;
        GUI gui = new GUI("Many Bouncy Balls", width, height);
        Sleeper sleeper = new Sleeper();
        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);
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
            ballsArray[i] = new Ball(center, radius, java.awt.Color.PINK);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ballsArray[i].setVelocity(v);
            ballsArray[i].setSize(width, height);
        }
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            for (int i = 0; i < ballsArray.length; i++) {
                ballsArray[i].moveOneStep();
                ballsArray[i].drawOn(surface);
            }
            gui.show(surface);
            sleeper.sleepFor(5);
        }
    }
}

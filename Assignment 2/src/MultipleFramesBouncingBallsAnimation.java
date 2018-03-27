import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

// Need to check edge cases (Balls too large and stuck in the corner)

public class MultipleFramesBouncingBallsAnimation {
    public static void main(String[] args) {
        Random rand = new Random();
        Sleeper sleeper = new Sleeper();
        Ball[] grayBalls = new Ball[args.length / 2];
        if (args.length % 2 != 0) {
            Ball[] yellowBalls = new Ball[args.length / 2 - 1];
        }
        Ball[] yellowBalls = new Ball[args.length / 2];
        int graySizeW = rand.nextInt(451) + 50;
        int graySizeH = rand.nextInt(451) + 50;
        int yellowSizeW = rand.nextInt(151) + 450;
        int yellowSizeH = rand.nextInt(151) + 450;
        GUI guiG = new GUI("Grey Frame", graySizeW, graySizeH);
        GUI guiY = new GUI("Yellow Frame", yellowSizeW, yellowSizeH);
        for (int i = 0; i < grayBalls.length; i++) {
            int radius = Integer.parseInt(args[i]);
            int centerX = rand.nextInt(200) - radius;
            int centerY = rand.nextInt(200) - radius;
            int angle = rand.nextInt(360);
            int speed;
            if (radius < 51) {
                speed = 51 / radius;
            } else {
                speed = 1;
            }
            Point center = new Point(centerX, centerY);
            grayBalls[i] = new Ball(center, radius, java.awt.Color.BLACK);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            grayBalls[i].setVelocity(v);
            grayBalls[i].setSize(graySizeW, graySizeH);
        }
        for (int i = 0; i < yellowBalls.length; i++) {
            int radius = Integer.parseInt(args[args.length / 2 + i]);
            int centerX = rand.nextInt(200) - radius;
            int centerY = rand.nextInt(200) - radius;
            int angle = rand.nextInt(360);
            double speed;
            if (radius < 51) {
                speed = 51 / radius;
            } else {
                speed = 1;
            }
            Point center = new Point(centerX, centerY);
            yellowBalls[i] = new Ball(center, radius, java.awt.Color.BLACK);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            yellowBalls[i].setVelocity(v);
            yellowBalls[i].setSize(yellowSizeW, yellowSizeH);
        }
        while (true) {
            DrawSurface surfaceG = guiG.getDrawSurface();
            surfaceG.setColor(Color.GRAY);
            surfaceG.fillRectangle(0, 0, graySizeW, graySizeH);
            for (int i = 0; i < grayBalls.length; i++) {
                grayBalls[i].moveOneStep();
                grayBalls[i].drawOn(surfaceG);
            }
            guiG.show(surfaceG);
            DrawSurface surfaceY = guiY.getDrawSurface();
            surfaceY.setColor(Color.YELLOW);
            surfaceY.fillRectangle(0,0, yellowSizeW, yellowSizeH);
            for (int i = 0; i < yellowBalls.length; i++) {
                yellowBalls[i].moveOneStep();
                yellowBalls[i].drawOn(surfaceY);
            }
            guiY.show(surfaceY);
            sleeper.sleepFor(5);
        }
    }

}

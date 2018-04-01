import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

// Check if no velocity is given?

public class BouncingBallAnimation {
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncy Ball",200,200);
        Sleeper sleeper = new Sleeper();
        Point center = new Point(122,88);
        Ball ball = new Ball(center, 30, java.awt.Color.BLACK);
        Velocity v = Velocity.fromAngleAndSpeed(48.21,5);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
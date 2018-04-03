import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;


/**
 * Creates a frame and draws a bouncing ball inside it.
 */
public class BouncingBallAnimation {

    /**
     * The function creates a frame of 200x200 pixels and inside it creates a ball and sets it to move and bounce
     * inside the frame.
     * @param args not used.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncy Ball", 200, 200);
        Sleeper sleeper = new Sleeper();
        Point center = new Point(0, 0);
        final int topBound = 0;
        final int bottomBound = 200;
        Ball ball = new Ball(center, 30, java.awt.Color.BLACK, topBound, bottomBound, topBound, bottomBound);
        Velocity v = Velocity.fromAngleAndSpeed(48.21, 5);
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
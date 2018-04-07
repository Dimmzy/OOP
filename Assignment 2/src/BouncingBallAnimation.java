import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * Creates a frame and draws a bouncing ball inside it.
 */
public class BouncingBallAnimation {

    // Defines START/MAX Position which denote the minimal and maximal values of the x and y values (size of the screen)
    private static final int SLEEP_TIMER = 50, START_POSITION = 0, MAX_POSITION = 200;
    /**
     * The function creates a frame of 200x200 pixels and inside it creates a ball and sets it to move and bounce
     * inside the frame.
     * @param args not used.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncy Ball", MAX_POSITION, MAX_POSITION);
        Sleeper sleeper = new Sleeper();
        Point center = new Point(0, 0);
        // Creates a new ball object with a radius of 30
        Ball ball = new Ball(center, 30, java.awt.Color.BLACK, MAX_POSITION, START_POSITION, MAX_POSITION,
                START_POSITION);
        // Sets arbitrary speed and angle to the ball.
        Velocity v = Velocity.fromAngleAndSpeed(48.21, 5);
        ball.setVelocity(v);
        // Creates a drawing surface and draws teh ball on it, then waits 50ms before drawing the next frame
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(SLEEP_TIMER);
        }
    }
}
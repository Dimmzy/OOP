/**
 * Velocity class. Holds values that dictate the movement speed of an object in our game.
 */
public class Velocity {

    // dx and dy start at 0 unless otherwise specified (no movement).
    private double dx = 0;
    private double dy = 0;

    /**
     * Velocity constructor.
     * @param dx delta x of our movement (move x pixels horizontally in each frame).
     * @param dy delta y of the point (move y pixels vertically in each frame).
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Converts a passed on angle to radians and calculates the velocity using the passed speed and some
     * nifty trigonometry.
     * @param angle the angle of the object's movement.
     * @param speed the speed of the object's movement.
     * @return returns a new velocity object after converting the angle and speed to dx dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Check if angle is zero
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        // Multiplies dy by -1 because we're dealing with inverted axis
        double dy = Math.cos(Math.toRadians(angle)) * speed * -1;
        return new Velocity(dx, dy);
    }

    /**
     * Displaces the object from it's starting location to the location after the movement using the velocity values.
     * @param p The object's starting location.
     * @return Returns the final location of the object (after applying the velocity deltas).
     */
    public Point applyToPoint(Point p) {
        Point newPos = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPos;
    }

    /**
     * @return Returns the current delta x of the point.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return Returns the current delta y of the point.
     */
    public double getDy() {
        return this.dy;
    }
}
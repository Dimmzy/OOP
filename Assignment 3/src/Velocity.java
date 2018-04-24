/**
 * Calculates and displaces the given point object.
 */
public class Velocity {

    // dx and dy start at 0 unless otherwise specified (no movement)
    private double dx = 0;
    private double dy = 0;

    /**
     * Velocity constructor.
     * @param dx delta x of the point.
     * @param dy delta y of the point.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Converts a given angle to radians and calculates the velocity using given speed and trigonometry.
     * @param angle the angle of movement.
     * @param speed the speed of the movement.
     * @return returns a new velocity object after converting the angle and speed to dx dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Check if angle is zero
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        // Multiplies dy by -1 because we're dealing with inverted axis
        double dy = Math.cos(Math.toRadians(angle)) * speed * -1;
        return new Velocity(dx, dy);
    }

    /**
     * Displaces the point from one location to another.
     * @param p the new location.
     * @return returns the new position of the point (a new point object).
     */
    public Point applyToPoint(Point p) {
        Point newPos = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPos;
    }

    /**
     * @return returns the current delta x of the point.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return returns the current delta y of the point.
     */
    public double getDy() {
        return this.dy;
    }
}

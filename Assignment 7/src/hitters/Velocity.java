package hitters;

import geometry.Point;

/**
 * Velocity class. Holds values that dictate the movement speed of an object in our game.
 */
public class Velocity {

    private double dx;
    private double dy;

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
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        // Multiplies dy by -1 because we're dealing with inverted axis
        double dy = Math.cos(Math.toRadians(angle)) * speed * -1;
        return new Velocity(dx, dy);
    }

    /**
     * Displaces the object from it's starting location to the location after the movement using the velocity values.
     * @param p The object's starting location.
     * @return Returns the final location of the object (after applying the velocity deltas).
     * @param dt normalize's the balls speed to be pixels per seconds rather than frames per second.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
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

    /**
     * @return returns the current speed of the object.
     */
    public double getSpeed() { return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2)); }
}
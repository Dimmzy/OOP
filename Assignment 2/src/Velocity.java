/**
 *
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     *
     * @param dx
     * @param dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRadians = Math.toRadians(angle+ 90);
        double dx = Math.cos(angleRadians) * speed;
        double dy = Math.sin(angleRadians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     *
     * @param p
     * @return
     */
    public Point applyToPoint(Point p) {
        Point newPos = new Point(p.getX()+ this.dx, p.getY()+ this.dy);
        return newPos;
    }

    public double getDx() {
        return this.dx;
    }

    public double getDy() {
        return this.dy;
    }
}

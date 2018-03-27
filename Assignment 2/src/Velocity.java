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

    /**
     *
     * @param p
     * @return
     */
    public Point applyToPoint(Point p) {
        Point newPos = new Point(p.getX()+ this.dx, p.getY()+ this.dy);
        return newPos;
    }
}

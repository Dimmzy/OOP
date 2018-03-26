public class Velocity {

    private double dx;
    private double dy;

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Point applyToPoint(Point p) {
        Point newPos = new Point(p.getX()+this.dx, p.getY()+this.dy);
        return newPos;
    }
}

/**
 * The Line Class.
 */
public class Line {

    private Point start, end;

    /**
     * EZ line constructor
     * @param start the starting point of the line
     * @param end the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Shittier line constructor
     * @param x1 x value of the start
     * @param y1 y value of the start
     * @param x2 x value of the end
     * @param y2 y value of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return returns the length of the line by using the distance method of
     * the point object.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Calculates the middle X and Y points and then creates a Point object
     * out of the results
     * @return returns the middle point between the start and end of the object
     */
    public Point middle() {
        double midX = (end.getX() - start.getX()) / 2;
        double midY = (end.getY() - end.getY()) / 2;
        Point middle = new Point(midX, midY);
        return middle;
    }

    /**
     * @return returns the starting point
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return returns the ending point
     */
    public Point end() {
        return this.end;
    }

    /**
     *
     * @param other line we check if the current object intersects with
     * @return
     */
    public boolean isIntersecting(Line other) {
        return this.findSlope() == other.findSlope();
    }

    /**
     *
     * @param other
     * @return
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other) == false) {
            return null;
        }

    }

    /**
     *
     * @param
     * @return
     */
    public double findSlope() {
        double xValue = (end.getX() - start.getX());
        double yValue = (end.getY() - start.getY());
        return yValue / xValue;
    }
}

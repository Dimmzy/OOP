/**
 * The Line class consists of four variables:
 * Start and End Point objects of the line.
 * The slope of the line (calculated using deltaY/deltaX).
 * The free value of the line's.
 * The class has methods to calculate the intersection of two lines, the
 * length of the line and to find the middle point of the line.
 */

// Need to add edge cases for the intersection (vertical/horizontal lines)
// Need to fix comments

public class Line {

    private Point start, end;

    /**
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
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
     * using the result.
     *
     * @return returns the middle point between the start and end of the object
     */
    public Point middle() {
        double midX = (end.getX() + start.getX()) / 2;
        double midY = (end.getY() + start.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return returns the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return returns the ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The method checks whether this line and the one specified intersect.
     * It checks for slope equality and then if the intersection point is in
     * the range of the lines.
     *
     * @param other line we check if the current object intersects with.
     * @return a boolean expression that states whether the lines intersect
     * by comparing their slopes.
     */
    public boolean isIntersecting(Line other) {
        double xSect = this.xIntersect(other);
        double ySect = this.yIntersect(xSect);
        if (this.getSlope() == other.getSlope()) {
            return false;
        }
        else if (this.getSlope() > 0) {
            if (this.start.getX() > this.end.getX()) {
                return this.start.getX() >= xSect && this.end.getX() <= xSect
                        || this.start.getY() >= ySect && this.end.getY() <= ySect;
            }
            else {
                return this.start.getX() <= xSect && this.end.getX() >= xSect
                        || this.start.getY() <= ySect && this.end.getY() >= ySect;
            }
        }
        else if (this.getSlope() < 0) {
            if (this.start.getX() > this.end.getX()) {
                return this.start.getX() <= xSect && this.end.getX() >= xSect
                        || this.start.getY() <= ySect && this.end.getY() >= ySect;
            }
            else {
                return this.start.getX() >= xSect && this.end.getX() <= xSect
                        || this.start.getY() >= ySect && this.end.getY() <= ySect;
            }
        }
        else {
            return this.start.getX() <= xSect && xSect <= this.end.getX();
        }
    }

    /**
     * The method makes use of the isIntersecting method to see whether the
     * lines intersect in the interval, if they do it creates a new point
     * object with the intersection values and returns it.
     *
     * @param other The line we check the intersection point with.
     * @return returns a new object, the intersection point.
     */
    public Point intersectionWith(Line other) {
        // If the lines don't intersect returns null
        if (!this.isIntersecting(other)) {
            return null;
        }
        // Otherwise creates and returns the intersection point
        return new Point(this.xIntersect(other),
                this.yIntersect(this.xIntersect(other)));
    }


    /**
     * Calculates the x value of the intersection point between two lines
     * using the delta of the free values divided by the -delta of the slopes.
     * @param other the other line we calculate the intersection with.
     * @return returns the x value of the intersection point.
     */
    public double xIntersect(Line other) {
        double deltaX = this.getSlope() - other.getSlope();
        double deltaFree = other.getFreeVal() - this.getFreeVal();
        return deltaFree / deltaX;
    }

    /**
     * Calculates the y value of the intersection point using the linear line
     * equation and the x value we calculated previously.
     * (slope * (x_intersection - x_1) + y_1)
     *
     * @param xIntersect uses the x value of the intersection point to
     *                   calculate the y value.
     * @return returns the y value of the intersection point.
     */
    public double yIntersect(double xIntersect) {
        return (this.getSlope() * xIntersect) + this.getFreeVal();
    }


    /**
     * @return returns the slope of the current line
     */

    public double getSlope() {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * @return returns the free value of the current line
     */
    public double getFreeVal() {
        return (this.getSlope() * -(start.getX())) + start.getY();
    }
}
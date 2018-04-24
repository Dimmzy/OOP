import java.util.List;

/**
 * The line class holds the values for our linear line segments, holding both the start point and the end point of
 * the line segments.
 */


public class Line {

    private Point start, end;

    /**
     * @param start the starting point of the line.
     * @param end  the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1 x value of the starting point.
     * @param y1 y value of the starting poin.t
     * @param x2 x value of the ending point.
     * @param y2 y value of the ending point.
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

    public void changeStart(Point newStart) { this.start = newStart; }

    public void changeEnd(Point newEnd) {
        this.end = newEnd;
    }

    /**
     * Calculates the cross product between two lines and returns the orientation of the triplet of points
     * @param start starting point.
     * @param end ending point.
     * @param orient orientation point.
     * @return 0 if all three points are co-linear, 1 if the orientation is clockwise and 2 if it's counterclockwise
     */
    public int calcXProduct(Point start, Point end, Point orient) {
        double xProduct = ((end.getY() - start.getY()) * ((orient.getX() - end.getX())) -
                (end.getX() - start.getX()) * (orient.getY() - end.getY()));
        if (xProduct == 0) {
            return 0;
        }
        else if (xProduct > 0) {
            return 1;
        }
        else {
            return 2;
        }
    }


    /**
     * Checks if the point that was passed is on the current line segment
     * @param point the point to check
     * @return true if the point is on the line, false otherwise.
     */
    public boolean hasPoint(Point point) {
        return Math.floor(this.start().distance(point)) +  Math.floor(point.distance(this.end())) == Math.floor(this
                .start().distance(this.end));
    }

    /**
     * Calculates if this line segment intersects with the passed line segment using the cross product and line
     * orientation method.
     * @param other line we check if the current line segment intersects with.
     * @return a boolean expression that states whether the lines intersect.
     */
    public boolean isIntersecting(Line other) {

        // Calculates the orientations
        int orientationOne = calcXProduct(this.start, this.end, other.start);
        int orientationTwo = calcXProduct(this.start, this.end, other.end);
        int orientationThree = calcXProduct(other.start, other.end, this.start);
        int orientationFour = calcXProduct(other.start, other.end, this.end);

        if (orientationOne != orientationTwo && orientationThree != orientationFour) {
            return true;
        }

        else {
            return false;
        }
    }

    /**
     * Checks if the lines intersect and then returns their intersection point (checking the special case of
     * horizontal/vertical lines).
     * @param other The line we check the intersection point with.
     * @return Returns the intersection pont. If the lines don't intersect, returns null.
     */
    public Point intersectionWith(Line other) {
        // If the lines don't intersect returns null
        if (!this.isIntersecting(other)) {
            return null;
        }
        // Otherwise creates and returns the intersection point
        Point intersectPoint = calcIntersect(other);
        // Checks the special case that the lines are vertical or horizontal
        if (intersectPoint == null) {
            // In our case infinite slope is passed as zero and checked here. (Vertical)
            if (this.start().getX() == this.end.getX()) {
                Point insect =  new Point(this.start.getX(), other.start().getY());
                return insect;
            }
            else {
                Point insect = new Point(other.start.getX(), this.start().getY());
                return insect;
            }
        }
        return intersectPoint;
    }


    /**
     * Calculates the intersection point by using the deltas.
     * @param other the other line we calculate the intersection with.
     * @return returns the x value of the
     */
    public Point calcIntersect(Line other) {
        double x1 = this.start().getX();
        double y1 = this.start().getY();
        double x2 = this.end().getX();
        double y2 = this.end().getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();
        double numeratorX = (x2 * y1 - x1 * y2) * (x4 - x3) - (x4 * y3 - x3 * y4) * (x2 - x1);
        double numeratorY =  (x2 * y1 - x1 * y2) * (y4 - y3) - (x4 * y3 - x3 * y4) * (y2 - y1);
        double denominator = (x2 - x1) * (y4 - y3) - (x4 - x3) * (y2 - y1);
        if (denominator == 0) {
            return null;
        }
        return new Point(numeratorX / denominator, numeratorY / denominator);
    }


    /**
     *
     * @param rect
     * @return
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestIntersect = intersectionPoints.get(0);
        for (int i = 0; i < intersectionPoints.size(); i++) {
            if (closestIntersect.distance(this.end) > intersectionPoints.get(i).distance(this.end)) {
                closestIntersect = intersectionPoints.get(i);
            }
        }
        Point hitPoint = new Point(Math.ceil(closestIntersect.getX()), Math.ceil(closestIntersect.getY()));
        return hitPoint;
    }
}
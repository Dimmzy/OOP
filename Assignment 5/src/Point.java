/**
 * The point class.
 * Holds two variables: the X value of the point and the Y value of the point
 * Has methods to calculate the distance between two points and check whether two points are equal.
 */

public class Point {

    private double x, y;

    /**
     * Point Object Constructor.
     * @param x the 'x' value of the point
     * @param y the 'y' value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and the passed one.
     * @param other passed point to calculate distance to.
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Checks the output from the distance method and returns whether the points are equal or not.
     * @param other the point to compare this one to.
     * @return true if the points are equal, false if they aren't
     */
    public boolean equals(Point other) {
        return this.distance(other) == 0;
    }

    /**
     * @return returns the x value of this object
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return returns the y value of this object
     */
    public double getY() {
        return this.y;
    }

    /**
     * @return returns a string that lets us print the x and y values of this point
     */
    public String toString() {
        return "X Value: " + this.getX() + " " + "Y Value: " + this.getY();
    }
}

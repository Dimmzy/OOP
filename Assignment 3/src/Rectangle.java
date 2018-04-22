import java.util.ArrayList;
import java.util.List;


/**
 * Rectangle Class.
 */
public class Rectangle {

    private Point upperLeft;
    private Line left, right, top, bottom;
    private double width;
    private double height;

    /**
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.left = new Line(bottomLeft, upperLeft);
        this.right = new Line(bottomRight, upperRight);
        this.top = new Line(upperLeft, upperRight);
        this.bottom = new Line(bottomLeft, bottomRight);
        this.width = width;
        this.height = height;
    }

    /**
     * The method creates the rectangles borders by creating lines from each edge, then checks intersection with the
     * line that is given as a parameter.
     * @param line the line we check if any of the rectangles borders intersect with
     * @return returns the list of borders the line intersects with
     */
    public java.util.List intersectionPoints(Line line) {
        Line[] rectEdges = new Line[4];
        List<Point> intersectList = new ArrayList();
        // Defines the rectangle edge's by creating line objects for each edge
        rectEdges[0] = this.left;
        rectEdges[1] = this.right;
        rectEdges[2] = this.top;
        rectEdges[3] = this.bottom;
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(rectEdges[i])){
                Point intersectPoint = line.intersectionWith(rectEdges[i]);
                intersectList.add(intersectPoint);
            }
        }
        return intersectList;
    }


    /**
     * @return returns the width of the current rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return returns the height of the current rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return returns the upper left corner point of the current rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Moves the rectangle to it's new spot.
     * @param newLoc the new location of the upper left point.
     */
    public void setNewLocation(Point newLoc) {
        this.upperLeft = newLoc;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.left.changeStart(upperRight);
        this.left.changeEnd(bottomRight);
        this.right.changeStart(upperRight);
        this.right.changeEnd(bottomRight);
        this.top.changeStart(upperLeft);
        this.top.changeEnd(upperRight);
        this.bottom.changeStart(bottomLeft);
        this.bottom.changeEnd(bottomRight);
    }
    /**
     * checks on which side of the rectangle the passed point is located.
     * @param point the point to be checked
     * @return returns the enumerated Border on which the point resides
     */
    public Border pointLocation(Point point) {
        System.out.println(point.toString());
        if (this.left.hasPoint(point)) {
            System.out.println("Hit Right");
            return Border.RIGHT;
        }
        else if (this.right.hasPoint(point)) {
            System.out.println("Hit Left");
            return Border.LEFT;
        }
        else if (this.top.hasPoint(point)) {
            System.out.println("Hit Top");
            return Border.TOP;
        }
        else if (this.bottom.hasPoint(point)) {
            System.out.println("Hit Bottom");
            return Border.BOTTOM;
        }
        // should never get here, make an exception
        else {
            System.out.println("Hit null");
            return null;
        }
    }
}

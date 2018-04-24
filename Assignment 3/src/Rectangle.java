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
     * Constructs a rectangle from given upperLeft point, width and height.
     * @param upperLeft the Upper Left corner point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
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
     * @param line The line we check if any of the rectangles borders intersect with
     * @return Returns the list of borders the line intersects with
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
     * Moves the rectangle to a newe location by editing it's fields.
     * @param newLoc the new location of the upper left point.
     */
    public void setNewLocation(Point newLoc) {
        this.upperLeft = newLoc;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.left.changeStart(upperLeft);
        this.left.changeEnd(bottomLeft);
        this.right.changeStart(upperRight);
        this.right.changeEnd(bottomRight);
        this.top.changeStart(upperLeft);
        this.top.changeEnd(upperRight);
        this.bottom.changeStart(bottomLeft);
        this.bottom.changeEnd(bottomRight);
    }
    /**
     * checks on which side of the rectangle the passed point is located.
     * @param point the point to be checked.
     * @return Returns the enumerated Border on which the point resides.
     */
    public Border pointLocation(Point point) throws Exception {
        if (this.left.hasPoint(point) && this.top.hasPoint(point)) {
            System.out.println("TOP LEFT");
            return Border.TLEFT;
        }
        else if (this.left.hasPoint(point) && this.bottom.hasPoint(point)) {
            System.out.println("BOTTOM LEFT");
            return Border.BLEFT;
        }
        else if (this.right.hasPoint(point) && this.top.hasPoint(point)) {
            System.out.println("TOP RIGHT");
            return Border.TRIGHT;
        }
        else if (this.right.hasPoint(point) && this.bottom.hasPoint(point)) {
            System.out.println("BOTTOM RIGHT");
            return Border.BRIGHT;
        }
        else if (this.left.hasPoint(point)) {
            System.out.println("LEFT");
            return Border.LEFT;
        }
        else if (this.right.hasPoint(point)) {
            System.out.println("RIGHT");
            return Border.RIGHT;
        }
        else if (this.top.hasPoint(point)) {
            System.out.println("TOP");
            return Border.TOP;
        }
        else if (this.bottom.hasPoint(point)) {
            System.out.println("BOTTOM");
            return Border.BOTTOM;
        }
        // Intersection point is not on any border (shouldn't occur, throws exception incase it does)
        else {
            throw new Exception("Hit location unknown");
        }
    }
}

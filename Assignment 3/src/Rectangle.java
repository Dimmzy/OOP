import java.util.ArrayList;
import java.util.List;


/**
 * Rectangle Class
 */
public class Rectangle {

    private Point upperLeft, upperRight, bottomLeft, bottomRight;
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
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() - height);
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() - height);
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @param line
     * @return
     */
    public java.util.List intersectionPoints(Line line) {
        Line[] rectEdges = new Line[4];
        List intersectList = new ArrayList();
        // Defines the rectangle edge's by creating line objects for each edge
        rectEdges[0] = new Line(upperLeft, bottomLeft);
        rectEdges[1] = new Line(upperRight, bottomRight);
        rectEdges[2] = new Line(upperLeft, upperRight);
        rectEdges[3] = new Line(bottomLeft, bottomRight);
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(rectEdges[i])){
                intersectList.add(line.intersectionWith(rectEdges[i]));
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
}

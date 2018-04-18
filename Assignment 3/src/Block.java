import biuoop.DrawSurface;
import java.awt.Color;
/**
 * Block method that consists of rectangles that can be collided with.
 */
public class Block implements Collidable {

    private Rectangle rectangle;

    /**
     * Constructs a new block from a passed rectangle.
     * @param rect the rectangle we'll create the block from.
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * Constructs a new block by creating a rectangle using given parameters.
     * @param upperLeft upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Block(Point upperLeft, double width, double height) {
        Rectangle rect = new Rectangle(upperLeft, width, height);
        this.rectangle = rect;
    }

    /**
     * Returns the current rectangle to the calling method.
     * @return
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * "hits" the block with given velocity in a given point.
     * @param collisionPoint the collision point of object with the block.
     * @param currentVelocity the velocity the object hit the block at.
     * @return returns to the calling object it's velocity after the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double deltaX = collisionPoint.getX() - currentVelocity.getDx();
        double deltaY = collisionPoint.getY() - currentVelocity.getDy();
        if (deltaX < 0 && deltaY < 0) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        else if (deltaX < 0 && deltaY > 0) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        else if (deltaX > 0 && deltaY < 0) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        else if (deltaX > 0 && deltaY > 0) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // Not needed?
        return null;
    }

    /**
     * draws the rectangle that defines this block on the provided surface.
     * @param surface the surface the rectangle will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLUE);
        Point topLeft = this.rectangle.getUpperLeft();
        surface.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                .rectangle.getHeight());
    }
}

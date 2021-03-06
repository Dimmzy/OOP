import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Block method that consists of rectangles that can be collided with.
 */
public class Block implements Collidable, Sprite {

    private Rectangle rectangle;
    private java.awt.Color color;
    private int hp;

    /**
     * Constructs a new block from a passed rectangle.
     * @param rect the rectangle we'll create the block from.
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * Constructs a block with a given color and health.
     * @param upperLeft upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color the color of the block.
     * @param hp the starting hit points of the block.
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color, int hp) {
        Rectangle rect = new Rectangle(upperLeft, width, height);
        this.rectangle = rect;
        this.color =  color;
        this.hp = hp;
    }

    /**
     * Adds the block to the game (as both a sprite and a collidable object).
     * @param game the game to add the block to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * @return Returns the current rectangle object to the calling method.
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
        if (this.hp > 0) {
            this.hp = this.hp - 1;
        }
        // Calls pointLocaton to return the enum of the side the the rectangle hit.
        Border borderHit;
        try {
            borderHit = this.rectangle.pointLocation(collisionPoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        // Changes the velocity according to which side wass hit
        if (borderHit == Border.LEFT || borderHit == Border.RIGHT) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (borderHit == Border.TOP || borderHit == Border.BOTTOM) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (borderHit == Border.BRIGHT || borderHit == Border.BLEFT) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (borderHit == Border.TRIGHT || borderHit == Border.TLEFT) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return null;
        }
    }



    /**
     * Draws the rectangle that defines this block on the provided surface.
     * @param surface the surface the rectangle will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point topLeft = this.rectangle.getUpperLeft();
        surface.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                .rectangle.getHeight());
        surface.setColor(Color.BLACK);
        if (this.hp > 0) {
            surface.drawText((int) (this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 2)), (int) (this
                            .rectangle.getUpperLeft().getY() + (this.rectangle.getHeight()) / 1.5),
                    Integer.toString(this.hp), 18);
        } else {
            surface.drawText((int) (this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 2)), (int) (this
                            .rectangle.getUpperLeft().getY() + (this.rectangle.getHeight()) / 1.5), "X", 18);
        }
    }

    /**
     * Required as the class implements Collidable, does nothing (behaviour is through rectangle).
     */
    public void timePassed() { }
}

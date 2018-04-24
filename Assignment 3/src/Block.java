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
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color,int hp) {
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
        if (this.hp > 0) {
            this.hp = this.hp - 1;
        }
        Border borderHit = this.rectangle.pointLocation(collisionPoint);
        if (borderHit == Border.LEFT || borderHit == Border.RIGHT) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        else if (borderHit == Border.TOP || borderHit == Border.BOTTOM) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        else {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }


    /**
     * draws the rectangle that defines this block on the provided surface.
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
        }
        else {
            surface.drawText((int) (this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 2)), (int) (this
                            .rectangle.getUpperLeft().getY() + (this.rectangle.getHeight()) / 1.5), "X", 18);
        }
    }

    /**
     * does nothing lul
     */
    public void timePassed() {

    }
}

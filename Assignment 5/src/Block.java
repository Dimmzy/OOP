import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Block method that consists of rectangles that can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
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
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color =  color;
        this.hp = hp;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Adds the block to the gameLevel (as both a sprite and a collidable object).
     * @param gameLevel the gameLevel to add the block to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Calls the GameLevel class to remove this block from the gameLevel.
     * @param gameLevel The gameLevel object we remove this block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Adds a HitListener object to our list of HitListeners.
     * @param h1 The HitListener object to add.
     */
    public void addHitListener(HitListener h1) {
        this.hitListeners.add(h1);
    }

    /**
     * Removes a HitListener object from our list of HitListeners.
     * @param h1 The HitListener object to remove.
     */
    public void removeHitListener(HitListener h1) {
        this.hitListeners.remove(h1);
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
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.hp > 0) {
            this.hp = this.hp - 1;
        }
        this.notifyHit(hitter);
        // Calls pointLocation to return the enum of the side the rectangle got hit.
        Border borderHit;
        try {
            borderHit = this.rectangle.pointLocation(collisionPoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        // Changes the velocity according to which side was hit
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
        /*
         * Draws a black border around the blocks, excluding the border blocks and the information panel, which are
         * marked with -1 hp.
         */
        if (this.hp != -1) {
            surface.setColor(Color.BLACK);
            surface.drawRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                    .rectangle.getHeight());
        }
    }

    /**
     * Required as the class implements Collidable, does nothing (behaviour is through rectangle).
     */
    public void timePassed() { }

    /**
     * @return Returns the amount of health this block has remaining.
     */
    public int getHitPoints() {
        return this.hp;
    }

    /**
     * Notifies the listeners that the block has been hit and by which ball.
     * @param hitter The ball that hit the block.
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners =  new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

package collidables;

import biuoop.DrawSurface;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitters.Bullet;
import hitters.Velocity;
import listeners.BallRemover;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;


import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

import static java.awt.Color.RED;


/**
 * Block method that consists of rectangles that can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Rectangle startingPosition;
    private Color blockColor;
    private List<Image> alienImage;
    private double width;
    private double height;
    private Velocity alienSpeed;
    private int drawState;
    private double epochTime;
    private List<Block> currColumn;


    /**
     * Constructs a block with a given color and health.
     * @param upperLeft upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param alienSpeed the speed of the alien block.
     */
    public Block(Point upperLeft, double width, double height, Velocity alienSpeed) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.startingPosition = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<HitListener>();
        this.alienImage = new ArrayList<Image>();
        this.blockColor = null;
        this.width = width;
        this.height = height;
        this.alienSpeed = alienSpeed;
        this.drawState = 0;
        this.epochTime = System.currentTimeMillis();
    }

    /**
     * Constructs a block with a given color and health.
     * @param upperLeft upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param blockColor the color of the block (for regular blocks/shields).
     */
    public Block(Point upperLeft, double width, double height, Color blockColor) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.startingPosition = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<HitListener>();
        this.alienImage = null;
        this.blockColor = blockColor;
        this.width = width;
        this.height = height;
        this.alienSpeed = null;
        this.drawState = 0;
        this.epochTime = System.currentTimeMillis();
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
     * @param currentVelocity the velocity the object hit the block at.
     * @param hitter The ball that hit the block.
     * @param collisionRectangle the rectangle the ball collided with.
     * @return returns to the calling object it's velocity after the hit.
     */
    public Velocity hit(Bullet hitter, Rectangle collisionRectangle, Velocity currentVelocity) {
        // Ignores friendly fire if this is an alien
        this.notifyHit(hitter);
        return null;
    }

    /**
     * For alien blocks, sets the column field so it knows in which column it is.
     * @param currentColumn the column of the alien.
     */
    public void setColumn(List<Block> currentColumn) {
        this.currColumn = currentColumn;
    }

    /**
     * Draws the rectangle that defines this block on the provided surface.
     * @param surface the surface the rectangle will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        if (System.currentTimeMillis() - this.epochTime > 500) {
            this.epochTime = System.currentTimeMillis();
            if (this.drawState == 0) {
                this.drawState = 1;
            } else {
                this.drawState = 0;
            }
        }
        if (this.blockColor != null) {
            surface.setColor(this.blockColor);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.width, (int) this.height);
        } else if (!(this.alienImage.isEmpty())) {
            surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    this.alienImage.get(this.drawState));
        }
    }

    /**
     * Required as the class implements Collidable, does nothing (behaviour is through rectangle).
     * @param dt does nothing, static block
     */
    public void timePassed(double dt) { }



    /**
     * @return returns block width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Notifies the listeners that the block has been hit and by which ball.
     * @param hitter The ball that hit the block.
     */
    public void notifyHit(Bullet hitter) {
        List<HitListener> listeners =  new ArrayList<HitListener>(this.hitListeners);

        /*
        Checks if the bullet is red (alien bullet) and the target that was hit is also an alien, if it is, only
        removes the bullet.
         */
        if (hitter.getColor() == RED && this.currColumn != null) {
            for (HitListener listener : listeners) {
                if (listener instanceof BallRemover) {
                    listener.hitEvent(this, hitter);
                }
            }
            return;
        }
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
        // Removes alien from formation if this is an alien.
        if (this.currColumn != null) {
            this.currColumn.remove(this);
        }
    }

    /**
     * Resets the position of the block (by resetting the position of the rectangle) to the default one set when it
     * was constructed.
     */
    public void resetPosition() {
        this.rectangle = new Rectangle(this.startingPosition.getUpperLeft(), this.startingPosition.getWidth(),
                this.startingPosition.getHeight());
    }
    /**
     * Sets the block images.
     * @param images The block images list.
     */
    public void setAlienImage(List<Image> images) {
        this.alienImage = images;
    }
    /**
     * @return returns if the block is an alien block (if it's a part of a column).
     */
    public boolean isAlien() {
        return (this.currColumn != null);
    }


}

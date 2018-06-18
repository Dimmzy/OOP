package collidables;

import biuoop.DrawSurface;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitters.Ball;
import hitters.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;


import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Block method that consists of rectangles that can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private Color borderColor;
    private Map<Integer, Color> blockColor;
    private Map<Integer, Image> blockImages;
    private Color defFillColor;
    private Image defFillImage;
    private int hp;
    private double width;

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
     * @param hp the starting hit points of the block.
     */
    public Block(Point upperLeft, double width, double height, int hp) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.blockColor = new TreeMap<Integer, Color>();
        this.blockImages = new TreeMap<Integer, Image>();
        this.hp = hp;
        this.hitListeners = new ArrayList<HitListener>();
        this.defFillColor = null;
        this.defFillImage = null;
        this.width = width;
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
     * @param hitter The ball that hit the block.
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
        Point topLeft = this.rectangle.getUpperLeft();
        if (this.blockColor.containsKey(this.hp)) {
            surface.setColor(this.blockColor.get(this.hp));
            surface.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        } else if (this.blockImages.containsKey(this.hp)) {
            surface.drawImage((int) topLeft.getX(), (int) topLeft.getY(), this.blockImages.get(this.hp));
        } else if (this.defFillColor != null) {
            surface.setColor(this.defFillColor);
            surface.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        } else {
            surface.drawImage((int) topLeft.getX(), (int) topLeft.getY(), this.defFillImage);
        }

        if (this.borderColor != null) {
            surface.setColor(this.borderColor);
            surface.drawRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        } else {
            surface.drawImage((int) topLeft.getX(), (int) topLeft.getY(), this.defFillImage);
        }
    }

    /**
     * Required as the class implements Collidable, does nothing (behaviour is through rectangle).
     * @param dt does nothing, static block
     */
    public void timePassed(double dt) { }

    /**
     * @return Returns the amount of health this block has remaining.
     */
    public int getHitPoints() {
        return this.hp;
    }

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
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners =  new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Sets the block color.
     * @param blockColors The block color map.
     */
    public void setBlockColor(Map<Integer, Color> blockColors) {
        this.blockColor = blockColors;
    }

    /**
     * Sets the block images.
     * @param blockImage The block images map.
     */
    public void setBlockImage(Map<Integer, Image> blockImage) {
        this.blockImages = blockImage;
    }

    /**
     * Sets the borders color.
     * @param color The border color.
     */
    public void setBorderColor(Color color) {
        this.borderColor = color;
    }

    /**
     * Sets default border color.
     * @param color the def border color.
     */
    public void setDefFillColor(Color color) {
        this.defFillColor = color;
    }

    /**
     * Sets the default image of the block.
     * @param image The def image.
     */
    public void setDefFillImage(Image image) {
        this.defFillImage = image;
    }
}

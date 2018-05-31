package collidables;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitters.Ball;
import hitters.Velocity;
import sprites.Sprite;

import java.awt.Color;

/**
 * Player controlled Paddle class. A paddle is a sprite and a collidable object.
 */

public class Paddle implements Sprite, Collidable {

    private static final int SCREEN_WIDTH = 800, BORDER_WIDTH = 25;
    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Velocity velocity;

    /**
     * Constructs the paddle object.
     * @param keyboard The provided keyboard sensor from the game object that was created through the GUI.
     * @param paddleStart The starting upper left point of the paddle.
     * @param paddleWidth The width of the created paddle.
     * @param paddleSpeed The speed of the created paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Point paddleStart, int paddleWidth, int paddleSpeed) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(paddleStart.getX(), paddleStart.getY()), paddleWidth, 15);
        // Paddle movement is defined only on the X axis.
        this.velocity = new Velocity(paddleSpeed, 0);
    }

    /**
     * Displaces the paddle to the left.
     */
    public void moveLeft() {
        // Check if the paddle isn't going to pass the screen's edge, if it does, don't move.
        if (this.rectangle.getUpperLeft().getX() - velocity.getDx() < BORDER_WIDTH) {
            return;
        }
        // Otherwise moves the paddle to the left using the velocities deltaX.
        Point moveLeft = new Point(this.rectangle.getUpperLeft().getX() - velocity.getDx(), this.rectangle
                .getUpperLeft().getY());
        this.rectangle.setNewLocation(moveLeft);
    }

    /**
     * Displaces the paddle to the right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + velocity.getDx()
                > SCREEN_WIDTH - BORDER_WIDTH) {
            return;
        }
        Point moveRight = new Point(this.rectangle.getUpperLeft().getX() + velocity.getDx(), this.rectangle
                .getUpperLeft().getY());
        this.rectangle.setNewLocation(moveRight);
    }

    /**
     * Checks if there was any user input from the keyboard, and moves the paddle accordingly.
     */
    public void timePassed(double dt) {
        this.setVelocity(this.velocity.getDx() * dt, this.velocity.getDy() * dt);
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given surface.
     * @param d The surface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        Point topLeft = this.rectangle.getUpperLeft();
        d.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                .rectangle.getHeight());
        // Draws a black border around the paddle for better visbility.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                .rectangle.getHeight());
    }


    /**
     * @return Returns the paddle rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * The hit method of the paddle. Provides the hitting object with new velocity according to the area hit.
     * If the paddle has been hit on the top, it'll change the ball's angle using a pre-defined list according to
     * which fifth of the paddle has been hit.
     * @param collisionPoint The collision point of the object with the paddle.
     * @param currentVelocity The velocity that the object hits the paddle with.
     * @param hitter The ball object hitting the paddle.
     * @return returns a new velocity according to the area that was hit on the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Border borderHit;
        try {
            borderHit = this.rectangle.pointLocation(collisionPoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (borderHit == Border.LEFT || borderHit == Border.RIGHT) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (borderHit == Border.TOP) {
            // Ball stuck in paddle check and fix
            if (hitter.getY() < this.getCollisionRectangle().getUpperLeft().getY()) {
                hitter.setCenter(new Point(hitter.getX(), hitter.getY() - hitter.getVelocity().getDy()));
            }
            int dXHit = (int) (collisionPoint.getX() - this.rectangle.getUpperLeft().getX());
            if (dXHit < rectangle.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            } else if (dXHit < (rectangle.getWidth() / 5) * 2) {
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            } else if (dXHit < (rectangle.getWidth() / 5) * 3) {
                return Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
            } else if (dXHit < (rectangle.getWidth() / 5) * 4) {
                return Velocity.fromAngleAndSpeed(30 , currentVelocity.getSpeed());
            } else {
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        } else if (borderHit == Border.BOTTOM) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            // Object hits the edge of the paddle.
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    /**
     * Adds the paddle to the game as a collidable object and a sprite.
     * @param g The game object to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Sets the velocity of the paddle to a new value.
     * @param dx The displacement on the x axis each frame.
     * @param dy The displacement on the y axis each frame.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
}
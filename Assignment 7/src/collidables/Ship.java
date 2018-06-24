package collidables;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitters.Bullet;
import hitters.Velocity;
import sprites.Sprite;

import java.awt.Color;

/**
 * Player controlled Ship class. A paddle is a sprite and a collidable object.
 */

public class Ship implements Sprite, Collidable {

    private static final int SCREEN_WIDTH = 800, BORDER_WIDTH = 25;
    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Velocity velocity;
    private double deltaTime;

    /**
     * Constructs the spaceship object.
     * @param keyboard The provided keyboard sensor from the game object that was created through the GUI.
     * @param paddleStart The starting upper left point of the paddle.
     * @param paddleWidth The width of the created paddle.
     * @param paddleSpeed The speed of the created paddle.
     */
    public Ship(biuoop.KeyboardSensor keyboard, Point paddleStart, int paddleWidth, int paddleSpeed) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(paddleStart.getX(), paddleStart.getY()), paddleWidth, 15);
        // Ship movement is defined only on the X axis.
        this.velocity = new Velocity(paddleSpeed, 0);
    }

    /**
     * Displaces the paddle to the left.
     * @param dt the deltatime we use to normalize the paddle's speed.
     */
    public void moveLeft(double dt) {
        // Check if the paddle isn't going to pass the screen's edge, if it does, don't move.
        if (this.rectangle.getUpperLeft().getX() - this.velocity.getDx() * dt < BORDER_WIDTH) {
            return;
        }
        // Otherwise moves the paddle to the left using the velocities deltaX.
        Point moveLeft = new Point(this.rectangle.getUpperLeft().getX() - this.velocity.getDx() * dt,
                this.rectangle.getUpperLeft().getY());
        this.rectangle.setNewLocation(moveLeft);
    }

    /**
     * Displaces the paddle to the right.
     * @param dt the deltatime we use to normalize the paddle's speed.
     */
    public void moveRight(double dt) {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + this.velocity.getDx() * dt
                > SCREEN_WIDTH - BORDER_WIDTH) {
            return;
        }
        Point moveRight = new Point(this.rectangle.getUpperLeft().getX() + this.velocity.getDx() * dt,
                this.rectangle.getUpperLeft().getY());
        this.rectangle.setNewLocation(moveRight);
    }

    /**
     * Checks if there was any user input from the keyboard, and moves the paddle accordingly.
     * @param dt the deltatime we use to normalize the paddle's speed.
     */
    public void timePassed(double dt) {
        this.deltaTime = dt;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
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
     * @param hitter The bullet object hitting the spaceship.
     * @return returns a new velocity according to the area that was hit on the paddle.
     */
    public Velocity hit(Bullet hitter, Point collisionPoint, Velocity currentVelocity) {


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
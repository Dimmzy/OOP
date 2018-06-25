package collidables;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import gamelogic.GameEnvironment;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitters.Bullet;
import hitters.Velocity;
import sprites.Sprite;
import utilities.ExtractImage;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

/**
 * Player controlled Ship class. A paddle is a sprite and a collidable object.
 */

public class Ship implements Sprite, Collidable {

    private static final int SCREEN_WIDTH = 800, BORDER_WIDTH = 5;
    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Velocity velocity;
    private double timeSinceLastShot;
    private GameEnvironment ge;
    private GameLevel level;
    private Random rand;
    private Image shipImage;

    /**
     * Constructs the spaceship object.
     * @param keyboard The provided keyboard sensor from the game object that was created through the GUI.
     * @param paddleStart The starting upper left point of the paddle.
     * @param paddleWidth The width of the created paddle.
     * @param paddleSpeed The speed of the created paddle.
     * @param ge the game environment the ship is a part of.
     * @param level the level the ship is a part of.
     */
    public Ship(biuoop.KeyboardSensor keyboard, Point paddleStart, int paddleWidth, int paddleSpeed,
                GameEnvironment ge, GameLevel level) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(paddleStart.getX(), paddleStart.getY()), paddleWidth, 15);
        // Ship movement is defined only on the X axis.
        this.velocity = new Velocity(paddleSpeed, 0);
        this.timeSinceLastShot = 0;
        this.ge = ge;
        this.level = level;
        this.rand = new Random();
        this.shipImage = new ExtractImage("ship.png").getImage();
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
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (System.currentTimeMillis() - this.timeSinceLastShot >= 350) {
                this.timeSinceLastShot = System.currentTimeMillis();
                this.shoot();
            }
        }
    }

    /**
     * Commands the ship to shoot. Rolls a 1 out of 10 chance to shoot two bullets to the sides in addition to a
     * straight shot.
     */
    public void shoot() {
        // 5% chance to shoot two shots to the sides
        if (rand.nextInt(20) == 1) {
            Bullet shotOne = new Bullet(new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2,
                    this.rectangle.getUpperLeft().getY() - 0.1), 3, ge, Color.BLUE);
            shotOne.setVelocity(Velocity.fromAngleAndSpeed(45, 500));
            shotOne.addToGame(level);
            Bullet shotTwo = new Bullet(new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2,
                    this.rectangle.getUpperLeft().getY() - 0.1), 3, ge, Color.BLUE);
            shotTwo.setVelocity(Velocity.fromAngleAndSpeed(-45, 500));
            shotTwo.addToGame(level);
        }
        Bullet shot = new Bullet(new Point(this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2, this
                .rectangle.getUpperLeft().getY() - 0.1), 3, ge, Color.BLUE);
        shot.setVelocity(Velocity.fromAngleAndSpeed(0, 500));
        shot.addToGame(level);
    }

    /**
     * Draws the paddle on the given surface.
     * @param d The surface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        Point topLeft = this.rectangle.getUpperLeft();
        if (this.shipImage != null) {
            d.drawImage((int) topLeft.getX(), (int) topLeft.getY(), this.shipImage);
            return;
        }
        d.setColor(Color.BLUE);
        d.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                .rectangle.getHeight());
        // Draws a black border around the paddle for better visbility.
        d.setColor(Color.WHITE);
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
     * @param collisionRectangle The collision rectangle of the object that hit the paddle (won't be used).
     * @param currentVelocity The velocity that the object hits the paddle with.
     * @param hitter The bullet object hitting the spaceship.
     * @return returns a new velocity according to the area that was hit on the paddle.
     */
    public Velocity hit(Bullet hitter, Rectangle collisionRectangle, Velocity currentVelocity) {
        if (hitter.getColor() == Color.BLUE) {
            return null;
        }
        this.level.stopAnimation();
        this.level.getLivesCounter().decrease(1);
        return null;
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
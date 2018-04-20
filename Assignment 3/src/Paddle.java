import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class Paddle implements Sprite, Collidable {

    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Velocity velocity;

    /**
     * Constructs the paddle object.
     * @param keyboard the provided keyboard sensor from the game object.
     * @param ballStart the starting location of the ball (so we can place the paddle beneath it).
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Point ballStart) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(ballStart.getX(), ballStart.getY() + 5), 50, 10);
        this.velocity = new Velocity(5, 0);
    }

    /**
     * Displaces the paddle to the left.
     */
    public void moveLeft() {
        Point moveLeft = new Point(this.rectangle.getUpperLeft().getX() - velocity.getDx(), this.rectangle
                .getUpperLeft().getY());
        this.rectangle.setNewLocation(moveLeft);
    }

    /**
     * Displaces the paddle to the right.
     */
    public void moveRight() {
        Point moveRight = new Point(this.rectangle.getUpperLeft().getX() + velocity.getDx(), this.rectangle
                .getUpperLeft().getY());
        this.rectangle.setNewLocation(moveRight);
    }

    /**
     * Checks if there was any user input from the keyboard, and moves the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            return;
        }
    }

    /**
     * Draws the paddle on the given surface.
     * @param d the surface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        Point topLeft = this.rectangle.getUpperLeft();
        d.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
                .rectangle.getHeight());
    }


    /**
     * @return returns the paddle rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     *
     * @param collisionPoint
     * @param currentVelocity
     * @return
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        border borderHit = this.rectangle.pointLocation(collisionPoint);
        if (borderHit == border.LEFT || borderHit == border.RIGHT) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        else {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    /**
     * adds the paddle to the game as a collidable object and a sprite.
     * @param g the game object to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

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
        // Check if the paddle isn't going to pass the screen's edge, if it does, don't move.
        if (this.rectangle.getUpperLeft().getX() - velocity.getDx() < 15) {
            return;
        }
        Point moveLeft = new Point(this.rectangle.getUpperLeft().getX() - velocity.getDx(), this.rectangle
                .getUpperLeft().getY());
        this.rectangle.setNewLocation(moveLeft);
    }

    /**
     * Displaces the paddle to the right.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + velocity.getDx() > 785) {
            return;
        }
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
        Border borderHit = this.rectangle.pointLocation(collisionPoint);
        if (borderHit == Border.LEFT || borderHit == Border.RIGHT) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        else if (borderHit == Border.TOP) {
            int dXHit = (int) (collisionPoint.getX() - this.rectangle.getUpperLeft().getX());
            // Change it to keep the current speed instead of assuming it's 3
            if (dXHit < 10) {
                System.out.println("zone 1");
                return Velocity.fromAngleAndSpeed(300, 3);
            }
            else if (dXHit < 20) {
                System.out.println("zone 2");
                return Velocity.fromAngleAndSpeed(330, 3);
            }
            else if (dXHit < 30) {
                System.out.println("zone 3");
                return Velocity.fromAngleAndSpeed(0, 3);
            }
            else if (dXHit < 40) {
                System.out.println("zone 4");
                return Velocity.fromAngleAndSpeed(30 , 3);
            }
            else {
                System.out.println("zone 5");
                return Velocity.fromAngleAndSpeed(60, 3);
            }
        }
        else if (borderHit == Border.BOTTOM) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // Object hits the edge of the paddle
        else {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
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
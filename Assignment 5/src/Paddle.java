import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Player controlled Paddle class. A paddle is a sprite and a collidable object.
 */

public class Paddle implements Sprite, Collidable {

    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Velocity velocity;
    private int ballSpeed;

    /**
     * Constructs the paddle object.
     * @param keyboard The provided keyboard sensor from the game object that was created through the GUI.
     * @param ballStart The starting location of the ball (The paddle is created beneath the ball).
     * @param ballSpeed The starting speed of the ball. We store it so we can keep the speed the same between hits.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Point ballStart, int ballSpeed) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(ballStart.getX(), ballStart.getY() + 20), 50, 10);
        // Paddle movement is defined only on the X axis.
        this.velocity = new Velocity(5, 0);
        // We save the ball speed be able to keep it between paddle hits.
        this.ballSpeed = ballSpeed;
    }

    /**
     * Displaces the paddle to the left.
     */
    public void moveLeft() {
        // Check if the paddle isn't going to pass the screen's edge, if it does, don't move.
        if (this.rectangle.getUpperLeft().getX() - velocity.getDx() < 15) {
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
        }
    }

    /**
     * Draws the paddle on the given surface.
     * @param d The surface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        Point topLeft = this.rectangle.getUpperLeft();
        d.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) this.rectangle.getWidth(), (int) this
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
     * @param collisionPoint The collision point of the object with the paddle.
     * @param currentVelocity The velocity that the object hits the paddle with/
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
            int dXHit = (int) (collisionPoint.getX() - this.rectangle.getUpperLeft().getX());
            if (dXHit < 10) {
                return Velocity.fromAngleAndSpeed(300, this.ballSpeed);
            } else if (dXHit < 20) {
                return Velocity.fromAngleAndSpeed(330, this.ballSpeed);
            } else if (dXHit < 30) {
                return Velocity.fromAngleAndSpeed(0, this.ballSpeed);
            } else if (dXHit < 40) {
                return Velocity.fromAngleAndSpeed(30 , this.ballSpeed);
            } else {
                return Velocity.fromAngleAndSpeed(60, this.ballSpeed);
            }
        } else if (borderHit == Border.BOTTOM) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else {
            // Object hits the edge of the paddle.
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    /**
     * Adds the paddle to the game as a collidable object and a sprite.
     * @param g The game object to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
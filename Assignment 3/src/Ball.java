import biuoop.DrawSurface;

/**
 * Ball class. Holds the values that define a Ball object in our program and it's movement behavior.
 */

public class Ball implements Sprite {

    private Point center;
    private Point startingLoc;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * The Ball object constructor.
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param environment the game environment the ball is a part of.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.startingLoc = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = environment;
    }

    /**
     * Adds the ball to the game (as a sprite).
     * @param game the game object to add the ball to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Draws the ball using it's color and size on the given surface.
     * @param surface the surface (defined through the DrawSurface class) to draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Sets the velocity field of the ball using the given velocity parameter.
     * @param v the velocity to set the ball to.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity field of the ball by creating a new Velocity object using given dx and dy.
     * @param dx delta x of the required velocity.
     * @param dy delta y of the required velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Displaces the ball one "step" forward, it calculates whether the ball will be in bounds after the movement, if
     * it won't then it'll change it's movement direction and bounce it off the surface.
     */
    public void moveOneStep() {
        // If the balls speed is zero, do nothing.
        if (this.velocity == null) {
            return;
        }
        // Calculates the trajectory of the ball by checking it's end point after applying the velocity to the location.
        Point endPoint = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo collisionCheck = gameEnvironment.getClosestCollision(trajectory);
        // If the ball doesn't collide with anything, move it as it's velocity dictates.
        if (collisionCheck == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Rectangle colRect = collisionCheck.collisionObject().getCollisionRectangle();
            // Check if the collision occurs with the paddle.
            if (collisionCheck.collisionObject() instanceof Paddle) {
                // Checks if the ball is stuck inside of the paddle. If it is, free it.
                if (this.center.getY() > colRect.getUpperLeft().getY()
                        && this.center.getY() < colRect.getUpperLeft().getY() + colRect.getHeight()
                        && this.center.getX() < colRect.getUpperLeft().getX() + colRect.getWidth()
                        && this.center.getX() > colRect.getUpperLeft().getX()) {
                    // offsets the ball y location to outside the paddle using the paddle y value and the balls' dy.
                    this.center = new Point(this.center.getX(), colRect.getUpperLeft().getY() + this.velocity.getDy());
                }
            }
            // Calculates it's new direction after the hit and sets the ball to move in the new direction.
            Velocity newSpeed = collisionCheck.collisionObject().hit(collisionCheck.collisionPoint(), this.velocity);
            // If caught exception , reset the ball into it's starting position.
            if (newSpeed == null) {
                this.center = this.startingLoc;
                return;
            }
            this.velocity = newSpeed;
            this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * When timePassed is called, moves the ball by using moveOneStep.
     */
    public void timePassed() {
        this.moveOneStep();
    }


    /**
     * @return returns this object's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return returns this object's center point's x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return returns this object's center point's y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return returns this object's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return returns this object's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

}

import biuoop.DrawSurface;


// Check the ordering of the methods/accessors

/**
 * a Ball class that holds the values that define a Ball object in our program.
 */
public class Ball implements Sprite{

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;


    /**
     * The Ball object constructor.
     * @param center the center point of the ball.
     * @param r the raidus of the ball.
     * @param color the color of the ball.
     * @param environment the game environment the ball is a part of.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = environment;
    }

    /**
     * Adds the ball to the game (as a sprite)
     * @param game the game object to add the ball to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
    /**
     * Draws the ball using it's color and size on the surface.
     * @param surface the surface (defined through the DrawSurface class) to draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Sets the velocity field of the ball through the given velocity parameter.
     * @param v the velocity to set the ball to.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity field off the ball by creating a new Velocity object using given dx and dy.
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
        if (this.velocity == null) {
            return;
        }
        Point endPoint = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo collisionCheck = gameEnvironment.getClosestCollision(trajectory);
        if (collisionCheck == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        else {
            Velocity moveClose = new Velocity(Math.floor(this.velocity.getDx()), Math.floor(this.velocity.getDy()));
            moveClose.applyToPoint(this.center);
            this.velocity = collisionCheck.collisionObject().hit(collisionCheck.collisionPoint(), this.velocity);
            this.getVelocity().applyToPoint(this.center);
            return;
        }
    }

    /**
     *
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

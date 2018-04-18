import biuoop.DrawSurface;


// Check the ordering of the methods/accessors

/**
 * a Ball class that holds the values that define a Ball object in our program.
 */
public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Point resolution;
    private GameEnvironment gameEnvironment;


    /**
     *
     * @param center
     * @param r
     * @param color
     * @param environment
     * @param resolution
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment, Point resolution) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = environment;
        this.resolution = resolution;
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
        Point endPoint = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo collisionCheck = gameEnvironment.getClosestCollision(trajectory);
        if (collisionCheck == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        else {
            // Displaces the ball as closely as possible given it's velocity
            this.velocity = collisionCheck.collisionObject().hit(collisionCheck.collisionPoint(), this.velocity);
            this.getVelocity().applyToPoint(this.center);
            return;
        }
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

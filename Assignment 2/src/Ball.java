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
    private int bottomLeftBound;
    private int bottomRightBound;
    private int topLeftBound;
    private int topRightBound;

    /**
     * Constructor: Constructs a ball object according to the given parameters.
     * @param center the center point of the ball (defined using a point object).
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor: Constructs a ball with defined bounds that enclose it inside a shape.
     * @param center the center point of the ball (defined using a point object)
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param bottomLeftBound the bottom left bound of the frame.
     * @param bottomRightBound the bottom right bound of the frame.
     * @param topLeftBound the top left bound of the frame.
     * @param topRightBound the top right bound of the frame.
     */
    public Ball(Point center, int r, java.awt.Color color, int bottomLeftBound, int bottomRightBound, int
            topLeftBound, int topRightBound) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.bottomLeftBound = bottomLeftBound;
        this.bottomRightBound = bottomRightBound;
        this.topLeftBound = topLeftBound;
        this.topRightBound = topRightBound;
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
        if (velocity.getDx() < 0 && this.center.getX() - this.radius < bottomLeftBound) {
            this.setVelocity(-velocity.getDx(), velocity.getDy());
        } else if (velocity.getDx() > 0 && this.center.getX() + this.radius > bottomRightBound) {
            this.setVelocity(-velocity.getDx(), velocity.getDy());
        }
        if (velocity.getDy() < 0 && this.center.getY() - this.radius < topLeftBound) {
            this.setVelocity(velocity.getDx(), -velocity.getDy());
        } else if (velocity.getDy() > 0 && this.center.getY() + this.radius > topRightBound) {
            this.setVelocity(velocity.getDx(), -velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
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

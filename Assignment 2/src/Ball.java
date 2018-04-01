import biuoop.DrawSurface;


// Check the ordering of the methods/accessors

public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int topLeftBound;
    private int topRightBound;
    private int bottomLeftBound;
    private int bottomRightBound;

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    public void moveOneStep() {
        if (velocity.getDx() < 0 && this.center.getX() - this.radius <= topLeftBound) {
            this.setVelocity(-velocity.getDx(), velocity.getDy());
        }
        else if (velocity.getDx() > 0 && this.center.getX() + this.radius >= topRightBound) {
            this.setVelocity(-velocity.getDx(), velocity.getDy());
        }
        if (velocity.getDy() < 0 && this.center.getY() - this.radius <= bottomLeftBound) {
            this.setVelocity(velocity.getDx(), -velocity.getDy());
        }
        else if (velocity.getDy() > 0 && this.center.getY() + this.radius >= bottomRightBound) {
            this.setVelocity(velocity.getDx(), -velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    public void setBounds(int tL, int tR, int bL, int bR) {
        this.topLeftBound = tL;
        this.topRightBound = tR;
        this.bottomLeftBound = bL;
        this.bottomRightBound = bR;
    }


    public Velocity getVelocity() {
        return this.velocity;
    }

    public int getX() {
        return (int) this.center.getX();
    }

    public int getY() {
        return (int) this.center.getY();
    }

    public int getSize() {
        return this.radius;
    }

    public java.awt.Color getColor() {
        return this.color;
    }

}

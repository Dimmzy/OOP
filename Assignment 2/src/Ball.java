import biuoop.DrawSurface;


// Check the ordering of the methods/accessors

public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int screenWidth;
    private int screenHeight;

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
        if (velocity.getDx() < 0 && this.center.getX() - this.radius <= 0) {
            this.setVelocity(-velocity.getDx(), velocity.getDy());
        }
        else if (velocity.getDx() > 0 && this.center.getX() + this.radius >= screenWidth) {
            this.setVelocity(-velocity.getDx(), velocity.getDy());
        }
        if (velocity.getDy() < 0 && this.center.getY() - this.radius <= 0) {
            this.setVelocity(velocity.getDx(), -velocity.getDy());
        }
        else if (velocity.getDy() > 0 && this.center.getY() + this.radius >= screenHeight) {
            this.setVelocity(velocity.getDx(), -velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    public void setSize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
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

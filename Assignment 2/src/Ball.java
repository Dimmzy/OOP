import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

// Check the ordering of the methods/accessors

public class Ball {

    private Point center;
    private int radius;
    private java.awt.Color color;

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(),this.getY(),this.radius);
    }

    public void setVelocity(Velocity v) {

    }

    public void setVelocity(double dx, double dy) {

    }

    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    public Velocity getVelocity() {

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

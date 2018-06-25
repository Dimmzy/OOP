package hitters;

import biuoop.DrawSurface;
import collidables.CollisionInfo;
import gamelogic.GameEnvironment;
import gamelogic.GameLevel;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;

/**
 * Bullet class. Holds the values that define a Bullet object in our program and it's movement behavior.
 */

public class Bullet implements Sprite {

    private Point center;
    private int radius;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Color color;

    /**
     * The Bullet object constructor.
     * @param center The center point of the ball.
     * @param r The radius of the ball.
     * @param environment The game environment the ball is a part of.
     * @param color the color of the bullet.
     */
    public Bullet(Point center, int r, GameEnvironment environment, Color color) {
        this.color = color;
        this.center = center;
        this.radius = r;
        this.gameEnvironment = environment;
    }


    /**
     * Adds the ball to the gameLevel (as a sprite).
     * @param gameLevel the gameLevel object to add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Draws a white ball with black borders using it's  size on the given surface.
     * @param surface the surface (defined through the DrawSurface class) to draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Sets the velocity field of the ball using the given velocity parameter.
     * @param v the velocity to set the ball to.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * Displaces the ball one "step" forward, it calculates whether the ball will be in bounds after the movement, if
     * it won't then it'll change it's movement direction and bounce it off the surface.
     * @param dt Lets us displace the ball "dt" pixels per frame.
     */
    public void moveOneStep(double dt) {
        // If the balls speed is zero, do nothing.
        if (this.velocity == null) {
            return;
        }
        // Calculates the trajectory of the ball by checking it's end point after applying the velocity to the location.
        Point endPoint = new Point(this.getX() + (this.velocity.getDx() * dt * 1.25),
                this.getY() + (this.velocity.getDy() * dt * 1.25));
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo collisionCheck = gameEnvironment.getClosestCollision(trajectory);
        // If the ball doesn't collide w ith anything, move it as it's velocity dictates.
        if (collisionCheck == null) {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            // Ignores collisions with other aliens if an alien shot the bullet (remove for friendly fire)
            collisionCheck.collisionObject().hit(this, collisionCheck.collisionObject().getCollisionRectangle(),
                    this.velocity);
        }
    }

    /**
     * When timePassed is called, moves the ball by using moveOneStep.
     * @param dt Used to displace the ball per pixel.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
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
     * @return returns the bullet's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Removes the ball from the level.
     * @param gameLevel the level to remove the ball from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }


}

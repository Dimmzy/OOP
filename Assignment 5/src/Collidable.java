/**
 * Collidable objects interface.
 */
public interface Collidable {

    /**
     * @return Returns the collision triangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Tells the object that it has been hit.
     * @param hitter The ball that hit the collidable block.
     * @param collisionPoint The point the object has been hit with.
     * @param currentVelocity The velocity the object has been hit with.
     * @return Returns the changed velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}

package collidables;


import geometry.Rectangle;
import hitters.Bullet;
import hitters.Velocity;


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
     * @param collisionRectangle The rectangle of the object that was hit.
     * @param currentVelocity The velocity the object has been hit with.
     * @return Returns the changed velocity after the hit.
     */
    Velocity hit(Bullet hitter, Rectangle collisionRectangle, Velocity currentVelocity);
}

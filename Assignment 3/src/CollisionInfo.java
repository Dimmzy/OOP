/**
 * Contains information about the collision between two objects.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs the collision object from the collision point and the object that was collided with.
     * @param colPoint
     * @param collObject
     */
    public CollisionInfo(Point colPoint, Collidable collObject) {
        this.collisionPoint = colPoint;
        this.collisionObject = collObject;
    }
    /**
     * @return returns the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return returns the object that has been collided with.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
import java.util.List;
import java.util.ArrayList;

/**
 * A collection of objects we can collide with.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Game Environment constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Adds a collidable object into our list.
     * @param c with collidable object we'll append to our list.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes the passed collidable from the collidables list.
     * @param c the collidable to remove.
     */
    public void removeCollidalbe(Collidable c) {collidables.remove(c);}

    /**
     * Uses the passed trajectory line and loops over our collidable objects, finding collisions.
     * @param trajectory the trajectory of the ball we'll use to figure the collision point
     * @return If found, returns the collision point. Otherwise, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (int i = 0; i < collidables.size(); i++) {
            // Creates a point from the output of the closest intersection method of the trajectory line.
            Point colPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            if (colPoint != null) {
                return new CollisionInfo(colPoint, collidables.get(i));
            }
        }
        return null;
    }

    /**
     * @return returns the list containing the collidable objects in the game environment
     */
    public List<Collidable> getObjectList() {
        return this.collidables;
    }
}
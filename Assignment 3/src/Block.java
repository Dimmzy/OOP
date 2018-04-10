public class Block implements Collidable, Rectangle {

    private Rectangle rectangle;

    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

    }
}

import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * SpriteCollection class. Contains a list of all of the created sprites in the game.
 */
public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * SpriteCollection constructor. Creates the list we will populate with sprites.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds a sprite to the list.
     * @param s The passed Sprite object to add to the list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Iterates through all of the list elements and calls the "timePassed" method that tells them a certain interval
     * has passed and let's them change their behavior/state.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Iterates through all of the list elements and calls the "drawOn" method to draw the sprite on the provided
     * surface.
     * @param d The surface on which we'll drive the Sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}
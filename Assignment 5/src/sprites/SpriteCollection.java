package sprites;

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
     * Removes the passed sprite from the sprite list.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }


    /**
     * Iterates through all of the list elements and calls the "timePassed" method that tells them a certain interval
     * has passed and let's them change their behavior/state.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Iterates through all of the list elements and calls the "drawOn" method to draw the sprite on the provided
     * surface.
     * @param d The surface on which we'll drive the Sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * @return Returns the current list of sprites.
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }
}
package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Background color class, in charge of drawing the level backgrounds.
 */
public class BackgroundColor implements Sprite {

    private Color backgroundColor;

    /**
     * Background construtor.
     * @param color The Color of the background.
     */
    public BackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    /**
     * Draws the background for the level.
     * @param d The surface tod raw the object on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    /**
     * Does nothing (static image).
     * @param dt not used.
     */
    @Override
    public void timePassed(double dt) {

    }
}

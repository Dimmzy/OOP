package sprites;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * A class used for drawing a background image for the level.
 */
public class BackgroundImage implements Sprite {

    private Image backgroundImage;

    /**
     * Constructor, receives a file name and extracts the image file.
     * @param fileName the filename of the image.
     */
    public BackgroundImage(String fileName) {
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
            this.backgroundImage = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("I/O Exception : Unable to read image file");
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println("I/O Exception: Unable to close image file");
                e.printStackTrace();
            }
        }
    }

    /**
     * Draws the image using the drawImage method of the biuoop library.
     * @param d The surface tod raw the object on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, backgroundImage);
    }

    /**
     * Not used, static image.
     * @param dt not used.
     */
    @Override
    public void timePassed(double dt) {

    }
}

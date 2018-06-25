package utilities;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * A class used for drawing a background image for the level.
 */
public class ExtractImage {

    private Image alienImage;

    /**
     * Constructor, receives a file name and extracts the image file.
     * @param fileName the filename of the image.
     */
    public ExtractImage(String fileName) {
        InputStream is = null;
        for (int i = 1; i <= 2; i++) {
            try {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
                this.alienImage = ImageIO.read(is);
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
    }

    /**
     * @return returns the image that was extracted from the passed file.
     */
    public Image getImage() {
        return this.alienImage;
    }

}

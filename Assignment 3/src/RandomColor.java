import java.util.Random;
import java.awt.Color;

public class RandomColor {

    // Need to check that the color doesn't match the background color
    /**
     * Generates a random color to the calling class
     * @return
     */
    public static Color generateRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }
}

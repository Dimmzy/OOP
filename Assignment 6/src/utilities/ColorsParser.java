package utilities;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * Parses a string to extract the color is specifies.
 */
public class ColorsParser {

    /**
     * Method that checks the substring and extracts the needed Color information.
     * @param s The string we extract the info from.
     * @return the color the string specifies.
     */
    public java.awt.Color colorFromString(String s) {
        Color color;
        if (s.contains("RGB")) {
            String[] colorCode = (s.substring(10, s.length() - 2)).split(",");
            int x = Integer.parseInt(colorCode[0]);
            int y = Integer.parseInt(colorCode[1]);
            int z = Integer.parseInt(colorCode[2]);
            color = new Color(x, y, z);
        } else {
            String colorString = s.substring(6, (s.length() - 1));
            try {
                Field field = Class.forName("java.awt.Color").getField(colorString);
                color = (Color) field.get(null);
            } catch (Exception e) {
                color = null;
            }
        }
        if (color != null) {
            return color;
        } else {
            System.out.println("Invalid color specified");
            return null;
        }
    }
}

package animation;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import utilities.ExtractImage;

import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

/**
 * The menu animation class is in charge of displaying the menu animation.
 * @param <T> the return parameter of the menu.
 */
public class MenuAnimation<T> implements Menu<T> {

    private static final int X_TEXT = 280;
    private static final int Y_OFFSET = 50;
    private KeyboardSensor keySensor;
    private String menuTitle;
    private List<MenuOptions<T>> optionList;
    private T status;
    private boolean stop;
    private Image menuBackground;


    /**
     * Constructs the menu animation using a specified title and a keyboard sensor.
     * @param menuTitle the title of the menu.
     * @param keySensor the keyboard sensor.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keySensor) {
        this.menuTitle = menuTitle;
        this.keySensor = keySensor;
        this.optionList = new ArrayList<MenuOptions<T>>();
        this.status = null;
        this.stop = false;
        this.menuBackground = new ExtractImage("menuBackground.png").getImage();
    }

    /**
     * Adds a selection to the menu.
     * @param key The key to select the option.
     * @param message The message we'll write on the screen.
     * @param returnVal the return value of the selection.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.optionList.add(new MenuOptions<T>(key, message, returnVal));
    }


    /**
     * @return returns the status of the menu's task.
     */
    public T getStatus() {
        return this.status;
    }


    /**
     * Draws the menu on the surface.
     * @param d The DrawSurface we'll draw the sprites on.
     * @param dt Frame dependant speed.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int yText = 70;
        d.drawImage(0, 0, menuBackground);
        d.setColor(Color.GREEN);
        d.drawText(X_TEXT, yText, menuTitle, 40);
        d.setColor(Color.GREEN);
        d.drawText(X_TEXT - 1, yText - 1, menuTitle, 40);
        yText += yText * 2;
        // Draws the animation
        for (MenuOptions<T> menuOp : optionList) {
            yText += Y_OFFSET;
            d.setColor(Color.WHITE);
            d.drawText(X_TEXT, yText, "(" + menuOp.getKey().toUpperCase() + ") " + menuOp.getMessage(), 30);
        }
        // Checks for key press
        for (MenuOptions<T> menuOp : optionList) {
            if (keySensor.isPressed(menuOp.getKey())) {
                this.status = menuOp.getReturnVal();
                this.stop = true;
                break;
            }
        }
    }

    /**
     * Restarts the menu (in case we need to go back to the main menu instead of the submenu).
     */
    public void restart() {
        this.stop = false;
    }

    /**
     * @return returns whether we should stop drawing the menu.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}

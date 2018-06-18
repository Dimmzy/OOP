package animation;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The menu animation class is in charge of displaying the menu animation.
 * @param <T> the return parameter of the menu.
 */
public class MenuAnimation<T> implements Menu<T> {

    private static final int X_TEXT = 300;
    private static final int Y_OFFSET = 40;
    private KeyboardSensor keySensor;
    private String menuTitle;
    private List<MenuOptions<T>> optionList;
    private Map<String, Menu<T>> subMenu;
    private T status;
    private boolean stop;


    /**
     * Constructs the menu animation using a specified title and a keyboard sensor.
     * @param menuTitle the title of the menu.
     * @param keySensor the keyboard sensor.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keySensor) {
        this.menuTitle = menuTitle;
        this.keySensor = keySensor;
        this.optionList = new ArrayList<MenuOptions<T>>();
        this.subMenu = new TreeMap<String, Menu<T>>();
        this.status = null;
        this.stop = false;
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
        int yText = 100;
        d.setColor(Color.RED);
        d.drawText(X_TEXT, yText, menuTitle, 30);
        // Draws the animation
        for (MenuOptions<T> menuOp : optionList) {
            yText += Y_OFFSET;
            d.drawText(X_TEXT, yText, menuOp.getKey().toUpperCase() + ". " + menuOp.getMessage(), 30);
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


    /**
     * Adds a submenu to the main menu.
     * @param key The key to enter the menu.
     * @param message The message written in the menu,
     * @param subMen The submenu itself that returns as a menu object.
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMen) {
        this.optionList.add(new MenuOptions<T>(key, message, subMen));
        this.subMenu.put(key, subMen);
    }
}

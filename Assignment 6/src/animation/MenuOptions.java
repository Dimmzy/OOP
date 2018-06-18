package animation;

/**
 * Class that saves the options of a menu.
 * @param <T> the status of the menu task.
 */
public class MenuOptions<T> {

    private String key;
    private String message;
    private T returnVal;
    private Menu<T> subMenu;

    /**
     * Constructs a menu option.
     * @param key the key to enter the option.
     * @param message the message of the option.
     * @param returnVal the return value of the option's task.
     */
    public MenuOptions(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * Constructs a menu option (for the submenu only).
     * @param key the key to enter the submenu.
     * @param message the message of the submenu.
     * @param subMenu the submenu itself.
     */
    public MenuOptions(String key, String message, Menu<T> subMenu) {
        this.key = key;
        this.message = message;
        this.subMenu = subMenu;
    }

    /**
     * @return returns the option's key to enter.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return returns the options message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return returns the options value.
     */
    public T getReturnVal() {
        return this.returnVal;
    }

}


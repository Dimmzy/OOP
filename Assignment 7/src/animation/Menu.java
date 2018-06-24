package animation;

/**
 * Interface that composes a menu.
 * @param <T> the return value of the menu. (it's status).
 */
public interface Menu<T> extends Animation {
    /**
     * Adds a selection a user can pick to the menu.
     * @param key The key to select the option.
     * @param message The message we'll write on the screen.
     * @param returnVal the return value of the selection.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return returns the status of the option.
     */
    T getStatus();

    /**
     * Adds a selection submenu (for different level difficulties).
     * @param key The key to enter the menu.
     * @param message The message written in the menu,
     * @param subMenu The submenu itself that returns as a menu object.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
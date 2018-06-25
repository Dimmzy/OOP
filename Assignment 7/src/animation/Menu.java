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

}
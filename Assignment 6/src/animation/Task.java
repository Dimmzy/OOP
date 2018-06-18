package animation;

/**
 * The task interface, let's us build an operation that returns a generic variable for different operations.
 * @param <T> the return value of each task (mostly null).
 */
public interface Task<T> {
    /**
     * @return runs the task and returns it's return value.
     */
    T run();
}